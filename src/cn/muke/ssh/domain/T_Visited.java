package cn.muke.ssh.domain;

public class T_Visited {
	
	private Integer id;
	private String url;
	
	public T_Visited(){
		
	}
	public T_Visited(String url){
		this.url = url;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
