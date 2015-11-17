Dev = function(){}

Dev.init = function(){
	
	return;
}

Dev.resetModal = function(){
	$("#devBillId").val("");
	$("#devDateValue").val("");
	$("#devAmount").val("");
	$("#devObservations").val("");
	$("#devProductCount").val("");
	$("#devProductId").val("");
	$("#devInstallment").val("");
	$("#devProductCode").val("");
	$("#devProductDescription").val("");
	
	return;
}

Dev.add = function(dialog, btn, responseHandler){
	var billId = $("#devBillId").val();

	var obj = new Object();
	obj.billId = billId;
	obj.amount = $("#devAmount").val();
	obj.observations = $("#devObservations").val();
	obj.date = $("#devDateValue").val();
	obj.productCount = $("#devProductCount").val();
	obj.productId = $("#devProductId").val();
	obj.productCode = $("#devProductCode").val();
	obj.productInstallment = $("#devInstallment").val();
	
	$.ajax({ 
	   type    : "POST",
	   url     : Constants.contextRoot + "/controller/html/dev",
	   dataType: 'json',
	   data: JSON.stringify(obj),
	   contentType: "application/json;",
	   success:function(data) {
		   Message.hideMessages($('#modalDevAlertMessages'), $("#modalDevMessages"));
		   if(data != null && data.status == 0){

			   dialog.enableButtons(true);
			   dialog.setClosable(true);
       		   btn.stopSpin();
			   dialog.close();

			   //BillHistory.init();
			   
			   responseHandler(data);
			   
			   return;
		   }else{
			   Message.showMessages($('#modalDevAlertMessages'), $("#modalDevMessages"), data.message);
			   
			   dialog.enableButtons(true);
			   dialog.setClosable(true);
       		   btn.stopSpin();
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#modalDevAlertMessages'), $("#modalDevMessages"), data.responseJSON.message);
		   dialog.enableButtons(true);
		   dialog.setClosable(true);
   		   btn.stopSpin();
		   
		   return;
	   }
	});
	
	return;
}

Dev.show = function(id){
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/dev?billId=" + id,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
			
		   var tbody = $("#tDevResult tbody");
		   
		   tbody.children('tr').remove();
		   
		   Message.hideMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"));
		   if(data != null && data.status == 0){

			   var table = $("#tDevResult").dataTable( {
				   "fnInitComplete": function(){
						$('#tBillResult tbody tr').each(function(){
							$(this).find('td:eq(1)').attr('nowrap', 'nowrap');
							
							return;
						});
						
						return;
					},
			   		"data" : data.data,
			   		"bDestroy" : true,
			   		responsive: true,
			        "columns": [
						{ 
							"className": 'centered',
							"data": "date" 
						},
			            { 	
			            	"className": 'centered',
			            	"data": "amount" 
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
			   
				BootstrapDialog.show({
					size: BootstrapDialog.SIZE_LARGE,
					type:BootstrapDialog.TYPE_DANGER,
					draggable: true,
					title: 'Devoluciones',
					autodestroy: false,
			        message: function(dialog) {
			        	
			        	$("#lov-dev-container").css({"display":"block"});
			        	
			        	return $("#lov-dev-container");
			        }
			    });
				
			} else {
				Message.showMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"), data.message);
			}
	   },
	   error:function(data){
		   Message.showMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"), data.responseJSON.message);
		   
		   return;
	   }
	});
	
	return;
}

Dev.showLovProduct = function(){
	
	var value = $("#devProductCode").val();
	if(value != null && value != ""){
		return Dev.getProductByCode(value);
	}
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/product",
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
			
		   var tbody = $("#tLovProductResult tbody");
		   
		   tbody.children('tr').remove();
		   
		   Message.hideMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"));
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
			            
			            var realPrice = 0;
			            if(price !=null && price != ""){
			            	realPrice = parseFloat(Math.floor(price * 100) / 100);
			            }
			            
			            $("#devProductId").val(selectedId);
			            $("#devProductDescription").val(selectedDescription);
			            $("#devProductCode").val(selectedCode);
			            
			            var cant = $("#devProductCount").val();
			            if(cant == null || cant == ""){
			            	cant = 1;
			            }
			            $("#devInstallment").val(parseFloat(dailyInstallment) * cant);
			            $("#devAmount").val(realPrice * cant);
			            $("#lov-client-container").css({"display": "none"});
			            
			            $.each(BootstrapDialog.dialogs, function(id, dialog){
			            	
			            	if(dialog.getTitle() == 'Productos'){
			            		
			            		dialog.close();
			            	}
			            	
			            	return;
			            });
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
				Message.showMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"), data.message);
			}
	   },
	   error:function(data){
		   Message.showMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"), data.responseJSON.message);
		   
		   return;
	   }
	});
	
	return;
}

