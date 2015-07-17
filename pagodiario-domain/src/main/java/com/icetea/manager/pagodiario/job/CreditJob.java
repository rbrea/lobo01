package com.icetea.manager.pagodiario.job;

import javax.inject.Named;

import org.springframework.scheduling.annotation.Scheduled;

@Named
public class CreditJob {

	@Scheduled(cron = "")// va a correr una vez por dia a la madrugada ...
	public void doJob(){
		
	}
	
}
