package com.mainone.splian.model;

/**
 * AbstractInquiry entity provides the base persistence definition of the
 * Inquiry entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractInquiry implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer uid;
	private Integer goodsId;
	private Integer sellerUid;
	private String title;
	private String content;
	private String email;
	private String phone;
	private Boolean status;
	private Integer addtime;
	private Integer updatetime;

	// Constructors

	/** default constructor */
	public AbstractInquiry() {
	}

	/** full constructor */
	public AbstractInquiry(Integer uid, Integer goodsId, Integer sellerUid,
			String title, String content, String email, String phone,
			Boolean status, Integer addtime, Integer updatetime) {
		this.uid = uid;
		this.goodsId = goodsId;
		this.sellerUid = sellerUid;
		this.title = title;
		this.content = content;
		this.email = email;
		this.phone = phone;
		this.status = status;
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

	public Integer getSellerUid() {
		return this.sellerUid;
	}

	public void setSellerUid(Integer sellerUid) {
		this.sellerUid = sellerUid;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
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