Payment = function(){}

Payment.init = function(){
	
	$("#bCollectorId").focus();
	
	$("#btnSearch").on('click', function(){
		
		var c = 0;
		
		$("#frmPaymentSearch").on('invalid.bs.validator', 
			function(e){
			    c++;
				
				return;
		});
		
		$("#frmPaymentSearch").validator('validate');
		
		if(c == 0){
			$(this).addClass("disabled");
    		
			var collectorId = $("#zone").val();
			
			$.ajax({ 
			   type    : "GET",
			   url     : Constants.contextRoot + "/controller/html/payment?collectorId=" + collectorId,
			   dataType: 'json',
			   contentType: "application/json;",
			   success:function(data) {
					
				   Message.hideMessages($('#paymentAlertMessages'), $("#paymentMessages"));
				   $("#btnSearch").removeClass("disabled");
				   
				   if(data != null && data.status == 0){
					   
					   $.each(data.data, function(){
						   
						   
						   return;
					   });
					   

				   } else {
					   Message.showMessages($('#paymentAlertMessages'), $("#paymentMessages"), data.message);
				   }
			   },
			   error:function(data){
				   Message.showMessages($('#paymentAlertMessages'), $("#paymentMessages"), data.responseJSON.message);
				   $("#btnSearch").removeClass("disabled");
				   
				   return;
			   }
			});
		}
		
		return;
	});
	
	$("#btnPaymentSearchCollector").on("click", function(){
		
		var value = $("#bCollectorId").val();
		if(value != null && value != ""){
			return Collector.getByZone(value, $("#zone"), $('#paymentAlertMessages'), $("#paymentMessages"));
		}
		
    	$.ajax({ 
		   type    : "GET",
		   url     : Constants.contextRoot + "/controller/html/collector",
		   dataType: 'json',
		   contentType: "application/json;",
		   success:function(data) {
				
			   var tbody = $("#tLovCollectorResult tbody");
			   
			   tbody.children('tr').remove();
			   
			   Message.hideMessages($('#paymentAlertMessages'), $("#paymentMessages"));
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
					Message.showMessages($('#paymentAlertMessages'), $("#paymentMessages"), data.message);
				}
		   },
		   error:function(data){
			   Message.showMessages($('#paymentAlertMessages'), $("#paymentMessages"), data.responseJSON.message);
			   
			   return;
		   }
		});
		
		return;
	});
	
	$("#bCollectorId").keyup(function(e){
		if(e.keyCode == 13) {
			var value = $(this).val();
			if(value != null && value != ""){
				Collector.getByZone(value, $("#zone"), $('#paymentAlertMessages'), $("#paymentMessages"));
				$("#creditNumber_0").focus();
			} else {
				$("#creditNumber_0").focus();			
			}
		} else {
			$("#bCollectorDescription").val("");
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
				Collector.getByZone(value, $("#zone"), $('#paymentAlertMessages'), $("#paymentMessages"));
				$("#creditNumber_0").focus();
			} else {
				$("#creditNumber_0").focus();			
			}
	    }
	    
	    return;
	});
	
	$("#btnPaymentAdd").on('click', function(){
		Commons.addNewClonedRow('paymentRow_', $('#basePaymentRow'), {
			'changeLabel':true,
			execute:function(row){
				
				var paymentAmountInput = row.find("input[id*='paymentAmount_']");
				
				paymentAmountInput.keyup(function(e){
				    if(e.keyCode == 13){
				        $("#btnPaymentAdd").trigger('click');
						$("input[id*='creditNumber_']:last").focus();
				    }
				    
				    return;
				});
				
				var creditNumberInput = row.find("input[id*='creditNumber_']");
				
				creditNumberInput.keyup(function(e){
				    if(e.keyCode == 13){
				    	$(this).parent().parent().parent().find('input:last').focus();
				    }
				    
				    return;
				});
				
				$("input").focus(function() { $(this).select(); } ).end().click(function () {$(this).select();});
				
				return;
			}
		});
		
		return;
	});
	
	$("#btnPaymentRemove").on('click', function(){
		Commons.removeClonedRow('paymentRow_');
		
		return;
	});
	
	Payment.addInputs();
	
	$("#btnReset").on('click', function(){
		
		var idxList = [];
		var paymentRows = $("div[id*='paymentRow_']");
		
		$.each(paymentRows, function(){
			
			var id = $(this).attr('id');
			var idValue = id.substring(id.indexOf("_") + 1);
			idxList.push(idValue);
			
			return;
		});
		
		for(var i=0;i<idxList.length;i++){
			if(idxList[i] != 0){
				$("div[id*='paymentRow_" + idxList[i] + "']").remove();
			}
		}
		
		$("#zone").val("").focus();
		$("#creditNumber_0").val("");
		$("#paymentAmount_0").val("");
		var errorSpan = $("#paymentMessageError_0");
		errorSpan.html("");
		errorSpan.addClass("hide");
		$("#paymentRow_0").children("div[class*='form-group']").removeClass("has-error");
		
		Message.hideMessages($('#paymentAlertMessages'), $("#paymentMessages"));
		
		return;
	});
	
	$("#btnAccept").on('click', function(){
		
		Payment.showConfirmModal();
		
		return;
	});
	
	// asigno el dia de hoy
	$("#paymentDateValue").val(moment().format('DD/MM/YYYY'));
	
	$('.not-writable').keypress(function(e) {
        e.preventDefault();
        
        return;
	}).css({
		"cursor": "not-allowed"
	});
	
	$('#paymentDate').datetimepicker({
        locale: 'es',
        showTodayButton: true,
        format: 'DD/MM/YYYY'
    });
	
	return;
}

