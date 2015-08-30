<div id="lov-container" class="container-fluid" style="display:none;">
	<div class="row">
		<div class="col-md-12">
			<div class="table-responsive">
				<table id="tTraderChildrenResult" class="table table-condensed display">
					<thead>
			            <tr>
			            	<th>C&oacute;digo</th>
			                <th>DNI</th>
			                <th>Nombre y apellido</th>
			            </tr>
			        </thead>
				</table>							
			</div>
		</div>
	</div>
</div>

<div id="modal-trader-container" class="container-fluid" style="display:none;">
	<div id="modalTraderMessages" class="row hide">
	    <div class="col-md-12">
	        <div id="modalTraderAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#modalTraderAlertMessages').children('span').eq(0).html('');$('#modalTraderMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	</div>
	<div class="row">
  		<form id="frmTrader">
  			<input type="hidden" id="traderId" name="id" value="">
		<div class="col-md-6">
			<div class="panel panel-default">
			  	<div class="panel-body">
			    	<div class="form-group">
						<label for="name">Nombre y Apellido</label>
					    <input type="text" class="form-control" id="name" name="name" placeholder="Ingrese Nombre y Apellido..." required>
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="documentNumber">N&uacute;mero de Documento</label>
					    <input type="number" class="form-control" id="documentNumber" name="documentNumber" placeholder="Ingrese N&uacute;mero de Documento..." required>
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="email">e-mail</label>
					    <input type="email" class="form-control" id="email" name="email" placeholder="Ingrese e-mail...">
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="traderParentDescription">Supervisor</label>
						<div class="inner-addon right-addon">
							<input type="hidden" id="traderParentId" name="traderParentId" value="">
						    <input type="text" class="form-control lov" id="traderParentDescription" name="traderParentDescription" placeholder="Asignar supervisor...">
							<i class="glyphicon glyphicon-search"></i>
							<div class="help-block with-errors"></div>
						</div>
						<a id="btnCleanSupervisor" class="btn btn-xs btn-primary">Limpiar Supervisor</a>
					</div>
					<div class="form-group">
						<div class="checkbox">
					    	<label>
					      		<input type="checkbox" name="supervisor" id="supervisor"> Es Supervisor?
					    	</label>
					  	</div>
					</div>
			  	</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="form-group">
						<label for="phone">Tel&eacute;fono</label>
					    <input type="text" class="form-control" id="phone" name="phone" placeholder="Ingrese N&uacute;mero de tel&eacute;fono...">
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="address">Domicilio</label>
					    <input type="text" class="form-control" id="address" name="address" placeholder="Ingrese domicilio...">
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="city">Localidad/Barrio</label>
					    <input type="text" class="form-control" id="city" name="city" placeholder="Ingrese localidad/barrio...">
					    <div class="help-block with-errors"></div>
					</div>
			  	</div>
			</div>
		</div>
		</form>
	</div>
</div>

<script>

	$(document).ready(
		function(){
		    
			$('#traderParentDescription').keypress(function(e) {
		         e.preventDefault();
		         
		         return;
			});
			
			$("#traderParentDescription").on('click', function(){
				
				var traderId = $("#traderId").val();
				
				var c = "";
				
				if(traderId != ""){
					c = "?parentId=" + traderId;
				}
				
	        	$.ajax({ 
				   type    : "GET",
				   url     : Constants.contextRoot + "/controller/html/trader/supervisor" + c,
				   dataType: 'json',
				   contentType: "application/json;",
				   success:function(data) {
						
					   var tbody = $("#tTraderChildrenResult tbody");
					   
					   tbody.children('tr').remove();
					   
					   Message.hideMessages($('#traderAlertMessages'), $("#traderMessages"));
					   if(data != null && data.status == 0){

						   var table = $("#tTraderChildrenResult").dataTable( {
						   		"data" : data.data,
						   		"bDestroy" : true,
						        "columns": [
									{ 
										"className": 'centered',
										"data": "id" 
									},
									{ 
										"className": 'centered',
										"data": "documentNumber" 
									},
						            { 	
						            	"className": 'centered',
						            	"data": "name" 
						            }
						        ],
						        "order": [[1, 'asc']],
						        "language": {
						            "lengthMenu": "Mostrar _MENU_ registros por p&aacute;gina",
						            "zeroRecords": "No se ha encontrado ningun elemento",
						            "info": "P&aacute;gina _PAGE_ de _PAGES_",
						            "infoEmpty": "No hay registros disponibles",
						            "infoFiltered": "(filtrados de un total de _MAX_ registros)",
						            "search": "Buscar: ",
						            "paginate": {
						            	"previous": "Anterior",
										"next": "Siguiente"
									}
						        } 
						   	});
						   
						   	$('#tTraderChildrenResult tbody').on( 'mouseover', 'tr', function () {
								$(this).css({"cursor": "pointer"});	
								
								return;
							});
							
							$('#tTraderChildrenResult tbody').on( 'click', 'tr', function () {
						        if ( $(this).hasClass('selected') ) {
						            $(this).removeClass('selected');
						        } else {
						            table.$('tr.selected').removeClass('selected');
						            $(this).addClass('selected');
						            
						            var selectedId = $(this).children('td').eq(0).html().trim();
						            var selectedDescription = $(this).children('td').eq(2).html().trim();
						            $("#traderParentId").val(selectedId);
						            $("#traderParentDescription").val(selectedDescription);
						            
						            $("#btnCleanSupervisor").removeClass("disabled");
						            
						            $("#lov-container").css({"display": "none"});

						            //BootstrapDialog.closeAll();
						            $.each(BootstrapDialog.dialogs, function(id, dialog){
						            	
						            	if(dialog.getTitle() == 'Vendedores'){
						            		
						            		dialog.close();
						            	}
						            	
						            	return;
						            });
						        }
						        
								return;
						    });
							
							BootstrapDialog.show({
								type:BootstrapDialog.TYPE_SUCCESS,
								title: 'Vendedores',
								autodestroy: false,
						        message: function(dialog) {
						        	
						        	$("#lov-container").css({"display":"block"});
						        	
						        	return $("#lov-container");
						        }
						    });
							
						} else {
							Message.showMessages($('#modalTraderAlertMessages'), $("#modalTraderMessages"), data.message);
						}
				   },
				   error:function(data){
					   Message.showMessages($('#modalTraderAlertMessages'), $("#modalTraderMessages"), data.responseJSON.message);
					   
					   return;
				   }
				});
	        	
				return;
			});
			
			$("#btnCleanSupervisor").on('click', function(){
				$("#traderParentId").val("");
				$("#traderParentDescription").val("");		
				$(this).addClass("disabled");
			});
			
			return;
		});
			
</script>