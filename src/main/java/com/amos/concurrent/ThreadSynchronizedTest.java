package com.amos.concurrent;

/** 
* @ClassName: ThreadSynchronizedTest 
* @Description: 多线程并发之线程安全
* @author: amosli
* @email:hi_amos@outlook.com
* @date Apr 20, 2014 2:44:29 PM  
*/
public class ThreadSynchronizedTest {
	public static void main(String[] args) {
		new ThreadSynchronizedTest().init();
	}
	
	private void init() {
		final OutPuter outPuter = new OutPuter();
		//新建一个线程
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					//休息10ms
					try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
					outPuter.output("hi_amos");//输出
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			public void run() {
				while (true) {
				try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
				outPuter.output("amosli");
				}
			}
		}).start();
	}

	static class OutPuter {
		//输出name,逐个字节读取,并输出
		public synchronized void output(String name) {
			int length = name.length();
				for (int i = 0; i < length; i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
		}
		public void output2(String name) {
				int length = name.length();
				synchronized (OutPuter.class) {
					for (int i = 0; i < length; i++) {
						System.out.print(name.charAt(i));
					}
					System.out.println();	
				}
		}
		public synchronized static void output3(String name) {
			int length = name.length();
				for (int i = 0; i < length; i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();	
		}
	}
}
