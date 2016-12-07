package cn.muke.ssh.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.hibernate.Session;

import cn.muke.ssh.dao.T_VisitedDao;
import cn.muke.ssh.domain.T_Visited;


/**
 * @author Cfrjkj
 *
 */
@Stateless
@Path("VisitedService")
public class VisitedService {

	public VisitedService(){
		
	}
	
	/**
	 */
	private final T_VisitedDao t_VisitedDao = new T_VisitedDao();

	
	/**
	 */
	@POST
	@Path("save")
	@Produces("application/json")
	public void save(@FormParam("para") String para){
		try {
			System.out.println("VisitedService save");
			t_VisitedDao.save(T_Visited.class,para);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 */
	public void save(T_Visited t_Visited){
		try {
			System.out.println("VisitedService save");
			t_VisitedDao.save(t_Visited);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param url
	 * @return
	 */
	@POST
	@Path("findVisitedByUrl")
	@Produces("application/json")
	public T_Visited findVisitedByUrl(@FormParam("url") String url){
		T_Visited tVisited = null;
		tVisited = t_VisitedDao.findByUrl(url);
		return tVisited;
	}
}
