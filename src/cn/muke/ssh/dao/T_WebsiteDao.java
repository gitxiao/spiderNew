package cn.muke.ssh.dao;

import java.util.List;

import cn.muke.ssh.domain.T_Website;

import com.supermap.sppm.yxcms.dao.BaseDao;

/**
 * @author Cfrjkj
 *
 */
public class T_WebsiteDao extends BaseDao{

	@SuppressWarnings("unchecked")
	public List findByState(int state){
		List rs = null;
		try {
			rs = getSession().createQuery( "from " + T_Website.class.getName() + " s where state = ? order by id" ).setParameter(0, state).list();
			return rs;
		} catch (RuntimeException re) {			
			throw re;
		}
	}
	
}