Dev.getProductByCode = function(code){
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/product?code=" + code,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {

		   $("#devProductDescription").parent().next().html("");
		   $("#devProductDescription").val("");
		   $("#devProductDescription").parent().parent().removeClass("has-error");
		   
		   Message.hideMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"));
		   
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
		            if(price !=null && price != ""){
		            	realPrice = parseFloat(Math.floor(price * 100) / 100);
		            }
		            
		            $("#devProductId").val(selectedId);
		            $("#devProductDescription").val(selectedDescription);
		            $("#devProductCode").val(selectedCode);
		            
		            var cant = $("#devProductCount").val();
		            if(cant == null || cant == ""){
		            	cant = 1;
		            }
		            $("#devInstallment").val(parseFloat(dailyInstallment) * cant);
		            $("#devAmount").val(realPrice * cant);
				   
		           $("#devInstallment").focus();
			   } else {
				   $("#devProductCode").focus();
				   $("#devProductDescription").parent().next().append("Producto no encontrado");
				   $("#devProductDescription").parent().parent().addClass("has-error");
			   }
			   
		   } else {
				Message.showMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"), data.message);
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"), data.responseJSON.message);
		   
		   return false;
	   }
	});
	
	return;
}

Dev.initModalProductRows = function(){

	$("#btnDevAddRow").on("click", function(){
		
		var i = $("div[id*='devProduct_']").length;
		
		var newRow = $("#modalDevProductRow").clone();
		
		newRow.contents().each(function () {
		    if (this.nodeType === 3) this.nodeValue = $.trim($(this).text()).replace(/_X/g, "_" + i)
		    if (this.nodeType === 1) $(this).html( $(this).html().replace(/_X/g, "_" + i) )
		});
		
		newRow.find("input[id*='devProductId_']").attr("id", "devProductId_" + i).attr("name", "devProductId_" + i);
		newRow.find("input[id*='devProductPrice_']").attr("id", "devProductPrice_" + i).attr("name", "devProductPrice_" + i);
		
		newRow.attr("id", "devProduct_" + i);
		newRow.removeClass("hide");
		
		newRow.insertAfter($("div[id*='devProduct_']:last"));
		
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
	
	return;
}

Dev.PRODUCT_TABLE_CONTAINER = function(){
	
	var row = $('<div id="modalDevProductRow" class="row"></div>');
	var col0 = $('<div class="col-md-1">&nbsp;</div>')
	var col1 = $('<div class="col-md-12" style="border: 1px solid #ccc;padding-top: 2%;margin-bottom: 2%;"></div>');
	var col2 = $('<div class="col-md-1">&nbsp;</div>')
	var table = $('<table class="table"></table>');
	var thead = $('<thead><tr><th class="col-md-1 centered">#</th><th class="col-md-2 centered">Cantidad</th><th class="col-md-2 centered">C&oacute;digo</th><th class="col-md-6 centered">Descripci&oacute;n</th><th class="col-md-1 centered">Acciones</th></tr></thead>');
	
	row.append(col1.append(table.append(thead)));
	
	return row;
}

Dev.buildRow = function(idx, product){
	
	var tr = $('<tr id="productRow_' + idx + '"><input type="hidden" id="devProductId_' + idx + '" name="devProductId_' + idx + '" value="' + product.productId + '"><input type="hidden" id="devProductPrice_' + idx + '" name="devProductPrice_' + idx + '" value="' + product.price + '"><input type="hidden" id="devProductAmount_' + idx + '" name="devProductAmount_' + idx + '" value="' + product.amount + '"><input type="hidden" id="devProductDailyInstallment_' + idx + '" name="devProductDailyInstallment_' + idx + '" value="' + product.dailyInstallment + '"></tr>');
	var td0 = $('<td class="centered">' + (idx+1) + '</td>');
	var td1 = $('<td><input class="form-control input-sm" type="number" id="devProductCount_' + idx + '" name="devProductCount_' + idx + '" min="1" value="' + product.count + '"></td>');
	var td2 = $('<td><input class="form-control input-sm" type="text" id="devProductCode_' + idx + '" name="devProductCode_' + idx + '" value="' + product.productCode + '" readonly></td>');
	var td3 = $('<td><input class="form-control input-sm" type="text" id="devProductDescription_' + idx + '" name="devProductDescription_' + idx + '" value="' + product.productDescription + '" readonly></td>');
	var td4 = $('<td class="centered"><button id="btnDevAddRow_X" class="btn btn-xs btn-danger"><i class="glyphicon glyphicon-minus-sign"></i></button></td>');
	
	tr.append(td0).append(td1).append(td2).append(td3).append(td4);
	
	return tr;
}

Dev.PRODUCT_TABLE_ROW_CONTENT = function(list){
	
	var tbody = $('<tbody></tbody>');
	
	if(list.length > 0){
		
		var element = list[0];
		
		for(var i=0;i<element.billProducts.length;i++){
			var tr = Dev.buildRow(i, element.billProducts[i]);
			tbody.append(tr);
		}

		return tbody;
	}
	
	var tr = $("<tr><td colspan='5' class='centered'>No se han encontrado elementos</td></tr>");
	tbody.append(tr);
	
	return tbody;
}

Dev.getDevInfo = function(id){
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/bill?id=" + id,
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
			
		   Message.hideMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"));
		   if(data != null && data.status == 0){
			   var row = Dev.PRODUCT_TABLE_CONTAINER();
			   
			   var tbody = Dev.PRODUCT_TABLE_ROW_CONTENT(data.data);
			   
			   var table = row.find("table");
			   
			   table.append(tbody);
			   
			   row.insertAfter($("#devDateRow:last"));
			   
		   } else {
			   Message.showMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"), data.message);
		   }
	   },
	   error:function(data){
		   Message.showMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"), data.responseJSON.message);
		   
		   return;
	   }
	});
	
	return;
}
