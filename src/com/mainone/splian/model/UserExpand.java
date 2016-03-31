package com.mainone.splian.model;

/**
 * UserExpand entity. @author MyEclipse Persistence Tools
 */
public class UserExpand extends AbstractUserExpand implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public UserExpand() {
	}

	/** minimal constructor */
	public UserExpand(Integer uid) {
		super(uid);
	}

	/** full constructor */
	public UserExpand(Integer uid, String nickname, String photo,
			Boolean gender, Integer birthday, Integer hometown,
			String telephone, String qq, Integer province, Integer city,
			Integer county, String address, Boolean validemail,
			Boolean validmobile, Integer addtime, Integer updatetime) {
		super(uid, nickname, photo, gender, birthday, hometown, telephone, qq,
				province, city, county, address, validemail, validmobile,
				addtime, updatetime);
	}

}
