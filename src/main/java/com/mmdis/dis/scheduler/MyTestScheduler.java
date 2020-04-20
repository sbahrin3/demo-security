package com.mmdis.dis.scheduler;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
//@EnableScheduling
public class MyTestScheduler {
	
	
	@Scheduled(fixedRate=5000)
	public void testPrint() {
		System.out.println("Test Print Scheduler... " + new Date());
	}

}
