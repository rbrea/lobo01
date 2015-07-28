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
		/*
		"fnRowCallback": function(nRow, aData, iDisplayIndex){
			$('td', nRow).attr('nowrap', 'nowrap');
			
			return nRow;
		},*/
		"fnInitComplete": function(){
			$('#tBillResult tbody tr').each(function(){
				$(this).find('td:eq(1)').attr('nowrap', 'nowrap');
				$(this).find('td:eq(2)').attr('nowrap', 'nowrap');
				$(this).find('td:eq(10)').attr('nowrap', 'nowrap');
				
				return;
			});
			
			return;
		},
		/*"bAutoWidth": false,*/
		"bDestroy" : true,
		responsive: true,
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
            	"render": function ( data, type, row ) {
			        var value = "INICIALIZADO";
			        if(row.status == 'ACTIVE'){
			        	value = "ACTIVO";
			        } else if(row.status == 'FINALIZED'){
			        	value = 'FINALIZADO';
			        } else if(row.status == 'CANCELED'){
			        	value = 'CANCELADO';
			        }
			        
			        return value;
			    } 
            },
            {
            	"className":      'centered',
	         	// The `data` parameter refers to the data for the cell (defined by the
                // `data` option, which defaults to the column being worked with, in
                // this case `data: 0`.
                "orderable": false,
                "render": function ( data, type, row ) {
                    //return data +' ('+ row[3]+')';
                	
                	var clazzDisabled = "";
                	if(row.status == 'FINALIZED'){
                		clazzDisabled = 'disabled';
                	}
                	
                	return BillHistory.getActionSelectElement(row.id, {
                		"totalDailyInstallment": row.totalDailyInstallment,
                		"collectorId" : row.collectorId,
                		"detailClazzDisabled": clazzDisabled
                	});
                	/*
                    return "<a id=\"btnShowPayments_" + row.id + "\" href=\"javascript:BillHistory.showPayments('" + row.id + "');\" class=\"btn btn-xs btn-info\" data-toggle=\"tooltip\" data-placement=\"bottom\" title=\"Mostrar pagos asociados a la factura\"><i class=\"glyphicon glyphicon-list\"></i></a>"
                    	+ "&nbsp;<a id=\"btnShowModalPayment_" + row.id + "\" href=\"javascript:BillHistory.showModalPayment('" + row.id + "', '" + row.totalDailyInstallment + "', '" + row.collectorId + "');\" class=\"btn btn-xs btn-danger " + clazzDisabled +"\" data-toggle=\"tooltip\" data-placement=\"left\" title=\"Mostrar formulario de pago\"><i class=\"glyphicon glyphicon-list-alt\"></i></a>"
                    	+ "&nbsp;<a id=\"btnShowDetail_" + row.id + "\" href=\"javascript:BillHistory.showDetail('" + row.id + "');\" class=\"btn btn-xs btn-success disabled\" data-toggle=\"tooltip\" data-placement=\"left\" title=\"Detalle de Factura\"><i class=\"glyphicon glyphicon-zoom-in\"></i></a>"
                    	+ "&nbsp;<a id=\"btnShowDiscount_" + row.id + "\" href=\"javascript:BillHistory.showDiscount('" + row.id + "');\" class=\"btn btn-xs btn-warning\" data-toggle=\"tooltip\" data-placement=\"left\" title=\"Descuentos\"><i class=\"glyphicon glyphicon-tasks\"></i></a>";
                    	*/
                } // onmouseover=\"javascript:Commons.setTooltip('btnShowPayments_');\"
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
	
	return;
}

