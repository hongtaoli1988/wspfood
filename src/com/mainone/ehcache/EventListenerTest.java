package com.mainone.ehcache;

import junit.framework.TestCase;

/**
 * 用户可以自定义处理cacheEventHandler, 处理诸如元素放入cache的各种事件(放入,移除,过期等事件)
 * 
 */
public class EventListenerTest extends TestCase {
	public static void main(String args[]){
		
		String key = "person";
		System.out.println("bbbb===="+MyCacheManager.getInstance().get("Test", key));
		
		MyCacheManager.getInstance().put("Test", key, "yes");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
		//assertNull(MyCacheManager.getInstance().get("Test", key));
		System.out.println("aaaa===="+MyCacheManager.getInstance().get("Test", key));

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("aaaa===="+MyCacheManager.getInstance().get("Test", key));
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("aaaa===="+MyCacheManager.getInstance().get("Test", key));
	}
}
