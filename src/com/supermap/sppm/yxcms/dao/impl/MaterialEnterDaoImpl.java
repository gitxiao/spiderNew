package com.supermap.sppm.yxcms.dao.impl;

import java.util.List;

import com.supermap.sppm.yxcms.dao.BaseDao;
import com.supermap.sppm.yxcms.pojo.TMaterialEnter;
import com.supermap.sppm.yxcms.utils.Common;

public class MaterialEnterDaoImpl extends BaseDao {
	
	/**
	 * @author liyutao
	 * 查找下一个物料入库单编码
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String nextRkFormCode(){
		List list = null;
		String dateStr = Common.getSysTime("yyyyMMdd");
		String code = "YXMI"+dateStr;
		try {
			list = getSession().createQuery( "from " + TMaterialEnter.class.getName() + " s where s.menterCode like '%"+code+"%' order by menterCode desc" ).list();
		} catch (RuntimeException re) {			
			throw re;
		}
		if(list!=null&&list.size()!=0){
			String formCode = ((TMaterialEnter)list.get(0)).getMenterCode();
			String str = formCode.substring(formCode.length()-4);
			return code+Common.lastCode(str);
		}else{
			return code+"0001";
		}
		
	}

}
