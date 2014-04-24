package com.amos.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/** 
* @ClassName: CacheData 
* @Description: 设计一个缓存系统
* @author: amosli
* @email:hi_amos@outlook.com
* @date Apr 23, 2014 1:40:06 AM  
*/
public class CacheData {
	public static void main(String[] args) {
		
	}
	private Map<String, Object> cache = new HashMap<String, Object>();
//	
//	public synchronized Object getData(String key){
//		Object object = cache.get(key);
//		if (object==null) {
//			object = "1323";//实际是去queryDB();
//		}
//		return object;
//	}
	//
	private ReadWriteLock rwl = new ReentrantReadWriteLock();
	public synchronized Object getData(String key){
		rwl.readLock();//read lock
		Object object = cache.get(key);
		try{
		if (object==null) {
			rwl.readLock().unlock();//释放锁
			rwl.writeLock().lock();//对写加锁
			try{
				object = "1323";//实际是去queryDB();
			}finally{
				rwl.writeLock().unlock();
			}
		}
		}finally{
			rwl.readLock().unlock();
		}
		return object;
	}
	
}	
