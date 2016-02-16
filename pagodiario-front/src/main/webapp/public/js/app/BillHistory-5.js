BillHistory = function(){}

BillHistory.init = function(){
	
	$("#billHistoryCollapseButton").on("click", function(){

		var div = $("#bill-history-filter");
		
		var i = $(this).children("i").eq(0);
		if(div.hasClass("collapse")){
			i.removeClass("fa-chevron-down").addClass("fa-chevron-up");
		} if(div.hasClass("in")){
			i.removeClass("fa-chevron-up").addClass("fa-chevron-down");
			BillHistory.resetFilter(false);
		} else {
			i.removeClass("fa-chevron-down").addClass("fa-chevron-up");
		}
		
		return;
	});
	
	$("#billHistoryTicketNumber").on('keypress', function(e){
		
		BillHistory.resetCollectorFilter(false);
		BillHistory.resetStatus(false);
		
		return;
	});
	
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
		responsive: false,
		"createdRow": function ( row, data, index ) {
    		
    		$(row).data('billId', data.id);
    		
    		return;
        },
        "ajax": Constants.contextRoot + "/controller/html/bill",
        "columns": [
            { 	
            	"className": 'centered',
            	"data": "id" 
            },
            { 
            	"className": 'centered',
            	"orderable": false,
            	"data": "startDate"
            },
            { 	
            	"className": 'centered',
            	"orderable": false,
            	"data": "endDate" 
            },
            { 	
            	"className": 'centered',
            	"orderable": true,
            	"data": "creditNumber" 
            },
            { 
            	"className": 'centered',
            	"orderable": false,
            	"data": "collectorId" 
            },
            { 	
            	"className": 'centered',
            	"orderable": false,
            	"render": function ( data, type, row ) {
			        
			        return "<span id='overdueDays_" + row.id + "'>" + row.overdueDays + "</span>";
			    } 
            },
            { 
            	"className": 'centered',
            	"orderable": false,
            	"render": function ( data, type, row ) {
			        
			        return "<span id='installmentAmount_" + row.id + "'>" + row.totalDailyInstallment + "</span>";
			    } 
            },
            { 	
            	"className": 'centered',
            	"orderable": false,
            	"render": function ( data, type, row ) {
			        
			        return "<span id='totalAmount_" + row.id + "'>" + row.totalAmount + "</span>";
			    } 
            },
            { 	
            	"className": 'centered',
            	"orderable": false,
            	"render": function ( data, type, row ) {
			        
			        return "<span id='remainingAmount_" + row.id + "'>" + row.remainingAmount + "</span>";
			    }  
            },
            { 	
            	"className": 'centered',
            	"orderable": false,
            	"render": function ( data, type, row ) {
			        
			        var value = Bill.translateStatus(row.status);
			        
			        return "<span id='status_" + row.id + "'>" + value + "</span>";
			    } 
            },
            {
            	"className":      'centered',
	         	// The `data` parameter refers to the data for the cell (defined by the
                // `data` option, which defaults to the column being worked with, in
                // this case `data: 0`.
                "orderable": false,
                "render": function ( data, type, row ) {
                	
                	var clazzDisabled = "";
                	if(row.status == 'CANCELED' || row.status == 'CANCELED_DISCOUNT' || row.status == 'REDUCED'){
                		clazzDisabled = 'disabled';
                	}
                	
                	return BillHistory.getActionSelectElement(row.id, {
                		"totalDailyInstallment": row.totalDailyInstallment,
                		"collectorId" : row.collectorId,
                		"detailClazzDisabled": clazzDisabled,
                		"creditNumber" : row.creditNumber,
                		"status" : row.status,
                		"weekSunday": row.weekSunday,
                		"weekMonday": row.weekMonday,
                		"weekTuesday": row.weekTuesday,
                		"weekWednesday": row.weekWednesday,
                		"weekThursday": row.weekThursday,
                		"weekFriday": row.weekFriday,
                		"weekSaturday": row.weekSaturday
                	});
                } // onmouseover=\"javascript:Commons.setTooltip('btnShowPayments_');\"
         	}
        ],
        "order": [[3, 'desc']],
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
	
	$("#btnBillHistorySearch").on('click', function(){
		
		var collectorId = $("#billHistoryCollectorId").val();
		var creditNumber = $("#billHistoryTicketNumber").val();
		var status = $("#billHistoryStatus").val();
		var clientId = $("#billClientIdSelected").val();
		
		BillHistory.searchByFilter(collectorId, creditNumber, status, clientId);
		
		return;
	});
	
	$("#btnBillHistoryReset").on('click', function(){
		
		BillHistory.resetFilter(true);
		
		BillHistory.searchByFilter(null, null, null, null);
		
		return;
	});
	
	$("#billHistoryCollectorZone").on('keypress', function(){
		
		BillHistory.resetCollectorFilter(false);
		BillHistory.resetTicketNumber(false);
		
		return;
	});
	
	$("#billHistoryCollectorZone").keyup(function(e){
		if(e.keyCode == 13) {
			var value = $(this).val();
			if(value != null && value != ""){
				$("#billHistoryStatus").focus();
				return BillHistory.getCollectorByZone(value, $('#billHistoryAlertMessages'), $("#billHistoryMessages"));
			}
		} else {
			$("#billHistoryCollectorDescription").val("");
		}
	    
	    return;
	});
	
	$('#billHistoryCollectorZone').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	var value = $(this).val();
			if(value != null && value != ""){
				e.preventDefault();
				$("#billHistoryStatus").focus();
				return BillHistory.getCollectorByZone(value, $('#billHistoryAlertMessages'), $("#billHistoryMessages"));
			}
	    }
	    
	    return;
	});
	
	$("#billHistoryStatus").keyup(function(e){
		if(e.keyCode == 13) {
			$("#billHistoryTicketNumber").focus();			
		}
	    
	    return;
	});
	
	$('#billHistoryStatus').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#billHistoryTicketNumber").focus();			
	    }
	    
	    return;
	});
	
	$("#billHistoryTicketNumber").keyup(function(e){
		if(e.keyCode == 13) {
			$("#btnBillHistorySearch").focus();			
		}
	    
	    return;
	});
	
	$('#billHistoryTicketNumber').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#btnBillHistorySearch").focus();			
	    }
	    
	    return;
	});
	
	$("#btnBillHistorySearchCollector").on("click", function(){
		
		var value = $("#billHistoryCollectorZone").val();
		if(value != null && value != ""){
			return BillHistory.getCollectorByZone(value, $('#billHistoryAlertMessages'), $("#billHistoryMessages"));
		}
		
    	$.ajax({ 
		   type    : "GET",
		   url     : Constants.contextRoot + "/controller/html/collector",
		   dataType: 'json',
		   contentType: "application/json;",
		   success:function(data) {
				
			   var tbody = $("#tLovCollectorResult tbody");
			   
			   tbody.children('tr').remove();
			   
			   Message.hideMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"));
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
				            
				            $("#billHistoryCollectorId").val(selectedId);
				            $("#billHistoryCollectorZone").val(zone);
				            $("#billHistoryCollectorDescription").val(selectedDescription);
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
					Message.showMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"), data.message);
				}
		   },
		   error:function(data){
			   Message.showMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"), data.responseJSON.message);
			   
			   return;
		   }
		});
		
		return;
	});

	
	return;
}

