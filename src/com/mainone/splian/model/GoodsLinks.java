package com.mainone.splian.model;

/**
 * GoodsLinks entity. @author MyEclipse Persistence Tools
 */
public class GoodsLinks extends AbstractGoodsLinks implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public GoodsLinks() {
	}

	/** minimal constructor */
	public GoodsLinks(Integer goodsId, String title, String url) {
		super(goodsId, title, url);
	}

	/** full constructor */
	public GoodsLinks(Integer goodsId, String title, String url,
			Integer addtime, Integer updatetime) {
		super(goodsId, title, url, addtime, updatetime);
	}

}
