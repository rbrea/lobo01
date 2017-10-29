package com.icetea.manager.pagodiario.factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.exception.ErrorTypedException;

@Named
public class TicketNumberListFactory {

	public List<Long> build(MultipartFile mpf) {

		List<Long> list = Lists.newArrayList();
		
		List<String> notValids = Lists.newArrayList();

		try {

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					mpf.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {

				String[] splitted = StringUtils.split(line, ",");
				
				for(String tn : splitted){
					
					tn = StringUtils.trim(tn);
					
					if(StringUtils.isNumeric(tn)){
						list.add(Long.valueOf(tn));
					} else {
						notValids.add(tn);
					}
				}
			}
			
			if(!notValids.isEmpty()){
				throw new ErrorTypedException("Los siguientes nros de tickets no son validos: " 
						+ StringUtils.join(notValids, ", "));
			}

		} catch (IOException e) {
			throw new ErrorTypedException("No se pudo leer el archivo correctamente.", e);
		}

		return list;
	}

}
