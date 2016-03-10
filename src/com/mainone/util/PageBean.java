package com.mainone.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建日期 2007-5-17
 * 作者 kyy
 * 功能描述  
 */
public interface PageBean {
	public abstract int getCurrentPage();

	public abstract boolean isHasNext();

	public abstract boolean isHasPrevious();

	public abstract int getNextPage();

	public abstract int getPageSize();

	public abstract int getPreviousPage();

	public abstract int getTotalPages();

	public abstract int getTotalRowsAmount();

	public abstract int getPageStartRow();

	public abstract int getPageEndRow();

	public abstract void setPageBean(int currentPage, int pageSize, String hql1, String hql2);
	
	public abstract void setPageBeanForSql(int currentPage, int pageSize, String sql1, String sql2);
	

	public abstract void setPageController(int totalRows, int currentPage);

	/**
	 * 得所有数据
	 * firstResult:起始行
	 * resultNumber：共查出的记录数
	 */
	public abstract List getAllData();
	
	public abstract List getAllDataForSql();
	

	/**
	 * 得总数
	 */
	public abstract int getSumNumber(String hql2);
	
	public abstract int getSumNumberForSql(String sql2);
	
	/**
	 * 页面显示用的菜单
	 * @param formName :页面显示的FORMNAME
	 * 
	 */
	public abstract String getToolsMenu(String formName);

	/**
	 * 页面显示用的菜单（for webservices）
	 * @param formName :页面显示的FORMNAME	
	 * 
	 */
	public abstract String getToolsMenu2(String formName);
	
	public abstract int getPageStartRow1(int currentPage, int pageSize);

	public abstract void setNewData(Object data);

	//		取得当前页面数据
	public abstract Object getNewData();

	public abstract void setTotalPages(int totalPages);

	public abstract void setPageSize(int pageSize);

	/**
	 * 设置BEAN参数(for hibernate)
	 */
	public abstract void setPageBean(Object[] obj, int pageSize, int currentPage);

	public abstract Object[] getResult(ArrayList alist, int pageStartRow,
			int pageRowCount) throws Exception;

	/**
	 * 将ArrayList封装成PageBean
	 */
	public abstract void getPageBean(int currentPage, int pageSize,
			ArrayList arrayList);

}