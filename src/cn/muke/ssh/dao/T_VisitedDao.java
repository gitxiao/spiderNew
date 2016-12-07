package cn.muke.ssh.dao;

import java.util.List;

import org.hibernate.Session;

import cn.muke.ssh.domain.T_Visited;

import com.supermap.sppm.yxcms.dao.BaseDao;

/**
 * @author Cfrjkj
 *
 */
public class T_VisitedDao extends BaseDao{

	/**
	 * @param url
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T_Visited findByUrl(String url){
		Session session = getSession();//获取Session对象   
//		  
		List<T_Visited> list = session.createQuery( "from " + T_Visited.class.getName() + " s where url = ? order by id" ).setParameter(0, url).list();
		
		if(list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
}