BillHistory.getActionSelectElement = function(id, map){
	
	var status = map.status;
	
	var hideClass = "";
	if(status != 'ACTIVE'){
		hideClass = "hide";
	}
	var hideClassDiscount = "hide";
	if(status == 'CANCELED'){
		hideClassDiscount = "";
	}
	
	var div = "<div class=\"btn-group\">" +
	  "<button type=\"button\" class=\"btn btn-xs btn-primary dropdown-toggle\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">" +
	    "Acciones&nbsp;<span class=\"caret\"></span>" +
	  "</button>" +
	  "<ul class=\"dropdown-menu dropdown-menu-left\">" +
	  	"<li class=\"dropdown-header\">Consultas</li>" + 
	    /*"<li><a href=\"javascript:void(0);\" onclick=\"javascript:BillHistory.showPayments('" + id + "');\">Pagos</a></li>" +
	    "<li><a href=\"javascript:void(0);\" onclick=\"javascript:Discount.show('" + id + "');\">Descuentos</a></li>" +
	    "<li><a href=\"javascript:void(0);\" onclick=\"javascript:Dev.show('" + id + "');\">Devoluciones</a></li>" +
	    "<li><a href=\"javascript:void(0);\" onclick=\"javascript:ProductReduction.show('" + id + "');\">Bajas</a></li>" +*/
	    "<li><a href=\"" + Constants.contextRoot + "/controller/html/bill/detail/index?billId=" + id + "&creditNumber=" + map.creditNumber + "\" \"><i class=\"glyphicon glyphicon-zoom-in\"></i>&nbsp;Detalle</a></li>" +
	    "<li id=\"liSeparatorCreation_" + id + "\" role=\"separator " + hideClass + "\" class=\"divider\"></li>" +
	    "<li id=\"liTitleCreation_" + id + "\" class=\"dropdown-header " + hideClass + "\">Altas</li>" +
	    "<li id=\"liPayment_" + id + "\" data-weekSunday='" + map.weekSunday + "' data-weekMonday='" + map.weekMonday + "' data-weekTuesday='" + map.weekTuesday + "' data-weekWednesday='" + map.weekWednesday + "' data-weekThursday='" + map.weekThursday + "' data-weekFriday='" + map.weekFriday + "' data-weekSaturday='" + map.weekSaturday + "' class=\"" + map.detailClazzDisabled + " " + hideClass + "\">" +
	    	"<a href=\"javascript:void(0);\" onclick=\"javascript:BillHistory.showModalPayment('" + id + "', '" + map.totalDailyInstallment + "', '" + map.collectorId + "', '" + map.creditNumber + "');\">Pago</a>" + 
	    "</li>" +
	    "<li id=\"liDiscount_" + id + "\" class=\"" + hideClass + "\"><a href=\"javascript:void(0);\" onclick=\"javascript:BillHistory.showDiscount('" + id + "', '" + map.totalDailyInstallment + "');\">Descuento</a></li>" +
	    "<li id=\"liDev_" + id + "\" class=\"" + hideClass + "\"><a href=\"javascript:void(0);\" onclick=\"javascript:BillHistory.showDev('" + id + "');\">Devoluci&oacute;n</a></li>" +
	    "<li id=\"liReduction_" + id + "\" class=\"" + hideClass + "\"><a href=\"javascript:void(0);\" onclick=\"javascript:BillHistory.showProductReduction('" + id + "');\">Baja</a></li>" +
	    "<li id=\"liCancelDiscount_" + id + "\" class=\"" + hideClassDiscount + "\"><a href=\"javascript:void(0);\" onclick=\"javascript:BillHistory.cancelWithDiscount('" + id + "');\">Cancelar/Descuento</a></li>" +
	    "<li role=\"separator\" class=\"divider\"></li>" +
	    "<li class=\"dropdown-header\">Otras</li>";
	
	    var removeBillOpt = "<li class=\"ROLE_ADMIN\"><a href=\"javascript:void(0);\" onclick=\"javascript:BillHistory.remove('" + id + "');\"><i class=\"glyphicon glyphicon-remove-circle\"></i>&nbsp;Deshacer</a></li>";

	    if(Permission.isAdmin){
	    	div = div + removeBillOpt;
	    }
	    
	    var closeOpt = "</ul></div>";
	    
	    div = div + closeOpt;
	
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
					type:BootstrapDialog.TYPE_DANGER,
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

BillHistory.showModalPayment = function(id, paymentAmount, collectorId, creditNumber){
	if($(this).hasClass('disabled')){
		return false;
	}
	
	BootstrapDialog.show({
		onhidden:function(){
			BillHistory.resetModal();
			
			$("#creditNumberSpan").html("");
			
			return;
		},
		onshown:function(){
			$("#paymentDateValue").focus();
			
			$("#paymentDateValue").keyup(function(e){
				if(e.keyCode == 13) {
					e.preventDefault();
					$("#paymentAmount").focus();
				}
			    
			    return;
			});
			
			$("#paymentAmount").keyup(function(e){
				if(e.keyCode == 13) {
					e.preventDefault();
					$("#paymentCollectorId").focus();
				}
			    
			    return;
			});

			$("#paymentCollectorId").keyup(function(e){
				if(e.keyCode == 13) {
					e.preventDefault();
					$("#btnPayementAccept").focus();
				}
			    
			    return;
			});
			
			$("#paymentCollectorId").keydown(function(e){
				if(e.keyCode == 9) {
					e.preventDefault();
					$("#btnPayementAccept").focus();
				}
			    
			    return;
			});
			
			$("#creditNumberSpan").html(""+creditNumber);
			
			return;
		},
		draggable: true,
		type:BootstrapDialog.TYPE_DANGER,
		title: '<i class="fa fa-chevron-right"></i>&nbsp;Cargar Pago',
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
        	
        	var liPayment = $("#liPayment_" + id);
        	
        	var weekSunday = liPayment.data("weeksunday");
        	var weekMonday = liPayment.data("weekmonday");
        	var weekTuesday = liPayment.data("weektuesday");
        	var weekWednesday = liPayment.data("weekwednesday");
        	var weekThursday = liPayment.data("weekthursday");
        	var weekFriday = liPayment.data("weekfriday");
        	var weekSaturday = liPayment.data("weeksaturday");
        	
        	Commons.selectDayOfWeek($("#weekSunday"), weekSunday);
        	Commons.selectDayOfWeek($("#weekMonday"), weekMonday);
        	Commons.selectDayOfWeek($("#weekTuesday"), weekTuesday);
        	Commons.selectDayOfWeek($("#weekWednesday"), weekWednesday);
        	Commons.selectDayOfWeek($("#weekThursday"), weekThursday);
        	Commons.selectDayOfWeek($("#weekFriday"), weekFriday);
        	Commons.selectDayOfWeek($("#weekSaturday"), weekSaturday);
        	
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
        	id: 'btnPayementAccept',
        	label: 'Guardar',
        	icon: 'glyphicon glyphicon-ok-sign',
        	cssClass: 'btn-success',
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
					BillHistory.addPayment(dialog, btn, BillHistory.addResponseHandler);
				}
        		
        		return;
        	}
        }]
    });
	
	return;
}

