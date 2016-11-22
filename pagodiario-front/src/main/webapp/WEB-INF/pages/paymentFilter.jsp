<div class="col-md-1">
	&nbsp;
</div>
<div class="col-md-10">
	<div id="paymentFilterMessages" class="row hide">
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	    <div class="col-md-8">
	        <div id="paymentFilterAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#paymentFilterAlertMessages').children('span').eq(0).html('');$('#paymentFilterMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	</div>
	<form id="frmPaymentFilterExportCsv" method="POST" action="${pageContext.request.contextPath}/controller/html/payment/export/csv">
		<input type="hidden" id="cfCollectorId" name="cfCollectorId">
		<input type="hidden" id="cfClientId" name="cfClientId">
		<input type="hidden" id="cfDateFrom" name="cfDateFrom">
		<input type="hidden" id="cfDateTo" name="cfDateTo">
	</form>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
		  		<div class="panel-heading">
		    		<i class="fa fa-folder-open-o fa-fw"></i>&nbsp;Filtro de Pagos
		    		<div class="pull-right">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                            	<i class="fa fa-chevron-down"></i>
                            </button>
                            <ul class="dropdown-menu pull-right" role="menu">
                                <li>
                                	<a href="javascript:void(0);" onclick="javascript:PaymentFilter.exportToCsv();">
                                    	<i class="glyphicon glyphicon-print"></i>&nbsp;&nbsp;Exportar
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="widget-icons pull-right">
                 		<a id="paymentFilterCollapseButton" class="wminimize" data-toggle="collapse" href="#payment-filter" 
                 			 aria-expanded="true" aria-controls="customer-filter">
                 			<i class="fa fa-chevron-up"></i>
                 		</a>&nbsp;&nbsp; 
               		</div>
		  		</div>
		  		<div class="panel-body">
		  			<div class="container-fluid">
		  				<div id="customer-filter" class="row">
		  					<div class="col-md-12">
		  						<div class="container-fluid">
		  							<div class="row">
						  				<div class="col-md-1">
											<input type="hidden" id="paymentFilterCollectorId" name="paymentFilterCollectorId">
											<div class="form-group">
												<label>Zona</label>
								                <input type="number" class="form-control " id="paymentFilterCollectorZone" name="paymentFilterCollectorZone" placeholder="ID" min="1">
												<div class="help-block with-errors"></div>
								            </div>		  					
						  				</div>
						  				<div class="col-md-5">
											<div class="form-group">
												<label for="paymentFilterCollectorDescription">Descripci&oacute;n Cobrador</label>
						  						<div class="input-group">
								                	<input type="text" class="form-control" id="paymentFilterCollectorDescription" 
								                			name="paymentFilterCollectorDescription" placeholder="Zona / Descripci&oacute;n">
								                	<span id="btnPaymentFilterSearchCollector" class="input-group-addon lov"><i class="glyphicon glyphicon-search"></i></span>
												</div>
												<div class="help-block with-errors"></div>
								            </div>		  					
						  				</div>
						  			</div>
						  			<div class="row">
						  				<div class="col-md-2">
						  					<div class="form-group">
												<label for="paymentFilterDateFromValue">Fecha Inicio Desde</label>
								                <div class='input-group date' id="paymentFilterDateFrom">
								                    <input type='text' class="form-control" id="paymentFilterDateFromValue" name="paymentFilterDateFromValue" placeholder="Fecha Desde"/>
								                    <span class="input-group-addon">
								                        <span class="glyphicon glyphicon-calendar"></span>
								                    </span>
								                </div>
								              	<div class="help-block with-errors"></div>
									        </div>
						  				</div>
						  				<div class="col-md-2">
						  					<div class="form-group">
												<label for="paymentFilterDateToValue">Fecha Inicio Hasta</label>
								                <div class='input-group date' id="paymentFilterDateTo">
								                    <input type='text' class="form-control" id="paymentFilterDateToValue" name="paymentFilterDateToValue" placeholder="Fecha Hasta"/>
								                    <span class="input-group-addon">
								                        <span class="glyphicon glyphicon-calendar"></span>
								                    </span>
								                </div>
								              	<div class="help-block with-errors"></div>
									        </div>
						  				</div>
						  				<div class="col-md-8">
						  					&nbsp;
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
						  					<button id="btnPaymentFilterSearch" type="button" data-loading-text="Espere..." class="btn btn-success"><i class="glyphicon glyphicon-search"></i>&nbsp;Buscar</button>
						  					&nbsp;
						  					<button id="btnPaymentFilterReset" type="button" data-loading-text="Espere..." class="btn btn-default"><i class="fa fa-thumbs-o-down"></i>&nbsp;Limpiar</button>
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
									<table id="tPaymentFilterResult" class="table table-condensed display">
										<thead>
								            <tr>
								            	<th class="centered">Id</th>
								                <th class="centered">Monto</th>
								                <th class="centered">Fecha</th>
								                <th class="centered">Cobrador/Zona</th>
								                <th class="centered">Pago Vend?</th>
								                <th class="centered">Cliente</th>
								            </tr>
								        </thead>
								        <!-- 
								        <tfoot>
									        <tr>
									            <th style="text-align:right" colspan="5"><b>Totales:</b></th>
									            <th id="totInstallment"></th>
									            <th id="totImpTotal"></th>
									            <th id="totSaldoRestante"></th>
									            <th></th>
									            <th></th>
									            <th></th>
									            <th></th>
									        </tr>
									    </tfoot>
									     -->
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
			
			PaymentFilter.init();
			
			return;
		}	
		
	);
</script>