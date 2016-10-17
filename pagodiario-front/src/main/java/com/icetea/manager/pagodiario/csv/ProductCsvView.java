package com.icetea.manager.pagodiario.csv;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.supercsv.io.ICsvBeanWriter;

import com.icetea.manager.pagodiario.api.dto.ProductDto;

public class ProductCsvView extends AbstractCsvView {

	public ProductCsvView() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void buildCsvDocument(ICsvBeanWriter csvWriter, Map<String, Object> model) throws IOException {
		
		List<ProductDto> list = (List<ProductDto>) model.get("csvData");
        String[] header = (String[]) model.get("csvHeader");
 
        csvWriter.writeHeader(header);
 
        for (ProductDto l : list) {
            csvWriter.write(l, header);
        }

	}

}
