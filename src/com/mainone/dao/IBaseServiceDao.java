package com.mainone.dao;

import com.mainone.util.DBRecord;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Date;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * To change this template use File | Settings | File Templates.
 */ 
public interface IBaseServiceDao{
   //����PageBean ������ҳ��ѯ������ ������Ŀ��
   public List findBySqlConditionParmFenYe(int currentPage, int pageSize, String sql);
   //��ѯ�������¼��
   public int resultCount(String sql);
   //ͨ���ص����� ����jdbc��װsql��� 
   public List<DBRecord> resultToDBRecord(String sql);
   //����List���� ������ҳ��ѯ sql�������н��
   public ArrayList<Map> findBySqlConditionParm(String sql);
   //����List<Object[]>��service�� Ȼ����з�װ
   public List<Object[]> findBySqlConditionParmtoObject(String sql);
    
   public Object getObject(Class clazz, Serializable id) throws Exception;

   public void saveObject(Object o) throws Exception;
   
   public void saveObjectNew(Object o) throws Exception;

   public void updateObject(Object o) throws Exception;

   public void removeObject(Object obj) throws Exception;

   public void removeObjects(Class clazz, Serializable[] id) throws Exception;

   public Session getBaseSession() throws Exception;

   public ArrayList getProcJdbc(String proc) throws Exception;

   public Object[] selectProcJdbcObj(String querySql, int beginNo, int pageRowCount) throws Exception;

   public List getSqlJdbc(String proc) throws Exception;

   public String selectOne(String sql) throws Exception;

   public DBRecord selectRow(String selects) throws Exception;

   public Iterator getHqlObject(String wherestring) throws Exception;

   public List findProcQuery(String str, Object[] obj) throws Exception;

   public List findSqlNameQuery(String str) throws Exception;

   public List findHqlQuery(String str) throws Exception;

   public Object[] selectProcQueryObj(String querySql, int beginNo, int pageRowCount, Object[] obj) throws Exception;

   public Object[] selectSqlNameQueryObj(String querySql, int beginNo, int pageRowCount) throws Exception;

   public Date getSysDate() throws Exception;

   public Object[] getResult(List alist, int beginNo, int pageRowCount) throws Exception;

   public Object[] getResult(List alist, List count, int beginNo, int pageRowCount) throws Exception;
  
   public  List getQueryData(String str,int end)throws Exception;
   public  boolean deleteHqlObject(String hql) throws Exception;
   public  boolean  updateHqlObject(String hql) throws Exception;
   public boolean execUpdate(String updateSql) throws Exception;
   public boolean execTransaction(String[] batchSql) throws Exception ;
   
}
