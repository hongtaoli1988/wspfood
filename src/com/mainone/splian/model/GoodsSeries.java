package com.mainone.splian.model;

/**
 * GoodsSeries entity. @author MyEclipse Persistence Tools
 */
public class GoodsSeries extends AbstractGoodsSeries implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public GoodsSeries() {
	}

	/** minimal constructor */
	public GoodsSeries(Integer goodsId) {
		super(goodsId);
	}

	/** full constructor */
	public GoodsSeries(Integer uid, Integer goodsId, String number,
			Integer starttime, Integer endtime, Integer addtime,
			Integer updatetime) {
		super(uid, goodsId, number, starttime, endtime, addtime, updatetime);
	}

}
