package com.icetea.manager.pagodiario.service;

import java.util.Date;
import java.util.List;

import com.icetea.manager.pagodiario.api.dto.BillDetailDto;
import com.icetea.manager.pagodiario.api.dto.BillDto;
import com.icetea.manager.pagodiario.api.dto.BillInfoDto;
import com.icetea.manager.pagodiario.api.pojo.jasper.BillTicketPojo;
import com.icetea.manager.pagodiario.model.Bill;

public interface BillService extends BasicIdentifiableService<Bill, BillDto> {

	BillDto insert(BillDto input);

	BillDto update(BillDto d);

	List<BillDto> searchActives();
	
	List<BillDto> searchActives(Long collectorId);

	boolean updateOverdueDays(Long billId);

	List<BillDto> searchByCollectorId(Long collectorId);

	BillDetailDto searchDetail(Long billId);

	List<BillTicketPojo> searchBillsByCollectorZone(String ticketDateValue, Long collectorZone,
			String fromDate, String toDate);

	List<BillDto> searchExpires();

	BillDto searchByCreditNumber(Long creditNumber);

	List<BillDto> searchByFilter(Long creditNumber, Long collectorId,
			String statusArg);

	List<BillDto> searchToMakeVouchers(Date date);

	List<BillDto> searchFinalizedInTime();

	BillInfoDto setCancelDiscount(Long id);

}
