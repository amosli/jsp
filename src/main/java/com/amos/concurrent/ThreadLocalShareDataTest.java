package com.amos.concurrent;
import java.util.Random;
/**
 * @ClassName: ThreadLocalShareData
 * @Description: 下面的例子用的是ThreadLocal对象将数据实现共享,封装复杂数据对象
 * @author: amosli
 * @email:hi_amos@outlook.com
 * @date Apr 20, 2014 6:19:02 PM
 */
public class ThreadLocalShareDataTest {
	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {
				public void run() {
					int data = new Random().nextInt();//给data设值,
					System.out.println(Thread.currentThread().getName() + " set data:" + data);
					MyThreadData.getMyThreadData().setName("name"+data);
					MyThreadData.getMyThreadData().setAge(data);
					new A().get();
					new B().get();
				}
			}).start();
		}
	}
	static class A {//这里A和B中的方法是一样的,可以只看一个
		public void get() {
			MyThreadData myThreadData = MyThreadData.getMyThreadData();
			int data =myThreadData.getAge();
			System.out.println("a from thread:" + Thread.currentThread().getName() + " age: " + data+"   name:"+myThreadData.getName());
		}
	}
	static class B{
		public void get() {
			MyThreadData myThreadData = MyThreadData.getMyThreadData();
			int data =myThreadData.getAge();
			System.out.println("b from thread:" + Thread.currentThread().getName() + "  age: " + data+"   name:"+myThreadData.getName());
		}
	}
	//自定义对象
	static class MyThreadData {
		private static ThreadLocal<MyThreadData> mapLocal = new ThreadLocal<MyThreadData>();
		private MyThreadData(){}
		//单例模式,获取数值
		public static MyThreadData getMyThreadData(){
			MyThreadData instance = mapLocal.get();
			if(instance==null){
				instance = new MyThreadData();
				mapLocal.set(instance);
			}
			return instance;
		}
		//name,age setter/getter
		private String name ;
		private Integer age;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
	}
	
}
