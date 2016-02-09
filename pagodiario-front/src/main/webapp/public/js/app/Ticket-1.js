Ticket = function(){}

Ticket.init = function(){
	
	$("#zone").focus();
	
	// asigno el dia de hoy
	$("#ticketDateValue").val(moment().add(1, 'days').format('DD/MM/YYYY'));
	
	$('#ticketDate').datetimepicker({
        locale: 'es',
        showTodayButton: true,
        format: 'DD/MM/YYYY'
    });
	
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
		$("#bCollectorId").val("")
		$("#bCollectorDescription").val("");
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
	
	$("#btnTicketSearchCollector").on("click", function(){
		
		var value = $("#bCollectorId").val();
		if(value != null && value != ""){
			return Collector.getByZone(value, $("#zone"), $('#ticketAlertMessages'), $("#ticketMessages"));
		}
		
    	$.ajax({ 
		   type    : "GET",
		   url     : Constants.contextRoot + "/controller/html/collector",
		   dataType: 'json',
		   contentType: "application/json;",
		   success:function(data) {
				
			   var tbody = $("#tLovCollectorResult tbody");
			   
			   tbody.children('tr').remove();
			   
			   Message.hideMessages($('#ticketAlertMessages'), $("#ticketMessages"));
			   if(data != null && data.status == 0){

				   var table = $("#tLovCollectorResult").dataTable( {
				   		"data" : data.data,
				   		"bDestroy" : true,
				   		"pagingType": "simple",
				        "columns": [
							{ 
								"className": 'centered',
								"data": "id" 
							},
							{ 
								"className": 'centered',
								"data": "zone" 
							},
				            { 	
				            	"className": 'centered',
				            	"data": "description" 
				            }
				        ],
				        "order": [[0, 'asc']],
				        "language": {
				            "lengthMenu": "Mostrar _MENU_ registros por p&aacute;gina",
				            "zeroRecords": "No se ha encontrado ningun elemento",
				            "info": "P&aacute;gina _PAGE_ de _PAGES_",
				            "infoEmpty": "No hay registros disponibles",
				            "infoFiltered": "(filtrados de un total de _MAX_ registros)",
				            "search": "Buscar: ",
				            "paginate": {
				            	"previous": "Anterior",
								"next": "Siguiente"
							}
				        } 
				   	});
				   
				   	$('#tLovCollectorResult tbody').on('mouseover', 'tr', function () {
						$(this).css({"cursor": "pointer"});	
						
						return;
					});
					
					$('#tLovCollectorResult tbody').on( 'click', 'tr', function () {
				        if ( $(this).hasClass('selected') ) {
				            $(this).removeClass('selected');
				        } else {
				            table.$('tr.selected').removeClass('selected');
				            $(this).addClass('selected');
				            
				            var selectedId = $(this).children('td').eq(0).html().trim();
				            var zone = $(this).children('td').eq(1).html().trim();
				            var selectedDescription = $(this).children('td').eq(2).html().trim();
				            
				            $("#zone").val(selectedId);
				            $("#bCollectorId").val(zone);
				            $("#bCollectorDescription").val(selectedDescription);
				            $("#lov-collector-container").css({"display": "none"});

							// si esta todo ok entonces doy de alta ...
							//$("#bClientId").focus();

				            BootstrapDialog.closeAll();
				        }
				        
						return;
				    });
					
					BootstrapDialog.show({
						type:BootstrapDialog.TYPE_DANGER,
						title: 'Cobradores',
						autodestroy: false,
						draggable: true,
				        message: function(dialog) {
				        	
				        	$("#lov-collector-container").css({"display":"block"});
				        	
				        	return $("#lov-collector-container");
				        }
				    });
					
				} else {
					Message.showMessages($('#ticketAlertMessages'), $("#ticketMessages"), data.message);
				}
		   },
		   error:function(data){
			   Message.showMessages($('#ticketAlertMessages'), $("#ticketMessages"), data.responseJSON.message);
			   
			   return;
		   }
		});
		
		return;
	});
	
	$("#bCollectorId").keyup(function(e){
		var value = $(this).val();
		if(e.keyCode == 13) {
			if(value != null && value != ""){
				Collector.getByZone(value, $("#zone"), $('#ticketAlertMessages'), $("#ticketMessages"));
				$("#fecDesdeValue").focus();
			} else {
				$("#fecDesdeValue").focus();			
			}
		} else {
			$("#bCollectorDescription").val("");
			if(value == null || value == ""){
				$("#btnAccept").addClass('disabled');
			}
		}
	    
	    return;
	});
	
	$('#bCollectorId').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
	    	var value = $(this).val();
			if(value != null && value != ""){
				Collector.getByZone(value, $("#zone"), $('#ticketAlertMessages'), $("#ticketMessages"));
				$("#fecDesdeValue").focus();
			} else {
				$("#fecDesdeValue").focus();			
			}
	    }
	    
	    return;
	});
	
	return;
}

Ticket.generate = function(){
	Message.hideMessages($('#ticketAlertMessages'), $("#ticketMessages"));
	var zone = $("#zone").val();
	if(Commons.isNotValid(zone)){
		Message.showMessages($('#ticketAlertMessages'), $("#ticketMessages"), "Cobrador no seleccionado. Por favor elija uno.");
	}
	
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
		var dateFromInput = $("#fecDesdeValue").val();
		var dateToInput = $("#fecHastaValue").val();
		
		var dateFrom = "";
		var dateTo = "";
		
		if(Commons.isValid(dateFromInput)){
			dateFrom = "&fecDesdeValue=" + dateFromInput;
		}
		
		if(Commons.isValid(dateToInput)){
			dateTo = "&fecHastaValue=" + dateToInput;
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
