package com.amos.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/** 
* @ClassName: BlockingSample 
* @Description: 生产者消费者模型,阻塞,只有生产好了,才能去消费
* @author: amosli
* @email:hi_amos@outlook.com
* @date Apr 28, 2014 1:44:07 AM  
*/
public class BlockingSample {
	public static void main(String[] args) {
		new BlockingSample().new Setup().main();
	}
	class Producer implements Runnable {
		   private final BlockingQueue queue;
		   Producer(BlockingQueue q) { queue = q; }
		   public void run() {
		     try {
		       while (true) { queue.put(produce());
		       System.out.println(Thread.currentThread().getName()+" 现在正在生产!");
		       }
		     } catch (Exception ex) { ex.printStackTrace();}
		   }
		   String produce() { System.out.println("produce now ....");
			return "produce"; }
		 }

		 class Consumer implements Runnable {
		   private final BlockingQueue queue;
		   Consumer(BlockingQueue q) { queue = q; }
		   public void run() {
		     try {
		       while (true) { consume(queue.take()); 
		       System.out.println(Thread.currentThread().getName()+" 现在正在消费!");
		       }
		     } catch (InterruptedException ex) { ex.printStackTrace();}
		   }
		   void consume(Object x) {System.out.println("consume...");}
		 }

		 class Setup {
		   void main() {
		     BlockingQueue q = new ArrayBlockingQueue<String>(3);
		     Producer p = new Producer(q);
		     Consumer c1 = new Consumer(q);
		     Consumer c2 = new Consumer(q);
		     new Thread(p).start();
		     new Thread(c1).start();
		     new Thread(c2).start();
		   }
		 }
		 
}
