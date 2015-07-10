<div id="lov-container" class="container-fluid" style="margin-top:-10%;display:none;">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-primary">
		  		<div class="panel-heading">
		    		<h3 class="panel-title">Lista de Vendedores</h3>
		  		</div>
		  		<div class="panel-body">
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
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="modalTrader" tabindex="-1" role="dialog" aria-labelledby="modalTraderLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modalTraderLabel">Vendedores/Supervisores</h4>
      </div>
      <div class="modal-body">
		<form id="frmTrader" 
				method="POST" data-toggle="validator">
			<input type="hidden" id="traderId" name="id" value="">
			<div class="container-fluid">
				<div class="row">
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
									    <input type="text" class="form-control lov" id="traderParentDescription" name="traderParentDescription" placeholder="Asignar supervisor..." readonly>
										<i class="glyphicon glyphicon-search"></i>
										<a id="lovTraderShow" href="javascript:void(0);"></a>
										<div class="help-block with-errors"></div>
									</div>
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
				</div>
			</div>
	    	
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        <button id="btnTrader" type="button" class="btn btn-primary">Guardar</button>
      </div>
    </div>
  </div>
</div>

<script>

	$(document).ready(
		function(){
			$("#btnTrader").click(function(){
				var c = 0;
				
				$("#frmTrader").on('invalid.bs.validator', 
					function(e){
					    c++;
						
						return;
				});
				
				$("#frmTrader").validator('validate');
				
				if(c == 0){
					// si esta todo ok entonces doy de alta ...
					Trader.add();
				}
				
		    	return;
		    });
		    
			$('#modalTrader').on('hidden.bs.modal', function (e) {
           		
				Trader.resetModal();
           		
           		return;
           	});
			
			$("#traderParentDescription").on('click', function(){
				$("#lovTraderShow").fancybox({
					'onStart': function() { 
						$("#lov-container").css("display","block"); 
						
						return;
					},            
			        'onClosed': function() { $("#lov-container").css("display","none"); },
			        'content' : $("#lov-container").html(),
					padding     : 5,
					margin      : 2,
					maxWidth	: 800,
					maxHeight	: 600,
					width		: '70%',
					height		: '60%',
					autoSize	: false,
					closeClick	: false,
					openEffect	: 'fade',
					closeEffect	: 'none'
				});
				
				$("#lovTraderShow").trigger('click');		
				
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
			            $.fancybox.close();
			        }
			    } );
				
				return;
			});
			
			var table = $("#tTraderChildrenResult").dataTable( {
		        "ajax": Constants.contextRoot + "/controller/html/trader",
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
			
			return;
		});

</script>