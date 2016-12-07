package com.supermap.sppm.yxcms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.supermap.sppm.yxcms.dao.BaseDao;
import com.supermap.sppm.yxcms.pojo.DetailOrderForm;
import com.supermap.sppm.yxcms.pojo.TCustomer;
import com.supermap.sppm.yxcms.pojo.TMaterialType;
import com.supermap.sppm.yxcms.pojo.TOrderForm;
import com.supermap.sppm.yxcms.utils.Common;
public class OrderFormDaoImpl extends BaseDao {
	

	@SuppressWarnings("unchecked")
	public List findAllOrder(){
		try {
			List list = this.findAll(TOrderForm.class);
			return getDetailOrderFormList(list);
		} catch (RuntimeException re) {			
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List findNoAssignedOverOrder(){
		List list = getSession().createQuery( "from " + TOrderForm.class.getName() + " s where s.unAssignedNum != 0" ).list();
		return getDetailOrderFormList(list);
	}
	
	/**
	 * @author xiayaoxing
	 * 通过state查找订单
	 */
	@SuppressWarnings("unchecked")
	public List findOrderByState(int state){
		try {
			List list = getSession().createQuery( "from " + TOrderForm.class.getName() + " s where s.state = ?" ).setParameter(0, state).list();
			return getDetailOrderFormList(list);
		} catch (RuntimeException re) {			
			throw re;
		}
	}
	
	/**
	 * @author xiayaoxing
	 * @param withDetail:是否需要获得订单细节数据
	 * 通过state查找订单
	 */
	@SuppressWarnings("unchecked")
	public List findOrderById(int id,boolean withDetail){
		try {
			List list = getSession().createQuery( "from " + TOrderForm.class.getName() + " s where s.id = ?" ).setParameter(0, id).list();
			if(withDetail){
				return getDetailOrderFormList(list);
			}else {
				return list;
			}
		} catch (RuntimeException re) {			
			throw re;
		}
	}
	
	/**
	 * @author liyutao
	 * @param userType
	 * 通过用户类型自动生成最新的订单编码
	 * @return 最新的订单编码
	 */
	@SuppressWarnings("unchecked")
	public String nextFormCode(String userId){
		List list = null;
		Integer temp = Integer.parseInt(userId);
		String dateStr = Common.getSysTime("yyyyMMdd");
		String code = "";
		if(temp<10){
			code = "YXYS00"+userId+dateStr;
		}else if(temp<100){
			code = "YXYS0"+userId+dateStr;
		}else{
			code = "YXYS"+userId+dateStr;
		}
		try {
			list = getSession().createQuery( "from " + TOrderForm.class.getName() + " s where s.formCode like '%"+code+"%' order by formCode desc" ).list();
		} catch (RuntimeException re) {			
			throw re;
		}
		if(list!=null&&list.size()!=0){
			String formCode = ((TOrderForm)list.get(0)).getFormCode();
			String str = formCode.substring(formCode.length()-4);
			return code+Common.lastCode(str);
		}else{
			return code+"0001";
		}
	}

	/**
	 * @author liyutao
	 * 查找没有报价的订单
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public List loadNoPriceForm() throws Exception{
		try {
			List list = getSession().createQuery( "from " + TOrderForm.class.getName() + " s where s.isGivePrice=0 and s.makeType=1  order by formCode desc" ).list();
			return getDetailOrderFormList(list);
		} catch (Exception re) {	
			throw re;
		}
	}
	
	/**
	 * @author liyutao
	 * 查找没有从门店收取钱的订单
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List loadNoGetForm(){
		try {
			List list = getSession().createQuery( "from " + TOrderForm.class.getName() + " s where s.isGetMoney=0 and s.makeType=1 and s.isGivePrice = 1  order by formCode desc" ).list();
			return getDetailOrderFormList(list);
		} catch (RuntimeException re) {			
			throw re;
		}
	}
	/**
	 * @author liyutao
	 * 查找工厂外协订单还没有付钱的订单
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List loadNoGiveForm(){
		try {
			List list = getSession().createQuery( "from " + TOrderForm.class.getName() + " s where s.isGiveMoney=0 and s.isPart=2   order by formCode desc" ).list();
			return getDetailOrderFormList(list);
		} catch (RuntimeException re) {			
			throw re;
		}
	}
	/**
	 * @author liyutao
	 * @param formCode 订单编码
	 * 通过订单编码查找订单
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public TOrderForm getOrderFormByCode(String formCode){
		List list = getSession().createQuery( "from " + TOrderForm.class.getName() + " s where s.formCode=? order by formCode desc" ).setString(0, formCode).list();
		if(list!=null&&list.size()!=0){
			return (TOrderForm)list.get(0);
		}else{
			return null;
		}
		
	}
	
	/**
	 * @author liyutao
	 * @param para
	 * 保存订单,对订单数据进行处理,设置一些默认值
	 * @return
	 */
	public Object saveForm(String para){
		TOrderForm of = null;
		
		try {
			Gson gson = new Gson();
			of = (TOrderForm)gson.fromJson(para, TOrderForm.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		if(of==null) return null;
		if(of.getGetMoneyDate()!=null&&of.getGetMoneyDate().trim().equalsIgnoreCase("1")){
			of.setGetMoneyDate(Common.getSysTime("yyyy-MM-dd"));
		}
		if(of.getGiveMoneyDate()!=null&&of.getGiveMoneyDate().trim().equalsIgnoreCase("1")){
			of.setGiveMoneyDate(Common.getSysTime("yyyy-MM-dd"));
		}
		
		if(of.getId()==null||of.getId()==0){
			if(of.getFormDate()==null||of.getFormDate().trim()==""||of.getFormDate().trim().equalsIgnoreCase("null")){
				of.setFormDate(Common.getSysTime("yyyy-MM-dd"));
			}
			if(of.getRemainPage()==null||of.getRemainPage()==0){
				of.setRemainPage(of.getPagination()*of.getProductCount());
			}
			if(of.getUnAssignedNum()==null||of.getUnAssignedNum()==0){
				of.setUnAssignedNum(of.getPagination()*of.getProductCount());
			}
		}
		System.out.println("aa");
		return super.save(of);
	}
	
	
	
	/**
	 * 把TOrderForm对象列表转换为DetailOrderForm对象列表
	 * @param ls
	 * @return
	 */
	private List<DetailOrderForm> getDetailOrderFormList(List<TOrderForm> ls){
		List<DetailOrderForm> rs = new ArrayList<DetailOrderForm>();
		for(TOrderForm tof:ls){
			DetailOrderForm dof = getDetailOrderFormFromTOrderForm(tof);
			rs.add(dof);
		}
		return rs;
	}
	
	/**
	 * 把TOrderForm对象转换为DetailOrderForm对象
	 * @param tof
	 * @return
	 */
	private DetailOrderForm getDetailOrderFormFromTOrderForm(TOrderForm tof){
		Gson json = new Gson();
		String str = json.toJson(tof);
		DetailOrderForm dof = json.fromJson(str, DetailOrderForm.class);
		dof.computeAllPageNum();
		dof.computeProducedNum();
		dof.resetStateName();
		CustomerDaoImpl customerDao = new CustomerDaoImpl();
		MaterialTypeDaoImpl materialDao = new MaterialTypeDaoImpl();
		TCustomer tc =  (TCustomer)customerDao.findById(TCustomer.class, dof.getCustomerId());
		TMaterialType mt = (TMaterialType)materialDao.findById(TMaterialType.class, dof.getPaperId());
		dof.setCusName(tc.getCusName());
		dof.setPaperDetail(mt.getMname()+mt.getMsize());
		dof.setPrintTypeName(dof.getPrintType());
		dof.setBindTypeName(dof.getBindtype());
		dof.setMakeTypeName(dof.getMakeType());
		return dof;
	}
	
	@SuppressWarnings("unchecked")
	public List getOrderFormByUserId(Integer userId){
		List lsList = getSession().createQuery( "from " + TOrderForm.class.getName() + " s where s.userId =? order by formCode desc" ).setParameter(0, userId).list(); 
		return getDetailOrderFormList(lsList);
	}
	
	
	
	//业务逻辑===========================================================>>>
	/**
	 * 分配生产单后, 需要修改对应的订单的未分配数量
	 * 修改订单的未分配数:  未分配=未分配-本次分配数
	 * xiayaoxing
	 */
	@SuppressWarnings("unchecked")
	public int distribute(int id,int assignedNum){
		List ls = findOrderById(id,false);
		TOrderForm of = (TOrderForm) ls.get(0);
		int unAssignedNum = of.getUnAssignedNum();		
		if(unAssignedNum >= assignedNum){
			of.setUnAssignedNum(unAssignedNum - assignedNum);		//重置未分配数
			this.save(of);
		}else {
			return -1;
		}
		return of.getId();
	}
	//业务逻辑===========================================================<<<
}
