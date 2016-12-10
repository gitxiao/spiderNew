package cn.muke.ssh.service;

import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.cfrj.spider.SpiderStarter;

/**
 * 业务层
 * @author Cfrjkj
 *
 */
@Stateless
@Path("CtrlService")
public class CtrlService{

	/**
	 * service中的保存方法
	 */
	@POST
	@Path("startSpider")
	@Produces("application/json")
	public String startSpider(@FormParam("para") String para){
		SpiderStarter.startSpider(true);
		return "1";
	}
	
	/**
	 * service中的保存方法
	 */
	@POST
	@Path("stopSpider")
	@Produces("application/json")
	public String stopSpider(@FormParam("para") String para){
		SpiderStarter.startSpider(false);
		return "1";
	}
}
