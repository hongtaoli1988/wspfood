package com.mainone.splian.model;

/**
 * AbstractDynamicTags entity provides the base persistence definition of the
 * DynamicTags entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractDynamicTags implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer dynamicId;
	private Integer tagsId;

	// Constructors

	/** default constructor */
	public AbstractDynamicTags() {
	}

	/** full constructor */
	public AbstractDynamicTags(Integer dynamicId, Integer tagsId) {
		this.dynamicId = dynamicId;
		this.tagsId = tagsId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDynamicId() {
		return this.dynamicId;
	}

	public void setDynamicId(Integer dynamicId) {
		this.dynamicId = dynamicId;
	}

	public Integer getTagsId() {
		return this.tagsId;
	}

	public void setTagsId(Integer tagsId) {
		this.tagsId = tagsId;
	}

}