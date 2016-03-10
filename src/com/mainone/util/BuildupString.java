package com.mainone.util;

import java.util.*;
/**
 * <p>Title: BuildupString 类</p>
 * <p>Description: 提供将数组，键值队转换为标准SQL类型的String流的工具类</p>
 * Copyright:    Copyright (c) 2004-07-17
 * Company: mainOne
 * @author 方克锐
 * @version 1.0
 */
public class BuildupString
{

        public static void main(String[] args)
        {
               String[] arrFieldName  = {"aa","bb","cc","dd","ee","ff","gg","ww"};

               //DBRecord dbRecord = new DBRecord();
               Vector dbRecord = new Vector();
               for(int i = 0 ; i< arrFieldName.length ; i++)
               {
                        dbRecord.add(arrFieldName[i]) ;
                        System.out.println("Key = " + arrFieldName[i]) ;
                        System.out.println("value = " + i) ;
                        System.out.println("---------------------------") ;
               }
               ///System.out.println(getCols(arrFieldName) );
               System.out.println(getCols(arrFieldName,4));
        }
        /**
         * 将数组中存储的数据转换为String流
         *      例如： String[] cols ={"aa","bb","cc"} 的数组，
         *            将转换为：数据格式为"aa,bb,cc"的String流
         * @param cols 数组
         * @return String 数据格式为"aa,bb,cc"的String流
         */
        public static String getCols(String[] cols)
        {
                String strCols = "" ;
                if(cols != null && cols.length > 0)
                {
                        for(int i = 0 ; i < cols.length ; i++)
                        {
                                if(i != (cols.length -1))
                                {
                                        strCols += cols[i] + "," ;
                                }
                                else
                                {
                                        strCols += cols[i] ;
                                }
                        }
                }
                else
                {
                        strCols = "*" ;
                }
                return strCols ;
        }
        /**
         * 将向量中存储的数据转换为String流
         *      例如： Vector cols ={"aa","bb","cc"} 的向量，
         *            将转换为：数据格式为"'aa','bb','cc'"的String流
         * @param cols 数组
         * @return String 数据格式为"'aa','bb','cc'"的String流
         */
        public static String getCols(String[] cols,int type)
        {
                String strCols = "" ;
                if(cols != null && cols.length > 0)
                {
                        for(int i = 0 ; i < cols.length ; i++)
                        {
                                if(i != (cols.length -1))
                                {
                                        strCols += "'" + cols[i]  + "'," ;
                                }
                                else
                                {
                                        strCols += "'" + cols[i]  + "'" ;
                                }
                        }
                }
                return strCols ;
        }
        /**
         * 将键值队数据结构中存储的数据转换为String流
         *      例如： DBRecord cols ={"aa=aa1","bb=bb1","cc=cc1"} 的键值队，
         *            将转换为：数据格式为"aa＝'aa1' and bb = 'bb1' and cc = 'cc1'"的String流
         * @param cols 数组
         * @return String 数据格式为"aa＝'aa1' and bb = 'bb1' and cc = 'cc1'"的String流
         */
        public static String getCols(DBRecord cond)
        {
                String conditions = "" ;
                if(cond != null && !cond.isEmpty() )
                {
		        /******从Properties中获得键值列表*******/

                        Enumeration enumConds = cond.keys() ;

                        while(enumConds.hasMoreElements() )
                        {
                                String key = (String)enumConds.nextElement() ;
                                String value = (String)cond.getField(key);

                                conditions += key + "='" +value+ "' AND ";
                        }

                        int iLenV = conditions.length() ;

			/*****去掉最后的','*****/
			conditions = conditions.substring(0 , iLenV - 4) ;
                }

                return conditions ;
        }
        /**
         * 将键值队数据结构中存储的数据转换为String流
         * @param cols 数组
         * @param str  间隔符
         *      例如： 如果间隔符 str 为 'and' 则 DBRecord cols ={"aa=aa1","bb=bb1","cc=cc1"} 的键值队，
         *            将转换为：数据格式为"aa＝'aa1' and bb = 'bb1' and cc = 'cc1'"的String流
         * @return String 数据格式为"aa＝'aa1' and bb = 'bb1' and cc = 'cc1'"的String流
         */
        public static String getCols(DBRecord cond , String str)
        {
                String conditions = "" ;
                if(cond != null && !cond.isEmpty() )
                {
		        /******从Properties中获得键值列表*******/

                        Enumeration enumConds = cond.keys() ;

                        while(enumConds.hasMoreElements() )
                        {
                                String key = (String)enumConds.nextElement() ;
                                String value = (String)cond.getField(key);

                                conditions += key + "='" +value+ "' " + str + " ";
                        }

                        int iLenV = conditions.length() ;

			/*****去掉最后的','*****/
			conditions = conditions.substring(0 , iLenV - 2) ;
                }

                return conditions ;

        }
}