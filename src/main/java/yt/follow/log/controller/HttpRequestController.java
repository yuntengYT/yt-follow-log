package yt.follow.log.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
}
