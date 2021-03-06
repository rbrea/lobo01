Bill = function(){}

Bill.initModalClient = function(){
	
	Bill.initControls();
	Bill.initProductControls(
			$("input[id*='bcant_']"), 
			$("input[id*='bProductCode_']"), 
			$("input[id*='bcuotadiaria_']"), 
			$("input[id*='bimp_']"));
	
	$("#btnBillNewClient").on("click", function(){

		//$("#modalBillClient").modal("show");
		Bill.showClientModal();
		
		return;
	});
	
	return;
}

Bill.init = function(){
	
	$("#baddress").on('keypress', function(){
		$("#billClientIdSelected").val("");
		
		$("#bill-client-form-group").removeClass("has-error");
		$("#billClientErrorMessageDiv").html("");
		
		return;
	});
	
	// asigno el dia de hoy
	$("#billDateValue").val(moment().format('DD/MM/YYYY'));
	
	$('.not-writable').keypress(function(e) {
        e.preventDefault();
        
        return;
	}).css({
		"cursor": "not-allowed"
	});
	
	$('#billDate').datetimepicker({
        locale: 'es',
        showTodayButton: true,
        format: 'DD/MM/YYYY'
    });
	
	Bill.initModalClient();
	Bill.initModalTrader();
	
	Bill.initCantInput();
	
	$("#btnCancel").on('click', function(){
		Bill.resetPage();
		$("html, body").animate({"scrollTop":"-590"}, "slow");
		
		return;
	});
	
	$("#btnFirstCancel").on('click', function(){
		Bill.resetFirst();
		
		return;
	});
	
	$("#btnSecondCancel").on('click', function(){
		Bill.resetSecond();
		
		return;
	});
	
	$("#btnThirdCancel").on('click', function(){
		Bill.resetThird();
		
		return;
	});
	
	$("#btnFourCancel").on('click', function(){
		Bill.resetFour();
		
		return;
	});
	
	Bill.resetPage();
	
	$("#btnBillAddProduct").on("click", function(){
		
		var i = $("div[id*='product_']").length;
		
		var newRow = $("#baseProductRow").clone();
		
		newRow.contents().each(function () {
		    if (this.nodeType === 3) this.nodeValue = $.trim($(this).text()).replace(/_X/g, "_" + i)
		    if (this.nodeType === 1) $(this).html( $(this).html().replace(/_X/g, "_" + i) )
		});
		
		newRow.find("input[id*='billProductId_']").attr("id", "billProductId_" + i).attr("name", "billProductId_" + i);
		newRow.find("input[id*='billProductPrice_']").attr("id", "billProductPrice_" + i).attr("name", "billProductPrice_" + i);
		newRow.find("input[id*='billInstallmentAmount_']").attr("id", "billInstallmentAmount_" + i).attr("name", "billInstallmentAmount_" + i);
		
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
		
		var bcant = newRow.find("input[id*='bcant_']");
		var bproductcode = newRow.find("input[id*='bProductCode_']");
		var bcuotadiaria = newRow.find("input[id*='bcuotadiaria_']");
		var bimp = newRow.find("input[id*='bimp_']");
		
		Bill.initProductControls(bcant, bproductcode, bcuotadiaria, bimp);
		
		$("input").focus(function() { $(this).select(); } ).end().click(function () {$(this).select();});
		
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
	
	$("#btnSearchClient").on("click", function(){
		
		var value = $("#bClientId").val();
		if(value != null && value != ""){
			return Bill.getClientById(value);
		}
		
		var lovClientContainer = $("#lov-client-container");
		
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
				   		"pagingType": "simple", 
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
				            },
				            { 	
				            	"className": 'centered',
				            	"render": function ( data, type, row ) {
				                    //return data +' ('+ row[3]+')';
				            		var mark = "<i class=\"fa fa-square-o fa-4\"></i>";
				            		if(row.reductionMark != null && row.reductionMark != ""){
				            			mark = "<i class=\"fa fa-check-square-o fa-4\"></i>";
				            		}
				            		
				                    return "<span id='reductionMark_'" + row.id + " style='font-weight:bold;'>" + mark + "</span>"; 
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
				   
				   var tbodyLovClientResult = $('#tLovClientResult tbody');
				   
				   tbodyLovClientResult.on('mouseover', 'tr', function () {
						$(this).css({"cursor": "pointer"});	
						
						return;
					});
					
				   tbodyLovClientResult.on( 'click', 'tr', function () {
					   
					   var me = $(this);
					   
				       if ( me.hasClass('selected') ) {
				    	   me.removeClass('selected');
				       } else {
				    	   table.$('tr.selected').removeClass('selected');
				    	   me.addClass('selected');
				    	   
				    	   var selectedId = me.children('td').eq(0).html().trim();
				    	   var selectedDescription = me.children('td').eq(1).html().trim();
				    	   var address = me.children('td').eq(2).html().trim();
				    	   var companyType = me.children('td').eq(3).html().trim();
				    	   var reductionMark = me.children('td').eq(4).html().trim();
				    	   
				    	   var hasMark = $(reductionMark).children("i").hasClass("fa-check-square-o");
				            
				    	   var mark = "";
				    	   if(hasMark){
				    		   mark = " / B";
				    	   }
				    	   
				    	   $("#billClientIdSelected").val(selectedId);
				    	   //$("#bClientId").val(selectedId);
				    	   $("#baddress").val(selectedDescription + " / " + address + " / " + companyType + mark);
				    	   lovClientContainer.css({"display": "none"});

				    	   // si esta todo ok entonces doy de alta ...
				    	   BootstrapDialog.closeAll();

				    	   $("#btraderid").focus();
				       }
				        
				       return;
				   });
					
					BootstrapDialog.show({
						type:BootstrapDialog.TYPE_DANGER,
						title: 'Clientes',
						autodestroy: false,
						draggable: true,
				        message: function(dialog) {
				        	
				        	lovClientContainer.css({"display":"block"});
				        	
				        	return lovClientContainer;
				        }
				    });
					
				} else {
					Message.showMessages($('#facturaAlertMessages'), $("#facturaMessages"), data.message);
				}
		   },
		   error:function(data){
			   Message.showMessages($('#facturaAlertMessages'), $("#facturaMessages"), data.responseJSON.message);
			   
			   return;
		   }
		});
    	
		return;
	});
	
	$("#btnSearchTrader").on("click", function(){
		
		var btraderidElement = $("#btraderid");
		
		var value = btraderidElement.val();
		if(value != null && value != ""){
			return Bill.getTraderById(value);
		}
		
    	$.ajax({ 
		   type    : "GET",
		   url     : Constants.contextRoot + "/controller/html/trader?status=Activo",
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
				   		"pagingType": "simple",
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
				            btraderidElement.val(selectedId);
				            $("#btradername").val(selectedDescription + " / " + selectedDni);
				            $("#lov-container").css({"display": "none"});

				            BootstrapDialog.closeAll();
				            // si esta todo ok entonces doy de alta ...
							$("#bcant_0").focus();
				        }
				        
						return;
				    });
					
					BootstrapDialog.show({
						type:BootstrapDialog.TYPE_DANGER,
						title: 'Vendedores',
						autodestroy: false,
				        message: function(dialog) {
				        	var lovContainer = $("#lov-container");
				        	
				        	lovContainer.css({"display":"block"});
				        	
				        	return lovContainer;
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
	
	$("#btnSearchCollector").on("click", function(){
		
		var value = $("#bCollectorId").val();
		if(value != null && value != ""){
			return Bill.getCollectorById(value);
		}
		
    	$.ajax({ 
		   type    : "GET",
		   url     : Constants.contextRoot + "/controller/html/collector",
		   dataType: 'json',
		   contentType: "application/json;",
		   success:function(data) {
				
			   var tbody = $("#tLovCollectorResult tbody");
			   
			   tbody.children('tr').remove();
			   
			   Message.hideMessages($('#facturaAlertMessages'), $("#facturaMessages"));
			   if(data != null && data.status == 0){

				   var table = $("#tLovCollectorResult").dataTable( {
				   		"data" : data.data,
				   		"bDestroy" : true,
				   		"pagingType": "simple",
				        "columns": [
							{ 
								"className": 'centered',
								"data": "id" 
							},
							{ 
								"className": 'centered',
								"data": "zone" 
							},
				            { 	
				            	"className": 'centered',
				            	"data": "description" 
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
				   
				   	$('#tLovCollectorResult tbody').on('mouseover', 'tr', function () {
						$(this).css({"cursor": "pointer"});	
						
						return;
					});
					
					$('#tLovCollectorResult tbody').on( 'click', 'tr', function () {
				        if ( $(this).hasClass('selected') ) {
				            $(this).removeClass('selected');
				        } else {
				            table.$('tr.selected').removeClass('selected');
				            $(this).addClass('selected');
				            
				            var selectedId = $(this).children('td').eq(0).html().trim();
				            var zone = $(this).children('td').eq(1).html().trim();
				            var selectedDescription = $(this).children('td').eq(2).html().trim();
				            
				            $("#bcobrador").val(selectedId);
				            $("#bCollectorId").val(zone);
				            $("#bCollectorDescription").val(selectedDescription);
				            $("#lov-collector-container").css({"display": "none"});

							// si esta todo ok entonces doy de alta ...
							//$("#bClientId").focus();

				            BootstrapDialog.closeAll();
				        }
				        
						return;
				    });
					
					BootstrapDialog.show({
						type:BootstrapDialog.TYPE_DANGER,
						title: 'Cobradores',
						autodestroy: false,
						draggable: true,
				        message: function(dialog) {
				        	
				        	$("#lov-collector-container").css({"display":"block"});
				        	
				        	return $("#lov-collector-container");
				        }
				    });
					
				} else {
					Message.showMessages($('#facturaAlertMessages'), $("#facturaMessages"), data.message);
				}
		   },
		   error:function(data){
			   Message.showMessages($('#facturaAlertMessages'), $("#facturaMessages"), data.responseJSON.message);
			   
			   return;
		   }
		});
		
		return;
	});
	
	$("#billNumber").focus();
	
	$("#billNumber").on('blur', function(){
		
		var creditNumber = $(this).val();
		
		if(creditNumber != null && creditNumber != ""){
			Bill.searchByCreditNumber($(this).val());
		}
		
		return;
	});
	
	$("#btnFirstNext").on('click', function(){
		
		var c = 0;
		
		$("#frmBillFirst").on('invalid.bs.validator', 
			function(e){
			    c++;
				
				return;
		});
		
		$("#frmBillFirst").validator('validate');
		
		if(c == 0){
			// si esta todo ok entonces doy de alta ...
			Bill.doPanelEnabled("#pnlClient");
			$("#bname").focus();
		}
		
		return;
	});
	
	$("#btnSecondNext").on('click', function(){
		
		var c = 0;
		
		$("#frmBillSecond").on('invalid.bs.validator', 
			function(e){
			    c++;
				
				return;
		});
		
		$("#frmBillSecond").validator('validate');
		
		if(c == 0){
			// si esta todo ok entonces doy de alta ...
			Bill.doPanelEnabled("#pnlTrader");
			$("#btraderid").focus();
		}
		
		return;
	});
	
	$("#btnThirdNext").on('click', function(){
		
		var c = 0;
		
		$("#frmBillThird").on('invalid.bs.validator', 
			function(e){
			    c++;
				
				return;
		});
		
		$("#frmBillThird").validator('validate');
		
		if(c == 0){
			// si esta todo ok entonces doy de alta ...
			Bill.doPanelEnabled("#pnlProduct");
			$("#bcant_0").focus();
		}
		
		return;
	});
	
	$("#btnFourNext").on('click', function(){
		
		var c = 0;
		
		$("#frmBillFour").on('invalid.bs.validator', 
			function(e){
			    c++;
				
				return;
		});
		
		$("#frmBillFour").validator('validate');
		
		if(c == 0){
			Bill.calculateCuotaDiaria();
			Bill.calculateImporteTotal();

			// si esta todo ok entonces doy de alta ...
			Bill.doPanelEnabled("#pnlFinalize");
			$("#btnFinalize").focus();
		}
		
		return;
	});
	
	$("#btnFinalize").on('click', function(){
		
		BootstrapDialog.show({
            title: 'Aviso!',
            message: 'CUIDADO! Esta seguro de que la factura es correcta?',
            type: BootstrapDialog.TYPE_DANGER, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
            closable: true, // <-- Default value is false
            draggable: true, // <-- Default value is false
            onshow: function(dialog) {
            	
            	return;
            },
            buttons: [{
                label: '<i class="glyphicon glyphicon-remove"></i>&nbsp;Cancelar',
                hotkey: 65, // Keycode of keyup event of key 'A' is 65.
                action: function(dialog) {
                	
                	dialog.close();
                	
                	return;
                }
            }, {
                label: '<i class="glyphicon glyphicon-ok"></i>&nbsp;Aceptar',
                hotkey: 13,
                cssClass: 'btn-success',
                action: function(dialog) {
                	$("#frmBillAdd").validator('destroy');
                	
                	var c = 0;
            		
            		$("#frmBillAdd").on('invalid.bs.validator', 
            			function(e){
            			    c++;
            				
            				return;
            		});
            		
            		$("#frmBillAdd").validator('validate');
            		
            		var clientId = $("#billClientIdSelected").val();

            		if(clientId == null || clientId == ""){
            			Client.searchByName($("#baddress").val());
            		}
            		clientId = $("#billClientIdSelected").val();
            		if(clientId == null || clientId == ""){
            			c++;
            		}
            		
            		if(c == 0){
            			Bill.doFinalize();
            			dialog.close();
            		} else {
            			dialog.close();
            		}
            		
            		return;
                }
            }]
        });
		
		return;
	});
	
	return;
}

Bill.initModalTrader = function(){
	
	return;
}

Bill.doFinalize = function(){
	
	// TODO
	var startDate = $("#billDateValue").val();
	var billNumber = $("#billNumber").val();
	var clientId = $("#billClientIdSelected").val();
	var collectorId = $("#bcobrador").val();
	var traderId = $("#btraderid").val();
	
	var obj = new Object();

	obj.startDate = startDate;
	obj.creditNumber = billNumber;
	obj.collectorId = collectorId;
	
	obj.clientId = clientId;
	
	obj.traderId = traderId;

	obj.billProducts = [];

	var billProductList = $("div[id*='product_']");
	
	var i = 0;
	
	$.each(billProductList, function(){
		
		var productCant = $(this).find("#bcant_" + i).val();
		var productId = $(this).find("#billProductId_" + i).val();
		var productPrice = $(this).find("#billProductPrice_" + i).val();
		var dailyInstallment = $(this).find("#bcuotadiaria_" + i).val();
		var amount = $(this).find("#bimp_" + i).val();
		
		var installmentAmount = $(this).find("#billInstallmentAmount_" + i).val();
		
		if(productCant != null && productCant != ""){
			if(productId != null && productId != ""){
				if(productPrice != null && productPrice != ""){
					if(dailyInstallment != null && dailyInstallment != ""){
						if(amount != null && amount != ""){
							var billProduct = new Object();

							billProduct.count = productCant;
							billProduct.productId = productId;
							billProduct.price = productPrice;
							billProduct.dailyInstallment = dailyInstallment;
							billProduct.amount = amount;
							
							obj.billProducts.push(billProduct);
						}
					}
				}
			}	
		}
		
		i++;
		
		return;
	});
	obj.totalAmount = $("#bimpTotal").val();
	obj.totalDailyInstallment = $("#bcuotaTotal").val();
	obj.overdueDays = 0;
	
	$.ajax({ 
	   type    : "POST",
	   url     : Constants.contextRoot + "/controller/html/bill",
	   dataType: 'json',
	   data: JSON.stringify(obj),
	   contentType: "application/json;",
	   success:function(data) {
		   Message.hideMessages($('#facturaAlertMessages'), $("#facturaMessages"));
		   if(data != null && data.status == 0){

			   Bill.resetPage();
			   $("html, body").animate({"scrollTop":"-590"}, "slow");
			   
			   Message.showMessages($('#facturaAlertMessages'), $("#facturaMessages"), 
					   "Factura creada correctamente. Para mayor informaci&oacute;n vaya a Detalle de Cr&eacute;dito.", "alert-warning", "glyphicon glyphicon-info-sign", "Bien! ");
			   setTimeout(function(){$("#facturaMessages").addClass("hide");}, 1500);
			   
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

Bill.calculateCuotaDiaria = function(){
	var elements = $("input[id*='bcuotadiaria_']");
	var sum = 0;
	$.each(elements, function(){
		var value = $(this).val();
		if(value != null && value != "" && value != undefined){
			sum = sum + parseFloat(Math.round(value * 100) / 100);
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
		var value = $(this).val();
		if(value != null && value != "" && value != undefined){
			sum = sum + parseFloat(Math.round(value * 100) / 100);
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

Bill.showLovProduct = function(elementId){
	
	
	var idValue = elementId.substring(elementId.indexOf("_") + 1);

	var value = $("#bProductCode_" + idValue).val();
	if(value != null && value != ""){
		return Bill.getProductByCode(idValue, value);
	}
	
	$("#elementSelectedId").val(idValue);
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/product",
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
			
		   var tbody = $("#tLovProductResult tbody");
		   
		   tbody.children('tr').remove();
		   
		   Message.hideMessages($('#facturaAlertMessages'), $("#facturaMessages"));
		   if(data != null && data.status == 0){

			   var table = $("#tLovProductResult").dataTable( {
			   		"data" : data.data,
			   		"bDestroy" : true,
			   		"pagingType": "simple",
			        "columns": [
						{ 
							"className": 'centered',
							"data": "id" 
						},
						{ 
							"className": 'centered',
							"data": "code" 
						},
			            { 	
			            	"className": 'centered',
			            	"data": "description" 
			            },
			            { 	
			            	"className": 'centered',
			            	"data": "price" 
			            },
			            { 	
			            	"className": 'centered',
			            	"data": "dailyInstallment" 
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
			   
			   	$('#tLovProductResult tbody').on('mouseover', 'tr', function () {
					$(this).css({"cursor": "pointer"});	
					
					return;
				});
				
				$('#tLovProductResult tbody').on( 'click', 'tr', function () {
			        if ( $(this).hasClass('selected') ) {
			            $(this).removeClass('selected');
			        } else {
			            table.$('tr.selected').removeClass('selected');
			            $(this).addClass('selected');
			            
			            var selectedId = $(this).children('td').eq(0).html().trim();
			            var selectedCode = $(this).children('td').eq(1).html().trim();
			            var selectedDescription = $(this).children('td').eq(2).html().trim();
			            var price = $(this).children('td').eq(3).html().trim();
			            var dailyInstallment = $(this).children('td').eq(4).html().trim();
			            
			            var idValue = $("#elementSelectedId").val();
			            
			            var realPrice = 0;
			            if(price !=null && price != ""){
			            	realPrice = parseFloat(Math.floor(price * 100) / 100);
			            }
			            
			            $("#billProductId_" + idValue).val(selectedId);
			            
			            $("#billInstallmentAmount_" + idValue).val(dailyInstallment);
			            
			            $("#bname_" + idValue).val(selectedCode + " - " + selectedDescription + " - $" + realPrice);
			            $("#bProductCode_" + idValue).val(selectedCode);
			            
			            var cant = $("#bcant_" + idValue).val();
			            if(cant == null || cant == ""){
			            	cant = 0;
			            }
			            $("#bcuotadiaria_" + idValue).val(dailyInstallment * cant);
			            $("#bimp_" + idValue).val(realPrice * cant);
			            $("#billProductPrice_" + idValue).val(realPrice);
			            $("#lov-client-container").css({"display": "none"});
			            
			    		Bill.calculateCuotaDiaria();
			    		Bill.calculateImporteTotal();

			            BootstrapDialog.closeAll();
			        }
			        
					return;
			    });
				
				BootstrapDialog.show({
					type:BootstrapDialog.TYPE_DANGER,
					title: 'Productos',
					autodestroy: false,
					draggable: true,
			        message: function(dialog) {
			        	
			        	$("#lov-product-container").css({"display":"block"});
			        	
			        	return $("#lov-product-container");
			        }
			    });
				
			} else {
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


Bill.initCantInput = function(){
	
	$("input[id*='bcant_']").on('blur', function(){
		
		Bill.calculateImp($(this));
		Bill.calculateImporteTotal();
		
		return;
	});
	
	return;
}


Bill.calculateImp = function(element){
	var id = element.attr("id");
	
	var idValue = id.substring(id.indexOf("_") + 1);
	
	var value = $("#billProductPrice_" + idValue).val();
	
	if(value == null || value == "" || value === undefined){
		return false;
	}
	var price = parseFloat($("#billProductPrice_" + idValue).val());
	
	var cant = element.val();
	if(cant != ""){
		cant = parseInt(cant);
	} else {
		cant = 0;
	}
	
	var imp = price * cant;
	
	$("#bimp_" + idValue).val(imp.toFixed(2));
	
	var installAmountElement = $("#billInstallmentAmount_" + idValue);
	
	var iAmount = installAmountElement.val();
	
	if(iAmount != null && iAmount != ""){
		var installmentAmount = parseFloat(iAmount);
		if(installmentAmount === undefined || installmentAmount == null || installmentAmount == ""){
			installmentAmount = 0;
		}
		iAmount = installmentAmount * cant;
		
		$("#bcuotadiaria_" + idValue).val(iAmount);
	}
	
	return;
}

Bill.resetFirst = function(){
	$("#billDate").val("");
	$("#billNumber").val("");
	$("#bnroticket").val("");
	$("#bcobrador").val("");
	$("#bCollectorId").val("");
	$("#bCollectorDescription").val("");
	$("#batraso").val("");
	
	return;
}

Bill.resetSecond = function(){
	$("#billClientIdSelected").val("");
	$("#bClientId").val("");
	$("#baddress").val("");
	
	return;
}

Bill.resetThird = function(){
	$("#btraderid").val("");
	$("#btradername").val("");
	
	return;
}

Bill.resetFour = function(){
	$("#bcant_0").val("");
	$("#bProductCode_0").val("");
	$("#bname_0").val("");
	$("#bcuotadiaria_0").val("");
	$("#bimp_0").val("");
	$("#bcant_1").val("");
	$("#bProductCode_1").val("");
	$("#bname_1").val("");
	$("#bcuotadiaria_1").val("");
	$("#bimp_1").val("");

	$("#bcant_2").val("");
	$("#bProductCode_2").val("");
	$("#bname_2").val("");
	$("#bcuotadiaria_2").val("");
	$("#bimp_2").val("");
	
	$("#bcant_3").val("");
	$("#bProductCode_3").val("");
	$("#bname_3").val("");
	$("#bcuotadiaria_3").val("");
	$("#bimp_3").val("");
	
	$("#bcant_4").val("");
	$("#bProductCode_4").val("");
	$("#bname_4").val("");
	$("#bcuotadiaria_4").val("");
	$("#bimp_4").val("");
	
	$("#bcuotaTotal").val("");
	$("#bimpTotal").val("");
	
	var list = $("div[id*='product_']");
	
	if(list.length > 5){
		for(var i = list.length-1;i>1;i--){
			$("#product_" + i).remove();
		}
	}
	
	return;
}

Bill.resetPage = function(){
	Bill.resetFirst();
	Bill.resetSecond();
	Bill.resetThird();
	Bill.resetFour();
	$("#frmBillAdd").validator('destroy');
	$("#billNumber").focus();
	
	return;
}


Bill.initControls = function(){
	$("#billNumber").keyup(function(e){
		if(e.keyCode == 13) {
			$("#bCollectorId").focus();
		}
	    
	    return;
	});
	
	$("#bCollectorId").keyup(function(e){
		if(e.keyCode == 10 || e.keyCode == 13) {
			var value = $(this).val();
			if(value != null && value != ""){
				Bill.getCollectorById(value);
				$("#baddress").focus();
			} else {
				$("#baddress").focus();			
			}
		} else {
			$("#bCollectorDescription").val("");
		}
	    
	    return;
	});
	
	$('#bCollectorId').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
	    	var value = $(this).val();
			if(value != null && value != ""){
				Bill.getCollectorById(value);
				$("#baddress").focus();
			} else {
				$("#baddress").focus();			
			}
	    }
	    
	    return;
	});
	
	var btraderidElement = $("#btraderid");
	
	$("#baddress").keyup(function(e){
		if(e.keyCode == 13) {
			btraderidElement.focus();			
		} else {
			$("#billClientIdSelected").val("");
		}
	    
	    return;
	});
	
	$('#baddress').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
	    	btraderidElement.focus();
	    }
	    
	    return;
	});
	
	btraderidElement.keyup(function(e){
		if(e.keyCode == 13) {
			var value = $(this).val();
			if(value != null && value != ""){
				Bill.getTraderById(value);
			} else {
				$("#bcant_0").focus();
			}
		} else {
			$("#btradername").val("");
		}
		
		return;
	});
	
	btraderidElement.keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
	    	var value = $(this).val();
			if(value != null && value != ""){
				Bill.getTraderById(value);
			} else {
				$("#bcant_0").focus();
			}
	    }
	    
	    return;
	});
	
	///////////////////////////////////////////////////
	
	$("#billClientName").keyup(function(e){
		if(e.keyCode == 13) {
			$("#billClientDocumentNumber").focus();			
		}
	    
	    return;
	});
	
	$('#billClientName').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#billClientDocumentNumber").focus();			
	    }
	    
	    return;
	});
	
	$("#billClientDocumentNumber").keyup(function(e){
		if(e.keyCode == 13) {
			$("#billClientEmail").focus();			
		}
	    
	    return;
	});
	
	$('#billClientDocumentNumber').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#billClientEmail").focus();			
	    }
	    
	    return;
	});
	
	$("#billClientEmail").keyup(function(e){
		if(e.keyCode == 13) {
			$("#billClientCompanyPhone").focus();			
		}
	    
	    return;
	});
	
	$('#billClientEmail').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#billClientCompanyPhone").focus();			
	    }
	    
	    return;
	});
	
	$("#billClientCompanyPhone").keyup(function(e){
		if(e.keyCode == 13) {
			$("#billClientCompanyAddress").focus();			
		}
	    
	    return;
	});
	
	$('#billClientCompanyPhone').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#billClientCompanyAddress").focus();			
	    }
	    
	    return;
	});
	
	$("#billClientCompanyAddress").keyup(function(e){
		if(e.keyCode == 13) {
			$("#billClientNearStreets").focus();			
		}
	    
	    return;
	});
	
	$('#billClientCompanyAddress').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#billClientNearStreets").focus();			
	    }
	    
	    return;
	});
	
	$("#billClientNearStreets").keyup(function(e){
		if(e.keyCode == 13) {
			$("#billClientCompanyCity").focus();			
		}
	    
	    return;
	});
	
	$('#billClientNearStreets').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#billClientCompanyCity").focus();			
	    }
	    
	    return;
	});
	
	$("#billClientCompanyCity").keyup(function(e){
		if(e.keyCode == 13) {
			$("#billClientCompanyType").focus();			
		}
	    
	    return;
	});
	
	$('#billClientCompanyCity').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#billClientCompanyType").focus();			
	    }
	    
	    return;
	});
	
	$("#billClientCompanyType").keyup(function(e){
		if(e.keyCode == 13) {
			$("#billClientPhone").focus();			
		}
	    
	    return;
	});
	
	$('#billClientCompanyType').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#billClientPhone").focus();			
	    }
	    
	    return;
	});
	
	$("#billClientPhone").keyup(function(e){
		if(e.keyCode == 13) {
			$("#billClientAddress").focus();			
		}
	    
	    return;
	});
	
	$('#billClientPhone').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#billClientAddress").focus();			
	    }
	    
	    return;
	});
	
	$("#billClientAddress").keyup(function(e){
		if(e.keyCode == 13) {
			$("#billClientCity").focus();			
		}
	    
	    return;
	});
	
	$('#billClientAddress').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#billClientCity").focus();			
	    }
	    
	    return;
	});
	
	$("#billClientCity").keyup(function(e){
		if(e.keyCode == 13) {
			$("#btnAccept").focus();			
		}
	    
	    return;
	});
	
	$('#billClientCity').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
			$("#btnAccept").focus();			
	    }
	    
	    return;
	});
	
	return;
}

