package com.icetea.manager.pagodiario.jasper.factory;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.icetea.manager.pagodiario.api.pojo.jasper.BillListPojo;

@Named
public class BillListPojoFactory {

	private final BillPojoFactory billPojoFactory;

	@Inject
	public BillListPojoFactory(BillPojoFactory billPojoFactory) {
		super();
		this.billPojoFactory = billPojoFactory;
	}

	public BillListPojo create(List<Long> billIdList){
		BillListPojo r = new BillListPojo();
		
		for(Long id : billIdList){
			r.getBills().add(this.billPojoFactory.create(id));
		}
		
		return r;
	}
	
}
