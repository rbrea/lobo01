<div id="modal-bonus-container" class="container-fluid" style="display:none;">
	<div id="modalBonusMessages" class="row hide">
	    <div class="col-md-12">
	        <div id="modalBonusAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#modalBonusAlertMessages').children('span').eq(0).html('');$('#modalBonusMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
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
  					<form id="frmBonus">
  						<input type="hidden" id="bonusBillId" name="bonusBillId">
					<div class="form-group">
						<label for="bonusDateValue">Fecha</label>
		            	<div class='input-group date' id='bonusDate'>
		                	<input type='text' class="form-control input-sm not-writable" id="bonusDateValue" name="bonusDateValue" placeholder="Ingrese fecha..." required/>
		                  	<span class="input-group-addon">
		                    	<span class="glyphicon glyphicon-calendar"></span>
		                  	</span>
		              	</div>
		              	<div class="help-block with-errors"></div>
			        </div>
					<div class="form-group">
						<label for="bonusAmount">Importe</label>
						<div class="input-group">
					    	<span class="input-group-addon">$</span>
					    	<input type="text" class="form-control" id="bonusAmount" name="bonusAmount" placeholder="Ingrese Importe..." required>
					    </div>
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="bonusObservations">Observaciones</label>
						<div class="input-group">
					    	<textarea id="bonusObservations" name="bonusObservations" class="form-control" rows="4" cols="38" maxlength="240"></textarea>
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