BillHistory.addPayment = function(dialog, btn, responseHandler){
	var billId = $("#paymentBillId").val();
	
	var paymentDateValue = "";
	
	var paymentDate = $("#paymentDateValue").val();
	if(Commons.isValid(paymentDate)){
		paymentDateValue += "&paymentDate=" + paymentDate;
	}
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/payment?billId=" + billId + paymentDateValue,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
		   Message.hideMessages($('#modalPaymentAlertMessages'), $("#modalPaymentMessages"));
		   if(data != null && data.status == 0){
			   
			   var enabled = true;
			   
			   if(data.data.length > 0){
				   enabled = confirm("Esta factura ya tiene 1 o más pagos hechos para el dia seleccionado. Quiere continuar?");
			   }
			   
			   if(enabled){
				   var obj = new Object();
					obj.billId = billId;
					obj.amount = $("#paymentAmount").val();
					obj.collectorId = $("#paymentCollectorId").val();
					obj.date = $("#paymentDateValue").val();
					
					obj.weekSunday = $("#weekSunday").prop("checked");
					obj.weekMonday = $("#weekMonday").prop("checked");
					obj.weekTuesday = $("#weekTuesday").prop("checked");
					obj.weekWednesday = $("#weekWednesday").prop("checked");
					obj.weekThursday = $("#weekThursday").prop("checked");
					obj.weekFriday = $("#weekFriday").prop("checked");
					obj.weekSaturday = $("#weekSaturday").prop("checked");
					
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
		
							   //BillHistory.init();
							   responseHandler(data);
							   
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
			   } else {
				   dialog.enableButtons(true);
				   dialog.setClosable(true);
		   		   btn.stopSpin();
			   }

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

BillHistory.showDiscount = function(id, installmentAmount){
	
	if($(this).hasClass('disabled')){
		return false;
	}
	
	BootstrapDialog.show({
		onhidden:function(){
			Discount.resetModal();
			$("#frmDiscount").validator('destroy');
			
			return;
		},
		onshown:function(){
			$("#discountDateValue").focus();
			
			$("#discountDateValue").keyup(function(e){
				if(e.keyCode == 13) {
					e.preventDefault();
					$("#discountAmount").focus();
				}
			    
			    return;
			});
			
			$("#discountAmount").keyup(function(e){
				if(e.keyCode == 13) {
					e.preventDefault();
					$("#discountInstallmentAmount").focus();
				}
			    
			    return;
			});
			
			$("#discountInstallmentAmount").keyup(function(e){
				if(e.keyCode == 13) {
					e.preventDefault();
					$("#discountObservations").focus();
				}
			    
			    return;
			});
			
			$("#discountObservations").keydown(function(e){
				if(e.keyCode == 9) {
					e.preventDefault();
					$("#btnDiscountAccept").focus();
				}
			    
			    return;
			});
			
			return;
		},
		draggable: true,
		type:BootstrapDialog.TYPE_DANGER,
		title: '<i class="fa fa-chevron-right"></i>&nbsp;Descuentos',
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
        	$("#discountInstallmentAmount").val(installmentAmount);
        	
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
        	id: 'btnDiscountAccept',
        	label: 'Guardar',
        	icon: 'glyphicon glyphicon-ok-sign',
        	cssClass: 'btn-success',
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
					Discount.add(dialog, btn, BillHistory.addResponseHandler);
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
		type:BootstrapDialog.TYPE_DANGER,
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
        	cssClass: 'btn-success',
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
			Message.hideMessages($('#modalProductReductionAlertMessages'), $("#modalProductReductionMessages"));
			
			return;
		},
		onshown:function(){
			$("#productReductionDateValue").focus();
			
			$("#productReductionDateValue").keyup(function(e){
				if(e.keyCode == 13) {
					e.preventDefault();
					$("#productReductionAmount").focus();
				}
			    
			    return;
			});
			
			$("#productReductionAmount").keyup(function(e){
				if(e.keyCode == 13) {
					e.preventDefault();
					$("#productReductionObservations").focus();
				}
			    
			    return;
			});
			
			$("#productReductionObservations").keydown(function(e){
				if(e.keyCode == 9) {
					e.preventDefault();
					$("#btnReductionAccept").focus();
				}
			    
			    return;
			});
			
			return;
		},
		draggable: true,
		type:BootstrapDialog.TYPE_DANGER,
		title: '<i class="glyphicon glyphicon-chevron-right"></i>&nbsp;Baja',
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
        	id: 'btnReductionAccept',
        	label: 'Guardar',
        	icon: 'glyphicon glyphicon-ok-sign',
        	cssClass: 'btn-success',
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
	        		ProductReduction.add(dialog, btn, BillHistory.addResponseHandler);
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
	
	var dialog = new BootstrapDialog({
		onshown: function(){
			$("#devDateValue").focus();
			Dev.getDevInfo(id);
			
			$("#devDateValue").keydown(function(e){
				if(e.keyCode == 9) {
					e.preventDefault();
					$("#devProductCount_0").focus();
				}
			    
			    return;
			});

			$("#devDateValue").keyup(function(e){
				if(e.keyCode == 10 || e.keyCode == 13) {
					e.preventDefault();
					$("#devProductCount_0").focus();
				}
			    
			    return;
			});
			
			$("#devObservations").keydown(function(e){
				if(e.keyCode == 9) {
					e.preventDefault();
					$("#btnAcceptDev").focus();
				}
			    
			    return;
			});
			
			return;
		},
		onhidden:function(){
			Dev.resetModal();
			$("#frmDev").validator('destroy');
			$("#modalDevProductRow").remove();
			
			return;
		},
		draggable: true,
		size: BootstrapDialog.SIZE_LARGE,
		type:BootstrapDialog.TYPE_DANGER,
		title: '<i class="glyphicon glyphicon-chevron-right"></i>&nbsp;Alta de Devoluci&oacute;n',
		cssClass: 'dialog-dev',
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
        	id: 'btnAcceptDev',
        	label: 'Guardar',
        	hotkey: 13,
        	icon: 'glyphicon glyphicon-ok-sign',
        	cssClass: 'btn-success',
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
					Dev.add(dialog, btn, BillHistory.addResponseHandler);
				}
        		
        		return;
        	}
        }]
    });
	
	dialog.setSize(BootstrapDialog.SIZE_WIDE);
	dialog.open();
	
	return;
}

