package com.icetea.manager.pagodiario.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;
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

import com.icetea.manager.pagodiario.api.dto.PayrollCollectDto;
import com.icetea.manager.pagodiario.dao.CollectorDao;
import com.icetea.manager.pagodiario.dao.PayrollCollectDao;
import com.icetea.manager.pagodiario.dao.PayrollItemCollectDao;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Bill.Status;
import com.icetea.manager.pagodiario.model.BillProduct;
import com.icetea.manager.pagodiario.model.Client;
import com.icetea.manager.pagodiario.model.Collector;
import com.icetea.manager.pagodiario.model.Discount;
import com.icetea.manager.pagodiario.model.Payment;
import com.icetea.manager.pagodiario.model.PayrollCollect;
import com.icetea.manager.pagodiario.model.PayrollItemCollect;
import com.icetea.manager.pagodiario.model.Product;
import com.icetea.manager.pagodiario.model.Trader;
import com.icetea.manager.pagodiario.utils.DateUtils;

@ContextConfiguration(locations = {"classpath:hibernate-db-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class PayrollCollectServiceTest extends AbstractPayrollServiceTest {

	@Inject
	private PayrollCollectServiceImpl instance;
	@Inject
	private PayrollItemCollectDao payrollItemCollectDao;
	@Inject
	private CollectorDao collectorDao;
	@Inject
	private PayrollCollectDao payrollCollectDao;
	
	@Test
	public void process_ok(){
		
		Date startDate = DateUtils.parseDate("01/10/2015");
		
		Date paymentDate = DateUtils.addDays(startDate, 10);
		
		Bill bill = new Bill();

		Trader supervisor = this.createTrader(3);
		supervisor.setSupervisor(true);
		
		Trader t = this.createTrader(1);
		
		t.setParent(supervisor);
		supervisor.addTrader(t);
		
		this.traderDao.saveOrUpdate(supervisor);
		this.traderDao.saveOrUpdate(t);

		Trader t2 = this.createTrader(2);
		this.traderDao.saveOrUpdate(t2);
		
		Client c = this.createClient(1);
		
		this.clientDao.saveOrUpdate(c);
		
		Product p1 = this.createProduct(1);
		this.productDao.saveOrUpdate(p1);
		Product p2 = this.createProduct(2);
		this.productDao.saveOrUpdate(p2);
		BillProduct bp1 = this.createBillProduct(bill, 1, p1);
		BillProduct bp2 = this.createBillProduct(bill, 2, p2);
		
		Collector collector = this.createCollector();
		this.collectorDao.save(collector);
		
		bill.addBillProduct(bp1);
		bill.addBillProduct(bp2);
		bill.setClient(c);
		bill.setCollector(collector);
		bill.setCreditNumber(111L);
		bill.setStartDate(startDate);
		bill.setEndDate(DateUtils.addDays(startDate, 30));
		bill.setOverdueDays(0);
		bill.setRemainingAmount(new BigDecimal(2958));
		bill.setStatus(Status.ACTIVE);
		bill.setTotalAmount(new BigDecimal(3000));
		bill.setTotalDailyInstallment(new BigDecimal(100));
		bill.setTrader(t);
		Payment payment = this.createPayment(bill);
		payment.setAmount(new BigDecimal(100));
		payment.setDate(paymentDate);
		bill.addPayment(payment);
		
		Discount d1 = new Discount();
		d1.setAmount(new BigDecimal(1000));
		d1.setBill(bill);
		d1.setDate(DateUtils.addDays(startDate, -2));
		d1.setObservations("Descuento de test 1");
		bill.addDiscount(d1);

		this.billDao.saveOrUpdate(bill);
		
		bill = new Bill();
		
		bp1 = this.createBillProduct(bill, 1, p1);
		bp2 = this.createBillProduct(bill, 2, p2);
		
		bill.addBillProduct(bp1);
		bill.addBillProduct(bp2);
		bill.setClient(c);
		bill.setCollector(collector);
		bill.setCreditNumber(112L);
		
		bill.setStartDate(startDate);
		bill.setEndDate(DateUtils.addDays(startDate, 30));
		bill.setOverdueDays(0);
		bill.setRemainingAmount(new BigDecimal(2958));
		bill.setStatus(Status.ACTIVE);
		bill.setTotalAmount(new BigDecimal(3000));
		bill.setTotalDailyInstallment(new BigDecimal(200));
		bill.setTrader(t);
		payment = this.createPayment(bill);
		payment.setDate(paymentDate);
		payment.setAmount(new BigDecimal(50));
		bill.addPayment(payment);
		
		this.billDao.saveOrUpdate(bill);
		
		PayrollCollectDto result = this.instance.processPayrollAlternative(
				DateUtils.toDate(
						paymentDate));
		
		assertThat("result == null", result != null, is(true));
		
		assertThat("payrollDate error", result.getPayrollDate(), is("11/10/2015"));
		assertThat("totalAmount error", result.getTotalAmount(), is("300.00"));
		assertThat("totalAmountToPay error", result.getTotalAmountToPay(), is("27.00"));
		assertThat("totalCards error", result.getTotalCards(), is(2));
		assertThat("totalPayment error", result.getTotalPayment(), is("150.00"));
	}

	@Test
	public void findPeriod_ok(){
		
		Collector collector = new Collector();
		collector.setZone(33L);
		collector.setDescription("COBRADOR 33");
		
		this.collectorDao.saveOrUpdate(collector);
		
		Collector collector1 = new Collector();
		collector1.setZone(99L);
		collector1.setDescription("COBRADOR 99");
		
		this.collectorDao.saveOrUpdate(collector1);
		
		PayrollCollect pc = new PayrollCollect();
		pc.setPayrollDate(DateUtils.addDays(new Date(), -2));
		pc.setTotalAmount(new BigDecimal(400));
		pc.setTotalPayment(new BigDecimal(300));
		
		PayrollItemCollect pic = new PayrollItemCollect();
		pic.setPayrollCollect(pc);
		pic.setTotalAmount(new BigDecimal(300));
		pic.setTotalPayment(new BigDecimal(200));
		pic.setCollector(collector);
		
		pc.getPayrollItemCollectList().add(pic);
		
		pic = new PayrollItemCollect();
		pic.setPayrollCollect(pc);
		pic.setTotalAmount(new BigDecimal(100));
		pic.setTotalPayment(new BigDecimal(100));
		pic.setCollector(collector);
		
		pc.getPayrollItemCollectList().add(pic);
		
		this.payrollCollectDao.saveOrUpdate(pc);
		
		pc = new PayrollCollect();
		pc.setPayrollDate(DateUtils.addDays(new Date(), -2));
		pc.setTotalAmount(new BigDecimal(200));
		pc.setTotalPayment(new BigDecimal(100));
		
		pic = new PayrollItemCollect();
		pic.setPayrollCollect(pc);
		pic.setTotalAmount(new BigDecimal(200));
		pic.setTotalPayment(new BigDecimal(100));
		pic.setCollector(collector1);
		
		pc.getPayrollItemCollectList().add(pic);
		
		this.payrollCollectDao.saveOrUpdate(pc);
		
		List<?> list = this.payrollItemCollectDao.findPeriod(new Date());
		
		assertThat("", list != null, is(true));
		assertThat("", list.size(), is(2));

		Object[] obj0 = (Object[])list.get(0);
		Long zone = (Long) obj0[0];
		String collectorDescription = (String) obj0[1];
		BigDecimal totalPayment = (BigDecimal) obj0[2];
		
		assertThat("", zone, is(33L));
		assertThat("", collectorDescription, is("COBRADOR 33"));
		assertThat("", totalPayment.compareTo(new BigDecimal(300)), is(0));
		
	}
	
}
