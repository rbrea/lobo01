Permission = function(){}

Permission.userPermissions = [];
Permission.isAdmin = false;

Permission.fill = function(user){

	var $roles = user.roles;
	
	for(var i=0;i<$roles.length;i++){
		Permission.userPermissions.push($roles[i]);
	}
	
	Permission.isAdmin = user.admin;
	
	return;
}

Permission.doCheck = function(){
	$("a[class*='ROLE_ADMIN']").each(function(){
		if(!Permission.isAdmin){
			$(this).css({"display" : "none"});
		}
		
		//$(this).attr("disabled", !Permission.isAdmin);
		
		return;
	});
	
	$("li[class*='ROLE_ADMIN']").each(function(){
		if(!Permission.isAdmin){
			$(this).addClass("disabled");
			$(this).children("a").attr("href", "javascript:void(0);");
			$(this).children("a").css({"cursor": "not-allowed"});
		}
		
		return;
	});
}

