package com.mainone.splian.model;

/**
 * AbstractGoodsSeries entity provides the base persistence definition of the
 * GoodsSeries entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractGoodsSeries implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer uid;
	private Integer goodsId;
	private String number;
	private Integer starttime;
	private Integer endtime;
	private Integer addtime;
	private Integer updatetime;

	// Constructors

	/** default constructor */
	public AbstractGoodsSeries() {
	}

	/** minimal constructor */
	public AbstractGoodsSeries(Integer goodsId) {
		this.goodsId = goodsId;
	}

	/** full constructor */
	public AbstractGoodsSeries(Integer uid, Integer goodsId, String number,
			Integer starttime, Integer endtime, Integer addtime,
			Integer updatetime) {
		this.uid = uid;
		this.goodsId = goodsId;
		this.number = number;
		this.starttime = starttime;
		this.endtime = endtime;
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

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Integer starttime) {
		this.starttime = starttime;
	}

	public Integer getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Integer endtime) {
		this.endtime = endtime;
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