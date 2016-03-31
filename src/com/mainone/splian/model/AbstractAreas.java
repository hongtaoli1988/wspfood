package com.mainone.splian.model;

/**
 * AbstractAreas entity provides the base persistence definition of the Areas
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAreas implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer pid;
	private String name;
	private Integer sort;

	// Constructors

	/** default constructor */
	public AbstractAreas() {
	}

	/** full constructor */
	public AbstractAreas(Integer pid, String name, Integer sort) {
		this.pid = pid;
		this.name = name;
		this.sort = sort;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}