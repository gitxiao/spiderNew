package com.supermap.sppm.yxcms.ws;

import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


import com.google.gson.Gson;
import com.supermap.sppm.yxcms.dao.impl.MakeListDaoImpl;
import com.supermap.sppm.yxcms.dao.impl.MaterialListDaoImpl;
import com.supermap.sppm.yxcms.dao.impl.MaterialOutDaoImpl;
import com.supermap.sppm.yxcms.pojo.TMakeList;
import com.supermap.sppm.yxcms.pojo.TMaterialList;
import com.supermap.sppm.yxcms.pojo.TMaterialOut;
import com.supermap.sppm.yxcms.utils.Common;

@Stateless
@Path("materialOut")
public class MaterialOutService {
	
	private static MaterialOutDaoImpl moDao = new MaterialOutDaoImpl();
	private static MakeListDaoImpl makeDao = new MakeListDaoImpl();
	private static MaterialListDaoImpl mlDao = new MaterialListDaoImpl();
	
	
	/**
	 * @author liyutao
	 * 查找下个出库单编号
	 * @return
	 */
	@POST
	@Path("nextCkFormCode")
	@Produces("application/json")
	public String nextCkFormCode(){
		return moDao.nextCkFormCode();
	}
	
	/**
	 * @author liyutao
	 * 保存或者修改出库单
	 * @param para
	 * @return
	 */
	@POST
	@Path("saveMoForm")
	@Produces("application/json")
	public Integer saveMoForm(@FormParam("para") String para){
		try {
			Gson gson = new Gson();
			TMaterialOut mo = gson.fromJson(para, TMaterialOut.class);
			if(mo.getOutDate()==null||mo.getOutDate().trim().length()==0){
				mo.setOutDate(Common.getSysTime("yyyy-MM-dd"));
			}
			moDao.save(mo);
			//修改生产单状态
			TMakeList tml = (TMakeList)makeDao.findById(TMakeList.class, mo.getMakeId());
			tml.setStateSc(2);
			makeDao.save(tml);
			//修改库存数量
			TMaterialList tmlc = (TMaterialList)mlDao.getMaterialCountByMid(mo.getMid());
			tmlc.setCount(tmlc.getCount()-mo.getGetCount());
			mlDao.save(tmlc);
			return mo.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -2;
		}
	}
	
	
	

}
