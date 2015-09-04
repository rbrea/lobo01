ConciliationItem = function(){}

ConciliationItem.init = function(){
	
	$("#btnExportLiqVend").on('click', function(){
		$("#frmConciliationItem").submit();
		
		return;
	});
}

ConciliationItem.initSupervisor = function(){
	
	$("#btnExportLiqSup").on('click', function(){
		$("#frmConciliationItemSup").submit();
		
		return;
	});
}

ConciliationItem.initDataTable = function(imgCheckUrl, id){
	
	var table = $("#tConciliationItemResult").dataTable( {
		"bDestroy" : true,
		"searching": false,
		"ordering": false,
		"info" : false,
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
        }/*,
        "footerCallback": function ( row, data, start, end, display ) {
            var api = this.api(), data;
 
            // Remove the formatting to get integer data for summation
            var intVal = function ( i ) {
                return typeof i === 'string' ?
                    i.replace(/[\$,]/g, '')*1 :
                    typeof i === 'number' ?
                        i : 0;
            };
 
            // Total over all pages
            total = api
                .column( 3 )
                .data()
                .reduce( function (a, b) {
                    return intVal(a) + intVal(b);
                } );
 
            // Total over this page
            pageTotal = api
                .column( 3, { page: 'current'} )
                .data()
                .reduce( function (a, b) {
                    return intVal(a) + intVal(b);
                }, 0 );
 
            // Update footer
            $( api.column( 3 ).footer() ).html(
                '$'+pageTotal +' ( $'+ total +' total)'
            );
            
            // Total over all pages
            total = api
                .column( 4 )
                .data()
                .reduce( function (a, b) {
                    return intVal(a) + intVal(b);
                } );
 
            // Total over this page
            pageTotal = api
                .column( 4, { page: 'current'} )
                .data()
                .reduce( function (a, b) {
                    return intVal(a) + intVal(b);
                }, 0 );
 
            // Update footer
            $( api.column( 4 ).footer() ).html(
                '$'+pageTotal +' ( $'+ total +' total)'
            );
        }*/
    });
	
	return;
}

ConciliationItem.initSupervisorDataTable = function(imgCheckUrl, id){
	
	var table = $("#tConciliationItemResult").dataTable( {
		"bDestroy" : true,
		"searching": false,
		"ordering": false,
		"info" : false,
        "ajax": Constants.contextRoot + "/controller/html/conciliationItem/supervisor?payrollItemId=" + id,
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
            	"data": "traderName" 
            },
            { 	
            	"className": 'centered',
            	"data": "creditAmount"
            },
            { 	
            	"className": 'centered',
            	"data": "devAmount"
            },
            { 	
            	"className": 'centered',
            	"data": "bonusAmount"
            },
            { 	
            	"className": 'centered',
            	"data": "reductionAmount"
            },
            { 	
            	"className": 'centered',
            	"data": "totalTrader"
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
        }/*,
        "footerCallback": function ( row, data, start, end, display ) {
        var api = this.api(), data;

        // Remove the formatting to get integer data for summation
        var intVal = function ( i ) {
            return typeof i === 'string' ?
                i.replace(/[\$,]/g, '')*1 :
                typeof i === 'number' ?
                    i : 0;
        };

        // Total over all pages
        total = api
            .column( 3 )
            .data()
            .reduce( function (a, b) {
                return intVal(a) + intVal(b);
            } );

        // Total over this page
        pageTotal = api
            .column( 3, { page: 'current'} )
            .data()
            .reduce( function (a, b) {
                return intVal(a) + intVal(b);
            }, 0 );

        // Update footer
        $( api.column( 3 ).footer() ).html(
            '$'+pageTotal +' ( $'+ total +' total)'
        );
        
        // Total over all pages
        total = api
            .column( 4 )
            .data()
            .reduce( function (a, b) {
                return intVal(a) + intVal(b);
            } );

        // Total over this page
        pageTotal = api
            .column( 4, { page: 'current'} )
            .data()
            .reduce( function (a, b) {
                return intVal(a) + intVal(b);
            }, 0 );

        // Update footer
        $( api.column( 4 ).footer() ).html(
            '$'+pageTotal +' ( $'+ total +' total)'
        );
    }*/
    });
	
	return;
}


