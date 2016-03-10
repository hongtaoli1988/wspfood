package com.mainone.app.talk;

import javax.servlet.http.HttpServletRequest;

import com.mainone.util.DBRecord;
import com.mainone.util.PageBean;

/**
 * @author��ly
 * ��˵�����ݷÿͻ�����
 */
public interface VisitReviewApp {
	
	
	/**
	 * ��ѯ�ݷÿͻ�
	 * @param currentPage
	 * @param pageSize
	 * @param customerName
	 * @param startTime
	 * @param endTime
	 * @param flagState
	 * @return
	 * @throws Exception
	 */
	public PageBean searchVisit(int currentPage, int pageSize,String areaId,String comId,String cdId,
			String customerName, String startTime, String endTime,
			String flagState, HttpServletRequest request)throws Exception;

	public DBRecord getTalkArea(String areaID, String talkId)throws Exception;

	public boolean updateDpTalkArea(SfaTalkAreaInfo talkArea)throws Exception;
	

	
}
