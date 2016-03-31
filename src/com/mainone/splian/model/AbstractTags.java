package com.mainone.splian.model;

/**
 * AbstractTags entity provides the base persistence definition of the Tags
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTags implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private Integer classifyId;
	private Integer uid;
	private Integer count;
	private Integer heat;

	// Constructors

	/** default constructor */
	public AbstractTags() {
	}

	/** minimal constructor */
	public AbstractTags(String title) {
		this.title = title;
	}

	/** full constructor */
	public AbstractTags(String title, Integer classifyId, Integer uid,
			Integer count, Integer heat) {
		this.title = title;
		this.classifyId = classifyId;
		this.uid = uid;
		this.count = count;
		this.heat = heat;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getClassifyId() {
		return this.classifyId;
	}

	public void setClassifyId(Integer classifyId) {
		this.classifyId = classifyId;
	}

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getHeat() {
		return this.heat;
	}

	public void setHeat(Integer heat) {
		this.heat = heat;
	}

}