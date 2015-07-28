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
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
		  		<div class="panel-heading">
		    		<h3 class="panel-title">Detalle de liquidaci&oacute;n por Vendedor</h3>
		  		</div>
		  		<div class="panel-body">
		  			<div class="row">
						<div clasS="col-md-12">
							<ol class="breadcrumb">
							  <li><a href="${pageContext.request.contextPath}/controller/html/payroll/index">Liquidaci&oacute;n</a></li>
							  <li><a href="${pageContext.request.contextPath}/controller/html/payrollDetail/index?payrollId=${payrollId}">Detalle de liquidaci&oacute;n</a></li>
							  <li class="active">Detalle de liquidaci&oacute;n por Vendedor</li>
							</ol>
						</div>
					</div>
		    		<div class="row">
		    			<div class="col-md-2">
		    				&nbsp;
		    			</div>
		  				<div class="col-md-8">
		  					<div class="table-responsive">
								<table id="tTraderConcialiationItem" class="table table-bordered">
						            <tr>
						            	<td colspan="3" class="centered title-td">${traderName}</td>
						            </tr>
						            <tr>
						            	<td colspan="3" class="centered title-td">DESDE ${fromDate} HASTA ${toDate}</td>
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
							            	<th>Detalle</th>
							                <th>A Cobrar</th>
							                <th>Descuentos</th>
							            </tr>
							        </thead>
							        <tfoot>
							            <tr>
							                <th colspan="2" style="text-align:left">SUBTOTAL</th>
							                <th style="background-color:#fcf8e3;">${totalCollect}</th>
							                <th style="background-color:#fcf8e3;">${totalDiscount}</th>
							            </tr>
							            <tr class="odd">
							                <th colspan="3" style="text-align:left">TOTAL</th>
							                <th style="background-color:#f2dede;text-align:center">${totalTrader}</th>
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
			
			ConciliationItem.initDataTable(imgCheckUrl, "${payrollItemId}");
		    
			return;
		}	
		
	);
</script>