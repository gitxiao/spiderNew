package com.supermap.sppm.yxcms.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.google.gson.Gson;
import com.supermap.sppm.yxcms.dao.impl.BuyFormDaoImpl;
import com.supermap.sppm.yxcms.dao.impl.MaterialTypeDaoImpl;
import com.supermap.sppm.yxcms.dao.impl.SupplierDaoImpl;
import com.supermap.sppm.yxcms.dao.impl.WorkerInfoDaoImpl;
import com.supermap.sppm.yxcms.pojo.DetailBuyForm;
import com.supermap.sppm.yxcms.pojo.TBuyForm;
import com.supermap.sppm.yxcms.pojo.TMaterialType;
import com.supermap.sppm.yxcms.pojo.TSupplier;
import com.supermap.sppm.yxcms.pojo.TWorkerInfo;
import com.supermap.sppm.yxcms.utils.Common;


@Stateless
@Path("buyform")
public class BuyFormService {
	private static final BuyFormDaoImpl buyDao = new BuyFormDaoImpl();
	private static final MaterialTypeDaoImpl mtDao = new MaterialTypeDaoImpl();
	private static final WorkerInfoDaoImpl wiDao = new WorkerInfoDaoImpl();
	private static final SupplierDaoImpl supDao = new SupplierDaoImpl();
	
	@POST
	@Path("nexBuyFormCode")
	@Produces("application/json")
	public String nexBuyFormCode(){
		return buyDao.nextBuyFormCOde();
	}

	@POST
	@Path("saveBuyForm")
	@Produces("application/json")
	public Integer saveBuyForm(@FormParam("para") String para){
		try{
			Gson gson = new Gson();
			TBuyForm tbf = gson.fromJson(para, TBuyForm.class);
			if(tbf.getBuyDate()==null||tbf.getBuyDate().trim().length()==0){
				tbf.setBuyDate(Common.getSysTime("yyyy-MM-dd"));
			}
			buyDao.save(tbf);
			return tbf.getId();
		}catch (Exception e) {
			// TODO: handle exception
			return -2;
		}
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("getBuyFormByState")
	@Produces("application/json")
	public List getBuyFormByState(@FormParam("state") Integer state){
		List ls = buyDao.getBuyFormByState(state);
		if(ls == null || ls.size() == 0){
			return null;
		}else{
			return ls;
		}
	}
	@POST
	@Path("getDetailBuyFormById")
	@Produces("application/json")
	public Object getDetailBuyFormById(@FormParam("id") Integer id){
		TBuyForm tbf = (TBuyForm)buyDao.findById(TBuyForm.class, id);
		return setDetailBuyForm(tbf);
	}
	
	@SuppressWarnings("unchecked")
	@POST
	@Path("findAllDetailForm")
	@Produces("application/json")
	public Object findAllDetailForm(){
		List ls = buyDao.findAll(TBuyForm.class);
		List rs = new ArrayList();
		for(Object obj:ls){
			TBuyForm tbf = (TBuyForm)obj;
			rs.add(setDetailBuyForm(tbf));
		}
		return rs;
	}
	
	private DetailBuyForm setDetailBuyForm(TBuyForm tbf){
		Gson gson = new Gson();
		String temp = gson.toJson(tbf);
		DetailBuyForm dbf = gson.fromJson(temp, DetailBuyForm.class);
		TMaterialType mt = (TMaterialType)mtDao.findById(TMaterialType.class, dbf.getMid());
		if(mt!=null){
			dbf.setMaterialName(mt.getMname());
			dbf.setMaterialSize(mt.getMsize()+mt.getMunit());
			
		}
		TWorkerInfo twi = (TWorkerInfo)wiDao.findById(TWorkerInfo.class, dbf.getBuyId());
		if(twi!=null){
			dbf.setBuyPersonName(twi.getWname());
		}
		TSupplier ts = (TSupplier)supDao.findById(TSupplier.class, dbf.getSupid());
		if(ts!=null){
			dbf.setSupplierName(ts.getSupName());
		}
		Integer tp =dbf.getState();
		if(tp==null){
			dbf.setStateName("未知");
		}else if(tp==0){
			dbf.setStateName("采购中");
		}else if(tp==1){
			dbf.setStateName("已入库");
		}else{
			dbf.setStateName("状态异常");
		}
		return dbf;
	}
}
