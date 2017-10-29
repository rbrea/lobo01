<div id="payment-detail-container" class="container-fluid" style="display:none;">
	<div class="row">
		<div class="col-md-12">
			<div class="table-responsive">
				<table id="tPaymentDetailResult" class="table table-condensed display">
					<thead>
			            <tr>
			            	<th>Id Cobrador</th>
			                <th>Fecha</th>
			                <th>Nro. Cr&eacute;dito</th>
			                <th>Importe</th>
			            </tr>
			        </thead>
				</table>							
			</div>
		</div>
	</div>
</div>


<div class="col-md-1">
	&nbsp;
</div>
<div class="col-md-10">
	<div id="paymentMessages" class="row hide">
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	    <div class="col-md-8">
	        <div id="paymentAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#paymentAlertMessages').children('span').eq(0).html('');$('#paymentMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div id="pnlPaymentAdd" class="panel panel-default">
		  		<div class="panel-heading">
		    		<h3 class="panel-title"><i class="fa fa-chevron-right fa-fw"></i> Ingresar Pagos</h3>
		  		</div>
		  		<div class="panel-body">
		  			<form id="frmPaymentAdd">
		  			<div class="row">
						<div class="form-group col-md-2">
							<label for="paymentDateValue">Fecha de Pago</label>
						</div>
						<div class="form-group col-md-3">
							<div class='input-group date' id='paymentDate'>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                    <input type='text' class="form-control" id="paymentDateValue" name="paymentDateValue" placeholder="Fecha de Pago" data-error="Requerido" required/>
			                </div>
							<div class="help-block with-errors"></div>
						</div>
						<div class="form-group col-md-2">
							&nbsp;
						</div>
						<div class="col-md-4">
		  					&nbsp;
		  				</div>
					</div>
		  			<div class="row">
						<div class="form-group col-md-2">
							<label for="bCollectorId">Zona / Cobrador</label>
						</div>
		  				<div class="col-md-1">
							<input type="hidden" id="zone" name="zone">
							<div class="form-group">
				                <input type="number" class="form-control " id="bCollectorId" name="bCollectorId" placeholder="ID" min="1" data-error="Requerido" required>
								<div class="help-block with-errors"></div>
				            </div>		  					
		  				</div>
		  				<div class="col-md-4">
							<div class="form-group">
		  						<div class="input-group">
				                	<input type="text" class="form-control not-writable" id="bCollectorDescription" 
				                			name="bCollectorDescription" placeholder="Zona / Descripci&oacute;n" data-error="Requerido" required>
				                	<span id="btnPaymentSearchCollector" class="input-group-addon lov"><i class="glyphicon glyphicon-search"></i></span>
								</div>
								<div class="help-block with-errors"></div>
				            </div>		  					
		  				</div>
						<div class="form-group col-md-2">
							&nbsp;
						</div>
						<div class="col-md-3">
		  					<button id="btnPaymentAdd" type="button" 
		  							class="btn btn-info btn-sm"><i class="glyphicon glyphicon-plus-sign"></i>&nbsp;Agregar</button>&nbsp;
	  						<button id="btnPaymentRemove" type="button"
	  							class="btn btn-danger btn-sm"><i class="glyphicon glyphicon-minus-sign"></i>&nbsp;Quitar</button>
		  				</div>
					</div>
		  			<div class="row">
		  				<div class="col-md-12">
	  						<div id="basePaymentRow" class="row hide">
		  						<div class="form-group col-md-2">
		  							<label id="lblPaymentRow_X" for="creditNumber_X">Pago #X</label>
		  						</div>
		  						<div class="form-group col-md-3">
		  							<div class="input-group">
		  								<div class="input-group-addon"><i class="glyphicon glyphicon-barcode"></i></div>
										<input type="number" class="form-control " id="creditNumber_X" placeholder="Nro. de cr&eacute;dito" required>
									</div>
									<div class="help-block with-errors"></div>
								</div>
								<div class="form-group col-md-2">
									<div class="input-group">
										<div class="input-group-addon">$</div>
										<input type="number" style="text-align: right;" class="form-control " id="paymentAmount_X" placeholder="$ Importe" required>
									</div>
									<div class="help-block with-errors"></div>
								</div>
								<div class="form-group col-md-2">
									Pago Vendedor?&nbsp;<input type="checkbox" id="traderPayment_X" name="traderPayment_X" style="cursor:pointer;">
								</div>
								<div class="form-group col-md-5">
									<span id="paymentMessageError_X" class="hide" style="color:RED;">&nbsp;</span>
									<div class="help-block with-errors"></div>
								</div>
	  						</div>
	  						<div id="paymentRow_0" class="row">
		  						<div class="form-group col-md-2" >
		  							<label id="lblPaymentRow_0" for="creditNumber_0">Pago #1</label>
		  						</div>
		  						<div class="form-group col-md-3">
		  							<div class="input-group">
		  								<div class="input-group-addon"><i class="glyphicon glyphicon-barcode"></i></div>
										<input type="number" class="form-control " id="creditNumber_0" placeholder="Nro. de cr&eacute;dito" required>
									</div>
									<div class="help-block with-errors"></div>
								</div>
								<div class="form-group col-md-2">
									<div class="input-group">
										<div class="input-group-addon">$</div>
										<input type="number" style="text-align: right;" class="form-control " id="paymentAmount_0" placeholder="$ Importe" required>
									</div>
									<div class="help-block with-errors"></div>
								</div>
								<div class="form-group col-md-2">
									Pago Vendedor?&nbsp;<input type="checkbox" id="traderPayment_0" name="traderPayment_0" style="cursor:pointer;">
								</div>
								<div class="form-group col-md-5">
									<span id="paymentMessageError_0" class="hide" style="color:RED;">&nbsp;</span>
									<div class="help-block with-errors"></div>
								</div>
	  						</div>
		  				</div>
		  			</div>
		    		<div class="row">
						<div class="col-md-12">
						</div>
					</div>
		  			</form>
		  			<div class="row">
						<div class="col-md-8">
							&nbsp;
						</div>
						<div class="col-md-2">
							<label for="totalToPay">Total a Pagar</label>
							<div class="input-group">
								<div class="input-group-addon">$</div>
    							<input type="text" style="text-align: right;" class="form-control" id="totalToPay" placeholder="0.00" value="0.00" readonly>
    						</div>
						</div>
						<div class="col-md-2">
							&nbsp;
						</div>
					</div>
		  		</div>
			</div>	
		</div>
	</div>
	<div class="row" >
		<div class="col-md-8">
			&nbsp;
		</div>
		<div class="col-md-4">
			<button id="btnAccept" type="button" data-loading-text="Espere..." class="btn btn-success"><i class="glyphicon glyphicon-play-circle"></i>&nbsp;Aceptar</button>&nbsp;
			<button id="btnPaymentScreenReset" type="button" data-loading-text="Espere..." class="btn btn-default"><i class="glyphicon glyphicon-refresh"></i>&nbsp;Limpiar</button>
		</div>
	</div>
</div>
<div class="col-md-1">
	&nbsp;
</div>


<script>
	$(document).ready(
		function(){
			
			Payment.init();
			/*
			$('input[name="q"]').autoComplete({
			    source: function(term, response){
			        $.getJSON('/some/ajax/url/', { q: term }, function(data){ response(data); });
			    }
			});*/
			
			return;
		}	
		
	);
</script>