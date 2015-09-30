package com.icetea.manager.pagodiario.jasper.factory.sample;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.ClientDto;

public class ClientReportFactory {

	public static Collection<ClientDto> createBeanCollection(){
		List<ClientDto> clients = Lists.newArrayList();
		
		ClientDto c = new ClientDto();
		c.setAddress("domicilio particular 111");
		c.setCity("Ciudad 1");
		c.setCompanyAddress("domicilio compania 1");
		c.setCompanyCity("ciudad compania 1");
		c.setCompanyPhone("tel compania 1");
		c.setCompanyType("tipo comercio 1");
		c.setDocumentNumber(111234L);
		c.setDocumentType("DNI");
		c.setEmail("email 1");
		c.setId(1L);
		c.setName("nombre cliente 1");
		c.setNearStreets("entre calles 1");
		c.setPhone("111111111");
		
		clients.add(c);
		
		c = new ClientDto();
		c.setAddress("domicilio particular 222");
		c.setCity("Ciudad 2");
		c.setCompanyAddress("domicilio compania 2");
		c.setCompanyCity("ciudad compania 2");
		c.setCompanyPhone("tel compania 2");
		c.setCompanyType("tipo comercio 2");
		c.setDocumentNumber(222234L);
		c.setDocumentType("DNI");
		c.setEmail("email 2");
		c.setId(2L);
		c.setName("nombre cliente 2");
		c.setNearStreets("entre calles 2");
		c.setPhone("222222222");
		
		clients.add(c);
		
		c = new ClientDto();
		c.setAddress("domicilio particular 333");
		c.setCity("Ciudad 3");
		c.setCompanyAddress("domicilio compania 3");
		c.setCompanyCity("ciudad compania 3");
		c.setCompanyPhone("tel compania 3");
		c.setCompanyType("tipo comercio 3");
		c.setDocumentNumber(333334L);
		c.setDocumentType("DNI");
		c.setEmail("email 3");
		c.setId(3L);
		c.setName("nombre cliente 3");
		c.setNearStreets("entre calles 3");
		c.setPhone("333333333");
		
		clients.add(c);
		
		c = new ClientDto();
		c.setAddress("domicilio particular 444");
		c.setCity("Ciudad 4");
		c.setCompanyAddress("domicilio compania 4");
		c.setCompanyCity("ciudad compania 4");
		c.setCompanyPhone("tel compania 4");
		c.setCompanyType("tipo comercio 4");
		c.setDocumentNumber(444444L);
		c.setDocumentType("DNI");
		c.setEmail("email 4");
		c.setId(4L);
		c.setName("nombre cliente 4");
		c.setNearStreets("entre calles 4");
		c.setPhone("444444444");
		
		clients.add(c);
		
		return clients;
	}
	
}