Bill.initProductControls = function(bcantElements, productCodeElements, bcuotadiariaElements, bimpElements){
	
	bcantElements.keyup(function(e){
		if(e.keyCode == 13) {
			var id = $(this).attr('id');
			var index = id.substring(id.indexOf('_') + 1);
			
			$("#bProductCode_" + index).focus();
		}
		
		return;
	});
	
	bcantElements.keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
	    	var id = $(this).attr('id');
	    	var index = id.substring(id.indexOf('_') + 1);
			$("#bProductCode_" + index).focus();
	    }
	    
	    return;
	});
	
	productCodeElements.keyup(function(e){
		if(e.keyCode == 13) {
			var value = $(this).val();
			var id = $(this).attr('id');
			var index = id.substring(13);
			if(value != null && value != ""){
				Bill.getProductByCode(index, value);
			} else {
				$("#bcuotadiaria_" + index).focus();
			}
		}
		
		return;
	});
	
	productCodeElements.keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
	    	var value = $(this).val();
	    	var id = $(this).attr('id');
			var index = id.substring(13);
			if(value != null && value != ""){
				Bill.getProductByCode(index, value);
			} else {
				$("#bcuotadiaria_" + index).focus();
			}
	    }
	    
	    return;
	});
	
	bcuotadiariaElements.keyup(function(e){
		if(e.keyCode == 13) {
			var id = $(this).attr('id');
			var index = id.substring(id.indexOf('_') + 1);
			
			$("#bimp_" + index).focus();
		}
		
		return;
	});
	
	bcuotadiariaElements.keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
	    	var id = $(this).attr('id');
	    	var index = id.substring(id.indexOf('_') + 1);
			$("#bimp_" + index).focus();
	    }
	    
	    return;
	});
	
	bimpElements.keyup(function(e){
		if(e.keyCode == 13) {
			var id = $(this).attr('id');
			var index = id.substring(id.indexOf('_') + 1);
			
			var nextElement = $("#bcant_" + (parseInt(index) + 1));
			if(nextElement.length > 0){
				nextElement.focus();
			} else {
				$("#btnFinalize").focus();
			}
		}
		
		return;
	});
	
	bimpElements.keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
	    	var id = $(this).attr('id');
	    	var index = id.substring(id.indexOf('_') + 1);
	    	
	    	var nextElement = $("#bcant_" + (parseInt(index) + 1));
			if(nextElement.length > 0){
				nextElement.focus();
			} else {
				$("#btnFinalize").focus();
			}
	    }
	    
	    return;
	});
	
	return;
}

