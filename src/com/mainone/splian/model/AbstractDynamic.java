package com.mainone.splian.model;

/**
 * AbstractDynamic entity provides the base persistence definition of the
 * Dynamic entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractDynamic implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer uid;
	private Integer goodsId;
	private Integer seriesId;
	private Integer classifyId;
	private String content;
	private String file;
	private Integer scan;
	private Integer like;
	private Integer comment;
	private String address;
	private String gps;
	private Boolean status;
	private Integer addtime;
	private Integer updatetime;

	// Constructors

	/** default constructor */
	public AbstractDynamic() {
	}

	/** minimal constructor */
	public AbstractDynamic(Integer uid, Integer goodsId) {
		this.uid = uid;
		this.goodsId = goodsId;
	}

	/** full constructor */
	public AbstractDynamic(Integer uid, Integer goodsId, Integer seriesId,
			Integer classifyId, String content, String file, Integer scan,
			Integer like, Integer comment, String address, String gps,
			Boolean status, Integer addtime, Integer updatetime) {
		this.uid = uid;
		this.goodsId = goodsId;
		this.seriesId = seriesId;
		this.classifyId = classifyId;
		this.content = content;
		this.file = file;
		this.scan = scan;
		this.like = like;
		this.comment = comment;
		this.address = address;
		this.gps = gps;
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

	public Integer getSeriesId() {
		return this.seriesId;
	}

	public void setSeriesId(Integer seriesId) {
		this.seriesId = seriesId;
	}

	public Integer getClassifyId() {
		return this.classifyId;
	}

	public void setClassifyId(Integer classifyId) {
		this.classifyId = classifyId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFile() {
		return this.file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Integer getScan() {
		return this.scan;
	}

	public void setScan(Integer scan) {
		this.scan = scan;
	}

	public Integer getLike() {
		return this.like;
	}

	public void setLike(Integer like) {
		this.like = like;
	}

	public Integer getComment() {
		return this.comment;
	}

	public void setComment(Integer comment) {
		this.comment = comment;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGps() {
		return this.gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
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