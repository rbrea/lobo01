CustomerFilter = function(){}

CustomerFilter.init = function(){
	
		$("#customerFilterCollapseButton").on("click", function(){

			var div = $("#customer-filter");
			
			var i = $(this).children("i").eq(0);
			if(div.hasClass("collapse")){
				i.removeClass("fa-chevron-down").addClass("fa-chevron-up");
			} if(div.hasClass("in")){
				i.removeClass("fa-chevron-up").addClass("fa-chevron-down");
				CustomerFilter.resetFilter(false);
			} else {
				i.removeClass("fa-chevron-down").addClass("fa-chevron-up");
			}
			
			return;
		});
		
		$("#btnCustomerFilterSearch").on('click', function(){
			
			var collectorId = $("#customerFilterCollectorId").val();
			var status = $("#customerFilterStatus").val();
			var dateFrom = $("#customerFilterDateFromValue").val();
			var dateTo = $("#customerFilterDateToValue").val();
			var none = $("#cancelationOnDateNone:checked").length > 0;
			var cancelationOnDate = $("#cancelationOnDate:checked").length > 0;
			var cancelationBeforeMore = $("#cancelationBeforeMore:checked").length > 0;
			if(none == true){
				cancelationOnDate = null;
				cancelationBeforeMore = null;
			}
			
			CustomerFilter.searchByFilter(collectorId, status, cancelationOnDate, cancelationBeforeMore, dateFrom, dateTo);
			
			return;
		});
		
		$("#btnCustomerFilterReset").on('click', function(){
			
			CustomerFilter.resetFilter(true);
			
			var table = $('#tCustomerFilterResult').DataTable();
			 
			table
			    .clear()
			    .draw();
			
			return;
		});
		
		$("#customerFilterCollectorZone").on('keypress', function(){
			
			CustomerFilter.resetCollectorFilter(false);
			
			return;
		});
		
		$("#customerFilterCollectorZone").keyup(function(e){
			if(e.keyCode == 13) {
				var value = $(this).val();
				if(value != null && value != ""){
					$("#customerFilterStatus").focus();
					return CustomerFilter.getCollectorByZone(value, $('#customerFilterAlertMessages'), $("#customerFilterMessages"));
				}
			} else {
				$("#customerFilterCollectorDescription").val("");
			}
		    
		    return;
		});
		
		$('#customerFilterCollectorZone').keydown(function(e){
			// 13: enter
			// 9: tab
		    if(e.keyCode == 9){
		    	var value = $(this).val();
				if(value != null && value != ""){
					e.preventDefault();
					$("#customerFilterStatus").focus();
					return CustomerFilter.getCollectorByZone(value, $('#customerFilterAlertMessages'), $("#customerFilterMessages"));
				}
		    }
		    
		    return;
		});
		
		$("#customerFilterStatus").keyup(function(e){
			if(e.keyCode == 13) {
				$("#customerFilterDateFrom").focus();			
			}
		    
		    return;
		});
		
		$('#customerFilterStatus').keydown(function(e){
			// 13: enter
			// 9: tab
		    if(e.keyCode == 9){
		    	e.preventDefault();
				$("#customerFilterDateFrom").focus();			
		    }
		    
		    return;
		});
		
		$("#customerFilterDateFrom").keyup(function(e){
			if(e.keyCode == 13) {
				$("#customerFilterDateTo").focus();			
			}
		    
		    return;
		});
		
		$('#customerFilterDateTo').keydown(function(e){
			// 13: enter
			// 9: tab
		    if(e.keyCode == 9){
		    	e.preventDefault();
				$("#btnCustomerFilterSearch").focus();			
		    }
		    
		    return;
		});
		
		$("#btnCustomerFilterSearchCollector").on("click", function(){
			
			var value = $("#customerFilterCollectorZone").val();
			if(value != null && value != ""){
				return CustomerFilter.getCollectorByZone(value, $('#customerFilterAlertMessages'), $("#customerFilterMessages"));
			}
			
	    	$.ajax({ 
			   type    : "GET",
			   url     : Constants.contextRoot + "/controller/html/collector",
			   dataType: 'json',
			   contentType: "application/json;",
			   success:function(data) {
					
				   var tbody = $("#tLovCollectorResult tbody");
				   
				   tbody.children('tr').remove();
				   
				   Message.hideMessages($('#customerFilterAlertMessages'), $("#customerFilterMessages"));
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
					            
					            $("#customerFilterCollectorId").val(selectedId);
					            $("#customerFilterCollectorZone").val(zone);
					            $("#customerFilterCollectorDescription").val(selectedDescription);
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
						Message.showMessages($('#customerFilterAlertMessages'), $("#customerFilterMessages"), data.message);
					}
			   },
			   error:function(data){
				   Message.showMessages($('#customerFilterAlertMessages'), $("#customerFilterMessages"), data.responseJSON.message);
				   
				   return;
			   }
			});
			
			return;
		});

		$('#customerFilterDateFrom').datetimepicker({
	        locale: 'es',
	        showTodayButton: true,
	        format: 'DD/MM/YYYY'
	    });
		
		$('#customerFilterDateTo').datetimepicker({
	        locale: 'es',
	        showTodayButton: true,
	        format: 'DD/MM/YYYY'
	    });
		
		//$("#customerFilterDateFromValue").val(moment().subtract(3, 'months').format('DD/MM/YYYY'));
		// asigno el dia de hoy
		//$("#customerFilterDateToValue").val(moment().format('DD/MM/YYYY'));
		
		//$("#cfDateFrom").val(moment().subtract(3, 'months').format('DD/MM/YYYY'));
		// asigno el dia de hoy
		//$("#cfDateTo").val(moment().format('DD/MM/YYYY'));
		var table = $("#tCustomerFilterResult").dataTable( {
			"fnInitComplete": function(){
				$('#tCustomerFilterResult tbody tr').each(function(){
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
					"data": "name" 
				},        
	            { 
	            	"className": '',
	            	"orderable": false,
	            	"data": "companyAddress"
	            },
	            { 	
	            	"className": '',
	            	"orderable": false,
	            	"data": "companyCity" 
	            },
	            { 
	            	"className": 'centered',
	            	"orderable": false,
	            	"data": "companyPhone" 
	            },
	            { 
	            	"className": 'centered',
	            	"orderable": true,
	            	"data": "documentNumber" 
	            },
	            { 
	            	"className": 'centered',
	            	"orderable": true,
	            	"data": "companyType" 
	            }
	        ],
	        "order": [[0, 'asc']],
	        "language": {
	            "lengthMenu": "Mostrar _MENU_ registros por p&aacute;gina",
	            "zeroRecords": "No se ha encontrado ningun elemento",
	            "info": "P&aacute;gina _PAGE_ de _PAGES_ <b>(Total: _MAX_ clientes)</b>",
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

CustomerFilter.exportToCsv = function(){

	$("#frmCustomerFilterExportCsv").submit();
	
	return;
}

CustomerFilter.searchByFilter = function(collectorId, status, cancelationOnDate, cancelationBeforeMore, dateFrom, dateTo){
	
	var urlQueryString = "";
	if(collectorId != null && collectorId != ""){
		urlQueryString = "?collectorId=" + collectorId;
		$("#bhCollectorId").val(collectorId);
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
	if(cancelationOnDate != null && cancelationOnDate != ""){
		if(urlQueryString == ""){
			urlQueryString = "?";
		} else {
			urlQueryString = urlQueryString + "&";
		}
		urlQueryString = urlQueryString + "cancelationOnDate=" + cancelationOnDate;
			
		$("#cfCancelationOnDate").val(cancelationOnDate);
	}
	if(cancelationBeforeMore != null && cancelationBeforeMore != ""){
		if(urlQueryString == ""){
			urlQueryString = "?";
		} else {
			urlQueryString = urlQueryString + "&";
		}
		urlQueryString = urlQueryString + "cancelationBeforeMore=" + cancelationBeforeMore;
			
		$("#cfCancelationBeforeMore").val(cancelationBeforeMore);
	}
	if(dateFrom != null && dateFrom != ""){
		if(urlQueryString == ""){
			urlQueryString = "?";
		} else {
			urlQueryString = urlQueryString + "&";
		}
		urlQueryString = urlQueryString + "dateFrom=" + dateFrom;
		$("#bhDateFrom").val(dateFrom);
	}
	if(dateTo != null && dateTo != ""){
		if(urlQueryString == ""){
			urlQueryString = "?";
		} else {
			urlQueryString = urlQueryString + "&";
		}
		urlQueryString = urlQueryString + "dateTo=" + dateTo;
		$("#bhDateTo").val(dateTo);
	}
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/client/filter" + urlQueryString,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
			
		   var tbody = $("#tCustomerFilterResult tbody");
		   
		   tbody.children('tr').remove();
		   
		   Message.hideMessages($('#customerFilterAlertMessages'), $("#customerFilterMessages"));
		   if(data != null && data.status == 0){
			   if(data)
			   
			   var table = $("#tCustomerFilterResult").dataTable( {
				"fnInitComplete": function(){
					$('#tCustomerFilterResult tbody tr').each(function(){
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
		        "data": data.data,
		        "columns": [
					{ 	
						"className": '',
						"orderable": true,
						"data": "name" 
					},        
		            { 
		            	"className": '',
		            	"orderable": false,
		            	"data": "companyAddress"
		            },
		            { 	
		            	"className": '',
		            	"orderable": false,
		            	"data": "companyCity" 
		            },
		            { 
		            	"className": 'centered',
		            	"orderable": false,
		            	"data": "companyPhone" 
		            },
		            { 
		            	"className": 'centered',
		            	"orderable": true,
		            	"data": "documentNumber" 
		            },
		            { 
		            	"className": 'centered',
		            	"orderable": true,
		            	"data": "companyType" 
		            }
		        ],
		        "order": [[0, 'asc']],
		        "language": {
		            "lengthMenu": "Mostrar _MENU_ registros por p&aacute;gina",
		            "zeroRecords": "No se ha encontrado ningun elemento",
		            "info": "P&aacute;gina _PAGE_ de _PAGES_ <b>(Total: _MAX_ clientes)</b>",
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
			   Message.showMessages($('#customerFilterAlertMessages'), $("#customerFilterMessages"), data.message);
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#customerFilterAlertMessages'), $("#customerFilterMessages"), data.responseJSON.message);
		   
		   return;
	   }
	});
	
	return;
}

CustomerFilter.getCollectorByZone = function(zone, alertMessagesContainer, messagesContainer){
	
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
				   
				   $("#customerFilterCollectorId").val(element.id)
				   $("#customerFilterCollectorZone").val(element.zone)
		           $("#customerFilterCollectorDescription").val(element.description);				   
			
		           $("#bClientId").focus();
			   } else {
				   $("#customerFilterCollectorZone").focus();
				   $("#customerFilterCollectorDescription").parent().next().append("Cobrador no encontrado");
				   $("#customerFilterCollectorZone").parent().parent().addClass("has-error");
				   $("#customerFilterCollectorDescription").parent().parent().addClass("has-error");
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

CustomerFilter.hideFilter = function(){

	CustomerFilter.resetFilter(false);
	
	$("#customer-filter").hide("slow");
	
	return;
}

CustomerFilter.showFilter = function(){
	
	$("#customer-filter").show("slow");
	
	return;
}

CustomerFilter.resetFilter = function(enabled){

	CustomerFilter.resetCollectorFilter(enabled);
	CustomerFilter.resetStatus(enabled);
	CustomerFilter.resetDates(enabled);
	CustomerFilter.resetOptions(enabled);
	
	return;
}

CustomerFilter.resetCollectorFilter = function(enabled){
	
	$("#customerFilterCollectorId").val("");
	if(enabled){
		$("#customerFilterCollectorZone").val("");
		$("#cfCollectorId").val("");
	}
	$("#customerFilterCollectorDescription").val("");
	
	$("#customerFilterCollectorDescription").parent().next().html("");
	$("#customerFilterCollectorDescription").val("");
	$("#customerFilterCollectorZone").parent().parent().removeClass("has-error");
	$("#customerFilterCollectorDescription").parent().parent().removeClass("has-error");
	
	return;
}

CustomerFilter.resetStatus = function(enabled){
	
	$('#customerFilterStatus option:contains("Todos")').prop('selected', true);
	if(enabled){
		$("#cfStatus").val("");
	}
	
	return;
}

CustomerFilter.resetDates = function(enabled){

	$("#customerFilterDateFromValue").val("");
	// asigno el dia de hoy
	$("#customerFilterDateToValue").val("");
	
	if(enabled){
		$("#cfDateFrom").val("");
		// asigno el dia de hoy
		$("#cfDateTo").val("");
	}
	
	return;
}

CustomerFilter.resetOptions = function(){
	
	$("#cancelationOnDateNone").prop("checked", true);
	
	return;
}