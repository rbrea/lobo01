<div class="col-md-1">
	&nbsp;
</div>
<div class="col-md-10">
	<div class="row">
		<div class="col-md-12">
			<nav class="navbar navbar-default">
			  <div class="container-fluid">
			    <!-- Brand and toggle get grouped for better mobile display -->
			    <div class="navbar-header">
			      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
			        <span class="sr-only">Toggle navigation</span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			      </button>
			    </div>
			    <p class="navbar-text navbar-left">Acciones: </p>
			    <button id="btnNew" type="button" data-loading-text="Espere..." class="btn btn-primary navbar-btn disabled"><i class="glyphicon glyphicon-plus"></i>&nbsp;Nuevo</button>
				<button id="btnUpdate" type="button" data-loading-text="Espere..." class="btn btn-success navbar-btn disabled"><i class="glyphicon glyphicon-pencil"></i>&nbsp;Modificar</button>
				<button id="btnRemove" type="button" data-loading-text="Espere..." class="btn btn-danger navbar-btn disabled"><i class="glyphicon glyphicon-remove"></i>&nbsp;Eliminar</button>
			  </div><!-- /.container-fluid -->
			</nav>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
		  		<div class="panel-heading">
		    		<h3 class="panel-title">ABM de Usuarios</h3>
		  		</div>
		  		<div class="panel-body">
		    		<div class="row">
						<div class="col-md-12">
							<div class="table-responsive">
								<table id="tResult" class="table table-condensed display">
									<thead>
							            <tr>
							                <th>Name</th>
							                <th>Position</th>
							                <th>Office</th>
							                <th>Extn.</th>
							                <th>Start date</th>
							                <th>Salary</th>
							            </tr>
							        </thead>
							 
							        <tfoot>
							            <tr>
							                <th>Name</th>
							                <th>Position</th>
							                <th>Office</th>
							                <th>Extn.</th>
							                <th>Start date</th>
							                <th>Salary</th>
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
<div class="col-md-1">
	&nbsp;
</div>


<script>
	$(document).ready(
		function(){
			var table = $("#tResult").dataTable( {
		        "ajax": "${pageContext.request.contextPath}/public/data/objects.txt",
		        "columns": [
		            { "data": "name" },
		            { "data": "position" },
		            { "data": "office" },
		            { "data": "extn" },
		            { "data": "start_date" },
		            { "data": "salary" }
		        ],
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
			
			$('#tResult tbody').on( 'mouseover', 'tr', function () {
				$(this).css({"cursor": "pointer"});	
				
				return;
			});
			
			$('#tResult tbody').on( 'click', 'tr', function () {
				
		        if ( $(this).hasClass('selected') ) {
		            $(this).removeClass('selected');
		            $("#btnUpdate").addClass("disabled");
		            $("#btnRemove").addClass("disabled");
		        }
		        else {
		            table.$('tr.selected').removeClass('selected');
		            $(this).addClass('selected');
		            $("#btnUpdate").removeClass("disabled");
		            $("#btnRemove").removeClass("disabled");
		            
		        }
		    } );
		 
		    /*$('#button').click( function () {
		        table.row('.selected').remove().draw( false );
		    } );*/
			
			return;
		}	
		
	);
</script>