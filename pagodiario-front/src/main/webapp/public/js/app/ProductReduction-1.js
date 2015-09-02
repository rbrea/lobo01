ProductReduction = function(){}

ProductReduction.resetModal = function(){
	$("#productReductionBillId").val("");
	$("#productReductionDateValue").val("");
	$("#productReductionAmount").val("");
	$("#productReductionObservations").val("");
	
	return;
}

ProductReduction.add = function(dialog, btn){
	var billId = $("#productReductionBillId").val();

	var obj = new Object();
	obj.billId = billId;
	obj.amount = $("#productReductionAmount").val();
	obj.observations = $("#productReductionObservations").val();
	obj.date = $("#productReductionDateValue").val();
	
	$.ajax({ 
	   type    : "POST",
	   url     : Constants.contextRoot + "/controller/html/productReduction",
	   dataType: 'json',
	   data: JSON.stringify(obj),
	   contentType: "application/json;",
	   success:function(data) {
		   Message.hideMessages($('#modalProductReductionAlertMessages'), $("#modalProductReductionMessages"));
		   if(data != null && data.status == 0){

			   dialog.enableButtons(true);
			   dialog.setClosable(true);
       		   btn.stopSpin();
			   dialog.close();

			   //BillHistory.init();
			   
			   return;
		   }else{
			   Message.showMessages($('#modalProductReductionAlertMessages'), $("#modalProductReductionMessages"), data.message);
			   
			   dialog.enableButtons(true);
			   dialog.setClosable(true);
       		   btn.stopSpin();
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#modalProductReductionAlertMessages'), $("#modalProductReductionMessages"), data.responseJSON.message);
		   dialog.enableButtons(true);
		   dialog.setClosable(true);
   		   btn.stopSpin();
		   
		   return;
	   }
	});
	
	return;
}

ProductReduction.show = function(id){
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/productReduction?billId=" + id,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
			
		   var tbody = $("#tProductReductionResult tbody");
		   
		   tbody.children('tr').remove();
		   
		   Message.hideMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"));
		   if(data != null && data.status == 0){

			   var table = $("#tProductReductionResult").dataTable( {
				   "fnInitComplete": function(){
						$('#tBillResult tbody tr').each(function(){
							$(this).find('td:eq(1)').attr('nowrap', 'nowrap');
							
							return;
						});
						
						return;
					},
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
			   
				BootstrapDialog.show({
					type:BootstrapDialog.TYPE_DANGER,
					draggable: true,
					title: 'Bajas',
					autodestroy: false,
			        message: function(dialog) {
			        	
			        	$("#productReductionBillId").val(id);
			        	
			        	$("#lov-productReduction-container").css({"display":"block"});
			        	
			        	return $("#lov-productReduction-container");
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
