package com.supermap.sppm.yxcms.pojo;

/**
 * TWorkerInfo entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TWorkerInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String wname;
	private String waddress;
	private String phoneNum;
	private String phoneNumBak;
	private String remark;
	private Integer duty; //0厂长 1 车间主任   2印刷工   3 装订工   4 库管  5 出纳   6  采购 
	private Integer state;

	// Constructors

	/** default constructor */
	public TWorkerInfo() {
	}

	/** minimal constructor */
	public TWorkerInfo(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TWorkerInfo(Integer id, String wname, String waddress,
			String phoneNum, String phoneNumBak,
			String remark,Integer state, Integer duty) {
		this.id = id;
		this.wname = wname;
		this.waddress = waddress;
		this.phoneNum = phoneNum;
		this.phoneNumBak = phoneNumBak;
		this.remark = remark;
		this.remark = remark;
		this.remark = remark;
		this.state = state;
		this.duty = duty;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWname() {
		return this.wname;
	}

	public void setWname(String wname) {
		this.wname = wname;
	}

	public String getWaddress() {
		return this.waddress;
	}

	public void setWaddress(String waddress) {
		this.waddress = waddress;
	}

	public String getPhoneNum() {
		return this.phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPhoneNumBak() {
		return this.phoneNumBak;
	}

	public void setPhoneNumBak(String phoneNumBak) {
		this.phoneNumBak = phoneNumBak;
	}


	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getDuty() {
		return duty;
	}

	public void setDuty(Integer duty) {
		this.duty = duty;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
}