<div class="col-md-1">
	&nbsp;
</div>
<div class="col-md-10">
	<div id="clientMessages" class="row hide">
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	    <div class="col-md-8">
	        <div id="clientAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#clientAlertMessages').children('span').eq(0).html('');$('#clientMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	</div>
	<form id="frmClientExportPdf" method="POST" action="${pageContext.request.contextPath}/controller/html/client/export/pdf">
	</form>
	<form id="frmClientExportXls" method="POST" action="${pageContext.request.contextPath}/controller/html/client/export.xls" enctype="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
	</form>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
		  		<div class="panel-heading">
		    		<i class="fa fa-chevron-right"></i>&nbsp;ABM de Clientes
		    		<div class="pull-right">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                            	<i class="fa fa-chevron-down"></i>
                            </button>
                            <ul class="dropdown-menu pull-right" role="menu">
                                <li>
                                	<a href="javascript:void(0);" onclick="javascript:Client.exportToPdf();">
                                    	<i class="glyphicon glyphicon-print"></i>&nbsp;&nbsp;Imprimir
                                    </a>
                                </li>
                                <li>
                                	<a href="javascript:void(0);" onclick="javascript:Client.exportToXls();">
                                    	<i class="fa fa-download"></i>&nbsp;&nbsp;Descargar
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
		  		</div>
		  		<div class="panel-body">
		  			<div class="row tools-bar">
		  				<div class="col-md-10">
		  					&nbsp;
		  				</div>
		  				<div class="col-md-2">
		  					<button id="btnNew" type="button" data-loading-text="Espere..." class="btn btn-success"><i class="glyphicon glyphicon-plus"></i>&nbsp;Nuevo</button>
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
								<table id="tClientResult" class="table table-condensed display">
									<thead>
							            <tr>
							            	<th>C&oacute;digo</th>
							                <th>Nombre y apellido</th>
							                <th>DNI</th>
							                <th>Tel&eacute;fono</th>
							                <th>Domicilio</th>
							                <th>Localidad / Barrio</th>
							                <th>Tipo Comercio</th>
							                <th>Email</th>
							                <th>Telefono Particular</th>
							                <th>Domicilio Particular</th>
							                <th>Ciudad Particular</th>
							                <th>Entre Calles</th>
							                <th>Tuvo Baja?</th>
							                <th>Cancel&oacute;?</th>
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
			
			Client.initDataTable(imgCheckUrl);
		    
           	$("#btnNew").click(function(){
           		Client.showModal(null);
           		
           		return;
           	});
           	
           	Client.initControls();
			
			return;
		}	
		
	);
</script>