Bill.getClientById = function(id){
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/client?id=" + id,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {

		   $("#baddress").parent().next().html("");
		   $("#baddress").val("");
		   $("#baddress").parent().parent().removeClass("has-error");
		   
		   Message.hideMessages($('#facturaAlertMessages'), $("#facturaMessages"));
		   
		   if(data != null && data.status == 0){
			   
			   var list = data.data;
			   
			   if(list.length > 0){
				   var client = list[0];
				   
				   $("#billClientIdSelected").val(client.id);
		           $("#baddress").val(client.name);				   
			
		           $("#btraderid").focus();
			   } else {
				   $("#bClientId").focus();
				   $("#baddress").parent().next().append("Cliente no encontrado");
				   $("#baddress").parent().parent().addClass("has-error");
			   }
			   
		   } else {
				Message.showMessages($('#facturaAlertMessages'), $("#facturaMessages"), data.message);
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#facturaAlertMessages'), $("#facturaMessages"), data.responseJSON.message);
		   
		   return false;
	   }
	});
	
	return;
}

Bill.getTraderById = function(id){
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/trader?id=" + id,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {

		   $("#btradername").parent().next().html("");
		   $("#btradername").val("");
		   $("#btradername").parent().parent().removeClass("has-error");
		   
		   Message.hideMessages($('#facturaAlertMessages'), $("#facturaMessages"));
		   
		   if(data != null && data.status == 0){
			   
			   var list = data.data;
			   
			   if(list.length > 0){
				   var trader = list[0];
				   
				   $("#btraderid").val(trader.id);
		           $("#btradername").val(trader.name + " / " + trader.documentNumber);
				   
		           $("#bcant_0").focus();
			   } else {
				   $("#btraderid").focus();
				   $("#btradername").parent().next().append("Vendedor no encontrado");
				   $("#btradername").parent().parent().addClass("has-error");
			   }
			   
		   } else {
				Message.showMessages($('#facturaAlertMessages'), $("#facturaMessages"), data.message);
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#facturaAlertMessages'), $("#facturaMessages"), data.responseJSON.message);
		   
		   return false;
	   }
	});
	
	return;
}

