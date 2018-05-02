package com.xu.webservice.util;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.AttemptTimeLimiters;
import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.RetryListener;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CharleyXu Created on 2018/5/2.
 */
public class RetryUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(RetryUtils.class);

	/**
	 * @param task 要重试执行的任务
	 * @param fixedWaitTime 重试间隔
	 * @param timeout 重试超时时间		Retransmission Time Out
	 * @param timeUnit 时间单位
	 * @param attemptNumber 重试次数
	 */
	public static <T> T retry(Callable<T> task, long fixedWaitTime, long timeout,
			TimeUnit timeUnit, int attemptNumber) {
		Retryer<T> retryer = RetryerBuilder
				.<T>newBuilder()
				//抛出RunTime异常、Checked异常时都会重试，但是抛出Error不会重试。
				.retryIfException()
				//.retryIfExceptionOfType(Exception.class)//设置自定义异常重试源
				//重试间隔
				.withWaitStrategy(WaitStrategies.fixedWait(fixedWaitTime, timeUnit))
				//重试次数
				.withStopStrategy(StopStrategies.stopAfterAttempt(attemptNumber))
				//重试超时时间，在规定的时间内没有返回结果会TimeoutException
				.withAttemptTimeLimiter(AttemptTimeLimiters.<T>fixedTimeLimit(timeout, timeUnit))
				//重试监听器
				.withRetryListener(new RetryListener() {
														 @Override
														 public <V> void onRetry(Attempt<V> attempt) {
															 if (attempt.hasException()) {
																 LOGGER.error("第【{}】次重试失败", attempt.getAttemptNumber(), attempt.getExceptionCause());
															 }
														 }
													 }
				).build();
		T t = null;
		try {
			t = retryer.call(task);
		} catch (ExecutionException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (RetryException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return t;
	}

	/**
	 * @param task 要重试执行的任务
	 * @param predicate 符合预期结果需要重试
	 * @param fixedWaitTime 重试间隔
	 * @param timeout 重试超时时间
	 * @param attemptNumber 重试次数
	 */
	public static <T> T retry(Callable<T> task, Predicate<T> predicate, long fixedWaitTime,
			long timeout, TimeUnit timeUnit, int attemptNumber) {
		Retryer<T> retryer = RetryerBuilder
				.<T>newBuilder()
				.retryIfException()
				//对执行结果的预期。符合预期就重试
				.retryIfResult(predicate)
				.withWaitStrategy(WaitStrategies.fixedWait(fixedWaitTime, timeUnit))
				.withStopStrategy(StopStrategies.stopAfterAttempt(attemptNumber))
				.withAttemptTimeLimiter(AttemptTimeLimiters.<T>fixedTimeLimit(timeout, timeUnit))
				//重试监听器
				.withRetryListener(new RetryListener() {
														 @Override
														 public <V> void onRetry(Attempt<V> attempt) {
															 if (attempt.hasException()) {
																 LOGGER.error("第【{}】次重试失败", attempt.getAttemptNumber(), attempt.getExceptionCause());
															 }
														 }
													 }
				).build();
		T t = null;
		try {
			t = retryer.call(task);
		} catch (ExecutionException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (RetryException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return t;
	}

	public static void main(String[] args) {

		// here shows thress kinds of test case
		Callable<Integer> task = () -> {
			int a = 1 / 0;
			return 2;
		};
		Callable<Integer> task2 = () -> {
			Thread.sleep(2000L);
			return 2;
		};
		Callable<Boolean> task3 = () -> {
			return false;
		};
		//异常重试
		Integer result = retry(task, 30L, 1000L, TimeUnit.MILLISECONDS, 3);
		LOGGER.info("result: {}", result);
		//超时重试
		Integer result2 = retry(task2, 30L, 1000L, TimeUnit.MILLISECONDS, 3);
		LOGGER.info("result: {}", result2);
		//预期值重试
		boolean result3 = retry(task3, Predicates.equalTo(false), 30L, 1000L, TimeUnit.MILLISECONDS, 3);
		LOGGER.info("result: {}", result3);
	}

	public void test01() {
		Callable<Boolean> task = new Callable<Boolean>() {
			int i = 0;

			@Override
			public Boolean call() throws Exception {
				i++;
				LOGGER.info("第{}次执行！", i);
				// do something
				if (i < 6) {// 模拟错2次
					LOGGER.info("模拟执行失败！");
					throw new IOException("异常");
				}
				LOGGER.info("模拟执行成功！");
				return true;
			}
		};
	}

}
