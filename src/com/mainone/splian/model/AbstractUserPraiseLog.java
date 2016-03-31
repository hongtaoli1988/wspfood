package com.mainone.splian.model;

/**
 * AbstractUserPraiseLog entity provides the base persistence definition of the
 * UserPraiseLog entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserPraiseLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer uid;
	private Integer gid;
	private Long did;
	private Boolean score;
	private Integer addtime;

	// Constructors

	/** default constructor */
	public AbstractUserPraiseLog() {
	}

	/** minimal constructor */
	public AbstractUserPraiseLog(Integer uid, Integer gid, Long did) {
		this.uid = uid;
		this.gid = gid;
		this.did = did;
	}

	/** full constructor */
	public AbstractUserPraiseLog(Integer uid, Integer gid, Long did,
			Boolean score, Integer addtime) {
		this.uid = uid;
		this.gid = gid;
		this.did = did;
		this.score = score;
		this.addtime = addtime;
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

	public Integer getGid() {
		return this.gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public Long getDid() {
		return this.did;
	}

	public void setDid(Long did) {
		this.did = did;
	}

	public Boolean getScore() {
		return this.score;
	}

	public void setScore(Boolean score) {
		this.score = score;
	}

	public Integer getAddtime() {
		return this.addtime;
	}

	public void setAddtime(Integer addtime) {
		this.addtime = addtime;
	}

}