BillHistory.remove = function(id){
	
	BootstrapDialog.confirm({
		title: "Confirmación",
		message: "Esta seguro de eliminar la factura seleccionada?",
		type: BootstrapDialog.TYPE_DANGER,
		draggable: true,
		btnCancelLabel: '<i class="glyphicon glyphicon-remove-sign"></i>&nbsp;NO', // <-- Default value is 'Cancel',
        btnOKLabel: '<i class="glyphicon glyphicon-ok-sign"></i>&nbsp;SI', // <-- Default value is 'OK',
        btnOKClass: 'btn-success',
		callback: function(result){
			if(result) {
				$.ajax({ 
				   type    : "DELETE",
				   url     : Constants.contextRoot + "/controller/html/bill/" + id,
				   dataType: 'json',
				   contentType: "application/json;",
				   success:function(data) {
					   Message.hideMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"));
					   if(data != null && data.status == 0){
						   
						   var trList = $('#tBillResult > tbody > tr');
						   
						   var row = null;
						   
						   trList.each(function(){
							   
							   var billId = $(this).data('billId');
							   if(billId == id){
								   row = $(this);
							   }
							   
							   return;
						   });
						   var table = $('#tBillResult').dataTable();

						   if(row != null){
							   table.fnDeleteRow(row, null, true);
						   }
						   
						   return;
					   }else{
						   Message.showMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"), data.message);
					   }
				   },
				   error:function(data){
					   Message.showMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"), data.responseJSON.message);
					   
					   return;
				   }
				});
			}
			
			return;
		}
	});
	
	return;
}

