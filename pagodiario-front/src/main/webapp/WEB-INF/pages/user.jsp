
<div id="modal-edit-user-container" class="container-fluid" style="display:none;">
	<div id="modalEditUserMessages" class="row hide">
	    <div class="col-md-12">
	        <div id="modalEditUserAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#modalEditUserAlertMessages').children('span').eq(0).html('');$('#modalEditUserMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	</div>
	<div class="row">
  		<form id="frmEditUser" action="${pageContext.request.contextPath}/controller/html/user/registration/edit" 
				method="POST" data-toggle="validator">
			<input type="hidden" id="userId" name="id" value="">
		<div class="col-md-12">
			<div class="panel panel-default">
			  	<div class="panel-body">
			    	<div class="form-group">
						<label for="enusername">Nombre de Usuario</label>
						<div class='input-group'>
							<span class="input-group-addon">
			                	<span class="fa fa-user"></span>
			                </span>
					    	<input type="text" class="form-control" id="enusername" name="username" placeholder="Ingrese Nombre de Usuario..." readonly>
					    </div>
					    <div class="help-block with-errors"></div>
					</div>
					<div class="checkbox">
				    	<label>
				      		<input type="checkbox" name="admin" id="admin"> Es Administrador?
				    	</label>
				  	</div>
			    	<div class="form-group">
						<label for="ename">Nombre y Apellido</label>
						<div class='input-group'>
							<span class="input-group-addon">
			                	<span class="fa fa-pencil-square-o"></span>
			                </span>
					    	<input type="text" class="form-control" id="ename" name="name" placeholder="Ingrese Nombre y Apellido...">
					    </div>
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="eemail">e-mail</label>
						<div class='input-group'>
							<span class="input-group-addon">
			                	<span class="fa fa-envelope-o"></span>
			                </span>
					    	<input type="email" class="form-control" id="eemail" name="email" placeholder="Ingrese e-mail...">
					    </div>
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="edocumentNumber">N&uacute;mero de Documento</label>
						<div class='input-group'>
							<span class="input-group-addon">
		                    	<span class="fa fa-barcode"></span>
		                    </span>
					    	<input type="number" class="form-control" id="edocumentNumber" name="documentNumber" placeholder="Ingrese N&uacute;mero de Documento...">
					    </div>
					    <div class="help-block with-errors"></div>
					</div>
			  	</div>
			</div>
		</div>
		</form>
	</div>
</div>

<div id="modal-user-container" class="container-fluid" style="display:none;">
	<div id="modalUserMessages" class="row hide">
	    <div class="col-md-12">
	        <div id="modalUserAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#modalUserAlertMessages').children('span').eq(0).html('');$('#modalUserMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	</div>
	<div class="row">
  		<form id="frmRegistration" action="${pageContext.request.contextPath}/controller/html/user/registration" 
				method="POST" data-toggle="validator">
			<input type="hidden" id="pageFrom" name="pageFrom" value="user">
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="form-group">
							<label for="nusername">Nombre de Usuario</label>
							<div class='input-group'>
								<span class="input-group-addon">
			                		<span class="fa fa-user"></span>
			                	</span>
						    	<input type="text" class="form-control" id="nusername" name="username" placeholder="Ingrese Nombre de Usuario..." required>
						    </div>
						    <div class="help-block with-errors"></div>
						</div>
						<div class="form-group">
							<label for="npassword">Contrase&ntilde;a</label>
							<div class='input-group'>
								<span class="input-group-addon">
			                		<span class="fa fa-expeditedssl"></span>
			                	</span>
						    	<input type="password" class="form-control" id="npassword" name="password" placeholder="Ingrese Contrase&ntilde;a..." data-minlength="6" required>
						    </div>
						    <div class="help-block with-errors"></div>
						</div>
						<div class="form-group">
							<label for="rpassword">Re-Ingrese Contrase&ntilde;a</label>
							<div class='input-group'>
								<span class="input-group-addon">
			                		<span class="fa fa-expeditedssl"></span>
			                	</span>
						    	<input type="password" class="form-control" id="rpassword" name="rpassword" placeholder="Re-Ingrese Contrase&ntilde;a..." data-minlength="6" data-match="#npassword" required>
						    </div>
						    <div class="help-block with-errors"></div>
						</div>
				  	</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="panel panel-default">
				  	<div class="panel-body">
				    	<div class="form-group">
							<label for="name">Nombre y Apellido</label>
							<div class='input-group'>
								<span class="input-group-addon">
			                		<span class="fa fa-pencil-square-o"></span>
			                	</span>
						    	<input type="text" class="form-control" id="name" name="name" placeholder="Ingrese Nombre y Apellido...">
						    </div>
						    <div class="help-block with-errors"></div>
						</div>
						<div class="form-group">
							<label for="email">e-mail</label>
							<div class='input-group'>
								<span class="input-group-addon">
		                        	<span class="fa fa-envelope-o"></span>
		                    	</span>
						    	<input type="email" class="form-control" id="email" name="email" placeholder="Ingrese e-mail...">
						    </div>
						    <div class="help-block with-errors"></div>
						</div>
						<div class="form-group">
							<label for="documentNumber">DNI</label>
							<div class='input-group'>
								<span class="input-group-addon">
		                        	<span class="fa fa-barcode"></span>
		                    	</span>
						    	<input type="number" class="form-control" id="documentNumber" name="documentNumber" placeholder="Ingrese N&uacute;mero de Documento...">
						    </div>
						    <div class="help-block with-errors"></div>
						</div>
				  	</div>
				</div>
			</div>
		</form>
	</div>
