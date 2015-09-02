<div class="col-md-1">
	&nbsp;
</div>
<div class="col-md-10">
	<div id="payrollMessages" class="row hide">
	    <div class="col-md-12">
	        <div id="payrollAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#payrollAlertMessages').children('span').eq(0).html('');$('#payrollMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
		  		<div class="panel-heading">
		    		<h3 class="panel-title">Procesar Per&iacute;odo</h3>
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
			<form id="frmPayroll" method="get">
				<input type="hidden" id="payrollId" name="payrollId">
			</form>
			<div class="panel panel-default">
		  		<div class="panel-heading">
		    		<h3 class="panel-title">Liquidaciones</h3>
		  		</div>
		  		<div class="panel-body">
		    		<div class="row">
						<div class="col-md-12">
							<div class="table-responsive">
								<table id="tPayrollResult" class="table table-condensed display">
									<thead>
							            <tr>
							            	<th>ID Liquidaci&oacute;n</th>
							                <th>Fecha Desde</th>
							                <th>Fecha Hasta</th>
							                <th>A Cobrar</th>
							                <th>Descuentos</th>
							                <th>Total</th>
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
			
			Payroll.init();
			Payroll.initDataTable(imgCheckUrl);
		    
			return;
		}	
		
	);
</script>