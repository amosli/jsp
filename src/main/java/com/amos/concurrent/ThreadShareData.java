package com.amos.concurrent;

/**
 * @ClassName: ThreadShareData
 * @Description: 数据共享的几种方式
 * @author: amosli
 * @email:hi_amos@outlook.com
 * @date Apr 21, 2014 12:00:11 AM
 */
public class ThreadShareData {

	public static void main(String[] args) {
		final ShareData1 shareData1 = new ShareData1();
		new Thread(new MyRunnable1(shareData1)).start();
		new Thread(new MyRunnable1(shareData1)).start();

		new Thread().start();
		
		new Thread(new Runnable() {
			public void run() {
				shareData1.increment();
			}
		}).start();
		new Thread(new Runnable() {
			
			public void run() {
				shareData1.decrement();
			}
		}).start();
	}
	
	static class MyRunnable1 implements Runnable{
		private ShareData1 shareData1;
		public void run() {
		}
		public MyRunnable1(ShareData1 shareData1){
			this.shareData1 = shareData1;
		}
	}
	static class MyRunnable2 implements Runnable{
		private ShareData1 shareData1;
		public void run() {
		}
		public MyRunnable2(ShareData1 shareData1){
			this.shareData1 = shareData1;
		}
	}
	
	static class ShareData1 {
		/*implements Runnable {
		public int count = 100;
		public void run() {
			count--;
			System.out.println("run:"+count);
		}*/
		
		
		
		public synchronized void increment() {
			int count=100;
			while (true) {
				count++;
				System.out.println("count++:" + count);
			}
		}

		public synchronized void decrement() {
			int count=100;
			while (true) {
				count--;
				System.out.println("count--:" + count);
			}
		}
	}

}
