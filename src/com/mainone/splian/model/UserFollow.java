package com.mainone.splian.model;

/**
 * UserFollow entity. @author MyEclipse Persistence Tools
 */
public class UserFollow extends AbstractUserFollow implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public UserFollow() {
	}

	/** minimal constructor */
	public UserFollow(Integer goodsId) {
		super(goodsId);
	}

	/** full constructor */
	public UserFollow(Integer uid, Integer goodsId, Integer addtime,
			Integer updatetime) {
		super(uid, goodsId, addtime, updatetime);
	}

}
