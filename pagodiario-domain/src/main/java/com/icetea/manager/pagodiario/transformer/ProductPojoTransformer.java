package com.icetea.manager.pagodiario.transformer;

import java.util.List;

import javax.inject.Named;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.pojo.jasper.ProductPojo;
import com.icetea.manager.pagodiario.model.BillProduct;

@Named
public class ProductPojoTransformer {

	public ProductPojo transform(BillProduct b){
		ProductPojo p = new ProductPojo();
		p.setCount(b.getCount());
		p.setName(b.getProduct().getDescription());
		
		return p;
	}
	
	public List<ProductPojo> transform(List<BillProduct> list){
		if(list == null){
			return null;
		}
		List<ProductPojo> r = Lists.newArrayList();
		for(BillProduct b : list){
			r.add(this.transform(b));
		}
		
		return r;
	}
	
}
