Payroll = function(){}

Payroll.init = function(){
	/*
	$('#fromDate').datetimepicker({
        locale: 'es',
        showTodayButton: true,
        format: 'DD/MM/YYYY'
    });
	
	$('#toDate').datetimepicker({
        locale: 'es',
        showTodayButton: true,
        format: 'DD/MM/YYYY'
    });
	*/
	
	$("#btnAccept").on('click', function(){
		Payroll.processPeriod();
		
		return;
	});
	
	Payroll.fillPeriodSelector();
	
	return;
}

Payroll.fillPeriodSelector = function(){
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/payroll/periods",
	   dataType: 'json',
	   async:false,
	   contentType: "application/json;",
	   success:function(list) {
		   Message.hideMessages($('#payrollAlertMessages'), $("#payrollMessages"));
		   if(list != null && list.length > 0){
			   
			   for(var i=0;i<list.length;i++){
				   
				   $("#optPayrollPeriod").append("<option>" + list[i] + "</option>");
			   }
			   
			   return;
		   }else{
			   Message.showMessages($('#payrollAlertMessages'), $("#payrollMessages"), data.message);
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#payrollAlertMessages'), $("#payrollMessages"), data.responseJSON.message);
		   
		   return;
	   }
	});
	
	return;
}

Payroll.initDataTable = function(imgCheckUrl){
	
	var table = $("#tPayrollResult").dataTable( {
		"bDestroy" : true,
		"bRedraw" : true,
        "ajax": Constants.contextRoot + "/controller/html/payroll",
        "columns": [
			{
				"className":      'centered',
				"data": "id"
			},
            { 	
            	"className": 'centered',
            	"data": "fromDate" 
            },
            { 
            	"className": 'centered',
            	"data": "toDate" 
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
            	"className": 'centered',
            	"render": function ( data, type, row ) {
			        var value = "Iniciado";
			        if(row.status == 'FINISHED'){
			        	value = "Finalizado";
			        }
			        
			        return value;
			    } 
            },
            {
            	"className":      'centered',
	         	// The `data` parameter refers to the data for the cell (defined by the
                // `data` option, which defaults to the column being worked with, in
                // this case `data: 0`.
                "orderable": false,
                "render": function ( data, type, row ) {
                	
                	var email = "";
                	if(row.email != null){
                		email = row.email;
                	}
                	var parentId = "";
                	if(row.parentId != null){
                		parentId = row.parentId;
                	}
                	var parentDescription = "";
                	if(row.parentDescription != null){
                		parentDescription = row.parentDescription;
                	}
                	
                    return "<a href=\"javascript:Payroll.showDetail('" + row.id + "');\" class=\"btn btn-xs btn-success\" data-toggle=\"tooltip\" data-placement=\"left\" title=\"Detalle de Liquidaci&oacute;n de Vendedor\"><i class=\"glyphicon glyphicon-zoom-in\"></i></a>"
                    + "&nbsp;<a href=\"javascript:Payroll.showSupervisorDetail('" + row.id + "');\" class=\"btn btn-xs btn-warning\" data-toggle=\"tooltip\" data-placement=\"left\" title=\"Detalle de Liquidaci&oacute;n de Supervisor\"><i class=\"glyphicon glyphicon-zoom-in\"></i></a>";
                }
         	}
        ],
        "order": [[0, 'desc']],
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

Payroll.processPeriod = function(){

	var obj = new Object();
	obj.period = $("#optPayrollPeriod > option:selected").val();
	
	$.ajax({ 
	   type    : "POST",
	   url     : Constants.contextRoot + "/controller/html/payroll/processperiod",
	   dataType: 'json',
	   data: JSON.stringify(obj),
	   contentType: "application/json;",
	   success:function(data) {
		   Message.hideMessages($('#payrollAlertMessages'), $("#payrollMessages"));
		   if(data != null && data.status == 0){
			   var table = $('#tPayrollResult').dataTable();
			   	
			   table.api().ajax.url(Constants.contextRoot + "/controller/html/payroll").load();

			   return;
		   }else{
			   Message.showMessages($('#payrollAlertMessages'), $("#payrollMessages"), data.message);
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#payrollAlertMessages'), $("#payrollMessages"), data.responseJSON.message);
		   
		   return;
	   }
	});
	
	return;
}

Payroll.showDetail = function(id){
	
	$("#frmPayroll")[0].action = Constants.contextRoot + "/controller/html/payrollDetail/index";
	$("#payrollId").val(id);
	$("#frmPayroll").submit();
	
	return;
}

Payroll.showSupervisorDetail = function(id){
	$("#frmPayroll")[0].action = Constants.contextRoot + "/controller/html/payrollDetail/supervisor/index";
	$("#payrollId").val(id);
	$("#frmPayroll").submit();
	
	return;
}
