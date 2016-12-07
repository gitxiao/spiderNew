package com.supermap.sppm.yxcms.pojo;

@SuppressWarnings("serial")
public class DetailMakeList extends TMakeList {
	
	
	
	//生产单属性---------------------------------------------------------------------------->>>
	private String stateScName;
	//生产单属性----------------------------------------------------------------------------<<<
	private String printerName;		//印刷工姓名
	private String binderName;		//装订工姓名
	//订单中的属性---------------------------------------------------------------------------->>>
	private String cusName;			//客户名
	private String printTypeName;	//印刷工艺
	private int allPageNum;			//总页数数量
	private int producedNum;		//已生产页数数量
	private String stateName;			//状态名
	private String formCode; //订单编码
	private String formDate; //订单日期
	private Integer customerId; //客户ID
	private String cusPhone; //客户联系方式
	private String productName; //产品名称
	private Integer paperId; //原材料ID.这里是纸张类型原材料的ID
	private Integer productSize; //产品尺寸,4k,8k,16k等
	private Integer pagination; //产品页码数
	private Integer printType; //打印类型 1 覆膜,2 UV,3压纹,4起鼓,5烫金,6烫银,7花线,8磨切,9压痕
	private Integer bindtype; //装订类型, 1 线订/2胶订,骑马钉,无封装
	private String printFrom; //出版地址
	private String bakCode; //内资备案号
	private String demoCode; //小样备案号
	private Integer formPrice; //订单价格
	private Integer makeType; //制作类型,  1 自印   2外协 
	private Integer partnerId; //外协单位ID
	private Integer userId;  //暂时为用户类型,后面改为用户ID
	private Integer printPrice; //外协价格或者工厂报价
	private Integer isGiveMoney; //是否给印刷厂或者外协单位钱
	private Integer isGetMoney;  //订单是否已经收钱
	private String giveMoneyDate; //给钱日期
	private String getMoneyDate; //收钱日期
	private Integer productCount; //产品数量
	private String finishDate; //订单完成日期
	private String designer; //订单设计者
	private String submitDate; //订单安排生产日期
	private Integer isFinish; //订单是否结束  1代表未技术  0代表结束
	private Integer isPart; //订单是否外协       1 自印   2外协
	private String expectDate; //交货时间时间
	protected Integer state; //分配状态 0 分配完毕  1 分配中   2 待分配 
	private Integer isGivePrice; //是否报价 0 未报价   1已报价
	private Integer unAssignedNum; //未分配生产的数量,初始值为(产品数量*产品页码)productCount*pagination
	
	private String bindTypeName;   //装订类型名称 
	private String paperDetail;    //纸张名称+型号 
	private String makeTypeName;	//加工类型名
	//订单中的属性----------------------------------------------------------------------------<<<
	
