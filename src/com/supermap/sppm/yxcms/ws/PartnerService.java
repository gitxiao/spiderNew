package com.supermap.sppm.yxcms.ws;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.supermap.sppm.yxcms.dao.impl.PartnerDaoImpl;


@Stateless
@Path("partner")
public class PartnerService {
	
	private static final PartnerDaoImpl partnerDao = new PartnerDaoImpl();
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("findPaperType")
	@Produces("application/json")
	public List findAllPartner(){
		return partnerDao.findAllPartner();
	}

}
