<div id="modal-client-container" class="container-fluid" style="display:none;">
	<div id="modalClientMessages" class="row hide">
	    <div class="col-md-12">
	        <div id="modalClientAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#modalClientAlertMessages').children('span').eq(0).html('');$('#modalClientMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	</div>
	<div class="row">
  		<form id="frmClient">
  			<input type="hidden" id="clientId" name="clientId" value="">
		<div class="col-md-4">
			<div class="panel panel-default">
			  	<div class="panel-body">
			    	<div class="form-group">
						<label for="name">Nombre y Apellido</label>
					    <input type="text" class="form-control" id="name" name="name" placeholder="Ingrese Nombre y Apellido..." required><!-- &nbsp;<span style="color:RED;font-weight:bold;">*</span> -->
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="documentNumber">N&uacute;mero de Documento</label>
					    <input type="number" class="form-control" id="documentNumber" name="documentNumber" placeholder="Ingrese N&uacute;mero de Documento...">
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="email">e-mail</label>
					    <input type="email" class="form-control" id="email" name="email" placeholder="Ingrese e-mail...">
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="companyPhone">Tel&eacute;fono</label>
					    <input type="text" class="form-control" id="companyPhone" name="companyPhone" placeholder="Ingrese N&uacute;mero de tel&eacute;fono...">
					    <div class="help-block with-errors"></div>
					</div>
			  	</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="panel panel-default">
			  	<div class="panel-body">
					<div class="form-group">
						<label for="companyAddress">Domicilio</label>
					    <input type="text" class="form-control" id="companyAddress" name="companyAddress" placeholder="Ingrese domicilio..." required>
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="nearStreets">Entre Calles</label>
					    <input type="text" class="form-control" id="nearStreets" name="nearStreets" placeholder="Ingrese entre calles...">
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="companyCity">Localidad/Barrio</label>
					    <input type="text" class="form-control" id="companyCity" name="companyCity" placeholder="Ingrese localidad/barrio..." required>
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="companyType">Tipo de Comercio</label>
					    <input type="text" class="form-control" id="companyType" name="companyType" placeholder="Ingrese tipo de comercio...">
					    <div class="help-block with-errors"></div>
					</div>
			  	</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="form-group">
						<label for="phone">Tel&eacute;fono Particular</label>
					    <input type="text" class="form-control" id="phone" name="phone" placeholder="Ingrese N&uacute;mero de tel&eacute;fono...">
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="address">Domicilio Particular</label>
					    <input type="text" class="form-control" id="address" name="address" placeholder="Ingrese domicilio...">
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="city">Localidad/Barrio Particular</label>
					    <input type="text" class="form-control" id="city" name="city" placeholder="Ingrese localidad/barrio...">
					    <div class="help-block with-errors"></div>
					</div>
			  	</div>
			</div>
		</div>
		</form>
	</div>
</div>


<script>

	$(document).ready(
		function(){
			$("#btnClient").click(function(){
				var c = 0;
				
				$("#frmClient").on('invalid.bs.validator', 
					function(e){
					    c++;
						
						return;
				});
				
				$("#frmClient").validator('validate');
				
				if(c == 0){
					// si esta todo ok entonces doy de alta ...
					Client.add();
				}
				
		    	return;
		    });
		    
			$('#modalClient').on('hidden.bs.modal', function (e) {
           		
				Client.resetModal();
           		
           		return;
           	});
			
			return;
		}		
	);

</script>