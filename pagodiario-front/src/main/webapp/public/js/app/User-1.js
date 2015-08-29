User = function(){}

User.printUsername = function(){
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/userData",
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
			
		   if(data != null){
				   
			   $("#userLogged").html(data.username);
			   
		   }
	   },
	   error:function(data){
		   
		   return;
	   }
	});
	
	return;
}