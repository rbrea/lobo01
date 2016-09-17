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
	<form id="frmBillHistoryExportPdf" method="POST" action="${pageContext.request.contextPath}/controller/html/bill/export/pdf">
		<input type="hidden" id="bhCollectorId" name="bhCollectorId">
		<input type="hidden" id="bhCreditNumber" name="bhCreditNumber">
		<input type="hidden" id="bhStatus" name="bhStatus">
		<input type="hidden" id="bhClientId" name="bhClientId">
		<input type="hidden" id="bhDateFrom" name="bhDateFrom">
		<input type="hidden" id="bhDateTo" name="bhDateTo">
	</form>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
		  		<div class="panel-heading">
		    		<i class="fa fa-folder-open-o fa-fw"></i>&nbsp;Historial de Cr&eacute;ditos
		    		<div class="pull-right">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                            	<i class="fa fa-chevron-down"></i>
                            </button>
                            <ul class="dropdown-menu pull-right" role="menu">
                                <li>
                                	<a href="javascript:void(0);" onclick="javascript:BillHistory.exportToPdf();">
                                    	<i class="glyphicon glyphicon-print"></i>&nbsp;&nbsp;Imprimir
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="widget-icons pull-right">
                 		<a id="billHistoryCollapseButton" class="wminimize" data-toggle="collapse" href="#bill-history-filter" 
                 			 aria-expanded="true" aria-controls="bill-history-filter">
                 			<i class="fa fa-chevron-up"></i>
                 		</a>&nbsp;&nbsp; 
               		</div>
		  		</div>
		  		<div class="panel-body">
		  			<div class="container-fluid">
		  				<div id="bill-history-filter" class="row">
		  					<div class="col-md-12">
		  						<div class="container-fluid">
		  							<div class="row">
						  				<div class="col-md-1">
											<input type="hidden" id="billHistoryCollectorId" name="billHistoryCollectorId">
											<div class="form-group">
												<label>Zona</label>
								                <input type="number" class="form-control " id="billHistoryCollectorZone" name="billHistoryCollectorZone" placeholder="ID" min="1" data-error="Requerido" required>
												<div class="help-block with-errors"></div>
								            </div>		  					
						  				</div>
						  				<div class="col-md-5">
											<div class="form-group">
												<label for="billHistoryCollectorZone">Descripci&oacute;n Cobrador</label>
						  						<div class="input-group">
								                	<input type="text" class="form-control" id="billHistoryCollectorDescription" 
								                			name="billHistoryCollectorDescription" placeholder="Zona / Descripci&oacute;n" data-error="Requerido" required>
								                	<span id="btnBillHistorySearchCollector" class="input-group-addon lov"><i class="glyphicon glyphicon-search"></i></span>
												</div>
												<div class="help-block with-errors"></div>
								            </div>		  					
						  				</div>
						  				<div class="col-md-3">
						  					<div class="form-group">
							  					<label for="billHistoryStatus">Estado</label>
							  					<select id="billHistoryStatus" class="form-control">
							  						<option selected="selected" value="all">Todos</option>
							  						<option value="ACTIVE">ACTIVO</option>
							  						<option value="CANCELED">CANCELADO</option>
							  						<option value="CANCELED_DISCOUNT">CANC C DESCUENTO</option>
							  						<option value="REDUCED">BAJA</option>
							  					</select>
							  				</div>
						  				</div>
										<div class="col-md-2">
						  					<div class="form-group">
												<label for="billHistoryTicketNumber">N&uacute;mero de Cr&eacute;dito</label>
								                <input type="number" class="form-control" id="billHistoryTicketNumber" name="billHistoryTicketNumber" placeholder="N&uacute;mero de Cr&eacute;dito" min="1">
												<div class="help-block with-errors"></div>
								            </div>
						  				</div>
						  				<div class="col-md-1">
						  					&nbsp;
						  				</div>
						  			</div>
						  			<div class="row">
						  				<div class="col-md-6">
						  					<div id="bill-client-form-group" class="form-group">
						  						<label for="baddress">Cliente</label>
						  						<div class="input-group">
						  							<input type="hidden" id="billClientIdSelected" name="billClientIdSelected" required>
									                <input type="text" class="form-control" id="baddress" name="baddress" placeholder="Ingrese nombre de cliente" required>
													<span id="btnSearchClient" class="input-group-addon"><i class="glyphicon glyphicon-search lov"></i></span>		  						
						  						</div>
												<div id="billClientErrorMessageDiv" class="help-block with-errors"></div>
								            </div>
						  				</div>
						  				<div class="col-md-3">
						  					<div class="form-group">
												<label for="billDateFromValue">Fecha Desde</label>
								                <div class='input-group date' id="billDateFrom">
								                    <input type='text' class="form-control" id="billDateFromValue" name="billDateFromValue" placeholder="Fecha Desde"/>
								                    <span class="input-group-addon">
								                        <span class="glyphicon glyphicon-calendar"></span>
								                    </span>
								                </div>
								              	<div class="help-block with-errors"></div>
									        </div>
						  				</div>
						  				<div class="col-md-3">
						  					<div class="form-group">
												<label for="billDateToValue">Fecha Desde</label>
								                <div class='input-group date' id="billDateTo">
								                    <input type='text' class="form-control" id="billDateToValue" name="billDateToValue" placeholder="Fecha Hasta"/>
								                    <span class="input-group-addon">
								                        <span class="glyphicon glyphicon-calendar"></span>
								                    </span>
								                </div>
								              	<div class="help-block with-errors"></div>
									        </div>
						  				</div>
						  			</div>
						  			<div class="row">
						  				<div class="col-md-12">
						  					&nbsp;
						  				</div>
						  			</div>
						    		<div class="row tools-bar">
						  				<div class="col-md-8">
						  					&nbsp;
						  				</div>
						  				<div class="col-md-4">
						  					<button id="btnBillHistorySearch" type="button" data-loading-text="Espere..." class="btn btn-success"><i class="glyphicon glyphicon-search"></i>&nbsp;Buscar</button>
						  					&nbsp;
						  					<button id="btnBillHistoryReset" type="button" data-loading-text="Espere..." class="btn btn-default"><i class="fa fa-thumbs-o-down"></i>&nbsp;Limpiar</button>
						  				</div>
						  			</div>
		  							<div class="row">
						  				<div class="col-md-12">
						  					&nbsp;
						  				</div>
						  			</div>
		  						</div>
		  					</div>
		  				</div>
			    		<div class="row">
							<div class="col-md-12">
								<div class="table-responsive">
									<table id="tBillResult" class="table table-condensed display">
										<thead>
								            <tr>
								            	<th>Nro. de Cr&eacute;dito</th>
								                <th>Fecha Inicio</th>
								                <th>Fecha Estimado Fin</th>
								                <th>Cobrador/Zona</th>
								                <th>D&iacute;as atraso</th>
								                <th>$ Cuota Diaria</th>
								                <th>$ Importe Total</th>
								                <th>$ Saldo Restante</th>
								                <th>Estado</th>
								                <th>Fec Liq</th>
								                <th>Acciones</th>
								            </tr>
								        </thead>
								        <tfoot>
									        <tr>
									            <th style="text-align:right" colspan="5"><b>Totales:</b></th>
									            <th id="totInstallment"></th>
									            <th id="totImpTotal"></th>
									            <th id="totSaldoRestante"></th>
									            <th></th>
									            <th></th>
									            <th></th>
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
</div>
<div class="col-md-1">
	&nbsp;
