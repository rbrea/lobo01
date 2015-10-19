Dev = function(){}

Dev.init = function(){

	var devProductCountElem = $("#devProductCount");
	var devProductCodeElem = $("#devProductCode");
	var devAmountElem = $("#devAmount"); 
	var devObservationsElem = $('#devObservations');
	var devInstallment = $("#devInstallment");
	
	devProductCountElem.keyup(function(e){
		if(e.keyCode == 13) {
			devProductCodeElem.focus();			
		}
	    
	    return;
	});

	devProductCountElem.keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
	    	devProductCodeElem.focus();			
	    }
	    
	    return;
	});
	
	devProductCodeElem.keyup(function(e){
		if(e.keyCode == 13) {
			var value = $(this).val();
			if(value != null && value != ""){
				Dev.getProductByCode(value);
				devInstallment.focus();
			} else {
				devInstallment.focus();			
			}
		} else {
			$("#devProductDescription").val("");
		}
	    
	    return;
	});
	
	devProductCodeElem.keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
	    	var value = $(this).val();
			if(value != null && value != ""){
				Dev.getProductByCode(value);
			} else {
				devInstallment.focus();			
			}
	    }
	    
	    return;
	});
	
	devProductCountElem.on('blur', function(){
		var cant = $(this).val();
		if(cant === undefined || cant == null || cant == ""){
			cant = 0;
		}
		var installmentAmount = devInstallment.val();
		if(installmentAmount != null && installmentAmount != ""){
			devInstallment.val(cant * parseFloat(installmentAmount));
		}
		
		return;
	});
	
	devInstallment.keyup(function(e){
		if(e.keyCode == 13) {
			devAmountElem.focus();			
		}
	    
	    return;
	});

	devInstallment.keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
	    	devAmountElem.focus();			
	    }
	    
	    return;
	});
	
	devAmountElem.keyup(function(e){
		if(e.keyCode == 13) {
			devObservationsElem.focus();			
		}
	    
	    return;
	});

	devAmountElem.keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
	    	devObservationsElem.focus();			
	    }
	    
	    return;
	});
	
	devObservationsElem.keyup(function(e){
		if(e.keyCode == 13) {
			$("#btnAcceptDev").focus();			
		}
	    
	    return;
	});

	devObservationsElem.keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#btnAcceptDev").focus();			
	    }
	    
	    return;
	});
	
	return;
}

Dev.resetModal = function(){
	$("#devBillId").val("");
	$("#devDateValue").val("");
	$("#devAmount").val("");
	$("#devObservations").val("");
	$("#devProductCount").val("");
	$("#devProductId").val("");
	$("#devInstallment").val("");
	$("#devProductCode").val("");
	$("#devProductDescription").val("");
	
	return;
}

