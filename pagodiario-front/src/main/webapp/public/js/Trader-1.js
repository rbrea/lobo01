Trader = function(){}

Trader.initDataTable = function(imgCheckUrl){
	
	var table = $("#tTraderResult").dataTable( {
		"bDestroy" : true,
        "ajax": Constants.contextRoot + "/controller/html/trader",
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
            	"className":      'centered',
	         	// The `data` parameter refers to the data for the cell (defined by the
                // `data` option, which defaults to the column being worked with, in
                // this case `data: 0`.
                "orderable": false,
                "render": function ( data, type, row ) {
                    //return data +' ('+ row[3]+')';
                    return "<a href=\"javascript:Trader.showModal('" + row.id + "');\" class=\"btn btn-xs btn-warning\"><i class=\"glyphicon glyphicon-pencil\"></i></a>" 
                        + "&nbsp;<a href=\"javascript:Trader.remove('" + row.id + "');\" class=\"btn btn-xs btn-danger\"><i class=\"glyphicon glyphicon-trash\"></i></a>";
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

Trader.add = function(){
	
	var id = $("#traderId").val();
	var name = $("#name").val();
	var documentNumber = $("#documentNumber").val();
	var email = $("#email").val();
	var address = $("#address").val();
	var phone = $("#phone").val();
	var address = $("#address").val();
	var city = $("#city").val();
	
	var obj = new Object();
	obj.id = id;
	obj.name = name;
	obj.documentNumber = documentNumber;
	obj.email = email;
	obj.address = address;
	obj.phone = phone;
	obj.city = city;
	
	$.ajax({ 
	   type    : "POST",
	   url     : Constants.contextRoot + "/controller/html/trader",
	   dataType: 'json',
	   data: JSON.stringify(obj),
	   contentType: "application/json;",
	   success:function(data) {
		   Message.hideMessages($('#traderAlertMessages'), $("#traderMessages"));
		   if(data != null && data.status == 0){
			   $('#tTraderResult').dataTable().fnClearTable();
			   
			   $("#modalTrader").modal('hide');
			   
			   window.location.reload();
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
	
	
	
	return;
}

Trader.showModal = function(id){
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/trader?id=" + id,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
		   Message.hideMessages($('#traderAlertMessages'), $("#traderMessages"));
		   if(data != null && data.status == 0){
			   
			   var elem = data.data[0];
			   
			   $("#traderId").val(elem.id);
			   $("#name").val(elem.name);
			   $("#documentNumber").val(elem.documentNumber);
			   $("#email").val(elem.email);
			   $("#phone").val(elem.phone);
			   $("#address").val(elem.address);
			   $("#city").val(elem.city);
			   
			   $("#modalClient").modal("show");
			   
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
	
	return;
}

Trader.remove = function(id){
	if(!confirm("Esta seguro de eliminar el registro seleccionado?")){
		return false;
	}
	
	// TODO: Hacer logica para q no pueda borrarse a si mismo ...
	
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
	
	return;
}

Trader.resetModal = function(){
	$("clientId").val('');
	$("#name").val('');
	$("#documentNumber").val('');
	$("#email").val('');
	$("#companyPhone").val('');
	$("#companyAddress").val('');
	$("#nearStreets").val('');
	$("#companyCity").val('');
	$("#companyType").val('');
	$("#phone").val('');
	$("#address").val('');
	$("#city").val('');
	
	return;
}
