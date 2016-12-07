package com.supermap.sppm.yxcms.pojo;

import java.util.Date;

/**
 * TProductOut entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TProductOut implements java.io.Serializable {

	// Fields

	private Integer id;
	private String outCode;
	private Integer fid;
	private Integer outCount;
	private Integer outPersonId;
	private String outDate;
	private String acceptMan;
	private String acceptPhone;
	private String acceptCompany;
	private String remark;

	// Constructors

	/** default constructor */
	public TProductOut() {
	}

	/** minimal constructor */
	public TProductOut(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TProductOut(Integer id, String outCode, Integer fid,
			Integer outCount, Integer outPersonId, String outDate,
			String acceptMan, String acceptPhone, String acceptCompany,
			String remark) {
		this.id = id;
		this.outCode = outCode;
		this.fid = fid;
		this.outCount = outCount;
		this.outPersonId = outPersonId;
		this.outDate = outDate;
		this.acceptMan = acceptMan;
		this.acceptPhone = acceptPhone;
		this.acceptCompany = acceptCompany;
		this.remark = remark;
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

	public Integer getFid() {
		return this.fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public Integer getOutCount() {
		return this.outCount;
	}

	public void setOutCount(Integer outCount) {
		this.outCount = outCount;
	}

	public Integer getOutPersonId() {
		return this.outPersonId;
	}

	public void setOutPersonId(Integer outPersonId) {
		this.outPersonId = outPersonId;
	}

	public String getOutDate() {
		return this.outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public String getAcceptMan() {
		return this.acceptMan;
	}

	public void setAcceptMan(String acceptMan) {
		this.acceptMan = acceptMan;
	}

	public String getAcceptPhone() {
		return this.acceptPhone;
	}

	public void setAcceptPhone(String acceptPhone) {
		this.acceptPhone = acceptPhone;
	}

	public String getAcceptCompany() {
		return this.acceptCompany;
	}

	public void setAcceptCompany(String acceptCompany) {
		this.acceptCompany = acceptCompany;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}