package com.amos.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ConditionConnect
 * @Description:用condition替代wait,notify实现线程间的通信,需求:子线程循环10次,主线程循环100次,这样间隔循环50次.
 * @author: amosli
 * @email:hi_amos@outlook.com
 * @date Apr 24, 2014 12:07:23 AM
 */
public class ConditionConnect {

	public static void main(String[] args) {
		final Business business = new Business();
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 5; i++) {
					business.sub(i);
				}
			}
		}).start();

		for (int i = 0; i < 5; i++) {
			business.main(i);
		}
	}

	/*
	 * 经验:要用到共同数据(包括同步锁)的若干方法,应该归在同一个类身上,这样方便实现,高类聚和程序的健状性上.
	 */
	static class Business {
		private boolean is_sub = true;
		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();

		// 子方法
		public void sub(int i) {
			lock.lock();
			try {
				while (!is_sub) {// 如果不为true,将等待,Blocked状态
					try {
						// this.wait();
						condition.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				for (int j = 0; j < 2; j++) {
					System.out.println("sub thread:" + j + "  loop:" + i);
				}
				is_sub = false;
				// this.notify();//唤醒正在等待的线程
				condition.signal();
			} finally {
				lock.unlock();
			}

		}

		// 主方法
		public void main(int i) {
			lock.lock();
			try {
				while (is_sub) {
					try {
						// this.wait();
						condition.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				for (int j = 0; j < 10; j++) {
					System.out.println("main thread:" + j + "  loop:" + i);
				}
				is_sub = true;
				// this.notify();
				condition.signal();
			} finally {
				lock.unlock();
			}

		}
	}

}
