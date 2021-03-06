package com.icetea.manager.pagodiario.csv.transformer;

import java.util.List;

import javax.inject.Named;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.ClientDto;
import com.icetea.manager.pagodiario.api.dto.csv.ClientFilterCsvDto;
import com.icetea.manager.pagodiario.utils.StringUtils;

@Named
public class ClientFilterTransformer {

	public ClientFilterCsvDto transform(ClientDto input){
		ClientFilterCsvDto out = new ClientFilterCsvDto();
		out.setDni(StringUtils.valueOf(input.getDocumentNumber()));
		out.setDomicilioEmpresa(input.getCompanyAddress());
		out.setLocalidadEmpresa(input.getCompanyCity());
		out.setNombreApellido(input.getName());
		out.setTelEmpresa(input.getCompanyPhone());
		out.setTipoDeComercio(input.getCompanyType());
		
		out.setId(input.getId());
		out.setDomicilio(input.getAddress());
		out.setTel(input.getPhone());
		out.setEmail(input.getEmail());
		out.setLocalidad(input.getCity());
		out.setTuvoCancelacion(StringUtils.isNotBlank(input.getCancelationMark()) ? "SI" : "NO");
		out.setTuvoDevolucion(StringUtils.isNotBlank(input.getReductionMark()) ? "SI" : "NO");
		
		return out;
	}
	
	public List<ClientFilterCsvDto> transform(List<ClientDto> list){
		
		if(list == null){
			return Lists.newArrayList();
		}
		List<ClientFilterCsvDto> out = Lists.newArrayList();
		for (ClientDto c : list) {
			out.add(this.transform(c));
		}
		
		return out;
	}
	
}
