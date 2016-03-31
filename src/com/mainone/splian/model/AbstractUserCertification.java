package com.mainone.splian.model;

/**
 * AbstractUserCertification entity provides the base persistence definition of
 * the UserCertification entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserCertification implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer uid;
	private Boolean type;
	private String name;
	private Integer category;
	private String number;
	private String picture;
	private Boolean status;
	private Integer adminId;
	private String reason;
	private Integer addtime;
	private Integer updatetime;

	// Constructors

	/** default constructor */
	public AbstractUserCertification() {
	}

	/** minimal constructor */
	public AbstractUserCertification(Integer uid, Boolean type, Integer category) {
		this.uid = uid;
		this.type = type;
		this.category = category;
	}

	/** full constructor */
	public AbstractUserCertification(Integer uid, Boolean type, String name,
			Integer category, String number, String picture, Boolean status,
			Integer adminId, String reason, Integer addtime, Integer updatetime) {
		this.uid = uid;
		this.type = type;
		this.name = name;
		this.category = category;
		this.number = number;
		this.picture = picture;
		this.status = status;
		this.adminId = adminId;
		this.reason = reason;
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

	public Boolean getType() {
		return this.type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public Integer getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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