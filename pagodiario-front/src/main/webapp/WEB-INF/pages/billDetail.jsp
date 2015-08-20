<div class="col-md-1">
	&nbsp;
</div>
<div class="col-md-10">
	<div id="billDetailMessages" class="row hide">
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	    <div class="col-md-8">
	        <div id="billDetailAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#billDetailAlertMessages').children('span').eq(0).html('');$('#billDetailMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	</div>
	<div class="row">
		<div clasS="col-md-12">
			<ol class="breadcrumb">
			  <li><a href="${pageContext.request.contextPath}/controller/html/bill/history/index">Consultar Cr&eacute;ditos</a></li>
			  <li class="active">Detalle de Cr&eacute;dito</li>
			</ol>
		</div>
	</div>
	<div class="row">
		<div class="col-md-2">
	        &nbsp;
	    </div>
		<div class="col-md-8">
			<div class="panel panel-primary">
		  		<div class="panel-heading">
		    		<h3 class="panel-title centered" style="font-weight:bold;">DETALLE DE CR&Eacute;DITO&nbsp;<span id="detailCreditNumber"></span></h3>
		  		</div>
		  		<div class="panel-body">
		  			<table id="tCreditDetail" class="table table-bordered table-striped">
		  				<tr>
		  					<td style="font-weight:bold;">Cliente</td>
		  					<td id="clientName"></td>
		  				</tr>
		  				<tr>
		  					<td style="font-weight:bold;">Direcci&oacute;n</td>
		  					<td id="clientAddress"></td>
		  				</tr>
		  				<tr>
		  					<td style="font-weight:bold;">Vendedor</td>
		  					<td id="traderName"></td>
		  				</tr>
		  				<tr>
		  					<td style="font-weight:bold;">Fecha Cr&eacute;dito</td>
		  					<td id="creditDate"></td>
		  				</tr>
		  				<tr>
		  					<td style="font-weight:bold;">Importe Cr&eacute;dito</td>
		  					<td id="creditAmount"></td>
		  				</tr>
		  				<tr>
		  					<td style="font-weight:bold;">Cuota</td>
		  					<td id="installment"></td>
		  				</tr>
		  				<tr>
		  					<td style="font-weight:bold;">Pago Primer Cuota</td>
		  					<td id="firstInstallment"></td>
		  				</tr>
		  			</table>
		  			<table id="tBillDevolution" class="table table-bordered table-striped">
		  				<thead>
			  				<tr>
			  					<th colspan="4" class="centered">DEVOLUCIONES</th>
			  				</tr>
			  				<tr>
			  					<th class="centered">FECHA</th>
			  					<th class="centered">PRODUCTO</th>
			  					<th class="centered">IMPORTE</th>
			  					<th class="centered">CUOTA</th>
			  				</tr>
		  				</thead>
		  				<tbody>
		  					<tr>
		  						<td colspan="4" class="centered">No se han encontrado resultados</td>
		  					</tr>
		  				</tbody>
		  			</table>
		  			<table id="tBillPayment" class="table table-bordered table-striped">
		  				<thead>
			  				<tr>
			  					<th colspan="3" class="centered">PAGOS</th>
			  				</tr>
			  				<tr>
			  					<th class="centered">FECHA</th>
			  					<th class="centered">COBRADOR</th>
			  					<th class="centered">IMPORTE</th>
			  				</tr>
		  				</thead>
		  				<tbody>
		  					<tr>
		  						<td colspan="3" class="centered">No se han encontrado resultados</td>
		  					</tr>
		  				</tbody>
		  			</table>
		  			<table id="billRemainingAmount" class="table table-bordered table-striped">
		  				<tr>
		  					<td style="font-weight:bold;">SALDO</td>
		  					<td id="remainingAmount" style="font-weight:bold;"></td>
		  				</tr>
		  			</table>
		  		</div>
			</div>	
		</div>
		<div class="col-md-2">
	        &nbsp;
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
			
			var input = null;
			var billId = '${billId}';
			if(billId != ''){
				input = billId;
			}
			
			Bill.initDetail(input);
		    
			return;
		}
		
	);
</script>