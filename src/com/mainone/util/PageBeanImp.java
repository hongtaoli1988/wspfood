package com.mainone.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import com.mainone.dao.IBaseServiceDao;

public class PageBeanImp implements PageBean {

    private IBaseServiceDao iBaseServiceDaoA;

    //	总行数
    int totalRowsAmount;
    //每页行数
    int pageSize = 3;
    //总页数
    int totalPages = 0;

    //当前页码
    int currentPage = 1;
    //下一页
    int nextPage;
    //上一页
    int previousPage;
    //是否有下一页
    boolean hasNext;
    //是否有上一页
    boolean hasPrevious;

    //当前页开始行
    int pageStartRow;

    //当前页结束行
    int pageEndRow;
    int totalRows;
    String hql1 = "";
    String hql2 = "";
    String sql1 = "";
    String sql2 = "";
    String formName = "";
/**
    public IBaseServiceDao getIBaseServiceDao() {
        return iBaseServiceDao;
    }
*/
    public void setIBaseServiceDaoA(IBaseServiceDao baseServiceDaoA) {
		this.iBaseServiceDaoA = baseServiceDaoA;
	}
    /* (non-Javadoc)
      * @see com.mainone.util.pagebean.back.PageBean#getCurrentPage()
      */
    public int getCurrentPage() {
        return currentPage;
    }

    /* (non-Javadoc)
      * @see com.mainone.util.pagebean.back.PageBean#isHasNext()
      */
    public boolean isHasNext() {
        return hasNext;
    }

    /* (non-Javadoc)
      * @see com.mainone.util.pagebean.back.PageBean#isHasPrevious()
      */
    public boolean isHasPrevious() {
        return hasPrevious;
    }

    /* (non-Javadoc)
      * @see com.mainone.util.pagebean.back.PageBean#getNextPage()
      */
    public int getNextPage() {
        return nextPage;
    }

    /* (non-Javadoc)
      * @see com.mainone.util.pagebean.back.PageBean#getPageSize()
      */
    public int getPageSize() {
        return pageSize;
    }

    /* (non-Javadoc)
      * @see com.mainone.util.pagebean.back.PageBean#getPreviousPage()
      */
    public int getPreviousPage() {
        return previousPage;
    }

    /* (non-Javadoc)
      * @see com.mainone.util.pagebean.back.PageBean#getTotalPages()
      */
    public int getTotalPages() {
        return totalPages;
    }

    /* (non-Javadoc)
      * @see com.mainone.util.pagebean.back.PageBean#getTotalRowsAmount()
      */
    public int getTotalRowsAmount() {
        return totalRowsAmount;
    }

    /* (non-Javadoc)
      * @see com.mainone.util.pagebean.back.PageBean#getPageStartRow()
      */
    public int getPageStartRow() {
        return pageStartRow;
    }

    /* (non-Javadoc)
      * @see com.mainone.util.pagebean.back.PageBean#getPageEndRow()
      */
    public int getPageEndRow() {
        return pageEndRow;
    }


    /**
     * hql分页
     */
    public void setPageBean(int currentPage, int pageSize, String hql1, String hql2) {

        this.pageSize = pageSize;
        this.hql1 = hql1;
        this.hql2 = hql2;
        int totalRows = this.getSumNumber(hql2);
        this.setPageController(totalRows, currentPage);
    }
    
    /**
     * sql分页
     */
    public void setPageBeanForSql(int currentPage, int pageSize, String sql1, String sql2) {

        this.pageSize = pageSize;
        this.sql1 = sql1;
        this.sql2 = sql2;
        int totalRows = this.getSumNumberForSql(sql2);
        this.setPageController(totalRows, currentPage);
    }


    /* (non-Javadoc)
      * @see com.mainone.util.pagebean.back.PageBean#setPageController(int, int)
      */
    public void setPageController(int totalRows, int currentPage) {

        setTotalRowsAmount(totalRows);
        setCurrentPage(currentPage);
    }

    /**
     * 设置总行数。
     *
     * @param i 总行数。
     */
    private void setTotalRowsAmount(int rows) {
        if (rows < 0) {
            this.totalRowsAmount = 0;
        }
        else {
            this.totalRowsAmount = rows;
            //新增修改，限制hql语句查询前100条
            /************begin*************/
//          if(rows>1000){
//        		this.totalRowsAmount = 1000;
//        	}
            /************end*************/
        }

        if (totalRowsAmount % pageSize == 0) {
            this.totalPages = totalRowsAmount / pageSize;
        }
        else {
            this.totalPages = totalRowsAmount / pageSize + 1;
        }

    }

