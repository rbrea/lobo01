Discount = function(){}

Discount.init = function(){

	var discountAmountElem = $("#discountAmount");
	var discountInstallmentAmountElem = $("#discountInstallmentAmount");
	var discountObservationsElem = $("#discountObservations"); 
	
	discountAmountElem.keyup(function(e){
		if(e.keyCode == 13) {
			discountInstallmentAmountElem.focus();			
		}
	    
	    return;
	});

	discountAmountElem.keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
	    	discountInstallmentAmountElem.focus();			
	    }
	    
	    return;
	});
	
	discountInstallmentAmountElem.keyup(function(e){
		if(e.keyCode == 13) {
			discountObservationsElem.focus();			
		}
	    
	    return;
	});

	discountInstallmentAmountElem.keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
	    	discountObservationsElem.focus();			
	    }
	    
	    return;
	});
	
	discountObservationsElem.keyup(function(e){
		if(e.keyCode == 13) {
			$("#btnAccept").focus();			
		}
	    
	    return;
	});

	discountObservationsElem.keydown(function(e){
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

Discount.resetModal = function(){
	$("#discountBillId").val("");
	$("#discountDateValue").val("");
	$("#discountAmount").val("");
	$("#discountObservations").val("");
	
	return;
}

Discount.add = function(dialog, btn, responseHandler){
	var billId = $("#discountBillId").val();

	var obj = new Object();
	obj.billId = billId;
	obj.amount = $("#discountAmount").val();
	obj.observations = $("#discountObservations").val();
	obj.date = $("#discountDateValue").val();
	obj.installmentAmount = $("#discountInstallmentAmount").val();
	
	$.ajax({ 
	   type    : "POST",
	   url     : Constants.contextRoot + "/controller/html/discount",
	   dataType: 'json',
	   data: JSON.stringify(obj),
	   contentType: "application/json;",
	   success:function(data) {
		   Message.hideMessages($('#modalDiscountAlertMessages'), $("#modalDiscountMessages"));
		   if(data != null && data.status == 0){

			   dialog.enableButtons(true);
			   dialog.setClosable(true);
       		   btn.stopSpin();
			   dialog.close();

			   //BillHistory.init();
			   responseHandler(data);
			   
			   return;
		   }else{
			   Message.showMessages($('#modalDiscountAlertMessages'), $("#modalDiscountMessages"), data.message);
			   
			   dialog.enableButtons(true);
			   dialog.setClosable(true);
       		   btn.stopSpin();
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#modalDiscountAlertMessages'), $("#modalDiscountMessages"), data.responseJSON.message);
		   dialog.enableButtons(true);
		   dialog.setClosable(true);
   		   btn.stopSpin();
		   
		   return;
	   }
	});
	
	return;
}

Discount.show = function(id){
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/discount?billId=" + id,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
			
		   var tbody = $("#tDiscountResult tbody");
		   
		   tbody.children('tr').remove();
		   
		   Message.hideMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"));
		   if(data != null && data.status == 0){

			   var table = $("#tDiscountResult").dataTable( {
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
					title: 'Descuentos',
					autodestroy: false,
			        message: function(dialog) {
			        	
			        	$("#lov-discount-container").css({"display":"block"});
			        	
			        	return $("#lov-discount-container");
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
