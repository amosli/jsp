package com.amos.concurrent;

/** 
* @ClassName: TraditionalThread 
* @Description: 创建线程的几种方式
* @author: amosli
* @email:amosli@infomorrow.com
* @date Apr 15, 2014 12:49:33 AM  
*/
public class TraditionalThread {
	public static void main(String[] args) {
//		//方式1:
//		Thread thread = new Thread(){
//			@Override
//			public void run() {
//				while(true){
//					try {
//						Thread.sleep(500);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					System.out.println("1:"+Thread.currentThread().getName());
//				}
//			}
//		};
//		thread.start();
//		
//		//方式2:
//		Thread thread2 = new Thread(new Runnable() {
//			public void run() {
//				while(true){
//					try {
//						Thread.sleep(500);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//					System.out.println("2:"+Thread.currentThread().getName());
//				}
//			}
//		});
//		thread2.start();
		
		
		//方式3:
		new Thread(){
			public void run() {
				while(true){
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("thread:"+Thread.currentThread().getName());
				}
			};
		}.start();

		//方式4:
		new Thread(new Runnable() {
			public void run() {
				while(true){
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("runnable:"+Thread.currentThread().getName());
				}
			}
		}).start();
	}
}
