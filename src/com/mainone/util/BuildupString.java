package com.mainone.util;

import java.util.*;
/**
 * <p>Title: BuildupString ��</p>
 * <p>Description: �ṩ�����飬��ֵ��ת��Ϊ��׼SQL���͵�String���Ĺ�����</p>
 * Copyright:    Copyright (c) 2004-07-17
 * Company: mainOne
 * @author ������
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
         * �������д洢������ת��ΪString��
         *      ���磺 String[] cols ={"aa","bb","cc"} �����飬
         *            ��ת��Ϊ�����ݸ�ʽΪ"aa,bb,cc"��String��
         * @param cols ����
         * @return String ���ݸ�ʽΪ"aa,bb,cc"��String��
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
         * �������д洢������ת��ΪString��
         *      ���磺 Vector cols ={"aa","bb","cc"} ��������
         *            ��ת��Ϊ�����ݸ�ʽΪ"'aa','bb','cc'"��String��
         * @param cols ����
         * @return String ���ݸ�ʽΪ"'aa','bb','cc'"��String��
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
         * ����ֵ�����ݽṹ�д洢������ת��ΪString��
         *      ���磺 DBRecord cols ={"aa=aa1","bb=bb1","cc=cc1"} �ļ�ֵ�ӣ�
         *            ��ת��Ϊ�����ݸ�ʽΪ"aa��'aa1' and bb = 'bb1' and cc = 'cc1'"��String��
         * @param cols ����
         * @return String ���ݸ�ʽΪ"aa��'aa1' and bb = 'bb1' and cc = 'cc1'"��String��
         */
        public static String getCols(DBRecord cond)
        {
                String conditions = "" ;
                if(cond != null && !cond.isEmpty() )
                {
		        /******��Properties�л�ü�ֵ�б�*******/

                        Enumeration enumConds = cond.keys() ;

                        while(enumConds.hasMoreElements() )
                        {
                                String key = (String)enumConds.nextElement() ;
                                String value = (String)cond.getField(key);

                                conditions += key + "='" +value+ "' AND ";
                        }

                        int iLenV = conditions.length() ;

			/*****ȥ������','*****/
			conditions = conditions.substring(0 , iLenV - 4) ;
                }

                return conditions ;
        }
        /**
         * ����ֵ�����ݽṹ�д洢������ת��ΪString��
         * @param cols ����
         * @param str  �����
         *      ���磺 �������� str Ϊ 'and' �� DBRecord cols ={"aa=aa1","bb=bb1","cc=cc1"} �ļ�ֵ�ӣ�
         *            ��ת��Ϊ�����ݸ�ʽΪ"aa��'aa1' and bb = 'bb1' and cc = 'cc1'"��String��
         * @return String ���ݸ�ʽΪ"aa��'aa1' and bb = 'bb1' and cc = 'cc1'"��String��
         */
        public static String getCols(DBRecord cond , String str)
        {
                String conditions = "" ;
                if(cond != null && !cond.isEmpty() )
                {
		        /******��Properties�л�ü�ֵ�б�*******/

                        Enumeration enumConds = cond.keys() ;

                        while(enumConds.hasMoreElements() )
                        {
                                String key = (String)enumConds.nextElement() ;
                                String value = (String)cond.getField(key);

                                conditions += key + "='" +value+ "' " + str + " ";
                        }

                        int iLenV = conditions.length() ;

			/*****ȥ������','*****/
			conditions = conditions.substring(0 , iLenV - 2) ;
                }

                return conditions ;

        }
}