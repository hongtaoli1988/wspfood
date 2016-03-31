package com.mainone.splian.model;

/**
 * Company entity. @author MyEclipse Persistence Tools
 */
public class Company extends AbstractCompany implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Company() {
	}

	/** minimal constructor */
	public Company(Integer uid) {
		super(uid);
	}

	/** full constructor */
	public Company(Integer uid, String company, String logo,
			String introduction, String website, String address, String gps,
			String code, String phone, String mobile, String email,
			Integer addtime, Integer updatetime) {
		super(uid, company, logo, introduction, website, address, gps, code,
				phone, mobile, email, addtime, updatetime);
	}

}
