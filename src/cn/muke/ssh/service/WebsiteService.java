package cn.muke.ssh.service;

import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.muke.ssh.dao.T_WebsiteDao;
import cn.muke.ssh.domain.T_Website;

/**
 * 准备爬取的网站
 * 后面可以添加扩展功能,用户自定义网站及爬取深度
 * @author Cfrjkj
 *
 */
@Stateless
@Path("WebsiteService")
public class WebsiteService{

	public WebsiteService(){
		
	}
	
	/**
	 * 业务层注入的dao类
	 */
	private final T_WebsiteDao websiteDao = new T_WebsiteDao();

	/**
	 * service中的保存方法
	 */
	@POST
	@Path("save")
	@Produces("application/json")
	public void save(@FormParam("para") String para){
		try {
			websiteDao.save(T_Website.class, para);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * service中的保存方法
	 */
	public void save(T_Website website){
		try {
			websiteDao.save(website);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	@POST
	@Path("findAll")
	@Produces("application/json")
	public Object findAll(@FormParam("para") String para){
		System.out.println("NewsService findAll, para = " + "," + para);
		try {
			return websiteDao.findAll(T_Website.class);
		} catch (Exception e) {
			e.printStackTrace();
			return "-1";
		}
	}
}
