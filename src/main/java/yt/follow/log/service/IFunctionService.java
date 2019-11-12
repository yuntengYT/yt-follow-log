package yt.follow.log.service;

@FunctionalInterface
public interface IFunctionService {

	void function1();

	static void function2(){
		System.out.println(111);
	};

	default void function3(){
		System.out.println(222);
	}
}
