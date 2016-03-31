package com.mainone.splian.model;

/**
 * AbstractUser entity provides the base persistence definition of the User
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUser implements java.io.Serializable {

	// Fields

	private Integer uid;
	private String username;
	private String email;
	private String mobile;
	private String password;
	private String passwordResetToken;
	private String passwordHash;
	private String authKey;
	private String resettoken;
	private String hash;
	private Boolean status;
	private String accessToken;
	private Boolean role;
	private Integer addtime;
	private Integer updatetime;

	// Constructors

	/** default constructor */
	public AbstractUser() {
	}

	/** minimal constructor */
	public AbstractUser(String username, String email, String password,
			Integer addtime, Integer updatetime) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.addtime = addtime;
		this.updatetime = updatetime;
	}

	/** full constructor */
	public AbstractUser(String username, String email, String mobile,
			String password, String passwordResetToken, String passwordHash,
			String authKey, String resettoken, String hash, Boolean status,
			String accessToken, Boolean role, Integer addtime,
			Integer updatetime) {
		this.username = username;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.passwordResetToken = passwordResetToken;
		this.passwordHash = passwordHash;
		this.authKey = authKey;
		this.resettoken = resettoken;
		this.hash = hash;
		this.status = status;
		this.accessToken = accessToken;
		this.role = role;
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

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordResetToken() {
		return this.passwordResetToken;
	}

	public void setPasswordResetToken(String passwordResetToken) {
		this.passwordResetToken = passwordResetToken;
	}

	public String getPasswordHash() {
		return this.passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getAuthKey() {
		return this.authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public String getResettoken() {
		return this.resettoken;
	}

	public void setResettoken(String resettoken) {
		this.resettoken = resettoken;
	}

	public String getHash() {
		return this.hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Boolean getRole() {
		return this.role;
	}

	public void setRole(Boolean role) {
		this.role = role;
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