Bill.getCollectorById = function(zone){
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/collector?zone=" + zone,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {

		   $("#bCollectorDescription").parent().next().html("");
		   $("#bCollectorDescription").val("");
		   $("#bCollectorDescription").parent().parent().removeClass("has-error");
		   
		   Message.hideMessages($('#facturaAlertMessages'), $("#facturaMessages"));
		   
		   if(data != null && data.status == 0){
			   
			   var list = data.data;
			   
			   if(list.length > 0){
				   var element = list[0];
				   
				   $("#bcobrador").val(element.id);
				   $("#bCollectorId").val(element.zone)
		           $("#bCollectorDescription").val(element.description);				   
			
		           $("#bClientId").focus();
			   } else {
				   $("#bCollectorId").focus();
				   $("#bCollectorDescription").parent().next().append("Cobrador no encontrado");
				   $("#bCollectorDescription").parent().parent().addClass("has-error");
			   }
			   
		   } else {
				Message.showMessages($('#facturaAlertMessages'), $("#facturaMessages"), data.message);
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#facturaAlertMessages'), $("#facturaMessages"), data.responseJSON.message);
		   
		   return false;
	   }
	});
	
	return;
}

Bill.getProductByCode = function(index, code){
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/product?code=" + code,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {

		   $("#bname_" + index).parent().next().html("");
		   $("#bname_" + index).val("");
		   $("#bname_" + index).parent().parent().removeClass("has-error");
		   
		   Message.hideMessages($('#facturaAlertMessages'), $("#facturaMessages"));
		   
		   if(data != null && data.status == 0){
			   
			   var list = data.data;
			   
			   if(list.length > 0){
				   var e = list[0];
				   
				   var selectedId = e.id;
		           var selectedCode = e.code;
		           var selectedDescription = e.description;
		           var price = e.price;
		           var dailyInstallment = e.dailyInstallment;
		            
		           var realPrice = 0;
		           if(price != ""){
		        	   realPrice = parseFloat(Math.floor(price * 100) / 100);
		           }
		            
		           $("#billProductId_" + index).val(selectedId);
		           var cant = $("#bcant_" + index).val();
		           if(cant == null || cant == ""){
		        	   cant = 0;
		           }
		           $("#billInstallmentAmount_" + index).val(e.dailyInstallment);
		           
		           $("#bcuotadiaria_" + index).val(e.dailyInstallment * cant);
		           $("#bimp_" + index).val(realPrice * cant);
		           $("#billProductPrice_" + index).val(realPrice);
		           
		           Bill.calculateCuotaDiaria();
		           Bill.calculateImporteTotal();
				   
				   $("#bProductCode_" + index).val(e.code);
		           $("#bname_" + index).val(e.code + " / " + e.description + " / " + e.price);
				   
		           $("#bcuotadiaria_" + index).focus();
			   } else {
				   $("#bProductCode_" + index).focus();
				   $("#bname_" + index).parent().next().append("Producto no encontrado");
				   $("#bname_" + index).parent().parent().addClass("has-error");
			   }
			   
		   } else {
				Message.showMessages($('#facturaAlertMessages'), $("#facturaMessages"), data.message);
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#facturaAlertMessages'), $("#facturaMessages"), data.responseJSON.message);
		   
		   return false;
	   }
	});
	
	return;
}

