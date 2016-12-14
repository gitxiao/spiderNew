package cn.muke.ssh.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import cn.muke.ssh.dao.T_UserDao;
import cn.muke.ssh.domain.T_User;

import com.google.gson.Gson;
import com.supermap.sppm.yxcms.utils.Common;

@Stateless
@Path("user")
public class UserService {
	private static final Logger log = Logger.getLogger(UserService.class);
	private static final T_UserDao userDao = new T_UserDao();
	
	@POST
	@Path("findUserById")
	@Produces("application/json")
	public Object findUserById (@FormParam("id") int id){
		//System.out.println("UserService findUserById");
		return userDao.findById(T_User.class,id);
	}

	@POST
	@Path("findAllUser")
	@Produces("application/json")
	public Object findAllUser (){
		return userDao.findAll(T_User.class);
	}
	
	/**
	 * 验证用户密码
	 * xiayaoxing
	 */
	@SuppressWarnings("unchecked")
	@POST
	@Path("checkUserPassword")
	@Produces("application/json")
	public String checkUserPassword(@FormParam("name") String name,@FormParam("password") String password){
		System.out.println("name password = " + name + "," + password);
		List ls = null;
		try {
			ls = (List)userDao.findByLoginName(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		if(ls == null || ls.size() == 0){
			return "nouser";
		}
		T_User user = (T_User)ls.get(0);
		String realPass =  user.getPassword();
		if(realPass.equals(password)){
			return user.getUserType() + "|" + user.getId() + "|" + Common.getRandomStr();		
		}else{
			return "failpass";
//			return user.getPassWord();
		}
	}
	
	/**
	 * 查找用户的所有类型
	 */
	@SuppressWarnings("unchecked")
	@POST
	@Path("findAllUserType")
	@Produces("application/json")
	public List findAllUserType(){
		System.out.println("UserService findAllUserType");
		return userDao.findAllUserType();
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("saveUser")
	@Produces("application/json")
	public String saveUser(@FormParam("para") String para){
		try {
			//在这里判断用户是否已经存在
			Gson gson = new Gson();
			T_User tui = gson.fromJson(para, T_User.class);
			List ls = userDao.findByLoginName(tui.getName());
			if(ls!=null && ls.size()!=0){
				return "-2";
			}
			//不存在时,保存用户信息
			T_User tab = (T_User)userDao.save(T_User.class,para);
			int id = tab.getId();
			return String.valueOf(id);
		} catch (Exception e) {
			e.printStackTrace();
			Common.buildException(Status.NOT_MODIFIED, e.getMessage());
			return "-1";
		}				
	}
	
	/**
	 * 更改用户信息
	 */
	@POST
	@Path("updateUserByLoginname")
	@Produces("application/json")
	public int updateUserByLoginname(@FormParam("para") String para){
		System.out.println("updateUserByLoginname para = " + para);
		try {
			T_User tui = (T_User)userDao.save(T_User.class, para);
			return tui.getId();
		} catch (Exception e) {
			log.error(e.getMessage());
			Common.buildException(Status.NOT_MODIFIED, e.getMessage());
		}				
		return -1;
	}
}
