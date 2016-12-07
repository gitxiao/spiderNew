package com.supermap.sppm.yxcms.dao.impl;

import java.util.List;

import com.supermap.sppm.yxcms.dao.BaseDao;
import com.supermap.sppm.yxcms.pojo.TMaterialList;

public class MaterialListDaoImpl extends BaseDao {
	
	/**
	 * @author liyutao
	 * 通过物料ID查询物料的库存信息
	 * @param mid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object getMaterialCountByMid(Integer mid){
		List list = getSession().createQuery( "from " + TMaterialList.class.getName() + " s where s.mid =? order by id desc" ).setParameter(0, mid).list();
		if(list==null||list.size()==0) return null;
		TMaterialList tml = (TMaterialList)list.get(0);
		return tml;
	}

}
