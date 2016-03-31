package com.mainone.splian.model;

/**
 * GoodsComment entity. @author MyEclipse Persistence Tools
 */
public class GoodsComment extends AbstractGoodsComment implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public GoodsComment() {
	}

	/** full constructor */
	public GoodsComment(Integer uid, Integer goodsId, Integer seriesId,
			Integer dynamicId, String content, Short like, Short reply,
			Boolean top, Boolean status, Integer addtime, Integer updatetime) {
		super(uid, goodsId, seriesId, dynamicId, content, like, reply, top,
				status, addtime, updatetime);
	}

}
