package com.supermap.sppm.yxcms.pojo;

/**
 * TCustomer entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TCustomer implements java.io.Serializable {

	// Fields

	private Integer id;
	private String cusName;
	private String cusAddress;
	private String cusPhone;
	private String cusPerson;

	// Constructors

	/** default constructor */
	public TCustomer() {
	}

	/** minimal constructor */
	public TCustomer(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TCustomer(Integer id, String cusName, String cusAddress,
			String cusPhone, String cusPerson) {
		this.id = id;
		this.cusName = cusName;
		this.cusAddress = cusAddress;
		this.cusPhone = cusPhone;
		this.cusPerson = cusPerson;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCusName() {
		return this.cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusAddress() {
		return this.cusAddress;
	}

	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}

	public String getCusPhone() {
		return this.cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}

	public String getCusPerson() {
		return this.cusPerson;
	}

	public void setCusPerson(String cusPerson) {
		this.cusPerson = cusPerson;
	}

}