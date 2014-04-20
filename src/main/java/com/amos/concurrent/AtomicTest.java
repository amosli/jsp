package com.amos.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
	
	static AtomicInteger atomicInteger = new AtomicInteger(1);
	public static void main(String[] args) {
		for( int i=0;i<100;i++){
			new Thread(new Runnable() {
				public void run() {
					System.out.println(Thread.currentThread().getName()+" add "+atomicInteger.addAndGet(1));
				}
			}).start();
			new Thread(new Runnable() {
				public void run() {
					System.out.println(Thread.currentThread().getName()+" sub  "+atomicInteger.addAndGet(-1));
				}
			}).start();
		}
	}
}
