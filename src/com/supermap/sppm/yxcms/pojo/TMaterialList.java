package com.supermap.sppm.yxcms.pojo;


/**
 * TMaterialList entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TMaterialList implements java.io.Serializable {

	// Fields

	private Integer id;  //库存记录ID
	private Integer mid; //物料ID
	private Integer count; //库存数量
	private Integer state; //物料状态   1 正常   2 停用
	private String enterDate; //入库日期
	private String remark; //备注
	private Integer enterId; //入库单ID

	// Constructors

	/** default constructor */
	public TMaterialList() {
	}

	/** minimal constructor */
	public TMaterialList(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TMaterialList(Integer id, Integer mid, Integer count, Integer state,
			String enterDate, String remark, Integer enterId) {
		this.id = id;
		this.mid = mid;
		this.count = count;
		this.state = state;
		this.enterDate = enterDate;
		this.remark = remark;
		this.enterId = enterId;
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

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getEnterDate() {
		return this.enterDate;
	}

	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getEnterId() {
		return this.enterId;
	}

	public void setEnterId(Integer enterId) {
		this.enterId = enterId;
	}

}