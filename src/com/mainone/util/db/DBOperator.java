/*
 * �������� 2006-2-23
 * ���� wangguan
 */
package com.mainone.util.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import com.mainone.util.BuildSQL;
import com.mainone.util.DBRecord;
import com.mainone.util.Debuger;
import com.mainone.util.db.dao.DateType;



/**
 * ���ݿ������
 */
public class DBOperator {
    Context ctx=null;
    DataSource ds=null;
    public DBOperator(){
        try{
            ctx=new InitialContext();
            if(ctx==null)throw new Exception("û��ƥ��Ļ���");
            ds=(DataSource)ctx.lookup("java:comp/env/jdbc/mo");
            if(ds==null)throw new Exception("û��ƥ�����ݿ�");
            ctx.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public DBOperator(String resourceName){
        try{
            ctx=new InitialContext();
            if(ctx==null)throw new Exception("û��ƥ��Ļ���");
            ds=(DataSource)ctx.lookup("java:comp/env/jdbc/"+resourceName);
            if(ds==null)throw new Exception("û��ƥ�����ݿ�");
            ctx.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * ��ѯ
     * @param sql
     * @return
     * @throws Exception
     */
    public ArrayList  selectList(String sql)
    {
        if(sql.trim().toLowerCase().startsWith("exec")){
            return procList(sql);
        }
    	String []columnName;
    	Statement stmt = null;
    	ResultSet Rs = null;	
    	Connection conn = null;
    	ArrayList ListData = new ArrayList();
    	int columnCount=0;
    	try{
    	  conn = ds.getConnection();	
    	  stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
    	  stmt.setQueryTimeout(60);
    	  Rs = stmt.executeQuery(sql);
    	  ResultSetMetaData rsmd = Rs.getMetaData();	
    	  int NumCols =  rsmd.getColumnCount();
    	  columnName = new String[NumCols];
    	  for(int k=0;k<NumCols;k++)
    			 {
    				columnName[k]=rsmd.getColumnName(k+1);
    			 }
    	  while(Rs.next()){
    		int i=0;
    		DBRecord record = new DBRecord();
    		for(;i<NumCols;i++){
    		   String columnValue=Rs.getString(i+1);
    	   columnValue = columnValue ==null?"":columnValue;
    	   record.setProperty(columnName[i],columnValue);
    		}
    		ListData.add(record);
    		
    	  }
    	 }
    	catch (java.sql.SQLException e){
    		e.printStackTrace();
    	 }
    	finally
    	 {  try {
    			  Rs.close();
    			  stmt.close();
    			  conn.close();
    			}
    	catch (java.sql.SQLException e)
    		{  
    			e.printStackTrace();
    		}     	    
    	 }
    	return ListData;  
    }
    /**
     * ��ҳ��ѯ
     * @param querySql
     * @param beginNo
     * @param pageRowCount
     * @return
     * @throws Exception
     */
    public Object[] selectObj(String querySql, int beginNo, int pageRowCount) throws Exception
    {
       ArrayList arrayList=selectList(querySql);
       return getResult(arrayList,beginNo,pageRowCount);
    } 
    /**
     * ʵ�ִ���SQL����ĵ�ֵ����
     * �ò�������SQL����ĵ�ֵ�������������ݵ�����ɾ���Ĵ���
     * @parm   updateSql     SQL��� 
     * @return ��boolean���أ��жϲ����Ƿ�ɹ�
     */
    public boolean execUpdate(String updateSql) throws Exception
    {
  	int iCode =0;
  	boolean resultState = true;
  	Statement stmt = null;
  	Connection conn = null;
  	try {
  	    	conn=ds.getConnection();	
  		  stmt = conn.createStatement();
  		  iCode = stmt.executeUpdate(updateSql);
  		}
  	catch (java.sql.SQLException e)
  	 {  
  		resultState = false;
  		Debuger.println("Error Occur.When Execute the Sql and Return Code " + iCode + " : " + updateSql);
  		e.printStackTrace();
          
  	 }
  	finally
  	 {  try {
  			  stmt.close();
  			  conn.close();
  			}
  	catch (java.sql.SQLException e)
  		{  Debuger.println("Error Occur.When Close the DB Statement or Connection Object!");
  			e.printStackTrace();
  		}     	    
  	 }
  	return resultState;
    }
    /**
     * ʵ�ִ������SQL����
     * �ò���ʵ�ִ������SQL���񣬰������ݵ�����ɾ���Ĵ���
     * @parm   batchSql     SQL������� 
     * @return ��boolean���أ��жϲ����Ƿ�ɹ�
     */
    public boolean execTransaction(String[] batchSql) throws Exception {
  	Statement stmt = null;         
  	int[] iCode;
  	boolean returnState = true;    
  	int i =0;
  	Connection conn = null;
  	try {
  	    	conn=ds.getConnection();
  		  conn.setAutoCommit(false);
  		  stmt = conn.createStatement();
  		  for (; i < batchSql.length; i++) 
  		  { 
  			  stmt.addBatch(batchSql[i]);
  		  }
  		  iCode = stmt.executeBatch();
  		  conn.commit();
  		}
  	catch (java.sql.SQLException e)
  	   {
  		 conn.rollback();
  		 e.printStackTrace();
  		 returnState = false;
  	   }
  	finally
  	 {  try {
  			  conn.setAutoCommit(true);
  			  stmt.close();
  			  conn.close();
  			}
  	catch (java.sql.SQLException e)
  		{  
  			e.printStackTrace();
  		}       	    
  	 }
  	return returnState;    
    }
    /**
     * ���ط���������һ����¼
     * @parame selects SQL���
     * @return DBRecord ���ط���������һ����¼ ,����ж�����¼��������,�򷵻����һ��
     * */

    public DBRecord selectRow(String selects)throws Exception
    {
    	String []columnName;
    	Statement stmt = null;
    	ResultSet rs = null;
    	Connection conn = null;	
    	DBRecord record = new DBRecord();
    	int columnCount=0;
    	try{
    	  conn = ds.getConnection();
    	  stmt = conn.createStatement();	
    	  rs = stmt.executeQuery(selects);
    	  ResultSetMetaData rsmd = rs.getMetaData();	
    	  int NumCols =  rsmd.getColumnCount();
    	  columnName = new String[NumCols];
    	  for(int k=0;k<NumCols;k++)
    			 {
    				columnName[k]=rsmd.getColumnName(k+1);
    			 }
    	   if(rs.next())
    	   {
    		  int i=0;
    		  for(;i< NumCols;i++)
    			{
    				String columnValue=rs.getString(i+1);
    				columnValue = columnValue ==null?"":columnValue;
    				record.setField(columnName[i],columnValue);
    			}
    	   }
    	}
    	catch (java.sql.SQLException e){
    		e.printStackTrace();
    	 }
    	finally
    	 {  try {
    			  rs.close();
    			  stmt.close();
    			  conn.close();
    			}
    	catch (java.sql.SQLException e)
    		{  Debuger.println("Error Occur.When Close the DB ResultSet,Statement or Connection Object!");
    			e.printStackTrace();
    		}     	    
    	 }
    	return record;        
      
    }
    /**
	   * ʵ�ֵ�ֵ��ѯ����
	   * �ò�ѯʵ�ֵ�ֵ��ѯ��������������ļ�¼����������������ĳ���ض��ֶ�ֵ�ȵ�
	   * @param  sql  ��ѯSQL��䡣
	   * @return ��String���󷵻صĲ�ѯ���
	   */  
	public String selectOne(String sql) throws SQLException
	{
		Statement stmt = null;
		ResultSet rs =null;
		Connection conn = null;
		String returnValue = null;
		try
		{
			conn = ds.getConnection();
			stmt=conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next())
			  { returnValue = rs.getString(1);  }
		 }
		catch(Exception e)
			{   Debuger.println("Error Occur.When Execute the Sql : " + sql);
				e.printStackTrace();
			}
		finally
		{
			try{  
				  rs.close();
				  stmt.close();
				  conn.close();
			   }
			catch(SQLException sqlexception)
			   {
					Debuger.println("Error Occur.When Close the DB ResultSet,Statement or Connection Object!");
					sqlexception.printStackTrace();
			   }
		}
		return returnValue;
	}
    public Object[] getResult(ArrayList alist,int beginNo, int pageRowCount) throws Exception
    {
    	Object[] returnObj = new Object[3];
  	ArrayList ListData = new ArrayList();
  	int columnCount=0;
  	int recordCount=0;
  	int Count=0;

  	  //�ܼ�¼��
  	  recordCount = alist.size();
  	  if(beginNo>recordCount){
  		  returnObj[0] = new Integer(0);  //�ܼ�¼��
  		  returnObj[1] = new Integer(1);    //��ҳ��
  		  returnObj[2] = ListData;                  //��ǰҳ����
  		return returnObj;
  	  }
  	  //��ҳ��  
  	  Count = recordCount / pageRowCount;
  	  //���ж����ڼ�����ҳ��         
  	  if (recordCount % pageRowCount != 0)
  		 { Count++; }
  	  //���ж����ڼ������һҳ�ļ�¼��
  	  if ((recordCount - (beginNo - 1)) < pageRowCount) 
  		 {
  			pageRowCount = recordCount - (Count - 1) * pageRowCount;
  		 }
  	  
  	  boolean flag = false;
  	  int j = 0;
  	  if(recordCount != 0){
  		  for(int i=beginNo-1;i<beginNo+pageRowCount-1;i++){
  		  	ListData.add(j,alist.get(i));
  		  	j++;
  		  }	
  	  }
  	  //System.out.println("ListData======"+ListData);
  	  //Debuger.println("ListData is successful and The Begin Row Is = " + beginNo);
  	  alist.clear();
  	  
  	//��try������װ�ط���ֵ 
  	try{	  
  		  returnObj[0] = new Integer(recordCount);  //�ܼ�¼��
  		  returnObj[1] = new Integer(Count);    //��ҳ��
  		  returnObj[2] = ListData;                  //��ǰҳ����
  	   }    
  	catch (Exception e)
  	 {  Debuger.println("Error Occur!");
  		e.printStackTrace();
  	 }     	
  	return returnObj;        
    }
    public Object[] getResult(Iterator it,int beginNo, int pageRowCount) throws Exception
    {
        ArrayList alist=new ArrayList();
        Object obj=null;
        int k=0;
        while(it.hasNext()){
            obj=it.next();
            alist.add(k,obj);
            k++;
        }
    	Object[] returnObj = new Object[3];
  	ArrayList ListData = new ArrayList();
  	int columnCount=0;
  	int recordCount=0;
  	int Count=0;

  	  //�ܼ�¼��
  	  recordCount = alist.size();
  	  if(beginNo>recordCount){
  		  returnObj[0] = new Integer(0);  //�ܼ�¼��
  		  returnObj[1] = new Integer(1);    //��ҳ��
  		  returnObj[2] = ListData;                  //��ǰҳ����
  		return returnObj;
  	  }
  	  //��ҳ��  
  	  Count = recordCount / pageRowCount;
  	  //���ж����ڼ�����ҳ��         
  	  if (recordCount % pageRowCount != 0)
  		 { Count++; }
  	  //���ж����ڼ������һҳ�ļ�¼��
  	  if ((recordCount - (beginNo - 1)) < pageRowCount) 
  		 {
  			pageRowCount = recordCount - (Count - 1) * pageRowCount;
  		 }
  	  
  	  boolean flag = false;
  	  int j = 0;
  	  if(recordCount != 0){
  		  for(int i=beginNo-1;i<beginNo+pageRowCount-1;i++){
  		  	ListData.add(j,alist.get(i));
  		  	j++;
  		  }	
  	  }
  	  //System.out.println("ListData======"+ListData);
  	  //Debuger.println("ListData is successful and The Begin Row Is = " + beginNo);
  	  alist.clear();
  	  
  	//��try������װ�ط���ֵ 
  	try{	  
  		  returnObj[0] = new Integer(recordCount);  //�ܼ�¼��
  		  returnObj[1] = new Integer(Count);    //��ҳ��
  		  returnObj[2] = ListData;                  //��ǰҳ����
  	   }    
  	catch (Exception e)
  	 {  Debuger.println("Error Occur!");
  		e.printStackTrace();
  	 }     	
  	return returnObj;        
    }
    /**
     * �洢���̲�ѯ
     * @param proc
     * @return
     * @throws Exception
     */
    private ArrayList  procList(String proc)
    {
    	String []columnName;
    	CallableStatement stmt = null;
    	ResultSet Rs = null;	
    	Connection conn = null;
    	ArrayList ListData = new ArrayList();
    	int columnCount=0;
    	try{
    	  conn = ds.getConnection();	
    	  stmt = conn.prepareCall(proc,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
    	  stmt.setQueryTimeout(60);
    	  Rs = stmt.executeQuery();
    	  ResultSetMetaData rsmd = Rs.getMetaData();	
    	  int NumCols =  rsmd.getColumnCount();
    	  columnName = new String[NumCols];
    	  for(int k=0;k<NumCols;k++)
    			 {
    				columnName[k]=rsmd.getColumnName(k+1);
    			 }
    	  while(Rs.next()){
    		int i=0;
    		DBRecord record = new DBRecord();
    		for(;i<NumCols;i++){
    		   String columnValue=Rs.getString(i+1);
    	   columnValue = columnValue ==null?"":columnValue;
    	   record.setProperty(columnName[i],columnValue);
    		}
    		ListData.add(record);
            	
    	  }
    	 }
    	catch (java.sql.SQLException e){
    		e.printStackTrace();
    	 }
    	finally
    	 {  try {
    			  Rs.close();
    			  stmt.close();
    			  conn.close();
    			}
    	catch (java.sql.SQLException e)
    		{  Debuger.println("Error Occur.When Close the DB ResultSet,Statement or Connection Object!");
    			e.printStackTrace();
    		}     	    
    	 }
    	return ListData;  
    }
    /**
	   * ʵ���Ƿ���ڸü�¼�Ĳ�ѯ����
	   * @param  sql  ��ѯSQL��䡣
	   * @return ��boolean���󷵻صĲ�ѯ���
	   */ 
public boolean isRowExist(String sql) throws Exception{
	boolean returnValue = false;
	Statement stmt = null;
	ResultSet rs =null;
	Connection conn = null;
	try
	{
		conn = ds.getConnection();
		stmt=conn.createStatement();
		rs = stmt.executeQuery(sql);
		if(rs.next())
		  { returnValue = true;  }
	 }
	catch(Exception e)
		{   
			Debuger.println("Error Occur.When Execute the Sql : " + sql);
			e.printStackTrace();
		}
	finally
	{
		try{  
			  rs.close();
			  stmt.close();
			  conn.close();
		   }
		catch(SQLException sqlexception)
		   {
				Debuger.println("Error Occur.When Close the DB ResultSet,Statement or Connection Object!");
				sqlexception.printStackTrace();
		   }
	}
	   return returnValue;
}
	public boolean insertBoo(String TableName,DBRecord values){
		boolean bReturn = false;
		BuildSQL buildSQL = new BuildSQL();
		Statement stmt = null;
		Connection conn = null;
		try {
		    conn = ds.getConnection();
            String sInsertInto = buildSQL.createSQL(TableName,values);
			stmt = conn.createStatement();
			stmt.executeUpdate(sInsertInto);
			bReturn = true;
		} 
		catch (Exception e){
			e.printStackTrace();
		} 
		finally {
			try{  
				  stmt.close();
				  conn.close();
			   }
			catch(Exception e)
			   {
					e.printStackTrace();
			   }
			
		}
		return bReturn;
	}
	public ArrayList selectList(String tableName,String[] selectCols,DBRecord conds){
        /****��ʼ������****/
        Vector vRecords = new Vector();
        ArrayList lRecords = new ArrayList();
		BuildSQL buildSQL = new BuildSQL();
		Statement stmt = null;
		ResultSet rs =null;
		Connection conn = null;

        try
        {
                String query =  "" ;

                query = buildSQL.selectSQL(tableName,selectCols,conds);
                conn = ds.getConnection();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);

                //���û�м�¼���׳�Exception����
	        if (!rs.next())
	        {
	                throw new Exception("Record has't found!");
	        }

                int count =
                        rs.getMetaData().getColumnCount() ;

	        do
                {
                        DBRecord singRecord = new DBRecord();

	                for(int i = 1 ;i < (count + 1) ; i++)
	                {
	                        String strValue = "";

                                /******ͨ���������ȡ�ֶ�����,�ֶ�����******/

                                String strColumnName =
                                        rs.getMetaData().getColumnName(i).toLowerCase() ;

                                String type = rs.getMetaData().getColumnTypeName(i).toLowerCase();

                                /**
                                 * �����������жϣ�
                                 * ���ݲ�ͬ���������Ͷ�Ӧ��ͬ�����ݽ��շ�ʽ
                                 **/

						
						
                                if(type == null)
                                {
                                        strValue = rs.getString(strColumnName);
                                }
	                        else if( type.equals(DateType.VARCHAR)
	            	                 ||type.equals(DateType.CHAR)
	            	                 ||type.equals(DateType.TEXT)
	            	                 ||type.equals(DateType.VARCHAR2))

	                        {
	                                strValue = rs.getString(strColumnName);
	                        }
                                else if( type.equals(DateType.INTEGER)
	            	                 ||
	            	                 type.equals(DateType.NUMBER)
                                         ||type.equals(DateType.INT))
	                        {
	                                strValue = rs.getInt(strColumnName) + "";
	                        }
	                        else if(type.equals(DateType.FLOAT))
	                        {
	                                strValue = rs.getFloat(strColumnName) + "";
	                        }
	                        else if(type.equals(DateType.DATE))
	                        {
	                        	strValue=rs.getDate(strColumnName)+"";
	                        }
	                        if(strValue != null)
	                        {
	            	                strColumnName =
	            	  	                strColumnName.toLowerCase() ;

	                                singRecord.setField(strColumnName,strValue) ;
	                        }
	                }

                        if(!singRecord.isEmpty())
                        {
							lRecords.add(singRecord) ;
                        }
	        }while(rs.next());

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
			try{  
				  rs.close();
				  stmt.close();
				  conn.close();
			   }
			catch(SQLException sqlexception)
			   {
					sqlexception.printStackTrace();
			   }
                
        }
        return lRecords;
	}
	public boolean updateBoo(String tableName,DBRecord updateCols,DBRecord conditions){
		boolean isSuccess = false;
		BuildSQL buildSQL = new BuildSQL();
		Statement stmt = null;
		Connection conn = null;
        try
        {
                String query = buildSQL.updataSQL(tableName,
                                                  updateCols,
                                                  conditions);
                conn = ds.getConnection();
                stmt = conn.createStatement();

                if(stmt.executeUpdate(query) < 1)
        {
	        throw new Exception("Update Record is failing !");
        }
                isSuccess = true ;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
			try{  
				  stmt.close();
				  conn.close();
			   }
			catch(SQLException sqlexception){
					sqlexception.printStackTrace();
			   }
                
        }
        return isSuccess ;
	}
	/**
	 * ����һ�����ݿ��е����б������
	 *
	 * @return String[] һ�����ݿ��е����б������
     *
     * @throws Exception
	 */
	public String[] getTables() throws Exception
	{
	    ArrayList alTables=null;
	    Connection conn = ds.getConnection();
	    DatabaseMetaData dbmd = null;
		//�ȼ���Ƿ���Ҫ��ѯ���ݿ�
        if(alTables == null)
        {
            alTables = new ArrayList();

            if(dbmd == null) dbmd=conn.getMetaData();
            ResultSet rsTables = dbmd.getTables(null, null,null, new String[] {"TABLE"});
            while (rsTables.next())
            {
                alTables.add(rsTables.getString("TABLE_NAME"));
            }// end of while
        }
        if(alTables.size() == 0)    return new String[0];
	    return (String[])alTables.toArray(new String[1]);
	}

	/**
	 * �������ݿ���ĳ���ض���������е�����
	 *
	 * @param table ָ���ı�����
	 * @return String[] �����е�����
     *
     * @throws Exception
	 */
	public String[] getColumns(String table) throws Exception
	{
	    HashMap hmTableColumns = null;
    	if (hmTableColumns == null) hmTableColumns = new HashMap();
    	ArrayList alColumns = new ArrayList();
	    Connection conn = ds.getConnection();
	    DatabaseMetaData dbmd = null;
        //�ȼ���Ƿ���Ҫ��ѯ���ݿ�
        if (!hmTableColumns.containsKey(table)) {
            if(dbmd == null) dbmd=conn.getMetaData();
            ResultSet rsColumns = dbmd.getColumns(null, null, table, null);
            hmTableColumns.put(table, rsColumns);
        }

        ResultSet rsColumns = (ResultSet)hmTableColumns.get(table);
        while (rsColumns.next())
        {
            alColumns.add(rsColumns.getString("COLUMN_NAME"));
        }// end of while
        if(alColumns.size() == 0)   return new String[0];
	    return (String[])alColumns.toArray(new String[1]);
	}

    /*****************2006-11*********************/
    /**
     * �洢���̲�ѯ������List������ΪMapԪ��
     * @param proc
     * @return List
     * @throws Exception
     */ 
    public List executeQueryProc(String proc) throws Exception
    {
        List list = new ArrayList();
        CallableStatement stmt = null;
    	ResultSet rs = null;	
    	Connection conn = null;
        int colCount = 0;

        try
        {
        	conn = ds.getConnection();	
      	    stmt = conn.prepareCall(proc,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      	    rs = stmt.executeQuery();        
            ResultSetMetaData rsmd = rs.getMetaData();
            colCount = rsmd.getColumnCount();
            
            while ( rs.next() )
            {  
                Map map = new LinkedHashMap();
                
                for ( int i = 1;i<= colCount; i++ )
                {
                    map.put(rsmd.getColumnName(i), rs.getObject(i));
                }

                list.add(map);
            }   
            
         }
          catch (NullPointerException en )
          {
        	  System.out.println("��ָ��");
        	  en.printStackTrace();
          }
          catch(SQLException sqle){         	  
          	  System.out.println("ȡ���ݲ�ѯʱ����");
          	  sqle.printStackTrace();
          }
          finally
          {
          	try{  
          		if ( rs != null ) rs.close();
                if ( stmt != null ) stmt.close();
				if ( conn != null ) conn.close();
			   }
			catch(SQLException sqlexception)
			   {
					sqlexception.printStackTrace();
			   }
          }
        
        return list;    
     } 
    /**
     * SQL��ѯ������List������ΪMapԪ��
     * @param sql
     * @return List
     * @throws Exception
     */     
    public List executeQuerySql(String sql) throws Exception
    {
        List list = new ArrayList();
        Statement stmt = null;
        ResultSet  rs  = null;
        Connection conn = null;
        int colCount = 0;

        try
        {
        	conn = ds.getConnection();
        	stmt = conn.createStatement();
            rs  = stmt.executeQuery(sql);           
            ResultSetMetaData rsmd = rs.getMetaData();
            colCount = rsmd.getColumnCount();
            
            while ( rs.next() )
            {  
                Map map = new LinkedHashMap();
                
                for ( int i = 1;i<= colCount; i++ )
                {
                    map.put(rsmd.getColumnName(i), rs.getObject(i));
                }

                list.add(map);
            }   
            
         }
          catch (NullPointerException en )
          {
        	  System.out.println("��ָ��");
        	  en.printStackTrace();
          }
          catch(SQLException sqle){         	  
          	  System.out.println("ȡ���ݲ�ѯʱ����");
          	  sqle.printStackTrace();
          }
          finally
          {
          	try{  
          		if ( rs != null ) rs.close();
                if ( stmt != null ) stmt.close();
				if ( conn != null ) conn.close();
			   }
			catch(SQLException sqlexception)
			   {
					sqlexception.printStackTrace();
			   }
          }
        
        return list;    
     } 
    //ͨ��JDBC����DBRecord���͵�List
    public List getSqlJdbc(String proc) throws Exception{
       String[] columnName;
       Statement stmt = null;
       ResultSet Rs = null;
       Connection conn = null;
       List ListData = new ArrayList();
       int columnCount = 0;
       try{
          conn = ds.getConnection();
          stmt = conn.createStatement();
          Rs = stmt.executeQuery(proc);
          ResultSetMetaData rsmd = Rs.getMetaData();
          int NumCols = rsmd.getColumnCount();
          columnName = new String[NumCols];
          for(int k = 0; k < NumCols; k++){
             columnName[k] = rsmd.getColumnName(k + 1);
          }
          while(Rs.next()){
             int i = 0;
             DBRecord record = new DBRecord();
             for(; i < NumCols; i++){
                String columnValue = Rs.getString(i + 1);
                columnValue = columnValue == null ? "" : columnValue;
                record.setProperty(columnName[i], columnValue);
             }
             ListData.add(record);

          }
       }
       catch(java.sql.SQLException e){
          e.printStackTrace();
       }
       finally{
          try{
             Rs.close();
             stmt.close();
             conn.close();
             
          }
          catch(java.sql.SQLException e){
             Debuger.println("Error Occur.When Close the DB ResultSet,Statement or Connection Object!");
             e.printStackTrace();
             
          }
       }
       return ListData;
    }

}
