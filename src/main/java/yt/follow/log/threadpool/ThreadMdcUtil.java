package yt.follow.log.threadpool;

import org.slf4j.MDC;
import yt.follow.log.constants.Constant;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * @author yunteng
 */
public class ThreadMdcUtil {

	public static void setRequextIdIfAbsent() {
		if (MDC.get(Constant.LOG_REQUEST_ID) == null) {
			MDC.put(Constant.LOG_REQUEST_ID, UUID.randomUUID().toString());
		}
	}

	public static void setRequextId() {
		MDC.put(Constant.LOG_REQUEST_ID, UUID.randomUUID().toString());
	}

	public static void setRequextId(String traceId) {
		MDC.put(Constant.LOG_REQUEST_ID, traceId);
	}

	public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
		return () -> {
			if (context == null) {
				MDC.clear();
			} else {
				MDC.setContextMap(context);
			}
			setRequextIdIfAbsent();
			try {
				return callable.call();
			} finally {
				MDC.clear();
			}
		};
	}

	public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
		return () -> {
			if (context == null) {
				MDC.clear();
			} else {
				MDC.setContextMap(context);
			}
			setRequextIdIfAbsent();
			try {
				runnable.run();
			} finally {
				MDC.clear();
			}
		};
	}
}

