package com.amos.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: LockTest
 * @Description: Lock学习
 * @author: amosli
 * @email:hi_amos@outlook.com
 * @date Apr 22, 2014 1:48:36 AM
 */
public class LockTest {
	public static void main(String[] args) {
		new LockTest().init();
	}

	private void init() {
		final OutPuter outPuter = new OutPuter();
		// 新建一个线程
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					// 休息10ms
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outPuter.output("hi_amos");// 输出
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outPuter.output("amosli");
				}
			}
		}).start();
	}

	static class OutPuter {
		// 方式1:使用synchronized关键字
		// public synchronized void output(String name) {
		// int length = name.length();
		// for (int i = 0; i < length; i++) {
		// System.out.print(name.charAt(i));
		// }
		// System.out.println();
		// }

		// 方式2:使用Lock锁
		Lock lock = new ReentrantLock();
		public void output(String name) {
			lock.lock();// 加锁
			int length = name.length();
			// 输出name,逐个字节读取,并输出
			try {
				for (int i = 0; i < length; i++) {
					System.out.print(name.charAt(i));
				}
				System.out.println();
			} finally {
				lock.unlock();// 解锁
			}
		}
	}
}
