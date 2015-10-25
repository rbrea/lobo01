<div class="col-md-1">
	&nbsp;
</div>
<div class="col-md-10">
	<div id="voucherMessages" class="row hide">
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	    <div class="col-md-8">
	        <div id="voucherAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#voucherAlertMessages').children('span').eq(0).html('');$('#voucherMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div id="pnlVoucher" class="panel panel-default">
		  		<div class="panel-heading">
		    		<h3 class="panel-title"><i class="fa fa-file-text-o fa-fw"></i> Generar voucher de Descuento</h3>
		  		</div>
		  		<div class="panel-body">
		  			<form id="frmVoucher" action="${pageContext.request.contextPath}/controller/html/voucher/export" method="post">
		  			<div class="row">
		  				<div class="form-group col-md-3">
		  					<div class="form-group">
		  						<label for="voucherDateValue">Fecha de voucher</label>
				                <div class='input-group date' id='voucherDate'>
				                    <input type='text' class="form-control not-writable" id="voucherDateValue" name="voucherDateValue" placeholder="Ingrese fecha de Voucher..." data-required-error="Requerido" required/>
				                    <span class="input-group-addon">
				                        <span class="glyphicon glyphicon-calendar"></span>
				                    </span>
				                </div>
				            </div>
		  				</div>
		  				<div class="col-md-8">
		  					&nbsp;
		  				</div>
		  			</div>
		  			</form>
		  		</div>
			</div>	
		</div>
	</div>
	<div class="row" >
		<div class="col-md-8">
			&nbsp;
		</div>
		<div class="col-md-4">
			<button id="btnAccept" type="button" data-loading-text="Espere..." class="btn btn-success"><i class="glyphicon glyphicon-print"></i>&nbsp;Generar</button>&nbsp;
		</div>
	</div>
</div>
<div class="col-md-1">
	&nbsp;
</div>


<script>
	$(document).ready(
		function(){
			
			Voucher.init();
			
			return;
		}	
		
	);
</script>
