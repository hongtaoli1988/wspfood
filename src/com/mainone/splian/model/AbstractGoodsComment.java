package com.mainone.splian.model;

/**
 * AbstractGoodsComment entity provides the base persistence definition of the
 * GoodsComment entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractGoodsComment implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer uid;
	private Integer goodsId;
	private Integer seriesId;
	private Integer dynamicId;
	private String content;
	private Short like;
	private Short reply;
	private Boolean top;
	private Boolean status;
	private Integer addtime;
	private Integer updatetime;

	// Constructors

	/** default constructor */
	public AbstractGoodsComment() {
	}

	/** full constructor */
	public AbstractGoodsComment(Integer uid, Integer goodsId, Integer seriesId,
			Integer dynamicId, String content, Short like, Short reply,
			Boolean top, Boolean status, Integer addtime, Integer updatetime) {
		this.uid = uid;
		this.goodsId = goodsId;
		this.seriesId = seriesId;
		this.dynamicId = dynamicId;
		this.content = content;
		this.like = like;
		this.reply = reply;
		this.top = top;
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

	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getSeriesId() {
		return this.seriesId;
	}

	public void setSeriesId(Integer seriesId) {
		this.seriesId = seriesId;
	}

	public Integer getDynamicId() {
		return this.dynamicId;
	}

	public void setDynamicId(Integer dynamicId) {
		this.dynamicId = dynamicId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Short getLike() {
		return this.like;
	}

	public void setLike(Short like) {
		this.like = like;
	}

	public Short getReply() {
		return this.reply;
	}

	public void setReply(Short reply) {
		this.reply = reply;
	}

	public Boolean getTop() {
		return this.top;
	}

	public void setTop(Boolean top) {
		this.top = top;
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