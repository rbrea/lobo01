Login = function(){}

Login.init = function(){
	
	$("#verificationCode").keyup(function(e){
		if(e.keyCode == 10 || e.keyCode == 13) {
			$("#newpassword").focus();			
		}
	    
	    return;
	});

	$('#verificationCode').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#newpassword").focus();			
	    }
	    
	    return;
	});
	
	$("#newpassword").keyup(function(e){
		if(e.keyCode == 10 || e.keyCode == 13) {
			$("#rnewpassword").focus();			
		}
	    
	    return;
	});

	$('#newpassword').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#rnewpassword").focus();			
	    }
	    
	    return;
	});
	
	$("#rnewpassword").keyup(function(e){
		if(e.keyCode == 10 || e.keyCode == 13) {
			$("#btnAcceptVerification").focus();			
		}
	    
	    return;
	});

	$('#rnewpassword').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#btnAcceptVerification").focus();			
	    }
	    
	    return;
	});

	$("#forgotUsername").keyup(function(e){
		if(e.keyCode == 10 || e.keyCode == 13) {
			$("#btnSendEmail").focus();			
		}
	    
	    return;
	});

	$('#forgotUsername').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#btnSendEmail").focus();			
	    }
	    
	    return;
	});
	
	return;
}

Login.initDataTable = function(dataTableUrl, imgCheckUrl){
	
	var table = $("#tResult").dataTable( {
        "ajax": dataTableUrl,
        "columns": [
			{
				"className":      'centered',
			 	// The `data` parameter refers to the data for the cell (defined by the
			    // `data` option, which defaults to the column being worked with, in
			    // this case `data: 0`.
			    "orderable": true,
			    "render": function ( data, type, row ) {
			        //return data +' ('+ row[3]+')';
			        return row.id + "<img id=\"imgCheck_" + row.id + "\" class=\"hide\" width=\"60%\" src=\"" + imgCheckUrl + "\">";
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
            	"data": "name" 
            },
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
                    return "<a href=\"javascript:User.showEditModal('" + row.id + "');\" class=\"btn btn-xs btn-warning\"><i class=\"glyphicon glyphicon-pencil\"></i></a>" 
                        + "&nbsp;<a href=\"javascript:Login.removeUser('" + row.id + "', '" + row.username + "');\" class=\"btn btn-xs btn-danger\"><i class=\"glyphicon glyphicon-trash\"></i></a>";
                }
         	}
        ],
        "order": [[0, 'asc']],
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
	$("#frmRegistration").validator('destroy');
	
	return;
}

Login.removeUser = function(id, username){
	
	if(Permission.username == username){
		Message.showMessages($('#userAlertMessages'), $("#userMessages"), "Error. No se puede borrar el usuario seleccionado" +
				", porque ya esta logueado al sistema.");
		
		return false;
	}
	
	BootstrapDialog.confirm({
		title: "Confirmación",
		message: "Esta seguro de eliminar el registro seleccionado?",
		type: BootstrapDialog.TYPE_DANGER,
		draggable: true,
		btnCancelLabel: '<i class="glyphicon glyphicon-remove-sign"></i>&nbsp;NO', // <-- Default value is 'Cancel',
        btnOKLabel: '<i class="glyphicon glyphicon-ok-sign"></i>&nbsp;SI', // <-- Default value is 'OK',
        btnOKClass: 'btn-success',
		callback: function(result){
			if(result) {
				
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
			}
			
			return;
		}
	});
	
	return;
}

Login.editUserReset = function(){
	$("#userId").val("");
	$("#name").val("");
	$("#email").val("");
	$("#documentNumber").val("");
	$("#admin").attr("checked", false);
	$("#nusername").val("");
	$("#frmEditUser").validator('destroy');
	
	return;
}

Login.showMailModal = function(){
	
	var dialog = new BootstrapDialog({
		onshown: function(){
			$("#forgotUsername").focus();
			
			return;
		},
		onhidden:function(){
			Login.resetForgotModal();
			
			return;
		},
		draggable: true,
		type:BootstrapDialog.TYPE_DANGER,
		title: 'Olvid&oacute; su contrase&ntilde;a?',
		autodestroy: false,
        message: function(dialog) {
        	
        	$("#modal-forgot-email-container").css({"display":"block"});
        	
        	return $("#modal-forgot-email-container");
        },
        buttons: [{
        	id: 'btnCancelEmail',
        	label: '&nbsp;Cancelar',
        	icon: 'glyphicon glyphicon-remove-sign',
        	action: function(dialog){
        		var btn = this;
        		Message.hideMessages($('#modalForgotEmailAlertMessages'), $("#modalForgotEmailMessages"));
        		dialog.close();
        		
        		return;
        	}
        },
        {
        	id: 'btnSendEmail',
        	label: '&nbsp;Env&iacute;ar',
        	icon: 'glyphicon glyphicon-ok-sign',
        	cssClass: 'btn-success',
        	action: function(dialog){
        		var btn = this;
        		var c = 0;
				
				$("#frmForgotEmail").on('invalid.bs.validator', 
					function(e){
					    c++;
						
						return;
				});
				
				$("#frmForgotEmail").validator('validate');
				
				if(c == 0){
					dialog.enableButtons(false);
					dialog.setClosable(false);
	        		btn.spin();
	        		
					// si esta todo ok entonces doy de alta ...
					Login.doEmailSent(dialog, btn);
				}
        		
        		return;
        	}
        }]
    });
	dialog.open();
	
	return;
}

Login.showForgetPasswordModal = function(){

	var dialog = new BootstrapDialog({
		onshown: function(){
			$("#verificationCode").focus();
			
			return;
		},
		onhidden:function(){
			Login.resetForgotModal();
			
			return;
		},
		draggable: true,
		type:BootstrapDialog.TYPE_DANGER,
		title: 'Olvid&oacute; su contrase&ntilde;a?',
		autodestroy: false,
        message: function(dialog) {
        	
        	$("#modal-forgot-password-container").css({"display":"block"});
        	
        	return $("#modal-forgot-password-container");
        },
        buttons: [{
        	id: 'btnCancelVerification',
        	label: '&nbsp;Cancelar',
        	icon: 'glyphicon glyphicon-remove-sign',
        	action: function(dialog){
        		var btn = this;
        		Message.hideMessages($('#modalForgotAlertMessages'), $("#modalForgotMessages"));
        		dialog.close();
        		
        		return;
        	}
        },
        {
        	id: 'btnAcceptVerification',
        	label: '&nbsp;Aceptar',
        	icon: 'glyphicon glyphicon-ok-sign',
        	cssClass: 'btn-success',
        	action: function(dialog){
        		var btn = this;
        		var c = 0;
				
				$("#frmForgotPassword").on('invalid.bs.validator', 
					function(e){
					    c++;
						
						return;
				});
				
				$("#frmForgotPassword").validator('validate');
				
				if(c == 0){
					dialog.enableButtons(false);
					dialog.setClosable(false);
	        		btn.spin();
	        		
					// si esta todo ok entonces doy de alta ...
					Login.doCodeVerification(dialog, btn);
				}
        		
        		return;
        	}
        }]
    });
	dialog.open();
	
	return;
}

Login.resetForgotModal = function(){

	$("#verificationCode").val("");
	$("#newpassword").val("");
	$("#rnewpassword").val("");
	$("#forgotUsername").val("");
	
	return;
}

Login.doCodeVerification = function(dialog, btn){

	var obj = new Object();
	obj.verificationCode = $("#verificationCode").val();
	obj.newPassword = $("#newpassword").val();
	
	$.ajax({ 
	   type    : "POST",
	   url     : Constants.contextRoot + "/controller/html/forgot/forgotPassword",
	   async:false,
	   dataType: 'json',
	   contentType: "application/json;",
	   data: JSON.stringify(obj),
	   success:function(data) {
		   Message.hideMessages($('#modalForgotAlertMessages'), $("#modalForgotMessages"));
		   if(data != null && data.status == 0){
			
			   BootstrapDialog.alert({
					title: "Confirmación",
					message: "Se ha modificado satisfactoriamente su contrase&ntilde;a.",
					type: BootstrapDialog.TYPE_DANGER,
					draggable: true,
			        btnOKLabel: '<i class="glyphicon glyphicon-ok-sign"></i>&nbsp;SI', // <-- Default value is 'OK',
			        btnOKClass: 'btn-success'
				});
			   dialog.enableButtons(true);
			   dialog.setClosable(true);
	   		   btn.stopSpin();
			   dialog.close();
		   } else {
			   Message.showMessages($('#modalForgotAlertMessages'), $("#modalForgotMessages"), data.message);
			   dialog.enableButtons(true);
			   dialog.setClosable(true);
	   		   btn.stopSpin();
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#modalForgotAlertMessages'), $("#modalForgotMessages"), data.responseJSON.message);
		   
		   dialog.enableButtons(true);
		   dialog.setClosable(true);
   		   btn.stopSpin();
		   
		   return;
	   }
	});
	
	return;
}

Login.doEmailSent = function(dialog, btn){
	var obj = new Object();
	obj.username = $("#forgotUsername").val();
	
	var url = Constants.contextRoot + "/controller/html/forgot/send/email";
	
	$.ajax({ 
	   type    : "POST",
	   url     : url,
	   dataType: 'json',
	   contentType: "application/json;",
	   data: JSON.stringify(obj),
	   success:function(data) {
		   Message.hideMessages($('#modalForgotEmailAlertMessages'), $("#modalForgotEmailMessages"));
		   if(data != null && data.status == 0){
			
			   BootstrapDialog.alert({
					title: "Alerta",
					message: "Se ha enviado satisfactoriamente el email con el c&oacute;digo de verificaci&oacute;n.",
					type: BootstrapDialog.TYPE_DANGER,
					draggable: true,
			        btnOKLabel: '<i class="glyphicon glyphicon-ok-sign"></i>&nbsp;SI', // <-- Default value is 'OK',
			        btnOKClass: 'btn-success'
				});
			   dialog.enableButtons(true);
			   dialog.setClosable(true);
	   		   btn.stopSpin();
			   dialog.close();
			   
			   // finalmente lanzo el modal para confirmar el cod de verificacion e ingresar el nuevo password ----
			   Login.showForgetPasswordModal();
		   } else {
			   Message.showMessages($('#modalForgotEmailAlertMessages'), $("#modalForgotEmailMessages"), data.message);
			   dialog.enableButtons(true);
			   dialog.setClosable(true);
	   		   btn.stopSpin();
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#modalForgotEmailAlertMessages'), $("#modalForgotEmailMessages"), data.responseJSON.message);
		   
		   dialog.enableButtons(true);
		   dialog.setClosable(true);
   		   btn.stopSpin();
		   
		   return;
	   }
	});
	
	return;
}
