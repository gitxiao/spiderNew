package com.supermap.sppm.yxcms.ws;

import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.supermap.sppm.yxcms.dao.impl.OrderFormDaoImpl;
import com.supermap.sppm.yxcms.pojo.TOrderForm;

@Stateless
@Path("orderform")
public class OrderFormService {
	//private static final Logger log = Logger.getLogger(OrderFormService.class);
	private static final OrderFormDaoImpl orderDao = new OrderFormDaoImpl();
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("findAllOrder")
	@Produces("application/json")
	public Object findAllOrder(){
		List<TOrderForm> rs = orderDao.findAllOrder();
		return rs;
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("findNoAssignedOverOrder")
	@Produces("application/json")
	public Object findNoAssignedOverOrder(){
		List<TOrderForm> rs = orderDao.findNoAssignedOverOrder();
		return rs;
	}
	
	@POST
	@Path("findOrderByState")
	@Produces("application/json")
	public Object findOrderByState (@FormParam("state") Integer state){
		return orderDao.findOrderByState(state);
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("findOrderById")
	@Produces("application/json")
	public Object findOrderById (@FormParam("id") Integer id){
		List ls = orderDao.findOrderById(id,true);
		return ls;
	}

	
	@POST
	@Path("findNextFormCode")
	@Produces("application/json")
	public String findNextFormCode(@FormParam("userId") String userId){
		return orderDao.nextFormCode(userId);
	}
	
	@POST
	@Path("saveForm")
	@Produces("application/json")
	public String saveForm(@FormParam("para") String para){
		TOrderForm tof;
		try {
			tof = (TOrderForm)orderDao.saveForm(para);
			if(tof==null){
				return "-2";
			}
			return String.valueOf(tof.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "-2";
		}
	}
	
	@POST
	@Path("loadNoPriceForm")
	@Produces("application/json")
	public Object loadNoPriceForm(){
		try {
			return orderDao.loadNoPriceForm();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "-1";
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("loadNoGetForm")
	@Produces("application/json")
	public List loadNoGetForm(){
		return orderDao.loadNoGetForm();
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("loadNoGiveForm")
	@Produces("application/json")
	public List loadNoGiveForm(){
		return orderDao.loadNoGiveForm();
	}
	
	
	/**
	 *	xiayaoxing
	 */
	@SuppressWarnings("unchecked")
	@POST
	@Path("getOrderFormByUserId")
	@Produces("application/json")
	public List getOrderFormByUserId(@FormParam("userId") Integer userId){
		List ls = orderDao.getOrderFormByUserId(userId);
		if(ls==null || ls.size()==0){
			return null;
		}else{
			return ls;
		}
	}
}
