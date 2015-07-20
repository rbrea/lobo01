<div id="modal-product-container" class="container-fluid" style="display:none;">
	<div id="modalProductMessages" class="row hide">
	    <div class="col-md-12">
	        <div id="modalProductAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#modalProductAlertMessages').children('span').eq(0).html('');$('#modalProductMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	</div>
	<div class="row">
		<div class="col-md-3">
			&nbsp;
		</div>
		<div class="col-md-6">
			<div class="panel panel-default">
  				<div class="panel-body">
  					<form id="frmProduct">
  						<input type="hidden" id="productId" name="id" value="">
						<div class="form-group">
							<label for="productCode">C&oacute;digo de Producto</label>
						    <input type="text" class="form-control" id="productCode" name="productCode" placeholder="Ingrese C&oacute;digo de Producto..." data-maxlength="40" required>
						    <div class="help-block with-errors"></div>
						</div>
						<div class="form-group">
							<label for="productDescription">Descripci&oacute;n</label>
						    <input type="text" class="form-control" id="productDescription" name="productDescription" placeholder="Ingrese Descripci&oacute;n..." data-maxlength="140" required>
						    <div class="help-block with-errors"></div>
						</div>
						<div class="form-group">
							<label for="productPrice">Precio Unitario</label>
						    <input type="number" class="form-control" id="productPrice" name="productPrice" placeholder="Ingrese Precio unitario...">
						    <div class="help-block with-errors"></div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="col-md-3">
			&nbsp;
		</div>
	</div>
</div>
