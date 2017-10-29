PaymentFilter = function(){}

PaymentFilter.init = function(){
	
		$("#paymentFilterCollapseButton").on("click", function(){

			var div = $("#payment-filter");
			
			var i = $(this).children("i").eq(0);
			if(div.hasClass("collapse")){
				i.removeClass("fa-chevron-down").addClass("fa-chevron-up");
			} if(div.hasClass("in")){
				i.removeClass("fa-chevron-up").addClass("fa-chevron-down");
				PaymentFilter.resetFilter(false);
			} else {
				i.removeClass("fa-chevron-down").addClass("fa-chevron-up");
			}
			
			return;
		});
		
		$("#btnPaymentFilterSearch").on('click', function(){
			
			var collectorId = $("#paymentFilterCollectorId").val();
			var dateFrom = $("#paymentFilterDateFromValue").val();
			var dateTo = $("#paymentFilterDateToValue").val();
			var clientId = $("#paymentFilterClientIdSelected").val();
			
			PaymentFilter.searchByFilter(collectorId, dateFrom, dateTo, clientId);
			
			return;
		});
		
		$("#btnPaymentFilterReset").on('click', function(){
			
			PaymentFilter.resetFilter(true);
			
			var table = $('#tPaymentFilterResult').DataTable();
			 
			table
			    .clear()
			    .draw();
			
			return;
		});
		
		$("#paymentFilterCollectorZone").on('keypress', function(){
			
			PaymentFilter.resetCollectorFilter(false);
			
			return;
		});
		
		$("#paymentFilterCollectorZone").keyup(function(e){
			if(e.keyCode == 13) {
				var value = $(this).val();
				if(value != null && value != ""){
					$("#paymentFilterStatus").focus();
					return PaymentFilter.getCollectorByZone(value, $('#paymentFilterAlertMessages'), $("#paymentFilterMessages"));
				}
			} else {
				$("#paymentFilterCollectorDescription").val("");
			}
		    
		    return;
		});
		
		$('#paymentFilterCollectorZone').keydown(function(e){
			// 13: enter
			// 9: tab
		    if(e.keyCode == 9){
		    	var value = $(this).val();
				if(value != null && value != ""){
					e.preventDefault();
					$("#paymentFilterStatus").focus();
					return PaymentFilter.getCollectorByZone(value, $('#paymentFilterAlertMessages'), $("#paymentFilterMessages"));
				}
		    }
		    
		    return;
		});
		
		$("#paymentFilterDateFrom").keyup(function(e){
			if(e.keyCode == 13) {
				$("#paymentFilterDateTo").focus();			
			}
		    
		    return;
		});
		
		$('#paymentFilterDateTo').keydown(function(e){
			// 13: enter
			// 9: tab
		    if(e.keyCode == 9){
		    	e.preventDefault();
				$("#btnPaymentFilterSearch").focus();			
		    }
		    
		    return;
		});
		
		$("#btnPaymentFilterSearchCollector").on("click", function(){
			
			var value = $("#paymentFilterCollectorZone").val();
			if(value != null && value != ""){
				return PaymentFilter.getCollectorByZone(value, $('#paymentFilterAlertMessages'), $("#paymentFilterMessages"));
			}
			
	    	$.ajax({ 
			   type    : "GET",
			   url     : Constants.contextRoot + "/controller/html/collector",
			   dataType: 'json',
			   contentType: "application/json;",
			   success:function(data) {
					
				   var tbody = $("#tLovCollectorResult tbody");
				   
				   tbody.children('tr').remove();
				   
				   Message.hideMessages($('#paymentFilterAlertMessages'), $("#paymentFilterMessages"));
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
					            
					            $("#paymentFilterCollectorId").val(selectedId);
					            $("#paymentFilterCollectorZone").val(zone);
					            $("#paymentFilterCollectorDescription").val(selectedDescription);
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
						Message.showMessages($('#paymentFilterAlertMessages'), $("#paymentFilterMessages"), data.message);
					}
			   },
			   error:function(data){
				   Message.showMessages($('#paymentFilterAlertMessages'), $("#paymentFilterMessages"), data.responseJSON.message);
				   
				   return;
			   }
			});
			
			return;
		});

		$('#paymentFilterDateFrom').datetimepicker({
	        locale: 'es',
	        showTodayButton: true,
	        format: 'DD/MM/YYYY'
	    });
		
		$('#paymentFilterDateTo').datetimepicker({
	        locale: 'es',
	        showTodayButton: true,
	        format: 'DD/MM/YYYY'
	    });
		
		//$("#paymentFilterDateFromValue").val(moment().subtract(3, 'months').format('DD/MM/YYYY'));
		// asigno el dia de hoy
		//$("#paymentFilterDateToValue").val(moment().format('DD/MM/YYYY'));
		
		//$("#cfDateFrom").val(moment().subtract(3, 'months').format('DD/MM/YYYY'));
		// asigno el dia de hoy
		//$("#cfDateTo").val(moment().format('DD/MM/YYYY'));
		var table = $("#tPaymentFilterResult").dataTable( {
			"fnInitComplete": function(){
				$('#tPaymentFilterResult tbody tr').each(function(){
					$(this).find('td:eq(1)').attr('nowrap', 'nowrap');
					$(this).find('td:eq(2)').attr('nowrap', 'nowrap');
					
					return;
				});
				
				return;
			},
			/*"bAutoWidth": false,*/
			"bDestroy" : true,
			responsive: true,
			"createdRow": function ( row, data, index ) {
	    		
	    		$(row).data('client_id', data.id);
	    		
	    		return;
	        },
	        "data": [],
	        "columns": [
				{ 	
					"className": '',
					"orderable": true,
					"data": "id" 
				},        
				{ 
	            	"className": 'centered',
	            	"orderable": true,
	            	"data": "creditNumber"
	            },
	            { 
	            	"className": '',
	            	"orderable": true,
	            	"data": "amount"
	            },
	            { 	
	            	"className": 'centered',
	            	"orderable": true,
	            	"data": "date" 
	            },
	            { 
	            	"className": 'centered',
	            	"orderable": true,
	            	"data": "collectorZone" 
	            },
	            { 
	            	"className": 'centered',
	            	"orderable": true,
	            	"data": "collectorDescription" 
	            },
	            { 
	            	"className": 'centered',
	            	"orderable": true,
	            	"render": function(data, type, row){
	            		if(row.traderPayment == true){
	            			return 'Si';
	            		}
	            		
	            		return 'No';
	            	}
	            },
	            { 
	            	"className": '',
	            	"orderable": true,
	            	"data": "clientName" 
	            }
	        ],
	        "order": [[0, 'asc']],
	        "language": {
	            "lengthMenu": "Mostrar _MENU_ registros por p&aacute;gina",
	            "zeroRecords": "No se ha encontrado ningun elemento",
	            "info": "P&aacute;gina _PAGE_ de _PAGES_ <b>(Total: _MAX_ Pagos)</b>",
	            "infoEmpty": "No hay registros disponibles",
	            "infoFiltered": /*"(filtrados de un total de _MAX_ registros)"*/"",
	            "search": "Buscar: ",
	            "paginate": {
	            	"previous": "Anterior",
					"next": "Siguiente"
				}
	        } 
	    });
		
	return;
}

