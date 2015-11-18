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

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.BillProductDto;
import com.icetea.manager.pagodiario.api.dto.DevAddDto;
import com.icetea.manager.pagodiario.dao.BillDaoImpl;
import com.icetea.manager.pagodiario.dao.BillProductDao;
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
import com.icetea.manager.pagodiario.model.Client;
import com.icetea.manager.pagodiario.model.Collector;
import com.icetea.manager.pagodiario.model.Dev;
import com.icetea.manager.pagodiario.model.Payment;
import com.icetea.manager.pagodiario.model.Product;
import com.icetea.manager.pagodiario.model.ProductReduction;
import com.icetea.manager.pagodiario.model.Trader;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@ContextConfiguration(locations = {"classpath:hibernate-db-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class DevServiceTest {

	@Inject
	private DevServiceImpl devService;
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
	@Inject
	private BillProductDao billProductDao;
	
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
	
	protected Product createProduct(int i, BigDecimal installmentAmount, BigDecimal price){
		Product p = new Product();
		p.setCode("00" + i);
		p.setDescription("Product Test " + i);
		p.setDailyInstallment(installmentAmount);
		p.setPrice(price);
		
		return p;
	}
	
	protected BillProduct createBillProduct(Bill bill, int count, Product product){
		BillProduct p = new BillProduct();
		p.setAmount(NumberUtils.multiply(product.getPrice(), new BigDecimal(count)));
		p.setBill(bill);
		p.setCount(count);
		p.setDailyInstallment(NumberUtils.multiply(product.getDailyInstallment(), new BigDecimal(count)));
		p.setProduct(product);
		
		return p;
	}
	
	protected Collector createCollector(){
		Collector p = new Collector();
		p.setZone(1L);
		p.setDescription("COBRADOR TEST");
		
		return p;
	}
	
	protected Payment createPayment(Bill bill, BigDecimal amount){
		Payment p = new Payment();
		p.setAmount(amount);
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
	public void insert_ok(){
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
		
		Product p1 = this.createProduct(1, new BigDecimal(20), new BigDecimal(200));
		this.productDao.saveOrUpdate(p1);
		Product p2 = this.createProduct(2, new BigDecimal(10), new BigDecimal(100));
		this.productDao.saveOrUpdate(p2);
		BillProduct bp1 = this.createBillProduct(bill, 1, p1);
		BillProduct bp2 = this.createBillProduct(bill, 2, p2);
		
		this.billProductDao.saveOrUpdate(bp1);
		this.billProductDao.saveOrUpdate(bp2);
		
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
		bill.setStatus(Status.ACTIVE);
		
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal installmentAmount = BigDecimal.ZERO;
		
		for(BillProduct bp : bill.getBillProducts()){
			totalAmount = NumberUtils.add(totalAmount, bp.getAmount());
			installmentAmount = NumberUtils.add(installmentAmount, bp.getDailyInstallment());
		}
		
		bill.setTotalAmount(totalAmount);
		bill.setTotalDailyInstallment(installmentAmount);
		bill.setTrader(t);
		
		BigDecimal paymentAmount = NumberUtils.subtract(totalAmount, installmentAmount);
		
		Payment payment = this.createPayment(bill, paymentAmount);
		bill.addPayment(payment);

		bill.setRemainingAmount(NumberUtils.subtract(totalAmount, installmentAmount));
		
		this.billDao.saveOrUpdate(bill);

		DevAddDto dto = new DevAddDto();
		dto.setBillId(bill.getId());
		dto.setObservations("test 1");
		dto.setSelectedDate("10/10/2015");
		List<BillProductDto> billProducts = Lists.newArrayList();
		
		for(BillProduct bp : bill.getBillProducts()){
			BillProductDto bpd = new BillProductDto();
			bpd.setId(bp.getId());
			bpd.setAmount(NumberUtils.toString(bp.getAmount()));
			bpd.setCount(bp.getCount());
			bpd.setDailyInstallment(NumberUtils.toString(bp.getDailyInstallment()));
			bpd.setPrice(NumberUtils.toString(bp.getProduct().getPrice()));
			bpd.setProductCode(bp.getProduct().getCode());
			bpd.setProductDescription(bp.getProduct().getDescription());
			bpd.setProductId(bp.getProduct().getId());
			
			billProducts.add(bpd);
		}
		
		BillProductDto bpd1 = billProducts.get(1);
		bpd1.setAmount("100");
		bpd1.setCount(1);
		bpd1.setDailyInstallment("10");
		
		dto.setBillProducts(billProducts);
		
		DevAddDto result = this.devService.insert(dto);
		
		assertThat("result wrong", result != null, is(true));
		assertThat("result wrong", result.getBillStatus(), is("ACTIVE"));
		assertThat("result wrong", result.getInstallmentAmount(), is(NumberUtils.toString(new BigDecimal(30))));
		assertThat("result wrong", result.getTotalAmount(), is(NumberUtils.toString(new BigDecimal(300))));
		assertThat("result wrong", result.getRemainingAmount(), is(NumberUtils.toString(new BigDecimal(260))));
		assertThat("result wrong", result.getOverdueDays(), is(-3));
	}
	
}
