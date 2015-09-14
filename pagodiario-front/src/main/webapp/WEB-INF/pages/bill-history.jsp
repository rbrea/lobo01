<div class="col-md-1">
	&nbsp;
</div>
<div class="col-md-10">
	<div id="billHistoryMessages" class="row hide">
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	    <div class="col-md-8">
	        <div id="billHistoryAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#billHistoryAlertMessages').children('span').eq(0).html('');$('#billHistoryMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
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
		    		<h3 class="panel-title">Historial de Cr&eacute;ditos</h3>
		  		</div>
		  		<div class="panel-body">
		    		<div class="row">
						<div class="col-md-12">
							<div class="table-responsive">
								<table id="tBillResult" class="table table-condensed display">
									<thead>
							            <tr>
							            	<th>Nro. de Ticket</th>
							                <th>Fecha Inicio</th>
							                <th>Fecha Fin</th>
							                <th>Nro. de Cr&eacute;dito</th>
							                <th>Cobrador/Zona</th>
							                <th>D&iacute;as de atraso</th>
							                <th>$ Cuota Diaria</th>
							                <th>$ Importe Total</th>
							                <th>$ Saldo Restante</th>
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
			
			BillHistory.init();
		    
			return;
		}	
		
	);
</script>