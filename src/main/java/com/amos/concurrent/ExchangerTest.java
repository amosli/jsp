package com.amos.concurrent;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 
* @ClassName: ExchangerTest 
* @Description: 线程间的数据交换Exchanger
* @author: amosli
* @email:hi_amos@outlook.com
* @date Apr 28, 2014 12:26:48 AM  
*/
public class ExchangerTest {
	public static void main(String[] args) {
		final Exchanger<String> exchanger = new Exchanger<String>();
	
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		
		//线程一
		newCachedThreadPool.execute(new Runnable() {
			public void run() {
				try {
					String data1="111";
					System.out.println("线程:"+Thread.currentThread().getName()+" 要换出去的数据为:"+data1);
					Thread.sleep(new Random().nextInt(1000));
					String exchange = exchanger.exchange(data1);
					System.out.println("线程:"+Thread.currentThread().getName()+" 换回来的数据为:"+exchange);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		//线程二
		newCachedThreadPool.execute(new Runnable() {
			public void run() {
				try {
					String data1="hi_amos";
					System.out.println("线程:"+Thread.currentThread().getName()+" 要换出去的数据为:"+data1);
					Thread.sleep(new Random().nextInt(1000));
					String exchange = exchanger.exchange(data1);
					System.out.println("线程:"+Thread.currentThread().getName()+" 换回来的数据为:"+exchange);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});
	}
}
