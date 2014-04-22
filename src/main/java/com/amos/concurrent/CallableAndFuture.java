package com.amos.concurrent;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ClassName: CallableAndFuture
 * @Description: 多线程中的Callable和Future学习
 * @author: amosli
 * @email:hi_amos@outlook.com
 * @date Apr 22, 2014 12:07:26 AM
 */
public class CallableAndFuture {

	public static void main(String[] args) throws Exception, ExecutionException {
		ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
		Future<String> future = newSingleThreadExecutor.submit(new Callable<String>() {
			public String call() throws Exception {
				Thread.sleep(20);
				return "hi,amos";
			}
		});

		// System.out.println("future:"+future.get(1,TimeUnit.MILLISECONDS));//等待指定的时间
		System.out.println("future:" + future.get());

//		// CompletionService,可以用麦子收割来比喻,种了10亩地的麦子,哪一块先成熟先收割哪一块
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(newFixedThreadPool);
		for(int i=0;i<11;i++){//创建10个任务
		final int task=i;
		completionService.submit(new Callable<Integer>() {//提交任务
			public Integer call() throws Exception {
				Thread.sleep(new Random().nextInt(3000));//最多3秒
				return task;
			}
		});
		}
		//take
		for(int i=0;i<11;i++){
			System.out.println("已完成的任务:"+completionService.take().get());
		}
	}

}