BillHistory.exportToPdf = function(){

	$("#frmBillHistoryExportPdf").submit();
	
	return;
}

BillHistory.searchByFilter = function(collectorId, creditNumber, status, clientId){
	
	var urlQueryString = "";
	if(collectorId != null && collectorId != ""){
		urlQueryString = "?collectorId=" + collectorId;
		$("#bhCollectorId").val(collectorId);
	}
	if(creditNumber != null && creditNumber != ""){
		if(urlQueryString == ""){
			urlQueryString = "?";
		} else {
			urlQueryString = urlQueryString + "&";
		}
		urlQueryString = urlQueryString + "creditNumber=" + creditNumber;
			
		$("#bhCreditNumber").val(creditNumber);
	}
	if(status != null && status != ""){
		if(urlQueryString == ""){
			urlQueryString = "?";
		} else {
			urlQueryString = urlQueryString + "&";
		}
		urlQueryString = urlQueryString + "status=" + status;
		$("#bhStatus").val(status);
	}
	if(clientId != null && clientId != ""){
		if(urlQueryString == ""){
			urlQueryString = "?";
		} else {
			urlQueryString = urlQueryString + "&";
		}
		urlQueryString = urlQueryString + "clientId=" + clientId;
		$("#bhClientId").val(clientId);
	}
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/bill" + urlQueryString,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
			
		   var tbody = $("#tBillResult tbody");
		   
		   tbody.children('tr').remove();
		   
		   Message.hideMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"));
		   if(data != null && data.status == 0){
			   if(data)
			   
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
				"createdRow": function ( row, data, index ) {
		    		
		    		$(row).data('billId', data.id);
		    		
		    		return;
		        },
		        "data": data.data,
		        "columns": [
		            { 	
		            	"className": 'centered',
		            	"data": "id" 
		            },
		            { 
		            	"className": 'centered',
		            	"orderable": false,
		            	"data": "startDate"
		            },
		            { 	
		            	"className": 'centered',
		            	"orderable": false,
		            	"data": "endDate" 
		            },
		            { 	
		            	"className": 'centered',
		            	"orderable": true,
		            	"data": "creditNumber" 
		            },
		            { 
		            	"className": 'centered',
		            	"orderable": false,
		            	"data": "collectorId" 
		            },
		            { 	
		            	"className": 'centered',
		            	"orderable": false,
		            	"render": function ( data, type, row ) {
					        
					        return "<span id='overdueDays_" + row.id + "'>" + row.overdueDays + "</span>";
					    } 
		            },
		            { 
		            	"className": 'centered',
		            	"orderable": false,
		            	"render": function ( data, type, row ) {
					        
					        return "<span id='installmentAmount_" + row.id + "'>" + row.totalDailyInstallment + "</span>";
					    } 
		            },
		            { 	
		            	"className": 'centered',
		            	"orderable": false,
		            	"data": "totalAmount" 
		            },
		            { 	
		            	"className": 'centered',
		            	"orderable": false,
		            	"render": function ( data, type, row ) {
					        
					        return "<span id='remainingAmount_" + row.id + "'>" + row.remainingAmount + "</span>";
					    }  
		            },
		            { 	
		            	"className": 'centered',
		            	"orderable": false,
		            	"render": function ( data, type, row ) {
					        
					        var value = Bill.translateStatus(row.status);
					        
					        return "<span id='status_" + row.id + "'>" + value + "</span>";
					    } 
		            },
		            {
		            	"className":      'centered',
			         	// The `data` parameter refers to the data for the cell (defined by the
		                // `data` option, which defaults to the column being worked with, in
		                // this case `data: 0`.
		                "orderable": false,
		                "render": function ( data, type, row ) {
		                	
		                	var clazzDisabled = "";
		                	if(row.status == 'CANCELED' || row.status == 'CANCELED_DISCOUNT' || row.status == 'REDUCED'){
		                		clazzDisabled = 'disabled';
		                	}
		                	
		                	return BillHistory.getActionSelectElement(row.id, {
		                		"totalDailyInstallment": row.totalDailyInstallment,
		                		"collectorId" : row.collectorId,
		                		"detailClazzDisabled": clazzDisabled,
		                		"creditNumber" : row.creditNumber,
		                		"status" : row.status
		                	});
		                } // onmouseover=\"javascript:Commons.setTooltip('btnShowPayments_');\"
		         	}
		        ],
		        "order": [[3, 'desc']],
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

BillHistory.getCollectorByZone = function(zone, alertMessagesContainer, messagesContainer){
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/collector?zone=" + zone,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {

		   $("#billHistoryCollectorDescription").parent().next().html("");
		   $("#billHistoryCollectorDescription").val("");
		   $("#billHistoryCollectorZone").parent().parent().removeClass("has-error");
		   $("#billHistoryCollectorDescription").parent().parent().removeClass("has-error");
		   
		   Message.hideMessages(alertMessagesContainer, messagesContainer);
		   
		   if(data != null && data.status == 0){
			   
			   var list = data.data;
			   
			   if(list.length > 0){
				   var element = list[0];
				   
				   $("#billHistoryCollectorId").val(element.id)
				   $("#billHistoryCollectorZone").val(element.zone)
		           $("#billHistoryCollectorDescription").val(element.description);				   
			
		           $("#bClientId").focus();
			   } else {
				   $("#billHistoryCollectorZone").focus();
				   $("#billHistoryCollectorDescription").parent().next().append("Cobrador no encontrado");
				   $("#billHistoryCollectorZone").parent().parent().addClass("has-error");
				   $("#billHistoryCollectorDescription").parent().parent().addClass("has-error");
			   }
			   
		   } else {
				Message.showMessages(alertMessagesContainer, messagesContainer, data.message);
		   }
	   },
	   error:function(data){
		   Message.showMessages(alertMessagesContainer, messagesContainer, data.responseJSON.message);
		   
		   return false;
	   }
	});
	
	return;
}

