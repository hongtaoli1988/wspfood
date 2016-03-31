package com.mainone.splian.model;

/**
 * GoodsEvaluate entity. @author MyEclipse Persistence Tools
 */
public class GoodsEvaluate extends AbstractGoodsEvaluate implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public GoodsEvaluate() {
	}

	/** minimal constructor */
	public GoodsEvaluate(Integer goodsId) {
		super(goodsId);
	}

	/** full constructor */
	public GoodsEvaluate(Integer uid, Integer goodsId, Boolean real,
			Boolean fresh, Boolean satisfied, Boolean taste, Boolean package_,
			Integer addtime) {
		super(uid, goodsId, real, fresh, satisfied, taste, package_, addtime);
	}

}
