package com.icetea.manager.pagodiario.service;

import com.icetea.manager.pagodiario.api.dto.BillDto;
import com.icetea.manager.pagodiario.model.Bill;

public interface BillService extends BasicIdentifiableService<Bill, BillDto> {

	BillDto insert(BillDto input);

	BillDto update(BillDto d);

}
