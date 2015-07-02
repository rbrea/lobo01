Login = function(){}

Login.initDataTable = function(dataTableUrl, imgCheckUrl){
	
	var table = $("#tResult").dataTable( {
        "ajax": dataTableUrl,
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
            	"data": "username" 
            },
            { 
            	"className": 'centered',
            	"render": function ( data, type, row ) {
			        //return data +' ('+ row[3]+')';
			        var value = "NO";
			        if(row.admin){
			        	value = "SI";
			        }
			        
			        return value;
			    } 
            },
            { 
            	"className": 'centered',
            	"data": "documentType" },
            { 	
            	"className": 'centered',
            	"data": "documentNumber" },
            { 
            	"className": 'centered',
            	"data": "email" },
            {
            	"className":      'centered',
	         	// The `data` parameter refers to the data for the cell (defined by the
                // `data` option, which defaults to the column being worked with, in
                // this case `data: 0`.
                "orderable": false,
                "render": function ( data, type, row ) {
                    //return data +' ('+ row[3]+')';
                    return "<a href=\"javascript:Login.showEditModal('" + row.id + "');\" class=\"btn btn-xs btn-success\"><i class=\"glyphicon glyphicon-pencil\"></i></a>" 
                        + "&nbsp;<a href=\"javascript:Login.removeUser('" + row.id + "');\" class=\"btn btn-xs btn-danger\"><i class=\"glyphicon glyphicon-trash\"></i></a>";
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
    /*
	$('#tResult tbody').on( 'mouseover', 'tr', function () {
		$(this).css({"cursor": "pointer"});	
		
		return;
	});
	
	$('#tResult tbody').on( 'click', 'tr', function () {
		
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
            $("#btnUpdate").addClass("disabled");
            $("#btnRemove").addClass("disabled");
            $(this).children("td:first").children("img").addClass("hide");
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
            $("#btnUpdate").removeClass("disabled");
            $("#btnRemove").removeClass("disabled");
            table.$('tr.selected').children("td:first").children("img").removeClass("hide");
            
        }
    } );
	*/
 
    /*$('#button').click( function () {
        table.row('.selected').remove().draw( false );
    } );*/
	
	return;
}

Login.doLogin = function(){
	var username = $("#username").val();
	var password = $("#password").val();
	
	
	$('#frmLogin').validator();
	
	var user = new Object();
	user.username = username;
	user.password = password;
	
	$.ajax({ 
	   type    : "POST",
	   url     : Constants.contextRoot + "/controller/service/login",
	   async:false,
	   dataType: 'json',
	   contentType: "application/json;",
	   data: JSON.stringify(user),
	   success:function(data) {
		   Message.hideMessages($('#loginAlertMessages'), $("#loginMessages"));
		   if(data != null && data.username != "" && data.username == username){
			   
			   return;
		   }else{
			   Message.showMessages($('#loginAlertMessages'), $("#loginMessages"), data.message);
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#loginAlertMessages'), $("#loginMessages"), data.responseJSON.message);
		   
		   return;
	   }
	});
	
	return;
}

Login.registrationReset = function(){
	
	$("#name").val("");
	$("#email").val("");
	$("#documentNumber").val("");
	$("#-username").val("");
	$("#-password").val("");
	
	return;
}

Login.showEditModal = function(id){

	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/service/registration?id=" + id,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
		   Message.hideMessages($('#loginAlertMessages'), $("#loginMessages"));
		   if(data != null && data.status == 0){
			   
			   var elem = data.data[0];
			   
			   $("#userId").val(elem.id);
			   $("#name").val(elem.name);
			   $("#email").val(elem.email);
			   $("#documentNumber").val(elem.documentNumber);
			   $("#nusername").val(elem.username);
			   if(elem.admin == true){
				   $("#admin").attr("checked", true);   
			   } else {
				   $("#admin").attr("checked", false);
			   }
			   
			   $("#modalEditUser").modal("show");
			   
			   return;
		   }else{
			   Message.showMessages($('#loginAlertMessages'), $("#loginMessages"), data.message);
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#loginAlertMessages'), $("#loginMessages"), data.responseJSON.message);
		   
		   return;
	   }
	});
	
	return;
}

Login.removeUser = function(id){
	
	if(!confirm("Esta seguro de eliminar el registro seleccionado?")){
		return false;
	}
	
	// TODO: Hacer logica para q no pueda borrarse a si mismo ...
	
	$.ajax({ 
	   type    : "DELETE",
	   url     : Constants.contextRoot + "/controller/service/user/registration/" + id,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
		   Message.hideMessages($('#loginAlertMessages'), $("#loginMessages"));
		   if(data != null && data.status == 0){
			   
			   var table = $('#tResult').dataTable();

			   table.fnDeleteRow($("#imgCheck_" + id).parent().parent(), null, true);
			   
			   return;
		   }else{
			   Message.showMessages($('#loginAlertMessages'), $("#loginMessages"), data.message);
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#loginAlertMessages'), $("#loginMessages"), data.responseJSON.message);
		   
		   return;
	   }
	});

	return;
}

Login.editUserReset = function(){
	
	$("#name").val("");
	$("#email").val("");
	$("#documentNumber").val("");
	$("#admin").attr("checked", false);
	$("#nusername").val("");
	
	return;
}