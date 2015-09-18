<div class="col-md-1">
	&nbsp;
</div>
<div class="col-md-10">
	<div id="payrollCollectItemMessages" class="row hide">
	    <div class="col-md-12">
	        <div id="payrollCollectItemAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#payrollCollectItemAlertMessages').children('span').eq(0).html('');$('#payrollCollectItemMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
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
								<label for="optPayrollPeriod">Seleccionar per&iacute;odo</label>
				            	<select id="optPayrollPeriod" class="form-control">
				            	
								</select>
				              	<div class="help-block with-errors"></div>
					        </div>
						</div>
						<div class="col-md-4">
							<label for="">&nbsp;</label><br>
							<button id="btnAccept" class="btn btn-success"><i class="glyphicon glyphicon-cog"></i>&nbsp;Procesar</button>&nbsp;
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
			<form id="frmPayrollCollectItem" method="get">
				<input type="hidden" id="payrollCollectItemId" name="payrollCollectItemId">
			</form>
			<div class="panel panel-default">
		  		<div class="panel-heading">
		    		<h3 class="panel-title"><i class="fa fa-calculator fa-fw"></i> Liquidaci&oacute;n de Cobradores</h3>
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
							                <th>Zona</th>
							                <th>Cobrador</th>
							                <th>Cantidad de tarjetas</th>
							                <th>Cobrado Bruto</th>
							                <th>Comisi&oacute;n</th>
							                <th>Cobrado Neto</th>
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