PaymentFilter.exportToCsv = function(){
	
	var collectorId = $("#cfCollectorId").val();
	var dateFrom = $("#cfDateFrom").val();
	var clientId = $("#cfClientId").val();
	
	if((collectorId == null || collectorId == "") && (dateFrom == null || dateFrom == "") && (clientId == null || clientId == "")){
		Message.showMessages($('#paymentFilterAlertMessages'), $("#paymentFilterMessages"), "Alguno de los filtros es requerido");
		
		return false;
	}

	$("#frmPaymentFilterExportCsv").submit();
	
	return;
}

PaymentFilter.searchByFilter = function(collectorId, dateFrom, dateTo, clientId){
	
	Message.hideMessages($('#paymentFilterAlertMessages'), $("#paymentFilterMessages"));
	
	var urlQueryString = "";
	if(collectorId != null && collectorId != ""){
		urlQueryString = "?collectorId=" + collectorId;
		$("#cfCollectorId").val(collectorId);
	}
	if(dateFrom != null && dateFrom != ""){
		if(urlQueryString == ""){
			urlQueryString = "?";
		} else {
			urlQueryString = urlQueryString + "&";
		}
		urlQueryString = urlQueryString + "dateFrom=" + dateFrom;
		$("#cfDateFrom").val(dateFrom);
	}
	if(dateTo != null && dateTo != ""){
		if(urlQueryString == ""){
			urlQueryString = "?";
		} else {
			urlQueryString = urlQueryString + "&";
		}
		urlQueryString = urlQueryString + "dateTo=" + dateTo;
		$("#cfDateTo").val(dateTo);
	}
	if(clientId != null && clientId != ""){
		if(urlQueryString == ""){
			urlQueryString = "?";
		} else {
			urlQueryString = urlQueryString + "&";
		}
		urlQueryString = urlQueryString + "clientId=" + clientId;
		$("#cfClientId").val(clientId);
	}
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/payment/filter" + urlQueryString,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
			
		   var tbody = $("#tPaymentFilterResult tbody");
		   
		   tbody.children('tr').remove();
		   
		   Message.hideMessages($('#paymentFilterAlertMessages'), $("#paymentFilterMessages"));
		   if(data != null && data.status == 0){
			   if(data)
			   
			   var table = $("#tPaymentFilterResult").dataTable( {
				"fnInitComplete": function(){
					$('#tPaymentFilterResult tbody tr').each(function(){
						$(this).find('td:eq(1)').attr('nowrap', 'nowrap');
						$(this).find('td:eq(2)').attr('nowrap', 'nowrap');
						
						return;
					});
					
					return;
				},
				/*"bAutoWidth": false,*/
				"bDestroy" : true,
				responsive: true,
				"fnFooterCallback": function ( nRow, aaData, iStart, iEnd, aiDisplay ) {
					
					var totalAmountAcum = 0;
					
					for ( var i=0 ; i<aaData.length ; i++ ){
						totalAmountAcum += parseFloat(aaData[i].amount);
					}
					
                    $("#totAmount").html("$ " + totalAmountAcum.toFixed(2));
                    
                    return;
                },
				"createdRow": function ( row, data, index ) {
		    		
		    		$(row).data('rowid', data.id);
		    		
		    		return;
		        },
		        "data": data.data,
		        "columns": [
					{ 	
						"className": '',
						"orderable": true,
						"data": "id" 
					},
					{ 
		            	"className": 'centered',
		            	"orderable": true,
		            	"data": "creditNumber"
		            },
		            { 
		            	"className": 'centered',
		            	"orderable": true,
		            	"data": "amount"
		            },
		            { 	
		            	"className": 'centered',
		            	"orderable": true,
		            	"data": "date" 
		            },
		            { 
		            	"className": 'centered',
		            	"orderable": true,
		            	"data": "collectorZone" 
		            },
		            { 
		            	"className": 'centered',
		            	"orderable": true,
		            	"data": "collectorDescription" 
		            },
		            { 
		            	"className": 'centered',
		            	"orderable": true,
		            	"render": function(data, type, row){
		            		if(row.traderPayment == true){
		            			return 'Si';
		            		}
		            		
		            		return 'No';
		            	}
		            },
		            { 
		            	"className": '',
		            	"orderable": true,
		            	"data": "clientName" 
		            }
		        ],
		        "order": [[3, 'asc']],
		        "language": {
		            "lengthMenu": "Mostrar _MENU_ registros por p&aacute;gina",
		            "zeroRecords": "No se ha encontrado ningun elemento",
		            "info": "P&aacute;gina _PAGE_ de _PAGES_ <b>(Total: _MAX_ Pagos)</b>",
		            "infoEmpty": "No hay registros disponibles",
		            "infoFiltered": /*"(filtrados de un total de _MAX_ registros)"*/"",
		            "search": "Buscar: ",
		            "paginate": {
		            	"previous": "Anterior",
						"next": "Siguiente"
					}
		        } 
		    });
		   } else {
			   Message.showMessages($('#paymentFilterAlertMessages'), $("#paymentFilterMessages"), data.message);
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#paymentFilterAlertMessages'), $("#paymentFilterMessages"), data.responseJSON.message);
		   
		   return;
	   }
	});
	
	return;
}

