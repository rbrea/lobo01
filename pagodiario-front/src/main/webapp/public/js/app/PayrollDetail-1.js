PayrollDetail = function(){}

PayrollDetail.initDataTable = function(imgCheckUrl, payrollId){
	
	var table = $("#tPayrollDetailResult").dataTable( {
		"bDestroy" : true,
		"searching": false,
		"info" : false,
        "ajax": Constants.contextRoot + "/controller/html/payrollDetail?payrollId=" + payrollId,
        "createdRow": function ( row, data, index ) {
    		
    		//$(row).data('email', data.email).data('phone', data.phone).data('address', data.address).data('city', data.city);
    		
    		return;
        },
        "columns": [
            { 	
            	"className": 'centered',
            	"data": "traderId" 
            },
            { 	
            	"className": 'centered',
            	"data": "name"
            },
            { 	
            	"className": 'centered',
            	"data": "totalAmount"
            },
            { 	
            	"className": 'centered',
            	"data": "totalDiscount"
            },
            { 	
            	"className": 'centered',
            	"data": "total"
            },
            {
            	"className":      'centered',
	         	// The `data` parameter refers to the data for the cell (defined by the
                // `data` option, which defaults to the column being worked with, in
                // this case `data: 0`.
                "orderable": false,
                "render": function ( data, type, row ) {
                    //return data +' ('+ row[3]+')';
                    return "<a href=\"javascript:PayrollDetail.showDetail('" + row.id + "', '" + row.name + "', '" + row.totalAmount + "', '" 
                    	+ row.totalDiscount + "', '" + row.total 
                    	+ "');\" class=\"btn btn-xs btn-success\"><i class=\"glyphicon glyphicon-zoom-in\"></i></a>";
                }
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

PayrollDetail.showDetail = function(payrollItemId, traderName, totalCollect, totalDiscount, totalTrader){
	
	$("#frmPayrollDetail")[0].action = Constants.contextRoot + "/controller/html/conciliationItem/index";
	$("#payrollItemId").val(payrollItemId);
	$("#traderName").val(traderName);
	$("#totalCollect").val(totalCollect);
	$("#totalDiscount").val(totalDiscount);
	$("#totalTrader").val(totalTrader);
	$("#frmPayrollDetail").submit();
	
	return;
}

PayrollDetail.initDataTableSupervisor = function(imgCheckUrl, payrollId){
	
	var table = $("#tPayrollDetailSupervisorResult").dataTable( {
		"bDestroy" : true,
		"searching": false,
		"info" : false,
        "ajax": Constants.contextRoot + "/controller/html/payrollDetail/supervisor?payrollId=" + payrollId,
        "createdRow": function ( row, data, index ) {
    		
    		//$(row).data('email', data.email).data('phone', data.phone).data('address', data.address).data('city', data.city);
    		
    		return;
        },
        "columns": [
            { 	
            	"className": 'centered',
            	"data": "traderId" 
            },
            { 	
            	"className": 'centered',
            	"data": "name"
            },
            { 	
            	"className": 'centered',
            	"data": "total"
            },
            {
            	"className":      'centered',
	         	// The `data` parameter refers to the data for the cell (defined by the
                // `data` option, which defaults to the column being worked with, in
                // this case `data: 0`.
                "orderable": false,
                "render": function ( data, type, row ) {
                    //return data +' ('+ row[3]+')';
                    return "<a href=\"javascript:PayrollDetail.showDetailSupervisor('" + row.id + "', '" + row.name + "', '" + row.total + "');\" class=\"btn btn-xs btn-success\"><i class=\"glyphicon glyphicon-zoom-in\"></i></a>";
                }
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
