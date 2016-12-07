package com.supermap.sppm.yxcms.dao.impl;

import java.util.List;

import com.supermap.sppm.yxcms.dao.BaseDao;
import com.supermap.sppm.yxcms.pojo.TPartner;

public class PartnerDaoImpl extends BaseDao {
	
	/**
	 * @author liyutao
	 * 查找所有的外协公司的名称
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List findAllPartner(){
		try {
			List list = getSession().createQuery( "from " + TPartner.class.getName() + " order by id" ).list();;
			return list;
		}catch(RuntimeException re){
			throw re;
		}
		
	}

}
