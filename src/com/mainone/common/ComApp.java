package com.mainone.common;
/**
 * @since 2012-11-21
 * @author zhanglei
 *
 */
public interface ComApp {
	 /**
	   * @ desc查询所有公司列表
	   * @return String 列表项 <option></option>
	   */
	  public String getCompanyNameInfoList(String comId);
	  
	  /**
	   * @ desc查询公司列表;去掉所有
	   * @return String 列表项 <option></option>
	   */
	  public String getCompanyNameInfoList1(String comId);
	  
	  /**
	   * @ desc查询分公司
	   * @return String 列表项 <option></option>
	   */
	  public String getComList(String com_ID);
	  public String getSubComId(String comId);
    /**
	 * 根据用户ID来得公司列表
	 * @param userId
	 * @param comId
	 * @return
	 */
	  public String getCompanyNameInfoListNew(String userId,String comId);
	  
	  /**
		 * 根据用户ID来得公司列表,去掉所有
		 * @param userId
		 * @param comId
		 * @return
		 */
	  public String getCompanyNameInfoListNew1(String userId,String comId);
		  
	/**
	 * 根据用户ID来得公司列表
	 * @param userId
	 * @param comId
	 * @return
	*/
	  public String getComListNew(String userId,String comId);
	  
	  /**
	   * 根据comId获取公司名称
	   * @param comId
	   * @return
	   */
	  public String getComName(String comId);
	  /**
		 * 根据用户ID来得公司列表
		 * @param userId
		 * @param comId
		 * @return
		 */
	public String getCompanyNameInfoListUseInServer(String userId,String comId);
		 
	/**
	 * 根据岗位ID得岗位列表
	 * @param posotionId
	 * @return
	 */
	public String getPositionList(String posotionId);
	
	/**
	 * 根据Positionid获取岗位名称
	 */
	public String getPositionName(String Positionid);
		
		 
}