BillHistory.getActionSelectElement = function(id, map){
	
	var div = "<div class=\"btn-group\">" +
	  "<button type=\"button\" class=\"btn btn-xs btn-danger dropdown-toggle\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">" +
	    "Acciones <span class=\"caret\"></span>" +
	  "</button>" +
	  "<ul class=\"dropdown-menu dropdown-menu-left\">" +
	  	"<li class=\"dropdown-header\">Consultas</li>" + 
	    "<li><a href=\"javascript:void(0);\" onclick=\"javascript:BillHistory.showPayments('" + id + "');\">Pagos</a></li>" +
	    "<li><a href=\"javascript:void(0);\" onclick=\"javascript:Discount.show('" + id + "');\">Descuentos</a></li>" +
	    "<li><a href=\"javascript:void(0);\" onclick=\"javascript:Dev.show('" + id + "');\">Devoluciones</a></li>" +
	    "<li><a href=\"javascript:void(0);\" onclick=\"javascript:ProductReduction.show('" + id + "');\">Bajas</a></li>" +
	    "<li class=\"disabled\"><a href=\"javascript:void(0);\" onclick=\"javascript:BillHistory.showDetail('" + id + "');\"><i class=\"glyphicon glyphicon-zoom-in\"></i>&nbsp;Detalle</a></li>" +
	    "<li role=\"separator\" class=\"divider\"></li>" +
	    "<li class=\"dropdown-header\">Altas</li>" +
	    "<li class=\"" + map.detailClazzDisabled + "\"><a href=\"javascript:void(0);\" onclick=\"javascript:BillHistory.showModalPayment('" + id + "', '" + map.totalDailyInstallment + "', '" + map.collectorId + "');\">Pago</a></li>" +
	    "<li><a href=\"javascript:void(0);\" onclick=\"javascript:BillHistory.showDiscount('" + id + "');\">Descuento</a></li>" +
	    "<li><a href=\"javascript:void(0);\" onclick=\"javascript:BillHistory.showDev('" + id + "');\">Devoluci&oacute;n</a></li>" +
	    "<li><a href=\"javascript:void(0);\" onclick=\"javascript:BillHistory.showProductReduction('" + id + "');\">Baja</a></li>" +
	    "</ul></div>";
	
	return div;
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
			            },
			            { 	
			            	"className": 'centered',
			            	"data": "collectorId" 
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
					type:BootstrapDialog.TYPE_PRIMARY,
					draggable: true,
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

BillHistory.showModalPayment = function(id, paymentAmount, collectorId){
	if($(this).hasClass('disabled')){
		return false;
	}
	
	BootstrapDialog.show({
		onhidden:function(){
			BillHistory.resetModal();
			
			return;
		},
		draggable: true,
		type:BootstrapDialog.TYPE_PRIMARY,
		title: 'Cargar Pago',
		autodestroy: false,
        message: function(dialog) {
        	
        	// asigno el dia de hoy
        	$("#paymentDateValue").val(moment().format('DD/MM/YYYY'));
        	
        	$('#paymentDate').datetimepicker({
                locale: 'es',
                showTodayButton: true,
                format: 'DD/MM/YYYY'
            });
        	
        	$("#paymentBillId").val(id);
        	$("#paymentAmount").val(paymentAmount);
        	$("#paymentCollectorId").val(collectorId);
        	
        	$("#modal-payment-container").css({"display":"block"});
        	
        	return $("#modal-payment-container");
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
        	label: 'Guardar',
        	icon: 'glyphicon glyphicon-ok-sign',
        	cssClass: 'btn-primary',
        	action: function(dialog){
        		var btn = this;
        		var c = 0;
				
				$("#frmPayment").on('invalid.bs.validator', 
					function(e){
					    c++;
						
						return;
				});
				
				$("#frmPayment").validator('validate');
				
				if(c == 0){
					dialog.enableButtons(false);
					dialog.setClosable(false);
	        		btn.spin();
	        		
					// si esta todo ok entonces doy de alta ...
					BillHistory.addPayment(dialog, btn);
				}
        		
        		return;
        	}
        }]
    });
	
	return;
}

BillHistory.addPayment = function(dialog, btn){
	var billId = $("#paymentBillId").val();

	var obj = new Object();
	obj.billId = billId;
	obj.amount = $("#paymentAmount").val();
	obj.collectorId = $("#paymentCollectorId").val();
	obj.date = $("#paymentDateValue").val();
	
	$.ajax({ 
	   type    : "POST",
	   url     : Constants.contextRoot + "/controller/html/payment",
	   dataType: 'json',
	   data: JSON.stringify(obj),
	   contentType: "application/json;",
	   success:function(data) {
		   Message.hideMessages($('#modalPaymentAlertMessages'), $("#modalPaymentMessages"));
		   if(data != null && data.status == 0){

			   dialog.enableButtons(true);
			   dialog.setClosable(true);
       		   btn.stopSpin();
			   dialog.close();

			   BillHistory.init();
			   
			   return;
		   }else{
			   Message.showMessages($('#modalPaymentAlertMessages'), $("#modalPaymentMessages"), data.message);
			   
			   dialog.enableButtons(true);
			   dialog.setClosable(true);
       		   btn.stopSpin();
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#modalPaymentAlertMessages'), $("#modalPaymentMessages"), data.responseJSON.message);
		   dialog.enableButtons(true);
		   dialog.setClosable(true);
   		   btn.stopSpin();
		   
		   return;
	   }
	});
	
	return;
}

BillHistory.resetModal = function(){
	$("#paymentBillId").val("");
	$("#paymentAmount").val("");
	$("#paymentCollectorId").val("");
	$("#paymentDateValue").val("");

	return;
}

BillHistory.showDiscount = function(id){
	
	if($(this).hasClass('disabled')){
		return false;
	}
	
	BootstrapDialog.show({
		onhidden:function(){
			Discount.resetModal();
			
			return;
		},
		draggable: true,
		type:BootstrapDialog.TYPE_PRIMARY,
		title: 'Descuentos',
		autodestroy: false,
        message: function(dialog) {
        	
        	// asigno el dia de hoy
        	$("#discountDateValue").val(moment().format('DD/MM/YYYY'));
        	
        	$('#discountDate').datetimepicker({
                locale: 'es',
                showTodayButton: true,
                format: 'DD/MM/YYYY'
            });
        	
        	$("#discountBillId").val(id);
        	
        	$("#modal-discount-container").css({"display":"block"});
        	
        	return $("#modal-discount-container");
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
        	label: 'Guardar',
        	icon: 'glyphicon glyphicon-ok-sign',
        	cssClass: 'btn-primary',
        	action: function(dialog){
        		var btn = this;
        		var c = 0;
				
				$("#frmDiscount").on('invalid.bs.validator', 
					function(e){
					    c++;
						
						return;
				});
				
				$("#frmDiscount").validator('validate');
				
				if(c == 0){
					dialog.enableButtons(false);
					dialog.setClosable(false);
	        		btn.spin();
	        		
					// si esta todo ok entonces doy de alta ...
					Discount.add(dialog, btn);
				}
        		
        		return;
        	}
        }]
    });
	
	return;
}

