package com.example.scheduler.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class StaticScheduler {

    // @Scheduled(fixedDelay = 2000)
    @Scheduled(cron = "0/2 * * * * ?")
    public void scheduler() {
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = current.format(formatter);
        System.out.println("Time to send report: " + formattedDateTime);
    }
}
