Collector = function(){}

Collector.init = function(){
	
	return;
}

Collector.initDataTable = function(imgCheckUrl){
	
	var table = $("#tCollectorResult").dataTable( {
		"bDestroy" : true,
        "ajax": Constants.contextRoot + "/controller/html/collector",
        "columns": [
			{
				"className":      'centered',
			 	// The `data` parameter refers to the data for the cell (defined by the
			    // `data` option, which defaults to the column being worked with, in
			    // this case `data: 0`.
			    "render": function ( data, type, row ) {
			        //return data +' ('+ row[3]+')';
			        return row.id + "<img id=\"imgCheck_" + row.id + "\" class=\"hide\" width=\"60%\" src=\"" + imgCheckUrl + "\">";
			    }
			},
            /*{
                "className":      'details-control',
                "orderable":      false,
                "data":           null,
                "defaultContent": ''
            },*/
			{ 
            	"className": 'centered',
            	"data": "zone" 
            },
            { 
            	"className": 'centered',
            	"data": "description" 
            },
            {
            	"className":      'centered',
	         	// The `data` parameter refers to the data for the cell (defined by the
                // `data` option, which defaults to the column being worked with, in
                // this case `data: 0`.
                "orderable": false,
                "render": function ( data, type, row ) {
                    //return data +' ('+ row[3]+')';
                    return "<a href=\"javascript:Collector.showModal('" + row.id + "');\" class=\"btn btn-xs btn-warning\"><i class=\"glyphicon glyphicon-pencil\"></i></a>" 
                        + "&nbsp;<a href=\"javascript:Collector.remove('" + row.id + "');\" class=\"btn btn-xs btn-danger\"><i class=\"glyphicon glyphicon-trash\"></i></a>";
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

Collector.initControls = function(){
	$("#collectorZone").keyup(function(e){
		if(e.keyCode == 13) {
			$("#collectorDescription").focus();			
		}
	    
	    return;
	});

	$('#collectorZone').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#collectorDescription").focus();			
	    }
	    
	    return;
	});
	
	$("#collectorDescription").keyup(function(e){
		if(e.keyCode == 13) {
			$("#btnCollectorAccept").focus();			
		}
	    
	    return;
	});

	return;
}

Collector.showModal = function(id){
	
	BootstrapDialog.show({
		onshown: function(){
			$("#collectorZone").focus();
			
			return;
		},
		onhidden:function(){
			Collector.resetModal();
			
			return;
		},
		draggable: true,
		type:BootstrapDialog.TYPE_DANGER,
		title: 'Cobradores',
		autodestroy: false,
        message: function(dialog) {
        	
        	if(id != null && id != ""){
        		$("#collectorId").val(id);
        		
        		var zone = $("#tCollectorResult").find('tr', 'tbody').find('td:eq(0)').children("img[id='imgCheck_" + id + "']")
        				.parent().parent().find('td:eq(1)').html().trim();
        		var description = $("#tCollectorResult").find('tr', 'tbody').find('td:eq(0)').children("img[id='imgCheck_" + id + "']")
					.parent().parent().find('td:eq(2)').html().trim();
        		
        		$("#collectorZone").val(zone);
        		$("#collectorDescription").val(description);
        	}
        	
        	$("#modal-collector-container").css({"display":"block"});
        	
        	return $("#modal-collector-container");
        },
        buttons: [{
        	id: 'btnCancel',
        	label: 'Cancelar',
        	icon: 'glyphicon glyphicon-remove-sign',
        	action: function(dialog){
        		var btn = this;
        		Message.hideMessages($('#modalCollectorAlertMessages'), $("#modalCollectorMessages"));
        		dialog.close();
        		
        		return;
        	}
        },
        {
        	id: 'btnCollectorAccept',
        	label: 'Guardar',
        	icon: 'glyphicon glyphicon-ok-sign',
        	cssClass: 'btn-success',
        	action: function(dialog){
        		var btn = this;
        		var c = 0;
				
				$("#frmCollector").on('invalid.bs.validator', 
					function(e){
					    c++;
						
						return;
				});
				
				$("#frmCollector").validator('validate');
				
				if(c == 0){
					dialog.enableButtons(false);
					dialog.setClosable(false);
	        		btn.spin();
	        		
					// si esta todo ok entonces doy de alta ...
					Collector.add(dialog, btn);
				}
        		
        		return;
        	}
        }]
    });
	
	return;
}

Collector.remove = function(id){
	BootstrapDialog.confirm({
		title: "Confirmación",
		message: "Esta seguro de eliminar el registro seleccionado?",
		type: BootstrapDialog.TYPE_DANGER,
		draggable: true,
		btnCancelLabel: '<i class="glyphicon glyphicon-remove-sign"></i>&nbsp;NO', // <-- Default value is 'Cancel',
        btnOKLabel: '<i class="glyphicon glyphicon-ok-sign"></i>&nbsp;SI', // <-- Default value is 'OK',
        btnOKClass: 'btn-success',
		callback: function(result){
			if(result) {
				$.ajax({ 
				   type    : "DELETE",
				   url     : Constants.contextRoot + "/controller/html/collector/" + id,
				   dataType: 'json',
				   contentType: "application/json;",
				   success:function(data) {
					   Message.hideMessages($('#collectorAlertMessages'), $("#collectorMessages"));
					   if(data != null && data.status == 0){
						   
						   var table = $('#tCollectorResult').dataTable();

						   table.fnDeleteRow($("#imgCheck_" + id).parent().parent(), null, true);
						   
						   return;
					   }else{
						   Message.showMessages($('#collectorAlertMessages'), $("#collectorMessages"), data.message);
					   }
				   },
				   error:function(data){
					   Message.showMessages($('#collectorAlertMessages'), $("#collectorMessages"), data.responseJSON.message);
					   
					   return;
				   }
				});
			}
			
			return;
		}
	});
	
	return;
}

Collector.resetModal = function(){
	$("#collectorId").val('');
	$("#collectorZone").val('');
	$("#collectorDescription").val('');
	$("#frmCollector").validator('destroy');
	
	return;
}

Collector.add = function(dialog, btn){
	
	var id = $("#collectorId").val();
	var zone = $("#collectorZone").val();
	var description = $("#collectorDescription").val();
	
	var obj = new Object();
	obj.id = id;
	obj.zone = zone;
	obj.description = description;
	
	$.ajax({ 
	   type    : "POST",
	   url     : Constants.contextRoot + "/controller/html/collector",
	   dataType: 'json',
	   data: JSON.stringify(obj),
	   contentType: "application/json;",
	   success:function(data) {
		   Message.hideMessages($('#modalCollectorAlertMessages'), $("#modalCollectorMessages"));
		   if(data != null && data.status == 0){
			   var table = $('#tCollectorResult').dataTable();

			   table.api().ajax.url(Constants.contextRoot + "/controller/html/collector").load();
			   
			   dialog.enableButtons(true);
			   dialog.setClosable(true);
       		   btn.stopSpin();
			   dialog.close();
			   
			   return;
		   }else{
			   Message.showMessages($('#modalCollectorAlertMessages'), $("#modalCollectorMessages"), data.message);
			   
			   dialog.enableButtons(true);
			   dialog.setClosable(true);
       		   btn.stopSpin();
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#modalCollectorAlertMessages'), $("#modalCollectorMessages"), data.responseJSON.message);
		   
		   dialog.enableButtons(true);
		   dialog.setClosable(true);
   		   btn.stopSpin();
		   
		   return;
	   }
	});
	
	
	
	return;
}

Collector.getByZone = function(zone, elementIdContainer, alertMessagesContainer, messagesContainer){
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/collector?zone=" + zone,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {

		   $("#bCollectorDescription").parent().next().html("");
		   $("#bCollectorDescription").val("");
		   $("#bCollectorDescription").parent().parent().removeClass("has-error");
		   
		   Message.hideMessages(alertMessagesContainer, messagesContainer);
		   
		   if(data != null && data.status == 0){
			   
			   var list = data.data;
			   
			   if(list.length > 0){
				   var element = list[0];
				   
				   elementIdContainer.val(element.id);
				   $("#zone").val(element.zone)
				   $("#bCollectorId").val(element.zone)
		           $("#bCollectorDescription").val(element.description);				   
			
		           $("#bClientId").focus();
			   } else {
				   $("#bCollectorId").focus();
				   $("#bCollectorDescription").parent().next().append("Cobrador no encontrado");
				   $("#bCollectorDescription").parent().parent().addClass("has-error");
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


Collector.initCollectorDetail = function(fromDate, toDate, imgCheckUrl){
	
	if(fromDate == null || toDate == null || fromDate == "" || toDate == ""){
		Message.showMessages($("#collectorDetailAlertMessages"), $("#collectorDetailMessages"), "Fecha desde y Fecha Hasta son requeridos");
		
		return;
	}
	
	Message.hideMessages($("#collectorDetailAlertMessages"), $("#collectorDetailMessages"));
	
	var table = $("#tCollectorDetailResult").dataTable( {
		
		"fnFooterCallback": function ( nRow, aaData, iStart, iEnd, aiDisplay ) {
			
			var totalAmountAcum = 0;
			var remainingAmountAcum = 0;
			var collectedAmountAcum = 0;
			
			for ( var i=0 ; i<aaData.length ; i++ ){
				totalAmountAcum += parseFloat(aaData[i].amountToCollect);
				collectedAmountAcum += parseFloat(aaData[i].amountCollected);
				remainingAmountAcum += parseFloat(aaData[i].remainingAmount);
			}
			
			$("#totalAmount").html("$ " + totalAmountAcum.toFixed(2));
            $("#collectedAmount").html("$ " + collectedAmountAcum.toFixed(2));
            $("#remainingAmount").html("$ " + remainingAmountAcum.toFixed(2));
            
            return;
        },
        "createdRow": function ( row, data, index ) {
        	
        	$(row).data('rowid', data.id);
    		
    		return;
        },
		"bDestroy" : true,
        "ajax": Constants.contextRoot + "/controller/html/collector/detail?from=" + fromDate + "&to=" + toDate,
        "columns": [
			{
				"className":      'centered',
			 	// The `data` parameter refers to the data for the cell (defined by the
			    // `data` option, which defaults to the column being worked with, in
			    // this case `data: 0`.
			    "render": function ( data, type, row ) {
			        //return data +' ('+ row[3]+')';
			        return row.id + "<img id=\"imgCheck_" + row.id + "\" class=\"hide\" width=\"60%\" src=\"" + imgCheckUrl + "\">";
			    }
			},
			{ 
            	"className": 'centered',
            	"data": "zone" 
            },
            { 
            	"className": 'centered',
            	"data": "name" 
            },
            { 
            	"className": 'centered',
            	"data": "amountToCollect" 
            },
            { 
            	"className": 'centered',
            	"data": "amountCollected" 
            },
            { 
            	"className": 'centered',
            	"data": "remainingAmount" 
            },
            {
            	"className": 'centered',
	         	// The `data` parameter refers to the data for the cell (defined by the
                // `data` option, which defaults to the column being worked with, in
                // this case `data: 0`.
                "orderable": false,
                "render": function ( data, type, row ) {
                	
                	var btnDetail = "<span id='collectorPaymentDetail_" + row.id 
		        			+ "'><a href='javascript:void(0)' onclick='javascript:Collector.showPaymentDetail(" + row.id 
		        			+ ")'><i class='glyphicon glyphicon-zoom-in'></i></a></span>";
                	
                	var tbody = $("<tbody></tbody>");
                	
                	var payments = row.payments;
 				   
 				   	if(payments != null && payments.length > 0){
 					   
 					   var sum = 0;
 					   
 					   var idx = 0;
 					   
 					   $.each(payments, function(){
 						   
 						   var tr = $("<tr></tr>");
 						   var td0 = $("<td class='centered'></td>").append(""+ (++idx));
 						   var td1 = $("<td class='centered'></td>").append(this.date);
 						   var td2 = $("<td class='centered'></td>").append(this.creditNumber);
 						   var td3 = $("<td class='centered'></td>").append(this.amount);
 						   
 						   sum += parseFloat(this.amount);
 						   
 						   tr.append(td0).append(td1).append(td2).append(td3);
 						   tbody.append(tr);
 						   
 						   return;
 					   });
 					   
 					   var footerRow = $("<tr></tr>");
 					   var tdFooter0 = $("<td class=''></td>");
 					   var tdFooter1 = $("<td class=''></td>");
 					   var tdFooter2 = $("<td class=''><span style='text-align:right;'><b>Total: </b></span></td>");
 					   var tdFooter3 = $("<td class='centered'></td>").append(sum.toFixed(2));
						   
 					   footerRow.append(tdFooter0).append(tdFooter1).append(tdFooter2).append(tdFooter3);
 					   tbody.append(footerRow);
 				   	}
                	
                	var divContainer = '<div id="detailCollector_' + row.id + '" class="row hide"><div class="col-md-12"><div class="panel panel-default">' +
    	        	'<div class="panel-heading">Detalle</div>' +
    	        		'<div class="panel-body">' +
    	        		'<table class="table table-striped">' +
    	        		'<tr>' +
    	        		'<th class="centered">Indice</th>' +
    	        		'<th class="centered">Fecha</th>' +
    	        		'<th class="centered">Nro Cr&eacute;dito</th>' +
    	        		'<th class="centered">Monto</th>' +
    	        		'</tr>' +
    	        		tbody.html() +
    	        		'</table>' +
    	        		'</div>' + 
    	        		'</div></div></div>';
            
                	return (btnDetail + divContainer); 
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

Collector.exportCollectorDetailToPdf = function(){
	
	$("#frmCollectorDetailExportPdf").submit();	
	
	return;
}

Collector.showPaymentDetail = function(rowId){
	
	BootstrapDialog.show({
		onshown: function(){
			
			return;
		},
		onhidden:function(){
			
			return;
		},
		draggable: true,
		type: BootstrapDialog.TYPE_DANGER,
		title: "Detalle de Cobros",
		autodestroy: true,
		closable: true,
		//cssClass: 'dialog-large dialog-flow-detail',
        message: function(dialog) {
        	
        	return $("#detailCollector_" + rowId).html().replace("hide", '');
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
