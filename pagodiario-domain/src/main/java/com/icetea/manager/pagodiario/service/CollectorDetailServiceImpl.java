package com.icetea.manager.pagodiario.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.CollectorDetailDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.dao.BillDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.transformer.CollectorDetailTransformer;
import com.icetea.manager.pagodiario.utils.DateUtils;

@Named
public class CollectorDetailServiceImpl extends BasicServiceImpl implements
		CollectorDetailService {

	@Inject
	private BillDao billDao;
	@Inject
	private CollectorDetailTransformer collectorDetailTransformer;
	
	@Override
	public List<CollectorDetailDto> search(String fromArg, String toArg){
		
		ErrorTypedConditions.checkArgument(StringUtils.isNotBlank(fromArg), 
				"La fecha desde es requerida", ErrorType.VALIDATION_ERRORS);
		ErrorTypedConditions.checkArgument(StringUtils.isNotBlank(toArg), 
				"La fecha hasta es requerida", ErrorType.VALIDATION_ERRORS);
		
		Date from = DateUtils.parseDate(fromArg);
		Date to = DateUtils.parseDate(toArg);
		
		List<Bill> bills = this.billDao.findBillsWithCollectors(from, to);
		
		if(bills == null){
			return Lists.newArrayList();
		}
		
		return this.collectorDetailTransformer.transform(bills, from, to); 
	}
	
}
