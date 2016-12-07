package com.supermap.sppm.yxcms.ws;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.supermap.sppm.yxcms.dao.impl.UserInfoDaoImpl;
import com.supermap.sppm.yxcms.pojo.TUserInfo;
import com.supermap.sppm.yxcms.utils.Common;

@Stateless
@Path("userinfo")
public class UserInfoService {
	private static final Logger log = Logger.getLogger(UserInfoService.class);
	private static final UserInfoDaoImpl userInfoDao = new UserInfoDaoImpl();
	
	@POST
	@Path("findUserInfoById")
	@Produces("application/json")
	public Object findUserInfoById (@FormParam("id") int id){
		//System.out.println("UserInfoService findUserInfoById");
		return userInfoDao.findById(TUserInfo.class,id);
	}

	@POST
	@Path("findAllUserInfo")
	@Produces("application/json")
	public Object findAllUserInfo (){
		return userInfoDao.findAll(TUserInfo.class);
	}
	
	/**
	 * 验证用户密码
	 * xiayaoxing
	 */
	@SuppressWarnings("unchecked")
	@POST
	@Path("checkUserPassword")
	@Produces("application/json")
	public String checkUserPassword(@FormParam("userName") String userName,@FormParam("passWord") String passWord){
		
		List ls = (List)userInfoDao.findByLoginName(userName);		
		if(ls==null||ls.size()==0){
			return "nouser";
		}
		TUserInfo userInfo = (TUserInfo)ls.get(0);
		@SuppressWarnings("unused")
		final int realType = userInfo.getUserType().intValue();
		
		@SuppressWarnings("unused")
		String realPass =  userInfo.getPassWord();
		@SuppressWarnings("unused")
		String inputPass = passWord;
		if(userInfo.getPassWord().equals(passWord)){
			return userInfo.getUserType()+"|"+userInfo.getId()+"|"+Common.getRandomStr();		
		}else{
			return "failpass";
//			return userInfo.getPassWord();
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
		System.out.println("UserInfoService findAllUserType");
		return userInfoDao.findAllUserType();
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("saveUserInfo")
	@Produces("application/json")
	public String saveUserInfo(@FormParam("para") String para){
		try {
			//在这里判断用户是否已经存在
			Gson gson = new Gson();
			TUserInfo tui = gson.fromJson(para, TUserInfo.class);
			List ls = userInfoDao.findByLoginName(tui.getLoginName());
			if(ls!=null && ls.size()!=0){
				return "-2";
			}
			//不存在时,保存用户信息
			TUserInfo tab = (TUserInfo)userInfoDao.save(TUserInfo.class,para);
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
			TUserInfo tui = (TUserInfo)userInfoDao.save(TUserInfo.class, para);
			return tui.getId();
		} catch (Exception e) {
			log.error(e.getMessage());
			Common.buildException(Status.NOT_MODIFIED, e.getMessage());
		}				
		return -1;
	}
}
