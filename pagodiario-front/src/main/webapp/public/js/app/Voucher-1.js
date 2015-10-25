Voucher = function(){}

Voucher.init = function(){
	
	// asigno el dia de hoy
	$("#voucherDateValue").val(moment().add(-1, 'days').format('DD/MM/YYYY'));
	
	$('#voucherDate').datetimepicker({
        locale: 'es',
        showTodayButton: true,
        format: 'DD/MM/YYYY'
    });
	
	$("#btnAccept").on('click', function(){
		var c = 0;
		
		$("#frmVoucher").on('invalid.bs.validator', 
			function(e){
			    c++;
				
				return;
		});
		
		$("#frmVoucher").validator('validate');
		
		if(c == 0){
			Voucher.generate();
		}
		
		return;
	});
	
	return;
}

Voucher.generate = function(){
	$("#frmVoucher").submit();
	
	return;
}
