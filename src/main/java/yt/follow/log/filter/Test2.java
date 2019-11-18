package yt.follow.log.filter;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test2 {

	public static ReentrantLock reentrantLock = new ReentrantLock();
	public static Condition condition =  reentrantLock.newCondition();
	public static boolean flag = false;

	public void printA(){
		reentrantLock.lock();
		while(flag) {
			try {
				condition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		flag = true;
		System.out.println("A");
		condition.signal();
	}

	public void printB(){
		reentrantLock.lock();
		while(!flag) {
			try {
				condition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		flag = false;
		System.out.println("B");
		condition.signal();
	}

	public static void main(String[] args) {
		Test2 test2 = new Test2();
		new Thread(){
			@Override
			public void run(){
				for(int i = 0; i< 10;i++){
					test2.printA();
				}
			}
		}.start();
		new Thread(){
			@Override
			public void run(){
				for(int i = 0; i< 10;i++){
					test2.printB();
				}
			}
		}.start();
	}

}