BillHistory.hideFilter = function(){

	BillHistory.resetFilter(false);
	
	$("#bill-history-filter").hide("slow");
	
	return;
}

BillHistory.showFilter = function(){
	
	$("#bill-history-filter").show("slow");
	
	return;
}

BillHistory.resetFilter = function(enabled){

	BillHistory.resetCollectorFilter(enabled);
	BillHistory.resetTicketNumber(enabled);
	BillHistory.resetStatus(enabled);
	BillHistory.resetClient(enabled);
	
	return;
}

BillHistory.resetCollectorFilter = function(enabled){
	
	$("#billHistoryCollectorId").val("");
	if(enabled){
		$("#billHistoryCollectorZone").val("");
		$("#bhCollectorId").val("");
	}
	$("#billHistoryCollectorDescription").val("");
	
	$("#billHistoryCollectorDescription").parent().next().html("");
	$("#billHistoryCollectorDescription").val("");
	$("#billHistoryCollectorZone").parent().parent().removeClass("has-error");
	$("#billHistoryCollectorDescription").parent().parent().removeClass("has-error");
	
	return;
}

BillHistory.resetTicketNumber = function(enabled){
	
	$("#billHistoryTicketNumber").val("");
	if(enabled){
		$("#bhCreditNumber").val("");
	}
	
	return;
}

