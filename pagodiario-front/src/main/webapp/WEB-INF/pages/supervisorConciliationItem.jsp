<div class="col-md-1">
	&nbsp;
</div>
<div class="col-md-10">
	<div id="conciliationItemMessages" class="row hide">
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	    <div class="col-md-8">
	        <div id="conciliationItemAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#conciliationItemAlertMessages').children('span').eq(0).html('');$('#conciliationItemMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	</div>
	<form id="frmConciliationItemSup" action="${pageContext.request.contextPath}/controller/html/conciliationItem/export/supervisor" method="post">
		<input type="hidden" id="payrollItemId" name="payrollItemId" value="${payrollItemId}">
	</form>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
		  		<div class="panel-heading">
		    		<h3 class="panel-title">Detalle de liquidaci&oacute;n por Supervisor</h3>
		  		</div>
		  		<div class="panel-body">
		  			<div class="row">
						<div clasS="col-md-12">
							<ol class="breadcrumb">
							  <li><a href="${pageContext.request.contextPath}/controller/html/payroll/index">Liquidaci&oacute;n</a></li>
							  <li><a href="${pageContext.request.contextPath}/controller/html/payrollDetail/supervisor/index?payrollId=${payrollId}">Detalle de liquidaci&oacute;n Supervisores</a></li>
							  <li class="active">Detalle de liquidaci&oacute;n por Supervisor</li>
							</ol>
						</div>
					</div>
					<div class="row">
						<div class="col-md-10">
							&nbsp;
						</div>
						<div class="col-md-2">
							<button id="btnExportLiqSup" type="button" data-loading-text="Espere..." 
								class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-print"></i>&nbsp;Imprimir</button>&nbsp;
						</div>
					</div>
		    		<div class="row">
		    			<div class="col-md-2">
		    				&nbsp;
		    			</div>
		  				<div class="col-md-8">
		  					<div class="table-responsive">
								<table id="tTraderConcialiationItem" class="table table-bordered">
						            <tr style="background-color: #f9f9f9;">
						            	<td colspan="3" class="centered title-td">${supervisorName}</td>
						            </tr>
						            <tr>
						            	<td colspan="3" class="centered title-td">DESDE EL ${fromDate} HASTA ${toDate}</td>
						            </tr>
								</table>							
							</div>
		  				</div>
		  				<div class="col-md-2">
		    				&nbsp;
		    			</div>
		  			</div>
		    		<div class="row">
		    			<div class="col-md-2">
		    				&nbsp;
		    			</div>
						<div class="col-md-8">
							<div class="table-responsive">
								<table id="tConciliationItemResult" class="table table-condensed display">
									<thead>
							            <tr>
							            	<th>ID</th>
							            	<th>Vendedor</th>
							                <th>Cr&eacute;ditos</th>
							                <th>Devoluciones</th>
							                <th>Premios</th>
							                <th>Bajas</th>
							                <th>Total</th>
							            </tr>
							        </thead>
							        <tfoot>
							            <tr>
							                <th colspan="2" style="text-align:left">TOTALES</th>
							                <th style="background-color:#fcf8e3;">${totalCredit}</th>
							                <th style="background-color:#fcf8e3;">${totalDev}</th>
							                <th style="background-color:#fcf8e3;">${totalBonus}</th>
							                <th style="background-color:#fcf8e3;">${totalReduction}</th>
							                <th style="background-color:#fcf8e3;">${totalAmount}</th>
							            </tr>
							        </tfoot>
								</table>							
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
</div>
<div class="col-md-1">
	&nbsp;
</div>


<script>
	$(document).ready(
		function(){
			
			var imgCheckUrl = "${pageContext.request.contextPath}/public/images/checkmark-outline_32x32.png";
			
			ConciliationItem.initSupervisorDataTable(imgCheckUrl, "${payrollItemId}");
			
			ConciliationItem.initSupervisor();
		    
			return;
		}	
		
	);
</script>