	public void setStateSc(Integer state) {
		super.setStateSc(state);
		setStateScName(state);
	}
	public String getMakeTypeName() {
		return makeTypeName;
	}
	public void setMakeTypeName(String makeTypeName) {
		this.makeTypeName = makeTypeName;
	}
	public String getStateScName() {
		return stateScName;
	}
	public void setStateScName(Integer state) {
		switch (state) {
		case STATE_PRODUCING:
			this.stateScName = "待领料";
			break;
		case STATE_FINISHED:
			this.stateScName = "结束";
			break;
		case STATE_GETPAPER:
			this.stateScName = "生产中";
			break;
		case STATE_BAD:
			this.stateScName = "废除";
			break;
		default:
			this.stateScName = "其他";
			break;
		}
	}
	public String getPrinterName() {
		return printerName;
	}
	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}
	public String getBinderName() {
		return binderName;
	}
	public void setBinderName(String binderName) {
		this.binderName = binderName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public void setPrintTypeName(String printTypeName) {
		this.printTypeName = printTypeName;
	}
	public void setAllPageNum(int allPageNum) {
		this.allPageNum = allPageNum;
	}
	public void setProducedNum(int producedNum) {
		this.producedNum = producedNum;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}
	public void setFormDate(String formDate) {
		this.formDate = formDate;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	public void setProductSize(Integer productSize) {
		this.productSize = productSize;
	}
	public void setPagination(Integer pagination) {
		this.pagination = pagination;
	}
	public void setPrintType(Integer printType) {
		this.printType = printType;
	}
	public void setBindtype(Integer bindtype) {
		this.bindtype = bindtype;
	}
	public void setPrintFrom(String printFrom) {
		this.printFrom = printFrom;
	}
	public void setBakCode(String bakCode) {
		this.bakCode = bakCode;
	}
	public void setDemoCode(String demoCode) {
		this.demoCode = demoCode;
	}
	public void setFormPrice(Integer formPrice) {
		this.formPrice = formPrice;
	}
	public void setMakeType(Integer makeType) {
		this.makeType = makeType;
	}
	public void setPartnerId(Integer partnerId) {
		this.partnerId = partnerId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setPrintPrice(Integer printPrice) {
		this.printPrice = printPrice;
	}
	public void setIsGiveMoney(Integer isGiveMoney) {
		this.isGiveMoney = isGiveMoney;
	}
	public void setIsGetMoney(Integer isGetMoney) {
		this.isGetMoney = isGetMoney;
	}
	public void setGiveMoneyDate(String giveMoneyDate) {
		this.giveMoneyDate = giveMoneyDate;
	}
	public void setGetMoneyDate(String getMoneyDate) {
		this.getMoneyDate = getMoneyDate;
	}
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	public void setDesigner(String designer) {
		this.designer = designer;
	}
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
	public void setIsFinish(Integer isFinish) {
		this.isFinish = isFinish;
	}
	public void setIsPart(Integer isPart) {
		this.isPart = isPart;
	}
	public void setExpectDate(String expectDate) {
		this.expectDate = expectDate;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public void setIsGivePrice(Integer isGivePrice) {
		this.isGivePrice = isGivePrice;
	}
	public void setUnAssignedNum(Integer unAssignedNum) {
		this.unAssignedNum = unAssignedNum;
	}
	public String getCusName() {
		return cusName;
	}
	public String getPrintTypeName() {
		return printTypeName;
	}
	public int getAllPageNum() {
		return allPageNum;
	}
	public int getProducedNum() {
		return producedNum;
	}
	public String getStateName() {
		return stateName;
	}
	public String getFormCode() {
		return formCode;
	}
	public String getFormDate() {
		return formDate;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public String getCusPhone() {
		return cusPhone;
	}
	public String getProductName() {
		return productName;
	}
	public Integer getPaperId() {
		return paperId;
	}
	public Integer getProductSize() {
		return productSize;
	}
	public Integer getPagination() {
		return pagination;
	}
	public Integer getPrintType() {
		return printType;
	}
	public Integer getBindtype() {
		return bindtype;
	}
	public String getPrintFrom() {
		return printFrom;
	}
	public String getBakCode() {
		return bakCode;
	}
	public String getDemoCode() {
		return demoCode;
	}
	public Integer getFormPrice() {
		return formPrice;
	}
	public Integer getMakeType() {
		return makeType;
	}
	public Integer getPartnerId() {
		return partnerId;
	}
	public Integer getUserId() {
		return userId;
	}
	public Integer getPrintPrice() {
		return printPrice;
	}
	public Integer getIsGiveMoney() {
		return isGiveMoney;
	}
	public Integer getIsGetMoney() {
		return isGetMoney;
	}
	public String getGiveMoneyDate() {
		return giveMoneyDate;
	}
	public String getGetMoneyDate() {
		return getMoneyDate;
	}
	public Integer getProductCount() {
		return productCount;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public String getDesigner() {
		return designer;
	}
	public String getSubmitDate() {
		return submitDate;
	}
	public Integer getIsFinish() {
		return isFinish;
	}
	public Integer getIsPart() {
		return isPart;
	}
	public String getExpectDate() {
		return expectDate;
	}
	public Integer getState() {
		return state;
	}
	public Integer getIsGivePrice() {
		return isGivePrice;
	}
	public Integer getUnAssignedNum() {
		return unAssignedNum;
	}
	public String getBindTypeName() {
		return bindTypeName;
	}
	public void setBindTypeName(String bindTypeName) {
		this.bindTypeName = bindTypeName;
	}
	public String getPaperDetail() {
		return paperDetail;
	}
	public void setPaperDetail(String paperDetail) {
		this.paperDetail = paperDetail;
	}
	public void setStateScName(String stateScName) {
		this.stateScName = stateScName;
	}
	
	
}
