package com.supermap.sppm.yxcms.pojo;

/**
 * TPartner entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TPartner implements java.io.Serializable {

	// Fields

	private Integer id;
	private String partnerName;
	private String partNerPhone;
	private String partNerAddress;
	private String partnerPerson;
	private String personMible;

	// Constructors

	/** default constructor */
	public TPartner() {
	}

	/** minimal constructor */
	public TPartner(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TPartner(Integer id, String partnerName, String partNerPhone,
			String partNerAddress, String partnerPerson, String personMible) {
		this.id = id;
		this.partnerName = partnerName;
		this.partNerPhone = partNerPhone;
		this.partNerAddress = partNerAddress;
		this.partnerPerson = partnerPerson;
		this.personMible = personMible;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPartnerName() {
		return this.partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getPartNerPhone() {
		return this.partNerPhone;
	}

	public void setPartNerPhone(String partNerPhone) {
		this.partNerPhone = partNerPhone;
	}

	public String getPartNerAddress() {
		return this.partNerAddress;
	}

	public void setPartNerAddress(String partNerAddress) {
		this.partNerAddress = partNerAddress;
	}

	public String getPartnerPerson() {
		return this.partnerPerson;
	}

	public void setPartnerPerson(String partnerPerson) {
		this.partnerPerson = partnerPerson;
	}

	public String getPersonMible() {
		return this.personMible;
	}

	public void setPersonMible(String personMible) {
		this.personMible = personMible;
	}

}