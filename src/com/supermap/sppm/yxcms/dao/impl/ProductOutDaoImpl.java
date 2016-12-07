package com.supermap.sppm.yxcms.dao.impl;

import java.util.List;

import com.supermap.sppm.yxcms.dao.BaseDao;
import com.supermap.sppm.yxcms.pojo.TProductOut;
import com.supermap.sppm.yxcms.utils.Common;

public class ProductOutDaoImpl extends BaseDao {

	/**
	 * @author liyutao
	 * 查找下一个产品出库单的编码
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String nextProductOutFormCode(){
		List list = null;
		String dateStr = Common.getSysTime("yyyyMMdd");
		String code = "YXPO"+dateStr;
		try {
			list = getSession().createQuery( "from " + TProductOut.class.getName() + " s where s.outCode like '%"+code+"%' order by outCode desc" ).list();
		} catch (RuntimeException re) {			
			throw re;
		}
		if(list!=null&&list.size()!=0){
			String formCode = ((TProductOut)list.get(0)).getOutCode();
			String str = formCode.substring(formCode.length()-4);
			return code+Common.lastCode(str);
		}else{
			return code+"0001";
		}
	}
}
