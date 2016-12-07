package com.supermap.sppm.yxcms.pojo;

/**
 * TMaterialType entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TMaterialType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String mname;
	private Integer mtype;
	private Integer dtype;
	private String msize;
	private String munit;
	private Integer mstate;
	private String mremark;

	// Constructors

	/** default constructor */
	public TMaterialType() {
	}

	/** minimal constructor */
	public TMaterialType(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TMaterialType(Integer id, String mname, Integer mtype, Integer dtype, String msize,
			String munit,int mstate,String mremark) {
		this.id = id;
		this.mname = mname;
		this.mtype = mtype;
		this.dtype = dtype;
		this.msize = msize;
		this.munit = munit;
		this.mstate = mstate;
		this.mremark = mremark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMname() {
		return this.mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public Integer getMtype() {
		return this.mtype;
	}

	public void setMtype(Integer mtype) {
		this.mtype = mtype;
	}
	
	public Integer getDtype() {
		return dtype;
	}

	public void setDtype(Integer dtype) {
		this.dtype = dtype;
	}

	public String getMsize() {
		return this.msize;
	}

	public void setMsize(String msize) {
		this.msize = msize;
	}

	public String getMunit() {
		return this.munit;
	}

	public void setMunit(String munit) {
		this.munit = munit;
	}

	public Integer getMstate() {
		return mstate;
	}

	public void setMstate(Integer mstate) {
		this.mstate = mstate;
	}

	public String getMremark() {
		return mremark;
	}

	public void setMremark(String mremark) {
		this.mremark = mremark;
	}
	
}