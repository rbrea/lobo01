<div id="modal-collector-container" class="container-fluid" style="display:none;">
	<div id="modalCollectorMessages" class="row hide">
	    <div class="col-md-12">
	        <div id="modalCollectorAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#modalCollectorAlertMessages').children('span').eq(0).html('');$('#modalCollectorMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
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
  					<form id="frmCollector">
  						<input type="hidden" id="collectorId" name="id" value="">
  						<div class="form-group">
							<label for="collectorZone">Zona</label>
							<div class='input-group'>
								<span class="input-group-addon">
			                        <span class="fa fa-map-o"></span>
			                    </span>
						    	<input type="number" class="form-control" id="collectorZone" name="collectorZone" placeholder="Ingrese Zona..." min="1" required>
						    </div>
						    <div class="help-block with-errors"></div>
						</div>
						<div class="form-group">
							<label for="collectorDescription">Descripci&oacute;n</label>
							<div class='input-group'>
								<span class="input-group-addon">
			                        <span class="fa fa-file-text"></span>
			                    </span>
						    	<input type="text" class="form-control" id="collectorDescription" name="collectorDescription" placeholder="Ingrese Descripci&oacute;n..." data-maxlength="140" required>
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
