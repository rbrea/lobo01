package com.icetea.manager.pagodiario.dao;

import java.util.List;

import com.icetea.manager.pagodiario.model.BillProduct;

public interface BillProductDao extends BasicIdentificableDao<BillProduct> {

	List<BillProduct> findByProductId(Long productId);

}
