package com.mainone.splian.model;

/**
 * Classify entity. @author MyEclipse Persistence Tools
 */
public class Classify extends AbstractClassify implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Classify() {
	}

	/** minimal constructor */
	public Classify(String title, Integer pid) {
		super(title, pid);
	}

	/** full constructor */
	public Classify(String title, Integer pid, String describe, Integer order) {
		super(title, pid, describe, order);
	}

}
