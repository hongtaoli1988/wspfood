package com.mainone.splian.model;

/**
 * UserCertification entity. @author MyEclipse Persistence Tools
 */
public class UserCertification extends AbstractUserCertification implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public UserCertification() {
	}

	/** minimal constructor */
	public UserCertification(Integer uid, Boolean type, Integer category) {
		super(uid, type, category);
	}

	/** full constructor */
	public UserCertification(Integer uid, Boolean type, String name,
			Integer category, String number, String picture, Boolean status,
			Integer adminId, String reason, Integer addtime, Integer updatetime) {
		super(uid, type, name, category, number, picture, status, adminId,
				reason, addtime, updatetime);
	}

}
