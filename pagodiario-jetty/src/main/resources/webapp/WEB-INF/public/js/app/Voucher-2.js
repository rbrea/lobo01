Voucher = function(){}

Voucher.init = function(){
	
	// asigno el dia de hoy
	//$("#voucherDateValue").val(moment().add(-1, 'days').format('DD/MM/YYYY'));
	
	$('#voucherFromDate').datetimepicker({
        locale: 'es',
        showTodayButton: true,
        format: 'DD/MM/YYYY'
    });
	
	$('#voucherToDate').datetimepicker({
        locale: 'es',
        showTodayButton: true,
        format: 'DD/MM/YYYY'
    });
	
	$("#doPrintVoucher").on('click', function(){
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
	
	$("#btnVoucherSearch").on('click', function(){
		Voucher.search();
		
		return;
	});
	
	$("#btnVoucherReset").on('click', function(){
		Voucher.reset();
		
		return;
	});
	
	return;
}

Voucher.generate = function(){
	$("#frmVoucher").submit();
	
	return;
}

Voucher.reset = function(){
	var table = $("#tVoucherResult").DataTable();
	 
	table.clear().draw();
	
	return;
}

Voucher.search = function(){
	
	var alterMessagesElement = $('#voucherAlertMessages');
	var messagesElement = $("#voucherMessages");
	   
	Message.hideMessages(alterMessagesElement, messagesElement);
	
	var voucherDateFromValue = $("#voucherDateFromValue").val();
	var voucherDateToValue = $("#voucherDateToValue").val();
	
	if(voucherDateFromValue == null || voucherDateFromValue == "" || voucherDateFromValue === undefined){
		Message.showMessages(alterMessagesElement, messagesElement, "Fecha desde es requerido");
		
		return false;
	}
	
	if(voucherDateToValue == null || voucherDateToValue == "" || voucherDateToValue === undefined){
		Message.showMessages(alterMessagesElement, messagesElement, "Fecha desde es requerido");
		
		return false;
	}
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/voucher?voucherDateFromValue=" + voucherDateFromValue + "&voucherDateToValue=" + voucherDateToValue,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
			
		   var tbody = $("#tVoucherResult tbody");
		   
		   tbody.children('tr').remove();
		   
		   
		   
		   Message.hideMessages(alterMessagesElement, messagesElement);
		   if(data != null && data.status == 0){
			   if(data)
			   
			   var table = $("#tVoucherResult").dataTable( {
				   "fnInitComplete": function() {
				        $('#tVoucherResult tbody tr').each(function(){
				                $(this).find('td:eq(1)').attr('nowrap', 'nowrap');
				                $(this).find('td:eq(4)').attr('nowrap', 'nowrap');
				        });
				    },
				"bDestroy" : true,
				responsive: false,
		        "data": data.data,
		        "columns": [
		            { 	
		            	"className": 'centered',
		            	"data": "voucherId" 
		            },
		            { 
		            	"className": 'centered',
		            	"data": "traderData"
		            },
		            { 	
		            	"className": 'centered',
		            	"data": "zone" 
		            },
		            { 	
		            	"className": 'centered',
		            	"data": "clientName" 
		            },
		            { 
		            	"className": 'centered',
		            	"data": "clientData" 
		            },
		            { 
		            	"className": 'centered',
		            	"data": "discountAmount" 
		            },
		            { 
		            	"className": 'centered',
		            	"data": "installmentAmount" 
		            },
		            { 
		            	"className": 'centered',
		            	"data": "expirationDate" 
		            }
		        ],
		        "order": [[0, 'desc']],
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
		   } else {
			   Message.showMessages(alterMessagesElement, messagesElement, data.message);
		   }
	   },
	   error:function(data){
		   Message.showMessages(alterMessagesElement, messagesElement, data.responseJSON.message);
		   
		   return;
	   }
	});
	
	return;
}
