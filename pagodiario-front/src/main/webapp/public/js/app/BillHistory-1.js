BillHistory = function(){}

BillHistory.init = function(){
	/*
	$('#startDate').datetimepicker({
        locale: 'es',
        format: 'DD/MM/YYYY',
        showTodayButton: true
    });
	
	$('#endDate').datetimepicker({
        locale: 'es',
        format: 'DD/MM/YYYY',
        showTodayButton: true
    });
	*/
	var table = $("#tBillResult").dataTable( {
		"bDestroy" : true,
        "ajax": Constants.contextRoot + "/controller/html/bill",
        "columns": [
            { 	
            	"className": 'centered',
            	"data": "id" 
            },
            { 
            	"className": 'centered',
            	"data": "startDate"
            },
            { 	
            	"className": 'centered',
            	"data": "endDate" 
            },
            { 	
            	"className": 'centered',
            	"data": "creditNumber" 
            },
            { 
            	"className": 'centered',
            	"data": "collectorId" 
            },
            { 	
            	"className": 'centered',
            	"data": "overdueDays" 
            },
            { 
            	"className": 'centered',
            	"data": "totalDailyInstallment" 
            },
            { 	
            	"className": 'centered',
            	"data": "totalAmount" 
            },
            { 	
            	"className": 'centered',
            	"data": "remainingAmount" 
            },
            { 	
            	"className": 'centered',
            	"data": "status" 
            },
            {
            	"className":      'centered',
	         	// The `data` parameter refers to the data for the cell (defined by the
                // `data` option, which defaults to the column being worked with, in
                // this case `data: 0`.
                "orderable": false,
                "render": function ( data, type, row ) {
                    //return data +' ('+ row[3]+')';
                    return "<a href=\"javascript:BillHistory.showPayments('" + row.id + "');\" class=\"btn btn-xs btn-warning\"><i class=\"glyphicon glyphicon-pencil\"></i></a>";
                }
         	}
        ],
        "order": [[1, 'desc']],
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