</div>


<script>
	$(document).ready(
		function(){
			
			var imgCheckUrl = "${pageContext.request.contextPath}/public/images/checkmark-outline_32x32.png";
			
			BillHistory.init();
			
			$("#baddress").autocomplete({
			    paramName: 'q',
			    serviceUrl: "${pageContext.request.contextPath}/controller/html/client/autocomplete",
			    transformResult: function(response) {
			    	
			    	var list = [];
			    	
			    	var parsed = JSON.parse(response);

			    	$.each(parsed, function(){
			    		
			    		var obj = new Object();
			    		obj.data = "" + this.id;
			    		
			    		var mark = "";
			    	   	if(this.reductionMark != null && this.reductionMark != ""){
			    			mark = " / B";
			    	   	}
			    	   	
			    	   	var cancelationMark = "";
			    	   	if(this.cancelationMark != null && this.cancelationMark != ""){
			    	   		cancelationMark = " / C";
			    	   	}
			    		
			    		obj.value = this.name + " / " + this.address + " / " + this.companyType + mark + cancelationMark;
			    		list.push(obj);
			    		
			    		return;
			    	});
			    	
			        return {
			            suggestions: list 
			        };
			    },
			    onSelect: function (suggestion) {
			        $("#billClientIdSelected").val(suggestion.data);
			        
			        return;
			    }
			});

			return;
		}	
		
	);
</script>