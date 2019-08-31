package yt.follow.log.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * followLogThreadPool
 *
 * @author zyt
 */
@Configuration
public class FollowLogThreadPoolConfig {
	@Bean("followLogThreadPool")
	public ExecutorService followLogThreadPool() {
		ThreadFactory threadFactory = new ThreadFactoryBuilder()
				.setNameFormat("thread-pool-okr-%d").build();

		int cpuNumber = Runtime.getRuntime().availableProcessors();
		return new ThreadPoolExecutor(
				(cpuNumber * 2) + 4,
				cpuNumber * 4,
				300,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(1000),
				threadFactory,
				new ThreadPoolExecutor.AbortPolicy()
		){
			@Override
			public void execute(Runnable command) {
				super.execute(ThreadMdcUtil.wrap(command,MDC.getCopyOfContextMap()));
			}
			@Override
			public <T> Future<T> submit(Callable<T> task) {
				return super.submit(ThreadMdcUtil.wrap(task,MDC.getCopyOfContextMap()));
			}
			@Override
			public Future<?> submit(Runnable task) {
				return super.submit(ThreadMdcUtil.wrap(task,MDC.getCopyOfContextMap()));
			}
			@Override
			public <T> Future<T> submit(Runnable task, T result) {
				return super.submit(ThreadMdcUtil.wrap(task,MDC.getCopyOfContextMap()),result);
			}
		};
	}
}
