package com.mainone.splian.model;

/**
 * UserRole entity. @author MyEclipse Persistence Tools
 */
public class UserRole extends AbstractUserRole implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public UserRole() {
	}

	/** minimal constructor */
	public UserRole(Integer uid) {
		super(uid);
	}

	/** full constructor */
	public UserRole(Integer uid, Boolean role, Boolean type, String number,
			String content, String picture, Boolean status, Integer addtime,
			Integer updatetime) {
		super(uid, role, type, number, content, picture, status, addtime,
				updatetime);
	}

}
