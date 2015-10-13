package com.icetea.manager.pagodiario.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Bill.Status;
import com.icetea.manager.pagodiario.model.Trader;

@ContextConfiguration(locations = {"classpath:hibernate-db-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class BillDaoTest {

	@Inject
	private BillDaoImpl instance;
	@Inject
	private TraderDao traderDao;
	
	@Test
	public void findActivesByDate_ok(){
	
		Date now = new Date();
		
		Bill bill = new Bill();
		bill.setCreditNumber(123L);
		bill.setStartDate(now);
		bill.setStatus(Status.ACTIVE);
		
		this.instance.save(bill);
		
		List<Bill> bills = this.instance.findActivesByDate(now);
		
		assertThat("error", bills != null && !bills.isEmpty() && bills.size() == 1, is(true));
	}
	
	@Test
	public void findActivesByDate_2_ok(){
	
		Date now = new Date();
		
		Bill bill = new Bill();
		bill.setCreditNumber(123L);
		bill.setStartDate(now);
		bill.setStatus(Status.FINALIZED);
		bill.setCompletedDate(now);
		
		this.instance.save(bill);
		
		List<Bill> bills = this.instance.findActivesByDate(now);
		
		assertThat("error", bills != null && !bills.isEmpty() && bills.size() == 1, is(true));
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void findActivesGroupingByWeekAndTrader_ok(){
		Date now = new Date();
		
		Trader trader = new Trader();
		trader.setName("TEST TEST 02");
		
		this.traderDao.saveOrUpdate(trader);

		Bill bill = new Bill();
		bill.setCreditNumber(123L);
		Date startDate = DateUtils.addMonths(now, -6);
		bill.setStartDate(startDate);
		bill.setStatus(Status.ACTIVE);
		bill.setCompletedDate(now);
		bill.setTotalAmount(new BigDecimal(10000));
		bill.setRemainingAmount(new BigDecimal(9500));
		bill.setTotalDailyInstallment(new BigDecimal(500));
		bill.setTrader(trader);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		bill.setWeekOfYear(calendar.get(Calendar.WEEK_OF_YEAR));
		
		this.instance.saveOrUpdate(bill);
		
		bill = new Bill();
		bill.setCreditNumber(555L);
		bill.setStartDate(startDate);
		bill.setStatus(Status.ACTIVE);
		bill.setCompletedDate(now);
		bill.setTotalAmount(new BigDecimal(1000));
		bill.setRemainingAmount(new BigDecimal(950));
		bill.setTotalDailyInstallment(new BigDecimal(50));
		bill.setTrader(trader);
		bill.setWeekOfYear(calendar.get(Calendar.WEEK_OF_YEAR));
		
		this.instance.saveOrUpdate(bill);
		
		bill = new Bill();
		bill.setCreditNumber(1234L);
		startDate = DateUtils.addMonths(now, -4);
		bill.setStartDate(startDate);
		bill.setStatus(Status.ACTIVE);
		bill.setCompletedDate(now);
		bill.setTotalAmount(new BigDecimal(10000));
		bill.setRemainingAmount(new BigDecimal(9000));
		bill.setTotalDailyInstallment(new BigDecimal(1000));
		bill.setTrader(trader);
		
		calendar.setTime(startDate);
		
		bill.setWeekOfYear(calendar.get(Calendar.WEEK_OF_YEAR));
		
		this.instance.saveOrUpdate(bill);
		
		List list = this.instance.findActivesGroupingByWeekAndTrader();
		
		assertThat("error", list != null && !list.isEmpty() && list.size() == 2, is(true));
	}
	
}
