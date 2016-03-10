package com.mainone.util;

import java.util.Hashtable;
/**
 * <p>Title: 公共基类 StringDist</p>
 * <p>Description: 字符串处理类， 将一个字符串从某位置开始以某字符作为分隔符进行分隔(得到每段作为字符串的字符串数组)</p>
 * Copyright:    Copyright (c) 2004-07-17
 * Company: mainOne
 * @author 方克锐
 * @version 1.0
 */
public class StringDist {
	 /**
	 *   将一个字符串从某位置开始以某字符作为分隔符进行分隔(得到每段作为字符串的字符串数组).
	 *  <blockquote><pre>
	 *     String list[] = StringDist.splitString("AAAA,BBBB,CCCC,DDDDD",0,',')
	 *     // list 为  { "AAAA","BBBB","CCCC","DDDD" }
	 *   </pre></blockquote>
	 *   @param  str  被分隔的字符串
	 *   @param  istart 开始位置
	 *   @param  delimiter  分隔符
	 *   @return  分隔的结果
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
	 * 将一个字符串以某字符作为分隔符进行分隔(得到每段作为字符串的字符串数组).
	 *   @param  str  被分隔的字符串
	 *   @param  delimiter  分隔符
	 *   @return  分隔的结果
	*/
	public static final String[] splitString(String str,char delimiter)
	{
		return splitString(str,0,delimiter);
	}
	
	/**
	 * 将一个字符串以某字符作为分隔符进行分隔后取其中某一段.
	 *  <blockquote><pre>
	 *     String subtext = StringDist.subSplitString("AAAA|BBBB|CCCC|DDDDD",0,'|',2)
	 *     // subtext 为 "CCCC"
	 *   </pre></blockquote>
	 *   @param  str  被分隔的字符串
	 *   @param  istart 开始位置
	 *   @param  delimiter  分隔符
	 *   @param  index  分隔后的子串数组下标
	 *   @return  第 index 个子串
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
	 * 将一个字符串以某字符作为分隔符进行分隔后取其中某一段.
	 *  <blockquote><pre>
	 *     String subtext = Utilities.splitString("AAAA,BBBB,CCCC,DDDDD",',',2)
	 *     // subtext 为 "CCCC"
	 *   </pre></blockquote>
	 *   @param  str  被分隔的字符串
	 *   @param  delimiter  分隔符
	 *   @param  index  分隔后的子串数组下标
	 *   @return  第 index 个子串
	*/
	public static final String subSplitString(String str,char delimiter,int index)
	{
		return subSplitString(str,0,delimiter,index);
	}

       /**
     * 把字符串line 所有的oldString 替换为newString.
     * @param line 查找替换的主字符串
     * @param oldString 要被替换的字符串
     * @param newString 替换成的字符串
     * @return 替换后的新line
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
    //设计生成下拉列表框
	/**
	  * 把哈希表中的数据生成下拉列表框
	  * @param strDefault
	  * @param hashTable 键名和键值对应下拉列表框中的text 和 value	  
	  * @return 字符串 格式:<option value=键名>键值</option>
	  */
	public static final String getRecordStateString(String strDefault,Hashtable hTable){
		String strRecord="";
		String key,value;
		if(hTable!=null){
			Object[] object=hTable.keySet().toArray();
			//默认选第一个
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
	
	
			/**排除单引号引起的错误
			 * 保证字符串入库时的合法性
			 * 当要入库的字符串为null时，入库为“”
			 * 当要入库的字符串含有“’”时，转为合法的“\’”
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