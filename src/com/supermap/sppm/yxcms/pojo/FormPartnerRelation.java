package com.supermap.sppm.yxcms.pojo;

/**
 * FormPartnerRelation entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class FormPartnerRelation implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer fid;
	private Integer prid;
	private Integer partType;
	private String remark;

	// Constructors

	/** default constructor */
	public FormPartnerRelation() {
	}

	/** minimal constructor */
	public FormPartnerRelation(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public FormPartnerRelation(Integer id, Integer fid, Integer prid,
			Integer partType, String remark) {
		this.id = id;
		this.fid = fid;
		this.prid = prid;
		this.partType = partType;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFid() {
		return this.fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public Integer getPrid() {
		return this.prid;
	}

	public void setPrid(Integer prid) {
		this.prid = prid;
	}

	public Integer getPartType() {
		return this.partType;
	}

	public void setPartType(Integer partType) {
		this.partType = partType;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}