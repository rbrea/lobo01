<div id="lov-container" class="container-fluid hide" style="margin-top:-10%;">
	<div class="row">
		<div class="col-md-2">
			&nbsp;
		</div>
		<div class="col-md-10">
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
		

<script>
	$(document).ready(
		function(){
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
		        }
		    } );
			
			return;
		});
</script>