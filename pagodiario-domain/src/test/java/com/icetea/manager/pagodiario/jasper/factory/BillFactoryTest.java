package com.icetea.manager.pagodiario.jasper.factory;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.BillDto;
import com.icetea.manager.pagodiario.api.dto.BillProductDto;
import com.icetea.manager.pagodiario.api.dto.ClientDto;
import com.icetea.manager.pagodiario.api.dto.CollectorDto;
import com.icetea.manager.pagodiario.api.dto.ProductDto;
import com.icetea.manager.pagodiario.api.dto.TraderDto;
import com.icetea.manager.pagodiario.api.pojo.jasper.BillListPojo;
import com.icetea.manager.pagodiario.service.BillService;
import com.icetea.manager.pagodiario.service.ClientService;
import com.icetea.manager.pagodiario.service.CollectorService;
import com.icetea.manager.pagodiario.service.ProductService;
import com.icetea.manager.pagodiario.service.TraderService;
import com.icetea.manager.pagodiario.utils.StringUtils;

@ContextConfiguration(locations = {"classpath:hibernate-db-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BillFactoryTest {
	@SuppressWarnings("unused")
	private BillPojoFactory instance;
	@Inject
	private BillListPojoFactory billListPojoFactory;
	@Inject
	private BillService billService;
	@Inject
	private ClientService clientService;
	@Inject
	private TraderService traderService;
	@Inject
	private ProductService productService;
	@Inject
	private CollectorService collectorService;
	
	@Before
	public void init(){
		this.instance = new BillPojoFactory(clientService, traderService, billService, productService);
	}
	
	@Test
	public void create_ok() throws JsonProcessingException{
		
		ProductDto p = new ProductDto();
		p.setCode("C001");
		p.setDescription("Toalla XL");
		p.setPrice("100.00");
		
		this.productService.insert(p);
		
		TraderDto t = new TraderDto();
		t.setAddress("domi 112");
		t.setCity("castelar");
		t.setDocumentNumber(1233213L);
		t.setDocumentType("DNI");
		t.setEmail("email@gmail.com");
		t.setName("Rodri");
		t.setNearStreets("bsas e irygoyen");
		t.setPhone("1146279900");
		t.setSupervisor(false);
		
		this.traderService.insert(t);
		
		ClientDto c = new ClientDto();
		c.setAddress("saraza 112");
		c.setCity("moron");
		c.setDocumentNumber(99989899L);
		c.setDocumentType("DNI");
		c.setEmail("otroemail@gmail.com");
		c.setName("Eliana");
		c.setNearStreets("");
		c.setPhone("1154544555");
		c.setCompanyAddress("domicilio 998");
		c.setCompanyCity("Ciudadela");
		c.setCompanyPhone("1190090090");
		c.setCompanyType("Kiosco");
		
		this.clientService.insert(c);
		
		BillDto b = new BillDto();
		BillProductDto bp = new BillProductDto();
		bp.setAmount("2000.00");
		bp.setCount(10);
		bp.setDailyInstallment("10.00");
		bp.setPrice("100.00");
		bp.setProductId(2L);
		
		b.getBillProducts().add(bp);
		b.setClientId(2L);
		
		CollectorDto collectorDto = new CollectorDto();
		collectorDto.setId(1L);
		collectorDto.setZone(1L);
		collectorDto.setDescription("COBRADOR TEST");
		
		this.collectorService.insert(collectorDto);
		
		b.setCollectorId(1L);
		b.setCreditNumber(7878787878L);
		b.setStartDate("10/07/2015");
		b.setTotalAmount("2000.00");
		b.setTotalDailyInstallment("10.00");
		b.setTraderId(2L);
		
		BillDto dto = this.billService.insert(b);
		
		BillListPojo pojo = this.billListPojoFactory.create(Lists.newArrayList(1L, dto.getId()));
		
		String json = new ObjectMapper().writeValueAsString(pojo);
		
		System.out.println(json);
		
		Assert.assertTrue(StringUtils.isNotBlank(json));
		
	}
	
}
