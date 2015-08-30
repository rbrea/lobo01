Ticket = function(){}

Ticket.init = function(){
	
	$("#zone").focus();
	
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
	
	$("#btnReset").on('click', function(){
		$("#zone").val("");
		$("#fecDesdeValue").val("");
		$("#fecHastaValue").val("");
		
		return;
	});
	
	$("#zone").keyup(function(e){
		if(e.keyCode == 13) {
			$("#btnAccept").focus();			
		}
	    
	    return;
	});

	$('#zone').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#btnAccept").focus();			
	    }
	    
	    return;
	});
	
	return;
}

Ticket.generate = function(){
	$("#frmTicket").submit();
	
	return;
}
