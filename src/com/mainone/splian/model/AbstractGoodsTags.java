package com.mainone.splian.model;

/**
 * AbstractGoodsTags entity provides the base persistence definition of the
 * GoodsTags entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractGoodsTags implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer goodsId;
	private Integer tagsId;

	// Constructors

	/** default constructor */
	public AbstractGoodsTags() {
	}

	/** minimal constructor */
	public AbstractGoodsTags(Integer goodsId) {
		this.goodsId = goodsId;
	}

	/** full constructor */
	public AbstractGoodsTags(Integer goodsId, Integer tagsId) {
		this.goodsId = goodsId;
		this.tagsId = tagsId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getTagsId() {
		return this.tagsId;
	}

	public void setTagsId(Integer tagsId) {
		this.tagsId = tagsId;
	}

}