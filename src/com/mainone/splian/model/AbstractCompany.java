package com.mainone.splian.model;

/**
 * AbstractCompany entity provides the base persistence definition of the
 * Company entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCompany implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer uid;
	private String company;
	private String logo;
	private String introduction;
	private String website;
	private String address;
	private String gps;
	private String code;
	private String phone;
	private String mobile;
	private String email;
	private Integer addtime;
	private Integer updatetime;

	// Constructors

	/** default constructor */
	public AbstractCompany() {
	}

	/** minimal constructor */
	public AbstractCompany(Integer uid) {
		this.uid = uid;
	}

	/** full constructor */
	public AbstractCompany(Integer uid, String company, String logo,
			String introduction, String website, String address, String gps,
			String code, String phone, String mobile, String email,
			Integer addtime, Integer updatetime) {
		this.uid = uid;
		this.company = company;
		this.logo = logo;
		this.introduction = introduction;
		this.website = website;
		this.address = address;
		this.gps = gps;
		this.code = code;
		this.phone = phone;
		this.mobile = mobile;
		this.email = email;
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

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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