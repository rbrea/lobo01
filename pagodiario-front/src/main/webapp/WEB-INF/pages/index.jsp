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

<div class="col-md-12">
	<div class="row">
		<div class="col-md-12">
			&nbsp;
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			&nbsp;
		</div>
		<div class="col-md-4 centered">
			<span style="font-weight: bold;">
				SGPD - Sistema de Gesti&oacute;n de Pago Diario - versi&oacute;n 1.0
			</span>
		</div>
		<div class="col-md-4">
			&nbsp;
		</div>	
	</div>
