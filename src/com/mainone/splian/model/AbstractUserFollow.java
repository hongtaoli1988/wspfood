package com.mainone.splian.model;

/**
 * AbstractUserFollow entity provides the base persistence definition of the
 * UserFollow entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserFollow implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer uid;
	private Integer goodsId;
	private Integer addtime;
	private Integer updatetime;

	// Constructors

	/** default constructor */
	public AbstractUserFollow() {
	}

	/** minimal constructor */
	public AbstractUserFollow(Integer goodsId) {
		this.goodsId = goodsId;
	}

	/** full constructor */
	public AbstractUserFollow(Integer uid, Integer goodsId, Integer addtime,
			Integer updatetime) {
		this.uid = uid;
		this.goodsId = goodsId;
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