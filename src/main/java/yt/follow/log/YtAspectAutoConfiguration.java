package yt.follow.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yt.follow.log.filter.MyFilter;

/**
 * @author yunteng
 */
@Configuration
@Slf4j
public class YtAspectAutoConfiguration {

	@Bean
	public MyFilter myFilter() {
		return new MyFilter();
	}


}
