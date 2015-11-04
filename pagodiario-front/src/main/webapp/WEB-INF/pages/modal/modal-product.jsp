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
							<div class='input-group'>
								<span class="input-group-addon">
			                        <span class="fa fa-barcode"></span>
			                    </span>
						    	<input type="text" class="form-control" id="productCode" name="productCode" placeholder="Ingrese C&oacute;digo de Producto..." data-maxlength="40" required>
						    </div>
						    <div class="help-block with-errors"></div>
						</div>
						<div class="form-group">
							<label for="productDescription">Descripci&oacute;n</label>
							<div class='input-group'>
								<span class="input-group-addon">
			                        <span class="fa fa-file-text"></span>
			                    </span>
						    	<input type="text" class="form-control" id="productDescription" name="productDescription" placeholder="Ingrese Descripci&oacute;n..." data-maxlength="140" required>
						    </div>
						    <div class="help-block with-errors"></div>
						</div>
						<div class="form-group">
							<label for="productPrice">Precio Unitario</label>
							<div class="input-group">
								<span class="input-group-addon">$</span>
							    <input type="text" class="form-control" id="productPrice" name="productPrice" placeholder="Ingrese Precio unitario...">
							</div>
						    <div class="help-block with-errors"></div>
						</div>
						<div class="form-group">
							<label for="dailyInstallment">Cuota Diaria</label>
							<div class="input-group">
								<span class="input-group-addon">$</span>
							    <input type="text" class="form-control" id="dailyInstallment" name="dailyInstallment" placeholder="Ingrese Cuota Diaria...">
							</div>
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
