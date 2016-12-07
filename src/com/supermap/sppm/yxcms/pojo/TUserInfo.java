package com.supermap.sppm.yxcms.pojo;

/**
 * TUserInfo entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class TUserInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String loginName;
	private String passWord;
	private Integer userType;   
	private String phoneNum;
	private String typeName;

	// Constructors

	/** default constructor */
	public TUserInfo() {
	}

	/** minimal constructor */
	public TUserInfo(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public TUserInfo(Integer id, String loginName, String passWord,
			Integer userType,String phoneNum,String typeName) {
		this.id = id;
		this.loginName = loginName;
		this.passWord = passWord;
		this.userType = userType;
		this.phoneNum = phoneNum;
		this.typeName = typeName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassWord() {
		return this.passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}