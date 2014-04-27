package com.amos.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
	public static void main(String[] args) {
		try {
			new CountDownLatchTest().new Driver().main();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	class Driver { // ...
		void main() throws InterruptedException {
			CountDownLatch startSignal = new CountDownLatch(1);
			CountDownLatch doneSignal = new CountDownLatch(3);

			for (int i = 0; i < 3; ++i)
				// create and start threads
			new Thread(new worker(startSignal, doneSignal)).start();
			dosomethingelse(); // don't let run yet
			startSignal.countDown(); // let all threads proceed
			dosomethingelse();
			doneSignal.await(); // wait for all to finish
		}

		private void dosomethingelse() {
			System.out.println("dosomethingelse...");
		}
	}

	class worker implements Runnable {
		private final CountDownLatch startsignal;
		private final CountDownLatch donesignal;

		worker(CountDownLatch startsignal, CountDownLatch donesignal) {
			this.startsignal = startsignal;
			this.donesignal = donesignal;
		}

		public void run() {
			try {
				startsignal.await();
				dowork();
				donesignal.countDown();
			} catch (Exception ex) {
			} // return;
		}

		void dowork() {
			System.out.println("dowork....");
		}
	}

}
