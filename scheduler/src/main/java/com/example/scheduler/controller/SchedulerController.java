package com.example.scheduler.controller;

import com.example.scheduler.scheduler.DynamicScheduler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scheduler")
public class SchedulerController {

    private final DynamicScheduler dynamicScheduler;

    public SchedulerController(DynamicScheduler dynamicScheduler) {
        this.dynamicScheduler = dynamicScheduler;
    }

    @PostMapping("/start")
    public String startScheduler() {
        dynamicScheduler.startScheduler();
        return "Scheduler started.";
    }

    @PostMapping("/stop")
    public String stopScheduler() {
        dynamicScheduler.stopScheduler();
        return "Scheduler stopped.";
    }

    @PostMapping("/update-cron")
    public String updateCron(@RequestParam String cron) {
        dynamicScheduler.updateCronExpression(cron);
        return "Scheduler updated with cron: " + cron;
    }
}
