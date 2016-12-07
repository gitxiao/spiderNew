package com.supermap.sppm.yxcms.pojo;

/**
 * TSupplier entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TSupplier implements java.io.Serializable {

	// Fields

	private Integer id;
	private String supName;
	private String supAddress;
	private String supPhone;
	private String supPerson;

	// Constructors

	/** default constructor */
	public TSupplier() {
	}

	/** minimal constructor */
	public TSupplier(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TSupplier(Integer id, String supName, String supAddress,
			String supPhone, String supPerson) {
		this.id = id;
		this.supName = supName;
		this.supAddress = supAddress;
		this.supPhone = supPhone;
		this.supPerson = supPerson;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSupName() {
		return this.supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}

	public String getSupAddress() {
		return this.supAddress;
	}

	public void setSupAddress(String supAddress) {
		this.supAddress = supAddress;
	}

	public String getSupPhone() {
		return this.supPhone;
	}

	public void setSupPhone(String supPhone) {
		this.supPhone = supPhone;
	}

	public String getSupPerson() {
		return this.supPerson;
	}

	public void setSupPerson(String supPerson) {
		this.supPerson = supPerson;
	}

}