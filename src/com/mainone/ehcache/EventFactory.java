package com.mainone.ehcache;

import java.util.Properties;

import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;

/**
 * 用户可以自定义处理cacheEventHandler, 处理诸如元素放入cache的各种事件(放入,移除,过期等事件)
 * 
 */
public class EventFactory extends CacheEventListenerFactory {
	@Override
	public CacheEventListener createCacheEventListener(Properties properties) {
		// TODO Auto-generated method stub
		return new CacheEvent();
	}

}
