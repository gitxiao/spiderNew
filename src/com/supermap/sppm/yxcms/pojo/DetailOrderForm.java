package com.supermap.sppm.yxcms.pojo;


@SuppressWarnings("serial")
public class DetailOrderForm extends TOrderForm {
	private String cusName;			//客户名
	private String printTypeName;	//印刷工艺
	private int allPageNum;			//总页数数量
	private int producedNum;		//已生产页数数量
	private String stateName;			//状态名
	private String bindTypeName;   //装订类型名称 
	private String paperDetail;    //纸张名称+型号 
    private String makeTypeName;	//加工类型名
    
	public String getPaperDetail() { 
		return paperDetail; 
	} 
	public void setPaperDetail(String paperDetail) { 
		this.paperDetail = paperDetail; 
	} 
	public String getBindTypeName() { 
		return bindTypeName; 
	} 
	public void setBindTypeName(Integer bindType) { 
		switch (bindType) {
		case 1:
			this.bindTypeName = "线订";
			break;
		case 2:
			this.bindTypeName = "胶订";
			break;
		case 3:
			this.bindTypeName = "骑马订";
			break;
		case 4:
			this.bindTypeName = "无缝装";
			break;
		default:
			break;
		}
	}
	public String getMakeTypeName() { 
		return makeTypeName; 
	} 
	public void setMakeTypeName(Integer makeType) { 
		if(makeType==1){ 
			this.makeTypeName = "自印"; 
		}else if(makeType==2){ 
			this.makeTypeName = "外协"; 
		}else{
			this.makeTypeName = "其他"; 
		}
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getPrintTypeName() {
		return printTypeName;
	}
	public void setPrintTypeName(Integer printType) {
		if(printType==1){
			this.printTypeName ="铜版";
		}else{
			this.printTypeName ="喷墨";
		}
		switch (printType) {
		case 1:
			this.printTypeName ="覆膜";
			break;
		case 2:
			this.printTypeName ="UV";
			break;
		case 3:
			this.printTypeName ="压纹";
			break;
		case 4:
			this.printTypeName ="起鼓";
			break;
		case 5:
			this.printTypeName ="烫金";
			break;
		case 6:
			this.printTypeName ="烫银";
			break;
		case 7:
			this.printTypeName ="花线";
			break;
		case 8:
			this.printTypeName ="磨切";
			break;
		case 9:
			this.printTypeName ="压痕";
			break;
		default:
			break;
		}
	}
	public int getAllPageNum() {
		return allPageNum;
	}
	public int getProducedNum() {
		return producedNum;
	}
	public void computeAllPageNum(){
		allPageNum = this.getProductCount() * this.getPagination();
	}
	public void computeProducedNum() {
		this.producedNum = this.getAllPageNum() - this.getRemainPage();
	}
//	public void setState(Integer state){
//		this.state = state;
//		this.resetStateName();
//	}
	public void resetStateName(){
		int productCount = getProductCount();
		int pagination = getPagination();
		int allpagetCount = productCount * pagination;
		int unAssignedNum = getUnAssignedNum();		
		if(unAssignedNum == allpagetCount){
			this.stateName = "待分配";
		}else if(unAssignedNum == 0){
			this.stateName = "分配完毕";
		}else if(unAssignedNum > 0 && unAssignedNum < allpagetCount){
			this.stateName = "分配中";
		}else{
			this.stateName = "数据异常";
		}
	}
	public String getStateName(){
		return this.stateName;
	}

	public void initDetailData(){
		//执行之前需要判断里面的属性是否已经初始化
		computeAllPageNum();
		computeProducedNum();
		setPrintTypeName(getPrintType());
	}
}
