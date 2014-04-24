package com.amos.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ThreadSynchronizedConnect
 * @Description:实现线程间的通信,需求:主线程循环3次后,子线程2循环2次,子线程3循环3次,然后主线程接着循环3次,如此循环3次.A->B->C---A->B->C---A->B->C
 * @author: amosli
 * @email:hi_amos@outlook.com
 * @date Apr 20, 2014 4:39:44 PM
 */
public class ThreeConnect {

	public static void main(String[] args) {
		final Business business = new Business();
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 3; i++) {
					business.sub2(i);
				}
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 3; i++) {
					business.sub3(i);
				}
			}
		}).start();

		for (int i = 0; i < 3; i++) {
			business.main(i);
		}
	}

	static class Business {
		Lock lock = new ReentrantLock();
		Condition conditionmain = lock.newCondition();
		Condition conditionsub2 = lock.newCondition();
		Condition conditionsub3 = lock.newCondition();

		private int current = 1;

		// 子方法2
		public void sub2(int i) {
			lock.lock();
			try {
				if (current != 2) {// 如果不为true,将等待,Blocked状态
					try {
						conditionsub2.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (int j = 0; j < 2; j++) {
					System.out.println("sub2 thread:" + j + "  loop:" + i);
				}
				current = 3;
				conditionsub3.signal();// 唤醒3
			} finally {
				lock.unlock();
			}
		}

		// 子方法3
		public void sub3(int i) {
			lock.lock();
			try {
				if (current != 3) {// 如果不为true,将等待,Blocked状态
					try {
						conditionsub3.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				for (int j = 0; j < 2; j++) {
					System.out.println("sub3 thread:" + j + "  loop:" + i);
				}
				current = 1;
				conditionmain.signal();
			} finally {
				lock.unlock();
			}
		}

		// 主方法
		public void main(int i) {
			lock.lock();
			try {
				if (current != 1) {
					try {
						conditionmain.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				for (int j = 0; j < 3; j++) {
					System.out.println("main thread:" + j + "  loop:" + i);
				}
				current = 2;
				conditionsub2.signal();
			} finally {
				lock.unlock();
			}

		}

	}

}
