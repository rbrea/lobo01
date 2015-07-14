package com.icetea.manager.pagodiario.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.google.common.base.Preconditions;
import com.icetea.manager.pagodiario.api.dto.BillDto;
import com.icetea.manager.pagodiario.api.dto.BillProductDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.dao.BillDao;
import com.icetea.manager.pagodiario.dao.ClientDao;
import com.icetea.manager.pagodiario.dao.ProductDao;
import com.icetea.manager.pagodiario.dao.TraderDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.BillProduct;
import com.icetea.manager.pagodiario.model.Client;
import com.icetea.manager.pagodiario.model.Product;
import com.icetea.manager.pagodiario.model.Trader;
import com.icetea.manager.pagodiario.transformer.BillDtoModelTransformer;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class BillServiceImpl 
	extends BasicIdentifiableServiceImpl<Bill, BillDao, BillDto, BillDtoModelTransformer>
		implements BillService {

	private final ClientDao clientDao;
	private final TraderDao traderDao;
	private final ProductDao productDao;
	
	@Inject
	public BillServiceImpl(BillDao dao, BillDtoModelTransformer transformer,
			ClientDao clientDao, TraderDao traderDao, ProductDao productDao) {
		super(dao, transformer);
		this.clientDao = clientDao;
		this.traderDao = traderDao;
		this.productDao = productDao;
	}

	@Override
	public BillDto insert(BillDto d) {
		
		List<BillProductDto> billProducts = d.getBillProducts();
		
		ErrorTypedConditions.checkArgument(billProducts != null && !billProducts.isEmpty(), 
				String.format("No se registran productos asociados a la factura"), 
				ErrorType.PRODUCT_REQUIRED);
		
		
		Client client = this.clientDao.findById(d.getClientId());
		
		ErrorTypedConditions.checkArgument(client != null, String.format("Cliente no encontrado con id: %s", d.getClientId()), 
				ErrorType.CLIENT_NOT_FOUND);
		
		Trader trader = this.traderDao.findById(d.getTraderId());
		
		ErrorTypedConditions.checkArgument(trader != null, String.format("Vendedor no encontrado con id: %s", d.getTraderId()), 
				ErrorType.TRADER_NOT_FOUND);
		
		Bill e = new Bill();
		
		for(BillProductDto p : billProducts){
			BillProduct bp = new BillProduct();
			bp.setAmount(NumberUtils.toBigDecimal(p.getAmount()));
			bp.setBill(e);
			bp.setCount(p.getCount());
			bp.setDailyInstallment(NumberUtils.toBigDecimal(p.getDailyInstallment()));
			Product product = this.productDao.findById(p.getProductId());
			
			ErrorTypedConditions.checkArgument(product != null, String.format("Producto no encontrado con id: %s", p.getProductId()), 
					ErrorType.PRODUCT_NOT_FOUND);
			
			bp.setProduct(product);
			e.addBillProduct(bp);
		}
		e.setClient(client);
		e.setStartDate(DateUtils.parseDate(d.getStartDate()));
		e.setTotalAmount(NumberUtils.toBigDecimal(d.getTotalAmount()));
		e.setTotalDailyInstallment(NumberUtils.toBigDecimal(d.getTotalDailyInstallment()));
		e.setTrader(trader);
		
		this.getDao().saveOrUpdate(e);
		
		return this.getTransformer().transform(e);
	}

	@Override
	public BillDto update(BillDto d) {

		Preconditions.checkArgument(d.getId() != null, "id required");
		
		Bill e = this.getDao().findById(d.getId());
		
		ErrorTypedConditions.checkArgument(e != null, ErrorType.BILL_NOT_FOUND);
		
		this.getDao().saveOrUpdate(e);
		
		return this.getTransformer().transform(e);
	}
	
}
