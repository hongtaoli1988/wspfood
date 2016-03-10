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
    // ���ͬһ��SessionFactory�е�session
    public Session getBaseSession() throws Exception {
        return super.getSessionFactory().openSession();
    }
 
    // ͨ��JDBC���ô洢���̣�����DBRecord���͵�List
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

    // ���ݴ����List������ָ��������������
    public Object[] getResult(List alist, int beginNo, int pageRowCount)
            throws Exception {
        Object[] returnObj = new Object[3];
        List ListData = new Vector();
        int columnCount = 0;
        int recordCount = 0;
        int Count = 0;

        // �ܼ�¼��
        recordCount = alist.size();
        if (beginNo > recordCount) {
            returnObj[0] = new Integer(0); // �ܼ�¼��
            returnObj[1] = new Integer(1); // ��ҳ��
            returnObj[2] = ListData; // ��ǰҳ����
            return returnObj;
        }
        // ��ҳ��
        Count = recordCount / pageRowCount;
        // ���ж����ڼ�����ҳ��
        if (recordCount % pageRowCount != 0) {
            Count++;
        }
        // ���ж����ڼ������һҳ�ļ�¼��
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

        // ��try������װ�ط���ֵ
        try {
            returnObj[0] = new Integer(recordCount); // �ܼ�¼��
            returnObj[1] = new Integer(Count); // ��ҳ��
            returnObj[2] = ListData; // ��ǰҳ����
        } catch (Exception e) {
            Debuger.println("Error Occur!");
            e.printStackTrace();
        }
        return returnObj;
    }

    // ��÷�ҳ�Ķ��������
    public Object[] getResult(List alist, List count, int beginNo,
            int pageRowCount) throws Exception {
        Object[] returnObj = new Object[3];
        List ListData = new Vector();
        int columnCount = 0;
        int recordCount = 0;
        int Count = 0;

        // �ܼ�¼��
        recordCount = new Integer(count.get(0).toString()).intValue();
        // recordCount = count.get(0);
        if (beginNo > recordCount) {
            returnObj[0] = new Integer(0); // �ܼ�¼��
            returnObj[1] = new Integer(1); // ��ҳ��
            returnObj[2] = ListData; // ��ǰҳ����
            return returnObj;
        }
        // ��ҳ��
        Count = recordCount / pageRowCount;
        // ���ж����ڼ�����ҳ��
        if (recordCount % pageRowCount != 0) {
            Count++;
        }
        // ���ж����ڼ������һҳ�ļ�¼��
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

        // ��try������װ�ط���ֵ
        try {
            returnObj[0] = new Integer(recordCount); // �ܼ�¼��
            returnObj[1] = new Integer(Count); // ��ҳ��
            returnObj[2] = ListData; // ��ǰҳ����
        } catch (Exception e) {
            Debuger.println("Error Occur!");
            e.printStackTrace();
        }
        return returnObj;
    }

    // ���ص��ô洢���̷��ص�����
    public Object[] selectProcJdbcObj(String querySql, int beginNo,
            int pageRowCount) throws Exception {
        List list = getProcJdbc(querySql);
        return getResult(list, beginNo, pageRowCount);
    }

    // ���������ļ��еĴ洢���̣�����ָ������������
    public Object[] selectProcQueryObj(String querySql, int beginNo,
            int pageRowCount, Object[] obj) throws Exception {
        List list = findProcQuery(querySql, obj);
        return getResult(list, beginNo, pageRowCount);
    }

    // ���������ļ����޲�����SQL������ָ������������
    public Object[] selectSqlNameQueryObj(String querySql, int beginNo,
            int pageRowCount) throws Exception {
        List list = findSqlNameQuery(querySql);
        return getResult(list, beginNo, pageRowCount);
    }

    // �õ�ϵͳ����

    public Date getSysDate() throws Exception {
        Session session = this.getBaseSession();
        List list = session.getNamedQuery("getDate").list();
        Date date = (Date) list.iterator().next();
        return date;
    }

    // ͨ��JDBC����DBRecord���͵�List
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

    // ͨ��JDBC�õ�һ����¼�ĵ�һ���ֶ�
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

    // ͨ��JDBC����һ��ָ����DBRecord
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

    // ͨ��HQL�õ���ѯ���
    public Iterator getHqlObject(String wherestring) throws Exception {
        Session session = this.getBaseSession();
        Query query = session.createQuery(wherestring);
        List it = query.list();
        session.close();
        return it.iterator();
    }

    // �õ�ָ���Ķ���
    public Object getObject(Class clazz, Serializable id) throws Exception {
        return this.getHibernateTemplate().get(clazz, id);
    }

    // ����һ������
    public void saveObject(Object o) throws Exception {
        this.getHibernateTemplate().save(o);
    }

    // ������޸�һ������session�������رգ�
    public void saveObjectNew(Object o) throws Exception {
        Session session = this.getBaseSession();
        session.saveOrUpdate(o);
        session.flush();
        session.close();
    }

    // �޸�һ������
    public void updateObject(Object o) throws Exception {
        this.getHibernateTemplate().update(o);
    }

    // ɾ��һ������
    public void removeObject(Object obj) throws Exception {
        this.getHibernateTemplate().delete(obj);
    }

    // ɾ��һ�����
    public void removeObjectAll(Collection collection) {
        this.getHibernateTemplate().deleteAll(collection);
    }

    // ɾ��һ�����
    public void removeObjects(Class clazz, Serializable[] id) throws Exception {
        if (id != null) {
            for (int i = 0; i < id.length; i++) {
                this.getHibernateTemplate().delete(getObject(clazz, id[i]));
            }
        }
    }

    // ���������ļ��еĴ洢����
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

    // ���������ļ����޲�����SQL
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
     * ʵ�ִ���SQL����ĵ�ֵ���� �ò�������SQL����ĵ�ֵ�������������ݵ�����ɾ���Ĵ���
     * 
     * @parm updateSql SQL���
     * @return ��boolean���أ��жϲ����Ƿ�ɹ�
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
     * ʵ�ִ������SQL���� �ò���ʵ�ִ������SQL���񣬰������ݵ�����ɾ���Ĵ���
     * 
     * @parm batchSql SQL�������
     * @return ��boolean���أ��жϲ����Ƿ�ɹ�
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


    // ʹ��Hibernate��session��ʽ����Map����
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

    
    //ʹ��Hibernate��session��ʽ�����ص�Object[]��װ��һ��DBRecord������
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
    // ʹ��Hibernate��session��ʽ����Map����  ��ҳ��ʾ
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
