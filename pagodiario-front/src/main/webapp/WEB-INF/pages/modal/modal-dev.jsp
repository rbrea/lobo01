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
		<div class="col-md-2">
			&nbsp;
		</div>
		<div class="col-md-8">
			<div class="panel panel-default">
  				<div class="panel-body">
  					<form id="frmDev">
  						<input type="hidden" id="devBillId" name="devBillId">
					<div class="form-group">
						<label for="devDateValue">Fecha</label>
		            	<div class='input-group date' id='devDate'>
		                	<input type='text' class="form-control input-sm not-writable" id="devDateValue" name="devDateValue" placeholder="Ingrese fecha..." required/>
		                  	<span class="input-group-addon">
		                    	<span class="glyphicon glyphicon-calendar"></span>
		                  	</span>
		              	</div>
		              	<div class="help-block with-errors"></div>
			        </div>
					<div class="form-group">
						<label for="devAmount">Importe</label>
						<div class="input-group">
					    	<span class="input-group-addon">$</span>
					    	<input type="text" class="form-control" id="devAmount" name="devAmount" placeholder="Ingrese Importe..." required>
					    </div>
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="devObservations">Observaciones</label>
						<div class="input-group">
					    	<textarea id="devObservations" name="devObservations" class="form-control" rows="4" cols="38" maxlength="240"></textarea>
					    </div>
					    <div class="help-block with-errors"></div>
					</div>
					</form>
				</div>
			</div>
		</div>
		<div class="col-md-2">
			&nbsp;
		</div>
	</div>
</div>