Bill.initDetail = function(billId){
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/bill/detail?billId=" + billId,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
			
		   Message.hideMessages($('#billDetailAlertMessages'), $("#billDetailMessages"));
		   if(data != null && data.status == 0){
			   var list = data.data;
			   
			   if(list.length > 0){
				   
				   var d = list[0];
				   
				   $("#clientName").append(d.clientName);
				   $("#clientAddress").append(d.clientAddress);
				   $("#traderName").append(d.traderName);
				   $("#creditDate").append(d.creditDate);
				   $("#creditAmount").append(d.creditAmount);
				   $("#installment").append(d.installmentAmount);
				   $("#firstInstallment").append(d.firstInstallmentAmount);
				   $("#remainingAmount").append(d.remainingAmount);
				   $("#completedDate").append(d.completedDate);
				   $("#customerCompanyType").append(d.customerCompanyType);
				   $("#billCollectorIdSelected").val(d.collectorId);
				   $("#collectorDescription").val(d.collectorDescription);
				   
				   $("#weekAmountContainer").append(d.weekAmount);
				   $("#currentAmountContainer").append(d.currentAmount);
				   
				   var statusElement = $("#status");
				   
				   statusElement.append(Bill.translateStatus(d.status));
				   
				   Bill.changeColorOnStatusContainer(statusElement, d.status);
				   
				   var devolutions = d.devolutions;
				   
				   var dBody = $("#tBillDevolution > tbody");

				   if(devolutions.length > 0){
					   dBody.children('tr').remove();
				   }
				   
				   for(var i=0;i<devolutions.length;i++){
					   var tr = $("<tr></tr>");
					   
					   var td0 = $("<td class=\"centered\"></td>").append(devolutions[i].date);
					   var td5 = $("<td class=\"centered\"></td>").append(devolutions[i].count);
					   var td1 = $("<td class=\"centered\"></td>").append(devolutions[i].productDescription);
					   var td2 = $("<td class=\"centered\"></td>").append(devolutions[i].amount);
					   var td3 = $("<td class=\"centered\"></td>").append(devolutions[i].installmentAmount);
					   
					   var detailDiv = $("<button id='btnDevObservation_" + i + "' type=\"button\" class=\"btn btn-xs btn-info\" data-toggle=\"popover\" data-placement=\"left\" title=\"Observaciones\" data-content=\"" + devolutions[i].observations + "\"><i class=\"glyphicon glyphicon-zoom-in\"></i></button>");
					   
					   var td4 = $("<td class=\"centered\"></td>").append(detailDiv);
					   
					   detailDiv.popover();
					   
					   tr.append(td0).append(td5).append(td1).append(td2).append(td3).append(td4);
					   
					   dBody.append(tr);
				   }
				   
				   var payments = d.payments;
				   var pBody = $("#tBillPayment > tbody");
				   
				   if(payments.length > 0){
					   pBody.children('tr').remove();
				   }
				   
				   for(var i=0;i<payments.length;i++){
					   var tr = $("<tr id='paymentRow_" + payments[i].id + "'></tr>");
					   
					   var td0 = $("<td class=\"centered\"></td>").append(payments[i].date);
					   var td1 = $("<td class=\"centered\"></td>").append(payments[i].collector);
					   var td2 = $("<td class=\"centered\"></td>").append(payments[i].amount);
					   var td3 = $("<td class=\"centered\"></td>").append(payments[i].traderPayment);
					   
					   var removeBtn = $("<a href='javascript:void(0);' class='btn btn-xs btn-danger' data-paymentid='" + payments[i].id + "' onclick='javascript:Payment.remove(" + payments[i].id + ", \"" + payments[i].date + "\")'><i class='fa fa-trash'></i></a>");

					   var td4 = $("<td class=\"centered\"></td>").append(removeBtn);
					   
					   tr.append(td0).append(td1).append(td2).append(td3).append(td4);
					   
					   pBody.append(tr);
				   }
				   
				   var products = d.products;
				   pBody = $("#tBillProducts > tbody");
				   
				   if(products.length > 0){
					   pBody.children('tr').remove();
				   }
				   
				   for(var i=0;i<products.length;i++){
					   var tr = $("<tr></tr>");
					   
					   var td0 = $("<td class=\"centered\"></td>").append(products[i].count);
					   var td1 = $("<td class=\"centered\"></td>").append(products[i].codProducto);
					   var td2 = $("<td class=\"centered\"></td>").append(products[i].description);
					   var td3 = $("<td class=\"centered\"></td>").append(products[i].installmentAmount);
					   var td4 = $("<td class=\"centered\"></td>").append(products[i].totalAmount);
					   
					   tr.append(td0).append(td1).append(td2).append(td3).append(td4);
					   
					   pBody.append(tr);
				   }
				   
				   var discounts = d.discounts;
				   pBody = $("#tBillDiscount > tbody");
				   
				   if(discounts.length > 0){
					   pBody.children('tr').remove();
				   }
				   
				   for(var i=0;i<discounts.length;i++){
					   var tr = $("<tr></tr>");
					   
					   var td0 = $("<td class=\"centered\"></td>").append(discounts[i].date);
					   var td1 = $("<td class=\"centered\"></td>").append(discounts[i].amount);
					   
					   var detailDiv = $("<button id='btnDiscountObservation_" + i + "' type=\"button\" class=\"btn btn-xs btn-info\" data-toggle=\"popover\" data-placement=\"left\" title=\"Observaciones\" data-content=\"" + discounts[i].observations + "\"><i class=\"glyphicon glyphicon-zoom-in\"></i></button>");
					   
					   var td2 = $("<td class=\"centered\"></td>").append(detailDiv);
					   
					   detailDiv.popover();
					   
					   tr.append(td0).append(td1).append(td2);
					   
					   pBody.append(tr);
				   }
				   
				   var reductions = d.reductions;
				   pBody = $("#tBillReduction > tbody");
				   
				   if(reductions.length > 0){
					   pBody.children('tr').remove();
				   }
				   
				   for(var i=0;i<reductions.length;i++){
					   var tr = $("<tr id=\"reduction-row_" + billId + "\"></tr>");
					   
					   var td0 = $("<td class=\"centered\"></td>").append(reductions[i].date);
					   var td1 = $("<td class=\"centered\"></td>").append(reductions[i].amount);
					   
					   var detailDiv = $("<button id='btnReductionObservation_" + i + "' type=\"button\" class=\"btn btn-xs btn-info\" data-toggle=\"popover\" data-placement=\"left\" title=\"Observaciones\" data-content=\"" + reductions[i].observations + "\"><i class=\"glyphicon glyphicon-zoom-in\"></i></button>");
					   
					   var actionDiv = $("<button id='btnRemoveReduction_" + i + "' onclick=\"javascript:Bill.removeReduction('" + reductions[i].id + "', '" + billId + "');\" type=\"button\" class=\"btn btn-xs btn-danger\" title=\"Eliminar Baja\"><i class=\"glyphicon glyphicon-trash\"></i></button>");

					   var td2 = $("<td class=\"centered\"></td>").append(detailDiv).append("&nbsp;").append(actionDiv);
					   
					   detailDiv.popover();
					   
					   tr.append(td0).append(td1).append(td2);
					   
					   pBody.append(tr);
				   }
			   }
			   
		   } else {
			   Message.showMessages($('#billDetailAlertMessages'), $("#billDetailMessages"), data.message);
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#billDetailAlertMessages'), $("#billDetailMessages"), data.responseJSON.message);
		   
		   return;
	   }
	});
	
	return;
}

