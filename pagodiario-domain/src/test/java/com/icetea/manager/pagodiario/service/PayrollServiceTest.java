package com.icetea.manager.pagodiario.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;
import java.util.Date;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icetea.manager.pagodiario.api.dto.PayrollDto;
import com.icetea.manager.pagodiario.dao.BillDaoImpl;
import com.icetea.manager.pagodiario.dao.ClientDaoImpl;
import com.icetea.manager.pagodiario.dao.PayrollDao;
import com.icetea.manager.pagodiario.dao.ProductDaoImpl;
import com.icetea.manager.pagodiario.dao.TraderDaoImpl;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Bill.Status;
import com.icetea.manager.pagodiario.model.BillProduct;
import com.icetea.manager.pagodiario.model.Client;
import com.icetea.manager.pagodiario.model.Payment;
import com.icetea.manager.pagodiario.model.Payroll;
import com.icetea.manager.pagodiario.model.PayrollItem;
import com.icetea.manager.pagodiario.model.Product;
import com.icetea.manager.pagodiario.model.Trader;

@ContextConfiguration(locations = {"classpath:hibernate-db-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class PayrollServiceTest {

	@Inject
	private PayrollServiceImpl instance;
	@Inject
	private BillDaoImpl billDao;
	@Inject
	private TraderDaoImpl traderDao;
	@Inject
	private ProductDaoImpl productDao;
	@Inject
	private ClientDaoImpl clientDao;
	@Inject
	private PayrollDao payrollDao;
	
	protected Trader createTrader(int i){
		Trader t = new Trader();
		t.setName("vendedor " + i);
		t.setDocumentType("DNI");
		t.setDocumentNumber(i + 1111111L);
		
		return t;
	}
	
	protected Client createClient(int i){
		Client c = new Client();
		c.setName("vendedor " + i);
		c.setDocumentType("DNI");
		c.setDocumentNumber(i + 1111111L);
		
		return c;
	}
	
	protected Product createProduct(int i){
		Product p = new Product();
		p.setCode("00" + i);
		p.setDescription("Product Test " + i);
		p.setDailyInstallment(new BigDecimal(14));
		p.setPrice(new BigDecimal(140));
		
		return p;
	}
	
	protected BillProduct createBillProduct(Bill bill, int count, Product product){
		BillProduct p = new BillProduct();
		p.setAmount(new BigDecimal(1000 * 2));
		p.setBill(bill);
		p.setCount(count);
		p.setDailyInstallment(new BigDecimal(count * 14));
		p.setProduct(product);
		
		return p;
	}
	
	protected Payment createPayment(Bill bill){
		Payment p = new Payment();
		p.setAmount(new BigDecimal(42));
		p.setBill(bill);
		p.setCollectorId(8);
		p.setDate(new Date());
		
		return p;
	}
	
	@Test
	public void processPeriod_ok(){

		Bill bill = new Bill();
		
		Trader t = this.createTrader(1);
		
		this.traderDao.saveOrUpdate(t);
		
		Client c = this.createClient(1);
		
		this.clientDao.saveOrUpdate(c);
		
		Product p1 = this.createProduct(1);
		this.productDao.saveOrUpdate(p1);
		Product p2 = this.createProduct(2);
		this.productDao.saveOrUpdate(p2);
		BillProduct bp1 = this.createBillProduct(bill, 1, p1);
		BillProduct bp2 = this.createBillProduct(bill, 2, p2);
		
		bill.addBillProduct(bp1);
		bill.addBillProduct(bp2);
		bill.setClient(c);
		bill.setCollectorId(8);
		bill.setCreditNumber(111L);
		bill.setStartDate(new Date());
		bill.setEndDate(DateUtils.addDays(new Date(), 30));
		bill.setOverdueDays(0);
		bill.setRemainingAmount(new BigDecimal(2958));
		bill.setStatus(Status.ACTIVE);
		bill.setTotalAmount(new BigDecimal(3000));
		bill.setTotalDailyInstallment(new BigDecimal(42));
		bill.setTrader(t);
		Payment payment = this.createPayment(bill);
		bill.addPayment(payment);
		
		this.billDao.saveOrUpdate(bill);
		
		bill = new Bill();
		
		bp1 = this.createBillProduct(bill, 1, p1);
		bp2 = this.createBillProduct(bill, 2, p2);
		
		bill.addBillProduct(bp1);
		bill.addBillProduct(bp2);
		bill.setClient(c);
		bill.setCollectorId(8);
		bill.setCreditNumber(112L);
		bill.setStartDate(new Date());
		bill.setEndDate(DateUtils.addDays(new Date(), 30));
		bill.setOverdueDays(0);
		bill.setRemainingAmount(new BigDecimal(2958));
		bill.setStatus(Status.ACTIVE);
		bill.setTotalAmount(new BigDecimal(3000));
		bill.setTotalDailyInstallment(new BigDecimal(42));
		bill.setTrader(t);
		payment = this.createPayment(bill);
		bill.addPayment(payment);
		
		this.billDao.saveOrUpdate(bill);
		
		PayrollDto payrollDto = this.instance.processPeriod(
				DateUtils.addDays(new Date(), -15), DateUtils.addDays(new Date(), 1));
		
		
		assertThat("error payroll dto null", payrollDto != null, is(true));
		
		Payroll payroll = this.payrollDao.findById(payrollDto.getId());
		
		assertThat("total Liq error", payroll.getTotal(), is(new BigDecimal(600)));
		assertThat("total Liq amount error", payroll.getTotalAmount(), is(new BigDecimal(600)));
		assertThat("total Liq discount error", payroll.getTotalDiscount(), is(new BigDecimal(0)));
		// FIXME: la liq supervisor es 0 pq no asocie ningun supervisor ...
		assertThat("total Liq total supervisor error", payroll.getTotalSupervisor(), is(new BigDecimal(0))); 
		
		assertThat("error payroll null", payroll != null, is(true));
		// la lista tiene 1 payrollItem, pq en esta liq solo hubo 1 vendedor con 2 facturas vendidas ...
		assertThat("error lista de payroll size != 1", payroll.getPayrollItemList().size(), is(1));
		
		PayrollItem payrollItem = payroll.getPayrollItemList().get(0);
		assertThat("total Liq Item collect error no 600", payrollItem.getSubtotalCollect(), is(new BigDecimal(600)));
		assertThat("total Liq Item discount error no 0", payrollItem.getSubtotalDiscount(), is(new BigDecimal(0)));
		assertThat("total Liq Item total error no 600", payrollItem.getTotalAmount(), is(new BigDecimal(600)));
		
		assertThat("error payroll items != 2", payrollItem.getItems().size(), is(2));
		
	}
}
