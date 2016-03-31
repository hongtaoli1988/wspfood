package com.mainone.splian.model;

/**
 * AbstractGoodsLinks entity provides the base persistence definition of the
 * GoodsLinks entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractGoodsLinks implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer goodsId;
	private String title;
	private String url;
	private Integer addtime;
	private Integer updatetime;

	// Constructors

	/** default constructor */
	public AbstractGoodsLinks() {
	}

	/** minimal constructor */
	public AbstractGoodsLinks(Integer goodsId, String title, String url) {
		this.goodsId = goodsId;
		this.title = title;
		this.url = url;
	}

	/** full constructor */
	public AbstractGoodsLinks(Integer goodsId, String title, String url,
			Integer addtime, Integer updatetime) {
		this.goodsId = goodsId;
		this.title = title;
		this.url = url;
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

	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
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