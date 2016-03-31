package com.mainone.splian.model;

/**
 * AbstractGoodsCommentReply entity provides the base persistence definition of
 * the GoodsCommentReply entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractGoodsCommentReply implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer uid;
	private Integer replyId;
	private Integer commentId;
	private String content;
	private Boolean type;
	private Short like;
	private Boolean status;
	private Integer addtime;
	private Integer updatetime;

	// Constructors

	/** default constructor */
	public AbstractGoodsCommentReply() {
	}

	/** full constructor */
	public AbstractGoodsCommentReply(Integer uid, Integer replyId,
			Integer commentId, String content, Boolean type, Short like,
			Boolean status, Integer addtime, Integer updatetime) {
		this.uid = uid;
		this.replyId = replyId;
		this.commentId = commentId;
		this.content = content;
		this.type = type;
		this.like = like;
		this.status = status;
		this.addtime = addtime;
		this.updatetime = updatetime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getReplyId() {
		return this.replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}

	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getType() {
		return this.type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	public Short getLike() {
		return this.like;
	}

	public void setLike(Short like) {
		this.like = like;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getAddtime() {
		return this.addtime;
	}

	public void setAddtime(Integer addtime) {
		this.addtime = addtime;
	}

	public Integer getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Integer updatetime) {
		this.updatetime = updatetime;
	}

}