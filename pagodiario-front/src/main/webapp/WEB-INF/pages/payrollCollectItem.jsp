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
			<form id="frmPayrollCollectItem" method="get">
				<input type="hidden" id="payrollCollectId" name="payrollCollectId" value="${payrollId}">
				<input type="hidden" id="payrollCollectItemId" name="payrollCollectItemId">
			</form>
			<div class="panel panel-default">
		  		<div class="panel-heading">
		    		<h3 class="panel-title"><i class="fa fa-calculator fa-fw"></i> Liquidaci&oacute;n de Cobradores</h3>
		  		</div>
		  		<div class="panel-body">
		  			<div class="row">
						<div clasS="col-md-12">
							<ol class="breadcrumb">
							  <li><a href="${pageContext.request.contextPath}/controller/html/payrollcollect/index">Liquidaci&oacute;n de Cobradores</a></li>
							  <li class="active">Detalle de liquidaci&oacute;n de Cobradores</li>
							</ol>
						</div>
					</div>
		    		<div class="row" >
		  				<div class="col-md-12">
		  					&nbsp;
		  				</div>
		  			</div>
		    		<div class="row">
						<div class="col-md-12">
							<div class="table-responsive">
								<table id="tPayrollCollectItemResult" class="table table-condensed display">
									<thead>
							            <tr>
							            	<th>ID</th>
							                <th>Zona</th>
							                <th>Cobrador</th>
							                <th>Cantidad de tarjetas</th>
							                <th>Total a Cobrar</th>
							                <th>Cobrado Bruto</th>
							                <th>Comisi&oacute;n</th>
							                <th>Cobrado Neto</th>
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
			
			PayrollCollect.initItemDataTable(imgCheckUrl, $("#payrollCollectId").val());
		    
			return;
		}	
		
	);
</script>