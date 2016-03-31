package com.mainone.splian.model;

/**
 * UserGoods entity. @author MyEclipse Persistence Tools
 */
public class UserGoods extends AbstractUserGoods implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public UserGoods() {
	}

	/** minimal constructor */
	public UserGoods(Integer uid) {
		super(uid);
	}

	/** full constructor */
	public UserGoods(Integer uid, Boolean role, Integer goodsId,
			Boolean status, Integer addtime, Integer updatetime) {
		super(uid, role, goodsId, status, addtime, updatetime);
	}

}
