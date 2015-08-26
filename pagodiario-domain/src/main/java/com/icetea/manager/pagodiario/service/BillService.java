package com.icetea.manager.pagodiario.service;

import java.util.List;

import com.icetea.manager.pagodiario.api.dto.BillDetailDto;
import com.icetea.manager.pagodiario.api.dto.BillDto;
import com.icetea.manager.pagodiario.api.pojo.jasper.BillTicketPojo;
import com.icetea.manager.pagodiario.model.Bill;

public interface BillService extends BasicIdentifiableService<Bill, BillDto> {

	BillDto insert(BillDto input);

	BillDto update(BillDto d);

	List<BillDto> searchActives();

	boolean updateOverdueDays(Long billId);

	List<BillDto> searchByCollectorId(Long collectorId);

	BillDetailDto searchDetail(Long billId);

	List<BillTicketPojo> searchBillsByCollectorId(Integer collectorId,
			String fromDate, String toDate);

	List<BillDto> searchExpires();

}
