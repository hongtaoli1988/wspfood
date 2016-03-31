package com.mainone.splian.model;

/**
 * Dynamic entity. @author MyEclipse Persistence Tools
 */
public class Dynamic extends AbstractDynamic implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Dynamic() {
	}

	/** minimal constructor */
	public Dynamic(Integer uid, Integer goodsId) {
		super(uid, goodsId);
	}

	/** full constructor */
	public Dynamic(Integer uid, Integer goodsId, Integer seriesId,
			Integer classifyId, String content, String file, Integer scan,
			Integer like, Integer comment, String address, String gps,
			Boolean status, Integer addtime, Integer updatetime) {
		super(uid, goodsId, seriesId, classifyId, content, file, scan, like,
				comment, address, gps, status, addtime, updatetime);
	}

}