    /**
     * 设置当前页数。
     *
     * @param i
     */
    private void setCurrentPage(int curPage) {
        if (curPage <= 1) {
            currentPage = 1;
        }
        else if (curPage > totalPages) {
            currentPage = totalPages;
        }
        else {
            currentPage = curPage;
        }

        if (currentPage == 1) {
            hasPrevious = false;
        }
        else {
            hasPrevious = true;
        }

        if (currentPage == totalPages) {
            hasNext = false;
        }
        else {
            hasNext = true;
        }

        nextPage = currentPage + 1;
        previousPage = currentPage - 1;

        //计算当前页开始行和结束行
        if (currentPage != totalPages) {

            pageStartRow = (currentPage - 1) * pageSize + 1;

        }
        else {
            pageStartRow = (currentPage - 1) * pageSize + 1;
        }

        //记录索引从0开始
        pageStartRow -= 1;
        pageEndRow = pageStartRow + pageSize;

    }
    
    /**
     * hql分页
     */
    public List getAllData() {

        List list = null;
        int firstResult = this.getPageStartRow();
        int resultNumber = this.getPageSize();
        Session session = null;
        try {
            session = iBaseServiceDaoA.getBaseSession();
            Query query = session.createQuery(hql1);
            query.setFirstResult(firstResult);
            query.setMaxResults(resultNumber);
            list = query.list();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return list;
    }


    /**
     * sql分页
     * @return
     */
    public List getAllDataForSql() {

        List<DBRecord> list = null;
        int firstResult = this.getPageStartRow();
        int resultNumber = this.getPageSize();
        Session session = null;
        try {
            session = iBaseServiceDaoA.getBaseSession();
            Query query = session.createSQLQuery(sql1);
            query.setFirstResult(firstResult);
            query.setMaxResults(resultNumber);
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            list = query.list();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return list;
    }

    
    /**
     * hql分页
     */
    public int getSumNumber(String hql2) {
        Session session = null;
        Long i = null;
        try {
            session = iBaseServiceDaoA.getBaseSession();
            Query query = session.createQuery(hql2);
            i = (Long) query.uniqueResult();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return i.intValue();
    }

    /**
     * sql分页
     * @param sql2
     * @return
     */
    public int getSumNumberForSql(String sql2) {
        Session session = null;
        Integer i = null;
        try {
            session = iBaseServiceDaoA.getBaseSession();
            Query query = session.createSQLQuery(sql2);
            i = (Integer) query.uniqueResult();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return i;
    }
    

    /* (non-Javadoc)
      * @see com.mainone.util.pagebean.back.PageBean#getToolsMenu(java.lang.String)
      */
    public String getToolsMenu(String formName) {
        StringBuffer str = new StringBuffer("");
        this.formName = formName;
        int next = 0, prev = 0;
        if (this.isHasPrevious()) {
            prev = this.getPreviousPage();
        }
        if (this.isHasNext()) {
            next = this.getNextPage();
        }
        if (this.getCurrentPage() > 1) {
            str
                    .append("<a href=\"javascript:document." + formName + ".currentPage.value=1;document." + formName + ".submit();\">首页</a>&nbsp;");
        }
        else {
            str.append("首页&nbsp;");
        }
        if (this.getCurrentPage() > 1) {
            str.append("<a href='javascript:document." + formName + ".currentPage.value="
                    + prev + ";document." + formName + ".submit();'>上页</a>&nbsp;");
        }
        else {
            str.append("上页&nbsp;");
        }
        if (this.getCurrentPage() < this.getTotalPages()) {

            str.append("<a href='javascript:document." + formName + ".currentPage.value="
                    + next + ";document." + formName + ".submit();'>下页</a>&nbsp;");
        }
        else {
            str.append("下页&nbsp;");
        }
        if (this.getTotalPages() > 1 && this.getCurrentPage() != this.getTotalPages()) {
            str.append("<a href='javascript:document." + formName + ".currentPage.value="
                    + this.getTotalPages()
                    + ";document." + formName + ".submit();'>末页</a>&nbsp;&nbsp;");
        }
        else {
            str.append("末页&nbsp;&nbsp;");
        }
        str.append(" 共" + this.getTotalRowsAmount() + "条记录");
        str
                .append("  每页<SELECT size=1 name=pageSize1 onchange='document." + formName + ".currentPage.value=1;document." + formName + ".pageSize.value=this.value;document." + formName + ".submit();'>");

        if (this.getPageSize() == 3) {
            str.append("<OPTION value=3 selected>3</OPTION>");
        }
        else {
            str.append("<OPTION value=3>3</OPTION>");
        }

        if (this.getPageSize() == 10) {
            str.append("<OPTION value=10 selected>10</OPTION>");
        }
        else {
            str.append("<OPTION value=10>10</OPTION>");
        }
        if (this.getPageSize() == 20) {
            str.append("<OPTION value=20 selected>20</OPTION>");
        }
        else {
             str.append("<OPTION value=20>20</OPTION>");
        }
        if (this.getPageSize() == 30) {
            str.append("<OPTION value=30 selected>30</OPTION>");
        }
        else {
            str.append("<OPTION value=30>30</OPTION>");
        }
        
        if (this.getPageSize() == 50) {
            str.append("<OPTION value=50 selected>50</OPTION>");
        }
        else {
            str.append("<OPTION value=50>50</OPTION>");
        }
        if (this.getPageSize() == 100) {
            str.append("<OPTION value=100 selected>100</OPTION>");
        }
        else {
            str.append("<OPTION value=100>100</OPTION>");
        }
        str.append("</SELECT>");
        str.append("条 分" + this.getTotalPages() + "页显示 转到");
        str
                .append("<SELECT size=1 name=Pagelist onchange='document." + formName + ".currentPage.value=this.value;document." + formName + ".submit();'>");
        for (int i = 1; i < this.getTotalPages() + 1; i++) {
            if (i == this.getCurrentPage()) {
                str.append("<OPTION value=" + i + " selected>" + i
                        + "</OPTION>");
            }
            else {
                str.append("<OPTION value=" + i + ">" + i + "</OPTION>");
            }
        }
        str.append("</SELECT>页");
        str.append("<INPUT type=hidden  value=" + this.getCurrentPage()
                + " name=\"currentPage\" > ");
        str.append("<INPUT type=hidden  value=" + this.getPageSize()
                + " name=\"pageSize\"> ");
        return str.toString();
    }
    
    //限制条数
    public String getToolsMenu2(String formName) {
        StringBuffer str = new StringBuffer("");
        this.formName = formName;
        int next = 0, prev = 0;
        if (this.isHasPrevious()) {
            prev = this.getPreviousPage();
        }
        if (this.isHasNext()) {
            next = this.getNextPage();
        }
        if (this.getCurrentPage() > 1) {
            str
                    .append("<a href=\"javascript:document." + formName + ".currentPage.value=1;document." + formName + ".submit();\">首页</a>&nbsp;");
        }
        else {
            str.append("首页&nbsp;");
        }
        if (this.getCurrentPage() > 1) {
            str.append("<a href='javascript:document." + formName + ".currentPage.value="
                    + prev + ";document." + formName + ".submit();'>上页</a>&nbsp;");
        }
        else {
            str.append("上页&nbsp;");
        }
        if (this.getCurrentPage() < this.getTotalPages()) {

            str.append("<a href='javascript:document." + formName + ".currentPage.value="
                    + next + ";document." + formName + ".submit();'>下页</a>&nbsp;");
        }
        else {
            str.append("下页&nbsp;");
        }
        if (this.getTotalPages() > 1 && this.getCurrentPage() != this.getTotalPages()) {
            str.append("<a href='javascript:document." + formName + ".currentPage.value="
                    + this.getTotalPages()
                    + ";document." + formName + ".submit();'>末页</a>&nbsp;&nbsp;");
        }
        else {
            str.append("末页&nbsp;&nbsp;");
        }
        str.append(" 共" + this.getTotalRowsAmount() + "条记录");
        str
                .append("  每页<SELECT size=1 name=pageSize onchange='document." + formName + ".currentPage.value=1;document." + formName + ".pageSize.value=this.value;document." + formName + ".submit();'>");

       

        if (this.getPageSize() == 10) {
            str.append("<OPTION value=10 selected>10</OPTION>");
        }
        else {
            str.append("<OPTION value=10>10</OPTION>");
        }
        if (this.getPageSize() == 20) {
            str.append("<OPTION value=20 selected>20</OPTION>");
        }
        else {
             str.append("<OPTION value=20>20</OPTION>");
        }
        str.append("</SELECT>");
        str.append("条 分" + this.getTotalPages() + "页显示 转到");
        str
                .append("<SELECT size=1 name=Pagelist onchange='document." + formName + ".currentPage.value=this.value;document." + formName + ".submit();'>");
        for (int i = 1; i < this.getTotalPages() + 1; i++) {
            if (i == this.getCurrentPage()) {
                str.append("<OPTION value=" + i + " selected>" + i
                        + "</OPTION>");
            }
            else {
                str.append("<OPTION value=" + i + ">" + i + "</OPTION>");
            }
        }
        str.append("</SELECT>页");
        str.append("<INPUT type=hidden  value=" + this.getCurrentPage()
                + " name=\"currentPage\" > ");
        str.append("<INPUT type=hidden  value=" + this.getPageSize()
                + " name=\"pageSize\"> ");
        return str.toString();
    }

    Object NewData; // 当前页面所有数据

    /* (non-Javadoc)
      * @see com.mainone.util.pagebean.back.PageBean#getPageStartRow1(int)
      */
    public int getPageStartRow1(int currentPage, int pageSize) {
        this.pageSize = pageSize;
        if (currentPage > 1) {
            pageStartRow = (currentPage - 1) * pageSize;
        } else {
            pageStartRow = 1;
        }
        if (currentPage != 1) {
            pageStartRow++;
        }
        return pageStartRow;
    }

    /* (non-Javadoc)
      * @see com.mainone.util.pagebean.back.PageBean#setNewData(java.lang.Object)
      */
    public void setNewData(Object data) {
        this.NewData = data;
    }
//		取得当前页面数据

    /* (non-Javadoc)
      * @see com.mainone.util.pagebean.back.PageBean#getNewData()
      */
    public Object getNewData() {
        return this.NewData;
    }

    /* (non-Javadoc)
      * @see com.mainone.util.pagebean.back.PageBean#setTotalPages(int)
      */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    /* (non-Javadoc)
      * @see com.mainone.util.pagebean.back.PageBean#setPageSize(int)
      */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    /* (non-Javadoc)
      * @see com.mainone.util.pagebean.back.PageBean#setPageBean(java.lang.Object[], int, int)
      */
    public void setPageBean(Object[] obj, int pageSize, int currentPage) {
        //取记录数
        setTotalRowsAmount(Integer.parseInt(obj[0].toString()));
        //总页数
        setTotalPages(Integer.parseInt(obj[1].toString()));
        //取当前页号
        setCurrentPage(currentPage);
        //每页记录数
        setPageSize(pageSize);
        //当前页的开始记录
        //setPageStartRow1(this.pageStartRow);
        //当前页的结束记录
        //setPageEndRow(this.pageEndRow);
        //存储当前数据
        setNewData(obj[2]);
    }


    /* (non-Javadoc)
          * @see com.mainone.util.pagebean.back.PageBean#getResult(java.util.ArrayList, int, int)
          */
    public Object[] getResult(ArrayList alist, int pageStartRow, int pageRowCount) throws Exception {
        Object[] returnObj = new Object[3];
        ArrayList ListData = new ArrayList();

        int totalRowsAmount = 0;
        int totalPages = 0;

        //总记录数
        totalRowsAmount = alist.size();
        if (pageStartRow > totalRowsAmount) {
            returnObj[0] = new Integer(0);  //总记录数
            returnObj[1] = new Integer(1);    //总页数
            returnObj[2] = ListData;                  //当前页数据
            return returnObj;
        }
        //总页数  
        totalPages = totalRowsAmount / pageRowCount;
        //该判断用于计算总页数         
        if (totalRowsAmount % pageRowCount != 0) {
            totalPages++;
        }
        //该判断用于计算最后一页的记录数
        if ((totalRowsAmount - (pageStartRow - 1)) < pageRowCount) {
            pageRowCount = totalRowsAmount - (totalPages - 1) * pageRowCount;
        }


        int j = 0;
        if (totalRowsAmount != 0) {
            for (int i = pageStartRow - 1; i < pageStartRow + pageRowCount - 1; i++) {

                ListData.add(j, alist.get(i));
                j++;
            }
        }
        //该try块用于装载返回值 
        try {
            returnObj[0] = new Integer(totalRowsAmount);  //总记录数
            returnObj[1] = new Integer(totalPages);    //总页数
            returnObj[2] = ListData;                  //当前页数据
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return returnObj;
    }

    /* (non-Javadoc)
      * @see com.mainone.util.pagebean.back.PageBean#getPageBean(int, int, java.util.ArrayList)
      */
    public void getPageBean(int currentPage, int pageSize, ArrayList arrayList) {
        //System.out.println("currentPage=" + currentPage);
        Object[] obj;
        try {
            obj = this.getResult(arrayList, this.getPageStartRow1(currentPage, pageSize), pageSize);
            this.setPageBean(obj, pageSize, currentPage);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


	
}


