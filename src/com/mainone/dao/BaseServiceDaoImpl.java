package com.mainone.dao;

import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import java.util.*;
import java.util.Date;
import java.sql.*;
import java.io.Serializable;

import com.mainone.util.DBRecord;
import com.mainone.util.Debuger;


/**
 * Created by IntelliJ IDEA. To change this template use File | Settings | File
 * Templates.
 */
public class BaseServiceDaoImpl extends HibernateDaoSupport implements
        IBaseServiceDao {
    // 获得同一个SessionFactory中的session
    public Session getBaseSession() throws Exception {
        return super.getSessionFactory().openSession();
    }
 
    // 通过JDBC调用存储过程，返回DBRecord类型的List
    public ArrayList getProcJdbc(String proc) throws Exception {
        String[] columnName;
        CallableStatement stmt = null;
        ResultSet Rs = null;
        Connection conn = null;
        Session session = this.getBaseSession();
        ArrayList ListData = new ArrayList();
        int columnCount = 0;
        try {
            conn = session.connection();
            stmt = conn.prepareCall(proc);
            Rs = stmt.executeQuery();

            ResultSetMetaData rsmd = Rs.getMetaData();
            int NumCols = rsmd.getColumnCount();
            columnName = new String[NumCols];
            for (int k = 0; k < NumCols; k++) {
                columnName[k] = rsmd.getColumnName(k + 1);
            }
            while (Rs.next()) {
                int i = 0;
                DBRecord record = new DBRecord();
                for (; i < NumCols; i++) {
                    String columnValue = Rs.getString(i + 1);
                    columnValue = columnValue == null ? "" : columnValue;
                    record.setProperty(columnName[i], columnValue);
                }
                ListData.add(record);
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                Rs.close();
                stmt.close();
                conn.close();
                session.close();
            } catch (java.sql.SQLException e) {
                Debuger
                        .println("Error Occur.When Close the DB ResultSet,Statement or Connection Object!");
                e.printStackTrace();
                session.close();
            }
        }
        return ListData;
    }

    // 根据传入的List，返回指定的数量的数据
    public Object[] getResult(List alist, int beginNo, int pageRowCount)
            throws Exception {
        Object[] returnObj = new Object[3];
        List ListData = new Vector();
        int columnCount = 0;
        int recordCount = 0;
        int Count = 0;

        // 总记录数
        recordCount = alist.size();
        if (beginNo > recordCount) {
            returnObj[0] = new Integer(0); // 总记录数
            returnObj[1] = new Integer(1); // 总页数
            returnObj[2] = ListData; // 当前页数据
            return returnObj;
        }
        // 总页数
        Count = recordCount / pageRowCount;
        // 该判断用于计算总页数
        if (recordCount % pageRowCount != 0) {
            Count++;
        }
        // 该判断用于计算最后一页的记录数
        if ((recordCount - (beginNo - 1)) < pageRowCount) {
            pageRowCount = recordCount - (Count - 1) * pageRowCount;
        }

        boolean flag = false;
        int j = 0;
        if (recordCount != 0) {
            for (int i = beginNo - 1; i < beginNo + pageRowCount - 1; i++) {
                ListData.add(j, alist.get(i));
                j++;
            }
        }
        alist.clear();

        // 该try块用于装载返回值
        try {
            returnObj[0] = new Integer(recordCount); // 总记录数
            returnObj[1] = new Integer(Count); // 总页数
            returnObj[2] = ListData; // 当前页数据
        } catch (Exception e) {
            Debuger.println("Error Occur!");
            e.printStackTrace();
        }
        return returnObj;
    }

    // 获得分页的对象的数量
    public Object[] getResult(List alist, List count, int beginNo,
            int pageRowCount) throws Exception {
        Object[] returnObj = new Object[3];
        List ListData = new Vector();
        int columnCount = 0;
        int recordCount = 0;
        int Count = 0;

        // 总记录数
        recordCount = new Integer(count.get(0).toString()).intValue();
        // recordCount = count.get(0);
        if (beginNo > recordCount) {
            returnObj[0] = new Integer(0); // 总记录数
            returnObj[1] = new Integer(1); // 总页数
            returnObj[2] = ListData; // 当前页数据
            return returnObj;
        }
        // 总页数
        Count = recordCount / pageRowCount;
        // 该判断用于计算总页数
        if (recordCount % pageRowCount != 0) {
            Count++;
        }
        // 该判断用于计算最后一页的记录数
        if ((recordCount - (beginNo - 1)) < pageRowCount) {
            pageRowCount = recordCount - (Count - 1) * pageRowCount;
        }

        boolean flag = false;
        int j = 0;
        if (recordCount != 0) {
            for (int i = 0; i <= pageRowCount - 1; i++) {
                ListData.add(j, alist.get(i));
                j++;
            }
        }
        alist.clear();

        // 该try块用于装载返回值
        try {
            returnObj[0] = new Integer(recordCount); // 总记录数
            returnObj[1] = new Integer(Count); // 总页数
            returnObj[2] = ListData; // 当前页数据
        } catch (Exception e) {
            Debuger.println("Error Occur!");
            e.printStackTrace();
        }
        return returnObj;
    }

    // 返回调用存储过程返回的数据
    public Object[] selectProcJdbcObj(String querySql, int beginNo,
            int pageRowCount) throws Exception {
        List list = getProcJdbc(querySql);
        return getResult(list, beginNo, pageRowCount);
    }

    // 调用配置文件中的存储过程，返回指定的数据数量
    public Object[] selectProcQueryObj(String querySql, int beginNo,
            int pageRowCount, Object[] obj) throws Exception {
        List list = findProcQuery(querySql, obj);
        return getResult(list, beginNo, pageRowCount);
    }

    // 调用配置文件中无参数的SQL，返回指定的数据数量
    public Object[] selectSqlNameQueryObj(String querySql, int beginNo,
            int pageRowCount) throws Exception {
        List list = findSqlNameQuery(querySql);
        return getResult(list, beginNo, pageRowCount);
    }

    // 得到系统日期

    public Date getSysDate() throws Exception {
        Session session = this.getBaseSession();
        List list = session.getNamedQuery("getDate").list();
        Date date = (Date) list.iterator().next();
        return date;
    }

    // 通过JDBC返回DBRecord类型的List
    public List getSqlJdbc(String proc) throws Exception {
        String[] columnName;
        Statement stmt = null;
        ResultSet Rs = null;
        Connection conn = null;
        List ListData = new ArrayList();
        int columnCount = 0;
        Session session = this.getBaseSession();
        try {
            conn = session.connection();
            stmt = conn.createStatement();
            Rs = stmt.executeQuery(proc);
            ResultSetMetaData rsmd = Rs.getMetaData();
            int NumCols = rsmd.getColumnCount();
            columnName = new String[NumCols];
            for (int k = 0; k < NumCols; k++) {
                columnName[k] = rsmd.getColumnName(k + 1);
            }
            while (Rs.next()) {
                int i = 0;
                DBRecord record = new DBRecord();
                for (; i < NumCols; i++) {
                    String columnValue = Rs.getString(i + 1);
                    columnValue = columnValue == null ? "" : columnValue;
                    record.setProperty(columnName[i], columnValue);
                }
                ListData.add(record);

            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                Rs.close();
                stmt.close();
                conn.close();
                session.close();
            } catch (java.sql.SQLException e) {
                Debuger
                        .println("Error Occur.When Close the DB ResultSet,Statement or Connection Object!");
                e.printStackTrace();
                session.close();
            }
        }
        return ListData;
    }

    // 通过JDBC得到一条记录的第一个字段
    public String selectOne(String sql) throws Exception {
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        String returnValue = null;
        Session session = this.getBaseSession();
        try {
            conn = session.connection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                returnValue = rs.getString(1);
            }
        } catch (Exception e) {
            Debuger.println("Error Occur.When Execute the Sql : " + sql);
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
                session.close();
            } catch (SQLException sqlexception) {
                Debuger
                        .println("Error Occur.When Close the DB ResultSet,Statement or Connection Object!");
                sqlexception.printStackTrace();
                session.close();
            }
        }
        return returnValue;
    }

    // 通过JDBC返回一条指定的DBRecord
    public DBRecord selectRow(String selects) throws Exception {
        String[] columnName;
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        DBRecord record = new DBRecord();
        int columnCount = 0;
        Session session = this.getBaseSession();
        try {
            conn = session.connection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(selects);
            ResultSetMetaData rsmd = rs.getMetaData();
            int NumCols = rsmd.getColumnCount();
            columnName = new String[NumCols];
            for (int k = 0; k < NumCols; k++) {
                columnName[k] = rsmd.getColumnName(k + 1);
            }
            if (rs.next()) {
                int i = 0;
                for (; i < NumCols; i++) {
                    String columnValue = rs.getString(i + 1);
                    columnValue = columnValue == null ? "" : columnValue;
                    record.setField(columnName[i], columnValue);
                }
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
                session.close();
            } catch (java.sql.SQLException e) {
                Debuger
                        .println("Error Occur.When Close the DB ResultSet,Statement or Connection Object!");
                e.printStackTrace();
                session.close();
            }
        }
        return record;
    }

    // 通过HQL得到查询结果
    public Iterator getHqlObject(String wherestring) throws Exception {
        Session session = this.getBaseSession();
        Query query = session.createQuery(wherestring);
        List it = query.list();
        session.close();
        return it.iterator();
    }

    // 得到指定的对象
    public Object getObject(Class clazz, Serializable id) throws Exception {
        return this.getHibernateTemplate().get(clazz, id);
    }

    // 保存一个对象
    public void saveObject(Object o) throws Exception {
        this.getHibernateTemplate().save(o);
    }

    // 保存或修改一个对象（session不正常关闭）
    public void saveObjectNew(Object o) throws Exception {
        Session session = this.getBaseSession();
        session.saveOrUpdate(o);
        session.flush();
        session.close();
    }

    // 修改一个对象
    public void updateObject(Object o) throws Exception {
        this.getHibernateTemplate().update(o);
    }

    // 删除一个对象
    public void removeObject(Object obj) throws Exception {
        this.getHibernateTemplate().delete(obj);
    }

    // 删除一组对象
    public void removeObjectAll(Collection collection) {
        this.getHibernateTemplate().deleteAll(collection);
    }

    // 删除一组对象
    public void removeObjects(Class clazz, Serializable[] id) throws Exception {
        if (id != null) {
            for (int i = 0; i < id.length; i++) {
                this.getHibernateTemplate().delete(getObject(clazz, id[i]));
            }
        }
    }

    // 调用配置文件中的存储过程
    public List findProcQuery(String str, Object[] obj) {
        try {
            Session session = this.getBaseSession();
            Query query = session.getNamedQuery(str);
            for (int i = 0; i < obj.length; i++) {
                query.setString(i, (String) obj[i]);
            }
            List list = query.list();
            session.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 调用配置文件中无参数的SQL
    public List findSqlNameQuery(String str) throws Exception {
        try {
            Session session = this.getBaseSession();
            List list = session.getNamedQuery(str).list();
            session.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List findHqlQuery(String str) throws Exception {
        try {
            Session session = this.getBaseSession();
            List list = session.createQuery(str).list();
            session.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String dataSource;

    public List getQueryData(String str, int end) throws Exception {
        // TODO Auto-generated method stub
        Session session = this.getBaseSession();
        List list = session.createQuery(str).setFirstResult(0).setMaxResults(
                end).list();
        session.close();
        return list;
    }

    public boolean deleteHqlObject(String hql) throws Exception {
        // TODO Auto-generated method stub
        Session session = null;
        Transaction tx = null;
        try {
            session = this.getBaseSession();
            tx = session.beginTransaction();
            session.createQuery(hql).executeUpdate();
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            session.close();
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateHqlObject(String hql) throws Exception {
        // TODO Auto-generated method stub
        Session session = null;
        Transaction tx = null;
        try {
            session = this.getBaseSession();
            tx = session.beginTransaction();
            session.createQuery(hql).executeUpdate();
            tx.commit();
            return true;
        } catch (Exception e) {
            tx.rollback();
            session.close();
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 实现处理SQL事务的单值操作 该操作处理SQL事务的单值操作，包括数据的增、删、改处理。
     * 
     * @parm updateSql SQL语句
     * @return 以boolean返回，判断操作是否成功
     */
    public boolean execUpdate(String updateSql) throws Exception {
        int iCode = 0;
        boolean resultState = true;
        Statement stmt = null;
        Connection conn = null;
        Session session = this.getBaseSession();
        try {
            conn = session.connection();
            stmt = conn.createStatement();
            iCode = stmt.executeUpdate(updateSql);
        } catch (java.sql.SQLException e) {
            resultState = false;
            Debuger.println("Error Occur.When Execute the Sql and Return Code "
                    + iCode + " : " + updateSql);
            e.printStackTrace();

        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (java.sql.SQLException e) {
                Debuger
                        .println("Error Occur.When Close the DB Statement or Connection Object!");
                e.printStackTrace();
            }
        }
        return resultState;
    }

    /**
     * 实现处理多条SQL事务 该操作实现处理多条SQL事务，包括数据的增、删、改处理。
     * 
     * @parm batchSql SQL语句数组
     * @return 以boolean返回，判断操作是否成功
     */
    public boolean execTransaction(String[] batchSql) throws Exception {
        Statement stmt = null;
        int[] iCode;
        boolean returnState = true;
        int i = 0;
        Connection conn = null;
        Session session = this.getBaseSession();
        try {
            conn = session.connection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            for (; i < batchSql.length; i++) {
                stmt.addBatch(batchSql[i]);
            }
            iCode = stmt.executeBatch();
            conn.commit();
        } catch (java.sql.SQLException e) {
            conn.rollback();
            e.printStackTrace();
            returnState = false;
        } finally {
            try {
                conn.setAutoCommit(true);
                stmt.close();
                conn.close();
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return returnState;
    }


    // 使用Hibernate的session方式返回Map对象
    public ArrayList<Map> findBySqlConditionParm(final String sql) {
       ArrayList<Map> array = (ArrayList<Map>) this.getHibernateTemplate().execute(new HibernateCallback(){
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Query query = session.createSQLQuery(sql);
//              System.out.println(sql);
                query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                query.list();
                return query.list();
            }
            
        });
        return array;
    }

    
    //使用Hibernate的session方式将返回的Object[]封装到一个DBRecord对象中
    public List<DBRecord> resultToDBRecord(final String sql) {
       final List<DBRecord> ListData = new ArrayList<DBRecord>();
       this.getHibernateTemplate().execute(new HibernateCallback(){
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                String[] columnName;
                Statement stmt = null;
                ResultSet Rs = null;
                Connection conn = null;
                int columnCount = 0;
                try {
//                  System.out.println(sql);
                    conn = session.connection();
                    stmt = conn.createStatement();
                    Rs = stmt.executeQuery(sql);
                    ResultSetMetaData rsmd = Rs.getMetaData();
                    int NumCols = rsmd.getColumnCount();
                    columnName = new String[NumCols];
                    for (int k = 0; k < NumCols; k++) {
                        columnName[k] = rsmd.getColumnName(k + 1);
                    }
                    while (Rs.next()) {
                        int i = 0;
                        DBRecord record = new DBRecord();
                        for (; i < NumCols; i++) {
                            String columnValue = Rs.getString(i + 1);
                            columnValue = columnValue == null ? "" : columnValue;
                            record.setProperty(columnName[i], columnValue);
                        }
                        ListData.add(record);

                    }
                } catch (java.sql.SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        Rs.close();
                        stmt.close();
                        conn.close();
                    } catch (java.sql.SQLException e) {
                        Debuger
                                .println("Error Occur.When Close the DB ResultSet,Statement or Connection Object!");
                        e.printStackTrace();
                    }
                }
                return ListData;
            }
        });
        return ListData;
    }

    public List<Object[]> findBySqlConditionParmtoObject(final String sql) {
        List<Object[]> finallist = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback(){

            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Query query = session.createSQLQuery(sql);
                return query.list();
            }
            
        });
        return finallist;
    }
    
    public int resultCount(final String sql){
       Integer i =  (Integer) this.getHibernateTemplate().execute(new HibernateCallback(){

            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Query query = session.createSQLQuery(sql);
                return query.uniqueResult();
            }
            
        });
        return i;
    }
    // 使用Hibernate的session方式返回Map对象  分页显示
    public List findBySqlConditionParmFenYe(int currentPage, int pageSize, final String sql) {
       final int startPage = (currentPage - 1) * pageSize;
       final int resultRows = pageSize;
       ArrayList array = (ArrayList) this.getHibernateTemplate().execute(new HibernateCallback(){
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Query query = session.createSQLQuery(sql);

                query.setFirstResult(startPage);
                query.setMaxResults(resultRows);
                query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                query.list();
                return query.list();
            }
        });
        return array;
    }
}
