package com.icetea.manager.pagodiario.dao;

import java.util.List;

import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Bill.Status;

public interface BillDao extends BasicIdentificableDao<Bill> {

	List<Bill> find(Status status);

}
