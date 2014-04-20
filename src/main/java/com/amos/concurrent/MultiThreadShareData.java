package com.amos.concurrent;

public class MultiThreadShareData {
	private int j;
	public static void main(String[] args) {
		MultiThreadShareData multiThreadShareData = new MultiThreadShareData();
		for(int i=0;i<2;i++){
			new Thread(multiThreadShareData.new ShareData1()).start();//增加
			new Thread(multiThreadShareData.new ShareData2()).start();//减少
		}
	}
	//自增
	private synchronized void Inc(){
		j++;
		System.out.println(Thread.currentThread().getName()+" inc "+j);
	}
	//自减
	private synchronized void Dec(){
		j--;
		System.out.println(Thread.currentThread().getName()+" dec "+j);
	}
	
	class ShareData1 implements Runnable {
		public void run() {
			for(int i=0;i<5;i++){
				Inc();
			}
		}
	}
	class ShareData2 implements Runnable {
		public void run() {
			for(int i=0;i<5;i++){
				Dec();
			}
		}
	}
}