Bill.showClientModal = function(){
	
	var dialog = new BootstrapDialog({
		onshown: function(){
			$("#billClientName").focus();
			
			return;
		},
		onhidden:function(){
			Bill.resetBillModal();
			
			return;
		},
		draggable: true,
		type:BootstrapDialog.TYPE_DANGER,
		title: 'Clientes',
		autodestroy: false,
		cssClass: 'dialog-client',
        message: function(dialog) {
        	
        	$("#modal-bill-client-container").css({"display":"block"});
        	
        	return $("#modal-bill-client-container");
        },
        buttons: [{
        	id: 'btnCancel',
        	label: 'Cancelar',
        	icon: 'glyphicon glyphicon-remove-sign',
        	action: function(dialog){
        		var btn = this;
        		Message.hideMessages($('#modalClientAlertMessages'), $("#modalClientMessages"));
        		dialog.close();
        		
        		return;
        	}
        },
        {
        	id: 'btnAccept',
        	label: 'Guardar',
        	icon: 'glyphicon glyphicon-ok-sign',
        	cssClass: 'btn-success',
        	action: function(dialog){
        		var btn = this;
        		var c = 0;
				
				$("#frmBillAddClient").on('invalid.bs.validator', 
					function(e){
					    c++;
						
						return;
				});
				
				$("#frmBillAddClient").validator('validate');
				
				if(c == 0){
					dialog.enableButtons(false);
					dialog.setClosable(false);
	        		btn.spin();
	        		
					// si esta todo ok entonces doy de alta ...
	        		Bill.addClient(dialog, btn);
				}
        		
        		return;
        	}
        }]
    });
	dialog.setSize(BootstrapDialog.SIZE_WIDE);
	dialog.open();
	
	return;
}

