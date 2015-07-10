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

Product.add = function(){
	
	var id = $("#productId").val();
	var productCode = $("#productCode").val();
	var productDescription = $("#productDescription").val();
	var productPrice = $("#productPrice").val();
	
	var obj = new Object();
	obj.id = id;
	obj.code = productCode;
	obj.description = productDescription;
	obj.price = productPrice;
	
	$.ajax({ 
	   type    : "POST",
	   url     : Constants.contextRoot + "/controller/html/product",
	   dataType: 'json',
	   data: JSON.stringify(obj),
	   contentType: "application/json;",
	   success:function(data) {
		   Message.hideMessages($('#productAlertMessages'), $("#productMessages"));
		   if(data != null && data.status == 0){
			   var table = $('#tProductResult').dataTable();

			   table.api().ajax.url(Constants.contextRoot + "/controller/html/product").load();
			   
			   $("#modalProduct").modal('hide');

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
	
	
	
	return;
}

Product.showModal = function(id){
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/product?id=" + id,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
		   Message.hideMessages($('#productAlertMessages'), $("#productMessages"));
		   if(data != null && data.status == 0){
			   
			   var elem = data.data[0];
			   
			   $("#productId").val(elem.id);
			   $("#productCode").val(elem.code);
			   $("#productDescription").val(elem.description);
			   $("#productPrice").val(elem.price);
			   
			   $("#modalProduct").modal("show");
			   
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
	
	return;
}

Product.remove = function(id){
	if(!confirm("Esta seguro de eliminar el registro seleccionado?")){
		return false;
	}
	
	// TODO: Hacer logica para q no pueda borrarse a si mismo ...
	
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
	
	return;
}

Product.resetModal = function(){
	$("productId").val('');
	$("#productCode").val('');
	$("#productDescription").val('');
	$("#productPrice").val('');
	
	return;
}
