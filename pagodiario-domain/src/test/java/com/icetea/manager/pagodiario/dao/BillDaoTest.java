package com.icetea.manager.pagodiario.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Bill.Status;

@ContextConfiguration(locations = {"classpath:hibernate-db-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class BillDaoTest {

	@Inject
	private BillDaoImpl instance;
	
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
	
}
