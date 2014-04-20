package com.amos.concurrent;
class Account {
	/*
	 * 定义一个ThreadLocal类型的变量,该变量是一个线程局部变量
	 */
	private ThreadLocal<String> name = new ThreadLocal<String>();

	// 定义一个初始化name属性的构造器
	public Account(String str) {
		this.name.set(str);
		// 下面的代码用于访问当前线程的name副本的值
		System.out.println("------" + this.name.get());
	}
	// name的getter,setter方法
	public String getName() {
		return name.get();
	}
	public void setName(String str) {
		this.name.set(str);
	}
}

class MyTest extends Thread {
	// 定义一个Account属性
	private Account account;

	public MyTest(Account account, String name) {
		super(name);// 设置thread的名称
		this.account = account;
	}

	@Override
	public void run() {
		// 循环
		for (int i = 0; i < 10; i++) {
			if (i == 6) {// 当i=6时,将name名称更改为当前的线程名
				account.setName(getName());
			}
			System.out.println(account.getName() + " 账户i的值:" + i);
		}
	}
}
public class ThreadLocalTest {
	public static void main(String[] args) {
		Account account = new Account("初始名称");
		// 启动两个线程,两人个线程共享同一个账户,即只有一个账户名.
		/*
		 * 虽然丙个线程共享同一个账户,即只有一个账户名.但由于账户名是ThradLocal类型的,所以每个线程都完全拥有各自的账户名副本,
		 * 因此在ｉ=６以后,将看到两人个线程访问同一个账户时出现不同的账户名
		 */
		new MyTest(account, "张三").start();
		new MyTest(account, "李四").start();
	}
}
