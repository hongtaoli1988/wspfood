package com.mainone.util;

import java.util.Enumeration;


/**
 * Title:        BuildSQL 类
 * Description:  产生基本数据库操作语句
 *               例如：select ,update ,insert ,delect 语句
 * Copyright:    Copyright (c) 2003-11-18
 * Company:      ce
 * @author      方克锐
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
	 * 生成插入数据库记录操作的SQL语句
	 * @param tableName 表名
	 * @param arrField  字段名列表
	 * @param record    字段名和字段值的键值队
	 * @return String   插入数据库记录操作的SQL语句
	 * @throws Exception
	 */
	public String createSQL( String tableName ,
				 DBRecord record ) throws Exception
	{
		String query = ""; 

		try
		{

			/***初始化SQL语句存放容器***/

			StringBuffer querys =
				new StringBuffer();

                        Enumeration enumKeys = record.keys() ;

                        String columns = "" ; //存贮字段名称容器
						String values  = "" ;  //存贮字段名对应的值容器

			/******从Properties中获得键值列表*******/

                        while(enumKeys.hasMoreElements() )
                        {
                                String key = (String)enumKeys.nextElement() ;
                                String value = (String)record.getField(key);

                                columns += key + ",";
								values +=  "'" + value + "'," ;
                        }

			/***************生成insert SQL语句******************/
			

			querys.append( "INSERT INTO " +
				       tableName +
				       "(" ) ;
			Debuger.println(querys);

			/******遍历列名列表，将列名存入StringBuffer中*********/

			int iLenC = columns.length() ;
			int iLenV = values.length() ;

			/*****去掉最后的','*****/

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
	 * 生成删除数据库记录操作的SQL语句
	 * @param tableName 表名
	 * @param conds      删除条件
	 * @return String   删除数据库记录操作的SQL语句
	 * @throws Exception
	 */
	public String removeSQL( String tableName,
                                 DBRecord conds ) throws Exception
	{
		String query  = "";
                String where = "" ;

		try
		{
			/***************生成update SQL语句******************/

			StringBuffer querys =
				new StringBuffer();

			querys.append( "DELETE "
					+ "FROM "
					+ tableName) ;

			/******遍历列名和列值列表，
	        	 *     将列名和对应列值存入StringBuffer中******/


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
	 * 生成查询数据库记录操作的SQL语句
	 * @param tableName 表名
	 * @param columns   返回字段名列表
	 * @param cond      查询条件
	 * @return  String 查询数据库记录操作的SQL语句
	 * @throws Exception
	 */
	public String selectSQL( String tableName ,
	                         String[] columns ,
	                         DBRecord cond) throws Exception
	{
		String query = "";

		try
		{

			/***初始化SQL语句存放容器***/

			StringBuffer querys =
				new StringBuffer();

                        String selectCol = BuildupString.getCols(columns) ;    //存贮字段名称容器
			String conditions  = BuildupString.getCols(cond)  ;    //存贮字段名对应的值容器

			/***************生成insert SQL语句******************/

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
	 * 生成更新数据库记录操作的SQL语句
	 * @param tableName 表名
	 * @param values    字段值列表
	 * @param conds     条件列表
	 * @return String 更新数据库记录操作的SQL语句
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
			/***************生成update SQL语句******************/

			StringBuffer querys =
				new StringBuffer();

			querys.append( "UPDATE "
				       + tableName
				       + " SET " ) ;

	        	/******从Properties中获得键值列表*******/

			/******遍历列名和列值列表，
	        	 *     将列名和对应列值存入StringBuffer中******/

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