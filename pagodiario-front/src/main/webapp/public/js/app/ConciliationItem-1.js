ConciliationItem = function(){}

ConciliationItem.initDataTable = function(imgCheckUrl, id){
	
	var table = $("#tConciliationItemResult").dataTable( {
		"bDestroy" : true,
        "ajax": Constants.contextRoot + "/controller/html/conciliationItem?payrollItemId=" + id,
        "createdRow": function ( row, data, index ) {
    		
    		//$(row).data('email', data.email).data('phone', data.phone).data('address', data.address).data('city', data.city);
    		
    		return;
        },
        "columns": [
			{ 	
				"className": 'centered',
				"data": "id" 
			},        
            { 	
            	"data": "description" 
            },
            { 	
            	"className": 'centered',
            	"data": "collectAmount"
            },
            { 	
            	"className": 'centered',
            	"data": "discountAmount"
            }
        ],
        "order": [[0, 'asc']],
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

