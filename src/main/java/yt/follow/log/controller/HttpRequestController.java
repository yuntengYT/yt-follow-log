package yt.follow.log.controller;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import yt.follow.log.service.IFunctionService;

import java.util.Set;

/**
 * @author yunteng
 */
@Slf4j
@RestController
public class HttpRequestController {
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("get-http-request")
	public void getHttpRequest(){
		restTemplate.getForObject("http://127.0.0.1:9069/get-all-user-info?id=3",Object.class);
		log.info("getHttpRequest====success");
	}


	/**
	 * 获取接口的所有实现类
	 * @param args
	 */
	public static void main(String[] args) {
		Reflections reflections = new Reflections("yt.follow.log");
		Set<Class<? extends IFunctionService>> set = reflections.getSubTypesOf(IFunctionService.class);
		if(CollectionUtils.isNotEmpty(set)) {
			set.forEach(one -> {
				try {
					IFunctionService functionService = one.newInstance();
					functionService.function1();
					functionService.function3();
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			});
		}
	}
}
