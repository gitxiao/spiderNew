package com.supermap.sppm.yxcms.pojo;

@SuppressWarnings("serial")
public class DetailMaterialType extends TMaterialType {

	public static final int USING = 0;
	public static final int UNUSING = 1;
	public static final int M_PAPER = 1;
	public static final int M_MO = 2;
	private int count;
	private String mtypename;
	private String mstatename;

	public Integer getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
	public String getMtypename() {
		return mtypename;
	}

	public void setMtypename(String mtypename) {
		this.mtypename = mtypename;
	}

	public String getMstatename() {
		return mstatename;
	}

	public void setMstatename(String mstatename) {
		this.mstatename = mstatename;
	}

	public void resetNames(){
		int mtype = this.getMtype();
		int mstate = this.getMstate();
		switch(mtype){
		case M_PAPER:
			setMtypename("纸");
			break;
		case M_MO:
			setMtypename("墨");
			break;
		}
		switch(mstate){
		case USING:
			setMstatename("在用");
			break;
		case UNUSING:
			setMstatename("停用");
			break;
		}
	}
}
