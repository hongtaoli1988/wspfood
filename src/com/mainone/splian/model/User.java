package com.mainone.splian.model;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
public class User extends AbstractUser implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String username, String email, String password,
			Integer addtime, Integer updatetime) {
		super(username, email, password, addtime, updatetime);
	}

	/** full constructor */
	public User(String username, String email, String mobile, String password,
			String passwordResetToken, String passwordHash, String authKey,
			String resettoken, String hash, Boolean status, String accessToken,
			Boolean role, Integer addtime, Integer updatetime) {
		super(username, email, mobile, password, passwordResetToken,
				passwordHash, authKey, resettoken, hash, status, accessToken,
				role, addtime, updatetime);
	}

}
