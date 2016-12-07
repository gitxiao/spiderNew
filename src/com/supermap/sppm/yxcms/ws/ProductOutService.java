package com.supermap.sppm.yxcms.ws;

import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.Gson;
import com.supermap.sppm.yxcms.dao.impl.OrderFormDaoImpl;
import com.supermap.sppm.yxcms.dao.impl.ProductOutDaoImpl;
import com.supermap.sppm.yxcms.pojo.TOrderForm;
import com.supermap.sppm.yxcms.pojo.TProductOut;
import com.supermap.sppm.yxcms.utils.Common;

@Stateless
@Path("productOut")
public class ProductOutService {

	private static final ProductOutDaoImpl poDao = new ProductOutDaoImpl();
	private static final OrderFormDaoImpl ofDao = new OrderFormDaoImpl();
	
	/**
	 * @author liyutao
	 * 查找下个产品出库单编号
	 * @return
	 */
	@POST
	@Path("findNextPoutFormCode")
	@Produces("application/json")
	public String findNextPoutFormCode(){
		return poDao.nextProductOutFormCode();
	}
	
	@POST
	@Path("saveProductOutForm")
	@Produces("application/json")
	public Integer saveProductOutForm(@FormParam("para") String para){
		try{
			//保存
			Gson gson = new Gson();
			TProductOut tpo = gson.fromJson(para, TProductOut.class);
			if(tpo.getOutDate()==null||tpo.getOutDate().trim().length()==0){
				tpo.setOutDate(Common.getSysTime("yyyy-MM-dd"));
			}
			poDao.save(tpo);
			TOrderForm tof = (TOrderForm)ofDao.findById(TOrderForm.class, tpo.getFid());
			tof.setState(5);
			tof.setSubmitDate(tpo.getOutDate());
			ofDao.save(tof);
			return tpo.getId();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -2;
		}
		
	}
}
