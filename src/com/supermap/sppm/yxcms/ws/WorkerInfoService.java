package com.supermap.sppm.yxcms.ws;


import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response.Status;


import com.supermap.sppm.yxcms.dao.impl.WorkerInfoDaoImpl;
import com.supermap.sppm.yxcms.pojo.TWorkerInfo;
import com.supermap.sppm.yxcms.utils.Common;

@Stateless
@Path("workerinfo")
public class WorkerInfoService {
	//private static final Logger log = Logger.getLogger(WorkerInfoService.class);
	private static final WorkerInfoDaoImpl workerInfoDao = new WorkerInfoDaoImpl();
	
	@POST
	@Path("findWorkerById")
	@Produces("application/json")
	public Object findWorkerById (@FormParam("para") Integer para){
		//System.out.println("WorkerInfoService findWorkerInfoById");
		return workerInfoDao.findById(TWorkerInfo.class,para);
	}
	
	@POST
	@Path("findWorkerByDuty")
	@Produces("application/json")
	public Object findWorkerByDuty (@FormParam("para") Integer para){
		//System.out.println("WorkerInfoService findWorkerInfoById");
		return workerInfoDao.findWorkerByDuty(para);
	}
	
	@POST
	@Path("findWorkerByState")
	@Produces("application/json")
	public Object findWorkerByState (@FormParam("state") Integer state){
		return workerInfoDao.findWorkerByState(state);
	}

	@POST
	@Path("findAllWorkerInfo")
	@Produces("application/json")
	public Object findAllWorkerInfo (){
		return workerInfoDao.findAll(TWorkerInfo.class);
//		return null;
	}
	
	@POST
	@Path("saveWorkerInfo")
	@Produces("application/json")
	public String saveWorkerInfo(@FormParam("para") String para){
		try {
			TWorkerInfo tab = (TWorkerInfo)workerInfoDao.save(TWorkerInfo.class, para);
			int id = tab.getId();
			return String.valueOf(id);
		} catch (Exception e) {
			e.printStackTrace();
			Common.buildException(Status.NOT_MODIFIED, e.getMessage());
			return "-1";
		}				
	}
}
