package com.supermap.sppm.yxcms.dao.impl;

import java.util.List;

import com.supermap.sppm.yxcms.dao.BaseDao;
import com.supermap.sppm.yxcms.pojo.TBuyForm;
import com.supermap.sppm.yxcms.utils.Common;

public class BuyFormDaoImpl extends BaseDao {
	
	@SuppressWarnings("unchecked")
	public String nextBuyFormCOde(){
		List list = null;
		String dateStr = Common.getSysTime("yyyyMMdd");
		String code = "YXBF"+dateStr;
		try {
			list = getSession().createQuery( "from " + TBuyForm.class.getName() + " s where s.buyFormCode like '%"+code+"%' order by buyFormCode desc" ).list();
		} catch (RuntimeException re) {			
			throw re;
		}
		if(list!=null&&list.size()!=0){
			String formCode = ((TBuyForm)list.get(0)).getBuyFormCode();
			String str = formCode.substring(formCode.length()-4);
			return code+Common.lastCode(str);
		}else{
			return code+"0001";
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List getBuyFormByState(Integer state){
		try {
			return getSession().createQuery( "from " + TBuyForm.class.getName() + " s where s.state =? order by buyFormCode desc" ).setParameter(0, state).list();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	

}
