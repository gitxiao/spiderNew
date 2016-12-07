package com.supermap.sppm.yxcms.pojo;


/**
 * TMaterialOut entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TMaterialOut implements java.io.Serializable {

	// Fields

	private Integer id;
	private String outCode;   //领料单编码
	private Integer mtype;                                    //暂时不用
	private Integer mid;     //物料ID,这里是纸张的ID
	private Integer tatalCount;                               //暂时不用
	private Integer makeId;    //生产单ID
	private Integer getPersonId;  //领料人ID
	private String outDate;       //领料日期
	private Integer outPersonId;   //出库人ID
	private Integer getCount;     //领料数量

	// Constructors

	/** default constructor */
	public TMaterialOut() {
	}

	/** minimal constructor */
	public TMaterialOut(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TMaterialOut(Integer id, String outCode, Integer mtype, Integer mid,
			Integer tatalCount, Integer makeId, Integer getPersonId,
			String outDate, Integer outPersonId, Integer getCount) {
		this.id = id;
		this.outCode = outCode;
		this.mtype = mtype;
		this.mid = mid;
		this.tatalCount = tatalCount;
		this.makeId = makeId;
		this.getPersonId = getPersonId;
		this.outDate = outDate;
		this.outPersonId = outPersonId;
		this.getCount = getCount;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOutCode() {
		return this.outCode;
	}

	public void setOutCode(String outCode) {
		this.outCode = outCode;
	}

	public Integer getMtype() {
		return this.mtype;
	}

	public void setMtype(Integer mtype) {
		this.mtype = mtype;
	}

	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public Integer getTatalCount() {
		return this.tatalCount;
	}

	public void setTatalCount(Integer tatalCount) {
		this.tatalCount = tatalCount;
	}

	public Integer getMakeId() {
		return this.makeId;
	}

	public void setMakeId(Integer makeId) {
		this.makeId = makeId;
	}

	public Integer getGetPersonId() {
		return this.getPersonId;
	}

	public void setGetPersonId(Integer getPersonId) {
		this.getPersonId = getPersonId;
	}

	public String getOutDate() {
		return this.outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public Integer getOutPersonId() {
		return this.outPersonId;
	}

	public void setOutPersonId(Integer outPersonId) {
		this.outPersonId = outPersonId;
	}

	public Integer getGetCount() {
		return this.getCount;
	}

	public void setGetCount(Integer getCount) {
		this.getCount = getCount;
	}

}