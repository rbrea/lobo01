Product = function(){}

Product.initDataTable = function(imgCheckUrl){
	
	var table = $("#tProductResult").dataTable( {
		"bDestroy" : true,
        "ajax": Constants.contextRoot + "/controller/html/product",
        "columns": [
			{
				"className":      'centered',
			 	// The `data` parameter refers to the data for the cell (defined by the
			    // `data` option, which defaults to the column being worked with, in
			    // this case `data: 0`.
			    "orderable": false,
			    "render": function ( data, type, row ) {
			        //return data +' ('+ row[3]+')';
			        return "<img id=\"imgCheck_" + row.id + "\" class=\"hide\" width=\"60%\" src=\"" + imgCheckUrl + "\">";
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
            },
            {
            	"className":      'centered',
	         	// The `data` parameter refers to the data for the cell (defined by the
                // `data` option, which defaults to the column being worked with, in
                // this case `data: 0`.
                "orderable": false,
                "render": function ( data, type, row ) {
                    //return data +' ('+ row[3]+')';
                    return "<a href=\"javascript:Product.showModal('" + row.id + "');\" class=\"btn btn-xs btn-warning\"><i class=\"glyphicon glyphicon-pencil\"></i></a>" 
                        + "&nbsp;<a href=\"javascript:Product.remove('" + row.id + "');\" class=\"btn btn-xs btn-danger\"><i class=\"glyphicon glyphicon-trash\"></i></a>";
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
	
	return;
}

Product.add = function(dialog, btn){
	
	var id = $("#productId").val();
	var productCode = $("#productCode").val();
	var productDescription = $("#productDescription").val();
	var productPrice = $("#productPrice").val();
	var dailyInstallment = $("#dailyInstallment").val();
	
	var obj = new Object();
	obj.id = id;
	obj.code = productCode;
	obj.description = productDescription;
	obj.price = productPrice;
	obj.dailyInstallment = dailyInstallment;
	
	$.ajax({ 
	   type    : "POST",
	   url     : Constants.contextRoot + "/controller/html/product",
	   dataType: 'json',
	   data: JSON.stringify(obj),
	   contentType: "application/json;",
	   success:function(data) {
		   Message.hideMessages($('#modalProductAlertMessages'), $("#modalProductMessages"));
		   if(data != null && data.status == 0){
			   var table = $('#tProductResult').dataTable();

			   table.api().ajax.url(Constants.contextRoot + "/controller/html/product").load();
			   
			   dialog.enableButtons(true);
			   dialog.setClosable(true);
       		   btn.stopSpin();
			   dialog.close();
			   
			   return;
		   }else{
			   Message.showMessages($('#modalProductAlertMessages'), $("#modalProductMessages"), data.message);
			   
			   dialog.enableButtons(true);
			   dialog.setClosable(true);
       		   btn.stopSpin();
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#modalProductAlertMessages'), $("#modalProductMessages"), data.responseJSON.message);
		   
		   dialog.enableButtons(true);
		   dialog.setClosable(true);
   		   btn.stopSpin();
		   
		   return;
	   }
	});
	
	
	
	return;
}

Product.showModal = function(id){
	
	BootstrapDialog.show({
		onshown: function(){
			$("#productCode").focus();
			
			return;
		},
		onhidden:function(){
			Product.resetModal();
			
			return;
		},
		draggable: true,
		type:BootstrapDialog.TYPE_PRIMARY,
		title: 'Productos',
		autodestroy: false,
        message: function(dialog) {
        	
        	if(id != null && id != ""){
        		$("#productId").val(id);
        		
        		var code = $("#tProductResult").find('tr', 'tbody').find('td:eq(0)').children("img[id='imgCheck_" + id + "']")
        				.parent().parent().find('td:eq(1)').html().trim();
        		var description = $("#tProductResult").find('tr', 'tbody').find('td:eq(0)').children("img[id='imgCheck_" + id + "']")
					.parent().parent().find('td:eq(2)').html().trim();
        		var price = $("#tProductResult").find('tr', 'tbody').find('td:eq(0)').children("img[id='imgCheck_" + id + "']")
					.parent().parent().find('td:eq(3)').html().trim();
        		var dailyInstallment = $("#tProductResult").find('tr', 'tbody').find('td:eq(0)').children("img[id='imgCheck_" + id + "']")
					.parent().parent().find('td:eq(4)').html().trim();
        		
        		$("#productCode").attr("readonly", true).val(code);
        		$("#productDescription").val(description);
        		$("#productPrice").val(price);
        		$("#dailyInstallment").val(dailyInstallment);
        	}
        	
        	$("#modal-product-container").css({"display":"block"});
        	
        	return $("#modal-product-container");
        },
        buttons: [{
        	id: 'btnCancel',
        	label: 'Cancelar',
        	icon: 'glyphicon glyphicon-remove-sign',
        	action: function(dialog){
        		var btn = this;
        		Message.hideMessages($('#modalProductAlertMessages'), $("#modalProductMessages"));
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
				
				$("#frmProduct").on('invalid.bs.validator', 
					function(e){
					    c++;
						
						return;
				});
				
				$("#frmProduct").validator('validate');
				
				if(c == 0){
					dialog.enableButtons(false);
					dialog.setClosable(false);
	        		btn.spin();
	        		
					// si esta todo ok entonces doy de alta ...
					Product.add(dialog, btn);
				}
        		
        		return;
        	}
        }]
    });
	
	return;
}	
	
Product.remove = function(id){
	BootstrapDialog.confirm("Esta seguro de eliminar el registro seleccionado?", function(result){
		if(result) {
			$.ajax({ 
			   type    : "DELETE",
			   url     : Constants.contextRoot + "/controller/html/product/" + id,
			   dataType: 'json',
			   contentType: "application/json;",
			   success:function(data) {
				   Message.hideMessages($('#productAlertMessages'), $("#productMessages"));
				   if(data != null && data.status == 0){
					   
					   var table = $('#tProductResult').dataTable();

					   table.fnDeleteRow($("#imgCheck_" + id).parent().parent(), null, true);
					   
					   return;
				   }else{
					   Message.showMessages($('#productAlertMessages'), $("#productMessages"), data.message);
				   }
			   },
			   error:function(data){
				   Message.showMessages($('#productAlertMessages'), $("#productMessages"), data.responseJSON.message);
				   
				   return;
			   }
			});
		}
		
		return;
	});
	
	return;
}

Product.resetModal = function(){
	$("productId").val('');
	$("#productCode").attr("readonly", false).val('');
	$("#productDescription").val('');
	$("#productPrice").val('');
	$("#dailyInstallment").val('');
	$("#frmProduct").validator('destroy');
	
	return;
}

Product.initControls = function(){
	
	$("#productCode").keyup(function(e){
		if(e.keyCode == 13) {
			$("#productDescription").focus();			
		}
	    
	    return;
	});

	$('#productCode').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#productDescription").focus();			
	    }
	    
	    return;
	});
	
	$("#productDescription").keyup(function(e){
		if(e.keyCode == 13) {
			$("#productPrice").focus();			
		}
	    
	    return;
	});

	$('#productDescription').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#productPrice").focus();			
	    }
	    
	    return;
	});
	
	$("#productPrice").keyup(function(e){
		if(e.keyCode == 13) {
			$("#dailyInstallment").focus();			
		}
	    
	    return;
	});

	$('#productPrice').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#dailyInstallment").focus();			
	    }
	    
	    return;
	});
	
	$("#dailyInstallment").keyup(function(e){
		if(e.keyCode == 13) {
			$("#btnAccept").focus();			
		}
	    
	    return;
	});

	$('#dailyInstallment').keydown(function(e){
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
