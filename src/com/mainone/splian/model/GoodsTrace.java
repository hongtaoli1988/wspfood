package com.mainone.splian.model;

/**
 * GoodsTrace entity. @author MyEclipse Persistence Tools
 */
public class GoodsTrace extends AbstractGoodsTrace implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public GoodsTrace() {
	}

	/** full constructor */
	public GoodsTrace(Integer uid, Integer goodsId, Integer seriesId,
			String gps, Integer addtime) {
		super(uid, goodsId, seriesId, gps, addtime);
	}

}
