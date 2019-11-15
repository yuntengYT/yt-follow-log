package yt.follow.log.filter;

import com.alibaba.fastjson.JSON;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yunteng
 */
@Order(10000)
public class MyFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Enumeration enumeration = request.getParameterNames();
		Map<String,Object> reMap = new HashMap<>(16);
		while(enumeration.hasMoreElements()) {
			String paramValue = (String)enumeration.nextElement();
			String[] values = request.getParameterValues(paramValue);
			StringBuilder value = new StringBuilder();
			for(int i = 0; i < values.length; i ++) {
				value.append(values[i]);
			}
			reMap.put(paramValue,value);
		}


		System.out.println(JSON.toJSONString(reMap));
		chain.doFilter(request,response);

		System.out.println("end===request");
	}

	@Override
	public void destroy() {
	}
}
