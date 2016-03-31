package com.mainone.splian.model;

/**
 * AbstractStationLetter entity provides the base persistence definition of the
 * StationLetter entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractStationLetter implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer fromUid;
	private Integer toUid;
	private Integer pid;
	private String content;
	private String url;
	private Boolean type;
	private Boolean status;
	private Integer sum;
	private Integer addtime;
	private Integer updatetime;

	// Constructors

	/** default constructor */
	public AbstractStationLetter() {
	}

	/** minimal constructor */
	public AbstractStationLetter(Integer fromUid, Integer toUid) {
		this.fromUid = fromUid;
		this.toUid = toUid;
	}

	/** full constructor */
	public AbstractStationLetter(Integer fromUid, Integer toUid, Integer pid,
			String content, String url, Boolean type, Boolean status,
			Integer sum, Integer addtime, Integer updatetime) {
		this.fromUid = fromUid;
		this.toUid = toUid;
		this.pid = pid;
		this.content = content;
		this.url = url;
		this.type = type;
		this.status = status;
		this.sum = sum;
		this.addtime = addtime;
		this.updatetime = updatetime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFromUid() {
		return this.fromUid;
	}

	public void setFromUid(Integer fromUid) {
		this.fromUid = fromUid;
	}

	public Integer getToUid() {
		return this.toUid;
	}

	public void setToUid(Integer toUid) {
		this.toUid = toUid;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getType() {
		return this.type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getSum() {
		return this.sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public Integer getAddtime() {
		return this.addtime;
	}

	public void setAddtime(Integer addtime) {
		this.addtime = addtime;
	}

	public Integer getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Integer updatetime) {
		this.updatetime = updatetime;
	}

}