package com.icetea.manager.pagodiario.job.executor;

import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import com.icetea.manager.pagodiario.api.dto.BillDto;
import com.icetea.manager.pagodiario.service.BillService;

@Named
public class OverdueDaysUpdaterExecutor {

	private static final Logger LOGGER = getLogger(OverdueDaysUpdaterExecutor.class);
	
	private final BillService billService;
	
	@Inject
	public OverdueDaysUpdaterExecutor(BillService billService) {
		super();
		this.billService = billService;
	}

	public void execute(){
		
		int counter = 0;
		
		LOGGER.info("empieza - actualizando dias de atraso");
		
		List<BillDto> expires = this.billService.searchExpires();
		if(expires != null && !expires.isEmpty()){
			LOGGER.info("OverdueDaysUpdaterExecutor - cantidad de facturas expiradas: " + expires.size());
			
			for(BillDto d : expires){
				boolean r = this.billService.updateOverdueDays(d.getId());
				if(r){
					counter++;
				}
			}
		}
		
		LOGGER.info(String.format("finaliza - actualizando dias de atraso de %s", counter));
	}
	
}
