package com.mainone.ehcache;

import java.util.Properties;

import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;

/**
 * �û������Զ��崦��cacheEventHandler, ��������Ԫ�ط���cache�ĸ����¼�(����,�Ƴ�,���ڵ��¼�)
 * 
 */
public class EventFactory extends CacheEventListenerFactory {
	@Override
	public CacheEventListener createCacheEventListener(Properties properties) {
		// TODO Auto-generated method stub
		return new CacheEvent();
	}

}
