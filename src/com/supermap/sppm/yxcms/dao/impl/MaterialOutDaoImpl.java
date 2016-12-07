package com.supermap.sppm.yxcms.dao.impl;

import java.util.List;

import com.supermap.sppm.yxcms.dao.BaseDao;
import com.supermap.sppm.yxcms.pojo.TMaterialOut;
import com.supermap.sppm.yxcms.utils.Common;

public class MaterialOutDaoImpl extends BaseDao {
	@SuppressWarnings("unchecked")
	public String nextCkFormCode(){
		List list = null;
		String dateStr = Common.getSysTime("yyyyMMdd");
		String code = "YXMO"+dateStr;
		try {
			list = getSession().createQuery( "from " + TMaterialOut.class.getName() + " s where s.outCode like '%"+code+"%' order by outCode desc" ).list();
		} catch (RuntimeException re) {			
			throw re;
		}
		if(list!=null&&list.size()!=0){
			String formCode = ((TMaterialOut)list.get(0)).getOutCode();
			String str = formCode.substring(formCode.length()-4);
			return code+Common.lastCode(str);
		}else{
			return code+"0001";
		}
		
	}

}
