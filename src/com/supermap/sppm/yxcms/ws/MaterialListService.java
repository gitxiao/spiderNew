package com.supermap.sppm.yxcms.ws;

import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.supermap.sppm.yxcms.dao.impl.MaterialListDaoImpl;
import com.supermap.sppm.yxcms.pojo.TMaterialList;


@Stateless
@Path("materialList")
public class MaterialListService {
	
	private static final MaterialListDaoImpl mlDao = new MaterialListDaoImpl();
	
	
	@POST
	@Path("getMCountByMid")
	@Produces("application/json")
	public Integer getMCountByMid(@FormParam("mid") Integer mid){
		TMaterialList tml = (TMaterialList)mlDao.getMaterialCountByMid(mid);
		if(tml==null){
			return 0;
		}else{
			return tml.getCount();
		}
		
	}
	
	
	

}
