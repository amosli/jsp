package com.amos;

/** 
* @ClassName: Singleton 
* @Description: Java中最常见的单例模式
* @author: amosli
* @email:amosli@infomorrow.com
* @date Apr 10, 2014 1:12:00 AM  
*/
public class Singleton {
	public static Singleton instance=new Singleton();
	//饱汉式
	public static Singleton getSingleton() {
		return instance;
	}
	//将构造方法私有化
	private Singleton() {
	}
	
	public static void main(String[] args) {
		
		Singleton singleton = Singleton.getSingleton();
		Singleton singleton2 = Singleton.getSingleton();
		System.out.println(singleton==singleton2);//true
		
		String string = new String("hi_amos");
		String string2 = new String("hi_amos");
		System.out.println(string==string2);//false
	}
}
