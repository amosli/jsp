package com.amos.concurrent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
/**
 * @ClassName: TraditionalTimerTest
 * @Description: 传统的线程计时器
 * @author: amosli
 * @email:hi_amos@outlook.com
 * @date Apr 17, 2014 12:54:32 AM
 */
public class TraditionalTimerTest {
	private static int count = 0 ;
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		//第一次是在1秒后启动,然后每隔3秒启动一次
//		new Timer().schedule(new TimerTask() {
//			@Override
//			public void run() {
//				System.out.println("bomb...");
//			}
//		}, 1000, 3000);
		//间隔2秒和4秒分开运行
		class myTimerTask extends TimerTask{
			@Override
			public void run() {
				count  = (count+1)%2;
				if(count==1){
					System.out.println("mybombing_2000....");
					new Timer().schedule(new myTimerTask(), 2000);
				}else {
					System.out.println("mybombing_4000....");
					new Timer().schedule(new myTimerTask(), 4000);
				}
			}
		}
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				new Timer().schedule(new myTimerTask(), 2000);
			}
		}, 2000);		
		//输出时间
		while (true) {
			System.out.println(new Date().getSeconds());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}
}



