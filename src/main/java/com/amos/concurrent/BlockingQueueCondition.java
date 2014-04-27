package com.amos.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 
* @ClassName: BlockingQueueCondition 
* @Description: 在前面用Condition实现的同步通知的例子的基础上，改为用阻塞队列来实现。
第一个线程：A.take()……..B.put()
第二个线程：B.take()……..A.put()
* @author: amosli
* @email:hi_amos@outlook.com
* @date Apr 28, 2014 12:57:51 AM  
*/
public class BlockingQueueCondition {

	public static void main(String[] args) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		final Business3 business = new Business3();
		service.execute(new Runnable(){

			public void run() {
				for(int i=0;i<2;i++){
					business.sub();
				}
			}
			
		});
		
		for(int i=0;i<3;i++){
			business.main();
		}
	}

}

class Business3{
	BlockingQueue subQueue = new ArrayBlockingQueue(1);
	BlockingQueue mainQueue = new ArrayBlockingQueue(1);
	{
		try {
			mainQueue.put(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void sub(){
		try
		{
			mainQueue.take();
			for(int i=0;i<10;i++){
				System.out.println(Thread.currentThread().getName() + " : " + i);
			}
			subQueue.put(1);
		}catch(Exception e){

		}
	}
	
	public void main(){
		try
		{
			subQueue.take();
			for(int i=0;i<5;i++){
				System.out.println(Thread.currentThread().getName() + " : " + i);
			}
			mainQueue.put(1);
		}catch(Exception e){
		}		
	}
}
