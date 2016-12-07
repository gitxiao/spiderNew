package com.supermap.sppm.yxcms.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.supermap.sppm.yxcms.dao.impl.MaterialListDaoImpl;
import com.supermap.sppm.yxcms.dao.impl.MaterialTypeDaoImpl;
import com.supermap.sppm.yxcms.pojo.DetailMaterialType;
import com.supermap.sppm.yxcms.pojo.TMaterialList;
import com.supermap.sppm.yxcms.pojo.TMaterialType;
import com.supermap.sppm.yxcms.utils.Common;


@Stateless
@Path("material")
public class MaterialTypeService {
	private static final Logger log = Logger.getLogger(UserInfoService.class);
	private static final MaterialTypeDaoImpl mtDao = new MaterialTypeDaoImpl();
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("findPaperType")
	@Produces("application/json")
	public List findPaperType(){
		return mtDao.findMaterialType(1);
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("findMaterialType")
	@Produces("application/json")
	public List findMaterialType(@FormParam("mtype") Integer mtype){
		return mtDao.findMaterialType(mtype);
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("findPaperSize")
	@Produces("application/json")
	public List findPaperSize(@FormParam("dtype") Integer dtype){
		return mtDao.findPaperSize(dtype);
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("findMaterialSize")
	@Produces("application/json")
	public List findMaterialSize(@FormParam("mtype") Integer mtype,@FormParam("dtype") Integer dtype){
		return mtDao.findMaterialSize(mtype, dtype);
	}

	@SuppressWarnings("unchecked")
	@POST
	@Path("findAllMaterialType")
	@Produces("application/json")
	public List findAllMaterialType(){
		List ls = mtDao.findAll(TMaterialType.class);
		return getDetailList(ls);
	}

	@SuppressWarnings("unchecked")
	@POST
	@Path("findAllMaterialDTypeByType")
	@Produces("application/json")
	public List findAllMaterialDTypeByType(@FormParam("para") String para){
		System.out.println("findAllMaterialDTypeByType para = " + para);
		int type = Integer.parseInt(para);
		switch(type){
		case MaterialTypeDaoImpl.MTYPE_PAPER:
			return mtDao.findMaterialType(1);
		case MaterialTypeDaoImpl.MTYPE_INK:
			return mtDao.findInkType();
		default:
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("saveMaterialType")
	@Produces("application/json")
	public String saveMaterialType(@FormParam("para") String para){
		try {
			//在这里判断材料类型是否已经存在
			Gson gson = new Gson();
			TMaterialType tmt = gson.fromJson(para, TMaterialType.class);
			List<TMaterialType> ls = mtDao.findAll(TMaterialType.class);
			
			for(TMaterialType tt:ls){
				//当材料的dtype和规格型号, 计量单位都一致时, 表明该材料已经存在
				if(tt.getDtype().equals(tmt.getDtype()) && tt.getMsize().equals(tmt.getMsize()) && tt.getMunit().equals(tmt.getMunit())){
					return "-2";
				}
			}
			
			//不存在时,保存材料类型
			TMaterialType mtype = null;
			mtype = (TMaterialType)mtDao.save(TMaterialType.class, para);
			int id = mtype.getDtype();
			return String.valueOf(id);
		} catch (Exception e) {
			e.printStackTrace();
			Common.buildException(Status.NOT_MODIFIED, e.getMessage());
			return "-1";
		}				
	}
	
	/**
	 * 更改用户信息
	 */
	@POST
	@Path("updateMaterialById")
	@Produces("application/json")
	public int updateMaterialById(@FormParam("para") String para){
		System.out.println("updateUserByLoginname para = " + para);
		try {
			TMaterialType tmt = (TMaterialType)mtDao.save(TMaterialType.class, para);
			return tmt.getId();
		} catch (Exception e) {
			log.error(e.getMessage());
			Common.buildException(Status.NOT_MODIFIED, e.getMessage());
		}				
		return -1;
	}
	
	
	@SuppressWarnings("unchecked")
	private List getDetailList(List<DetailMaterialType> ls){
		List rs = new ArrayList();
		for(TMaterialType tm:ls){
			DetailMaterialType dt = getDetailFromTFather(tm);
			rs.add(dt);
		}
		return rs;
	}
	
	/**
	 * 获取材料表详情,包含库存
	 */
	private DetailMaterialType getDetailFromTFather(TMaterialType tm){
		Gson json = new Gson();
		String str = json.toJson(tm);
		DetailMaterialType dt = json.fromJson(str, DetailMaterialType.class);
		dt.resetNames();
		
		//从材料库存表拿到库存数据
		TMaterialList tml = (TMaterialList)new MaterialListDaoImpl().getMaterialCountByMid(tm.getId());
		if(tml == null){
			//没有库存数据
			dt.setCount(0);
		}else{
			//ReflectCopy.copyPropertiesExclude(dt, tml,new String[]{"count"});
			dt.setCount(tml.getCount());
		}
		return dt;
	}
}
