Client = function(){}

Client.initDataTable = function(imgCheckUrl){
	
	var table = $("#tClientResult").dataTable( {
		"bDestroy" : true,
        "ajax": Constants.contextRoot + "/controller/html/client",
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
            	"data": "companyPhone" 
            },
            { 
            	"className": 'centered',
            	"data": "companyAddress" 
            },
            { 	
            	"className": 'centered',
            	"data": "nearStreets" 
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

Client.add = function(){
	
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
		   Message.hideMessages($('#clientAlertMessages'), $("#clientMessages"));
		   if(data != null && data.status == 0){
			   $('#tClientResult').dataTable().fnClearTable();
			   
			   $("#modalClient").modal('hide');
			   
			   window.location.reload();
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
	
	
	
	return;
}

Client.showModal = function(id){
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/client?id=" + id,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
		   Message.hideMessages($('#clientAlertMessages'), $("#clientMessages"));
		   if(data != null && data.status == 0){
			   
			   var elem = data.data[0];
			   
			   $("#clientId").val(elem.id);
			   $("#name").val(elem.name);
			   $("#documentNumber").val(elem.documentNumber);
			   $("#email").val(elem.email);
			   $("#companyPhone").val(elem.companyPhone);
			   $("#companyAddress").val(elem.companyAddress);
			   $("#nearStreets").val(elem.nearStreets);
			   $("#companyCity").val(elem.companyCity);
			   $("#companyType").val(elem.companyType);
			   $("#phone").val(elem.phone);
			   $("#address").val(elem.address);
			   $("#city").val(elem.city);
			   
			   $("#modalClient").modal("show");
			   
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
	
	return;
}

Client.remove = function(id){
	if(!confirm("Esta seguro de eliminar el registro seleccionado?")){
		return false;
	}
	
	// TODO: Hacer logica para q no pueda borrarse a si mismo ...
	
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
	
	return;
}

Client.resetModal = function(){
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
