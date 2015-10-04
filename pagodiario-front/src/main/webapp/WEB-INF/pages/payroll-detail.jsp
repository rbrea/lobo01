<div class="col-md-1">
	&nbsp;
</div>
<div class="col-md-10">
	<div id="payrollDetailMessages" class="row hide">
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	    <div class="col-md-8">
	        <div id="payrollDetailAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#payrollDetailAlertMessages').children('span').eq(0).html('');$('#payrollDetailMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<form id="frmPayrollDetail" method="get">
				<input type="hidden" id="payrollId" name="payrollId" value="${payrollId}">
				<input type="hidden" id="payrollItemId" name="payrollItemId">
				<input type="hidden" id="traderName" name="traderName">
				<input type="hidden" id="totalCollect" name="totalCollect">
				<input type="hidden" id="totalDiscount" name="totalDiscount">
				<input type="hidden" id="totalTrader" name="totalTrader">
			</form>
			<div class="panel panel-default">
		  		<div class="panel-heading">
		    		<i class="glyphicon glyphicon-stats"></i>&nbsp;&nbsp;Detalle de liquidaci&oacute;n
		  		</div>
		  		<div class="panel-body">
		  			<div class="row">
						<div clasS="col-md-12">
							<ol class="breadcrumb">
							  <li><a href="${pageContext.request.contextPath}/controller/html/payroll/index">Liquidaci&oacute;n</a></li>
							  <li class="active">Detalle de liquidaci&oacute;n</li>
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
								<table id="tPayrollDetailResult" class="table table-condensed display">
									<thead>
							            <tr>
							            	<th>Id Vendedor</th>
							                <th>Nombre y apellido</th>
							                <th>A cobrar</th>
							                <th>Descuentos</th>
							                <th>Total</th>
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
			
			PayrollDetail.initDataTable(imgCheckUrl, "${payrollId}");
		    
			return;
		}	
		
	);
</script>