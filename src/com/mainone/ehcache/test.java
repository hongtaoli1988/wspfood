/*
 * Created on Jan 28, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.mainone.ehcache;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @author lichun
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class test 
{
	private static Cache sampleCache = null; 
	public static void main(String[] args) { 
		init(); 
		test(); 
		test2();
	} 
	
	private static void test() { 
		
			//д�뻺��          
			sampleCache.put(new Element("zl", "wd"));    
			sampleCache.put(new Element("xxdf", "wd"));     
			//��ӡ��ǰ���������ֵ
			System.out.println("1===="+sampleCache.getKeys()); 
			//��ȡ����            
			Element e = sampleCache.get("zl"); 
			System.out.println("2===="+e.getValue()); 

		
		//��ӡ����ͳ�� 
		System.out.println("3===="+sampleCache.getStatistics()); 
	} 
	private static void test2() { 
		//��ȡ����            
		Element e = sampleCache.get("zl"); 
		System.out.println("4===="+e.getValue()); 
	} 
	
	private static void init() {
		CacheManager manager = CacheManager.create();
		//manager.addCache("sample"); //�Ѿ��������ļ�������� 
		sampleCache = manager.getCache("Test"); 
	} 
		
}

