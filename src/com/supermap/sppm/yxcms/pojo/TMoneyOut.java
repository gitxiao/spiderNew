package com.supermap.sppm.yxcms.pojo;


/**
 * TMoneyOut entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TMoneyOut implements java.io.Serializable {

	// Fields

	private Integer id;
	private String moneyCode;
	private String outDate;
	private Integer outPersonId;
	private Integer outCount;
	private Integer outType;
	private String remak;

	// Constructors

	/** default constructor */
	public TMoneyOut() {
	}

	/** minimal constructor */
	public TMoneyOut(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TMoneyOut(Integer id, String moneyCode, String outDate,
			Integer outPersonId, Integer outCount, Integer outType, String remak) {
		this.id = id;
		this.moneyCode = moneyCode;
		this.outDate = outDate;
		this.outPersonId = outPersonId;
		this.outCount = outCount;
		this.outType = outType;
		this.remak = remak;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMoneyCode() {
		return this.moneyCode;
	}

	public void setMoneyCode(String moneyCode) {
		this.moneyCode = moneyCode;
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

	public Integer getOutCount() {
		return this.outCount;
	}

	public void setOutCount(Integer outCount) {
		this.outCount = outCount;
	}

	public Integer getOutType() {
		return this.outType;
	}

	public void setOutType(Integer outType) {
		this.outType = outType;
	}

	public String getRemak() {
		return this.remak;
	}

	public void setRemak(String remak) {
		this.remak = remak;
	}

}