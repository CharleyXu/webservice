package com.xu.webservice.task;

import com.google.common.base.Strings;
import java.time.LocalTime;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

/**
 * @author charlie Created on 2018/3/20.
 *
 * 动态修改定时任务cron参数
 *
 * 不需要重启应用就可以动态的改变Cron表达式的值 不能使用@Scheduled(cron = “${jobs.cron}”)实现
 *
 * 运行之后需要在修改完之后，只有再一次到达定时任务的时间，才会调用新的触发时间，所以页面设置的时间并不能即时生效
 */
@Component
public class MyScheduler implements SchedulingConfigurer {

	@Value("${cascading.caseTransmit.cron}")
	private String cron;

	public MyScheduler setCron(String cron) {
		this.cron = cron;
		return this;
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
		scheduledTaskRegistrar.addTriggerTask(new Runnable() {
			@Override
			public void run() {
				System.out.println(LocalTime.now());
			}
		}, new Trigger() {
			@Nullable
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				if (Strings.isNullOrEmpty(cron)) {
					return null;
				}
				CronTrigger cronTrigger = new CronTrigger(cron);
				// 定时任务触发，可修改定时任务的执行周期
				Date nextExecutionDate = cronTrigger.nextExecutionTime(triggerContext);
				return nextExecutionDate;
			}
		});
	}
}
