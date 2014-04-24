package com.amos.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/** 
* @ClassName: SemaPhoreTest 
* @Description: 线程通信中的"信号灯"
* @author: amosli
* @email:hi_amos@outlook.com
* @date Apr 25, 2014 12:06:22 AM  
*/
public class SemaPhoreTest {
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		
		final Semaphore semaphore=new Semaphore(3);
		for(int i=0;i<10;i++){
			threadPool.execute(new Runnable() {
				public void run() {
					try {
						semaphore.acquire();//获取一个可用的permits
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("线程 " + Thread.currentThread().getName()+" 已进入.  " + "目前已经有"+(3-semaphore.availablePermits())+" 个线程进入");
					try {
						Thread.sleep(new Random().nextInt(1000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("线程 "+Thread.currentThread().getName()+ " 即将离开...");
					semaphore.release();//释放一个线程
					System.out.println("线程 "+Thread.currentThread().getName()+ " 已离开.  当前有"+(3-semaphore.availablePermits())+"并发");
				}
			});
		}
	}
}
