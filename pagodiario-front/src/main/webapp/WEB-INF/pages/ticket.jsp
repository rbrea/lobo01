<div class="col-md-1">
	&nbsp;
</div>
<div class="col-md-10">
	<div id="ticketMessages" class="row hide">
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	    <div class="col-md-8">
	        <div id="ticketAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#ticketAlertMessages').children('span').eq(0).html('');$('#ticketMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div id="pnlTicket" class="panel panel-default">
		  		<div class="panel-heading">
		    		<h3 class="panel-title"><i class="fa fa-file-text-o fa-fw"></i> Buscar tickets de cobro</h3>
		  		</div>
		  		<div class="panel-body">
		  			<form id="frmTicket" action="${pageContext.request.contextPath}/controller/html/ticket" method="post">
		  			<div class="row">
		  				<div class="form-group col-md-3">
		  					<div class="form-group">
		  						<label for="ticketDateValue">Fecha de Ticket</label>
				                <div class='input-group date' id='ticketDate'>
				                    <input type='text' class="form-control input-sm not-writable" id="ticketDateValue" name="ticketDateValue" placeholder="Ingrese fecha de Ticket..." data-required-error="Requerido" required/>
				                    <span class="input-group-addon">
				                        <span class="glyphicon glyphicon-calendar"></span>
				                    </span>
				                </div>
				            </div>
		  				</div>
						<div class="form-group col-md-3">
							<label for="zone">Zona / Cobrador</label>
							<div class="input-group">
					    		<div class="input-group-addon"><i class="glyphicon glyphicon-tag"></i></div>
					    		<input type="number" class="form-control input-sm" id="zone" name="zone" placeholder="Ingresar zona/cobrador ..." data-required-error="Requerido" required>
							</div>
							<div class="help-block with-errors"></div>
						</div>
						<div class="form-group col-md-3">
	  						<label for="fecDesdeValue">Fecha desde</label>
			                <div class='input-group date' id='fecDesde'>
			                    <input type='text' class="form-control input-sm not-writable" id="fecDesdeValue" name="fecDesdeValue" placeholder="Ingrese fecha desde ..."/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
						</div>
						<div class="form-group col-md-3">
	  						<label for="fecHastaValue">Fecha hasta</label>
			                <div class='input-group date' id='fecHasta'>
			                    <input type='text' class="form-control input-sm not-writable" id="fecHastaValue" name="fecHastaValue" placeholder="Ingrese fecha hasta ..."/>
			                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
			                </div>
						</div>
					</div>
		  			</form>
		  		</div>
			</div>	
		</div>
	</div>
	<div class="row" >
		<div class="col-md-8">
			&nbsp;
		</div>
		<div class="col-md-4">
			<button id="btnValidation" type="button" data-loading-text="Espere..." class="btn btn-primary"><i class="glyphicon glyphicon-ok-circle"></i>&nbsp;Validar</button>&nbsp;
			<button id="btnAccept" type="button" data-loading-text="Espere..." class="btn btn-success disabled"><i class="glyphicon glyphicon-print"></i>&nbsp;Generar</button>&nbsp;
			<button id="btnReset" type="button" data-loading-text="Espere..." class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i>&nbsp;Limpiar</button>
		</div>
	</div>
</div>
<div class="col-md-1">
	&nbsp;
</div>


<script>
	$(document).ready(
		function(){
			
			Ticket.init();
			
			return;
		}	
		
	);
</script>