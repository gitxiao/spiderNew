package com.supermap.sppm.yxcms.ws;

import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.Gson;
import com.supermap.sppm.yxcms.dao.impl.BuyFormDaoImpl;
import com.supermap.sppm.yxcms.dao.impl.MaterialEnterDaoImpl;
import com.supermap.sppm.yxcms.dao.impl.MaterialListDaoImpl;
import com.supermap.sppm.yxcms.pojo.TBuyForm;
import com.supermap.sppm.yxcms.pojo.TMaterialEnter;
import com.supermap.sppm.yxcms.pojo.TMaterialList;
import com.supermap.sppm.yxcms.utils.Common;


@Stateless
@Path("materialEnter")
public class MaterialEnterService {
	
	private static final MaterialEnterDaoImpl meoDao = new MaterialEnterDaoImpl();
	private static final BuyFormDaoImpl buyDao = new BuyFormDaoImpl();
	private static final MaterialListDaoImpl mlDao = new MaterialListDaoImpl();
	

	/**
	 * @author liyutao
	 * 查找下个入库单编号
	 * @return
	 */
	@POST
	@Path("nextRkFormCode")
	@Produces("application/json")
	public String nextRkFormCode(){
		return meoDao.nextRkFormCode();
	}
	
	@POST
	@Path("saveEnterForm")
	@Produces("application/json")
	public Integer saveEnterForm(@FormParam("para") String para){
		try{
			Gson gson = new Gson();
			TMaterialEnter tme = gson.fromJson(para, TMaterialEnter.class);
			if(tme.getEnterDate()==null||tme.getEnterDate().trim().length()==0){
				tme.setEnterDate(Common.getSysTime("yyyy-MM-dd"));
			}
			meoDao.save(tme);
			//修改采购单状态
			TBuyForm buyForm = (TBuyForm)buyDao.findById(TBuyForm.class, tme.getBid());
			buyForm.setState(1);
			buyDao.save(buyForm);
			//修改库存数量
			TMaterialList tml = (TMaterialList)mlDao.getMaterialCountByMid(tme.getEnterMid());
			if(tml==null){
				tml = new TMaterialList();
				tml.setCount(0);
				tml.setMid(tme.getEnterMid());
				tml.setState(1);
			}
			tml.setCount(tml.getCount()+tme.getEnterCount());
			tml.setEnterDate(tme.getEnterDate());
			tml.setEnterId(tme.getId());
			mlDao.save(tml);
			return tme.getId();
		}catch (Exception e) {
			// TODO: handle exception
			return -2;
		}
	}
}
