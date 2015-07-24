package com.icetea.manager.pagodiario.dao;

import java.util.Date;
import java.util.List;

import com.icetea.manager.pagodiario.model.ProductReduction;

public interface ProductReductionDao extends
		BasicIdentificableDao<ProductReduction> {

	List<ProductReduction> findByBillId(Long billId);

	List<ProductReduction> find(Date dateFrom, Date dateTo);

}
