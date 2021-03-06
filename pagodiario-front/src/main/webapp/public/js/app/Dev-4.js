Dev = function(){}

Dev.init = function(){
	
	return;
}

Dev.resetModal = function(){
	
	Message.hideMessages($('#modalDevAlertMessages'), $("#modalDevMessages"));
	Dev.cleanBillProductError();
	$("#devBillId").val("");
	$("#devDateValue").val("");
	$("#devInstallment").val("");
	$("#devAmount").val("");
	$("#devObservations").val("");
	
	
	return;
}

Dev.add = function(dialog, btn, responseHandler){
	
	if(!confirm("Esta usted seguro de aceptar la devolución?")){
		dialog.enableButtons(true);
		dialog.setClosable(true);
		btn.stopSpin();
		
		return false;
	}
	
	var billId = $("#devBillId").val();

	var obj = Dev.createObject(billId);
	
	if(obj == null){
		Message.showMessages($('#modalDevAlertMessages'), $("#modalDevMessages"), "La cantidad no puede ser mayor a la cantidad original. Tampoco negativa.");
		dialog.enableButtons(true);
		dialog.setClosable(true);
		btn.stopSpin();
		// significa q tuve error ...
		return false;
	}
	
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

Dev.blurProductCount = function(idx){

	$("#devProductCount_" + idx).parent().removeClass("has-error");
	Message.hideMessages($('#modalDevAlertMessages'), $("#modalDevMessages"));

	var originalCount = parseInt($("#devProductCountAlt_" + idx).val());

	var count = $("#devProductCount_" + idx).val();
	
	if(count < 0 || count > originalCount){
		$("#devProductCount_" + idx).parent().addClass("has-error");
		Message.showMessages($('#modalDevAlertMessages'), $("#modalDevMessages"), "La cantidad no puede ser mayor a la cantidad original. Tampoco negativa.");
		
		return;
	}
	
	Dev.doTotalAmountsAccounts();
	
	return;
}

Dev.doTotalAmountsAccounts = function(){
	
	var devInstallment = 0;
	var devTotalAmount = 0;
	var remainingAmount = 0;
	
	$("tr[id*='productRow_']").each(
		function(e){
			var id = $(this).attr("id");
			
			var idx = Commons.getIndexFromString(id, '_');
			
			var originalCount = parseInt($("#devProductCountAlt_" + idx).val());

			var count = $("#devProductCount_" + idx).val();
			
			var originalTotalInstallmentAmount = parseFloat($("#devProductDailyInstallment_" + idx).val());
			var originalInstallmentAmount = originalTotalInstallmentAmount / originalCount;
			var newInstallmentAmount = originalInstallmentAmount * count;
			
			devInstallment = devInstallment + newInstallmentAmount;
			
			var originalTotalAmount = parseFloat($("#devProductAmount_" + idx).val());
			var originalAmount = originalTotalAmount / originalCount;
			var newAmount = originalAmount * count;
			
			devTotalAmount = devTotalAmount + newAmount;

			return;
		}
	);
	
	$("#devInstallment").val(devInstallment);
	$("#devAmount").val(devTotalAmount);
	
	return;
}

Dev.removeRow = function(idx, productId){
	
	var row = $("#productRow_" + idx);
	
	row.remove();
	
	Dev.doTotalAmountsAccounts();
	
	$("#devDateValue").keydown(function(e){
		if(e.keyCode == 9) {
			e.preventDefault();
			
			var first = $("input[id*=devProductCount_]:first");
			if(first.length > 0){
				first.focus();
			} else {
				$("#devObservations").focus();
			}
		}
	    
	    return;
	});
	
	return;
}

Dev.buildRow = function(idx, product){
	
	var tr = $('<tr id="productRow_' + idx + '"><input type="hidden" id="devProductId_' + idx + '" name="devProductId_' + idx + '" value="' + product.productId + '"><input type="hidden" id="devProductPrice_' + idx + '" name="devProductPrice_' + idx + '" value="' + product.price + '"><input type="hidden" id="devProductAmount_' + idx + '" name="devProductAmount_' + idx + '" value="' + product.amount + '"><input type="hidden" id="devProductDailyInstallment_' + idx + '" name="devProductDailyInstallment_' + idx + '" value="' + product.dailyInstallment + '"></tr>');
	var billProductIdContainer = $('<input type="hidden" id="devBillProductId_' + idx + '" name="devBillProductId_' + idx + '" value="' + product.id + '">');
	var hiddenProductCount = $('<input type="hidden" id="devProductCountAlt_' + idx + '" name="devProductCountAlt_' + idx + '" value="' + product.count + '">');
	tr.append(hiddenProductCount).append(billProductIdContainer);
	
	var td0 = $('<td class="centered"><div class="form-group">' + (idx+1) + '</div></td>');
	var td1 = $('<td><div class="form-group"><input class="form-control input-sm" tabindex="' + (idx+1) + '" type="number" id="devProductCount_' + idx + '" name="devProductCount_' + idx + '" min="1" value="' + product.count + '" onblur="javascript:Dev.blurProductCount(' + idx + ');"></div></td>');
	var td2 = $('<td><input class="form-control input-sm" type="text" id="devProductCode_' + idx + '" name="devProductCode_' + idx + '" value="' + product.productCode + '" readonly></td>');
	var td3 = $('<td><input class="form-control input-sm" type="text" id="devProductDescription_' + idx + '" name="devProductDescription_' + idx + '" value="' + "$" + product.dailyInstallment + " - $" + product.amount + " - " + product.productDescription + '" readonly></td>');
	var td4 = $('<td class="centered"><div class="form-group"><button id="btnDevRemoveRow_' + idx + '" onclick="javascript:Dev.removeRow(' + idx + ', ' + product.productId + ');" class="btn btn-xs btn-danger"><i class="glyphicon glyphicon-minus-sign"></i></button></div></td>');
	
	tr.append(td0).append(td1).append(td2).append(td3).append(td4);
	
	return tr;
}

Dev.PRODUCT_TABLE_ROW_CONTENT = function(list){
	
	var tbody = $('<tbody></tbody>');
	
	var lastIdx = 0;

	if(list.length > 0){
		
		var element = list[0];
		
		
		for(var i=0;i<element.billProducts.length;i++){
			var tr = Dev.buildRow(i, element.billProducts[i]);
			tbody.append(tr);
			
			lastIdx = i;
		}
		
		$("#devObservations").attr("tabindex", (lastIdx + 1));

		return tbody;
	}
	
	var tr = $("<tr><td colspan='5' class='centered'>No se han encontrado elementos</td></tr>");
	tbody.append(tr);
	
	$("#devObservations").attr("tabindex", (lastIdx + 1));
	
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
			   
			   var elem = data.data[0];
			   
			   $("#devInstallment").val(elem.totalDailyInstallment);
			   $("#devAmount").val(elem.totalAmount);
			   
			   
			   $("input[id*='devProductCount_']").keyup(
						function(e){
							if(e.keyCode == 10 || e.keyCode == 13) {
								e.preventDefault();
							}
							
							return;
						}
					);
			   
			   $("input[id*='devProductCount_']").keydown(
					function(e){
						if(e.keyCode == 10 || e.keyCode == 13) {
							e.preventDefault();
						}
						
						return;
					}
				);
				
			   $("[id*='btnDevRemoveRow_']").keyup(
					function(e){
						if(e.keyCode == 10 || e.keyCode == 13) {
							e.preventDefault();
						}
						
						return;
					}
				);
			   
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

Dev.cleanBillProductError = function(){
	
	$("tr[id*='productRow_']").each(
		function(e){
			var id = $(this).attr("id");
			
			var idx = Commons.getIndexFromString(id, '_');
			
			var productCountContainer = $("#devProductCount_" + idx);
			
			productCountContainer.parent().removeClass("has-error");

			return;
		}
	);
	
	return;
}

Dev.createObject = function(billId){

	var dto = new Object();
	dto.billId = billId;
	
	var products = [];
	
	var hasError = false;
	
	Dev.cleanBillProductError();
	
	$("tr[id*='productRow_']").each(
		function(e){
			
			var id = $(this).attr("id");
			
			var idx = Commons.getIndexFromString(id, '_');
			
			var productCountContainer = $("#devProductCount_" + idx);
			
			var productCount = parseInt(productCountContainer.val());
			var productCountAlt = parseInt($("#devProductCountAlt_" + idx).val());
			var billProductId = $("#devBillProductId_" + idx).val();
			
			if(productCount > productCountAlt || productCount < 0){
				// mostrar error en pantalla
				productCountContainer.parent().addClass("has-error");
				
				hasError = true;
				return;
			}
			
			var product = new Object();
			product.id = billProductId;
			product.count = productCount;
			product.productId = $("#devProductId_" + idx).val(); 
			
			products.push(product);
			
			return;
		}
	);
	
	if(hasError){
		return null;
	}
	
	dto.billProducts = products;
	dto.selectedDate = $("#devDateValue").val();
	dto.observations = $("#devObservations").val();
	dto.totalAmount = $("#devAmount").val();
	dto.totalInstallment = $("#devInstallment").val();
	
	return dto;
}
