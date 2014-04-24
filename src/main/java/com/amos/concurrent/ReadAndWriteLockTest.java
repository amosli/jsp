package com.amos.concurrent;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class ReadAndWriteLockTest {
	public static void main(String[] args) {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
		newFixedThreadPool.execute(new Runnable() {
			public void run() {
				for(int i=0;i<10;i++){
					CachedData cachedData = new ReadAndWriteLockTest().new CachedData();
					cachedData.processCachedData();
				}
			}
		});
		
	}

	class CachedData {
		Object data;
		volatile boolean cacheValid;
		final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

		void processCachedData() {
			rwl.readLock().lock();
			if (!cacheValid) {
				// Must release read lock before acquiring write lock
				rwl.readLock().unlock();
				rwl.writeLock().lock();
				try {
					// Recheck state because another thread might have
					// acquired write lock and changed state before we did.
					if (!cacheValid) {
						data = "hi_amos";
						cacheValid = true;
					}
					// Downgrade by acquiring read lock before releasing write
					// lock
					rwl.readLock().lock();
				} finally {
					rwl.writeLock().unlock(); // Unlock write, still hold read
				}
			}

			try {
				use(data);
			} finally {
				rwl.readLock().unlock();
			}
		}

		private void use(Object data2) {
			System.out.println(new Date().getSeconds());
			System.out.println("data:" + data2);
		}
	}
}
