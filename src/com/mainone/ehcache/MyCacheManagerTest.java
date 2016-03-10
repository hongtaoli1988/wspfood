package com.mainone.ehcache;

import junit.framework.TestCase;

public class MyCacheManagerTest extends TestCase
{
	public static final String CACHE_NAME = "Test";

	public void testGetInstance()
	{
		MyCacheManager cacheManager1 = MyCacheManager.getInstance();
		assertNotNull(cacheManager1);
		MyCacheManager cacheManager2 = MyCacheManager.getInstance();
		assertNotNull(cacheManager2);
		assertSame(cacheManager1, cacheManager2);		
	}
	
	public void testPutGet()
	{
		 Person p201 = (Person)MyCacheManager.getInstance().get("Test", "zl");
	        System.out.println("p201==="+p201);
	        
        String key20 = "zl";
        String key30 = "wd";
        Person person20 = new Person("zlzlzlwdwdwd", 20);
        Person person30 = new Person("person30", 30);
        MyCacheManager.getInstance().put(CACHE_NAME, key20, person20);
        MyCacheManager.getInstance().put(CACHE_NAME, key30, person30);
        Person p20 = (Person)MyCacheManager.getInstance().get("Test", "zl");
        System.out.println("p20==="+p20);
        Person p30 = (Person)MyCacheManager.getInstance().get("Test", key30);
        log(p20); 
        assertSame(person20, p20);
        assertSame(person30, p30);
        log(System.getProperty("java.io.tmpdir"));        
	}
	
	public static void main(String args[]){
		MyCacheManagerTest aa = new MyCacheManagerTest();
		aa.testPutGet();
	}
	
	private void log(Object obj)
	{
		System.out.println(obj);
	}
}
