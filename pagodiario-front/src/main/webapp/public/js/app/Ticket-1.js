Ticket = function(){}

Ticket.init = function(){
	
	$('#fecDesde').datetimepicker({
        locale: 'es',
        showTodayButton: true,
        format: 'DD/MM/YYYY'
    });
	
	$('#fecHasta').datetimepicker({
        locale: 'es',
        showTodayButton: true,
        format: 'DD/MM/YYYY'
    });
	
	$("#btnAccept").on('click', function(){
		var c = 0;
		
		$("#frmTicket").on('invalid.bs.validator', 
			function(e){
			    c++;
				
				return;
		});
		
		$("#frmTicket").validator('validate');
		
		if(c == 0){
			Ticket.generate();
		}
		
		return;
	});
	
	return;
}

Ticket.generate = function(){
	
	$("#frmTicket").submit();
	
	return;
}
