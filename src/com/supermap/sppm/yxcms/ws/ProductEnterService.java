package com.supermap.sppm.yxcms.ws;

import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.Gson;
import com.supermap.sppm.yxcms.dao.impl.MakeListDaoImpl;
import com.supermap.sppm.yxcms.dao.impl.MaterialListDaoImpl;
import com.supermap.sppm.yxcms.dao.impl.OrderFormDaoImpl;
import com.supermap.sppm.yxcms.dao.impl.ProductEnterDaoImpl;
import com.supermap.sppm.yxcms.pojo.TMakeList;
import com.supermap.sppm.yxcms.pojo.TMaterialList;
import com.supermap.sppm.yxcms.pojo.TOrderForm;
import com.supermap.sppm.yxcms.pojo.TProductEnter;
import com.supermap.sppm.yxcms.utils.Common;

@Stateless
@Path("productEnter")
public class ProductEnterService {
	
	private static final ProductEnterDaoImpl  peDao = new ProductEnterDaoImpl();
	private static final MakeListDaoImpl makeDao = new MakeListDaoImpl();
	private static final MaterialListDaoImpl mlDao = new MaterialListDaoImpl();
	private static final OrderFormDaoImpl ofDao = new OrderFormDaoImpl();
	
	
	/**
	 * @author liyutao
	 * 查找下个产品入库单编号
	 * @return
	 */
	@POST
	@Path("findNextPFormCode")
	@Produces("application/json")
	public String findNextPFormCode(){
		return peDao.nextProductFormCode();
	}
	/**
	 * @author liyutao
	 * 保存产品入库单
	 * @param para
	 * @return
	 */
	@POST
	@Path("saveProdunctEnterForm")
	@Produces("application/json")
	public Integer saveProdunctEnterForm(@FormParam("para") String para){
		try{
			//保存产品入库单
			Gson gson = new Gson();
			TProductEnter tpe = gson.fromJson(para, TProductEnter.class);
			if(tpe.getEnterDate()==null||tpe.getEnterDate().trim().length()==0){
				tpe.setEnterDate(Common.getSysTime("yyyy-MM-dd"));
			}
			peDao.save(tpe);
			//修改生产单状态
			TMakeList tml = (TMakeList)makeDao.findById(TMakeList.class, tpe.getOfId());
			tml.setRemainPage(tml.getPlanPage()-tpe.getEnterCount());
			tml.setStateSc(1);
			tml.setIsEnter(1);
			makeDao.save(tml);
			//修改库存状态
			TMaterialList tma =  (TMaterialList)mlDao.getMaterialCountByMid(tpe.getMid());
			tma.setCount(tma.getCount()+tpe.getmCount());
			mlDao.save(tma);
			//修改订单状态
			TOrderForm of = (TOrderForm)ofDao.findById(TOrderForm.class, tml.getFid());
			Integer remainPage = of.getRemainPage() - tpe.getEnterCount();
			of.setRemainPage(remainPage);
			of.setUnAssignedNum(tml.getPlanPage()-tpe.getEnterCount()+of.getUnAssignedNum());
			if(remainPage==0){
				of.setState(4);
				of.setFinishDate(Common.getSysTime("yyyy-MM-dd"));
			}
			ofDao.save(of);
			return tpe.getId();
		}catch (Exception e) {
			// TODO: handle exception
			return -2;
		}
		
		
	}

}
