package com.mainone.util;

import java.util.ArrayList;
import java.util.List;

/**
 * �������� 2007-5-17
 * ���� kyy
 * ��������  
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
	 * ����������
	 * firstResult:��ʼ��
	 * resultNumber��������ļ�¼��
	 */
	public abstract List getAllData();
	
	public abstract List getAllDataForSql();
	

	/**
	 * ������
	 */
	public abstract int getSumNumber(String hql2);
	
	public abstract int getSumNumberForSql(String sql2);
	
	/**
	 * ҳ����ʾ�õĲ˵�
	 * @param formName :ҳ����ʾ��FORMNAME
	 * 
	 */
	public abstract String getToolsMenu(String formName);

	/**
	 * ҳ����ʾ�õĲ˵���for webservices��
	 * @param formName :ҳ����ʾ��FORMNAME	
	 * 
	 */
	public abstract String getToolsMenu2(String formName);
	
	public abstract int getPageStartRow1(int currentPage, int pageSize);

	public abstract void setNewData(Object data);

	//		ȡ�õ�ǰҳ������
	public abstract Object getNewData();

	public abstract void setTotalPages(int totalPages);

	public abstract void setPageSize(int pageSize);

	/**
	 * ����BEAN����(for hibernate)
	 */
	public abstract void setPageBean(Object[] obj, int pageSize, int currentPage);

	public abstract Object[] getResult(ArrayList alist, int pageStartRow,
			int pageRowCount) throws Exception;

	/**
	 * ��ArrayList��װ��PageBean
	 */
	public abstract void getPageBean(int currentPage, int pageSize,
			ArrayList arrayList);

}