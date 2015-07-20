package com.icetea.manager.pagodiario.job;

import static org.slf4j.LoggerFactory.getLogger;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import com.icetea.manager.pagodiario.job.executor.OverdueDaysUpdaterExecutor;

@Named
public class CreditJob {

	private static final Logger LOGGER = getLogger(CreditJob.class);
	
	private final OverdueDaysUpdaterExecutor overdueDaysUpdaterExecutor;
	private final boolean enabled;
	
	@Inject
	public CreditJob(OverdueDaysUpdaterExecutor overdueDaysUpdaterExecutor,
			@Value("${overdueDays.service.job.enabled:true}") boolean enabled) {
		super();
		this.overdueDaysUpdaterExecutor = overdueDaysUpdaterExecutor;
		this.enabled = enabled;
	}

	@Scheduled(cron = "${quartz.job.updater.cron.expression.overdueDays}")// va a correr una vez por dia a la madrugada ...
	public void doJob(){
		
		if(!this.enabled){
			LOGGER.debug("CreditJob - disabled. Finished!");
			
			return;
		} else {
//			if(this.cancelation == 0){
//				this.cancelation = 1;
//			} else if(this.cancelation == 1){
//				
//				return;	
//			}
		}
		
		this.overdueDaysUpdaterExecutor.execute();
		
		LOGGER.debug("CreditJob - Finished!");
	}
	
}
