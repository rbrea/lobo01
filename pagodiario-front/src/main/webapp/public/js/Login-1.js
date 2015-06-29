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
//			   $.get(Constants.contextRoot + "/controller/html/index");
			   
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
