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
			<form id="frmPayrollItemCollectExportPdf" method="POST" action="${pageContext.request.contextPath}/controller/html/payrollcollectitem/export/pdf">
				<input type="hidden" id="payrollItemId" name="payrollItemId" value="${payrollId}">
			</form>
			<div class="panel panel-default">
		  		<div class="panel-heading">
		    		<i class="fa fa-calculator fa-fw"></i> Liquidaci&oacute;n de Cobradores
		    		<div class="pull-right">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                            	<i class="fa fa-chevron-down"></i>
                            </button>
                            <ul class="dropdown-menu pull-right" role="menu">
                                <li>
                                	<a href="javascript:void(0);" onclick="javascript:PayrollCollect.exportToPdf();">
                                    	<i class="glyphicon glyphicon-print"></i>&nbsp;&nbsp;Imprimir
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
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
					<div class="row">
						<div class="col-md-1">
							&nbsp;
						</div>
		  				<div class="col-md-4">
		  					<h3><b>Fecha:</b> ${payrollDate}</h3>
		  				</div>
		  				<div class="col-md-7">
							&nbsp;
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
							                <th>Cant tarjetas</th>
							                <th>Cant tarjetas Cobradas</th>
							                <th>Total a Cobrar</th>
							                <th>Cobrado Bruto</th>
							                <th>Comisi&oacute;n</th>
							                <th>Cobrado Neto</th>
							                <th>&nbsp;</th>
							            </tr>
							        </thead>
							        <tfoot>
							            <tr class="odd">
							                <th colspan="3" style="text-align:left">TOTAL</th>
							                <th style="background-color:#f2dede;text-align:center">${totalCards}</th>
							                <th style="background-color:#f2dede;text-align:center">${totalCardsReal}</th>
							                <th style="background-color:#f2dede;text-align:center">${totalToCollect}</th>
							                <th style="background-color:#f2dede;text-align:center">${totalCollectedGross}</th>
							                <th style="background-color:#f2dede;text-align:center">${totalCommission}</th>
							                <th style="background-color:#f2dede;text-align:center">${totalCollectedNet}</th>
							                <th style="background-color:#f2dede;text-align:center">&nbsp;</th>
							            </tr>
							        </tfoot>
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