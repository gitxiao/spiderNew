package com.supermap.sppm.yxcms.pojo;


/**
 * TMakeList entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TMakeList implements java.io.Serializable {

	public static final int STATE_PRODUCING = 0;	//生产单状态,生产中
	public static final int STATE_FINISHED = 1;		//生产单状态,结束,也表示入库成功
	public static final int STATE_GETPAPER = 2;	    //领料成功
	public static final int STATE_BAD = 3;        //废除,不做查询用
	public static final String DISTRIBUTE_FAIL = "生产单分配失败";		//生产单状态,结束
	
	
	

	private Integer id;					
	private Integer fid;					//订单id
	private String distributeDate;			//分配日期
	private String makeCode;				//生产单编号
	private Integer planPage;				//计划生产数
	private Integer remainPage;				//剩余生产数							//无效,这个数量不会进行查询和修改, 只有生产单结束时才有意义. 而结束时需要
	private Integer printerId;				//印刷工id
	private Integer bindId;					//装订工id
	private String remark;					//备注
	private Integer isEnter;				//是否入库    0 未入库     1 已入库  2 车间主任中断
	private Integer stateSc;				//生产单状态,0进行中,1结束. 主任中断操作或者生产完成入库时会进入结束状态 2 领料成功

	// Constructors

	/** default constructor */
	public TMakeList() {
	}

	/** minimal constructor */
	public TMakeList(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TMakeList(Integer id, Integer fid, String distributeDate,
			String makeCode, Integer productCount, Integer pagination,
			Integer totalPage, Integer doPage, Integer planPage,
			Integer remainPage, Integer printerId, Integer bindId,
			String remark, Integer thisPage, Integer isEnter) {
		this.id = id;
		this.fid = fid;
		this.distributeDate = distributeDate;
		this.makeCode = makeCode;
//		this.productCount = productCount;
//		this.pagination = pagination;
//		this.totalPage = totalPage;
//		this.doPage = doPage;
		this.planPage = planPage;
		this.remainPage = remainPage;
		this.printerId = printerId;
		this.bindId = bindId;
		this.remark = remark;
//		this.thisPage = thisPage;
		this.isEnter = isEnter;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFid() {
		return this.fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getDistributeDate() {
		return this.distributeDate;
	}

	public void setDistributeDate(String distributeDate) {
		this.distributeDate = distributeDate;
	}

	public String getMakeCode() {
		return this.makeCode;
	}

	public void setMakeCode(String makeCode) {
		this.makeCode = makeCode;
	}

	public Integer getPlanPage() {
		return this.planPage;
	}

	public void setPlanPage(Integer planPage) {
		this.planPage = planPage;
	}

	public Integer getRemainPage() {
		return this.remainPage;
	}

	public void setRemainPage(Integer remainPage) {
		this.remainPage = remainPage;
	}

	public Integer getPrinterId() {
		return this.printerId;
	}

	public void setPrinterId(Integer printerId) {
		this.printerId = printerId;
	}

	public Integer getBindId() {
		return this.bindId;
	}

	public void setBindId(Integer bindId) {
		this.bindId = bindId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsEnter() {
		return this.isEnter;
	}

	public void setIsEnter(Integer isEnter) {
		this.isEnter = isEnter;
	}

	public Integer getStateSc() {
		return stateSc;
	}

	public void setStateSc(Integer state) {
		this.stateSc = state;
	}
	
}