package com.mainone.splian.model;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */
public class Goods extends AbstractGoods implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Goods() {
	}

	/** minimal constructor */
	public Goods(Integer uid, String barcode, String title, String summary,
			String picture) {
		super(uid, barcode, title, summary, picture);
	}

	/** full constructor */
	public Goods(Integer uid, String barcode, String title, String summary,
			String soldarea, String detail, String picture, Double price,
			Double marketPrice, String unit, Integer weight, String brand,
			Integer classify, Integer inventory, Integer sales,
			Integer comments, Double score, Integer scan, Integer dynamic,
			Integer trace, Integer place, String address, String gps,
			String keyword, String description, Boolean status,
			Integer adminId, String reason, Integer addtime, Integer updatetime) {
		super(uid, barcode, title, summary, soldarea, detail, picture, price,
				marketPrice, unit, weight, brand, classify, inventory, sales,
				comments, score, scan, dynamic, trace, place, address, gps,
				keyword, description, status, adminId, reason, addtime,
				updatetime);
	}

}
