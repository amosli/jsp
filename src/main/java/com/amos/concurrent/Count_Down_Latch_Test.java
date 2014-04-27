package com.amos.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 
* @ClassName: Count_Down_Latch_Test 
* @Description: 倒计时学习
* @author: amosli
* @email:hi_amos@outlook.com
* @date Apr 27, 2014 11:51:43 PM  
*/
public class Count_Down_Latch_Test {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final CountDownLatch countdownOrder = new CountDownLatch(1);// an order
		final CountDownLatch countdownAnwser = new CountDownLatch(3);// anwser
		for (int i = 0; i < 3; i++) {
			Runnable runnable = new Runnable() {
				public void run() {
					try {
						countdownOrder.await();
						System.out.println("线程" + Thread.currentThread().getName() + " 正准备接受命令");
						System.out.println("线程"+Thread.currentThread().getName()+" 已经接受命令!");
						Thread.sleep(new Random().nextInt(1000));
						System.out.println("线程"+Thread.currentThread().getName()+" 回应处理结果!");
						countdownAnwser.countDown();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			executorService.execute(runnable);//启动线程池
		}
		
		
		
		try {
			Thread.sleep(new Random().nextInt(1000));
			System.out.println("线程"+Thread.currentThread().getName()+" 即将下达命令!!");
			countdownOrder.countDown();
			System.out.println("线程"+Thread.currentThread().getName()+" 已经下达命令,正在等待返回结果!");
			countdownAnwser.await();
			System.out.println("线程"+Thread.currentThread().getName()+" 已经收到所有处理结果!");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

	}
}
