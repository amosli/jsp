package com.amos.concurrent;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

class BeeperControl {
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public void beepForAnHour() {
		final Runnable beeper = new Runnable() {
			@SuppressWarnings("deprecation")
			public void run() {
			System.out.println(new Date().getSeconds());//计时
			System.out.println("beep");
			}
		};
		@SuppressWarnings("rawtypes")
		final ScheduledFuture beeperHandle = scheduler.scheduleAtFixedRate(beeper, 10, 2, SECONDS);//任务,10s delay,2s/次,单位秒 
		
		scheduler.schedule(new Runnable() {
			public void run() {
				beeperHandle.cancel(true);//撤消任务
			}
		}, 60 * 60, SECONDS);//60*60s=60min=1h
	}
}
/** 
* @ClassName: Beep 
* @Description: 每隔2秒执行一次任务
* @author: amosli
* @email:hi_amos@outlook.com
* @date Apr 21, 2014 11:56:12 PM  
*/
public class Beep{
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		System.out.println(new Date().getSeconds());//计时
		new BeeperControl().beepForAnHour();
	}
}