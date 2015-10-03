Client = function(){}

Client.initDataTable = function(imgCheckUrl){
	
	var table = $("#tClientResult").dataTable( {
		"bDestroy" : true,
        "ajax": Constants.contextRoot + "/controller/html/client",
        "createdRow": function ( row, data, index ) {
    		
    		$(row).data('email', data.email).data('phone', data.phone).data('address', data.address).data('city', data.city);
    		
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
            	"data": "companyPhone" 
            },
            { 
            	"className": 'centered',
            	"data": "companyAddress" 
            },
            { 
            	"className": 'centered',
            	"data": "companyCity" 
            },
            { 	
            	"className": 'centered',
            	"data": "companyType" 
            },
            { 	
            	"className": 'centered',
            	"data": "email",
            	"visible":false
            },
            { 	
            	"className": 'centered',
            	"data": "phone",
            	"visible":false 
            },
            { 	
            	"className": 'centered',
            	"data": "address",
            	"visible":false 
            },
            { 	
            	"className": 'centered',
            	"data": "city",
            	"visible":false
            },
            {
            	"className":      'centered',
	         	// The `data` parameter refers to the data for the cell (defined by the
                // `data` option, which defaults to the column being worked with, in
                // this case `data: 0`.
                "orderable": false,
                "render": function ( data, type, row ) {
                    //return data +' ('+ row[3]+')';
                    return "<a href=\"javascript:Client.showModal('" + row.id + "');\" class=\"btn btn-xs btn-warning\"><i class=\"glyphicon glyphicon-pencil\"></i></a>" 
                        + "&nbsp;<a href=\"javascript:Client.remove('" + row.id + "');\" class=\"btn btn-xs btn-danger\"><i class=\"glyphicon glyphicon-trash\"></i></a>";
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

Client.add = function(dialog, btn){
	
	var id = $("#clientId").val();
	var name = $("#name").val();
	var documentNumber = $("#documentNumber").val();
	var email = $("#email").val();
	var companyPhone = $("#companyPhone").val();
	var companyAddress = $("#companyAddress").val();
	var nearStreets = $("#nearStreets").val();
	var companyCity = $("#companyCity").val();
	var companyType = $("#companyType").val();
	var phone = $("#phone").val();
	var address = $("#address").val();
	var city = $("#city").val();
	
	var obj = new Object();
	obj.id = id;
	obj.name = name;
	obj.documentNumber = documentNumber;
	obj.email = email;
	obj.companyPhone = companyPhone;
	obj.companyAddress = companyAddress;
	obj.nearStreets = nearStreets;
	obj.companyCity = companyCity;
	obj.companyType = companyType;
	obj.phone = phone;
	obj.address = address;
	obj.city = city;
	
	$.ajax({ 
	   type    : "POST",
	   url     : Constants.contextRoot + "/controller/html/client",
	   dataType: 'json',
	   data: JSON.stringify(obj),
	   contentType: "application/json;",
	   success:function(data) {
		   Message.hideMessages($('#modalClientAlertMessages'), $("#modalClientMessages"));
		   if(data != null && data.status == 0){
			   var table = $('#tClientResult').dataTable();
			   
			   table.api().ajax.url(Constants.contextRoot + "/controller/html/client").load();
			   
			   dialog.enableButtons(true);
			   dialog.setClosable(true);
       		   btn.stopSpin();
			   dialog.close();
			   
			   return;
		   }else{
			   Message.showMessages($('#modalClientAlertMessages'), $("#modalClientMessages"), data.message);
			   
			   dialog.enableButtons(true);
			   dialog.setClosable(true);
       		   btn.stopSpin();
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#modalClientAlertMessages'), $("#modalClientMessages"), data.responseJSON.message);
		   
		   dialog.enableButtons(true);
		   dialog.setClosable(true);
   		   btn.stopSpin();
		   
		   return;
	   }
	});
	
	
	
	return;
}

Client.showModal = function(id){
	
	var dialog = new BootstrapDialog({
		onshown: function(){
			$("#name").focus();
			
			return;
		},
		onhidden:function(){
			Client.resetModal();
			
			return;
		},
		draggable: true,
		type:BootstrapDialog.TYPE_DANGER,
		title: 'Clientes',
		autodestroy: false,
		cssClass: 'dialog-client',
        message: function(dialog) {
        	
        	if(id != null && id != ""){
        		$("#clientId").val(id);
        		
        		var name = $("#tClientResult").find('tr', 'tbody').find('td:eq(0)').children("img[id='imgCheck_" + id + "']")
        				.parent().parent().find('td:eq(1)').html().trim();
        		var documentNumber = $("#tClientResult").find('tr', 'tbody').find('td:eq(0)').children("img[id='imgCheck_" + id + "']")
					.parent().parent().find('td:eq(2)').html().trim();
        		var companyPhone = $("#tClientResult").find('tr', 'tbody').find('td:eq(0)').children("img[id='imgCheck_" + id + "']")
					.parent().parent().find('td:eq(3)').html().trim();
        		
        		var companyAddress = $("#tClientResult").find('tr', 'tbody').find('td:eq(0)').children("img[id='imgCheck_" + id + "']")
					.parent().parent().find('td:eq(4)').html().trim();
        		var nearStreets = $("#tClientResult").find('tr', 'tbody').find('td:eq(0)').children("img[id='imgCheck_" + id + "']")
					.parent().parent().find('td:eq(5)').html().trim();
        		var companyCity = $("#tClientResult").find('tr', 'tbody').find('td:eq(0)').children("img[id='imgCheck_" + id + "']")
					.parent().parent().find('td:eq(6)').html().trim();
        		var companyType = $("#tClientResult").find('tr', 'tbody').find('td:eq(0)').children("img[id='imgCheck_" + id + "']")
					.parent().parent().find('td:eq(7)').html().trim();
        		
        		var email = $("#tClientResult").find('tr', 'tbody').find('td:eq(0)')
        			.children("img[id='imgCheck_" + id + "']").parent().parent().data('email');
        		var phone = $("#tClientResult").find('tr', 'tbody').find('td:eq(0)')
					.children("img[id='imgCheck_" + id + "']").parent().parent().data('phone');
        		var address = $("#tClientResult").find('tr', 'tbody').find('td:eq(0)')
					.children("img[id='imgCheck_" + id + "']").parent().parent().data('address');
        		var city = $("#tClientResult").find('tr', 'tbody').find('td:eq(0)')
					.children("img[id='imgCheck_" + id + "']").parent().parent().data('city');
        		
 			    $("#name").val(name);
 			    $("#documentNumber").attr("readonly", true).val(documentNumber);
 			    $("#email").val(email);
 			    $("#companyPhone").val(companyPhone);
 			    $("#companyAddress").val(companyAddress);
 			    $("#nearStreets").val(nearStreets);
 			    $("#companyCity").val(companyCity);
 			    $("#companyType").val(companyType);
 			    $("#phone").val(phone);
 			    $("#address").val(address);
 			    $("#city").val(city);
        	}
        	
        	$("#modal-client-container").css({"display":"block"});
        	
        	return $("#modal-client-container");
        },
        buttons: [{
        	id: 'btnCancel',
        	label: 'Cancelar',
        	icon: 'glyphicon glyphicon-remove-sign',
        	action: function(dialog){
        		var btn = this;
        		Message.hideMessages($('#modalClientAlertMessages'), $("#modalClientMessages"));
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
				
				$("#frmClient").on('invalid.bs.validator', 
					function(e){
					    c++;
						
						return;
				});
				
				$("#frmClient").validator('validate');
				
				if(c == 0){
					dialog.enableButtons(false);
					dialog.setClosable(false);
	        		btn.spin();
	        		
					// si esta todo ok entonces doy de alta ...
					Client.add(dialog, btn);
				}
        		
        		return;
        	}
        }]
    });
	dialog.setSize(BootstrapDialog.SIZE_WIDE);
	dialog.open();

	//$("#modal-client-container").parent().parent().parent().parent().parent().css({'width':'800px'});
	
	return;
}

Client.remove = function(id){
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
				   url     : Constants.contextRoot + "/controller/html/client/" + id,
				   dataType: 'json',
				   contentType: "application/json;",
				   success:function(data) {
					   Message.hideMessages($('#clientAlertMessages'), $("#clientMessages"));
					   if(data != null && data.status == 0){
						   
						   var table = $('#tClientResult').dataTable();

						   table.fnDeleteRow($("#imgCheck_" + id).parent().parent(), null, true);
						   
						   return;
					   }else{
						   Message.showMessages($('#clientAlertMessages'), $("#clientMessages"), data.message);
					   }
				   },
				   error:function(data){
					   Message.showMessages($('#clientAlertMessages'), $("#clientMessages"), data.responseJSON.message);
					   
					   return;
				   }
				});
			}
			
			return;
		}
	});
	
	return;
}

