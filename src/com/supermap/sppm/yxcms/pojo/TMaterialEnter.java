package com.supermap.sppm.yxcms.pojo;


/**
 * TMaterialEnter entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TMaterialEnter implements java.io.Serializable {

	// Fields

	private Integer id;  //入库单ID
	private String menterCode;  //入库单编码
	private Integer bid;        //采购单ID
	private Integer enterMtype;  //入库物料类型
	private Integer enterMid;    //入库物料ID
	private Integer enterCount;  //入库数量
	private String enterDate;      //入库时间
	private Integer enterPersonId;  //入库人ID
	private Integer supId;        //最新的供应商的ID
	private String remark;        //备注

	// Constructors

	/** default constructor */
	public TMaterialEnter() {
	}

	/** minimal constructor */
	public TMaterialEnter(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TMaterialEnter(Integer id, String menterCode, Integer bid,
			Integer enterMtype, Integer enterMid, Integer enterCount,
			String enterDate, Integer enterPersonId, Integer supId, String remark) {
		this.id = id;
		this.menterCode = menterCode;
		this.bid = bid;
		this.enterMtype = enterMtype;
		this.enterMid = enterMid;
		this.enterCount = enterCount;
		this.enterDate = enterDate;
		this.enterPersonId = enterPersonId;
		this.supId = supId;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMenterCode() {
		return this.menterCode;
	}

	public void setMenterCode(String menterCode) {
		this.menterCode = menterCode;
	}

	public Integer getBid() {
		return this.bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public Integer getEnterMtype() {
		return this.enterMtype;
	}

	public void setEnterMtype(Integer enterMtype) {
		this.enterMtype = enterMtype;
	}

	public Integer getEnterMid() {
		return this.enterMid;
	}

	public void setEnterMid(Integer enterMid) {
		this.enterMid = enterMid;
	}

	public Integer getEnterCount() {
		return this.enterCount;
	}

	public void setEnterCount(Integer enterCount) {
		this.enterCount = enterCount;
	}

	public String getEnterDate() {
		return this.enterDate;
	}

	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
	}

	public Integer getEnterPersonId() {
		return this.enterPersonId;
	}

	public void setEnterPersonId(Integer enterPersonId) {
		this.enterPersonId = enterPersonId;
	}

	public Integer getSupId() {
		return this.supId;
	}

	public void setSupId(Integer supId) {
		this.supId = supId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}