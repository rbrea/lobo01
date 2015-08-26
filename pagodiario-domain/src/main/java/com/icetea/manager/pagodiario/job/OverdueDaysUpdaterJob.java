package com.icetea.manager.pagodiario.job;

import static org.slf4j.LoggerFactory.getLogger;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import com.icetea.manager.pagodiario.job.executor.OverdueDaysUpdaterExecutor;

@Named
public class OverdueDaysUpdaterJob {

	private static final Logger LOGGER = getLogger(OverdueDaysUpdaterJob.class);
	
	private final OverdueDaysUpdaterExecutor overdueDaysUpdaterExecutor;
	private final boolean enabled;
	
	@Inject
	public OverdueDaysUpdaterJob(OverdueDaysUpdaterExecutor overdueDaysUpdaterExecutor,
			@Value("${overdueDays.service.job.enabled:true}") boolean enabled) {
		super();
		this.overdueDaysUpdaterExecutor = overdueDaysUpdaterExecutor;
		this.enabled = enabled;
	}

	@Scheduled(initialDelayString = "${job.updater.overdueDays.delay}",
			fixedRateString = "${job.updater.overdueDays.rate}")
	public void doJob(){
		
		LOGGER.debug("OverdueDaysUpdaterJob - Iniciando!");
		
		if(!this.enabled){
			LOGGER.debug("OverdueDaysUpdaterJob - disabled. Finished!");
			
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
		
		LOGGER.debug("OverdueDaysUpdaterJob - Terminado!");
	}
	
}
