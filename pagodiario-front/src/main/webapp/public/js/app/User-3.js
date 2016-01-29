User = function(){}

User.printUsername = function(){
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/userData",
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
			
		   if(data != null){
			
			   var userLoggedValue = data.username;
			   if(data.name != null && data.name != ""){
				   userLoggedValue = data.name;
			   }
			   $("#userLogged").html(userLoggedValue);
			   // completo la lista de perfiles ...
			   Permission.fill(data);
			   
			   Permission.doCheck();
		   }
	   },
	   error:function(data){
		   
		   return;
	   }
	});
	
	return;
}


User.showModal = function(){
	
	var dialog = new BootstrapDialog({
		onshown: function(){
			$("#nusername").focus();
			
			return;
		},
		onhidden:function(){
			Login.registrationReset();
			
			return;
		},
		draggable: true,
		type: BootstrapDialog.TYPE_DANGER,
		title: 'Alta de Usuarios',
		autodestroy: false,
		cssClass: 'dialog-trader',
        message: function(dialog) {
        	
        	
        	$("#modal-user-container").css({"display":"block"});
        	
        	return $("#modal-user-container");
        },
        buttons: [{
        	id: 'btnCancel',
        	label: 'Cancelar',
        	icon: 'glyphicon glyphicon-remove-sign',
        	action: function(dialog){
        		var btn = this;
        		Message.hideMessages($('#modalUserAlertMessages'), $("#modalTraderMessages"));
        		dialog.close();
        		
        		return;
        	}
        },
        {
        	id: 'btnAcceptNew',
        	label: 'Guardar',
        	icon: 'glyphicon glyphicon-ok-sign',
        	cssClass: 'btn-success',
        	action: function(dialog){
        		var btn = this;
        		
        		$("#frmRegistration").submit();
        		
        		return;
        	}
        }]
    });
	//dialog.setSize(BootstrapDialog.SIZE_WIDE);
	dialog.open();
	
	return;
}

User.showEditModal = function(id, username){
	BootstrapDialog.show({
		onshow: function(){
			
			return;
		},
		onshown: function(){
			
if((id != null && id != "") || (username != null && username != "")){
				
				var args = "";
				if(id != null && id != ""){
					args += "?id=" + id; 
				}
				if(username != null && username != ""){
					if(args == ""){
						args += "?username=" + username;
					} else {
						args += "&username=" + username;
					}
				}
				
        		$.ajax({ 
    			   type    : "GET",
    			   url     : Constants.contextRoot + "/controller/service/registration" + args,
    			   dataType: 'json',
    			   contentType: "application/json;",
    			   async:false,
    			   success:function(data) {
    				   Message.hideMessages($('#modalEditUserAlertMessages'), $("#modalEditUserMessages"));
    				   if(data != null && data.status == 0){
    					   
    					   var elem = data.data[0];
    					   
    					   $("#euuserId").val(elem.id);
    					   $("#euname").val(elem.name);
    					   $("#euemail").val(elem.email);
    					   $("#eudocumentNumber").val(elem.documentNumber);
    					   $("#enuusername").val(elem.username);
    					   
    					   $("#euadmin").prop("checked", elem.admin == true);
    					   
    					   $("#euadmin").prop("disabled", !Permission.isAdmin);   
    					   $("#euname").focus();
    					   
    					   return;
    				   }else{
    					   Message.showMessages($('#modalEditUserAlertMessages'), $("#modalEditUserMessages"), data.message);
    				   }
    			   },
    			   error:function(data){
    				   Message.showMessages($('#modalEditUserAlertMessages'), $("#modalEditUserMessages"), data.responseJSON.message);
    				   
    				   return;
    			   }
    			});
        	}
			
			return;
		},
		onhidden:function(){
			User.editUserReset();
			
			return;
		},
		draggable: true,
		type:BootstrapDialog.TYPE_DANGER,
		title: 'Editar Usuario',
		autodestroy: false,
        message: function(dialog) {
        	
        	$("#modal-edit-user-container").css({"display":"block"});
        	
        	return $("#modal-edit-user-container");
        },
        buttons: [{
        	id: 'btnCancel',
        	label: 'Cancelar',
        	icon: 'glyphicon glyphicon-remove-sign',
        	action: function(dialog){
        		var btn = this;
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
        		$("#frmEditUser").submit();
        		
        		return;
        	}
        }]
    });
	
	
	return;
}

User.initControls = function(){
	
	$("#ename").keyup(function(e){
		if(e.keyCode == 13) {
			$("#eemail").focus();			
		}
	    
	    return;
	});

	$('#ename').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#eemail").focus();			
	    }
	    
	    return;
	});
	
	$("#eemail").keyup(function(e){
		if(e.keyCode == 13) {
			$("#edocumentNumber").focus();			
		}
	    
	    return;
	});

	$('#eemail').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#edocumentNumber").focus();			
	    }
	    
	    return;
	});
	
	$("#edocumentNumber").keyup(function(e){
		if(e.keyCode == 13) {
			$("#btnAccept").focus();			
		}
	    
	    return;
	});

	$('#edocumentNumber').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#btnAccept").focus();			
	    }
	    
	    return;
	});
	
	$("#nusername").keyup(function(e){
		if(e.keyCode == 13) {
			$("#npassword").focus();			
		}
	    
	    return;
	});

	$('#nusername').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#npassword").focus();			
	    }
	    
	    return;
	});
	
	$("#npassword").keyup(function(e){
		if(e.keyCode == 13) {
			$("#rpassword").focus();			
		}
	    
	    return;
	});

	$('#npassword').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#rpassword").focus();			
	    }
	    
	    return;
	});
	
	$("#rpassword").keyup(function(e){
		if(e.keyCode == 13) {
			$("#name").focus();			
		}
	    
	    return;
	});

	$('#rpassword').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#name").focus();			
	    }
	    
	    return;
	});
	
	$("#name").keyup(function(e){
		if(e.keyCode == 13) {
			$("#email").focus();			
		}
	    
	    return;
	});

	$('#name').keydown(function(e){
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
			$("#documentNumber").focus();			
		}
	    
	    return;
	});

	$('#email').keydown(function(e){
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
			$("#btnAcceptNew").focus();			
		}
	    
	    return;
	});

	$('#documentNumber').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#btnAcceptNew").focus();			
	    }
	    
	    return;
	});
	
	return;
}

User.editUserReset = function(){
	$("#euuserId").val("");
	$("#euname").val("");
	$("#euemail").val("");
	$("#eudocumentNumber").val("");
	$("#euadmin").prop("checked", false);
	$("#enuusername").val("");
	$("#frmEditUser").validator('destroy');
	
	return;
}
