package com.amos;

/**
 * @ClassName: EqualTest
 * @Description:Java中的equal和==的比较
 * @author: amosli
 * @email:amosli@infomorrow.com
 * @date Apr 10, 2014 1:14:10 AM
 */
public class EqualTest {
	public static void main(String[] args) {
		int a = 1;
		float b = 1.0f;
		System.out.println(a == b);// true
		String c = new String("hi_amos");
		String d = new String("hi_amos");
		System.out.println(c == d);// false
		System.out.println(c.equals(d));// true
	}
}