</div>

<div class="col-md-1">
	&nbsp;
</div>
<div class="col-md-10">
	<div class="row">
		<div class="col-md-12">
			&nbsp;
		</div>
	</div>
	<div id="userMessages" class="row hide">
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	    <div class="col-md-8">
	        <div id="userAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#userAlertMessages').children('span').eq(0).html('');$('#userMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
		  		<div class="panel-heading">
		    		<h3 class="panel-title"><i class="fa fa-users fa-fw"></i> ABM de Usuarios</h3>
		  		</div>
		  		<div class="panel-body">
		  			<div class="row tools-bar">
		  				<div class="col-md-10">
		  					&nbsp;
		  				</div>
		  				<div class="col-md-2">
		  					<button id="btnNew" type="button" data-loading-text="Espere..." class="btn btn-success"><i class="glyphicon glyphicon-plus"></i>&nbsp;Nuevo</button>
		  				</div>
		  				
		  			</div>
		    		<div class="row" >
		  				<div class="col-md-12">
		  					&nbsp;
		  				</div>
		  			</div>
		    		<div class="row">
						<div class="col-md-12">
							<div class="table-responsive">
								<table id="tResult" class="table table-condensed display">
									<thead>
							            <tr>
							            	<th></th>
							                <th>Nombre de Usuario</th>
							                <th>Es administrador?</th>
							                <th>Nombre</th>
							                <th>DNI</th>
							                <th>Email</th>
							                <th>Acciones</th>
							            </tr>
							        </thead>
							 
								</table>							
							</div>
						</div>
					</div>
		  		</div>
			</div>	
		</div>
	</div>
</div>
<div class="col-md-1">
	&nbsp;
</div>


<script>
	$(document).ready(
		function(){
			
			var urlDataTable = "${pageContext.request.contextPath}/controller/service/registration";
			var imgCheckUrl = "${pageContext.request.contextPath}/public/images/checkmark-outline_32x32.png";
			
			Login.initDataTable(urlDataTable, imgCheckUrl);
			/*
		    $("#btnEditUser").click(function(){
		 	
		    	$("#frmEditUser").submit();
		    	
		    	return;
		    });
		    
			$('#modalEditUser').on('hidden.bs.modal', function (e) {
           		
				Login.editUserReset();
           		
           		return;
           	});
			*/

			$("#btnNew").click(function(){
           		User.showModal();
           		
           		return;
           	});
			
			User.initControls();
			
			return;
		}	
		
	);
</script>