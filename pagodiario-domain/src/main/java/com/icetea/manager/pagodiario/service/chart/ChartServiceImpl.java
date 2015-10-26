package com.icetea.manager.pagodiario.service.chart;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections4.Closure;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.chart.ChartDto;
import com.icetea.manager.pagodiario.api.dto.chart.ChartTraderSalesWeekDto;
import com.icetea.manager.pagodiario.api.dto.chart.CollectorChartDto;
import com.icetea.manager.pagodiario.api.dto.chart.TopTraderDto;
import com.icetea.manager.pagodiario.api.dto.chart.TotalSaleDto;
import com.icetea.manager.pagodiario.dao.BillDao;
import com.icetea.manager.pagodiario.dao.PayrollDao;
import com.icetea.manager.pagodiario.dao.PayrollItemCollectDao;
import com.icetea.manager.pagodiario.dao.TraderDao;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Payroll;
import com.icetea.manager.pagodiario.model.PayrollItem;
import com.icetea.manager.pagodiario.model.Trader;
import com.icetea.manager.pagodiario.service.BasicServiceImpl;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class ChartServiceImpl extends BasicServiceImpl implements ChartService {

	private final PayrollDao payrollDao;
	private final PayrollItemCollectDao payrollItemCollectDao;
	private final BillDao billDao;
	private final TraderDao traderDao;
	
	private static final List<String> COLORS = Lists.newArrayList("#005CDE", "#00A36A", "#992B00", "#7D0096", "#DE000F", "#ED7B00"); 

	@Inject
	public ChartServiceImpl(PayrollDao payrollDao,
			PayrollItemCollectDao payrollItemCollectDao,
			BillDao billDao,
			TraderDao traderDao) {
		super();
		this.payrollDao = payrollDao;
		this.payrollItemCollectDao = payrollItemCollectDao;
		this.billDao = billDao;
		this.traderDao = traderDao;
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

		return chart;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<ChartTraderSalesWeekDto> searchTraderBillsByWeek(){
		
		List<ChartTraderSalesWeekDto> list = Lists.newArrayList();
		
		List c = this.billDao.findActivesGroupingByWeekAndTrader();
		if(c != null && !c.isEmpty()){
			for(int i=0;i<c.size();i++){
				Object[] o = (Object[]) c.get(i);
				Trader trader = this.traderDao.findById((Long)o[0]);
				//Integer weekOfYear = (Integer)o[1];
				Integer month = (Integer)o[1];
				Integer year = (Integer)o[2];
				BigDecimal amount = (BigDecimal)o[3];
				if(trader == null){
					continue;
				}
				final String traderName = trader.getName();
				ChartTraderSalesWeekDto d = CollectionUtils.find(list, new Predicate<ChartTraderSalesWeekDto>() {
					@Override
					public boolean evaluate(ChartTraderSalesWeekDto w) {
						return StringUtils.equals(w.getLabel(), traderName);
					}
				});
				if(d == null){
					d = new ChartTraderSalesWeekDto();
					d.setLabel(traderName);
					list.add(d);
				}
				List<Object> elem = Lists.newArrayListWithExpectedSize(3);
//				elem.add((weekOfYear < 10) ? "0" + weekOfYear : weekOfYear + "-" + year);
				//elem.add(month);
				//elem.add(year);
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				calendar.set(Calendar.MONTH, month);
				calendar.set(Calendar.YEAR, year);
				elem.add(calendar.getTimeInMillis());
				elem.add(NumberUtils.toString(amount));
				d.addData(elem);
			}
		}
		
		return list; 
	}
	
	@Override
	public List<TopTraderDto> searchTopTraders(){
		
		List<TopTraderDto> list = Lists.newArrayList();
		
		List<Bill> bills = this.billDao.findLastYear();
		
		for(final Bill b : bills){
			
			TopTraderDto t = CollectionUtils.find(list, new Predicate<TopTraderDto>() {
				@Override
				public boolean evaluate(TopTraderDto o) {
					return b.getTrader().getId().equals(o.getTraderId());
				}
			});
			
			if(t == null){
				t = new TopTraderDto();
				t.setTraderId(b.getTrader().getId());
				t.setNombre(b.getTrader().getName());
				list.add(t);
			}
			t.setValue(NumberUtils.add(t.getValue(), b.getTotalAmount()));
		}
		
		return list;
	}
	
	@Override
	public List<CollectorChartDto> searchTopCollectors(){
		
		List<CollectorChartDto> list = Lists.newArrayList();
		
		List<Bill> bills = this.billDao.findLastYear();
		
		int k = 0;
		
		BigDecimal total = BigDecimal.ZERO;
		
		for(final Bill b : bills){
			
			CollectorChartDto t = CollectionUtils.find(list, new Predicate<CollectorChartDto>() {
				@Override
				public boolean evaluate(CollectorChartDto o) {
					return b.getCollector().getId().equals(o.getCollectorId());
				}
			});
			
			if(t == null){
				t = new CollectorChartDto();
				t.setCollectorId(b.getCollector().getId());
				t.setLabel("Zona: " + b.getCollector().getZone());
				list.add(t);
				t.setColor(COLORS.get(k));
				k++;
				if(k == 6){
					k = 0;
				}
			}
			t.setTotalAmount(NumberUtils.add(t.getTotalAmount(), b.getTotalAmount()));
			total = NumberUtils.add(total, b.getTotalAmount());
		}
		
		for(CollectorChartDto c : list){
			
			c.setLabel(c.getLabel() + " - $" + NumberUtils.toString(c.getTotalAmount()));
			
			BigDecimal part = NumberUtils.divide(c.getTotalAmount(), total);
			c.setData(NumberUtils.multiply(part, NumberUtils._100));
		}
		
		return list;
	}
	
}
