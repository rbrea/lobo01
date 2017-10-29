package com.icetea.manager.pagodiario.csv;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.supercsv.io.ICsvBeanWriter;

import com.icetea.manager.pagodiario.api.dto.csv.ClientFilterCsvDto;

public class ClientFilterCsvView extends AbstractCsvView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildCsvDocument(ICsvBeanWriter csvWriter, Map<String, Object> model) throws IOException {

	    List<ClientFilterCsvDto> list = (List<ClientFilterCsvDto>) model.get("csvData");
        String[] header = (String[]) model.get("csvHeader");
 
        csvWriter.writeHeader(header);
 
        for (ClientFilterCsvDto l : list) {
            csvWriter.write(l, header);
        }
	}

}