Client.resetModal = function(){
	$("#clientId").val('');
	$("#name").val('');
	$("#documentNumber").attr("readonly", false).val('');
	$("#email").val('');
	$("#companyPhone").val('');
	$("#companyAddress").val('');
	$("#nearStreets").val('');
	$("#companyCity").val('');
	$("#companyType").val('');
	$("#phone").val('');
	$("#address").val('');
	$("#city").val('');
	$("#frmClient").validator('destroy');
	
	return;
}

Client.initControls = function(){
	
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
			$("#companyPhone").focus();			
		}
	    
	    return;
	});

	$('#email').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#companyPhone").focus();			
	    }
	    
	    return;
	});
	
	$("#companyPhone").keyup(function(e){
		if(e.keyCode == 13) {
			$("#companyAddress").focus();			
		}
	    
	    return;
	});

	$('#companyPhone').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#companyAddress").focus();			
	    }
	    
	    return;
	});
	
	$("#companyAddress").keyup(function(e){
		if(e.keyCode == 13) {
			$("#nearStreets").focus();			
		}
	    
	    return;
	});

	$('#companyAddress').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#nearStreets").focus();			
	    }
	    
	    return;
	});
	
	$("#nearStreets").keyup(function(e){
		if(e.keyCode == 13) {
			$("#companyCity").focus();			
		}
	    
	    return;
	});

	$('#nearStreets').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#companyCity").focus();			
	    }
	    
	    return;
	});
	
	$("#companyCity").keyup(function(e){
		if(e.keyCode == 13) {
			$("#companyType").focus();			
		}
	    
	    return;
	});

	$('#companyCity').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#companyType").focus();			
	    }
	    
	    return;
	});
	
	$("#companyType").keyup(function(e){
		if(e.keyCode == 13) {
			$("#phone").focus();			
		}
	    
	    return;
	});

	$('#companyType').keydown(function(e){
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

Client.searchByName = function(name){
	
	var url = Constants.contextRoot + "/controller/html/client/autocomplete?q=" + name;
	
	$.ajax({ 
	   type    : "GET",
	   url     : url,
	   dataType: 'json',
	   contentType: "application/json;",
	   async: false,
	   success:function(data) {
		   $("#bill-client-form-group").removeClass("has-error");
		   $("#billClientErrorMessageDiv").html("");
		   if(data != null && data.length == 1){
			   // todo ok
			   var client = data[0];
			   $("#billClientIdSelected").val(client.id);
		   } else {
			   $("#bill-client-form-group").addClass("has-error");
			   $("#billClientErrorMessageDiv").append(Message.createErrorBlockMessage("Cliente inexistente"));
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#facturaAlertMessages'), $("#facturaMessages"), data.responseJSON.message);
		   
		   dialog.enableButtons(true);
		   dialog.setClosable(true);
   		   btn.stopSpin();
		   
		   return;
	   }
	});
	
	return;
}

Client.exportToPdf = function(){

	$("#frmClientExportPdf").submit();
	
	return;
}
