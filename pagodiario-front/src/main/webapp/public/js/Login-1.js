Login = function(){
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
