package yt.follow.log.http;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import yt.follow.log.constants.Constant;

import java.util.ArrayList;

/**
 * @author yunteng
 */
@Configuration
public class RestTemplateConfig {

	@Bean
	public RestTemplate restTemplate() {
		// 以下省略其他相关配置
		RestTemplate restTemplate = new RestTemplate();
		// 使用拦截器包装http header
		restTemplate.setInterceptors(new ArrayList<ClientHttpRequestInterceptor>() {
			{
				add((request, body, execution) -> {
					String requestId = MDC.get(Constant.LOG_REQUEST_ID);
					if (StringUtils.isNotEmpty(requestId)) {
						request.getHeaders().add(Constant.LOG_REQUEST_ID, requestId);
					}
					return execution.execute(request, body);
				});
			}
		});

		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		// 注意此处需开启缓存，否则会报getBodyInternal方法“getBody not supported”错误
		factory.setBufferRequestBody(true);
		restTemplate.setRequestFactory(factory);
		return restTemplate;
	}

}
