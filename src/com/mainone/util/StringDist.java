package com.mainone.util;

import java.util.Hashtable;
/**
 * <p>Title: �������� StringDist</p>
 * <p>Description: �ַ��������࣬ ��һ���ַ�����ĳλ�ÿ�ʼ��ĳ�ַ���Ϊ�ָ������зָ�(�õ�ÿ����Ϊ�ַ������ַ�������)</p>
 * Copyright:    Copyright (c) 2004-07-17
 * Company: mainOne
 * @author ������
 * @version 1.0
 */
public class StringDist {
	 /**
	 *   ��һ���ַ�����ĳλ�ÿ�ʼ��ĳ�ַ���Ϊ�ָ������зָ�(�õ�ÿ����Ϊ�ַ������ַ�������).
	 *  <blockquote><pre>
	 *     String list[] = StringDist.splitString("AAAA,BBBB,CCCC,DDDDD",0,',')
	 *     // list Ϊ  { "AAAA","BBBB","CCCC","DDDD" }
	 *   </pre></blockquote>
	 *   @param  str  ���ָ����ַ���
	 *   @param  istart ��ʼλ��
	 *   @param  delimiter  �ָ���
	 *   @return  �ָ��Ľ��
	*/

        public static final String[] splitString(String str,int istart,char delimiter){
		if(str==null)
			return null;
		int sl = str.length();
		int n = 0;
		for(int i=istart;i<sl;i++)
			if(str.charAt(i)==delimiter)
				n++;
		String[] sa = new String[n+1];
		int i=istart,j = 0;
		for(;i<sl;)
		{
			int iend = str.indexOf(delimiter,i);
			if(iend<0)
				break;
			sa[j++] = str.substring(i,iend);
			i = iend+1;
		}
		sa[j++] = str.substring(i);
		return sa;
	}
	/**
	 * ��һ���ַ�����ĳ�ַ���Ϊ�ָ������зָ�(�õ�ÿ����Ϊ�ַ������ַ�������).
	 *   @param  str  ���ָ����ַ���
	 *   @param  delimiter  �ָ���
	 *   @return  �ָ��Ľ��
	*/
	public static final String[] splitString(String str,char delimiter)
	{
		return splitString(str,0,delimiter);
	}
	
	/**
	 * ��һ���ַ�����ĳ�ַ���Ϊ�ָ������зָ���ȡ����ĳһ��.
	 *  <blockquote><pre>
	 *     String subtext = StringDist.subSplitString("AAAA|BBBB|CCCC|DDDDD",0,'|',2)
	 *     // subtext Ϊ "CCCC"
	 *   </pre></blockquote>
	 *   @param  str  ���ָ����ַ���
	 *   @param  istart ��ʼλ��
	 *   @param  delimiter  �ָ���
	 *   @param  index  �ָ�����Ӵ������±�
	 *   @return  �� index ���Ӵ�
	*/
	public static final String subSplitString(String str,int istart,char delimiter,int index)
	{

                if(str==null) return null;
		int sl = str.length();
		int i=istart,j = 0;
		for(;i<sl;)
		{
			int iend = str.indexOf(delimiter,i);
			if(iend<0)
				break;
			if(j++==index)  return str.substring(i,iend);
			i = iend+1;
		}
		return j++==index ? str.substring(i) : null;
	}
	/**
	 * ��һ���ַ�����ĳ�ַ���Ϊ�ָ������зָ���ȡ����ĳһ��.
	 *  <blockquote><pre>
	 *     String subtext = Utilities.splitString("AAAA,BBBB,CCCC,DDDDD",',',2)
	 *     // subtext Ϊ "CCCC"
	 *   </pre></blockquote>
	 *   @param  str  ���ָ����ַ���
	 *   @param  delimiter  �ָ���
	 *   @param  index  �ָ�����Ӵ������±�
	 *   @return  �� index ���Ӵ�
	*/
	public static final String subSplitString(String str,char delimiter,int index)
	{
		return subSplitString(str,0,delimiter,index);
	}

       /**
     * ���ַ���line ���е�oldString �滻ΪnewString.
     * @param line �����滻�����ַ���
     * @param oldString Ҫ���滻���ַ���
     * @param newString �滻�ɵ��ַ���
     * @return �滻�����line
     */
    public static final String replace( String line, String oldString, String newString ){
        int i=0;
        if ( ( i=line.indexOf( oldString, i ) ) >= 0 ) {
            char [] line2 = line.toCharArray();
	        char [] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while( ( i=line.indexOf( oldString, i ) ) > 0 ) {
                buf.append(line2, j, i-j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        return line;
    }
    //**************************************************************************************************
    //������������б��
	/**
	  * �ѹ�ϣ���е��������������б��
	  * @param strDefault
	  * @param hashTable �����ͼ�ֵ��Ӧ�����б���е�text �� value	  
	  * @return �ַ��� ��ʽ:<option value=����>��ֵ</option>
	  */
	public static final String getRecordStateString(String strDefault,Hashtable hTable){
		String strRecord="";
		String key,value;
		if(hTable!=null){
			Object[] object=hTable.keySet().toArray();
			//Ĭ��ѡ��һ��
			if(strDefault==null || strDefault.equals(""))
				strDefault="";	
			for(int i=hTable.size()-1;i>=0;i--){
				key=(String)object[i];	
				value=(String)hTable.get(object[i]);
				if(key.equalsIgnoreCase(strDefault))
					strRecord +="<option value='" + key + "' selected>" + value + "</option>"; 			
				else
					strRecord +="<option value='" + key + "'>" + value + "</option>";				
			}			
		}
		return strRecord;	
	}
	//******************************************************************************************************
	
	
			/**�ų�����������Ĵ���
			 * ��֤�ַ������ʱ�ĺϷ���
			 * ��Ҫ�����ַ���Ϊnullʱ�����Ϊ����
			 * ��Ҫ�����ַ������С�����ʱ��תΪ�Ϸ��ġ�\����
			 * */
 
			static public String escapeQuote(String input)
			{
			if ( input == null )
					return "";

				int length = input.length();
				StringBuffer sb = new StringBuffer(length);

				for ( int i = 0; i < length; i++ )
				{
						char character = input.charAt(i);
						if ( character == '\'' )
						sb.append('\'');
						sb.append(character);
				}
				return sb.toString();
			}
			

}