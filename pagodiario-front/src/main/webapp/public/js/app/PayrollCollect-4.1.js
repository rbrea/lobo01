PayrollCollect = function(){}

PayrollCollect.init = function(){
	
	$("#btnAcceptPayrollCollect").on('click', function(){
		$(this).addClass("disabled");
		PayrollCollect.processPeriod($(this));
		
		return;
	});
	
	// asigno el dia de hoy
	$("#payrollCollectDateValue").val(moment().format('DD/MM/YYYY'));
	
	$('.not-writable').keypress(function(e) {
        e.preventDefault();
        
        return;
	}).css({
		"cursor": "not-allowed"
	});
	
	$('#payrollCollectDate').datetimepicker({
        locale: 'es',
        showTodayButton: true,
        format: 'DD/MM/YYYY'
    });
	
	return;
}

PayrollCollect.initDataTable = function(imgCheckUrl){
	
	var table = $("#tPayrollCollectResult").dataTable( {
		"bDestroy" : true,
		"bRedraw" : true,
        "ajax": Constants.contextRoot + "/controller/html/payrollcollect",
        "columns": [
			{
				"className": 'centered',
				"data": "id"
			},
            { 	
            	"className": 'centered',
            	"data": "payrollDate" 
            },
            { 
            	"className": 'centered',
            	"data": "totalCards" 
            },
            { 
            	"className": 'centered',
            	"data": "totalCardsReal" 
            },
            { 	
            	"className": 'centered',
            	"data": "totalAmount" 
            },
            { 
            	"className": 'centered',
            	"data": "totalPayment" 
            },
            { 
            	"className": 'centered',
            	"data": "totalAmountToPay" 
            },
            { 
            	"className": 'centered',
            	"render": function ( data, type, row ) {
			        var value = "Iniciado";
			        if(row.status == 'FINISHED'){
			        	value = "Finalizado";
			        } else if(row.status == 'COMMITED'){
			        	value = "Confirmado";
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
                	
                	var email = "";
                	if(row.email != null){
                		email = row.email;
                	}
                	var parentId = "";
                	if(row.parentId != null){
                		parentId = row.parentId;
                	}
                	var parentDescription = "";
                	if(row.parentDescription != null){
                		parentDescription = row.parentDescription;
                	}
                	
                	var commitButtonHtml = "";
                	var undoButtonHtml = "";
                	
                	if(row.status == 'FINISHED'){
                		if(Permission.isAdmin){
                			commitButtonHtml = "&nbsp;<a id=\"btnCommit_" + row.id + "\" href=\"javascript:PayrollCollect.commit('" + row.id + "');\" class=\"btn btn-xs btn-primary ROLE_ADMIN\" data-toggle=\"tooltip\" data-placement=\"left\" title=\"Confirmar Liquidaci&oacute;n\"><i class=\"glyphicon glyphicon-ok\"></i></a>";
                			undoButtonHtml = "&nbsp;<a id=\"btnUndo_" + row.id + "\" href=\"javascript:PayrollCollect.undo('" + row.id + "');\" class=\"btn btn-xs btn-danger ROLE_ADMIN\" data-toggle=\"tooltip\" data-placement=\"left\" title=\"Deshacer Liquidaci&oacute;n\"><i class=\"glyphicon glyphicon-remove\"></i></a>";
                		}
                	}
                	
                    return "<a href=\"javascript:PayrollCollect.showDetail('" + row.id + "');\" class=\"btn btn-xs btn-success\" data-toggle=\"tooltip\" data-placement=\"left\" title=\"Detalle de Liquidaci&oacute;n de Cobrador\"><i class=\"glyphicon glyphicon-zoom-in\"></i></a>"
                    	+ commitButtonHtml
                    	+ undoButtonHtml;
                }
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

PayrollCollect.processPeriod = function(btn){

	var obj = new Object();
	obj.payrollDate = $("#payrollCollectDateValue").val();
	
	$.ajax({ 
	   type    : "POST",
	   url     : Constants.contextRoot + "/controller/html/payrollcollect/processperiod",
	   dataType: 'json',
	   data: JSON.stringify(obj),
	   contentType: "application/json;",
	   success:function(data) {
		   btn.removeClass("disabled");
		   Message.hideMessages($('#payrollCollectAlertMessages'), $("#payrollCollectMessages"));
		   if(data != null && data.status == 0){
			   var table = $('#tPayrollCollectResult').dataTable();
			   	
			   table.api().ajax.url(Constants.contextRoot + "/controller/html/payrollcollect").load();

			   return;
		   }else{
			   Message.showMessages($('#payrollCollectAlertMessages'), $("#payrollCollectMessages"), data.message);
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#payrollCollectAlertMessages'), $("#payrollCollectMessages"), data.responseJSON.message);
		   btn.removeClass("disabled");
		   
		   return;
	   }
	});
	
	return;
}

PayrollCollect.showDetail = function(id){
	
	$("#frmPayrollCollect")[0].action = Constants.contextRoot + "/controller/html/payrollcollectitem/index";
	$("#payrollId").val(id);
	$("#frmPayrollCollect").submit();
	
	return;
}

PayrollCollect.commit = function(id){
	
	BootstrapDialog.confirm({
		title: "Confirmaci&oacute;n",
		message: "Esta seguro de confirmar la liquidaci&oacute;n con id: " + id + "? NO VA A HABER VUELTA ATR&Aacute;S!",
		type: BootstrapDialog.TYPE_DANGER,
		draggable: true,
		btnCancelLabel: '<i class="glyphicon glyphicon-remove-sign"></i>&nbsp;NO', // <-- Default value is 'Cancel',
        btnOKLabel: '<i class="glyphicon glyphicon-ok-sign"></i>&nbsp;SI', // <-- Default value is 'OK',
        btnOKClass: 'btn-success',
		callback: function(result){
			if(result) {
				$.ajax({ 
				   type    : "POST",
				   url     : Constants.contextRoot + "/controller/html/payrollcollect/commitliq/"  + id,
				   dataType: 'json',
				   contentType: "application/json;",
				   success:function(data) {
					   Message.hideMessages($('#payrollCollectAlertMessages'), $("#payrollCollectMessages"));
					   if(data != null && data.status == 0){

						   var list = data.data;
						   if(list != null && list.length > 0){
							   var payroll = list[0];
							   if(payroll.status == 'COMMITED'){
								   $("#btnCommit_" + id).remove();
								   $("#btnUndo_" + id).remove();
								   $("#btnCommit_" + id).parent().parent().children('td').eq(6).html('Confirmado');
							   }
						   }
						   
						   return;
					   }else{
						   Message.showMessages($('#payrollCollectAlertMessages'), $("#payrollCollectMessages"), data.message);
					   }
				   },
				   error:function(data){
					   Message.showMessages($('#payrollCollectAlertMessages'), $("#payrollCollectMessages"), data.responseJSON.message);
					   
					   return;
				   }
				});
			}
			
			return;
		}
	});

	return;
}

PayrollCollect.undo = function(id){
	
	BootstrapDialog.confirm({
		title: "Confirmaci&oacute;n",
		message: "Esta seguro de deshacer la liquidaci&oacute;n con id: " + id + "?",
		type: BootstrapDialog.TYPE_DANGER,
		draggable: true,
		btnCancelLabel: '<i class="glyphicon glyphicon-remove-sign"></i>&nbsp;NO', // <-- Default value is 'Cancel',
        btnOKLabel: '<i class="glyphicon glyphicon-ok-sign"></i>&nbsp;SI', // <-- Default value is 'OK',
        btnOKClass: 'btn-success',
		callback: function(result){
			if(result) {
				$.ajax({ 
				   type    : "POST",
				   url     : Constants.contextRoot + "/controller/html/payrollcollect/undoliq/"  + id,
				   dataType: 'json',
				   contentType: "application/json;",
				   success:function(data) {
					   Message.hideMessages($('#payrollCollectAlertMessages'), $("#payrollCollectMessages"));
					   if(data != null && data.status == 0){

						   var table = $('#tPayrollCollectResult').dataTable();
						   	
						   table.api().ajax.url(Constants.contextRoot + "/controller/html/payrollcollect").load();
						   
						   return;
					   }else{
						   Message.showMessages($('#payrollCollectAlertMessages'), $("#payrollCollectMessages"), data.message);
					   }
				   },
				   error:function(data){
					   Message.showMessages($('#payrollCollectAlertMessages'), $("#payrollCollectMessages"), data.responseJSON.message);
					   
					   return;
				   }
				});
			}
			
			return;
		}
	});
	
	return;
}

PayrollCollect.initItemDataTable = function(imgCheckUrl, id){
	
	var table = $("#tPayrollCollectItemResult").dataTable( {
		"bDestroy" : true,
		"bRedraw" : true,
		"searching": false,
		"ordering": false,
		"paging":   false,
        "ajax": Constants.contextRoot + "/controller/html/payrollcollectitem?id=" + id,
        "columns": [
			{
				"className": 'centered',
				"data": "id"
			},
            { 
            	"className": 'centered',
            	"data": "collectorZone" 
            },
            { 	
            	"className": 'centered',
            	"data": "collectorDescription" 
            },
            { 
            	"className": 'centered',
            	"data": "cardsCount" 
            },
            { 
            	"className": 'centered',
            	"data": "cardsCountReal" 
            },
            { 
            	"className": 'centered',
            	"data": "totalAmount" 
            },
            { 
            	"className": 'centered',
            	"data": "totalPayment" 
            },
            { 
            	"className": 'centered',
            	"data": "amountToPay"
            },
            {
            	"className": 'centered',
            	"data": "totalToCollect"
         	},
            {
            	"className": 'centered',
            	"render": function (data, type, row) {
			        
			        return "<span id='collectDetail_" + row.id 
			        	+ "'><a href='javascript:void(0)' onclick='javascript:PayrollCollect.showPayrollCollectDetail(" + row.id 
			        		+ ")'><i class='glyphicon glyphicon-zoom-in'></i></a></span>";
			    }
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
	
	return;
}

PayrollCollect.exportToPdf = function(){

	$("#frmPayrollItemCollectExportPdf").submit();
	
	return;
}

PayrollCollect.showPayrollCollectDetail = function(payrollCollectItemId){
	
	BootstrapDialog.show({
		onshown: function(){
			
			return;
		},
		onhidden:function(){
			//$("#txtInputContainer").html("");
			//$("#txtOutputContainer").html("");
			
			return;
		},
		draggable: true,
		type: BootstrapDialog.TYPE_DANGER,
		title: "Hoja de Ruta",
		autodestroy: true,
		closable: true,
		//cssClass: 'dialog-large dialog-flow-detail',
        message: function(dialog) {

        	var tbody = $("<tbody></tbody>");
        	
        	$.ajax({ 
    		   type    : "GET",
    		   url     : Constants.contextRoot + "/controller/html/payrollcollectitem/detail/payrollItemCollect?id=" + payrollCollectItemId,
    		   dataType: 'json',
    		   contentType: "application/json;",
    		   async : false,
    		   success:function(data) {
    				
    			   Message.hideMessages($('#payrollCollectItemAlertMessages'), $("#payrollCollectItemMessages"));
    				
    			   if(data != null){
    	
    				   var payrollItemCollectList = data.data;
    				   
    				   if(payrollItemCollectList.length > 0){
    					   var detail = payrollItemCollectList[0];
    					   
    					   var items = detail.items;
    					   
    					   var sum = 0;
    					   
    					   var idx = 0;
    					   
    					   $.each(items, function(){
    						   
    						   var tr = $("<tr></tr>");
    						   var td0 = $("<td class='centered'></td>").append(""+ (++idx));
    						   var td1 = $("<td class='centered'></td>").append(this.creditNumber);
    						   var td2 = $("<td class='centered'></td>").append(this.amount);
    						   
    						   sum += parseFloat(this.amount);
    						   
    						   tr.append(td0).append(td1).append(td2);
    						   tbody.append(tr);
    						   
    						   return;
    					   });
    					   
    					   var footerRow = $("<tr></tr>");
    					   var tdFooter0 = $("<td class=''></td>");
    					   var tdFooter1 = $("<td class=''><span style='text-align:right;'><b>Total: </b></span></td>");
						   var tdFooter2 = $("<td class='centered'></td>").append(sum.toFixed(2));
						   
						   footerRow.append(tdFooter0).append(tdFooter1).append(tdFooter2);
						   tbody.append(footerRow);
    				   }
    	
    			   } else {
    				   Message.showMessages($('#payrollCollectItemAlertMessages'), $("#payrollCollectItemMessages"), data.message);
    			   }
    		   },
    		   error:function(data){
    			   Message.showMessages($('#payrollCollectItemAlertMessages'), $("#payrollCollectItemMessages"), data.responseJSON.message);
			   
    			   return;
    		   }
			});
        
	        var divContainer = '<div class="row"><div class="col-md-12"><div class="panel panel-default">' +
	        	'<div class="panel-heading">Detalle</div>' +
	        		'<div class="panel-body">' +
	        		'<table class="table table-striped">' +
	        		'<tr>' +
	        		'<th class="centered">Indice</th>' +
	        		'<th class="centered">Nro Cr&eacute;dito</th>' +
	        		'<th class="centered">Monto</th>' +
	        		'</tr>' +
	        		tbody.html() +
	        		'</table>' +
	        		'</div>' + 
	        		'</div></div></div>';
        
        	return divContainer;
        },
        buttons: [
            {
	        	id: 'btnPayrollCollectDetailClose',
	        	label: 'Cerrar',
	        	icon: 'glyphicon glyphicon-ok-sign',
	        	cssClass: 'btn-primary',
	        	action: function(dialog){
	        		var btn = this;
	        		
	        		dialog.close();
					
	        		return;
	        	}
            }
        ]
    });
	
	return;
}

PayrollCollect.showPayrollCollectDetailNoPaid = function(payrollCollectItemId){
	
	BootstrapDialog.show({
		onshown: function(){
			
			return;
		},
		onhidden:function(){
			
			return;
		},
		draggable: true,
		type: BootstrapDialog.TYPE_DANGER,
		title: "Detalle de Facturas no pagadas",
		autodestroy: true,
		closable: true,
		//cssClass: 'dialog-large dialog-flow-detail',
        message: function(dialog) {

        	var tbody = $("<tbody></tbody>");
        	
        	$.ajax({ 
    		   type    : "GET",
    		   url     : Constants.contextRoot + "/controller/html/payrollcollectitem/detail/payrollItemCollect?id=" + payrollCollectItemId,
    		   dataType: 'json',
    		   contentType: "application/json;",
    		   async : false,
    		   success:function(data) {
    				
    			   Message.hideMessages($('#payrollCollectItemAlertMessages'), $("#payrollCollectItemMessages"));
    				
    			   if(data != null){
    	
    				   var payrollItemCollectList = data.data;
    				   
    				   if(payrollItemCollectList.length > 0){
    					   var detail = payrollItemCollectList[0];
    					   
    					   var items = detail.items;
    					   
    					   var sum = 0;
    					   
    					   var idx = 0;
    					   
    					   $.each(items, function(){
    						   
    						   var tr = $("<tr></tr>");
    						   var td0 = $("<td class='centered'></td>").append(""+ (++idx));
    						   var td1 = $("<td class='centered'></td>").append(this.creditNumber);
    						   var td2 = $("<td class='centered'></td>").append(this.amount);
    						   
    						   sum += parseFloat(this.amount);
    						   
    						   tr.append(td0).append(td1).append(td2);
    						   tbody.append(tr);
    						   
    						   return;
    					   });
    					   
    					   var footerRow = $("<tr></tr>");
    					   var tdFooter0 = $("<td class=''></td>");
    					   var tdFooter1 = $("<td class=''><span style='text-align:right;'><b>Total: </b></span></td>");
						   var tdFooter2 = $("<td class='centered'></td>").append(sum.toFixed(2));
						   
						   footerRow.append(tdFooter0).append(tdFooter1).append(tdFooter2);
						   tbody.append(footerRow);
    				   }
    	
    			   } else {
    				   Message.showMessages($('#payrollCollectItemAlertMessages'), $("#payrollCollectItemMessages"), data.message);
    			   }
    		   },
    		   error:function(data){
    			   Message.showMessages($('#payrollCollectItemAlertMessages'), $("#payrollCollectItemMessages"), data.responseJSON.message);
			   
    			   return;
    		   }
			});
        
	        var divContainer = '<div class="row"><div class="col-md-12"><div class="panel panel-default">' +
	        	'<div class="panel-heading">Detalle</div>' +
	        		'<div class="panel-body">' +
	        		'<table class="table table-striped">' +
	        		'<tr>' +
	        		'<th class="centered">Indice</th>' +
	        		'<th class="centered">Nro Cr&eacute;dito</th>' +
	        		'<th class="centered">Monto</th>' +
	        		'</tr>' +
	        		tbody.html() +
	        		'</table>' +
	        		'</div>' + 
	        		'</div></div></div>';
        
        	return divContainer;
        },
        buttons: [
            {
	        	id: 'btnPayrollCollectDetailClose',
	        	label: 'Cerrar',
	        	icon: 'glyphicon glyphicon-ok-sign',
	        	cssClass: 'btn-primary',
	        	action: function(dialog){
	        		var btn = this;
	        		
	        		dialog.close();
					
	        		return;
	        	}
            }
        ]
    });
	
	return;
}
