package com.icetea.manager.pagodiario.service.chart;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.CollectionUtils;

import com.icetea.manager.pagodiario.api.dto.chart.ChartDto;
import com.icetea.manager.pagodiario.api.dto.chart.CollectorChartDto;
import com.icetea.manager.pagodiario.api.dto.chart.TopTraderDto;
import com.icetea.manager.pagodiario.api.dto.chart.TotalSaleDto;
import com.icetea.manager.pagodiario.dao.PayrollDao;
import com.icetea.manager.pagodiario.dao.PayrollItemCollectDao;
import com.icetea.manager.pagodiario.model.Payroll;
import com.icetea.manager.pagodiario.model.PayrollItem;
import com.icetea.manager.pagodiario.service.BasicServiceImpl;
import com.icetea.manager.pagodiario.utils.DateUtils;

@Named
public class ChartServiceImpl extends BasicServiceImpl implements ChartService {

	private final PayrollDao payrollDao;
	private final PayrollItemCollectDao payrollItemCollectDao;

	@Inject
	public ChartServiceImpl(PayrollDao payrollDao,
			PayrollItemCollectDao payrollItemCollectDao) {
		super();
		this.payrollDao = payrollDao;
		this.payrollItemCollectDao = payrollItemCollectDao;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ChartDto search(){
		
		ChartDto chart = new ChartDto();
		
		List<TopTraderDto> topTraders = chart.getTopTraders();

		Payroll lastPayroll = this.payrollDao.findLast();
		
		
		if(lastPayroll != null){
			
			List<PayrollItem> payrollItemList = lastPayroll.getPayrollItemList();
			
			if(payrollItemList != null){
				
				Collections.sort(payrollItemList, new Comparator<PayrollItem>() {
					@Override
					public int compare(PayrollItem o1, PayrollItem o2) {
						
						return o2.getTotalAmount().compareTo(o1.getTotalAmount());
					}
				});
				
				int size = payrollItemList.size();
				
				if(size > 5){
					size = 5;
				}
				
				for(int i=0;i<size-1;i++){
					
					PayrollItem p = payrollItemList.get(i);
					TopTraderDto t = new TopTraderDto();
					t.setNombre(p.getTrader().getName());
					t.setValue(p.getTotalAmount());
					topTraders.add(t);
					
				}
				
			}
		}
		
		final List<TotalSaleDto> totalSales = chart.getTotalSales();
		
		List<Payroll> lastPayrollList = this.payrollDao.findLast(12);
		
		if(lastPayrollList != null){
			
			Collections.sort(lastPayrollList, new Comparator<Payroll>() {
				@Override
				public int compare(Payroll o1, Payroll o2) {
					return o1.getDateFrom().compareTo(o2.getDateFrom());
				}
			});
			
			CollectionUtils.forAllDo(lastPayrollList, new Closure<Payroll>() {
				@Override
				public void execute(Payroll p) {
					TotalSaleDto s = new TotalSaleDto();
					s.setPeriodo(DateUtils.toDate(p.getDateFrom()) + " - " + DateUtils.toDate(p.getDateTo()));
					s.setValue(p.getTotal());
					totalSales.add(s);
				}
			});
			
		}

		List<CollectorChartDto> topCollectors = chart.getTopCollectors();
		
		List<?> picList = this.payrollItemCollectDao.findPeriod(new Date());
		
		if(picList != null){
			
			List<Object[]> list = (List<Object[]>)picList;
			
			for(Object[] o : list){
				
				Long zone = (Long) o[0];
				String collectorDescription = (String) o[1];
				BigDecimal totalPayment = (BigDecimal) o[2];
				
				CollectorChartDto c = new CollectorChartDto();
				c.setLabel(collectorDescription);
				c.setValue(totalPayment);
				
				topCollectors.add(c);
			}
			
		}

		return chart;
	}
	
}
