BillHistory = function(){}

BillHistory.init = function(){
	/*
	$('#startDate').datetimepicker({
        locale: 'es',
        format: 'DD/MM/YYYY',
        showTodayButton: true
    });
	
	$('#endDate').datetimepicker({
        locale: 'es',
        format: 'DD/MM/YYYY',
        showTodayButton: true
    });
	*/
	var table = $("#tBillResult").dataTable( {
		"bDestroy" : true,
        "ajax": Constants.contextRoot + "/controller/html/bill",
        "columns": [
            { 	
            	"className": 'centered',
            	"data": "id" 
            },
            { 
            	"className": 'centered',
            	"data": "startDate"
            },
            { 	
            	"className": 'centered',
            	"data": "endDate" 
            },
            { 	
            	"className": 'centered',
            	"data": "creditNumber" 
            },
            { 
            	"className": 'centered',
            	"data": "collectorId" 
            },
            { 	
            	"className": 'centered',
            	"data": "overdueDays" 
            },
            { 
            	"className": 'centered',
            	"data": "totalDailyInstallment" 
            },
            { 	
            	"className": 'centered',
            	"data": "totalAmount" 
            },
            { 	
            	"className": 'centered',
            	"data": "remainingAmount" 
            },
            { 	
            	"className": 'centered',
            	"data": "status" 
            },
            {
            	"className":      'centered',
	         	// The `data` parameter refers to the data for the cell (defined by the
                // `data` option, which defaults to the column being worked with, in
                // this case `data: 0`.
                "orderable": false,
                "render": function ( data, type, row ) {
                    //return data +' ('+ row[3]+')';
                    return "<a id=\"btnShowPayments_" + row.id + "\" href=\"javascript:BillHistory.showPayments('" + row.id + "');\" onmouseover=\"javascript:Commons.setTooltip('btnShowPayments_');\" class=\"btn btn-xs btn-info\" data-toggle=\"tooltip\" data-placement=\"left\" title=\"Mostrar pagos asociados a la factura\"><i class=\"glyphicon glyphicon-list\"></i></a>"
                    + "<a id=\"btnShowModalPayment_" + row.id + "\" href=\"javascript:BillHistory.showModalPayment('" + row.id + "');\" onmouseover=\"javascript:Commons.setTooltip('btnShowPayments_');\" class=\"btn btn-xs btn-info\" data-toggle=\"tooltip\" data-placement=\"left\" title=\"Mostrar formulario de pago\"><i class=\"glyphicon glyphicon-ok-sign\"></i></a>";
                }
         	}
        ],
        "order": [[1, 'desc']],
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
	
	return;
}

BillHistory.showPayments = function(id){
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/payment?billId=" + id,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
			
		   var tbody = $("#tPaymentResult tbody");
		   
		   tbody.children('tr').remove();
		   
		   Message.hideMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"));
		   if(data != null && data.status == 0){

			   var table = $("#tPaymentResult").dataTable( {
			   		"data" : data.data,
			   		"bDestroy" : true,
			   		responsive: true,
			        "columns": [
						{ 
							"className": 'centered',
							"data": "id" 
						},
						{ 
							"className": 'centered',
							"data": "date" 
						},
			            { 	
			            	"className": 'centered',
			            	"data": "amount" 
			            },
			            { 	
			            	"className": 'centered',
			            	"data": "collectorId" 
			            }
			        ],
			        "order": [[1, 'desc']],
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
			   
				BootstrapDialog.show({
					type:BootstrapDialog.TYPE_INFO,
					title: 'Pagos',
					autodestroy: false,
			        message: function(dialog) {
			        	
			        	$("#lov-payment-container").css({"display":"block"});
			        	
			        	return $("#lov-payment-container");
			        }
			    });
				
			} else {
				Message.showMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"), data.message);
			}
	   },
	   error:function(data){
		   Message.showMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"), data.responseJSON.message);
		   
		   return;
	   }
	});
	
	return;
}
