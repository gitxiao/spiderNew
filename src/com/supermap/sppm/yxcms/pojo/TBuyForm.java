package com.supermap.sppm.yxcms.pojo;

/**
 * TBuyForm entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TBuyForm implements java.io.Serializable {

	// Fields

	private Integer id;
	private String buyFormCode;  //采购单编码
	private Integer mtype;   //物料大类
	private Integer mid;     //物料ID
	private Integer mcount;   //物料数量
	private Integer supid;    //供应商ID
	private Integer totalPrice; //采购单总价格
	private Integer buyId; //采购人ID
	private String buyDate; //采购下订单日期
	private String remark;  //备注
	private Integer state; // 0 采购中    1 已入库
 
	// Constructors

	

	/** default constructor */
	public TBuyForm() {
	}

	/** minimal constructor */
	public TBuyForm(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TBuyForm(Integer id, String buyFormCode, Integer mtype, Integer mid,
			Integer mcount, Integer supid, Integer totalPrice, Integer buyId,
			String buyDate, String remark,Integer state) {
		this.id = id;
		this.buyFormCode = buyFormCode;
		this.mtype = mtype;
		this.mid = mid;
		this.mcount = mcount;
		this.supid = supid;
		this.totalPrice = totalPrice;
		this.buyId = buyId;
		this.buyDate = buyDate;
		this.remark = remark;
		this.state = state;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBuyFormCode() {
		return this.buyFormCode;
	}

	public void setBuyFormCode(String buyFormCode) {
		this.buyFormCode = buyFormCode;
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

	public Integer getMcount() {
		return this.mcount;
	}

	public void setMcount(Integer mcount) {
		this.mcount = mcount;
	}

	public Integer getSupid() {
		return this.supid;
	}

	public void setSupid(Integer supid) {
		this.supid = supid;
	}

	public Integer getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getBuyId() {
		return this.buyId;
	}

	public void setBuyId(Integer buyId) {
		this.buyId = buyId;
	}

	public String getBuyDate() {
		return this.buyDate;
	}

	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}