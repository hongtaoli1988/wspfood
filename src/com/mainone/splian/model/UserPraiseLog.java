package com.mainone.splian.model;

/**
 * UserPraiseLog entity. @author MyEclipse Persistence Tools
 */
public class UserPraiseLog extends AbstractUserPraiseLog implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public UserPraiseLog() {
	}

	/** minimal constructor */
	public UserPraiseLog(Integer uid, Integer gid, Long did) {
		super(uid, gid, did);
	}

	/** full constructor */
	public UserPraiseLog(Integer uid, Integer gid, Long did, Boolean score,
			Integer addtime) {
		super(uid, gid, did, score, addtime);
	}

}
