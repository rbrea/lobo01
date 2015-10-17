<div id="modal-dev-container" class="container-fluid" style="display:none;">
	<div id="modalDevMessages" class="row hide">
	    <div class="col-md-12">
	        <div id="modalDevAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#modalDevAlertMessages').children('span').eq(0).html('');$('#modalDevMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
  				<div class="panel-body">
  					<form id="frmDev">
  						<input type="hidden" id="devBillId" name="devBillId">
  					<div class="row">
  						<div class="col-md-6">
							<div class="form-group">
								<label for="devDateValue">Fecha</label>
				            	<div class='input-group date' id='devDate'>
				                	<input type='text' class="form-control  not-writable" id="devDateValue" name="devDateValue" placeholder="Ingrese fecha..." required/>
				                  	<span class="input-group-addon">
				                    	<span class="glyphicon glyphicon-calendar"></span>
				                  	</span>
				              	</div>
				              	<div class="help-block with-errors"></div>
					        </div>
  						</div>
  						<div class="col-md-6">
  							&nbsp;
  						</div>
  					</div>
  					<div class="row">
  						<div class="col-md-3">
  							<div class="form-group">
								<label for="devProductCount">Cantidad</label>
							    <input type="number" class="form-control" id="devProductCount" name="devProductCount" min="1" placeholder="Cantidad" value="1" required>
							    <div class="help-block with-errors"></div>
							</div>
  						</div>
  						<div class="col-md-9">
  							&nbsp;
  						</div>
  					</div>
  					<div class="row">
						<input type="hidden" id="devProductId" name="devProductId">
						<div class="col-md-3">
		  					<div class="form-group">
		  						<label for="devProductCode">C&oacute;digo</label>
					            <input type="text" class="form-control" id="devProductCode" name="devProductCode" placeholder="C&oacute;digo" data-error="Requerido" required>
								<div class="help-block with-errors"></div>
				            </div>
						</div>
						<div class="col-md-9">
  							<div class="form-group">
		  						<label for="devProductDescription">Producto</label>
		  						<div class="input-group">
					                <input type="text" class="form-control not-writable" id="devProductDescription" name="devProductDescription" placeholder="Producto" data-error="Requerido" required>
									<span id="btnDevSearchProduct" class="input-group-addon lov" onclick="javascript:Dev.showLovProduct();"><i class="glyphicon glyphicon-search"></i></span>		  						
		  						</div>
								<div class="help-block with-errors"></div>
				            </div>
						</div>
					</div>
					<div class="row">
  						<div class="col-md-6">
							<div class="form-group">
								<label for="devInstallment">Cuota Diaria a Restar</label>
								<div class="input-group">
							    	<span class="input-group-addon">$</span>
							    	<input type="text" class="form-control" id="devInstallment" name="devInstallment" placeholder="Ingrese Cuota Diaria a restar..." required>
							    	<span class="input-group-addon">.00</span>
							    </div>
							    <div class="help-block with-errors"></div>
							</div>
  						</div>
  						<div class="col-md-6">
  							&nbsp;
  						</div>
  					</div>
  					<div class="row">
  						<div class="col-md-6">
							<div class="form-group">
								<label for="devAmount">Importe</label>
								<div class="input-group">
							    	<span class="input-group-addon">$</span>
							    	<input type="text" class="form-control" id="devAmount" name="devAmount" placeholder="Ingrese Importe..." required>
							    	<span class="input-group-addon">.00</span>
							    </div>
							    <div class="help-block with-errors"></div>
							</div>
  						</div>
  						<div class="col-md-6">
  							&nbsp;
  						</div>
  					</div>
  					<div class="row">
  						<div class="col-md-12">
							<div class="form-group">
								<label for="devObservations">Observaciones</label>
								<div class="input-group">
							    	<textarea id="devObservations" name="devObservations" class="form-control" rows="2" cols="240" maxlength="240"></textarea>
							    </div>
							    <div class="help-block with-errors"></div>
							</div>  						
  						</div>
  					</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>