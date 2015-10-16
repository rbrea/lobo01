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
                            	<li id="optBillHistoryHideFilter">
                                	<a id="aBillHistoryHideFilter" href="javascript:void(0);" onclick="javascript:BillHistory.hideFilter();">
                                    	<i class="fa fa-search-minus"></i>&nbsp;&nbsp;Ocultar Filtro
                                    </a>
                                </li>
                                <li id="optBillHistoryShowFilter">
                                	<a id="aBillHistoryShowFilter" href="javascript:void(0);" onclick="javascript:BillHistory.showFilter();">
                                    	<i class="fa fa-search-plus"></i>&nbsp;&nbsp;Mostrar Filtro
                                    </a>
                                </li>
                                <li role="separator" class="divider"></li>
                                <li>
                                	<a href="javascript:void(0);" onclick="javascript:BillHistory.exportToPdf();">
                                    	<i class="glyphicon glyphicon-print"></i>&nbsp;&nbsp;Imprimir
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
		  		</div>
		  		<div class="panel-body">
		  			<div class="container-fluid">
						<div class="row bill-history-filter">
			  				<div class="form-group col-md-2">
								<label for="billHistoryCollectorZone">Zona / Cobrador</label>
							</div>
			  				<div class="col-md-1">
								<input type="hidden" id="billHistoryCollectorId" name="billHistoryCollectorId">
								<div class="form-group">
					                <input type="number" class="form-control " id="billHistoryCollectorZone" name="billHistoryCollectorZone" placeholder="ID" min="1" data-error="Requerido" required>
									<div class="help-block with-errors"></div>
					            </div>		  					
			  				</div>
			  				<div class="col-md-4">
								<div class="form-group">
			  						<div class="input-group">
					                	<input type="text" class="form-control not-writable" id="billHistoryCollectorDescription" 
					                			name="billHistoryCollectorDescription" placeholder="Zona / Descripci&oacute;n" data-error="Requerido" required>
					                	<span id="btnBillHistorySearchCollector" class="input-group-addon lov"><i class="glyphicon glyphicon-search"></i></span>
									</div>
									<div class="help-block with-errors"></div>
					            </div>		  					
			  				</div>
							<div class="col-md-5">
								&nbsp;
							</div>
			  			</div>
			    		<div class="row bill-history-filter tools-bar">
			  				<div class="col-md-8">
			  					&nbsp;
			  				</div>
			  				<div class="col-md-4">
			  					<button id="btnBillHistorySearch" type="button" data-loading-text="Espere..." class="btn btn-success"><i class="glyphicon glyphicon-search"></i>&nbsp;Buscar</button>
			  					&nbsp;
			  					<button id="btnBillHistoryReset" type="button" data-loading-text="Espere..." class="btn btn-default"><i class="fa fa-thumbs-o-down"></i>&nbsp;Limpiar</button>
			  				</div>
			  			</div>
			    		<div class="row bill-history-filter">
			  				<div class="col-md-12">
			  					&nbsp;
			  				</div>
			  			</div>
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
</div>
<div class="col-md-1">
	&nbsp;
</div>


<script>
	$(document).ready(
		function(){
			
			var imgCheckUrl = "${pageContext.request.contextPath}/public/images/checkmark-outline_32x32.png";
			
			BillHistory.init();
			Dev.init();
		    
			return;
		}	
		
	);
</script>