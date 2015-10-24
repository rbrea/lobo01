package com.icetea.manager.pagodiario.jasper.factory.sample;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.pojo.jasper.VoucherPojo;

public class VoucherFactory {

	public static Collection<VoucherPojo> createBeanCollection(){
		
		List<VoucherPojo> list = Lists.newArrayList();
		
		for(int i=0;i<18;i++){
			
			VoucherPojo v = new VoucherPojo();
			v.setVoucherId("V123" + i);
			v.setTraderData("Vendedor-" + i + " (114567890" + i + ")");
			v.setClientName("LUIS PIZARRO-" + i);
			v.setClientData("Gaona 100" + i + " y Chile (Lavadero de Auto-" + i + ")");
			v.setClientDocumentNumber("2987667" + i);
			v.setDiscountAmount("$ 154" + i + ".00");
			v.setInstallmentAmount("$2" + i);
			v.setExpirationDate("11/11/2016");
			
			list.add(v);
		}
		
		return list;
	}
	
}