Dev.add = function(dialog, btn){
	var billId = $("#devBillId").val();

	var obj = new Object();
	obj.billId = billId;
	obj.amount = $("#devAmount").val();
	obj.observations = $("#devObservations").val();
	obj.date = $("#devDateValue").val();
	obj.productCount = $("#devProductCount").val();
	obj.productId = $("#devProductId").val();
	obj.productCode = $("#devProductCode").val();
	obj.productInstallment = $("#devInstallment").val();
	
	$.ajax({ 
	   type    : "POST",
	   url     : Constants.contextRoot + "/controller/html/dev",
	   dataType: 'json',
	   data: JSON.stringify(obj),
	   contentType: "application/json;",
	   success:function(data) {
		   Message.hideMessages($('#modalDevAlertMessages'), $("#modalDevMessages"));
		   if(data != null && data.status == 0){

			   dialog.enableButtons(true);
			   dialog.setClosable(true);
       		   btn.stopSpin();
			   dialog.close();

			   BillHistory.init();
			   
			   return;
		   }else{
			   Message.showMessages($('#modalDevAlertMessages'), $("#modalDevMessages"), data.message);
			   
			   dialog.enableButtons(true);
			   dialog.setClosable(true);
       		   btn.stopSpin();
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#modalDevAlertMessages'), $("#modalDevMessages"), data.responseJSON.message);
		   dialog.enableButtons(true);
		   dialog.setClosable(true);
   		   btn.stopSpin();
		   
		   return;
	   }
	});
	
	return;
}

Dev.show = function(id){
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/dev?billId=" + id,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
			
		   var tbody = $("#tDevResult tbody");
		   
		   tbody.children('tr').remove();
		   
		   Message.hideMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"));
		   if(data != null && data.status == 0){

			   var table = $("#tDevResult").dataTable( {
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
					size: BootstrapDialog.SIZE_LARGE,
					type:BootstrapDialog.TYPE_DANGER,
					draggable: true,
					title: 'Devoluciones',
					autodestroy: false,
			        message: function(dialog) {
			        	
			        	$("#lov-dev-container").css({"display":"block"});
			        	
			        	return $("#lov-dev-container");
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

Dev.showLovProduct = function(){
	
	var value = $("#devProductCode").val();
	if(value != null && value != ""){
		return Dev.getProductByCode(value);
	}
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/product",
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
			
		   var tbody = $("#tLovProductResult tbody");
		   
		   tbody.children('tr').remove();
		   
		   Message.hideMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"));
		   if(data != null && data.status == 0){

			   var table = $("#tLovProductResult").dataTable( {
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
							"data": "code" 
						},
			            { 	
			            	"className": 'centered',
			            	"data": "description" 
			            },
			            { 	
			            	"className": 'centered',
			            	"data": "price" 
			            },
			            { 	
			            	"className": 'centered',
			            	"data": "dailyInstallment" 
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
			   
			   	$('#tLovProductResult tbody').on('mouseover', 'tr', function () {
					$(this).css({"cursor": "pointer"});	
					
					return;
				});
				
				$('#tLovProductResult tbody').on( 'click', 'tr', function () {
			        if ( $(this).hasClass('selected') ) {
			            $(this).removeClass('selected');
			        } else {
			            table.$('tr.selected').removeClass('selected');
			            $(this).addClass('selected');
			            
			            var selectedId = $(this).children('td').eq(0).html().trim();
			            var selectedCode = $(this).children('td').eq(1).html().trim();
			            var selectedDescription = $(this).children('td').eq(2).html().trim();
			            var price = $(this).children('td').eq(3).html().trim();
			            var dailyInstallment = $(this).children('td').eq(4).html().trim();
			            
			            var realPrice = 0;
			            if(price !=null && price != ""){
			            	realPrice = parseFloat(Math.floor(price * 100) / 100);
			            }
			            
			            $("#devProductId").val(selectedId);
			            $("#devProductDescription").val(selectedDescription);
			            $("#devProductCode").val(selectedCode);
			            
			            var cant = $("#devProductCount").val();
			            if(cant == null || cant == ""){
			            	cant = 1;
			            }
			            $("#devInstallment").val(parseFloat(dailyInstallment) * cant);
			            $("#devAmount").val(realPrice * cant);
			            $("#lov-client-container").css({"display": "none"});
			            
			            $.each(BootstrapDialog.dialogs, function(id, dialog){
			            	
			            	if(dialog.getTitle() == 'Productos'){
			            		
			            		dialog.close();
			            	}
			            	
			            	return;
			            });
			        }
			        
					return;
			    });
				
				BootstrapDialog.show({
					type:BootstrapDialog.TYPE_DANGER,
					title: 'Productos',
					autodestroy: false,
					draggable: true,
			        message: function(dialog) {
			        	
			        	$("#lov-product-container").css({"display":"block"});
			        	
			        	return $("#lov-product-container");
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

Dev.getProductByCode = function(code){
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/product?code=" + code,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {

		   $("#devProductDescription").parent().next().html("");
		   $("#devProductDescription").val("");
		   $("#devProductDescription").parent().parent().removeClass("has-error");
		   
		   Message.hideMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"));
		   
		   if(data != null && data.status == 0){
			   
			   var list = data.data;
			   
			   if(list.length > 0){
				   var e = list[0];
				   
				   var selectedId = e.id;
		           var selectedCode = e.code;
		           var selectedDescription = e.description;
		           var price = e.price;
		           var dailyInstallment = e.dailyInstallment;
		            
		           var realPrice = 0;
		            if(price !=null && price != ""){
		            	realPrice = parseFloat(Math.floor(price * 100) / 100);
		            }
		            
		            $("#devProductId").val(selectedId);
		            $("#devProductDescription").val(selectedDescription);
		            $("#devProductCode").val(selectedCode);
		            
		            var cant = $("#devProductCount").val();
		            if(cant == null || cant == ""){
		            	cant = 1;
		            }
		            $("#devInstallment").val(parseFloat(dailyInstallment) * cant);
		            $("#devAmount").val(realPrice * cant);
				   
		           $("#devInstallment").focus();
			   } else {
				   $("#devProductCode").focus();
				   $("#devProductDescription").parent().next().append("Producto no encontrado");
				   $("#devProductDescription").parent().parent().addClass("has-error");
			   }
			   
		   } else {
				Message.showMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"), data.message);
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"), data.responseJSON.message);
		   
		   return false;
	   }
	});
	
	return;
}
