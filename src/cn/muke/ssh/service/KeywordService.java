package cn.muke.ssh.service;

import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import cn.muke.ssh.dao.T_KeywordDao;
import cn.muke.ssh.domain.T_Keyword;

/**
 * 业务层
 * @author Cfrjkj
 *
 */
@Stateless
@Path("keyword")
public class KeywordService{

	public KeywordService(){
		
	}
	
	/**
	 * 业务层注入的dao类
	 */
	private final T_KeywordDao keywordDao = new T_KeywordDao();

	/**
	 * service中的保存方法
	 */
	@POST
	@Path("save")
	@Produces("application/json")
	public void save(@FormParam("para") String para){
		try {
			keywordDao.save(T_Keyword.class, para);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * service中的保存方法
	 */
	public void save(T_Keyword word){
		try {
			keywordDao.save(word);
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
	public Object findAll(){
		System.out.println("KeywordService findAll");
		try {
			//直接从网页调用时,没能经过spring创建对象,t_NewsDao为空,所以使用前要先初始化
			return keywordDao.findAll(T_Keyword.class);
		} catch (Exception e) {
			e.printStackTrace();
			return "-1";
		}
	}
}
