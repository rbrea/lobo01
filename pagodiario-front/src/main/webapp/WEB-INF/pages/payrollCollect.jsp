<div class="col-md-1">
	&nbsp;
</div>
<div class="col-md-10">
	<div id="payrollCollectMessages" class="row hide">
	    <div class="col-md-12">
	        <div id="payrollCollectAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#payrollCollectAlertMessages').children('span').eq(0).html('');$('#payrollCollectMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
		  		<div class="panel-heading">
		    		<h3 class="panel-title"><i class="fa fa-calendar fa-fw"></i> Procesar Per&iacute;odo</h3>
		  		</div>
		  		<div class="panel-body">
		    		<div class="row">
		    			<div class="col-md-2">
							&nbsp;
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="payrollCollectDateValue">Fecha a Liquidar</label>
				                <div class='input-group date' id="payrollCollectDate">
				                    <input type='text' class="form-control not-writable" id="payrollCollectDateValue" name="payrollCollectDateValue" placeholder="Ingrese fecha" data-error="Requerido" required/>
				                    <span class="input-group-addon">
				                        <span class="glyphicon glyphicon-calendar"></span>
				                    </span>
				                </div>
				              	<div class="help-block with-errors"></div>
					        </div>
						</div>
						<div class="col-md-4">
							<label for="">&nbsp;</label><br>
							<button id="btnAcceptPayrollCollect" class="btn btn-success"><i class="glyphicon glyphicon-cog"></i>&nbsp;Procesar</button>&nbsp;
						</div>
						<div class="col-md-2">
							&nbsp;
						</div>
					</div>
		  		</div>
			</div>	
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<form id="frmPayrollCollect" method="get">
				<input type="hidden" id="payrollCollectId" name="payrollCollectId">
			</form>
			<div class="panel panel-default">
		  		<div class="panel-heading">
		    		<h3 class="panel-title"><i class="fa fa-calculator fa-fw"></i> Liquidaciones de Cobradores</h3>
		  		</div>
		  		<div class="panel-body">
		    		<div class="row">
						<div class="col-md-12">
							<div class="table-responsive">
								<table id="tPayrollCollectResult" class="table table-condensed display">
									<thead>
							            <tr>
							            	<th>ID</th>
							                <th>Fecha</th>
							                <th>Cantidad de tarjetas</th>
							                <th>Total Facturado</th>
							                <th>Total Cobrado</th>
							                <th>Total Comisiones</th>
							                <th>Estado</th>
							                <th>Acciones</th>
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
			
			var imgCheckUrl = "${pageContext.request.contextPath}/public/images/checkmark-outline_32x32.png";
			
			PayrollCollect.init();
			PayrollCollect.initDataTable(imgCheckUrl);
		    
			return;
		}	
		
	);
</script>