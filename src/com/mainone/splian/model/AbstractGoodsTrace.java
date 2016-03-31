package com.mainone.splian.model;

/**
 * AbstractGoodsTrace entity provides the base persistence definition of the
 * GoodsTrace entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractGoodsTrace implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer uid;
	private Integer goodsId;
	private Integer seriesId;
	private String gps;
	private Integer addtime;

	// Constructors

	/** default constructor */
	public AbstractGoodsTrace() {
	}

	/** full constructor */
	public AbstractGoodsTrace(Integer uid, Integer goodsId, Integer seriesId,
			String gps, Integer addtime) {
		this.uid = uid;
		this.goodsId = goodsId;
		this.seriesId = seriesId;
		this.gps = gps;
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

	public Integer getSeriesId() {
		return this.seriesId;
	}

	public void setSeriesId(Integer seriesId) {
		this.seriesId = seriesId;
	}

	public String getGps() {
		return this.gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}

	public Integer getAddtime() {
		return this.addtime;
	}

	public void setAddtime(Integer addtime) {
		this.addtime = addtime;
	}

}