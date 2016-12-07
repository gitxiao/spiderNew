package com.supermap.sppm.yxcms.ws;

import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.supermap.sppm.yxcms.dao.impl.MoneyOutInfoDaoImpl;
import com.supermap.sppm.yxcms.pojo.TMoneyOut;
import com.supermap.sppm.yxcms.utils.Common;

@Stateless
@Path("moneyout")
public class MoneyOutInfoService {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(MoneyOutInfoService.class);
	private static final MoneyOutInfoDaoImpl infoDao = new MoneyOutInfoDaoImpl();
	

	@POST
	@Path("findAllMoneyOutInfo")
	@Produces("application/json")
	public Object findAllMoneyOutInfo (){
		return infoDao.findAll(TMoneyOut.class);
//		return null;
	}
	
	
	@POST
	@Path("saveMoneyOutInfo")
	@Produces("application/json")
	public String saveMoneyOutInfo(@FormParam("para") String para){
		try {
			TMoneyOut obj = (TMoneyOut)infoDao.save(TMoneyOut.class,para);
			int id = obj.getId();
			return String.valueOf(id);
		} catch (Exception e) {
			e.printStackTrace();
			Common.buildException(Status.NOT_MODIFIED, e.getMessage());
			return "-1";
		}				
	}
}
