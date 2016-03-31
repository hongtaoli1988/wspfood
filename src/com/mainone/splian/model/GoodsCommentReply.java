package com.mainone.splian.model;

/**
 * GoodsCommentReply entity. @author MyEclipse Persistence Tools
 */
public class GoodsCommentReply extends AbstractGoodsCommentReply implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public GoodsCommentReply() {
	}

	/** full constructor */
	public GoodsCommentReply(Integer uid, Integer replyId, Integer commentId,
			String content, Boolean type, Short like, Boolean status,
			Integer addtime, Integer updatetime) {
		super(uid, replyId, commentId, content, type, like, status, addtime,
				updatetime);
	}

}
