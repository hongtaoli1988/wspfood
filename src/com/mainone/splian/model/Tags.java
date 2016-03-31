package com.mainone.splian.model;

/**
 * Tags entity. @author MyEclipse Persistence Tools
 */
public class Tags extends AbstractTags implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tags() {
	}

	/** minimal constructor */
	public Tags(String title) {
		super(title);
	}

	/** full constructor */
	public Tags(String title, Integer classifyId, Integer uid, Integer count,
			Integer heat) {
		super(title, classifyId, uid, count, heat);
	}

}
