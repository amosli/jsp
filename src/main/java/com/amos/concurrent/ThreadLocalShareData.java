package com.amos.concurrent;
import java.util.Random;
/**
 * @ClassName: ThreadLocalShareData
 * @Description: 下面的例子用的是ThreadLocal对象将数据实现共享
 * @author: amosli
 * @email:hi_amos@outlook.com
 * @date Apr 20, 2014 6:19:02 PM
 */
public class ThreadLocalShareData {
	private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {
				public void run() {
					int data = new Random().nextInt();//给data设值,
					System.out.println(Thread.currentThread().getName() + " set data:" + data);
					threadLocal.set(data);//使用ThreadLocal来设值
					new A().get();
					new B().get();
				}
			}).start();
		}
	}
	static class A {//这里A和B的方法虽然是一样的,这里是想表示有可能调用不同的对象去执行数据操作
		public int get() {
			int data = threadLocal.get();
			System.out.println("a from thread:" + Thread.currentThread().getName() + " is " + data);
			return data;
		}
	}
	static class B {
		public int get() {
		int data = threadLocal.get();	
			System.out.println("b from thread:" + Thread.currentThread().getName() + " is " + data);
			return data;
		}
	}
}