Bill.resetBillModal = function(){
	$("#billClientName").val('');
	$("#billClientDocumentNumber").attr("readonly", false).val('');
	$("#billClientEmail").val('');
	$("#billClientCompanyPhone").val('');
	$("#billClientCompanyAddress").val('');
	$("#billClientNearStreets").val('');
	$("#billClientCompanyCity").val('');
	$("#billClientCompanyType").val('');
	$("#billClientPhone").val('');
	$("#billClientAddress").val('');
	$("#billClientCity").val('');
	
	return;
}

Bill.addClient = function(dialog, btn){
	
	var name = $("#billClientName").val();
	var documentNumber = $("#billClientDocumentNumber").val();
	var email = $("#billClientEmail").val();
	var companyPhone = $("#billClientCompanyPhone").val();
	var companyAddress = $("#billClientCompanyAddress").val();
	var nearStreets = $("#billClientNearStreets").val();
	var companyCity = $("#billClientCompanyCity").val();
	var companyType = $("#billClientCompanyType").val();
	var phone = $("#billClientPhone").val();
	var address = $("#billClientAddress").val();
	var city = $("#billClientCity").val();
	
	var obj = new Object();
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
		   Message.hideMessages($('#modalClientAlertMessages'), $("#modalClientMessages"));
		   if(data != null && data.status == 0){
			   
			   var list = data.data;

			   if(list.length > 0){
				   
				   var r = list[0];
				   
				   var selectedId = r.id;
				   var selectedDescription = r.name;
				   var address = r.companyAddress;
				   var companyType = r.companyType;
				   
				   $("#bClientId").val(selectedId);
				   $("#billClientIdSelected").val(selectedId);
				   $("#baddress").val(selectedDescription + " / " + address + " / " + companyType);
				   
			   }
			   // comento este focus, porque me da un error jquery: Uncaught RangeError: Maximum call stack size exceeded
			   //$("#btraderid").focus();
			   
			   dialog.enableButtons(true);
			   dialog.setClosable(true);
       		   btn.stopSpin();
			   dialog.close();
			   
			   return;
		   }else{
			   Message.showMessages($('#modalClientAlertMessages'), $("#modalClientMessages"), data.message);
			   
			   dialog.enableButtons(true);
			   dialog.setClosable(true);
       		   btn.stopSpin();
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#modalClientAlertMessages'), $("#modalClientMessages"), data.responseJSON.message);
		   
		   dialog.enableButtons(true);
		   dialog.setClosable(true);
   		   btn.stopSpin();
		   
		   return;
	   }
	});
	
	return;
}

Bill.exportDetailToPdf = function(){
	
	$("#frmCreditDetailExportPdf").submit();
	
	return;
}

Bill.searchByCreditNumber = function(creditNumber){
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/bill?creditNumber=" + creditNumber,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {

		   $("#billCreditNumberErrorMessageDiv").html("");
		   $("#billNumber").parent().parent().removeClass("has-error");
		   
		   Message.hideMessages($('#facturaAlertMessages'), $("#facturaMessages"));
		   
		   if(data != null && data.status == 0){
			   
			   var list = data.data;
			   
			   if(list.length > 0){
				   //$("#bClientId").focus();
				   $("#billCreditNumberErrorMessageDiv").append("Nro de Crédito ya usado");
				   $("#billNumber").parent().parent().addClass("has-error");
			   }
			   
		   } else {
				Message.showMessages($('#facturaAlertMessages'), $("#facturaMessages"), data.message);
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#facturaAlertMessages'), $("#facturaMessages"), data.responseJSON.message);
		   
		   return false;
	   }
	});
	
	return;
}

Bill.translateStatus = function(status){
	var value = "INICIALIZADO";
    
	if(status == 'ACTIVE'){
    	value = "ACTIVO";
    } else if(status == 'CANCELED'){
    	value = 'CANCELADO';
    } else if(status == 'CANCELED_DISCOUNT'){
    	value = 'CANC/DESC';
    } else if(status == 'REDUCED'){
    	value = 'BAJA';
    }
    
	return value;
}

var isReductionObservationToggled = false;

Bill.showReductionObservations = function(){

	if(!isReductionObservationToggled){
		$("#btnReductionObservation").popover('show');
		isReductionObservationToggled = true;
	} else {
		$("#btnReductionObservation").popover('hide');
		isReductionObservationToggled = false;
	}
	
	
	return;
}