BillHistory.showBonus = function(id){
	
	if($(this).hasClass('disabled')){
		return false;
	}
	
	BootstrapDialog.show({
		onhidden:function(){
			Bonus.resetModal();
			
			return;
		},
		draggable: true,
		type:BootstrapDialog.TYPE_PRIMARY,
		title: 'Alta de Premio',
		autodestroy: false,
        message: function(dialog) {
        	$('#bonusDate').datetimepicker({
                locale: 'es',
                showTodayButton: true,
                format: 'DD/MM/YYYY'
            });
        	
        	$("#bonusBillId").val(id);
        	
        	$("#modal-bonus-container").css({"display":"block"});
        	
        	return $("#modal-bonus-container");
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
        	label: 'Guardar',
        	icon: 'glyphicon glyphicon-ok-sign',
        	cssClass: 'btn-primary',
        	action: function(dialog){
        		var btn = this;
        		var c = 0;
				
				$("#frmBonus").on('invalid.bs.validator', 
					function(e){
					    c++;
						
						return;
				});
				
				$("#frmBonus").validator('validate');
				
				if(c == 0){
					dialog.enableButtons(false);
					dialog.setClosable(false);
	        		btn.spin();
	        		
					// si esta todo ok entonces doy de alta ...
					Bonus.add(dialog, btn);
				}
        		
        		return;
        	}
        }]
    });
	
	return;
}

BillHistory.showProductReduction = function(id){
	
	if($(this).hasClass('disabled')){
		return false;
	}
	
	BootstrapDialog.show({
		onhidden:function(){
			ProductReduction.resetModal();
			
			return;
		},
		draggable: true,
		type:BootstrapDialog.TYPE_PRIMARY,
		title: 'Baja',
		autodestroy: false,
        message: function(dialog) {
        	
        	// asigno el dia de hoy
        	$("#productReductionDateValue").val(moment().format('DD/MM/YYYY'));
        	
        	$('#productReductionDate').datetimepicker({
                locale: 'es',
                showTodayButton: true,
                format: 'DD/MM/YYYY'
            });
        	
        	$("#productReductionBillId").val(id);
        	
        	$("#modal-productReduction-container").css({"display":"block"});
        	
        	return $("#modal-productReduction-container");
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
        	label: 'Guardar',
        	icon: 'glyphicon glyphicon-ok-sign',
        	cssClass: 'btn-primary',
        	action: function(dialog){
        		var btn = this;
        		var c = 0;
				
				$("#frmProductReduction").on('invalid.bs.validator', 
					function(e){
					    c++;
						
						return;
				});
				
				$("#frmProductReduction").validator('validate');
				
				if(c == 0){
					dialog.enableButtons(false);
					dialog.setClosable(false);
	        		btn.spin();
	        		
					// si esta todo ok entonces doy de alta ...
	        		ProductReduction.add(dialog, btn);
				}
        		
        		return;
        	}
        }]
    });
	
	return;
}

BillHistory.showDev = function(id){
	
	if($(this).hasClass('disabled')){
		return false;
	}
	
	BootstrapDialog.show({
		onhidden:function(){
			Dev.resetModal();
			
			return;
		},
		draggable: true,
		type:BootstrapDialog.TYPE_PRIMARY,
		title: 'Alta de Devoluci&oacute;n',
		autodestroy: false,
        message: function(dialog) {
        	// asigno el dia de hoy
        	$("#devDateValue").val(moment().format('DD/MM/YYYY'));
        	
        	$('#devDate').datetimepicker({
                locale: 'es',
                showTodayButton: true,
                format: 'DD/MM/YYYY'
            });
        	
        	$("#devBillId").val(id);
        	
        	$("#modal-dev-container").css({"display":"block"});
        	
        	return $("#modal-dev-container");
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
        	label: 'Guardar',
        	icon: 'glyphicon glyphicon-ok-sign',
        	cssClass: 'btn-primary',
        	action: function(dialog){
        		var btn = this;
        		var c = 0;
				
				$("#frmDev").on('invalid.bs.validator', 
					function(e){
					    c++;
						
						return;
				});
				
				$("#frmDev").validator('validate');
				
				if(c == 0){
					dialog.enableButtons(false);
					dialog.setClosable(false);
	        		btn.spin();
	        		
					// si esta todo ok entonces doy de alta ...
					Dev.add(dialog, btn);
				}
        		
        		return;
        	}
        }]
    });
	
	return;
}