PaymentFilter.getCollectorByZone = function(zone, alertMessagesContainer, messagesContainer){
	
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
				   
				   $("#paymentFilterCollectorId").val(element.id)
				   $("#paymentFilterCollectorZone").val(element.zone)
		           $("#paymentFilterCollectorDescription").val(element.description);				   
			
		           $("#bClientId").focus();
			   } else {
				   $("#paymentFilterCollectorZone").focus();
				   $("#paymentFilterCollectorDescription").parent().next().append("Cobrador no encontrado");
				   $("#paymentFilterCollectorZone").parent().parent().addClass("has-error");
				   $("#paymentFilterCollectorDescription").parent().parent().addClass("has-error");
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

PaymentFilter.hideFilter = function(){

	PaymentFilter.resetFilter(false);
	
	$("#payment-filter").hide("slow");
	
	return;
}

PaymentFilter.showFilter = function(){
	
	$("#payment-filter").show("slow");
	
	return;
}

PaymentFilter.resetFilter = function(enabled){

	PaymentFilter.resetCollectorFilter(enabled);
	PaymentFilter.resetDates(enabled);
	
	return;
}

PaymentFilter.resetCollectorFilter = function(enabled){
	
	$("#paymentFilterCollectorId").val("");
	if(enabled){
		$("#paymentFilterCollectorZone").val("");
		$("#cfCollectorId").val("");
		$("#cfClientId").val("");
	}
	$("#paymentFilterCollectorDescription").val("");
	
	$("#paymentFilterCollectorDescription").parent().next().html("");
	$("#paymentFilterCollectorDescription").val("");
	$("#paymentFilterCollectorZone").parent().parent().removeClass("has-error");
	$("#paymentFilterCollectorDescription").parent().parent().removeClass("has-error");
	
	$("#cfDateFrom").val("");
	$("#cfDateTo").val("");
	
	$("#paymentFilterClientIdSelected").val("");
	$("#baddress").val("");
	
	return;
}

PaymentFilter.resetDates = function(enabled){

	$("#paymentFilterDateFromValue").val("");
	// asigno el dia de hoy
	$("#paymentFilterDateToValue").val("");
	
	if(enabled){
		$("#cfDateFrom").val("");
		// asigno el dia de hoy
		$("#cfDateTo").val("");
	}
	
	return;
}

$("#btnSearchPaymentFilterClient").on("click", function(){
	
	var lovClientContainer = $("#lov-client-container");
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/client",
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
			
		   var tbody = $("#tLovClientResult tbody");
		   
		   tbody.children('tr').remove();
		   
		   Message.hideMessages($('#paymentFilterAlertMessages'), $("#paymentFilterMessages"));
		   if(data != null && data.status == 0){

			   var table = $("#tLovClientResult").dataTable( {
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
							"data": "name" 
						},
			            { 	
			            	"className": 'centered',
			            	"data": "companyAddress" 
			            },
			            { 	
			            	"className": 'centered',
			            	"data": "companyType" 
			            },
			            { 	
			            	"className": 'centered',
			            	"render": function ( data, type, row ) {
			                    //return data +' ('+ row[3]+')';
			            		var mark = "<i class=\"fa fa-square-o fa-4\"></i>";
			            		if(row.reductionMark != null && row.reductionMark != ""){
			            			mark = "<i class=\"fa fa-check-square-o fa-4\"></i>";
			            		}
			            		
			                    return "<span id='reductionMark_'" + row.id + " style='font-weight:bold;'>" + mark + "</span>"; 
			                }
			            }
			        ],
			        "order": [[1, 'asc']],
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
			   
			   var tbodyLovClientResult = $('#tLovClientResult tbody');
			   
			   tbodyLovClientResult.on('mouseover', 'tr', function () {
					$(this).css({"cursor": "pointer"});	
					
					return;
				});
				
			   tbodyLovClientResult.on( 'click', 'tr', function () {
				   
				   var me = $(this);
				   
			       if ( me.hasClass('selected') ) {
			    	   me.removeClass('selected');
			       } else {
			    	   table.$('tr.selected').removeClass('selected');
			    	   me.addClass('selected');
			    	   
			    	   var selectedId = me.children('td').eq(0).html().trim();
			    	   var selectedDescription = me.children('td').eq(1).html().trim();
			    	   var address = me.children('td').eq(2).html().trim();
			    	   var companyType = me.children('td').eq(3).html().trim();
			    	   var reductionMark = me.children('td').eq(4).html().trim();
			    	   
			    	   var hasMark = $(reductionMark).children("i").hasClass("fa-check-square-o");
			            
			    	   var mark = "";
			    	   if(hasMark){
			    		   mark = " / B";
			    	   }
			    	   
			    	   $("#paymentFilterClientIdSelected").val(selectedId);
			    	   //$("#bClientId").val(selectedId);
			    	   $("#baddress").val(selectedDescription + " / " + address + " / " + companyType + mark);
			    	   lovClientContainer.css({"display": "none"});

			    	   // si esta todo ok entonces doy de alta ...
			    	   BootstrapDialog.closeAll();
			       }
			        
			       return;
			   });
				
				BootstrapDialog.show({
					type:BootstrapDialog.TYPE_DANGER,
					title: 'Clientes',
					autodestroy: false,
					draggable: true,
			        message: function(dialog) {
			        	
			        	lovClientContainer.css({"display":"block"});
			        	
			        	return lovClientContainer;
			        }
			    });
				
			} else {
				Message.showMessages($('#paymentFilterAlertMessages'), $("#paymentFilterMessages"), data.message);
			}
	   },
	   error:function(data){
		   Message.showMessages($('#paymentFilterAlertMessages'), $("#paymentFilterMessages"), data.responseJSON.message);
		   
		   return;
	   }
	});
	
	return;
});
