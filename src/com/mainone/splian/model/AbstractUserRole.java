package com.mainone.splian.model;

/**
 * AbstractUserRole entity provides the base persistence definition of the
 * UserRole entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserRole implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer uid;
	private Boolean role;
	private Boolean type;
	private String number;
	private String content;
	private String picture;
	private Boolean status;
	private Integer addtime;
	private Integer updatetime;

	// Constructors

	/** default constructor */
	public AbstractUserRole() {
	}

	/** minimal constructor */
	public AbstractUserRole(Integer uid) {
		this.uid = uid;
	}

	/** full constructor */
	public AbstractUserRole(Integer uid, Boolean role, Boolean type,
			String number, String content, String picture, Boolean status,
			Integer addtime, Integer updatetime) {
		this.uid = uid;
		this.role = role;
		this.type = type;
		this.number = number;
		this.content = content;
		this.picture = picture;
		this.status = status;
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

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Boolean getRole() {
		return this.role;
	}

	public void setRole(Boolean role) {
		this.role = role;
	}

	public Boolean getType() {
		return this.type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
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