package com.supermap.sppm.yxcms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.supermap.sppm.yxcms.dao.BaseDao;
import com.supermap.sppm.yxcms.pojo.TMaterialType;

public class MaterialTypeDaoImpl extends BaseDao {
	
	public static final int MTYPE_PAPER = 1;	//材料类型编号,纸
	public static final int MTYPE_INK = 2;		//材料类型编号,油墨
	
	/**
	 * @author liyutao
	 * 查找所有的纸的类型
	 * @return 纸类型的LIST
	 */
	@SuppressWarnings("unchecked")
	public List findMaterialType(Integer mtype){
		List rs = new ArrayList();
		try {
			List list = getSession().createQuery( "from " + TMaterialType.class.getName() + " s where s.mtype = ? order by dtype" ).setParameter(0, mtype).list();
			int temp = 0;
			for(Object mt:list){
				TMaterialType tmt = (TMaterialType)mt;
				if(tmt.getDtype()!=temp){
					rs.add(tmt);
					temp = tmt.getDtype();
				}				
			}
			return rs;
		} catch (RuntimeException re) {			
			throw re;
		}
	}

	/**
	 * @author xiayaoxing
	 * 查找所有的油墨的类型
	 * @return 纸类型的LIST
	 */
	@SuppressWarnings("unchecked")
	public List findInkType(){
		List rs = new ArrayList();
		try {
			List list = getSession().createQuery( "from " + TMaterialType.class.getName() + " s where s.mtype = 2 order by dtype" ).list();
			int temp = 0;
			for(Object mt:list){
				TMaterialType tmt = (TMaterialType)mt;
				if(tmt.getDtype()!=temp){
					rs.add(tmt);
					temp = tmt.getDtype();
				}				
			}
			return rs;
		} catch (RuntimeException re) {			
			throw re;
		}
	}
	
	/**
	 * @author liyutao
	 * @param dtype 纸的类型编码
	 * @return 该类型纸的所以型号
	 * 查找该类型纸的所有型号
	 */
	@SuppressWarnings("unchecked")
	public List findPaperSize(Integer dtype){
		try {
			List list = getSession().createQuery( "from " + TMaterialType.class.getName() + " s where s.mtype = 1 and s.dtype = ?  order by id" ).setParameter(0, dtype).list();
			return list;
		} catch (RuntimeException re) {			
			throw re;
		}
	}
	
	/**
	 * @author liyutao
	 * @param mtype 物料的大类类型编码
	 * @param dtype 物料的子类类型编码
	 * @return 该类型物料的所以型号
	 * 查找该类型物料的所有型号
	 */
	@SuppressWarnings("unchecked")
	public List findMaterialSize(Integer mtype, Integer dtype){
		try {
			List list = getSession().createQuery( "from " + TMaterialType.class.getName() + " s where s.mtype = ? and s.dtype = ?  order by id" ).setParameter(0, mtype).setParameter(1, dtype).list();
			return list;
		} catch (RuntimeException re) {			
			throw re;
		}
	}

}
