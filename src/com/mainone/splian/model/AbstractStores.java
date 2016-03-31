package com.mainone.splian.model;

/**
 * AbstractStores entity provides the base persistence definition of the Stores
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractStores implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer uid;
	private Boolean body;
	private String title;
	private String summary;
	private String contacts;
	private String company;
	private Boolean type;
	private String credentials;
	private String apply;
	private String numbering;
	private String picture;
	private String logo;
	private String website;
	private String address;
	private String gps;
	private String phone;
	private String mobile;
	private String email;
	private String zipcode;
	private Boolean status;
	private Integer addtime;
	private Integer updatetime;

	// Constructors

	/** default constructor */
	public AbstractStores() {
	}

	/** minimal constructor */
	public AbstractStores(Integer uid, Boolean body, String title) {
		this.uid = uid;
		this.body = body;
		this.title = title;
	}

	/** full constructor */
	public AbstractStores(Integer uid, Boolean body, String title,
			String summary, String contacts, String company, Boolean type,
			String credentials, String apply, String numbering, String picture,
			String logo, String website, String address, String gps,
			String phone, String mobile, String email, String zipcode,
			Boolean status, Integer addtime, Integer updatetime) {
		this.uid = uid;
		this.body = body;
		this.title = title;
		this.summary = summary;
		this.contacts = contacts;
		this.company = company;
		this.type = type;
		this.credentials = credentials;
		this.apply = apply;
		this.numbering = numbering;
		this.picture = picture;
		this.logo = logo;
		this.website = website;
		this.address = address;
		this.gps = gps;
		this.phone = phone;
		this.mobile = mobile;
		this.email = email;
		this.zipcode = zipcode;
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

	public Boolean getBody() {
		return this.body;
	}

	public void setBody(Boolean body) {
		this.body = body;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContacts() {
		return this.contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Boolean getType() {
		return this.type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	public String getCredentials() {
		return this.credentials;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	public String getApply() {
		return this.apply;
	}

	public void setApply(String apply) {
		this.apply = apply;
	}

	public String getNumbering() {
		return this.numbering;
	}

	public void setNumbering(String numbering) {
		this.numbering = numbering;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGps() {
		return this.gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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