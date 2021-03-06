<style>
	
	.payday.active, .payday:active {
    	background-color: #398439;
    }
}

</style>

<div id="modal-payment-container" class="container-fluid" style="display:none;">
	<div id="modalPaymentMessages" class="row hide">
	    <div class="col-md-12">
	        <div id="modalPaymentAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#modalPaymentAlertMessages').children('span').eq(0).html('');$('#modalPaymentMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	</div>
	<div class="row">
		<div class="col-md-7">
			&nbsp;
		</div>
		<div class="col-md-3">
			<h4><span class="label label-primary"><i class="fa fa-key"></i>&nbsp;N&uacute;mero de Cr&eacute;dito:&nbsp;<span id="creditNumberSpan" class="badge"></span></span></h4>
		</div>
		<div class="col-md-2">
			&nbsp;
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			&nbsp;
		</div>
	</div>
	<div class="row">
		<div class="col-md-3">
			&nbsp;
		</div>
		<div class="col-md-6">
			<div class="panel panel-default">
  				<div class="panel-body">
  					<form id="frmPayment">
  						<input type="hidden" id="paymentBillId" name="paymentBillId">
					<div class="form-group">
						<label for="paymentDateValue">Fecha de Pago</label>
		            	<div class='input-group date' id='paymentDate'>
		                	<input type='text' class="form-control" id="paymentDateValue" name="paymentDateValue" placeholder="Ingrese fecha..." required/>
		                  	<span class="input-group-addon">
		                    	<span class="glyphicon glyphicon-calendar"></span>
		                  	</span>
		              	</div>
		              	<div class="help-block with-errors"></div>
			        </div>
					<div class="form-group">
						<label for="paymentAmount">Importe</label>
						<div class="input-group">
					    	<span class="input-group-addon">$</span>
					    	<input type="text" class="form-control" id="paymentAmount" name="paymentAmount" placeholder="Ingrese Importe..." required>
					    </div>
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="paymentCollectorId">Cobrador / Zona</label>
					    <input type="number" class="form-control" id="paymentCollectorId" name="paymentCollectorId" placeholder="Ingrese cobrador/zona..." min="1" required>
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="paymentCollectorId">Es pago de Vendedor?</label>
					    <input type="checkbox" id="traderPayment" name="traderPayment" style="cursor:pointer;">
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