package com.supermap.sppm.yxcms.pojo;

/**
 * TWorkPay entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TWorkPay implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer workId;
	private Integer year;
	private Integer month;
	private Integer workDay;
	private Integer basePay;
	private Integer perfPay;
	private String remark;

	// Constructors

	/** default constructor */
	public TWorkPay() {
	}

	/** minimal constructor */
	public TWorkPay(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TWorkPay(Integer id, Integer workId, Integer year, Integer month,
			Integer workDay, Integer basePay, Integer perfPay, String remark) {
		this.id = id;
		this.workId = workId;
		this.year = year;
		this.month = month;
		this.workDay = workDay;
		this.basePay = basePay;
		this.perfPay = perfPay;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWorkId() {
		return this.workId;
	}

	public void setWorkId(Integer workId) {
		this.workId = workId;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return this.month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getWorkDay() {
		return this.workDay;
	}

	public void setWorkDay(Integer workDay) {
		this.workDay = workDay;
	}

	public Integer getBasePay() {
		return this.basePay;
	}

	public void setBasePay(Integer basePay) {
		this.basePay = basePay;
	}

	public Integer getPerfPay() {
		return this.perfPay;
	}

	public void setPerfPay(Integer perfPay) {
		this.perfPay = perfPay;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}