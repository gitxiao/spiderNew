package com.supermap.sppm.yxcms.pojo;


/**
 * TOrderForm entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TOrderForm implements java.io.Serializable {

	// Fields

	private Integer id;  //id
	private String formCode; //订单编码
	private String formDate; //订单日期
	private Integer customerId; //客户ID
	private String cusPhone; //客户联系方式
	private String productName; //产品名称
	private Integer paperId; //原材料ID.这里是纸张类型原材料的ID
	private Integer productSize; //产品尺寸,4k,8k,16k等
	private Integer pagination; //产品页码数
	private Integer printType; //打印类型 1 铜版/ 2喷墨
	private Integer bindtype; //装订类型, 1 线装/2喷胶
	private String printFrom; //出版地址
	private String bakCode; //内资备案号
	private String demoCode; //小样备案号
	private Integer formPrice; //订单价格
	private Integer partnerId; //外协单位ID
	private Integer userId;  //暂时为用户类型,后面改为用户ID
	private String remark; //备注
	private String priceRemark; //报价备注
	private Integer isGiveMoney; //是否给印刷厂或者外协单位钱,0未给钱  1已给钱
	private Integer isGetMoney;  //订单是否已经收钱 0 未收钱 1已收钱
	private String giveMoneyDate; //给钱日期
	private String getMoneyDate; //收钱日期
	private Integer productCount; //产品数量
	private String finishDate; //订单完成日期
	private String designer; //订单设计者
	private String submitDate; //规定订单交货日期
	private Integer isFinish; //订单是否结束  1代表未结束  0代表结束
	private Integer isPart; //订单是否外协       0 没有外协   1 门店外协    2 工厂外协    3 车间主任外协
	private Integer makeType; //制作类型,  1 自印   2外协 
	private String expectDate; //交货时间时间
	private Integer remainPage; //剩余页码
	protected Integer state; //订单状态        1 新订单(待报价)         2  已报价(待分配)    3 生产中(已分配生产单)   4 生产完成(已全部入库, 待发货 ,待出库状态)  5 发货(已出库) 6 结束(已经给钱)
	private Integer printPrice; //外协价格或者工厂报价
	private Integer isGivePrice; //是否报价 0 未报价   1已报价
	private Integer unAssignedNum; //未分配生产的数量,初始值为(产品数量*产品页码)productCount*pagination
	private Integer templateNum;	//版数
	private Double templatePrice;	//版单价

	/** default constructor */
	public TOrderForm() {
	}

	/** minimal constructor */
	public TOrderForm(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TOrderForm(Integer id, String formCode, String formDate,
			Integer customerId, String cusPhone, String productName,
			Integer paperId, Integer productSize, Integer pagination,
			Integer printType, Integer bindtype, String printFrom,
			String bakCode, String demoCode, Integer formPrice,
			Integer makeType, Integer partnerId, Integer userId, String remark,
			Integer printPrice, Integer isGiveMoney, Integer isGetMoney,
			String giveMoneyDate, String getMoneyDate, Integer productCount,
			String finishDate, String designer, String submitDate,
			Integer isFinish, Integer isPart, String expectDate,
			Integer remainPage,Integer state, Integer isGivePrice) {
		this.id = id;
		this.formCode = formCode;
		this.formDate = formDate;
		this.customerId = customerId;
		this.cusPhone = cusPhone;
		this.productName = productName;
		this.paperId = paperId;
		this.productSize = productSize;
		this.pagination = pagination;
		this.printType = printType;
		this.bindtype = bindtype;
		this.printFrom = printFrom;
		this.bakCode = bakCode;
		this.demoCode = demoCode;
		this.formPrice = formPrice;
		this.makeType = makeType;
		this.partnerId = partnerId;
		this.userId = userId;
		this.remark = remark;
		this.printPrice = printPrice;
		this.isGiveMoney = isGiveMoney;
		this.isGetMoney = isGetMoney;
		this.giveMoneyDate = giveMoneyDate;
		this.getMoneyDate = getMoneyDate;
		this.productCount = productCount;
		this.finishDate = finishDate;
		this.designer = designer;
		this.submitDate = submitDate;
		this.isFinish = isFinish;
		this.isPart = isPart;
		this.expectDate = expectDate;
		this.remainPage = remainPage;
		this.state = state;
		this.isGivePrice = isGivePrice;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFormCode() {
		return this.formCode;
	}

	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}

	public String getFormDate() {
		return this.formDate;
	}

	public void setFormDate(String formDate) {
		this.formDate = formDate;
	}

	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCusPhone() {
		return this.cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getPaperId() {
		return this.paperId;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

	public Integer getProductSize() {
		return this.productSize;
	}

	public void setProductSize(Integer productSize) {
		this.productSize = productSize;
	}

	public Integer getPagination() {
		return this.pagination;
	}

	public void setPagination(Integer pagination) {
		this.pagination = pagination;
	}

	public Integer getPrintType() {
		return this.printType;
	}

	public void setPrintType(Integer printType) {
		this.printType = printType;
	}

	public Integer getBindtype() {
		return this.bindtype;
	}

	public void setBindtype(Integer bindtype) {
		this.bindtype = bindtype;
	}

	public String getPrintFrom() {
		return this.printFrom;
	}

	public void setPrintFrom(String printFrom) {
		this.printFrom = printFrom;
	}

	public String getBakCode() {
		return this.bakCode;
	}

	public void setBakCode(String bakCode) {
		this.bakCode = bakCode;
	}

	public String getDemoCode() {
		return this.demoCode;
	}

	public void setDemoCode(String demoCode) {
		this.demoCode = demoCode;
	}

	public Integer getFormPrice() {
		return this.formPrice;
	}

	public void setFormPrice(Integer formPrice) {
		this.formPrice = formPrice;
	}

	public Integer getMakeType() {
		return this.makeType;
	}

	public void setMakeType(Integer makeType) {
		this.makeType = makeType;
	}

	public Integer getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(Integer partnerId) {
		this.partnerId = partnerId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPrintPrice() {
		return this.printPrice;
	}

	public void setPrintPrice(Integer printPrice) {
		this.printPrice = printPrice;
	}

	public Integer getIsGiveMoney() {
		return this.isGiveMoney;
	}

	public void setIsGiveMoney(Integer isGiveMoney) {
		this.isGiveMoney = isGiveMoney;
	}

	public Integer getIsGetMoney() {
		return this.isGetMoney;
	}

	public void setIsGetMoney(Integer isGetMoney) {
		this.isGetMoney = isGetMoney;
	}

	public String getGiveMoneyDate() {
		return this.giveMoneyDate;
	}

	public void setGiveMoneyDate(String giveMoneyDate) {
		this.giveMoneyDate = giveMoneyDate;
	}

	public String getGetMoneyDate() {
		return this.getMoneyDate;
	}

	public void setGetMoneyDate(String getMoneyDate) {
		this.getMoneyDate = getMoneyDate;
	}

	public Integer getProductCount() {
		return this.productCount;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	public String getFinishDate() {
		return this.finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public String getDesigner() {
		return this.designer;
	}

	public void setDesigner(String designer) {
		this.designer = designer;
	}

	public String getSubmitDate() {
		return this.submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public Integer getIsFinish() {
		return this.isFinish;
	}

	public void setIsFinish(Integer isFinish) {
		this.isFinish = isFinish;
	}

	public Integer getIsPart() {
		return this.isPart;
	}

	public void setIsPart(Integer isPart) {
		this.isPart = isPart;
	}

	public String getExpectDate() {
		return this.expectDate;
	}

	public void setExpectDate(String expectDate) {
		this.expectDate = expectDate;
	}

	public Integer getRemainPage() {
		return this.remainPage;
	}

	public void setRemainPage(Integer remainPage) {
		this.remainPage = remainPage;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public Integer getIsGivePrice() {
		return isGivePrice;
	}

	public void setIsGivePrice(Integer isGivePrice) {
		this.isGivePrice = isGivePrice;
	}

	public Integer getUnAssignedNum() {
		return unAssignedNum;
	}

	public void setUnAssignedNum(Integer unAssignedNum) {
		this.unAssignedNum = unAssignedNum;
	}

	public String getPriceRemark() {
		return priceRemark;
	}

	public void setPriceRemark(String priceRemark) {
		this.priceRemark = priceRemark;
	}

	public Integer getTemplateNum() {
		return templateNum;
	}

	public void setTemplateNum(Integer templateNum) {
		this.templateNum = templateNum;
	}

	public Double getTemplatePrice() {
		return templatePrice;
	}

	public void setTemplatePrice(Double templatePrice) {
		this.templatePrice = templatePrice;
	}
	
}