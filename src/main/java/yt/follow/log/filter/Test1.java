package yt.follow.log.filter;

public class Test1 {

	boolean flag = true;
	synchronized void printA(){
		while (!flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		flag = false;
		System.out.println("A");
		this.notify();
	}
	synchronized void printB(){
		while (flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		flag = true;
		System.out.println("B");
		this.notify();
	}

	public static void main(String[] args) {
		Test1 test1 = new Test1();
		new Thread(){
			@Override
			public void run(){
				for(int i = 0; i< 10;i++){
					test1.printA();
				}
			}
		}.start();
		new Thread(){
			@Override
			public void run(){
				for(int i = 0; i< 10;i++){
					test1.printB();
				}
			}
		}.start();
	}
}
