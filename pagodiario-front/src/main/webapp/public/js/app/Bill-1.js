Bill = function(){}

Bill.initModalClient = function(){
	
	$("#btnBillClient").click(function(){
		var c = 0;
		
		$("#frmBillClient").on('invalid.bs.validator', 
			function(e){
			    c++;
				
				return;
		});
		
		$("#frmBillClient").validator('validate');
		
		if(c == 0){
			// si esta todo ok entonces doy de alta ...
			Bill.addClient();
		}
		
    	return;
    });
    
	$('#modalBillClient').on('hidden.bs.modal', function (e) {
   		
		Bill.resetModalClient();
   		
   		return;
   	});
	
	$("#btnBillNewClient").on("click", function(){

		$("#modalBillClient").modal("show");
		
		return;
	});
	
	return;
}

Bill.init = function(){
	$('.not-writable').keypress(function(e) {
        e.preventDefault();
        
        return;
	}).css({
		"cursor": "not-allowed"
	});
	
	$('#billDate').datetimepicker({
        locale: 'es'
    });
	
	Bill.initModalClient();
	Bill.initModalTrader();
	
	$("#btnBillAddProduct").on("click", function(){
		
		var i = $("div[id*='product_']").length;
		
		var newRow = $("#baseProductRow").clone();
		
		newRow.contents().each(function () {
		    if (this.nodeType === 3) this.nodeValue = $.trim($(this).text()).replace(/_X/g, "_" + i)
		    if (this.nodeType === 1) $(this).html( $(this).html().replace(/_X/g, "_" + i) )
		});
		newRow.attr("id", "product_" + i);
		newRow.removeClass("hide");
		
		newRow.insertAfter($("div[id*='product_']:last"));
		
		$("input[id*='bcuotadiaria_']").on('blur', function(){
			Bill.calculateCuotaDiaria();
			
			return;
		});
		
		$("input[id*='bimp_']").on('blur', function(){
			Bill.calculateImporteTotal();
			
			return;
		});
		
		return;
	});
	
	$("#btnBillRemoveProduct").on("click", function(){
		var i = $("div[id*='product_']").length;
		if(i == 1){
			return false;
		}
		$("div[id*='product_']:last").remove();
		
		Bill.calculateCuotaDiaria();
		Bill.calculateImporteTotal();
		
		return;
	});
	
	$("input[id*='bcuotadiaria_']").on('blur', function(){
		Bill.calculateCuotaDiaria();
		
		return;
	});
	
	$("input[id*='bimp_']").on('blur', function(){
		Bill.calculateImporteTotal();
		
		return;
	});
	
	$("#pnlTrader div[class='panel-body'] div[class='row']").find('input').attr("disabled", true).end()
		.find('button').attr("disabled", true).end().find('.lov').attr("disabled", true);
	
	//Bill.doPanelDisabled("#pnlBill");
	Bill.doPanelDisabled("#pnlClient");
	Bill.doPanelDisabled("#pnlTrader");
	Bill.doPanelDisabled("#pnlProduct");
	Bill.doPanelDisabled("#pnlFinalize");
	
	$("#btnSearchClient").on("click", function(){
		
    	$.ajax({ 
		   type    : "GET",
		   url     : Constants.contextRoot + "/controller/html/client",
		   dataType: 'json',
		   contentType: "application/json;",
		   success:function(data) {
				
			   var tbody = $("#tLovClientResult tbody");
			   
			   tbody.children('tr').remove();
			   
			   Message.hideMessages($('#facturaAlertMessages'), $("#facturaMessages"));
			   if(data != null && data.status == 0){

				   var table = $("#tLovClientResult").dataTable( {
				   		"data" : data.data,
				   		"bDestroy" : true,
				        "columns": [
							{ 
								"className": 'centered',
								"data": "id" 
							},
							{ 
								"className": 'centered',
								"data": "name" 
							},
				            { 	
				            	"className": 'centered',
				            	"data": "companyAddress" 
				            },
				            { 	
				            	"className": 'centered',
				            	"data": "companyType" 
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
				   
				   	$('#tLovClientResult tbody').on('mouseover', 'tr', function () {
						$(this).css({"cursor": "pointer"});	
						
						return;
					});
					
					$('#tLovClientResult tbody').on( 'click', 'tr', function () {
				        if ( $(this).hasClass('selected') ) {
				            $(this).removeClass('selected');
				        } else {
				            table.$('tr.selected').removeClass('selected');
				            $(this).addClass('selected');
				            
				            var selectedId = $(this).children('td').eq(0).html().trim();
				            var selectedDescription = $(this).children('td').eq(1).html().trim();
				            var address = $(this).children('td').eq(2).html().trim();
				            var companyType = $(this).children('td').eq(3).html().trim();
				            
				            $("#billClientIdSelected").val(selectedId);
				            $("#bname").val(selectedDescription);
				            $("#baddress").val(address + " / " + companyType);
				            $("#lov-client-container").css({"display": "none"});

				            BootstrapDialog.closeAll();
				        }
				        
						return;
				    });
					
					BootstrapDialog.show({
						type:BootstrapDialog.TYPE_SUCCESS,
						title: 'Clientes',
						autodestroy: false,
				        message: function(dialog) {
				        	
				        	$("#lov-client-container").css({"display":"block"});
				        	
				        	return $("#lov-client-container");
				        }
				    });
					
				} else {
					Message.showMessages($('#traderAlertMessages'), $("#traderMessages"), data.message);
				}
		   },
		   error:function(data){
			   Message.showMessages($('#traderAlertMessages'), $("#traderMessages"), data.responseJSON.message);
			   
			   return;
		   }
		});
    	
		return;
	});
	
	$("#btnSearchTrader").on("click", function(){
		
    	$.ajax({ 
		   type    : "GET",
		   url     : Constants.contextRoot + "/controller/html/trader",
		   dataType: 'json',
		   contentType: "application/json;",
		   success:function(data) {
				
			   var tbody = $("#tTraderChildrenResult tbody");
			   
			   tbody.children('tr').remove();
			   
			   Message.hideMessages($('#traderAlertMessages'), $("#traderMessages"));
			   if(data != null && data.status == 0){

				   var table = $("#tTraderChildrenResult").dataTable( {
				   		"data" : data.data,
				   		"bDestroy" : true,
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
				            var selectedDni = $(this).children('td').eq(1).html().trim();
				            var selectedDescription = $(this).children('td').eq(2).html().trim();
				            $("#btraderid").val(selectedId);
				            $("#bdni").val(selectedDni);
				            $("#btradername").val(selectedDescription);
				            $("#lov-container").css({"display": "none"});

				            BootstrapDialog.closeAll();
				        }
				        
						return;
				    });
					
					BootstrapDialog.show({
						type:BootstrapDialog.TYPE_SUCCESS,
						title: 'Vendedores',
						autodestroy: false,
				        message: function(dialog) {
				        	
				        	$("#lov-container").css({"display":"block"});
				        	
				        	return $("#lov-container");
				        }
				    });
					
				} else {
					Message.showMessages($('#traderAlertMessages'), $("#traderMessages"), data.message);
				}
		   },
		   error:function(data){
			   Message.showMessages($('#traderAlertMessages'), $("#traderMessages"), data.responseJSON.message);
			   
			   return;
		   }
		});
		
		return;
	});
	
	$("#billNumber").focus();
	
	$("#btnFirstNext").on('click', function(){
		Bill.doPanelEnabled("#pnlClient");
		$("#bname").focus();
		
		return;
	});
	
	$("#btnSecondNext").on('click', function(){
		Bill.doPanelEnabled("#pnlTrader");
		$("#btraderid").focus();
		
		return;
	});
	
	$("#btnThirdNext").on('click', function(){
		Bill.doPanelEnabled("#pnlProduct");
		$("#bcant_0").focus();
		
		return;
	});
	
	$("#btnFourNext").on('click', function(){
		Bill.doPanelEnabled("#pnlFinalize");
		$("#btnFinalize").focus();
		
		return;
	});
	
	return;
}

Bill.initModalTrader = function(){
	
	return;
}

Bill.doPanelDisabled = function(panelId){
	$(panelId + " div[class='panel-body'] div[class='row']").find('input').attr("disabled", true).end()
		.find('button').attr("disabled", true).end().find('.lov').attr("disabled", true);
	$(panelId).addClass("panel-default").removeClass("panel-success");
	
	return;
}

Bill.calculateCuotaDiaria = function(){
	var elements = $("input[id*='bcuotadiaria_']");
	var sum = 0;
	$.each(elements, function(){
		if($(this).val() != null && $(this).val() != ""){
			sum = sum + parseFloat(Math.round($(this).val() * 100) / 100);
		}
		
		return;
	});
	
	$("#bcuotaTotal").val(sum.toFixed(2));
	
	return;
}

Bill.calculateImporteTotal = function(){
	var elements = $("input[id*='bimp_']");
	var sum = 0;
	$.each(elements, function(){
		if($(this).val() != null && $(this).val() != ""){
			sum = sum + parseFloat(Math.round($(this).val() * 100) / 100);
		}
		
		return;
	});
	
	$("#bimpTotal").val(sum.toFixed(2));
	
	return;
}

Bill.doPanelEnabled = function(panelId){
	$(panelId + " div[class='panel-body'] div[class='row']").find('input').attr("disabled", false).end()
		.find('button').attr("disabled", false).end().find('.lov').attr("disabled", false);
	$(panelId).removeClass("panel-default").addClass("panel-success");
	
	return;
}

Bill.resetModalClient = function(){
	$("billClientId").val('');
	$("#clientName").val('');
	$("#clientDocumentNumber").val('');
	$("#clientEmail").val('');
	$("#clientCompanyPhone").val('');
	$("#clientCompanyAddress").val('');
	$("#clientNearStreets").val('');
	$("#clientCompanyCity").val('');
	$("#clientCompanyType").val('');
	$("#clientPhone").val('');
	$("#clientAddress").val('');
	$("#clientCity").val('');
	
	return;
}

Bill.addClient = function(){
	
	var id = $("#billClientId").val();
	var name = $("#clientName").val();
	var documentNumber = $("#clientDocumentNumber").val();
	var email = $("#clientEmail").val();
	var companyPhone = $("#clientCompanyPhone").val();
	var companyAddress = $("#clientCompanyAddress").val();
	var nearStreets = $("#clientNearStreets").val();
	var companyCity = $("#clientCompanyCity").val();
	var companyType = $("#clientCompanyType").val();
	var phone = $("#clientPhone").val();
	var address = $("#clientAddress").val();
	var city = $("#clientCity").val();
	
	var obj = new Object();
	obj.id = id;
	obj.name = name;
	obj.documentNumber = documentNumber;
	obj.email = email;
	obj.companyPhone = companyPhone;
	obj.companyAddress = companyAddress;
	obj.nearStreets = nearStreets;
	obj.companyCity = companyCity;
	obj.companyType = companyType;
	obj.phone = phone;
	obj.address = address;
	obj.city = city;
	
	$.ajax({ 
	   type    : "POST",
	   url     : Constants.contextRoot + "/controller/html/client",
	   dataType: 'json',
	   data: JSON.stringify(obj),
	   contentType: "application/json;",
	   success:function(data) {
		   Message.hideMessages($('#facturaAlertMessages'), $("#facturaMessages"));
		   if(data != null && data.status == 0){

			   var list = data.data;
			   if(list.length > 0){
				   
				   var r = list[0];
				   
				   var selectedId = r.id;
				   var selectedDescription = r.name;
				   var address = r.companyAddress;
				   var companyType = r.companyType;
				   
				   $("#traderParentId").val(selectedId);
				   $("#bname").val(selectedDescription);
				   $("#baddress").val(address + " / " + companyType);
			   }
			   
			   $("#modalBillClient").modal('hide');
			   
			   return;
		   }else{
			   Message.showMessages($('#facturaAlertMessages'), $("#facturaMessages"), data.message);
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#facturaAlertMessages'), $("#facturaMessages"), data.responseJSON.message);
		   
		   return;
	   }
	});
	
	
	
	return;
}


