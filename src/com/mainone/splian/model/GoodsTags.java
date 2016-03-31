package com.mainone.splian.model;

/**
 * GoodsTags entity. @author MyEclipse Persistence Tools
 */
public class GoodsTags extends AbstractGoodsTags implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public GoodsTags() {
	}

	/** minimal constructor */
	public GoodsTags(Integer goodsId) {
		super(goodsId);
	}

	/** full constructor */
	public GoodsTags(Integer goodsId, Integer tagsId) {
		super(goodsId, tagsId);
	}

}
