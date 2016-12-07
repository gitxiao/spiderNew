package com.supermap.sppm.yxcms.ws;


import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.chunfeng.utils.LogMsg;
import com.supermap.sppm.yxcms.dao.impl.MakeListDaoImpl;
import com.supermap.sppm.yxcms.dao.impl.MaterialListDaoImpl;
import com.supermap.sppm.yxcms.dao.impl.OrderFormDaoImpl;
import com.supermap.sppm.yxcms.pojo.TMakeList;
import com.supermap.sppm.yxcms.pojo.TMaterialList;
import com.supermap.sppm.yxcms.pojo.TOrderForm;
import com.supermap.sppm.yxcms.utils.Common;

@Stateless
@Path("makelist")
public class MakeListService {
	//private static final Logger log = Logger.getLogger(OrderFormService.class);
	private static final MakeListDaoImpl makeDao = new MakeListDaoImpl();
	private static final OrderFormDaoImpl orderDao = new OrderFormDaoImpl();
	private static final MaterialListDaoImpl mlDao = new MaterialListDaoImpl();
	
	@POST
	@Path("findAllMakeList")
	@Produces("application/json")
	public Object findAllMakeList(){
		try {
			return makeDao.findAllMakeList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@POST
	@Path("findMakeListByState")
	@Produces("application/json")
	public Object findMakeListByState(@FormParam("state") Integer state){
		try {
			return makeDao.findMakeListByState(state);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@POST
	@Path("findNoBreakMakeList")
	@Produces("application/json")
	public Object findNoBreakMakeList(){
		try {
			return makeDao.findNoBreakMakeList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@POST
	@Path("findMakeListById")
	@Produces("application/json")
	public Object findMakeListById(@FormParam("id") Integer id){
		return makeDao.findById(TMakeList.class, id);
	}
	
	@POST
	@Path("findDetailMakeListById")
	@Produces("application/json")
	public Object findDetailMakeListById(@FormParam("id") Integer id){
		return makeDao.findDetailMakeListById(id);
	}
	
	@POST
	@Path("saveMakeList")
	@Produces("application/json")
	public Object saveMakeList(@FormParam("para") String para,@FormParam("userId") String userId){
		TMakeList tof;
		try {
			tof = (TMakeList)makeDao.saveMakeList(para,userId);
			LogMsg.print("para,userId", para,userId);
//			return String.valueOf(tof.getId());
			return tof;
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"sign\":-2}";
		}
	}
	@POST
	@Path("stopNoGetMakeListById")
	@Produces("application/json")
	public Integer stopNoGetMakeListById(@FormParam("id") Integer id){
		try {
			//修改生产单信息
			TMakeList tm = (TMakeList)makeDao.findById(TMakeList.class, id);
			tm.setStateSc(TMakeList.STATE_BAD);
			tm.setIsEnter(0);
			makeDao.save(tm);
			//修改订单信息
			TOrderForm tof = (TOrderForm)orderDao.findById(TOrderForm.class, tm.getFid());
			tof.setUnAssignedNum(tof.getUnAssignedNum()+tm.getPlanPage());
			orderDao.save(tof);
			
			return tm.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	@POST
	@Path("stopMakeListById")
	@Produces("application/json")
	public Integer stopMakeListById(@FormParam("id") Integer id,@FormParam("hadProductCount") Integer hadProductCount,@FormParam("remainPaperCount") Integer remainPaperCount){
		try {
			//修改生产单信息
			TMakeList tm = (TMakeList)makeDao.findById(TMakeList.class, id);
			tm.setRemainPage(tm.getPlanPage()-hadProductCount);
			tm.setStateSc(TMakeList.STATE_FINISHED);
			tm.setIsEnter(2);
			makeDao.save(tm);
			//修改订单信息
			TOrderForm tof = (TOrderForm)orderDao.findById(TOrderForm.class, tm.getFid());
			tof.setUnAssignedNum(tof.getUnAssignedNum()+tm.getRemainPage());
			tof.setRemainPage(tof.getRemainPage()-hadProductCount);
			orderDao.save(tof);
			//修改库存状态
			TMaterialList tma =  (TMaterialList)mlDao.getMaterialCountByMid(tof.getPaperId());
			tma.setCount(tma.getCount()+remainPaperCount);
			mlDao.save(tma);
			
			return tm.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
}
