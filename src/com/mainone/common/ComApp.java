package com.mainone.common;
/**
 * @since 2012-11-21
 * @author zhanglei
 *
 */
public interface ComApp {
	 /**
	   * @ desc��ѯ���й�˾�б�
	   * @return String �б��� <option></option>
	   */
	  public String getCompanyNameInfoList(String comId);
	  
	  /**
	   * @ desc��ѯ��˾�б�;ȥ������
	   * @return String �б��� <option></option>
	   */
	  public String getCompanyNameInfoList1(String comId);
	  
	  /**
	   * @ desc��ѯ�ֹ�˾
	   * @return String �б��� <option></option>
	   */
	  public String getComList(String com_ID);
	  public String getSubComId(String comId);
    /**
	 * �����û�ID���ù�˾�б�
	 * @param userId
	 * @param comId
	 * @return
	 */
	  public String getCompanyNameInfoListNew(String userId,String comId);
	  
	  /**
		 * �����û�ID���ù�˾�б�,ȥ������
		 * @param userId
		 * @param comId
		 * @return
		 */
	  public String getCompanyNameInfoListNew1(String userId,String comId);
		  
	/**
	 * �����û�ID���ù�˾�б�
	 * @param userId
	 * @param comId
	 * @return
	*/
	  public String getComListNew(String userId,String comId);
	  
	  /**
	   * ����comId��ȡ��˾����
	   * @param comId
	   * @return
	   */
	  public String getComName(String comId);
	  /**
		 * �����û�ID���ù�˾�б�
		 * @param userId
		 * @param comId
		 * @return
		 */
	public String getCompanyNameInfoListUseInServer(String userId,String comId);
		 
	/**
	 * ���ݸ�λID�ø�λ�б�
	 * @param posotionId
	 * @return
	 */
	public String getPositionList(String posotionId);
	
	/**
	 * ����Positionid��ȡ��λ����
	 */
	public String getPositionName(String Positionid);
		
		 
}
