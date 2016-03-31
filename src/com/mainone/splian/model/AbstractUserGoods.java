package com.mainone.splian.model;

/**
 * AbstractUserGoods entity provides the base persistence definition of the
 * UserGoods entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserGoods implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer uid;
	private Boolean role;
	private Integer goodsId;
	private Boolean status;
	private Integer addtime;
	private Integer updatetime;

	// Constructors

	/** default constructor */
	public AbstractUserGoods() {
	}

	/** minimal constructor */
	public AbstractUserGoods(Integer uid) {
		this.uid = uid;
	}

	/** full constructor */
	public AbstractUserGoods(Integer uid, Boolean role, Integer goodsId,
			Boolean status, Integer addtime, Integer updatetime) {
		this.uid = uid;
		this.role = role;
		this.goodsId = goodsId;
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

	public Boolean getRole() {
		return this.role;
	}

	public void setRole(Boolean role) {
		this.role = role;
	}

	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
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