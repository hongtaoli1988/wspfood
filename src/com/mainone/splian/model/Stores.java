package com.mainone.splian.model;

/**
 * Stores entity. @author MyEclipse Persistence Tools
 */
public class Stores extends AbstractStores implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Stores() {
	}

	/** minimal constructor */
	public Stores(Integer uid, Boolean body, String title) {
		super(uid, body, title);
	}

	/** full constructor */
	public Stores(Integer uid, Boolean body, String title, String summary,
			String contacts, String company, Boolean type, String credentials,
			String apply, String numbering, String picture, String logo,
			String website, String address, String gps, String phone,
			String mobile, String email, String zipcode, Boolean status,
			Integer addtime, Integer updatetime) {
		super(uid, body, title, summary, contacts, company, type, credentials,
				apply, numbering, picture, logo, website, address, gps, phone,
				mobile, email, zipcode, status, addtime, updatetime);
	}

}
