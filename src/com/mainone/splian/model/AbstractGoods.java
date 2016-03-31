package com.mainone.splian.model;

/**
 * AbstractGoods entity provides the base persistence definition of the Goods
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractGoods implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer uid;
	private String barcode;
	private String title;
	private String summary;
	private String soldarea;
	private String detail;
	private String picture;
	private Double price;
	private Double marketPrice;
	private String unit;
	private Integer weight;
	private String brand;
	private Integer classify;
	private Integer inventory;
	private Integer sales;
	private Integer comments;
	private Double score;
	private Integer scan;
	private Integer dynamic;
	private Integer trace;
	private Integer place;
	private String address;
	private String gps;
	private String keyword;
	private String description;
	private Boolean status;
	private Integer adminId;
	private String reason;
	private Integer addtime;
	private Integer updatetime;

	// Constructors

	/** default constructor */
	public AbstractGoods() {
	}

	/** minimal constructor */
	public AbstractGoods(Integer uid, String barcode, String title,
			String summary, String picture) {
		this.uid = uid;
		this.barcode = barcode;
		this.title = title;
		this.summary = summary;
		this.picture = picture;
	}

	/** full constructor */
	public AbstractGoods(Integer uid, String barcode, String title,
			String summary, String soldarea, String detail, String picture,
			Double price, Double marketPrice, String unit, Integer weight,
			String brand, Integer classify, Integer inventory, Integer sales,
			Integer comments, Double score, Integer scan, Integer dynamic,
			Integer trace, Integer place, String address, String gps,
			String keyword, String description, Boolean status,
			Integer adminId, String reason, Integer addtime, Integer updatetime) {
		this.uid = uid;
		this.barcode = barcode;
		this.title = title;
		this.summary = summary;
		this.soldarea = soldarea;
		this.detail = detail;
		this.picture = picture;
		this.price = price;
		this.marketPrice = marketPrice;
		this.unit = unit;
		this.weight = weight;
		this.brand = brand;
		this.classify = classify;
		this.inventory = inventory;
		this.sales = sales;
		this.comments = comments;
		this.score = score;
		this.scan = scan;
		this.dynamic = dynamic;
		this.trace = trace;
		this.place = place;
		this.address = address;
		this.gps = gps;
		this.keyword = keyword;
		this.description = description;
		this.status = status;
		this.adminId = adminId;
		this.reason = reason;
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

	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSoldarea() {
		return this.soldarea;
	}

	public void setSoldarea(String soldarea) {
		this.soldarea = soldarea;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getMarketPrice() {
		return this.marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getClassify() {
		return this.classify;
	}

	public void setClassify(Integer classify) {
		this.classify = classify;
	}

	public Integer getInventory() {
		return this.inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public Integer getSales() {
		return this.sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public Integer getComments() {
		return this.comments;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
	}

	public Double getScore() {
		return this.score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getScan() {
		return this.scan;
	}

	public void setScan(Integer scan) {
		this.scan = scan;
	}

	public Integer getDynamic() {
		return this.dynamic;
	}

	public void setDynamic(Integer dynamic) {
		this.dynamic = dynamic;
	}

	public Integer getTrace() {
		return this.trace;
	}

	public void setTrace(Integer trace) {
		this.trace = trace;
	}

	public Integer getPlace() {
		return this.place;
	}

	public void setPlace(Integer place) {
		this.place = place;
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

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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