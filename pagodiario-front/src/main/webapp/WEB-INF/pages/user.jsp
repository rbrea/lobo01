<div class="modal fade" id="modalEditUser" tabindex="-1" role="dialog" aria-labelledby="modalEditUserLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modalEditUserLabel">Editar Usuario</h4>
      </div>
      <div class="modal-body">
		<form id="frmEditUser" action="${pageContext.request.contextPath}/controller/html/user/registration/edit" 
				method="POST" data-toggle="validator">
			<input type="hidden" id="userId" name="id" value="">
	    	<div class="form-group">
				<label for="name">Nombre y Apellido</label>
			    <input type="text" class="form-control" id="name" name="name" placeholder="Ingrese Nombre y Apellido..." required>
			    <div class="help-block with-errors"></div>
			</div>
			<div class="form-group">
				<label for="email">e-mail</label>
			    <input type="email" class="form-control" id="email" name="email" placeholder="Ingrese e-mail..." required>
			    <div class="help-block with-errors"></div>
			</div>
			<div class="form-group">
				<label for="documentNumber">N&uacute;mero de Documento</label>
			    <input type="number" class="form-control" id="documentNumber" name="documentNumber" placeholder="Ingrese N&uacute;mero de Documento..." required>
			    <div class="help-block with-errors"></div>
			</div>
			<div class="form-group">
				<label for="nusername">Nombre de Usuario</label>
			    <input type="text" class="form-control" id="nusername" name="username" placeholder="Ingrese Nombre de Usuario..." readonly>
			    <div class="help-block with-errors"></div>
			</div>
			<div class="checkbox">
		    	<label>
		      		<input type="checkbox" name="admin" id="admin"> Es Administrador?
		    	</label>
		  	</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        <button id="btnEditUser" type="button" class="btn btn-primary">Guardar</button>
      </div>
    </div>
  </div>
</div>
<div class="modal fade" id="modalRegistration" tabindex="-1" role="dialog" aria-labelledby="modalRegistrationLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modalRegistrationLabel">Registraci&oacute;n</h4>
      </div>
      <div class="modal-body">
		<form id="frmRegistration" action="${pageContext.request.contextPath}/controller/html/user/registration" 
				method="POST" data-toggle="validator">
			<input type="hidden" id="pageFrom" name="pageFrom" value="user">
	    	<div class="form-group">
				<label for="name">Nombre y Apellido</label>
			    <input type="text" class="form-control" id="name" name="name" placeholder="Ingrese Nombre y Apellido..." required>
			    <div class="help-block with-errors"></div>
			</div>
			<div class="form-group">
				<label for="email">e-mail</label>
			    <input type="email" class="form-control" id="email" name="email" placeholder="Ingrese e-mail..." required>
			    <div class="help-block with-errors"></div>
			</div>
			<div class="form-group">
				<label for="documentNumber">N&uacute;mero de Documento</label>
			    <input type="number" class="form-control" id="documentNumber" name="documentNumber" placeholder="Ingrese N&uacute;mero de Documento..." required>
			    <div class="help-block with-errors"></div>
			</div>
			<div class="form-group">
				<label for="nusername">Nombre de Usuario</label>
			    <input type="text" class="form-control" id="nusername" name="username" placeholder="Ingrese Nombre de Usuario..." required>
			    <div class="help-block with-errors"></div>
			</div>
			<div class="form-group">
				<label for="npassword">Contrase&ntilde;a</label>
			    <input type="password" class="form-control" id="npassword" name="password" placeholder="Ingrese Contrase&ntilde;a..." data-minlength="6" required>
			    <div class="help-block with-errors"></div>
			</div>
			<div class="form-group">
				<label for="rpassword">Re-Ingrese Contrase&ntilde;a</label>
			    <input type="password" class="form-control" id="rpassword" name="rpassword" placeholder="Re-Ingrese Contrase&ntilde;a..." data-minlength="6" data-match="#npassword" required>
			    <div class="help-block with-errors"></div>
			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        <button id="btnRegistration" type="button" class="btn btn-primary">Guardar</button>
      </div>
    </div>
  </div>
</div>
<div class="col-md-1">
	&nbsp;
</div>
<div class="col-md-10">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
		  		<div class="panel-heading">
		    		<h3 class="panel-title">ABM de Usuarios</h3>
		  		</div>
		  		<div class="panel-body">
		  			<div class="row tools-bar">
		  				<div class="col-md-11">
		  					&nbsp;
		  				</div>
		  				<div class="col-md-1">
		  					<button id="btnNew" type="button" data-loading-text="Espere..." class="btn btn-success btn-xs"><i class="glyphicon glyphicon-plus"></i>&nbsp;Nuevo</button>
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
							                <th>Nombre</th>
							                <th>Nombre de Usuario</th>
							                <th>Es administrador?</th>
							                <th>Tipo de Documento</th>
							                <th>Nro. de Documento</th>
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
		    
		    $("#btnEditUser").click(function(){
		 	
		    	$("#frmEditUser").submit();
		    	
		    	return;
		    });
		    
			$('#modalEditUser').on('hidden.bs.modal', function (e) {
           		
				Login.editUserReset();
           		
           		return;
           	});
		    
		    $("#btnRegistration").click(
           		function(){
           			
           			$("#frmRegistration").submit();
           			
           			return;
           		}		
           	);
		    
           	$('#modalRegistration').on('hidden.bs.modal', function (e) {
           		
           		Login.registrationReset();
           		
           		return;
           	});
           	
           	$("#btnNew").click(function(){
           		$("#modalRegistration").modal("show");
           		
           		return;
           	});
			
			return;
		}	
		
	);
</script>