		<div class="col-md-2">
			&nbsp;
		</div>
		<div class="col-md-10">
			<div class="panel panel-default">
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

<script>
	$(document).ready(
		function(){
			var table = $("#tTraderChildrenResult").dataTable( {
		        "ajax": Constants.contextRoot + "/controller/html/trader/children?parentId=${parentId}",
		        "columns": [
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
		}	
		
	);
</script>