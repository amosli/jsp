package com.amos.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 
* @ClassName: ConditionTest 
* @Description: 官方提供的例子
* @author: amosli
* @email:hi_amos@outlook.com
* @date Apr 24, 2014 12:40:58 AM  
*/
public class ConditionTest {
	public static void main(String[] args) throws Exception {
		BoundedBuffer buffer = new BoundedBuffer();
		buffer.put("hi_amos");
		for(int i=0;i<3;i++){
			buffer.put(i);
			System.out.println("take:"+buffer.take());
		}
		
	}
	static  class BoundedBuffer {
		   final Lock lock = new ReentrantLock();
		   final Condition notFull  = lock.newCondition(); 
		   final Condition notEmpty = lock.newCondition(); 

		   final Object[] items = new Object[100];
		   int putptr, takeptr, count;
		   
		   //设值
		   public void put(Object x) throws InterruptedException {
		     lock.lock();
		     try {
		       while (count == items.length)
		         notFull.await();
		       items[putptr] = x;
		       if (++putptr == items.length) putptr = 0;
		       ++count;
		       notEmpty.signal();
		     } finally {
		       lock.unlock();
		     }
		   }

		   //取值
		   public Object take() throws InterruptedException {
		     lock.lock();
		     try {
		       while (count == 0)
		         notEmpty.await();
		       Object x = items[takeptr];
		       if (++takeptr == items.length) takeptr = 0;
		       --count;
		       notFull.signal();
		       return x;
		     } finally {
		       lock.unlock();
		     }
		   }
		 }
}
