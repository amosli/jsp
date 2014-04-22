package com.amos.concurrent;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/** 
* @ClassName: ThreadPoolTest 
* @Description: java5中的线程池
* @author: amosli
* @email:hi_amos@outlook.com
* @date Apr 21, 2014 2:13:27 AM  
*/
public class ThreadPoolTest {

	public static void main(String[] args) {
//		ExecutorService executorService = Executors.newFixedThreadPool(3);//设置固定的线程数
//		ExecutorService executorService = Executors.newCachedThreadPool();//创建带缓存的线程池,自动分配线程数
		ExecutorService executorService = Executors.newSingleThreadExecutor();//创建单一线程池,如何实现线程死了,马上自动重新建一个
		Executors.newScheduledThreadPool(3).schedule(new Runnable() {//创建一个有3个线程数的线程池,指定3秒后执行run方法里的内容
			public void run() {
				System.out.println("bomb.....");
			}
		}, 3, TimeUnit.SECONDS	);//隔3秒
		 
		for (int i = 0; i < 10; i++) {//创建10个任务
			final int task = i;
			executorService.execute(new Runnable() {//执行任务的线程
				public void run() {
					for (int i = 0; i < 10; i++) {//循环执行10个次
						System.out.println(Thread.currentThread().getName() + "  loop" + i + " task is" + task);
					}
				}
			});
		}
		System.out.println("all of 10 task has committed!!");
		List<Runnable> shutdownNow = executorService.shutdownNow();//立即关闭线程,并返回未执行的线程
		System.out.println("closed thread size:"+shutdownNow.size());
		for(Runnable a:shutdownNow){
			System.out.println(a);
		}
	}
}
