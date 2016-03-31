package com.mainone.splian.model;

/**
 * AbstractGoodsEvaluate entity provides the base persistence definition of the
 * GoodsEvaluate entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractGoodsEvaluate implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer uid;
	private Integer goodsId;
	private Boolean real;
	private Boolean fresh;
	private Boolean satisfied;
	private Boolean taste;
	private Boolean package_;
	private Integer addtime;

	// Constructors

	/** default constructor */
	public AbstractGoodsEvaluate() {
	}

	/** minimal constructor */
	public AbstractGoodsEvaluate(Integer goodsId) {
		this.goodsId = goodsId;
	}

	/** full constructor */
	public AbstractGoodsEvaluate(Integer uid, Integer goodsId, Boolean real,
			Boolean fresh, Boolean satisfied, Boolean taste, Boolean package_,
			Integer addtime) {
		this.uid = uid;
		this.goodsId = goodsId;
		this.real = real;
		this.fresh = fresh;
		this.satisfied = satisfied;
		this.taste = taste;
		this.package_ = package_;
		this.addtime = addtime;
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

	public Boolean getReal() {
		return this.real;
	}

	public void setReal(Boolean real) {
		this.real = real;
	}

	public Boolean getFresh() {
		return this.fresh;
	}

	public void setFresh(Boolean fresh) {
		this.fresh = fresh;
	}

	public Boolean getSatisfied() {
		return this.satisfied;
	}

	public void setSatisfied(Boolean satisfied) {
		this.satisfied = satisfied;
	}

	public Boolean getTaste() {
		return this.taste;
	}

	public void setTaste(Boolean taste) {
		this.taste = taste;
	}

	public Boolean getPackage_() {
		return this.package_;
	}

	public void setPackage_(Boolean package_) {
		this.package_ = package_;
	}

	public Integer getAddtime() {
		return this.addtime;
	}

	public void setAddtime(Integer addtime) {
		this.addtime = addtime;
	}

}