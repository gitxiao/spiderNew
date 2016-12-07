package com.supermap.sppm.yxcms.ws;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.supermap.sppm.yxcms.dao.impl.SupplierDaoImpl;
import com.supermap.sppm.yxcms.pojo.TSupplier;


@Stateless
@Path("supplier")
public class SupplierService {
	
	private static final SupplierDaoImpl supDao = new SupplierDaoImpl();
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("findAllSupplier")
	@Produces("application/json")
	public List findAllSupplier(){
		return supDao.findAll(TSupplier.class);
	}

}
