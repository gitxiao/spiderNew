package com.supermap.sppm.yxcms.pojo;

@SuppressWarnings("serial")
public class DetailBuyForm extends TBuyForm {
	private String materialName;
	private String materialSize;
	private String supplierName;
	private String buyPersonName;
	private String stateName;
	
	
	
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaterialSize() {
		return materialSize;
	}
	public void setMaterialSize(String materialSize) {
		this.materialSize = materialSize;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getBuyPersonName() {
		return buyPersonName;
	}
	public void setBuyPersonName(String buyPersonName) {
		this.buyPersonName = buyPersonName;
	}
}
