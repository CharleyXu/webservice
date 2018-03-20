package com.xu.webservice.task;

import com.google.common.base.Strings;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

/**
 * @author charlie Created on 2018/3/20.
 *
 * 默认Core线程数 1	,	即时生效
 */
@Component
public class DynamicScheduleTask {

	private ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
	private ScheduledFuture<?> future;

	@Value("${cascading.caseTransmit.cron}")
	private String cron;

	public void setCron(String cron) {
		this.cron = cron;
		stopCron();
		//必须初始化才能用
		threadPoolTaskScheduler.initialize();
		future = threadPoolTaskScheduler.schedule(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("Run Task:" + LocalTime.now());
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Run Error" + LocalTime.now());
				}
			}
		}, new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				if (Strings.isNullOrEmpty(cron)) {
					return null;
				}
				CronTrigger trigger = new CronTrigger(cron);// 定时任务触发，可修改定时任务的执行周期
				Date nextExecDate = trigger.nextExecutionTime(triggerContext);
				return nextExecDate;
			}
		});
	}

	public void stopCron() {
		if (future != null) {
			//取消任务调度
			future.cancel(true);
		}
	}
}