BillHistory.resetStatus = function(enabled){
	
	$('#billHistoryStatus option:contains("Todos")').prop('selected', true);
	if(enabled){
		$("#bhStatus").val("");
	}
	
	return;
}

BillHistory.resetClient = function(enabled){
	
	$("#billClientIdSelected").val("");
	$("#baddress").val("");
	if(enabled){
		$("#bhClientId").val("");
	}
	
	return;
}

BillHistory.addResponseHandler = function(data){

	var list = data.data;
	
	var element = list[0];
	
	var billStatus = element.billStatus;
	var billId = element.billId;
	
	if(billStatus != 'ACTIVE'){
		$("#liSeparatorCreation_" + element.billId).addClass("hide");
		$("#liTitleCreation_" + element.billId).addClass("hide");
		$("#liPayment_" + element.billId).addClass("hide");
		$("#liDiscount_" + element.billId).addClass("hide");
		$("#liDev_" + element.billId).addClass("hide");
		$("#liReduction_" + element.billId).addClass("hide");
	}
	if(billStatus == 'CANCELED'){
		$("#liCancelDiscount_" + billId).removeClass("hide");
	}
	
	var status = Bill.translateStatus(billStatus);
	$("#status_" + element.billId).html(status)

	if(Commons.isValid(element.overdueDays)){
		$("#overdueDays_" + billId).html(element.overdueDays);
	}
	if(Commons.isValid(element.installmentAmount)){
		$("#installmentAmount_" + billId).html(element.installmentAmount);
	}
	if(Commons.isValid(element.remainingAmount)){
		$("#remainingAmount_" + billId).html(element.remainingAmount);
	}
	if(Commons.isValid(element.totalAmount)){
		$("#totalAmount_" + billId).html(element.totalAmount);
	}
	
	var liPayment = $("#liPayment_" + element.billId);
	
	liPayment.data("weeksunday", element.weekSunday);
	liPayment.data("weekmonday", element.weekMonday);
	liPayment.data("weektuesday", element.weekTuesday);
	liPayment.data("weekwednesday", element.weekWednesday);
	liPayment.data("weekthursday", element.weekThursday);
	liPayment.data("weekfriday", element.weekFriday);
	liPayment.data("weeksaturday", element.weekSaturday);
	
	return;
}

BillHistory.cancelWithDiscount = function(id){

	$.ajax({ 
	   type    : "POST",
	   url     : Constants.contextRoot + "/controller/html/bill/canceldiscount?id=" + id,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
		   Message.hideMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"));
		   if(data != null && data.status == 0){

			   BillHistory.addResponseHandler(data);
			   
			   return;
		   }else{
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