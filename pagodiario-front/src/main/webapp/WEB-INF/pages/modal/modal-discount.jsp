<div id="modal-discount-container" class="container-fluid" style="display:none;">
	<div id="modalDiscountMessages" class="row hide">
	    <div class="col-md-12">
	        <div id="modalDiscountAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#modalDiscountAlertMessages').children('span').eq(0).html('');$('#modalDiscountMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
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
  					<form id="frmDiscount">
  						<input type="hidden" id="discountBillId" name="discountBillId">
					<div class="form-group">
						<label for="discountDateValue">Fecha</label>
		            	<div class='input-group date' id='discountDate'>
		                	<input type='text' class="form-control  not-writable" id="discountDateValue" name="discountDateValue" placeholder="Ingrese fecha..." required/>
		                  	<span class="input-group-addon">
		                    	<span class="glyphicon glyphicon-calendar"></span>
		                  	</span>
		              	</div>
		              	<div class="help-block with-errors"></div>
			        </div>
					<div class="form-group">
						<label for="discountAmount">Importe</label>
						<div class="input-group">
					    	<span class="input-group-addon">$</span>
					    	<input type="text" class="form-control" id="discountAmount" name="discountAmount" placeholder="Ingrese Importe..." required>
					    	<span class="input-group-addon">.00</span>
					    </div>
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="discountInstallmentAmount">Cuota Diaria</label>
						<div class="input-group">
					    	<span class="input-group-addon">$</span>
					    	<input type="text" class="form-control" id="discountInstallmentAmount" name="discountInstallmentAmount" placeholder="Ingrese Cuota Diaria..." required>
					    	<span class="input-group-addon">.00</span>
					    </div>
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="discountObservations">Observaciones</label>
						<div class="input-group">
					    	<textarea id="discountObservations" name="discountObservations" class="form-control" rows="4" cols="38" maxlength="240"></textarea>
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


<script>

	$(document).ready(
		function(){
			
			Discount.init();
			
			return;
		}	
		
	);

</script>