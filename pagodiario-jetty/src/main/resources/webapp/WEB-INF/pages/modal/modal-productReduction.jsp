<div id="modal-productReduction-container" class="container-fluid" style="display:none;">
	<div id="modalProductReductionMessages" class="row hide">
	    <div class="col-md-12">
	        <div id="modalProductReductionAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#modalProductReductionAlertMessages').children('span').eq(0).html('');$('#modalProductReductionMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
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
  					<form id="frmProductReduction">
  						<input type="hidden" id="productReductionBillId" name="productReductionBillId">
					<div class="form-group">
						<label for="productReductionDateValue">Fecha</label>
		            	<div class='input-group date' id='productReductionDate'>
		                	<input type='text' class="form-control" id="productReductionDateValue" name="productReductionDateValue" placeholder="Ingrese fecha..." required/>
		                  	<span class="input-group-addon">
		                    	<span class="glyphicon glyphicon-calendar"></span>
		                  	</span>
		              	</div>
		              	<div class="help-block with-errors"></div>
			        </div>
					<div class="form-group">
						<label for="productReductionAmount">Importe</label>
						<div class="input-group">
					    	<span class="input-group-addon">$</span>
					    	<input type="text" class="form-control" id="productReductionAmount" name="productReductionAmount" placeholder="Ingrese Importe..." required>
					    </div>
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="productReductionObservations">Observaciones</label>
						<div class="input-group">
					    	<textarea id="productReductionObservations" name="productReductionObservations" class="form-control" rows="4" cols="38" maxlength="240"></textarea>
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