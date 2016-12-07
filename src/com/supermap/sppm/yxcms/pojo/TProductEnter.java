package com.supermap.sppm.yxcms.pojo;


/**
 * TProductEnter entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TProductEnter implements java.io.Serializable {

	// Fields

	private Integer id;    //ID
	private Integer mid;   //物料ID
	private Integer ofId;  //生产单ID
	private Integer mCount;  //物料数量
	private String enterCode; //产品入库单编码
	private Integer doCount;  //已经完成数量
	private Integer enterCount; //入库数量
	private String enterDate;     //入库时间
	private Integer enterPersonId; //入库人ID
	private String remark;   //备注

	// Constructors

	/** default constructor */
	public TProductEnter() {
	}

	/** minimal constructor */
	public TProductEnter(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TProductEnter(Integer id, Integer mid,Integer ofId, Integer mCount, String enterCode,
			Integer doCount, Integer enterCount, String enterDate,
			Integer enterPersonId, String remark) {
		this.id = id;
		this.mid = mid;
		this.ofId = ofId;
		this.mCount = mCount;
		this.enterCode = enterCode;
		this.doCount = doCount;
		this.enterCount = enterCount;
		this.enterDate = enterDate;
		this.enterPersonId = enterPersonId;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getEnterCode() {
		return this.enterCode;
	}

	public void setEnterCode(String enterCode) {
		this.enterCode = enterCode;
	}

	public Integer getDoCount() {
		return this.doCount;
	}

	public void setDoCount(Integer doCount) {
		this.doCount = doCount;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getOfId() {
		return ofId;
	}

	public void setOfId(Integer ofId) {
		this.ofId = ofId;
	}

	public Integer getmCount() {
		return mCount;
	}

	public void setmCount(Integer mCount) {
		this.mCount = mCount;
	}


}