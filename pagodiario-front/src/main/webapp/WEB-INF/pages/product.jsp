<div class="col-md-1">
	&nbsp;
</div>
<div class="col-md-10">
	<div id="productMessages" class="row hide">
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	    <div class="col-md-8">
	        <div id="productAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#productAlertMessages').children('span').eq(0).html('');$('#productMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	</div>
	<form id="frmProductExportPdf" method="POST" action="${pageContext.request.contextPath}/controller/html/product/export/pdf">
	</form>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
		  		<div class="panel-heading">
		    		<i class="fa fa-money fa-fw"></i>&nbsp;ABM de Productos
		    		<div class="pull-right">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                            	<i class="fa fa-chevron-down"></i>
                            </button>
                            <ul class="dropdown-menu pull-right" role="menu">
                                <li>
                                	<a href="javascript:void(0);" onclick="javascript:Product.exportToPdf();">
                                    	<i class="glyphicon glyphicon-print"></i>&nbsp;&nbsp;Imprimir
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
								<table id="tProductResult" class="table table-condensed display">
									<thead>
							            <tr>
							            	<th></th>
							                <th>C&oacute;digo de Producto</th>
							                <th>Descripci&oacute;n</th>
							                <th>Precio Unitario</th>
							                <th>Cuota Diaria</th>
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
			
			Product.initDataTable(imgCheckUrl);
		    
           	$("#btnNew").click(function(){
           		Product.showModal(null);
           		
           		return;
           	});
           	
           	Product.initControls();
			
			return;
		}	
		
	);
</script>