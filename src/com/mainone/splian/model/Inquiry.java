package com.mainone.splian.model;

/**
 * Inquiry entity. @author MyEclipse Persistence Tools
 */
public class Inquiry extends AbstractInquiry implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Inquiry() {
	}

	/** full constructor */
	public Inquiry(Integer uid, Integer goodsId, Integer sellerUid,
			String title, String content, String email, String phone,
			Boolean status, Integer addtime, Integer updatetime) {
		super(uid, goodsId, sellerUid, title, content, email, phone, status,
				addtime, updatetime);
	}

}
