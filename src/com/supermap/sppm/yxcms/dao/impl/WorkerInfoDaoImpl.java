package com.supermap.sppm.yxcms.dao.impl;

import java.util.List;
import com.supermap.sppm.yxcms.dao.BaseDao;
import com.supermap.sppm.yxcms.pojo.TWorkerInfo;
public class WorkerInfoDaoImpl extends BaseDao {
	

	
	/**
	 * @author xiayaoxing
	 * 通过duty查找员工信息
	 */
	@SuppressWarnings("unchecked")
	public List findWorkerByDuty(int duty){
		try {
			List list = getSession().createQuery( "from " + TWorkerInfo.class.getName() + " s where s.duty = ?" ).setParameter(0, duty).list();
			return list;
		} catch (RuntimeException re) {			
			throw re;
		}
	}
	
	/**
	 * @author xiayaoxing
	 * 通过id查找员工信息
	 */
	@SuppressWarnings("unchecked")
	public List findWorkerByState(int state){
		try {
			List list = getSession().createQuery( "from " + TWorkerInfo.class.getName() + " s where s.state = ?" ).setParameter(0, state).list();
			return list;
		} catch (RuntimeException re) {			
			throw re;
		}
	}
	
}
