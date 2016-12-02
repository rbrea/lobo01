package com.icetea.manager.pagodiario.service;

import java.util.List;

import com.icetea.manager.pagodiario.api.dto.ClientDto;
import com.icetea.manager.pagodiario.model.Client;

public interface ClientService extends BasicIdentifiableService<Client, ClientDto> {

	ClientDto insert(ClientDto input);

	ClientDto update(ClientDto d);

	List<ClientDto> searchByName(String q);

	List<ClientDto> filter(Long collectorId, Boolean hasReductionMark, Boolean cancelationMark, String statusArg, Boolean cancelationOnDate,
			Boolean cancelationBeforeMore, String dateFrom, String dateTo);

}
