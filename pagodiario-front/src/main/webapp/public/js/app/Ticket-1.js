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
		$("#btnAccept").addClass('disabled');
		
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
	
	$("#btnValidation").on('click', function(){
		Ticket.validate();
		
		return;
	});
	
	return;
}

Ticket.generate = function(){
	$("#frmTicket").submit();
	
	return;
}

Ticket.validate = function(){
	
	var c = 0;
	
	$("#frmTicket").on('invalid.bs.validator', 
		function(e){
		    c++;
			
			return;
	});
	
	$("#frmTicket").validator('validate');
	
	if(c == 0){
		var collectorId = $("#zone").val();
		var dateFrom = $("#fecDesdeValue").val();
		var dateTo = $("#fecHastaValue").val();
		
		if(dateFrom != null && dateFrom != ""){
			dateFrom = "&fecDesdeValue=" + dateFrom;
		}
		
		if(dateTo != null && dateTo != ""){
			dateTo = "&fecHastaValue=" + dateTo;
		}
		
		$.ajax({ 
		   type    : "GET",
		   url     : Constants.contextRoot + "/controller/html/ticket/validate?collectorId=" + collectorId + dateFrom + dateTo,
		   dataType: 'json',
		   contentType: "application/json;",
		   success:function(data) {
			   Message.hideMessages($('#ticketAlertMessages'), $("#ticketMessages"));
			   
			   if(data != null && data.basicOutputDto != null && data.basicOutputDto.status == 0){
				   $("#btnAccept").removeClass('disabled');
			   } else {
				   Message.showMessages($('#ticketAlertMessages'), $("#ticketMessages"), data.message);
			   }
			   
			   return;
		   },
		   error:function(data){
			   Message.showMessages($('#ticketAlertMessages'), $("#ticketMessages"), data.responseJSON.message);
			   
			   return;
		   }
		});
	}
	
	return;
}
