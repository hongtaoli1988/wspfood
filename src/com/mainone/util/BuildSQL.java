package com.mainone.util;

import java.util.Enumeration;


/**
 * Title:        BuildSQL ��
 * Description:  �����������ݿ�������
 *               ���磺select ,update ,insert ,delect ���
 * Copyright:    Copyright (c) 2003-11-18
 * Company:      ce
 * @author      ������
 * @version 1.0
 */

public class BuildSQL
{

	public BuildSQL()
	{
	}
        public static void  main(String[] args)
        {
               BuildSQL buildSQL = new BuildSQL();
               String[] arrFieldName  = {"aa","bb","cc","dd","ee","ff","gg","ww"};

               DBRecord dbRecord = new DBRecord();

               for(int i = 0 ; i< arrFieldName.length-2 ; i++)
               {
                        dbRecord.setField(arrFieldName[i],"" + i*2) ;
                        Debuger.println("Key = " + arrFieldName[i]) ;
                        Debuger.println("value = " + i) ;
                        Debuger.println("---------------------------") ;
               }
               try
               {

                       String sql=null;
                        //sql = buildSQL.selectSQL("otc",arrFieldName,dbRecord);
                        sql=buildSQL.updataSQL("otc",dbRecord,dbRecord);
                        Debuger.println(buildSQL.createSQL("otc",dbRecord)) ;
                        Debuger.println(sql);

               }catch(Exception e){}
        }
	/**
	 * ���ɲ������ݿ��¼������SQL���
	 * @param tableName ����
	 * @param arrField  �ֶ����б�
	 * @param record    �ֶ������ֶ�ֵ�ļ�ֵ��
	 * @return String   �������ݿ��¼������SQL���
	 * @throws Exception
	 */
	public String createSQL( String tableName ,
				 DBRecord record ) throws Exception
	{
		String query = ""; 

		try
		{

			/***��ʼ��SQL���������***/

			StringBuffer querys =
				new StringBuffer();

                        Enumeration enumKeys = record.keys() ;

                        String columns = "" ; //�����ֶ���������
						String values  = "" ;  //�����ֶ�����Ӧ��ֵ����

			/******��Properties�л�ü�ֵ�б�*******/

                        while(enumKeys.hasMoreElements() )
                        {
                                String key = (String)enumKeys.nextElement() ;
                                String value = (String)record.getField(key);

                                columns += key + ",";
								values +=  "'" + value + "'," ;
                        }

			/***************����insert SQL���******************/
			

			querys.append( "INSERT INTO " +
				       tableName +
				       "(" ) ;
			Debuger.println(querys);

			/******���������б�����������StringBuffer��*********/

			int iLenC = columns.length() ;
			int iLenV = values.length() ;

			/*****ȥ������','*****/

			columns = columns.substring(0 , iLenC - 1) ;
			values = values.substring(0 , iLenV - 1) ;

			querys.append( columns
				       + " ) VALUES ( "
				       + values
				       + " ) " );

			query = querys.toString();

		}
		catch(Exception e)
		{
			throw new Exception("Error executing SQL"+query+ e.toString());
		}
		return query ;
	}
	/**
	 * ����ɾ�����ݿ��¼������SQL���
	 * @param tableName ����
	 * @param conds      ɾ������
	 * @return String   ɾ�����ݿ��¼������SQL���
	 * @throws Exception
	 */
	public String removeSQL( String tableName,
                                 DBRecord conds ) throws Exception
	{
		String query  = "";
                String where = "" ;

		try
		{
			/***************����update SQL���******************/

			StringBuffer querys =
				new StringBuffer();

			querys.append( "DELETE "
					+ "FROM "
					+ tableName) ;

			/******������������ֵ�б�
	        	 *     �������Ͷ�Ӧ��ֵ����StringBuffer��******/


                        where = BuildupString.getCols(conds);

                        if(where == null || where.trim().length() <= 0)
                        {
			        querys.append( where );

                        }
                        else
                        {
			        querys.append( " WHERE " +
                                               where );
                        }
			query = querys.toString();

			Debuger.println(query) ;
		}
		catch(Exception e)
		{
			throw new Exception("Error executing SQL : " + query + e.toString());
		}

		return query;
	}
	/**
	 * ���ɲ�ѯ���ݿ��¼������SQL���
	 * @param tableName ����
	 * @param columns   �����ֶ����б�
	 * @param cond      ��ѯ����
	 * @return  String ��ѯ���ݿ��¼������SQL���
	 * @throws Exception
	 */
	public String selectSQL( String tableName ,
	                         String[] columns ,
	                         DBRecord cond) throws Exception
	{
		String query = "";

		try
		{

			/***��ʼ��SQL���������***/

			StringBuffer querys =
				new StringBuffer();

                        String selectCol = BuildupString.getCols(columns) ;    //�����ֶ���������
			String conditions  = BuildupString.getCols(cond)  ;    //�����ֶ�����Ӧ��ֵ����

			/***************����insert SQL���******************/

			querys.append( "SELECT ") ;

                        if(conditions == null || conditions.trim() .length() <= 0)
                        {
			        querys.append( selectCol
			        	       + " FROM "
			        	       + tableName );

                        }
                        else
                        {
			        querys.append( selectCol
			        	       + " FROM "
			        	       + tableName
			        	       + " WHERE "
			        	       + conditions );
                        }

			query = querys.toString();

		}
		catch(Exception e)
		{
			throw new Exception("Error executing SQL"+query+ e.toString());
		}
		return query ;
	}
	/**
	 * ���ɸ������ݿ��¼������SQL���
	 * @param tableName ����
	 * @param values    �ֶ�ֵ�б�
	 * @param conds     �����б�
	 * @return String �������ݿ��¼������SQL���
	 * @throws Exception
	 */
	public String updataSQL( String tableName ,
				 DBRecord values ,
				 DBRecord conds ) throws Exception
	{
		String query  = "";
                String update = "";
                String where = "" ;

		try
		{
			/***************����update SQL���******************/

			StringBuffer querys =
				new StringBuffer();

			querys.append( "UPDATE "
				       + tableName
				       + " SET " ) ;

	        	/******��Properties�л�ü�ֵ�б�*******/

			/******������������ֵ�б�
	        	 *     �������Ͷ�Ӧ��ֵ����StringBuffer��******/

                        update = BuildupString.getCols(values,",");
                        where = BuildupString.getCols(conds);

                        if(where == null || where.trim().length() <= 0)
                        {
			        querys.append( update +
			                       where );

                        }
                        else
                        {
			        querys.append( update
			        	       + " WHERE "
			        	       + where );
                        }

			query = querys.toString();
			//Debuger.println(query) ;
		}
		catch(Exception e)
		{
			throw new Exception("Error executing SQL : " + query + e.toString());
		}
		return query ;
	}
}