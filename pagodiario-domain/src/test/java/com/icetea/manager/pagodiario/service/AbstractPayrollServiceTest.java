package com.icetea.manager.pagodiario.service;

import java.math.BigDecimal;
import java.util.Date;

import javax.inject.Inject;

import com.icetea.manager.pagodiario.dao.BillDaoImpl;
import com.icetea.manager.pagodiario.dao.ClientDaoImpl;
import com.icetea.manager.pagodiario.dao.CollectorDao;
import com.icetea.manager.pagodiario.dao.DevDao;
import com.icetea.manager.pagodiario.dao.PayrollDao;
import com.icetea.manager.pagodiario.dao.ProductDaoImpl;
import com.icetea.manager.pagodiario.dao.ProductReductionDao;
import com.icetea.manager.pagodiario.dao.TraderDaoImpl;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.BillProduct;
import com.icetea.manager.pagodiario.model.Bonus;
import com.icetea.manager.pagodiario.model.Client;
import com.icetea.manager.pagodiario.model.Collector;
import com.icetea.manager.pagodiario.model.Dev;
import com.icetea.manager.pagodiario.model.Payment;
import com.icetea.manager.pagodiario.model.Product;
import com.icetea.manager.pagodiario.model.ProductReduction;
import com.icetea.manager.pagodiario.model.Trader;

public abstract class AbstractPayrollServiceTest {

	@Inject
	protected BillDaoImpl billDao;
	@Inject
	protected TraderDaoImpl traderDao;
	@Inject
	protected ProductDaoImpl productDao;
	@Inject
	protected ClientDaoImpl clientDao;
	@Inject
	protected PayrollDao payrollDao;
	@Inject
	protected DevDao devDao;
	@Inject
	protected ProductReductionDao productReductionDao;
	@Inject
	protected CollectorDao collectorDao;
	
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
	
}
