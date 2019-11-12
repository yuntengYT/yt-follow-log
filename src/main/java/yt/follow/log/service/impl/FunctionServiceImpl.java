package yt.follow.log.service.impl;

import org.springframework.stereotype.Service;
import yt.follow.log.service.IFunctionService;

@Service
public class FunctionServiceImpl implements IFunctionService {
	@Override
	public void function1() {
		System.out.println("ONE");
	}

	@Override
	public void function3() {
		System.out.println("ONE-function3");
	}
}
