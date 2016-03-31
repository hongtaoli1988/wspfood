package com.mainone.splian.model;

/**
 * StationLetter entity. @author MyEclipse Persistence Tools
 */
public class StationLetter extends AbstractStationLetter implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public StationLetter() {
	}

	/** minimal constructor */
	public StationLetter(Integer fromUid, Integer toUid) {
		super(fromUid, toUid);
	}

	/** full constructor */
	public StationLetter(Integer fromUid, Integer toUid, Integer pid,
			String content, String url, Boolean type, Boolean status,
			Integer sum, Integer addtime, Integer updatetime) {
		super(fromUid, toUid, pid, content, url, type, status, sum, addtime,
				updatetime);
	}

}
