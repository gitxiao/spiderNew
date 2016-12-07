package com.supermap.sppm.yxcms.dao.impl;

import java.util.List;

import com.supermap.sppm.yxcms.dao.BaseDao;
import com.supermap.sppm.yxcms.pojo.TProductEnter;
import com.supermap.sppm.yxcms.utils.Common;

public class ProductEnterDaoImpl extends BaseDao {
	
	/**
	 * @author liyutao
	 * 查找下一个产品入库单的编码
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String nextProductFormCode(){
		List list = null;
		String dateStr = Common.getSysTime("yyyyMMdd");
		String code = "YXPI"+dateStr;
		try {
			list = getSession().createQuery( "from " + TProductEnter.class.getName() + " s where s.enterCode like '%"+code+"%' order by enterCode desc" ).list();
		} catch (RuntimeException re) {			
			throw re;
		}
		if(list!=null&&list.size()!=0){
			String formCode = ((TProductEnter)list.get(0)).getEnterCode();
			String str = formCode.substring(formCode.length()-4);
			return code+Common.lastCode(str);
		}else{
			return code+"0001";
		}
	}

}
