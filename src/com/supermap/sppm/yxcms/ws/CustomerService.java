package com.supermap.sppm.yxcms.ws;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.supermap.sppm.yxcms.dao.impl.CustomerDaoImpl;
import com.supermap.sppm.yxcms.pojo.TCustomer;

@Stateless
@Path("customer")
public class CustomerService {
	
	private static final CustomerDaoImpl cusDao = new CustomerDaoImpl();
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("findAll")
	@Produces("application/json")
	public List findAll(){
		return cusDao.findAll(TCustomer.class);
	}

}
