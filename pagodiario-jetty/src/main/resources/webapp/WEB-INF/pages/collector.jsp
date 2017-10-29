<div class="col-md-1">
	&nbsp;
</div>
<div class="col-md-10">
	<div id="collectorMessages" class="row hide">
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	    <div class="col-md-8">
	        <div id="collectorAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#collectorAlertMessages').children('span').eq(0).html('');$('#collectorMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
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
		    		<h3 class="panel-title"><i class="fa fa-chevron-right"></i> ABM de Cobradores</h3>
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
								<table id="tCollectorResult" class="table table-condensed display">
									<thead>
							            <tr>
							                <th>C&oacute;digo de Cobrador</th>
							                <th>Zona</th>
							                <th>Descripci&oacute;n</th>
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
			
			Collector.initDataTable(imgCheckUrl);
		    
           	$("#btnNew").click(function(){
           		Collector.showModal(null);
           		
           		return;
           	});
           	
           	Collector.initControls();
			
			return;
		}	
		
	);
</script>