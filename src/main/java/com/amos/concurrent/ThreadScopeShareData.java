package com.amos.concurrent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * @ClassName: ThreadScopeShareData
 * @Description: 
 *               线程内的数据共享与对象独立,举例:张三给李四转钱,开启A线程去执行转钱这个动作,刚好同时王五给赵六转钱,开启B线程去执行转钱,因为是调用的同样一个
 *               动作或者说对象,所以如果不能保证线程间的对象独立,那么很有可能发生,张三给李四转钱时把王五转给赵六的转钱一块提交了,
 *               而王五转钱整个动作还未完成,那么就造成了转钱错误
 *               所以线程间一方面要保证数据的共享,另一方面要保证对象的对立.下面的例子用的是Map对象将数据实现共享
 * @author: amosli
 * @email:hi_amos@outlook.com
 * @date Apr 20, 2014 6:19:02 PM
 */
public class ThreadScopeShareData {
	public static int data = 0;
	public static Map<Object, Integer> map = new HashMap<Object, Integer>();

	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {
				public void run() {
					data = new Random().nextInt();//给data设值,
					System.out.println(Thread.currentThread().getName() + " set data:" + data);
					map.put(Thread.currentThread(), data);//将值按照Thread去设值,取的时候也按Thread去取,以保证数据的共享,但又保证了对象的独立.
					new A().get();
					new B().get();
				}
			}).start();
		}
	}

	static class A {//这里A和B的方法虽然是一样的,这里是想表示有可能调用不同的对象去执行数据操作
		public int get() {
			data = map.get(Thread.currentThread());
			System.out.println("a from thread:" + Thread.currentThread().getName() + " is " + data);
			return data;
		}
	}

	static class B {
		public int get() {
			data = map.get(Thread.currentThread());
			System.out.println("b from thread:" + Thread.currentThread().getName() + " is " + data);
			return data;
		}
	}

}
