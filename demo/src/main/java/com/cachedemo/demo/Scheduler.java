package com.cachedemo.demo;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class Scheduler {

	private Scheduler() {
	}

	/**
	 * resetting retries to zero for every 5 minutes
	 */
	@Scheduled(cron = "*/1 * * * *")
	public static void resetRetries() {
		DemoResource.retries = 0;
	}
}
