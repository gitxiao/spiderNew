package com.supermap.sppm.yxcms.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chunfeng.utils.ReflectCopy;
import com.google.gson.Gson;
import com.supermap.sppm.yxcms.dao.BaseDao;
import com.supermap.sppm.yxcms.pojo.DetailMakeList;
import com.supermap.sppm.yxcms.pojo.DetailOrderForm;
import com.supermap.sppm.yxcms.pojo.TCustomer;
import com.supermap.sppm.yxcms.pojo.TMakeList;
import com.supermap.sppm.yxcms.pojo.TWorkerInfo;
public class MakeListDaoImpl extends BaseDao {
	
	@SuppressWarnings("unchecked")
	public List findAllMakeList() throws Exception{
		try {
			List list = this.findAll(TMakeList.class);
			return getDetailList(list);
		} catch (RuntimeException re) {			
			throw re;
		}
	}

	/**
	 * 查询指定state的生产单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Object findMakeListByState(int state) throws Exception{
		try {
			List list = getSession().createQuery( "from " + TMakeList.class.getName() + " s where s.stateSc = ?" ).setParameter(0, state).list();
			return getDetailList(list);
		} catch (RuntimeException re) {			
			throw re;
		}
	}
	
	/**
	 * 查询指定state的生产单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Object findNoBreakMakeList() throws Exception{
		try {
			List list = getSession().createQuery( "from " + TMakeList.class.getName() + " s where s.stateSc = 0  or s.stateSc = 2" ).list();
			return getDetailList(list);
		} catch (RuntimeException re) {			
			throw re;
		}
	}
	/**
	 * @author liyutao
	 * @param id
	 * 通过ID查找生产单的详细情况
	 * @return
	 */
	public  Object findDetailMakeListById(Integer id){
		try {
			TMakeList tml =  (TMakeList)super.findById(TMakeList.class, id);
			return  getDetailFromTFather(tml);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 中断生产单(暂时不用)
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Object stopMakeListById(int id) throws Exception{
		try {
			TMakeList tml = (TMakeList) this.findById(TMakeList.class, id);
			tml.setStateSc(TMakeList.STATE_FINISHED);
			this.save(tml);
			return getDetailFromTFather(tml);
		} catch (RuntimeException re) {			
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	private List getDetailList(List<DetailMakeList> ls) throws Exception{
		List rs = new ArrayList();
		for(TMakeList tm:ls){
			DetailMakeList dt = getDetailFromTFather(tm);
			rs.add(dt);
		}
		return rs;
	}
	
	
	/**
	 * 保存生产单,如果是第一次保存, 先由userId生成生产单编号
	 * 保存完成后, 需要修改对应的订单的未分配数
	 * @throws Exception 
	 */
	public Object saveMakeList(String jsonPara,String userId) throws Exception{
		Gson json = new Gson();
		TMakeList ml = json.fromJson(jsonPara, TMakeList.class);
		String code = nextFormCode(userId);
		ml.setMakeCode(code);						//设置生产单编号
		ml.setStateSc(TMakeList.STATE_PRODUCING);	//设置生产单状态
		
		//保存生产单前先判断订单中是否有足够数量的未分配任务
		int assignedNum = ml.getPlanPage();			//生产单计划生产数, 也就是分配数
		int orderId = ml.getFid();
		OrderFormDaoImpl ofdi = new OrderFormDaoImpl();
		int distributeResult = ofdi.distribute(orderId,assignedNum);
		if(distributeResult != -1){
			TMakeList savedList =  (TMakeList) this.save(ml);
			return getDetailFromTFather(savedList);		//返回带详细信息的生产单
		}else{
			return TMakeList.DISTRIBUTE_FAIL;
		}
	}
	
	
	
	/**
	 * 获取生产单详情
	 */
	@SuppressWarnings("unchecked")
	private DetailMakeList getDetailFromTFather(TMakeList tm) throws Exception{
		Gson json = new Gson();
		String str = json.toJson(tm);
		DetailMakeList dt = json.fromJson(str, DetailMakeList.class);
		dt.setStateScName(dt.getStateSc());		//根据状态设置状态名
		
		int orderId = tm.getFid();
		OrderFormDaoImpl ofdi = new OrderFormDaoImpl();
		List ls = ofdi.findOrderById(orderId,true);
		DetailOrderForm dof = (DetailOrderForm) ls.get(0);
		dof.initDetailData();					//订单数据,由数据库中存储的数据计算一些其他数据
		
		CustomerDaoImpl customerDao = new CustomerDaoImpl();
		TCustomer tc =  (TCustomer)customerDao.findById(TCustomer.class, dof.getCustomerId());
		dof.setCusName(tc.getCusName());		//由客户id获取订单客户名
		
		int printerId = dt.getPrinterId();
		int binderId = dt.getBindId();
		WorkerInfoDaoImpl widiDaoImpl = new WorkerInfoDaoImpl();
		TWorkerInfo printer = (TWorkerInfo) widiDaoImpl.findById(TWorkerInfo.class, printerId);
		dt.setPrinterName(printer.getWname());	//印刷工名字
		TWorkerInfo binder = (TWorkerInfo) widiDaoImpl.findById(TWorkerInfo.class, binderId);
		dt.setBinderName(binder.getWname());	//装订工名字
		
		ReflectCopy.copyProperties(dof, dt);
		return dt;
	}

	
	/**
	 * @author xiayaoxing
	 * @param userType
	 * 生成最新的生产单编码
	 * @return 最新的订单编码
	 */
	@SuppressWarnings("unchecked")
	public String nextFormCode(String userId){
		List list = null;
		Integer temp = Integer.parseInt(userId);
		String dateStr = getSysTime("yyyyMMdd");
		String code = "";
		if(temp<10){
			code = "YXSC00"+userId+dateStr;
		}else if(temp<100){
			code = "YXSC0"+userId+dateStr;
		}else{
			code = "YXSC"+userId+dateStr;
		}
		try {
			list = getSession().createQuery( "from " + TMakeList.class.getName() + " s where makeCode like '%"+code+"%' order by makeCode desc" ).list();
		} catch (RuntimeException re) {			
			throw re;
		}
		if(list!=null&&list.size()!=0){
			String lastcode = ((TMakeList)list.get(0)).getMakeCode();
			String str = lastcode.substring(lastcode.length()-4);
			return code+tempCode(str);
		}else{
			return code+"0001";
		}
	}
	
	/**
	 * @author xiayaoxing
	 * @param code
	 * 生成最新的生产单编号的后四位
	 * @return
	 */
	private String tempCode(String code){
		String str = code.substring(code.length()-4);
		Integer num = Integer.parseInt(str);
		num++;
		if(num<10){
			return "000"+num;
		}else if(num<100){
			return "00"+num;
		}else if(num<1000){
			return "0"+num;
		}else{
			return String.valueOf(num);
		}
	}
	
	/**
	 * @@author xiayaoxing
	 * @param dateFormat
	 * 获取系统日期
	 * @return
	 */
	private String getSysTime(String dateFormat){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(new Date());
	}
	
}
