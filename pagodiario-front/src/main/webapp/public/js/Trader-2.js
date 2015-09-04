Trader = function(){}

Trader.initDataTable = function(imgCheckUrl){
	
	var table = $("#tTraderResult").dataTable( {
		"bDestroy" : true,
		"bRedraw" : true,
        "ajax": Constants.contextRoot + "/controller/html/trader",
        "createdRow": function ( row, data, index ) {
    		
    		$(row).data('email', data.email).data('traderParentId', data.parentId)
    			.data('traderParentDescription', data.parentDescription);
    		
    		return;
        },
        "columns": [
			{
				"className":      'centered',
			 	// The `data` parameter refers to the data for the cell (defined by the
			    // `data` option, which defaults to the column being worked with, in
			    // this case `data: 0`.
			    "orderable": true,
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
            	"data": "name" 
            },
            { 
            	"className": 'centered',
            	"data": "documentNumber" 
            },
            { 	
            	"className": 'centered',
            	"data": "phone" 
            },
            { 
            	"className": 'centered',
            	"data": "address" 
            },
            { 
            	"className": 'centered',
            	"data": "city" 
            },
            { 
            	"className": 'centered',
            	"render": function ( data, type, row ) {
			        var value = "NO";
			        if(row.supervisor){
			        	value = "SI";
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
                	var clazz = 'disabled';
                	if(row.supervisor){
                		clazz = '';
                	}
                	
                    return "<a href=\"javascript:Trader.showTraders('" + row.id + "');\" class=\"btn btn-xs btn-info " + clazz + "\"><i class=\"glyphicon glyphicon-th-list\"></i></a>";
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
                	
                    //return data +' ('+ row[3]+')';
                    return "<a href=\"javascript:Trader.showModal('" + row.id + "', '" + email + "', '" + parentId + "', '" + parentDescription + "');\" class=\"btn btn-xs btn-warning\"><i class=\"glyphicon glyphicon-pencil\"></i></a>"
                    	/*+ "&nbsp;<a href=\"javascript:Trader.addTrader('" + row.id + "');\" class=\"btn btn-xs btn-success\"><i class=\"glyphicon glyphicon-th-list\"></i></a>"*/
                        + "&nbsp;<a href=\"javascript:Trader.remove('" + row.id + "');\" class=\"btn btn-xs btn-danger\"><i class=\"glyphicon glyphicon-trash\"></i></a>";
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

Trader.add = function(dialog, btn){
	
	var id = $("#traderId").val();
	var name = $("#name").val();
	var documentNumber = $("#documentNumber").val();
	var email = $("#email").val();
	var address = $("#address").val();
	var phone = $("#phone").val();
	var address = $("#address").val();
	var city = $("#city").val();
	
	var supervisor = false;
	if($("#supervisor:checked").length > 0){
		supervisor = true;
	}
	var traderParentId = $("#traderParentId").val();
	
	var obj = new Object();
	obj.id = id;
	obj.name = name;
	obj.documentNumber = documentNumber;
	obj.email = email;
	obj.address = address;
	obj.phone = phone;
	obj.city = city;
	obj.supervisor = supervisor;
	obj.parentId = traderParentId;
	
	$.ajax({ 
	   type    : "POST",
	   url     : Constants.contextRoot + "/controller/html/trader",
	   dataType: 'json',
	   data: JSON.stringify(obj),
	   contentType: "application/json;",
	   success:function(data) {
		   Message.hideMessages($('#modalTraderAlertMessages'), $("#modalTraderMessages"));
		   if(data != null && data.status == 0){

			   var table = $('#tTraderResult').dataTable();
			   	
			   table.api().ajax.url(Constants.contextRoot + "/controller/html/trader").load();
			   
			   dialog.enableButtons(true);
			   dialog.setClosable(true);
       		   btn.stopSpin();
			   dialog.close();
			   	
			   return;
		   }else{
			   Message.showMessages($('#modalTraderAlertMessages'), $("#modalTraderMessages"), data.message);
			   
			   dialog.enableButtons(true);
			   dialog.setClosable(true);
       		   btn.stopSpin();
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#modalTraderAlertMessages'), $("#modalTraderMessages"), data.responseJSON.message);
		   
		   dialog.enableButtons(true);
		   dialog.setClosable(true);
   		   btn.stopSpin();
		   
		   return;
	   }
	});
	
	
	
	return;
}

Trader.showModal = function(id, email, parentId, parentDescription){
	
	var dialog = new BootstrapDialog({
		onshown: function(){
			$("#name").focus();
			
			return;
		},
		onhidden:function(){
			Trader.resetModal();
			
			return;
		},
		draggable: true,
		type: BootstrapDialog.TYPE_DANGER,
		title: 'Vendedor/Supervisor',
		autodestroy: false,
		cssClass: 'dialog-trader',
        message: function(dialog) {
        	
        	if(id != null && id != ""){
        		$("#traderId").val(id);
        		
        		var name = $("#tTraderResult").find('tr', 'tbody').find('td:eq(0)').children("img[id='imgCheck_" + id + "']")
        				.parent().parent().find('td:eq(1)').html().trim();
        		var documentNumber = $("#tTraderResult").find('tr', 'tbody').find('td:eq(0)').children("img[id='imgCheck_" + id + "']")
					.parent().parent().find('td:eq(2)').html().trim();
        		var phone = $("#tTraderResult").find('tr', 'tbody').find('td:eq(0)').children("img[id='imgCheck_" + id + "']")
					.parent().parent().find('td:eq(3)').html().trim();
        		
        		var address = $("#tTraderResult").find('tr', 'tbody').find('td:eq(0)').children("img[id='imgCheck_" + id + "']")
					.parent().parent().find('td:eq(4)').html().trim();
        		var city = $("#tTraderResult").find('tr', 'tbody').find('td:eq(0)').children("img[id='imgCheck_" + id + "']")
					.parent().parent().find('td:eq(5)').html().trim();
        		var isSupervisor = $("#tTraderResult").find('tr', 'tbody').find('td:eq(0)').children("img[id='imgCheck_" + id + "']")
					.parent().parent().find('td:eq(6)').html().trim();
        	/*	
        		var email = $("#tClientResult").find('tr', 'tbody').find('td:eq(0)')
    				.children("img[id='imgCheck_" + id + "']").parent().parent().data('email');
        		var traderParentId = $("#tClientResult").find('tr', 'tbody').find('td:eq(0)')
					.children("img[id='imgCheck_" + id + "']").parent().parent().data('traderParentId');
        		var traderParentDescription = $("#tClientResult").find('tr', 'tbody').find('td:eq(0)')
					.children("img[id='imgCheck_" + id + "']").parent().parent().data('traderParentDescription');
        		*/
 			   	$("#name").val(name).attr("readonly", "readonly");
 			   	$("#documentNumber").val(documentNumber).attr("readonly", "readonly");
 			   	$("#email").val(email);
 			   	$("#phone").val(phone);
 			   	$("#address").val(address);
 			   	$("#city").val(city);
 			   	if(isSupervisor == "SI"){
 				   $("#supervisor").attr("checked", true);   
 			   	} else {
 				   $("#supervisor").attr("checked", false);
 			   	}
 			   	
 			   	$("#btnCleanSupervisor").addClass("disabled");
 			   
 			   	$.ajax({ 
 				   type    : "GET",
 				   url     : Constants.contextRoot + "/controller/html/trader?id=" + id,
 				   dataType: 'json',
 				   async:false,
 				   contentType: "application/json;",
 				   success:function(data) {
 					   Message.hideMessages($('#traderAlertMessages'), $("#traderMessages"));
 					   if(data != null && data.status == 0){
 						   
 						   list = data.data;
 						   
 						   if(list != null && list.length > 0){
 							   var trader = list[0];
 							   
 							  $("#traderParentId").val(trader.parentId);
 							  $("#traderParentDescription").val(trader.parentDescription);
 							  if(trader.parentId != undefined && trader.parentId != null && trader.parentId != ""){
 								  $("#btnCleanSupervisor").removeClass("disabled");
 							  }
 						   }
 						   
 						   return;
 					   }else{
 						   Message.showMessages($('#traderAlertMessages'), $("#traderMessages"), data.message);
 					   }
 				   },
 				   error:function(data){
 					   Message.showMessages($('#traderAlertMessages'), $("#traderMessages"), data.responseJSON.message);
 					   
 					   return;
 				   }
 				});
        	} else {
        		$("#traderId").val("");
        	}
        	
        	$("#modal-trader-container").css({"display":"block"});
        	
        	return $("#modal-trader-container");
        },
        buttons: [{
        	id: 'btnCancel',
        	label: 'Cancelar',
        	icon: 'glyphicon glyphicon-remove-sign',
        	action: function(dialog){
        		var btn = this;
        		Message.hideMessages($('#modalTraderAlertMessages'), $("#modalTraderMessages"));
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
				
				$("#frmTrader").on('invalid.bs.validator', 
					function(e){
					    c++;
						
						return;
				});
				
				$("#frmTrader").validator('validate');
				
				if(c == 0){
					dialog.enableButtons(false);
					dialog.setClosable(false);
	        		btn.spin();
	        		
					// si esta todo ok entonces doy de alta ...
					Trader.add(dialog, btn);
				}
        		
        		return;
        	}
        }]
    });
	//dialog.setSize(BootstrapDialog.SIZE_WIDE);
	dialog.open();
	
	return;
}

Trader.remove = function(id){
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
				   url     : Constants.contextRoot + "/controller/html/trader/" + id,
				   dataType: 'json',
				   contentType: "application/json;",
				   success:function(data) {
					   Message.hideMessages($('#traderAlertMessages'), $("#traderMessages"));
					   if(data != null && data.status == 0){
						   
						   var table = $('#tTraderResult').dataTable();
	
						   table.fnDeleteRow($("#imgCheck_" + id).parent().parent(), null, true);
						   
						   return;
					   }else{
						   Message.showMessages($('#traderAlertMessages'), $("#traderMessages"), data.message);
					   }
				   },
				   error:function(data){
					   Message.showMessages($('#traderAlertMessages'), $("#traderMessages"), data.responseJSON.message);
					   
					   return;
				   }
				});
			}
			
			return;
		}
	});
	
	return;
}

Trader.resetModal = function(){
	$("#traderId").val('');
	$("#name").val('').attr("readonly", false);
	$("#documentNumber").val('').attr("readonly", false);
	$("#email").val('');
	$("#companyPhone").val('');
	$("#companyAddress").val('');
	$("#nearStreets").val('');
	$("#companyCity").val('');
	$("#companyType").val('');
	$("#phone").val('');
	$("#address").val('');
	$("#city").val('');
	$("#supervisor").attr("checked", false);
	$("#traderParentId").val('');
	$("#traderParentDescription").val('');
	$("#frmTrader").validator('destroy');
	
	return;
}

Trader.showTraders = function(parentId){
	BootstrapDialog.show({
		type:BootstrapDialog.TYPE_DANGER,
		title: 'Vendedores a Cargo',
		autodestroy: false,
        message: function(dialog) {
        	
        	var list = [];
        	
	    	$.ajax({ 
			   type    : "GET",
			   url     : Constants.contextRoot + "/controller/html/trader/children?parentId=" + parentId,
			   dataType: 'json',
			   async:false,
			   contentType: "application/json;",
			   success:function(data) {
				   Message.hideMessages($('#traderAlertMessages'), $("#traderMessages"));
				   if(data != null && data.status == 0){
					   
					   list = data.data;
					   
					   return;
				   }else{
					   Message.showMessages($('#traderAlertMessages'), $("#traderMessages"), data.message);
				   }
			   },
			   error:function(data){
				   Message.showMessages($('#traderAlertMessages'), $("#traderMessages"), data.responseJSON.message);
				   
				   return;
			   }
			});
        	
            var traderChildrenDiv = $('#traderChildrenDiv');
            
            var table = $("#tTraderChildrenTable").dataTable({
            	"bDestroy" : true,
            	"createdRow": function ( row, data, index ) {
            		
            		$(row).attr('id', "traderChildrenId_" + data.id);
            		
            		return;
                },
            	"data" : list,
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
                    	"data": "documentNumber" 
                    },
                    {
                    	"className":      'centered',
        	         	// The `data` parameter refers to the data for the cell (defined by the
                        // `data` option, which defaults to the column being worked with, in
                        // this case `data: 0`.
                        "orderable": false,
                        "render": function ( data, type, row ) {
                            //return data +' ('+ row[3]+')';
                            return "<a href=\"javascript:Trader.removeChild('" + row.parentId + "', '" + row.id + "');\" class=\"btn btn-xs btn-danger\"><i class=\"glyphicon glyphicon-trash\"></i></a>";
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
            
            traderChildrenDiv.css({"display": "block"});
            
            return traderChildrenDiv;
        },
        buttons: [
			{
			    label: 'Cerrar',
			    action: function(dialogItself){
			    	var traderChildrenDiv = $('#traderChildrenDiv');
			    	
			    	traderChildrenDiv.css({"display": "none"})
			    	
			        dialogItself.close();
			        
			        return;
			    }
			}
        ]
    });
	
	return;
}


Trader.removeChild = function(parentId, childId){
	
	BootstrapDialog.confirm('Esta usted seguro?', function(result){
        if(result) {
        	$.ajax({ 
    		   type    : "DELETE",
    		   url     : Constants.contextRoot + "/controller/html/trader/children/" + parentId + "/" + childId,
    		   dataType: 'json',
    		   contentType: "application/json;",
    		   success:function(data) {
    			   Message.hideMessages($('#traderAlertMessages'), $("#traderMessages"));
    			   if(data != null && data.status == 0){
    				   
    				   var table = $('#tTraderChildrenTable').dataTable();
    				   
    				   table.fnDeleteRow($("#traderChildrenId_" + childId), null, true);
    				   
    				   return;
    			   }else{
    				   Message.showMessages($('#traderAlertMessages'), $("#traderMessages"), data.message);
    			   }
    		   },
    		   error:function(data){
    			   Message.showMessages($('#traderAlertMessages'), $("#traderMessages"), data.responseJSON.message);
    			   
    			   return;
    		   }
    		});
        }
    });
	
	return;
}

Trader.addTrader = function(parentId){
	var c = "";
	
	if(parentId != ""){
		c = "?parentId=" + parentId;
	}
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/trader" + c,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
			
		   var tbody = $("#tTraderChildrenResult tbody");
		   
		   tbody.children('tr').remove();
		   
		   Message.hideMessages($('#traderAlertMessages'), $("#traderMessages"));
		   if(data != null && data.status == 0){

			   var table = $("#tTraderChildrenResult").dataTable( {
			   		"data" : data.data,
			   		"bDestroy" : true,
			        "columns": [
						{ 
							"className": 'centered',
							"data": "id" 
						},
						{ 
							"className": 'centered',
							"data": "documentNumber" 
						},
			            { 	
			            	"className": 'centered',
			            	"data": "name" 
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
			   
			   	$('#tTraderChildrenResult tbody').on( 'mouseover', 'tr', function () {
					$(this).css({"cursor": "pointer"});	
					
					return;
				});
				
				$('#tTraderChildrenResult tbody').on( 'click', 'tr', function () {
			        if ( $(this).hasClass('selected') ) {
			            $(this).removeClass('selected');
			        } else {
			            table.$('tr.selected').removeClass('selected');
			            $(this).addClass('selected');
			            
			            var selectedId = $(this).children('td').eq(0).html().trim();
			            var selectedDescription = $(this).children('td').eq(2).html().trim();
			            
			            $.ajax({ 
			     		   type    : "POST",
			     		   url     : Constants.contextRoot + "/controller/html/trader/children/" + parentId + "/" + selectedId,
			     		   success:function(data) {
			     			   Message.hideMessages($('#traderAlertMessages'), $("#traderMessages"));
			     			   if(data != null && data.status == 0){
			     				   
			     				   $("#lov-container").css({"display": "none"});

						           BootstrapDialog.closeAll();
			     				   
			     				   return;
			     			   }else{
			     				   Message.showMessages($('#traderAlertMessages'), $("#traderMessages"), data.message);
			     			   }
			     		   },
			     		   error:function(data){
			     			   Message.showMessages($('#traderAlertMessages'), $("#traderMessages"), data.responseJSON.message);
			     			   
			     			   return;
			     		   }
			     		});
			            
			        }
			        
					return;
			    });
				
				BootstrapDialog.show({
					type:BootstrapDialog.TYPE_DANGER,
					title: 'Vendedores',
					autodestroy: false,
			        message: function(dialog) {
			        	
			        	$("#lov-container").css({"display":"block"});
			        	
			        	return $("#lov-container");
			        }
			    });
				
			} else {
				Message.showMessages($('#traderAlertMessages'), $("#traderMessages"), data.message);
			}
	   },
	   error:function(data){
		   Message.showMessages($('#traderAlertMessages'), $("#traderMessages"), data.responseJSON.message);
		   
		   return;
	   }
	});
	
	return;
}

Trader.initControls = function(){
	
	$("#name").keyup(function(e){
		if(e.keyCode == 13) {
			$("#documentNumber").focus();			
		}
	    
	    return;
	});

	$('#name').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#documentNumber").focus();			
	    }
	    
	    return;
	});
	
	$("#documentNumber").keyup(function(e){
		if(e.keyCode == 13) {
			$("#email").focus();			
		}
	    
	    return;
	});

	$('#documentNumber').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#email").focus();			
	    }
	    
	    return;
	});
	
	$("#email").keyup(function(e){
		if(e.keyCode == 13) {
			$("#supervisor").focus();			
		}
	    
	    return;
	});

	$('#email').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#supervisor").focus();			
	    }
	    
	    return;
	});
	
	$("#supervisor").keyup(function(e){
		if(e.keyCode == 13) {
			$("#phone").focus();			
		}
	    
	    return;
	});

	$('#supervisor').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#phone").focus();			
	    }
	    
	    return;
	});
	
	$("#phone").keyup(function(e){
		if(e.keyCode == 13) {
			$("#address").focus();			
		}
	    
	    return;
	});

	$('#phone').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#address").focus();			
	    }
	    
	    return;
	});
	
	$("#address").keyup(function(e){
		if(e.keyCode == 13) {
			$("#city").focus();			
		}
	    
	    return;
	});

	$('#address').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#city").focus();			
	    }
	    
	    return;
	});
	
	$("#city").keyup(function(e){
		if(e.keyCode == 13) {
			$("#btnAccept").focus();			
		}
	    
	    return;
	});

	$('#city').keydown(function(e){
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
