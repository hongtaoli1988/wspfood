package com.mainone.splian.model;

/**
 * AbstractUserExpand entity provides the base persistence definition of the
 * UserExpand entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserExpand implements java.io.Serializable {

	// Fields

	private Integer uid;
	private String nickname;
	private String photo;
	private Boolean gender;
	private Integer birthday;
	private Integer hometown;
	private String telephone;
	private String qq;
	private Integer province;
	private Integer city;
	private Integer county;
	private String address;
	private Boolean validemail;
	private Boolean validmobile;
	private Integer addtime;
	private Integer updatetime;

	// Constructors

	/** default constructor */
	public AbstractUserExpand() {
	}

	/** minimal constructor */
	public AbstractUserExpand(Integer uid) {
		this.uid = uid;
	}

	/** full constructor */
	public AbstractUserExpand(Integer uid, String nickname, String photo,
			Boolean gender, Integer birthday, Integer hometown,
			String telephone, String qq, Integer province, Integer city,
			Integer county, String address, Boolean validemail,
			Boolean validmobile, Integer addtime, Integer updatetime) {
		this.uid = uid;
		this.nickname = nickname;
		this.photo = photo;
		this.gender = gender;
		this.birthday = birthday;
		this.hometown = hometown;
		this.telephone = telephone;
		this.qq = qq;
		this.province = province;
		this.city = city;
		this.county = county;
		this.address = address;
		this.validemail = validemail;
		this.validmobile = validmobile;
		this.addtime = addtime;
		this.updatetime = updatetime;
	}

	// Property accessors

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Boolean getGender() {
		return this.gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Integer getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Integer birthday) {
		this.birthday = birthday;
	}

	public Integer getHometown() {
		return this.hometown;
	}

	public void setHometown(Integer hometown) {
		this.hometown = hometown;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Integer getProvince() {
		return this.province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return this.city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getCounty() {
		return this.county;
	}

	public void setCounty(Integer county) {
		this.county = county;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getValidemail() {
		return this.validemail;
	}

	public void setValidemail(Boolean validemail) {
		this.validemail = validemail;
	}

	public Boolean getValidmobile() {
		return this.validmobile;
	}

	public void setValidmobile(Boolean validmobile) {
		this.validmobile = validmobile;
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