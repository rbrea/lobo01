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
		    		<i class="glyphicon glyphicon-play"></i>&nbsp;Generar vouchers de Descuento
		    		<div class="pull-right">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                            	<i class="fa fa-chevron-down"></i>
                            </button>
                            <ul class="dropdown-menu pull-right" role="menu">
                                <li>
                                	<a id="doPrintVoucher" href="javascript:void(0);">
                                    	<i class="glyphicon glyphicon-print"></i>&nbsp;&nbsp;Imprimir
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
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
					<div class="row tools-bar">
						<div class="col-md-8">
							&nbsp;
						</div>
						<div class="col-md-4">
							<!--  
							<button id="btnAccept" type="button" data-loading-text="Espere..." class="btn btn-success"><i class="glyphicon glyphicon-print"></i>&nbsp;Generar</button>&nbsp;
							-->
							<button id="btnVoucherSearch" type="button" data-loading-text="Espere..." class="btn btn-success"><i class="glyphicon glyphicon-search"></i>&nbsp;Buscar</button>
		  					&nbsp;
		  					<button id="btnVoucherReset" type="button" data-loading-text="Espere..." class="btn btn-default"><i class="glyphicon glyphicon-refresh"></i>&nbsp;Limpiar</button>
						</div>
					</div>
					<div class="row">
		  				<div class="col-md-12">
		  					&nbsp;
		  				</div>
		  			</div>
		  			<div class="row">
						<div class="col-md-12">
							<div class="table-responsive">
								<table id="tVoucherResult" class="table table-condensed display">
									<thead>
							            <tr>
							            	<th>ID Voucher</th>
							                <th>Vendedor</th>
							                <th>Zona</th>
							                <th>Nombre Cliente</th>
							                <th>Info Cliente</th>
							                <th>Monto Descuento</th>
							                <th>Monto Cuota Diaria</th>
							                <th>Vencimiento</th>
							            </tr>
							        </thead>
								</table>							
							</div>
						</div>
					</div>	
		  		</div>
			</div>	
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
