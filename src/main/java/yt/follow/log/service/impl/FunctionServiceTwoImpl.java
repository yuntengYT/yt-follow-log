package yt.follow.log.service.impl;

import org.springframework.stereotype.Service;
import yt.follow.log.service.IFunctionService;

@Service
public class FunctionServiceTwoImpl implements IFunctionService {
	@Override
	public void function1() {
		System.out.println("TWO");
	}
}
