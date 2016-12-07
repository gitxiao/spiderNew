package com.supermap.sppm.yxcms.dao;

import java.util.List;

import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.Gson;
import com.supermap.sppm.yxcms.HibernateSessionFactory;

/**
 * 数据库操作实体类
 * @author xianchao
 *
 */
public abstract class BaseDao {
	
	//private static final Logger log = LoggerFactory.getLogger(BaseDao.class);
	private static final Gson gson = new Gson();
	public static Session getSession() {
		return HibernateSessionFactory.getSession();
	}
	
	public Object save(Object transientInstance) {
	
		try {
			Session session = getSession();
			session.clear();
			Transaction t = session.beginTransaction();
			session.saveOrUpdate(transientInstance);
			session.flush();
			t.commit();				
		    session.clear();
		    return transientInstance;
			
		} catch (RuntimeException re) {
			//log.error("save failed", re);
			throw re;
		}
	}

	protected void delete(Object persistentInstance)throws Exception{	
		Session session = null;
		Transaction t = null;
		try {
			session = getSession();
			t = session.beginTransaction();
			session.delete(persistentInstance);
			t.commit();						
		
		} catch (RuntimeException re) {
			//log.error("delete failed", re);
			t.rollback();
			throw re;
		}
	}

	
	
	@SuppressWarnings("unchecked")
	protected List fetchByHql(String hql){
		try {
			Query q= getSession().createQuery(hql);
			
			return q.list();
			
		} catch (RuntimeException re) {
			//log.error("fetchByHql", re);
			throw re;
		}
	}

	
	/**
	 * 	
	 * 功能:
	 * 作�?: Ninglg
	 * 创建日期:2012-7-11
	 * @param hql
	 * @return
	 */
	protected Object findUniqueResult(String hql,Object[] paras) {
		try {
			Query q= getSession().createQuery(hql);
			if(paras != null && paras.length > 0) {
				for(int i=0; i<paras.length; i++) {
					q.setParameter(i, paras[i]);
				}
			}
		   return	q.uniqueResult();
		} catch (RuntimeException e) {
			throw e;
		}
		
	}
	@SuppressWarnings("unchecked")
	protected List fetchByHql(String hql, int firstResult, int maxResult){
		try {
			Query q= getSession().createQuery(hql);
			q.setFirstResult(firstResult);
			q.setMaxResults(maxResult);
			return q.list();
		} catch (RuntimeException re) {
			//log.error("fetchByHql", re);
			throw re;
		}
	}
	

	
	@SuppressWarnings("unchecked")
	protected boolean updatePasswordBycCusCode(Class cls, String jsonPara) throws Exception {
		//解析json
		JSONObject  dataJson=new JSONObject(jsonPara);
		String ccusCode = dataJson.getString("ccusCode");
		String passwWord = dataJson.getString("passWord");
//		System.out.println(ccusCode);   //{"ccusCode":"1","passWord":"testpas"}
//		System.out.println(passwWord);
		
//		findById();
		
		
		Session session = null;
		Transaction t = null;
		try {
			session = getSession();
			session.clear();
			t = session.beginTransaction();
			String sql="update "+cls.getSimpleName()+ " set passWord = "+"'"+passwWord +"'"+ " where ccusCode="+"'"+ccusCode+"'";
			session.createQuery(sql).executeUpdate();
			t.commit();
			session.flush();
			return true;

		} catch (Exception e) {
			t.rollback();
			throw e;
		}
	}

	
	
	
	//功能：根据传入的pojo类对象，及id，删除对应的dao实体
	//作�?：宁利广
	//日期�?012-4-25
	@SuppressWarnings("unchecked")
	protected void delete(Class cls,int id)throws Exception{	
		try {
			Session session = getSession();
			Transaction t = session.beginTransaction();
			
			Object obj = findById(cls, id);
			session.delete(obj);
			t.commit();						
		
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	/**
	 * 
	 * 功能:由hql语句查询列表
	 * 作�?: Ninglg
	 * 创建日期:2012-7-11
	 * @param hql：hql语句
	 * @param paras：对应的参数
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	protected List fetchByHql(String hql, Object[] paras){
		try {
			Query q = getSession().createQuery(hql); 
			if(paras != null && paras.length > 0) {
				for(int i=0; i<paras.length; i++) {
					q.setParameter(i, paras[i]);
				}
			}
			
			return q.list();
			
		} catch (RuntimeException re) {
			//log.error("fetchByHql", re);
			throw re;
		}
	}

	
	/**
	 * @author liyutao
	 * @param id 用户ID
	 * @throws Exception 删除异常，代表删除失败
	 * 通过用户ID删除用户信息 
	 */
	@SuppressWarnings("unchecked")
	public void deleteById (Class cls, java.lang.Integer id) throws Exception{
		delete(cls, id);
	}
	
	/**
	 * @author liyutao
	 * @param json 对象转化的JSON字符串
	 * @param cls 对象类
	 * @return 保存的对象
	 * @throws Exception 保存异常，保存信息失败
	 * 把对象信息保存到数据库表，如果有ID代表的是修改，如果ID为空代表是新增，（注意ID为自增长）
	 */
	@SuppressWarnings("unchecked")
	public Object save(Class cls, String jsonPara) throws Exception {
		Object object = null;
//		jsonPara = Common.getDecoderUrl(jsonPara);
		try {
			object = gson.fromJson(jsonPara, cls);
		} catch (Exception e) {
			throw new Exception("传入的json格式有误," + e.getMessage());
		}
		Session session = null;
		Transaction t = null;
		try {
			session = getSession();
			session.clear();
			t = session.beginTransaction();
			session.saveOrUpdate(object);
			t.commit();
			session.flush();

		} catch (Exception e) {
			t.rollback();
			throw e;
		}
		return object;

	}
	/**
	 * @author liyutao
	 * @param id 用户ID
	 * @param cls 对象类
	 * @return 查找对象
	 * 通过对象ID查找对象
	 */
	@SuppressWarnings("unchecked")
	public Object findById(Class cls, java.lang.Integer id) {
		try {
			Session session = getSession();
			Object instance = (Object) session.get(cls.getName(), id);
			return instance;
		} catch (RuntimeException re) {
			//log.error("get failed", re);
			throw re;
		}
	}
	
	/**
	 * @author xiayaoxing
	 * @param cls 对象类
	 * @return 查找对象
	 * 查找所有查找对象
	 */
	@SuppressWarnings("unchecked")
	public List findAll(Class cls) {
		try {
			Session session = getSession();
			List instances = (List) session.createQuery("from " + cls.getName() + " order by id" ).list();
			return instances;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
}
