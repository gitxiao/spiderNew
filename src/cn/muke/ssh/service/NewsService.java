package cn.muke.ssh.service;

import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.muke.ssh.dao.T_NewsDao;
import cn.muke.ssh.domain.T_News;

/**
 * 业务层
 * @author Cfrjkj
 *
 */
@Stateless
@Path("NewsService")
public class NewsService{

	public NewsService(){
		
	}
	
	/**
	 * 业务层注入的dao类
	 */
	private final T_NewsDao t_NewsDao = new T_NewsDao();

	/**
	 * service中的保存方法
	 */
	@POST
	@Path("save")
	@Produces("application/json")
	public void save(@FormParam("para") String para){
		try {
			t_NewsDao.save(T_News.class, para);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * service中的保存方法
	 */
	public void save(T_News news){
		try {
			t_NewsDao.save(news);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	@POST
	@Path("findNews")
	@Produces("application/json")
	public Object findNews(@FormParam("para") String para){
		System.out.println("NewsService findNews t_NewsDao, para = " + t_NewsDao + "," + para);
		try {
			//直接从网页调用时,没能经过spring创建对象,t_NewsDao为空,所以使用前要先初始化
			return t_NewsDao.findAll(T_News.class);
		} catch (Exception e) {
			e.printStackTrace();
			return "-1";
		}
	}
}