Payment.addInputs = function(){
	
	$("input[id*='paymentAmount_']").keyup(function(e){
	    if(e.keyCode == 13){
	        $("#btnPaymentAdd").trigger('click');
			$("input[id*='creditNumber_']:last").focus();
	    }
	    
	    return;
	});
	
	$("input[id*='creditNumber_']").keyup(function(e){
	    if(e.keyCode == 13){
	    	$(this).parent().parent().parent().find('input:last').focus();
	    }
	    
	    return;
	});
	
	return;
}

Payment.add = function(dialog){
	
	var c = 0;
	
	$("#frmPaymentAdd").on('invalid.bs.validator', 
		function(e){
		    c++;
			
			return;
	});
	
	$("#frmPaymentAdd").validator('validate');
	
	if(c > 0){
		
		return false;
	}
	
	var idxList = [];
	var paymentRows = $("div[id*='paymentRow_']");
	
	$.each(paymentRows, function(){
		
		var id = $(this).attr('id');
		var idValue = id.substring(id.indexOf("_") + 1);
		idxList.push(idValue);
		
		return;
	});
	
	
	//var size = $("div[id*='paymentRow_']").length;
	var list = [];
	
	for(var i=0;i<idxList.length;i++){
		var obj = new Object();
		
		obj.idx = idxList[i];
		obj.creditNumber = $("#creditNumber_" + idxList[i]).val();
		obj.amount = $("#paymentAmount_" + idxList[i]).val();
		obj.collectorId = $("#zone").val();
		obj.date = $("#paymentDateValue").val();
		
		list.push(obj);
	}
	
	$.ajax({ 
	   type    : "POST",
	   url     : Constants.contextRoot + "/controller/html/payment/list",
	   dataType: 'json',
	   data: JSON.stringify(list),
	   contentType: "application/json;",
	   success:function(data) {
		   Message.hideMessages($('#paymentAlertMessages'), $("#paymentMessages"));
		   $("div[id*='paymentRow_']").children("div[class*='form-group']").removeClass("has-error");
		   if(data != null && data.status == 0){
			   
			   var list = data.data;
			   if(list != null && list.length > 0){
				   for(var i=0;i<list.length;i++){
					   var p = list[i];
					   var errorMessage = p.errorMessage;
					   if(errorMessage != null && errorMessage != ""){
						   var errorSpan = $("#paymentMessageError_" + p.idx);
						   errorSpan.html("<i class=\"glyphicon glyphicon-info-sign\"></i>&nbsp;" + errorMessage);
						   errorSpan.removeClass("hide");
						   $("#paymentRow_" + p.idx).children("div[class*='form-group']").addClass("has-error");
					   } else {
						   // si no tuvimos errores entonces reseteamos ...
						   //$("#btnReset").trigger('click');
						   if(p.idx == 0){
							   $("#creditNumber_0").val("");
							   $("#paymentAmount_0").val("");
						   } else {
							   $("#paymentRow_" + p.idx).remove();
						   }
					   }
				   }
			   }

		   }else{
			   Message.showMessages($('#paymentAlertMessages'), $("#paymentMessages"), data.message);
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#paymentAlertMessages'), $("#paymentMessages"), data.responseJSON.message);
		   
		   return;
	   }
	});
	dialog.close();

	return;
}

Payment.showConfirmModal = function(){

	$("#frmPaymentAdd").validator('destroy');
	
	var c = 0;
	
	$("#frmPaymentAdd").on('invalid.bs.validator', 
		function(e){
		    c++;
			
			return;
	});
	
	$("#frmPaymentAdd").validator('validate');
	
	if(c > 0){
		
		return false;
	}
	
	BootstrapDialog.show({
		onhidden:function(){
			$("#tPaymentDetailResult > tbody > tr").remove();
			
			return;
		},
		draggable: true,
		type:BootstrapDialog.TYPE_DANGER,
		title: 'Confirmar Pagos?',
		autodestroy: false,
        message: function(dialog) {
        	
        	var idxList = [];
        	var paymentRows = $("div[id*='paymentRow_']");
        	
        	$.each(paymentRows, function(){
        		
        		var id = $(this).attr('id');
        		var idValue = id.substring(id.indexOf("_") + 1);
        		idxList.push(idValue);
        		
        		return;
        	});
        	
        	
        	//var size = $("div[id*='paymentRow_']").length;
        	var list = [];
        	
        	for(var i=0;i<idxList.length;i++){
        		var obj = new Object();
        		
        		obj.idx = idxList[i];
        		obj.creditNumber = $("#creditNumber_" + idxList[i]).val();
        		obj.amount = $("#paymentAmount_" + idxList[i]).val();
        		obj.collectorId = $("#zone").val();
        		obj.date = moment().format('DD/MM/YYYY');
        		
        		list.push(obj);
        	}
        	
        	var table = $("#tPaymentDetailResult").dataTable( {
		   		"data" : list,
		   		"bDestroy" : true,
		   		responsive: true,
		        "columns": [
					{ 
						"className": 'centered',
						"data": "collectorId" 
					},
					{ 
						"className": 'centered',
						"data": "date" 
					},
		            { 	
		            	"className": 'centered',
		            	"data": "creditNumber" 
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
        	
        	$("#payment-detail-container").css({"display":"block"});
        	
        	return $("#payment-detail-container");
        },
        buttons: [{
        	id: 'btnCancel',
        	label: 'Cancelar',
        	icon: 'glyphicon glyphicon-remove-sign',
        	action: function(dialog){
        		var btn = this;
        		dialog.close();
        		
        		return;
        	}
        },
        {
        	id: 'btnAccept',
        	label: 'Aceptar',
        	icon: 'glyphicon glyphicon-ok-sign',
        	cssClass: 'btn-success',
        	action: function(dialog){
        		var btn = this;
        		
        		Payment.add(dialog);
        		
        		return;
        	}
        }]
    });
	
	
	return;
}
