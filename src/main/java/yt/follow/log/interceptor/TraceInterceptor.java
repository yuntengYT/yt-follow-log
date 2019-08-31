package yt.follow.log.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import yt.follow.log.constants.Constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author yunteng
 */
@Component
public class TraceInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String requestId = request.getHeader(Constant.LOG_REQUEST_ID);
		if (StringUtils.isEmpty(requestId)) {
			requestId = UUID.randomUUID().toString();
		}
		MDC.put(Constant.LOG_REQUEST_ID, requestId);
		return true;
	}
}