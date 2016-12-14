package cn.muke.ssh.domain;


public class T_Website {
	
	private Integer id;
	private Integer depth;
	private String url;
	private String name;
	private String keywordIds;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getKeywordIds() {
		return keywordIds;
	}
	public void setKeywordIds(String keywordIds) {
		this.keywordIds = keywordIds;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getDepth() {
		return depth;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