Bill.showDiscountObservations = function(idx){
	
	$("#btnReductionObservation").popover('show');
	
	return;
}

Bill.removeReduction = function(reductionId, billId){
	
	Message.hideMessages($('#billDetailAlertMessages'), $("#billDetailMessages"));
	
	
	BootstrapDialog.confirm({
		title: "Confirmación",
		message: "Esta seguro de eliminar la Baja seleccionada?",
		type: BootstrapDialog.TYPE_DANGER,
		draggable: true,
		btnCancelLabel: '<i class="glyphicon glyphicon-remove-sign"></i>&nbsp;NO', // <-- Default value is 'Cancel',
        btnOKLabel: '<i class="glyphicon glyphicon-ok-sign"></i>&nbsp;SI', // <-- Default value is 'OK',
        btnOKClass: 'btn-success',
		callback: function(result){
			if(result) {
				$.ajax({ 
				   type    : "DELETE",
				   url     : Constants.contextRoot + "/controller/html/productReduction/" + reductionId,
				   dataType: 'json',
				   contentType: "application/json;",
				   success:function(data) {

					   if(data != null && data.status == 0){
						   
						   $("#reduction-row_" + billId).children("td").remove();
						   $("#reduction-row_" + billId).append("<td colspan=\"3\" class=\"centered\">No se han encontrado resultados</td>");
						   
						   var statusContainer = $("#status");
						   
						   statusContainer.html(Bill.translateStatus("ACTIVE"));
						   
						   Bill.changeColorOnStatusContainer(statusContainer, "ACTIVE");
						   
					   } else {
							Message.showMessages($('#billDetailAlertMessages'), $("#billDetailMessages"), data.message);
					   }
				   },
				   error:function(data){
					   Message.showMessages($('#billDetailAlertMessages'), $("#billDetailMessages"), data.responseJSON.message);
					   
					   return false;
				   }
				});
				
			}
			
			return;
		}
	});
	
	return;
}

Bill.changeColorOnStatusContainer = function(statusElement, status){
	
	if(status == 'ACTIVE'){
	   statusElement.css("background-color", "#f0ad4e");
	} else if(status == 'CANCELED'){
	   statusElement.css("background-color", "#5cb85c");
	} else if(status == 'CANCELED_DISCOUNT'){
	   statusElement.css("background-color", "#428bca");
	} else if(status == 'REDUCED'){
	   statusElement.css("background-color", "#d9534f");
	}
	
	return;
}

Bill.showPaydays = function(){

	var billId = $("#billId").val();
	
	$.ajax({ 
		   type    : "GET",
		   url     : Constants.contextRoot + "/controller/html/bill/payday?billId=" + billId,
		   dataType: 'json',
		   contentType: "application/json;",
		   success:function(data) {
				
			   Message.hideMessages($('#billDetailAlertMessages'), $("#billDetailMessages"));
			   if(data != null && data.status == 0){
				   
				   var dialog = new BootstrapDialog({
						onshown: function(){
							
							$("#creditNumberPaydaySpan").append($("#creditNumber").val());
							
							Commons.selectDayOfWeek($("#weekSunday"), data.weekSunday);
				        	Commons.selectDayOfWeek($("#weekMonday"), data.weekMonday);
				        	Commons.selectDayOfWeek($("#weekTuesday"), data.weekTuesday);
				        	Commons.selectDayOfWeek($("#weekWednesday"), data.weekWednesday);
				        	Commons.selectDayOfWeek($("#weekThursday"), data.weekThursday);
				        	Commons.selectDayOfWeek($("#weekFriday"), data.weekFriday);
				        	Commons.selectDayOfWeek($("#weekSaturday"), data.weekSaturday);
							
							return;
						},
						onhidden:function(){
							
							$("#creditNumberPaydaySpan").html("");
							
							return;
						},
						draggable: true,
						type:BootstrapDialog.TYPE_DANGER,
						title: '<i class="fa fa-calendar-o"></i>&nbsp;D&iacute;as de Cobro',
						autodestroy: false,
				        message: function(dialog) {
				        	
				        	$("#modal-payday-container").css({"display":"block"});
				        	
				        	return $("#modal-payday-container");
				        },
				        buttons: [{
				        	id: 'btnCancelPayday',
				        	label: 'Cancelar',
				        	icon: 'glyphicon glyphicon-remove-sign',
				        	action: function(dialog){
				        		var btn = this;
				        		Message.hideMessages($('#modalClientAlertMessages'), $("#modalClientMessages"));
				        		dialog.close();
				        		
				        		return;
				        	}
				        },
				        {
				        	id: 'btnAcceptPayday',
				        	label: 'Guardar',
				        	icon: 'glyphicon glyphicon-ok-sign',
				        	cssClass: 'btn-success',
				        	action: function(dialog){
				        		var btn = this;
								dialog.enableButtons(false);
								dialog.setClosable(false);
				        		btn.spin();
				        		
								// si esta todo ok entonces doy de alta ...
				        		Bill.updatePayday(billId, dialog, btn);
				        		
				        		return;
				        	}
				        }]
				    });
					dialog.open();
				   
				   
				   
				   
			   } else {
				   Message.showMessages($('#billDetailAlertMessages'), $("#billDetailMessages"), data.message);
			   }
		   },
		   error:function(data){
			   Message.showMessages($('#billDetailAlertMessages'), $("#billDetailMessages"), data.responseJSON.message);
			   
			   return;
		   }
	});
	
	return;
}

Bill.updatePayday = function(billId, dialog, btn){
	
	var obj = new Object();
	obj.billId = billId;
	obj.weekSunday = $("#weekSunday").prop("checked");
	obj.weekMonday = $("#weekMonday").prop("checked");
	obj.weekTuesday = $("#weekTuesday").prop("checked");
	obj.weekWednesday = $("#weekWednesday").prop("checked");
	obj.weekThursday = $("#weekThursday").prop("checked");
	obj.weekFriday = $("#weekFriday").prop("checked");
	obj.weekSaturday = $("#weekSaturday").prop("checked");
	
	$.ajax({ 
	   type    : "POST",
	   url     : Constants.contextRoot + "/controller/html/bill/payday/" + billId,
	   dataType: 'json',
	   data: JSON.stringify(obj),
	   contentType: "application/json;",
	   success:function(data) {
		   Message.hideMessages($('#modalPaydayAlertMessages'), $("#modalPaydayMessages"));
		   if(data != null && data.status == 0){
			   
			   dialog.enableButtons(true);
			   dialog.setClosable(true);
       		   btn.stopSpin();
			   dialog.close();
			   
			   return;
		   }else{
			   Message.showMessages($('#modalPaydayAlertMessages'), $("#modalPaydayMessages"), data.message);
			   
			   dialog.enableButtons(true);
			   dialog.setClosable(true);
       		   btn.stopSpin();
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#modalPaydayAlertMessages'), $("#modalPaydayMessages"), data.responseJSON.message);
		   
		   dialog.enableButtons(true);
		   dialog.setClosable(true);
   		   btn.stopSpin();
		   
		   return;
	   }
	});
	
	return;
}

Bill.exportDetailToXls = function(){
	
	$("#frmCreditDetailExportXls").submit();
	
	return;
}
