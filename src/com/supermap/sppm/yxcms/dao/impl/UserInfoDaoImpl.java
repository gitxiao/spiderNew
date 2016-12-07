package com.supermap.sppm.yxcms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.supermap.sppm.yxcms.dao.BaseDao;
import com.supermap.sppm.yxcms.pojo.TUserInfo;
public class UserInfoDaoImpl extends BaseDao {
	

	
	/**
	 * @author liyutao
	 * @param loginName 登陆名
	 * @return 用户信息对象
	 * 通过用户名查找用户信息
	 */
	@SuppressWarnings("unchecked")
	public List findByLoginName(String loginName){
		try {
			List list = getSession().createQuery( "from " + TUserInfo.class.getName() + " s where s.loginName = ? order by id" ).setParameter(0, loginName).list();
			return list;
		} catch (RuntimeException re) {			
			throw re;
		}
	}
	
	
	/**
	 * @author liyutao
	 * @param id 用户ID
	 * @throws Exception 删除用户信息失败
	 * 通过编写SQL语句删除用户信息
	 */
	public void deleteBySql(Integer id) throws Exception{
		int count = 0;
		Session session = getSession();
        Transaction tx =null;
        try {
        	tx = session.beginTransaction();
            String hql ="";
            hql = "delete "+TUserInfo.class.getName()+" where id= :id";
            count = session.createQuery(hql).setInteger("id", id).executeUpdate();
            tx.commit(); 
		} catch (Exception e) {
			tx.rollback();
			throw e;
		}
		if(count==0){
			throw (new Exception("删除失败！"));
		}
	}
	
	
	/**
	 * @author xiayaoxing
	 * 查找所有的用户的类型
	 * @return 纸类型的LIST
	 */
	@SuppressWarnings("unchecked")
	public List findAllUserType(){
		List<TUserInfo> rs = new ArrayList<TUserInfo>();
		boolean typeAdded = false;
		try {
			List list = getSession().createQuery("from " + TUserInfo.class.getName() + " order by userType" ).list();
			int type1 = -1;
			int type2 = -1;
			
			for(Object mt:list){
				typeAdded = false;
				TUserInfo tui = (TUserInfo)mt;
				for(TUserInfo tUserInfo:rs){
					type1 = tUserInfo.getUserType();
					type2 = tui.getUserType();
					if(type1 == type2){
						typeAdded = true;
						break;
					}
				}
				if(!typeAdded){
					rs.add(tui);
				}				
			}
			return rs;
		} catch (RuntimeException re) {			
			throw re;
		}
	}
	
	
//	/**
//	 * 更改客户密码
//	 * @throws Exception 
//	 */
//	public boolean updateTypeByLoginname(String userName,String userType) throws Exception {
////		JSONObject  dataJson=new JSONObject(json);
////		int userType = dataJson.getInt("userType");
////		String userName = dataJson.getString("userName");
//		
//		
//		boolean b = false;
//		Session session = null;
//		Transaction t = null;
//		try {
//			session = getSession();
//			session.clear();
//			t = session.beginTransaction();
//			String sql="update "+TUserInfo.class.getSimpleName()+ " set userType = " + "'" +userType + "'" + " where loginName=" + "'" + userName + "'";
//			session.createQuery(sql).executeUpdate();
//			t.commit();
//			session.flush();
//			b = true;
//
//		} catch (Exception e) {
//			t.rollback();
//			throw e;
//		}
//		return b;
//	}
	
}
