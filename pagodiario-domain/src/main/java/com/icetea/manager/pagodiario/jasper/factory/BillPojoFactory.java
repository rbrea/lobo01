package com.icetea.manager.pagodiario.jasper.factory;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.BillDto;
import com.icetea.manager.pagodiario.api.dto.BillProductDto;
import com.icetea.manager.pagodiario.api.dto.ProductDto;
import com.icetea.manager.pagodiario.api.pojo.jasper.BillPojo;
import com.icetea.manager.pagodiario.api.pojo.jasper.ProductPojo;
import com.icetea.manager.pagodiario.service.BillService;
import com.icetea.manager.pagodiario.service.ClientService;
import com.icetea.manager.pagodiario.service.ProductService;
import com.icetea.manager.pagodiario.service.TraderService;

@Named
public class BillPojoFactory {

	private final ClientService clientService;
	private final TraderService traderService;
	private final BillService billService;
	private final ProductService productService;
	
	@Inject
	public BillPojoFactory(ClientService clientService,
			TraderService traderService, 
			BillService billService,
			ProductService productService) {
		super();
		this.clientService = clientService;
		this.traderService = traderService;
		this.billService = billService;
		this.productService = productService;
	}
	
	public BillPojo create(Long billId){
		
		BillDto billDto = this.billService.searchById(billId);
		
		if(billDto == null){
			return null;
		}
		
		BillPojo pojo = new BillPojo();
		List<BillProductDto> billProducts = billDto.getBillProducts();
		for(BillProductDto bp : billProducts){
			
			ProductDto p = this.productService.searchById(bp.getProductId());
			
			ProductPojo k = new ProductPojo();
			k.setCount(bp.getCount());
			k.setName(p.getDescription());
			pojo.getProducts().add(k);
		}
		pojo.setClient(this.clientService.searchById(billDto.getClientId()));
		pojo.setCollectorId(billDto.getCollectorId());
		pojo.setCreditNumber(billDto.getCreditNumber());
		pojo.setEndDate(billDto.getEndDate());
		pojo.setOverdueDays(billDto.getOverdueDays());
		pojo.setRemainingAmount(billDto.getRemainingAmount());
		pojo.setStartDate(billDto.getStartDate());
		pojo.setStatus(billDto.getStatus());
		pojo.setTotalAmount(billDto.getTotalAmount());
		pojo.setTotalDailyInstallment(billDto.getTotalDailyInstallment());
		pojo.setTrader(this.traderService.searchById(billDto.getTraderId()));
		
		return pojo;
	}
	
}
