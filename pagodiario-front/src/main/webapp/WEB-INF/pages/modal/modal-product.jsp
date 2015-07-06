<div class="modal fade" id="modalProduct" tabindex="-1" role="dialog" aria-labelledby="modalProductLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modalProductLabel">Producto</h4>
      </div>
      <div class="modal-body">
		<form id="frmProduct" 
				method="POST" data-toggle="validator">
			<input type="hidden" id="productId" name="id" value="">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default">
						  	<div class="panel-body">
						    	<div class="form-group">
									<label for="productCode">C&oacute;digo de Producto</label>
								    <input type="text" class="form-control" id="productCode" name="productCode" placeholder="Ingrese C&oacute;digo de Producto..." data-minlength="40" required>
								    <div class="help-block with-errors"></div>
								</div>
								<div class="form-group">
									<label for="productDescription">Descripci&oacute;n</label>
								    <input type="text" class="form-control" id="productDescription" name="productDescription" placeholder="Ingrese Descripci&oacute;n..." data-minlength="140" required>
								    <div class="help-block with-errors"></div>
								</div>
								<div class="form-group">
									<label for="productPrice">Precio Unitario</label>
								    <input type="number" class="form-control" id="productPrice" name="productPrice" placeholder="Ingrese Precio unitario...">
								    <div class="help-block with-errors"></div>
								</div>
						  	</div>
						</div>
					</div>
				</div>
			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        <button id="btnProduct" type="button" class="btn btn-primary">Guardar</button>
      </div>
    </div>
  </div>
</div>

<script>

	$(document).ready(
		function(){
			$("#btnProduct").click(function(){
				var c = 0;
				
				$("#frmProduct").on('invalid.bs.validator', 
					function(e){
					    c++;
						
						return;
				});
				
				$("#frmProduct").validator('validate');
				
				if(c == 0){
					// si esta todo ok entonces doy de alta ...
					Product.add();
				}
				
		    	return;
		    });
		    
			$('#modalProduct').on('hidden.bs.modal', function (e) {
           		
				Product.resetModal();
           		
           		return;
           	});
			
			return;
		}		
	);

</script>