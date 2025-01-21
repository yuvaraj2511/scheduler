package com.example.scheduler.scheduler;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ScheduledFuture;

@Service
public class DynamicScheduler {

    private final TaskScheduler taskScheduler;
    private ScheduledFuture<?> scheduledTask;
    private String cronExpression = "0/5 * * * * ?";

    public DynamicScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.initialize();
        this.taskScheduler = threadPoolTaskScheduler;
    }

    public void startScheduler() {
        stopScheduler();
        scheduledTask = taskScheduler.schedule(this::executeTask, new CronTrigger(cronExpression));
        System.out.println("Scheduler started with cron: " + cronExpression);
    }

    public void stopScheduler() {
        if (scheduledTask != null && !scheduledTask.isCancelled()) {
            scheduledTask.cancel(true);
            System.out.println("Scheduler stopped.");
        }
    }

    public void updateCronExpression(String newCronExpression) {
        System.out.println("Updating cron expression to: " + newCronExpression);
        this.cronExpression = newCronExpression;
        startScheduler();
    }

    private void executeTask() {
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = current.format(formatter);
        System.out.println("Task executed at: " + formattedDateTime);
    }
}
