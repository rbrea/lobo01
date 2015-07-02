<div class="modal fade" id="modalEditUser" tabindex="-1" role="dialog" aria-labelledby="modalEditUserLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modalEditUserLabel">Editar Usuario</h4>
      </div>
      <div class="modal-body">
		<form id="frmEditUser" action="${pageContext.request.contextPath}/controller/html/user/registration/edit" 
				method="POST" data-toggle="validator">
			<input type="hidden" id="userId" name="id" value="">
	    	<div class="form-group">
				<label for="name">Nombre y Apellido</label>
			    <input type="text" class="form-control" id="name" name="name" placeholder="Ingrese Nombre y Apellido..." required>
			    <div class="help-block with-errors"></div>
			</div>
			<div class="form-group">
				<label for="email">e-mail</label>
			    <input type="email" class="form-control" id="email" name="email" placeholder="Ingrese e-mail..." required>
			    <div class="help-block with-errors"></div>
			</div>
			<div class="form-group">
				<label for="documentNumber">N&uacute;mero de Documento</label>
			    <input type="number" class="form-control" id="documentNumber" name="documentNumber" placeholder="Ingrese N&uacute;mero de Documento..." required>
			    <div class="help-block with-errors"></div>
			</div>
			<div class="form-group">
				<label for="nusername">Nombre de Usuario</label>
			    <input type="text" class="form-control" id="nusername" name="username" placeholder="Ingrese Nombre de Usuario..." readonly>
			    <div class="help-block with-errors"></div>
			</div>
			<div class="checkbox">
		    	<label>
		      		<input type="checkbox" name="admin" id="admin"> Es Administrador?
		    	</label>
		  	</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        <button id="btnEditUser" type="button" class="btn btn-primary">Guardar</button>
      </div>
    </div>
  </div>
</div>
<div class="row">
	<div class="col-md-12">
		&nbsp;
	</div>
</div>
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
			    <p class="navbar-text navbar-left">Herramientas: </p>
			    <button id="btnNew" type="button" data-loading-text="Espere..." class="btn btn-primary navbar-btn"><i class="glyphicon glyphicon-plus"></i>&nbsp;Nuevo</button>
				<!--  
				<button id="btnUpdate" type="button" data-loading-text="Espere..." class="btn btn-success navbar-btn disabled"><i class="glyphicon glyphicon-pencil"></i>&nbsp;Modificar</button>
				<button id="btnRemove" type="button" data-loading-text="Espere..." class="btn btn-danger navbar-btn disabled"><i class="glyphicon glyphicon-trash"></i>&nbsp;Eliminar</button>
				-->
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
							            	<th></th>
							                <th>Nombre</th>
							                <th>Nombre de Usuario</th>
							                <th>Es administrador?</th>
							                <th>Tipo de Documento</th>
							                <th>Nro. de Documento</th>
							                <th>Email</th>
							                <th>Acciones</th>
							            </tr>
							        </thead>
							 
							        <tfoot>
							            <tr>
							            	<th></th>
							                <th>Nombre</th>
							                <th>Nombre de Usuario</th>
							                <th>Es administrador?</th>
							                <th>Tipo de Documento</th>
							                <th>Nro. de Documento</th>
							                <th>Email</th>
							                <th>Acciones</th>
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
		        "ajax": "${pageContext.request.contextPath}/controller/service/registration",
		        "columns": [
					{
						"className":      'centered',
					 	// The `data` parameter refers to the data for the cell (defined by the
					    // `data` option, which defaults to the column being worked with, in
					    // this case `data: 0`.
					    "orderable": false,
					    "render": function ( data, type, row ) {
					        //return data +' ('+ row[3]+')';
					        return "<img id=\"imgCheck_" + row.id + "\" class=\"hide\" width=\"60%\" src=\"${pageContext.request.contextPath}/public/images/checkmark-outline_32x32.png\">";
					    }
					},
		            /*{
		                "className":      'details-control',
		                "orderable":      false,
		                "data":           null,
		                "defaultContent": ''
		            },*/
		            { 	
		            	"className": 'centered',
		            	"data": "name" 
		            },
		            { 
		            	"className": 'centered',
		            	"data": "username" 
		            },
		            { 
		            	"className": 'centered',
		            	"render": function ( data, type, row ) {
					        //return data +' ('+ row[3]+')';
					        var value = "NO";
					        if(row.admin){
					        	value = "SI";
					        }
					        
					        return value;
					    } 
		            },
		            { 
		            	"className": 'centered',
		            	"data": "documentType" },
		            { 	
		            	"className": 'centered',
		            	"data": "documentNumber" },
		            { 
		            	"className": 'centered',
		            	"data": "email" },
		            {
		            	"className":      'centered',
			         	// The `data` parameter refers to the data for the cell (defined by the
		                // `data` option, which defaults to the column being worked with, in
		                // this case `data: 0`.
		                "orderable": false,
		                "render": function ( data, type, row ) {
		                    //return data +' ('+ row[3]+')';
		                    return "<a href=\"javascript:Login.showEditModal('" + row.id + "');\" class=\"btn btn-xs btn-success\"><i class=\"glyphicon glyphicon-pencil\"></i></a>" 
		                        + "&nbsp;<a href=\"javascript:Login.removeUser('" + row.id + "');\" class=\"btn btn-xs btn-danger\"><i class=\"glyphicon glyphicon-trash\"></i></a>";
		                }
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
		    /*
			$('#tResult tbody').on( 'mouseover', 'tr', function () {
				$(this).css({"cursor": "pointer"});	
				
				return;
			});
			
			$('#tResult tbody').on( 'click', 'tr', function () {
				
		        if ( $(this).hasClass('selected') ) {
		            $(this).removeClass('selected');
		            $("#btnUpdate").addClass("disabled");
		            $("#btnRemove").addClass("disabled");
		            $(this).children("td:first").children("img").addClass("hide");
		        }
		        else {
		            table.$('tr.selected').removeClass('selected');
		            $(this).addClass('selected');
		            $("#btnUpdate").removeClass("disabled");
		            $("#btnRemove").removeClass("disabled");
		            table.$('tr.selected').children("td:first").children("img").removeClass("hide");
		            
		        }
		    } );
			*/
		 
		    /*$('#button').click( function () {
		        table.row('.selected').remove().draw( false );
		    } );*/
		    
		    $("#btnEditUser").click(function(){
		 	
		    	$("#frmEditUser").submit();
		    	
		    	return;
		    });
			
			return;
		}	
		
	);
</script>