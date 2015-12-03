package com.icetea.manager.pagodiario.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
import com.icetea.manager.pagodiario.dao.CollectorDao;
import com.icetea.manager.pagodiario.dao.DevDao;
import com.icetea.manager.pagodiario.dao.PayrollDao;
import com.icetea.manager.pagodiario.dao.ProductDaoImpl;
import com.icetea.manager.pagodiario.dao.ProductReductionDao;
import com.icetea.manager.pagodiario.dao.TraderDaoImpl;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Bill.Status;
import com.icetea.manager.pagodiario.model.BillProduct;
import com.icetea.manager.pagodiario.model.Bonus;
import com.icetea.manager.pagodiario.model.BonusConciliationItem;
import com.icetea.manager.pagodiario.model.Client;
import com.icetea.manager.pagodiario.model.Collector;
import com.icetea.manager.pagodiario.model.Dev;
import com.icetea.manager.pagodiario.model.Discount;
import com.icetea.manager.pagodiario.model.Payment;
import com.icetea.manager.pagodiario.model.Payroll;
import com.icetea.manager.pagodiario.model.PayrollItem;
import com.icetea.manager.pagodiario.model.Product;
import com.icetea.manager.pagodiario.model.ProductReduction;
import com.icetea.manager.pagodiario.model.SupervisorPayrollItem;
import com.icetea.manager.pagodiario.model.Trader;
import com.icetea.manager.pagodiario.utils.NumberUtils;

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
	@Inject
	private DevDao devDao;
	@Inject
	private ProductReductionDao productReductionDao;
	@Inject
	private CollectorDao collectorDao;
	
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
	
	protected Collector createCollector(){
		Collector p = new Collector();
		p.setZone(1L);
		p.setDescription("COBRADOR TEST");
		
		return p;
	}
	
	protected Payment createPayment(Bill bill){
		Payment p = new Payment();
		p.setAmount(new BigDecimal(42));
		p.setBill(bill);
		p.setCollector(bill.getCollector());
		p.setDate(new Date());
		
		return p;
	}
	
	protected Dev createDev(Bill bill){
		Dev dev = new Dev();
		dev.setAmount(new BigDecimal(100));
		dev.setDate(new Date());
		dev.setObservations("devolucion test");
		dev.setBill(bill);
		bill.addDev(dev);
		
		return dev;
	}
	
	protected ProductReduction createProductReduction(Bill bill){
		ProductReduction p = new ProductReduction();
		p.setAmount(new BigDecimal(500));
		p.setBill(bill);
		p.setDate(new Date());
		p.setObservations("BAJA TEST");
		bill.addProductReduction(p);
		
		return p;
	}
	
	protected Bonus createBonus(Bill bill){
		Bonus bonus = new Bonus();
		bonus.setAmount(new BigDecimal(100));
		bonus.setDate(new Date());
		bonus.setObservations("bonus test");
		bonus.setBill(bill);
		bill.addBonus(bonus);
		
		return bonus;
	}
	
	protected BillProduct createBillProductReal(Bill bill, int count, Product product){
		BillProduct p = new BillProduct();
		p.setAmount(new BigDecimal(1000 * count));
		p.setBill(bill);
		p.setCount(count);
		p.setDailyInstallment(new BigDecimal(count * 5));
		p.setProduct(product);
		
		return p;
	}
	
	@Test
	public void processPeriod_ok(){

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
		bill.setStartDate(new Date());
		bill.setEndDate(DateUtils.addDays(new Date(), 30));
		bill.setOverdueDays(0);
		bill.setRemainingAmount(new BigDecimal(2958));
		bill.setStatus(Status.ACTIVE);
		bill.setTotalAmount(new BigDecimal(3000));
		bill.setTotalAmountToLiq(new BigDecimal(3000));
		bill.setTotalDailyInstallment(new BigDecimal(42));
		bill.setTrader(t);
		Payment payment = this.createPayment(bill);
		bill.addPayment(payment);
		
		Discount d1 = new Discount();
		d1.setAmount(new BigDecimal(1000));
		d1.setBill(bill);
		d1.setDate(DateUtils.addDays(new Date(), -2));
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
		bill.setStartDate(new Date());
		bill.setEndDate(DateUtils.addDays(new Date(), 30));
		bill.setOverdueDays(0);
		bill.setRemainingAmount(new BigDecimal(2958));
		bill.setStatus(Status.ACTIVE);
		bill.setTotalAmount(new BigDecimal(3000));
		bill.setTotalAmountToLiq(new BigDecimal(3000));
		bill.setTotalDailyInstallment(new BigDecimal(42));
		bill.setTrader(t);
		payment = this.createPayment(bill);
		bill.addPayment(payment);
		
		this.billDao.saveOrUpdate(bill);
		
		bill = new Bill();
		
		bp1 = this.createBillProduct(bill, 1, p1);
		bp2 = this.createBillProduct(bill, 2, p2);
		
		bill.addBillProduct(bp1);
		bill.addBillProduct(bp2);
		bill.setClient(c);
		bill.setCollector(collector);
		bill.setCreditNumber(113L);
		bill.setStartDate(new Date());
		bill.setEndDate(DateUtils.addDays(new Date(), 30));
		bill.setOverdueDays(0);
		bill.setRemainingAmount(new BigDecimal(2958));
		bill.setStatus(Status.ACTIVE);
		bill.setTotalAmount(new BigDecimal(3000));
		bill.setTotalAmountToLiq(new BigDecimal(3000));
		bill.setTotalDailyInstallment(new BigDecimal(42));
		bill.setTrader(t2);
		payment = this.createPayment(bill);
		bill.addPayment(payment);
		
		Discount d2 = new Discount();
		d2.setAmount(new BigDecimal(100));
		d2.setBill(bill);
		d2.setDate(DateUtils.addDays(new Date(), -3));
		d2.setObservations("Descuento de test 2");
		bill.addDiscount(d2);
		
		Dev dev = this.createDev(bill);
		this.devDao.saveOrUpdate(dev);
		
		ProductReduction productReduction = this.createProductReduction(bill);
		this.productReductionDao.saveOrUpdate(productReduction);
		
		this.billDao.saveOrUpdate(bill);
		
		bill = new Bill();
		
		bp1 = this.createBillProduct(bill, 1, p1);
		bp2 = this.createBillProduct(bill, 2, p2);
		
		bill.addBillProduct(bp1);
		bill.addBillProduct(bp2);
		bill.setClient(c);
		bill.setCollector(collector);
		bill.setCreditNumber(130L);
		bill.setStartDate(new Date());
		bill.setEndDate(DateUtils.addDays(new Date(), 30));
		bill.setOverdueDays(0);
		bill.setRemainingAmount(new BigDecimal(2958));
		bill.setStatus(Status.ACTIVE);
		bill.setTotalAmount(new BigDecimal(3000));
		bill.setTotalAmountToLiq(new BigDecimal(3000));
		bill.setTotalDailyInstallment(new BigDecimal(42));
		bill.setTrader(this.traderDao.findById(13L));
		payment = this.createPayment(bill);
		bill.addPayment(payment);
		
		this.billDao.saveOrUpdate(bill);
		

		PayrollDto payrollDto = this.instance.processPeriod(
				DateUtils.addDays(new Date(), -15), DateUtils.addDays(new Date(), 1));
		
		assertThat("error payroll dto null", payrollDto != null, is(true));
		
		Payroll payroll = this.payrollDao.findById(payrollDto.getId());
		
		assertThat("total Liq error", payroll.getTotal(), is(new BigDecimal(972)));
		assertThat("total Liq amount error", payroll.getTotalAmount(), is(new BigDecimal(1140)));
		assertThat("total Liq discount error", payroll.getTotalDiscount(), is(new BigDecimal(168)));
		// FIXME: la liq supervisor es 0 pq no asocie ningun supervisor ...
		assertThat("total Liq total supervisor error", payroll.getTotalSupervisor(), is(new BigDecimal(387)));
		
		List<SupervisorPayrollItem> supervisorPayrollItemList = payroll.getSupervisorPayrollItemList();
		
		assertThat("error supervisorPayrollItemList.size() != 1", supervisorPayrollItemList.size(), is(1));
		SupervisorPayrollItem supervisorPayrollItem = supervisorPayrollItemList.get(0);
		assertThat("error supervisorPayrollItem totalAmount", supervisorPayrollItem.getTotalAmount(), is(new BigDecimal(387)));
		
		assertThat("error payroll null", payroll != null, is(true));
		// la lista tiene 2 payrollItem, pq en esta liq solo hubo 1 vendedor con 2 facturas vendidas ...
		assertThat("error lista de payrollitems size != 3", payroll.getPayrollItemList().size(), is(3));
		
		PayrollItem payrollItem = payroll.getPayrollItemList().get(0);
		assertThat("total Liq Item collect error no 600", payrollItem.getSubtotalCollect(), is(new BigDecimal(600)));
		assertThat("total Liq Item discount error no 100", payrollItem.getSubtotalDiscount(), is(new BigDecimal(84)));
		assertThat("total Liq Item total error no 500", payrollItem.getTotalAmount(), is(new BigDecimal(516)));
		
		assertThat("error payroll items != 2", payrollItem.getItems().size(), is(2));
		
		payrollItem = payroll.getPayrollItemList().get(1);
		assertThat("total Liq Item 2 collect error no 300", payrollItem.getSubtotalCollect(), is(new BigDecimal(240)));
		assertThat("total Liq Item 2 discount error no 10", payrollItem.getSubtotalDiscount(), is(new BigDecimal(42)));
		assertThat("total Liq Item 2 total error no 290", payrollItem.getTotalAmount(), is(new BigDecimal(198)));
		
		assertThat("error payroll 2 items size", payrollItem.getItems().size(), is(3)); // 3 = 1 CRED + 1 DEV + 1 BAJA
		
		payrollItem = payroll.getPayrollItemList().get(2);
		assertThat("total Liq Item 2 collect error no 300", payrollItem.getSubtotalCollect(), is(new BigDecimal(300)));
		assertThat("total Liq Item 2 discount error no 10", payrollItem.getSubtotalDiscount(), is(new BigDecimal(42)));
		assertThat("total Liq Item 2 total error no 290", payrollItem.getTotalAmount(), is(new BigDecimal(258)));
		
		assertThat("error payroll items size", payrollItem.getItems().size(), is(1));
	}
	
	@Test
	public void processPeriod_bonus_ok(){

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
		BillProduct bp1 = this.createBillProductReal(bill, 201, p1);
		BillProduct bp2 = this.createBillProductReal(bill, 100, p2);
		
		bill.addBillProduct(bp1);
		bill.addBillProduct(bp2);
		bill.setClient(c);
		
		Collector collector = this.createCollector();
		this.collectorDao.save(collector);
		
		bill.setCollector(collector);
		bill.setCreditNumber(111L);
		bill.setStartDate(new Date());
		bill.setEndDate(DateUtils.addDays(new Date(), 30));
		bill.setOverdueDays(0);
		bill.setRemainingAmount(
				NumberUtils.add(bp1.getAmount(), bp2.getAmount()).subtract(
						NumberUtils.add(bp1.getDailyInstallment(), bp2.getDailyInstallment())));
		bill.setStatus(Status.ACTIVE);
		bill.setTotalAmount(NumberUtils.add(bp1.getAmount(), bp2.getAmount()));
		bill.setTotalAmountToLiq(NumberUtils.add(bp1.getAmount(), bp2.getAmount()));
		bill.setTotalDailyInstallment(NumberUtils.add(bp1.getDailyInstallment(), bp2.getDailyInstallment()));
		bill.setTrader(t);
		Payment payment = this.createPayment(bill);
		bill.addPayment(payment);
		
		this.billDao.saveOrUpdate(bill);
		
		PayrollDto payrollDto = this.instance.processPeriod(
				DateUtils.addDays(new Date(), -15), DateUtils.addDays(new Date(), 1));
		
		assertThat("error payroll dto null", payrollDto != null, is(true));
		
		Payroll payroll = this.payrollDao.findById(payrollDto.getId());
		
		assertThat("total Liq error", payroll.getTotal(), is(new BigDecimal("30659.16")));
		assertThat("total Liq amount error", payroll.getTotalAmount(), is(new BigDecimal("30701.16")));
		assertThat("total Liq discount error", payroll.getTotalDiscount(), is(new BigDecimal(42)));
		// FIXME: la liq supervisor es 0 pq no asocie ningun supervisor ...
		assertThat("total Liq total supervisor error", payroll.getTotalSupervisor(), is(new BigDecimal("15329.58")));
		
		List<SupervisorPayrollItem> supervisorPayrollItemList = payroll.getSupervisorPayrollItemList();
		
		assertThat("error supervisorPayrollItemList.size() != 1", supervisorPayrollItemList.size(), is(1));
		SupervisorPayrollItem supervisorPayrollItem = supervisorPayrollItemList.get(0);
		assertThat("error supervisorPayrollItem totalAmount", supervisorPayrollItem.getTotalAmount(), is(new BigDecimal("15329.58")));
		
		assertThat("error payroll null", payroll != null, is(true));
		// la lista tiene 2 payrollItem, pq en esta liq solo hubo 1 vendedor con 2 facturas vendidas ...
		assertThat("error lista de payrollitems size", payroll.getPayrollItemList().size(), is(1));
		
		PayrollItem payrollItem = payroll.getPayrollItemList().get(0);
		assertThat("total Liq Item collect error no 600", payrollItem.getSubtotalCollect(), is(new BigDecimal("30701.16")));
		assertThat("total Liq Item discount error no 100", payrollItem.getSubtotalDiscount(), is(new BigDecimal(42)));
		assertThat("total Liq Item total error no 500", payrollItem.getTotalAmount(), is(new BigDecimal("30659.16")));
		
		assertThat("error payroll items != 2", payrollItem.getItems().size(), is(1));
		
		BonusConciliationItem bonusItem = payrollItem.getBonusItem();
		
		assertThat("bonusItem error", bonusItem.getCollectAmount(), is(new BigDecimal("601.16")));
		assertThat("bonusItem error", bonusItem.getDescription(), is("PREMIO 2% (productos periodo(301)/Dias Habiles)"));
	}
	
	@Test
	public void processPeriod_bonus_some_traders_same_supervisor_get_bonus_ok(){

		Bill bill = new Bill();
		// este si cobrara bono
		Trader supervisor = this.createTrader(3);
		supervisor.setSupervisor(true);
		// este si cobrara bono
		Trader t = this.createTrader(1);
		
		t.setParent(supervisor);
		this.traderDao.saveOrUpdate(t);
		// este si cobrara bono
		Trader t2 = this.createTrader(2);
		t2.setParent(supervisor);
		this.traderDao.saveOrUpdate(t2);
		supervisor.addTrader(t);
		supervisor.addTrader(t2);
		
		this.traderDao.saveOrUpdate(supervisor);
		
		// este no cobrara bono
		Trader t3 = this.createTrader(4);
		this.traderDao.saveOrUpdate(t3);
		
		Client c = this.createClient(1);
		this.clientDao.saveOrUpdate(c);
		
		Product p1 = this.createProduct(1);
		this.productDao.saveOrUpdate(p1);
		Product p2 = this.createProduct(2);
		this.productDao.saveOrUpdate(p2);
		BillProduct bp1 = this.createBillProductReal(bill, 50, p1);
		BillProduct bp2 = this.createBillProductReal(bill, 50, p2);
		
		bill.addBillProduct(bp1);
		bill.addBillProduct(bp2);
		bill.setClient(c);
		
		Collector collector = this.createCollector();
		this.collectorDao.save(collector);
		
		bill.setCollector(collector);
		bill.setCreditNumber(111L);
		bill.setStartDate(new Date());
		bill.setEndDate(DateUtils.addDays(new Date(), 30));
		bill.setOverdueDays(0);
		bill.setRemainingAmount(
				NumberUtils.add(bp1.getAmount(), bp2.getAmount()).subtract(
						NumberUtils.add(bp1.getDailyInstallment(), bp2.getDailyInstallment())));
		bill.setStatus(Status.ACTIVE);
		bill.setTotalAmount(NumberUtils.add(bp1.getAmount(), bp2.getAmount()));
		bill.setTotalAmountToLiq(NumberUtils.add(bp1.getAmount(), bp2.getAmount()));
		bill.setTotalDailyInstallment(NumberUtils.add(bp1.getDailyInstallment(), bp2.getDailyInstallment()));
		bill.setTrader(t);
		Payment payment = this.createPayment(bill);
		bill.addPayment(payment);
		
		this.billDao.saveOrUpdate(bill);
		
		// 2 factura
		bill = new Bill();
		bp1 = this.createBillProductReal(bill, 100, p1);
		bp2 = this.createBillProductReal(bill, 50, p2);
		
		bill.addBillProduct(bp1);
		bill.addBillProduct(bp2);
		bill.setClient(c);
		
		bill.setCollector(collector);
		bill.setCreditNumber(112L);
		bill.setStartDate(new Date());
		bill.setEndDate(DateUtils.addDays(new Date(), 30));
		bill.setOverdueDays(0);
		bill.setRemainingAmount(
				NumberUtils.add(bp1.getAmount(), bp2.getAmount()).subtract(
						NumberUtils.add(bp1.getDailyInstallment(), bp2.getDailyInstallment())));
		bill.setStatus(Status.ACTIVE);
		bill.setTotalAmount(NumberUtils.add(bp1.getAmount(), bp2.getAmount()));
		bill.setTotalAmountToLiq(NumberUtils.add(bp1.getAmount(), bp2.getAmount()));
		bill.setTotalDailyInstallment(NumberUtils.add(bp1.getDailyInstallment(), bp2.getDailyInstallment()));
		bill.setTrader(t2);
		payment = this.createPayment(bill);
		bill.addPayment(payment);
		
		this.billDao.saveOrUpdate(bill);
		
		// 3 factura
		bill = new Bill();
		bp1 = this.createBillProductReal(bill, 60, p1);
		bp2 = this.createBillProductReal(bill, 40, p2);
		
		bill.addBillProduct(bp1);
		bill.addBillProduct(bp2);
		bill.setClient(c);
		
		bill.setCollector(collector);
		bill.setCreditNumber(113L);
		bill.setStartDate(new Date());
		bill.setEndDate(DateUtils.addDays(new Date(), 30));
		bill.setOverdueDays(0);
		bill.setRemainingAmount(
				NumberUtils.add(bp1.getAmount(), bp2.getAmount()).subtract(
						NumberUtils.add(bp1.getDailyInstallment(), bp2.getDailyInstallment())));
		bill.setStatus(Status.ACTIVE);
		bill.setTotalAmount(NumberUtils.add(bp1.getAmount(), bp2.getAmount()));
		bill.setTotalAmountToLiq(NumberUtils.add(bp1.getAmount(), bp2.getAmount()));
		bill.setTotalDailyInstallment(NumberUtils.add(bp1.getDailyInstallment(), bp2.getDailyInstallment()));
		bill.setTrader(t3);
		payment = this.createPayment(bill);
		bill.addPayment(payment);
		
		this.billDao.saveOrUpdate(bill);
		
		PayrollDto payrollDto = this.instance.processPeriod(
				DateUtils.addDays(new Date(), -15), DateUtils.addDays(new Date(), 1));
		
		assertThat("error payroll dto null", payrollDto != null, is(true));
		
		Payroll payroll = this.payrollDao.findById(payrollDto.getId());
		
		assertThat("total Liq error", payroll.getTotal(), is(new BigDecimal("35374")));
		assertThat("total Liq amount error", payroll.getTotalAmount(), is(new BigDecimal("35500")));
		assertThat("total Liq discount error", payroll.getTotalDiscount(), is(new BigDecimal(126)));
		// FIXME: la liq supervisor es 0 pq no asocie ningun supervisor ...
		assertThat("total Liq total supervisor error", payroll.getTotalSupervisor(), is(new BigDecimal("12708")));
		
		List<SupervisorPayrollItem> supervisorPayrollItemList = payroll.getSupervisorPayrollItemList();
		
		assertThat("error supervisorPayrollItemList.size() != 1", supervisorPayrollItemList.size(), is(1));
		SupervisorPayrollItem supervisorPayrollItem = supervisorPayrollItemList.get(0);
		assertThat("error supervisorPayrollItem totalAmount", supervisorPayrollItem.getTotalAmount(), is(new BigDecimal("12708")));
		
		assertThat("error payroll null", payroll != null, is(true));
		// la lista tiene 2 payrollItem, pq en esta liq solo hubo 1 vendedor con 2 facturas vendidas ...
		assertThat("error lista de payrollitems size", payroll.getPayrollItemList().size(), is(3));
		
		PayrollItem payrollItem = payroll.getPayrollItemList().get(0);
		assertThat("total Liq Item collect error no 600", payrollItem.getSubtotalCollect(), is(new BigDecimal("10200")));
		assertThat("total Liq Item discount error no 100", payrollItem.getSubtotalDiscount(), is(new BigDecimal(42)));
		assertThat("total Liq Item total error no 500", payrollItem.getTotalAmount(), is(new BigDecimal("10158")));
		
		assertThat("error payroll items != 2", payrollItem.getItems().size(), is(1));
		
		BonusConciliationItem bonusItem = payrollItem.getBonusItem();
		
		assertThat("bonusItem error", bonusItem.getCollectAmount(), is(new BigDecimal("200")));
		assertThat("bonusItem error", bonusItem.getDescription(), is("PREMIO 2% (productos periodo(250)/Dias Habiles)"));
	}
	
}
