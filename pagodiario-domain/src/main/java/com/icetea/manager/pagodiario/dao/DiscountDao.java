package com.icetea.manager.pagodiario.dao;

import java.util.Date;
import java.util.List;

import com.icetea.manager.pagodiario.model.Discount;

public interface DiscountDao extends BasicIdentificableDao<Discount> {

	List<Discount> findByBillId(Long billId);

	List<Discount> find(Date dateFrom, Date dateTo);

}
