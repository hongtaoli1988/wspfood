package com.mainone.splian.model;

/**
 * AbstractClassify entity provides the base persistence definition of the
 * Classify entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractClassify implements java.io.Serializable {

	// Fields

	private Integer id;
	private String title;
	private Integer pid;
	private String describe;
	private Integer order;

	// Constructors

	/** default constructor */
	public AbstractClassify() {
	}

	/** minimal constructor */
	public AbstractClassify(String title, Integer pid) {
		this.title = title;
		this.pid = pid;
	}

	/** full constructor */
	public AbstractClassify(String title, Integer pid, String describe,
			Integer order) {
		this.title = title;
		this.pid = pid;
		this.describe = describe;
		this.order = order;
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

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getDescribe() {
		return this.describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Integer getOrder() {
		return this.order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

}