package cn.muke.ssh.domain;

public class T_User {
	
	private Integer id;
	private Integer userType;
	private String name;
	private String password;

	public T_User(){
		
	}
	public T_User(String name,String password,Integer userType){
		this.name = name;
		this.password = password;
		this.userType = userType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
