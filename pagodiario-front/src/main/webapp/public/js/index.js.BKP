$(function ($) {
			
	indexInit = function(contextPath){	
		
		var url = contextPath + "/api/clients";
		var location = window.location.pathname.split('/')[4];
		$('#' + location).addClass('active');
		loadTable();
		$("#dlgClient").hide();
		$("#dlgConfirm").hide();
		$("#ssnGroup").hide();
		$("#invoiceAddressCode").hide();
		$("#paymentAddressCode").hide();
		$("#client-messages").hide();
		$("#messages").hide();
		$('#supplier-fields').hide();
		$('#excelBtn').hide();
	
		return;
	};
	
	loadTable = function(){
		var url = contextPath + "/api/clients";
		$('#excelBtn').hide();
		$.ajax({ 
			   type    : "GET",
			   url     : url,
			   dataType: "json",
			   success:function(data) {
				   if(data.length > 0){
					   //drawTable(data);
		        	   $.each(data, function(){
		        		   var invoiceAddressesList, paymentAddressesList, suppliersList = [];
		        		   if(this.invoiceAddressesDto){
		        			   invoiceAddressesList = createRowFromAddress(this, this.invoiceAddressesDto, false);
		        		   }
		        		   
		        		   if(typeof this.paymentAddressesDto != 'undefined'){
		        			   paymentAddressesList = createRowFromAddress(this, this.paymentAddressesDto, true);
		        		   }
		        		   
		        		   if(typeof this.suppliers != 'undefined'){
		        			   suppliersList = createRowFromSuppliers(this, this.suppliers);
		        		   }		        		   
		        		   
		        		   if(paymentAddressesList){
		        			   var result = invoiceAddressesList.concat(paymentAddressesList, suppliersList);
		        		   }else {
		        			   var result = invoiceAddressesList.concat(suppliersList);
		        		   }		        		   
		        		   		        		   
		        		   drawRow(result);		        		   
		        		   $('#excelBtn').show();		        		
		        		   
		        	   });		        	   
				   }else{
					   var table = $("#clients-table tbody");
					   table.append("<tr><td colspan='11' style='text-align:center'>There is no results</td><</tr>");
				   }
			   },
			   error:function(){
				   var table = $("#clients-table tbody");
		           var tr = $("#clients-table tbody tr");
		           tr.remove();
		           table.append("<tr><td colspan='11' style='text-align:center'>There is no results</td><</tr>");
			   }
			});	
	};
	
	drawTable = function(data) {
		$.each(data, function(idx, elem){
			//drawRow(data[idx]);
		});			
	};
	
	
	var Row = function() {
		this.countryId = "";
		this.taxPayerId = "";
		this.invoiceAddressId = "";
		this.paymentAddressId = "";
		this.invoiceOracleAddressCode = "";
		this.paymentOracleAddressCode = "";
		this.ssn = "";
		this.supplierId = "";
		this.gatewayId = "";
		this.providerId = "";
	}
		
	createRowFromAddress = function(client, listAddresses, isPayment){
		var l = [];
		var i = 0;
		
		$.each(listAddresses, function(){
			var r = new Row();
			if(isPayment){
				r.paymentAddressId = this.addressId;
				r.paymentOracleAddressCode = this.oracleAddressCode;
			}else{
				r.invoiceAddressId = this.addressId;
				r.invoiceOracleAddressCode = this.oracleAddressCode;
			}
			r.countryId = client.countryId? client.countryId: "";
			r.taxPayerId = client.taxPayerId? client.taxPayerId: "";
			r.ssn = client.ssn? client.ssn: "";
			l[i] = r;
			i++;
		});
		return l;
	};
			
	createRowFromSuppliers = function(client, listSuppliers){
		var l = [];
		var i = 0;
		$.each(listSuppliers, function(){
			var r = new Row();
			r.invoiceAddressId = this.invoiceAddressDto.addressId;
			r.invoiceOracleAddressCode = this.invoiceAddressDto.oracleAddressCode;
			if(this.paymentAddressDto != null){
				r.paymentAddressId = this.paymentAddressDto.addressId;
				r.paymentOracleAddressCode = this.paymentAddressDto.oracleAddressCode;			
			}
			r.countryId = client.countryId;
			r.taxPayerId = client.taxPayerId;
			r.ssn = client.ssn;
			r.supplierId = this.supplierId;
			r.gatewayId = this.gatewayId;
			r.providerId = this.providerId;
			l[i] = r;
			i++;
		});
		return l;
	};
	
	drawRow = function(rowData) {
	    var idx = 0;	
	    $.each(rowData, function(){
	    	var row = $("<tr />")
		    row.attr('id', 'row_' + idx + '_' + this.ssn);
		    $("#clients-table tbody").append(row);
		    row.append($("<td class='countryId'>" + this.countryId + "</td>"));
		    row.append($("<td class='taxPayerId'>" + this.taxPayerId + "</td>"));
		    row.append($("<td class='gatewayId'>" + this.gatewayId + "</td>"));
		    row.append($("<td class='providerId'>" + this.providerId + "</td>"));
		    row.append($("<td class='supplierId'>" + this.supplierId + "</td>"));
		    row.append($("<td class='invoiceAddressId'>" + this.invoiceAddressId + "</td>"));
		    row.append($("<td class='paymentAddressId'>" + this.paymentAddressId + "</td>"));
		    row.append($("<td class='ssn'>" + this.ssn + "</td>"));
		    row.append($("<td class='invoiceOracleAddressCode'>" + this.invoiceOracleAddressCode + "</td>"));
		    row.append($("<td class='paymentOracleAddressCode'>" + this.paymentOracleAddressCode + "</td>"));
		    //row.append($("<td><a data-toggle='modal' href='#clientModal' class='editRow'>Edit</a> | <a href='#' class='deleteRow' >Delete</a></td></tr></td>"));
		    row.append($("<td><a href='#' class='editRow'>Edit</a> | <a href='#' class='deleteRow' >Delete</a></td></tr></td>"));
		    idx++;
	    });
		
	};
	
	drawRowSupplier = function(rowData) {
		$.each(rowData.suppliers, function(idx, elem){
			var gatewayId = rowData.suppliers[idx].gatewayId ? rowData.suppliers[idx].gatewayId : "";
	    	var providerId = rowData.suppliers[idx].providerId ? rowData.suppliers[idx].providerId : "";
	    	var supplierId = rowData.suppliers[idx].supplierId ? rowData.suppliers[idx].supplierId : "";
			
		    var row = $("<tr />")
		    $("#clients-table tbody").append(row);
		    row.append($("<td class='countryId'>" + rowData.countryId + "</td>"));
		    row.append($("<td class='taxPayerId'>" + rowData.taxPayerId + "</td>"));
		    row.append($("<td class='gatewayId'>" + gatewayId + "</td>"));
		    row.append($("<td class='providerId'>" + providerId + "</td>"));
		    row.append($("<td class='supplierId'>" + supplierId + "</td>"));
		    row.append($("<td class='invoiceOracleAddressCode'>" + rowData.suppliers[idx].invoiceAddressDto.oracleAddressCode + "</td>"));
		    row.append($("<td class='paymentOracleAddressCode'>" + rowData.suppliers[idx].paymentAddressDto.oracleAddressCode + "</td>"));
		    row.append($("<td class='ssn'>" + rowData.ssn + "</td>"));
		    row.append($("<td class='invoiceAddressId'>" + rowData.suppliers[idx].invoiceAddressDto.addressId + "</td>"));
		    row.append($("<td class='paymentAddressId'>" + rowData.suppliers[idx].paymentAddressDto.addressId + "</td>"));
		    row.append($("<td><a href='#' class='editRow'>Edit</a> | <a href='#' class='deleteRow' >Delete</a></td></tr></td>"));
		});	    
	};
	
	addClient = function(){	
		var url = contextPath + "/api/clients";
	    var clientData = new Array();
	    $("#client-messages").html("").hide();
	    var obj = {};
	    
	    if(($('#inputGatewayId').val() != "") && ($('#inputProviderId').val() != "") && ($('#inputSupplierId').val() != "")) {
	    	
	    	obj = ({
	    		"invoiceAddressesDto":[  
	    		                       {  
	    		                          "addressId":$('#inputInvoiceAddressId').val(),
	    		                       }
	    		                    ],
	    		    	    		//"ssn": null,	
	    		    	            "countryId":$('#inputCountryId').val(),
	    		    	            "taxPayerId":($('#inputTaxPayerId').val()),
	    		    	            "suppliers":[{
	    		    		            "supplierId":$('#inputSupplierId').val(),
	    		    		            "gatewayId":$('#inputGatewayId').val(),
	    		    		            "providerId":$('#inputProviderId').val(),
	    		    		            "invoiceAddressDto":{
	    		    			            "addressId":$('#inputInvoiceAddressId').val(),
	    		    		            },
	    		    		       }]
	    		    		    });
	    	
	    	//clientData.push(obj);
	    	clientData = $.extend({}, obj);
	    	
	    	if($('#inputPaymentAddressId').val() != ""){
	 		   var obj2 = {};
	 		   obj2 = ({"paymentAddressesDto":[  
	                        {  
	                     	   "addressId":$('#inputPaymentAddressId').val(),
	                         }
	                     ],"suppliers":[{
		    		            "supplierId":$('#inputSupplierId').val(),
		    		            "gatewayId":$('#inputGatewayId').val(),
		    		            "providerId":$('#inputProviderId').val(),
		    		            "invoiceAddressDto":{
		    			            "addressId":$('#inputInvoiceAddressId').val(),
		    		            },
		    		            "paymentAddressDto": {
		    		            	"addressId": $('#inputPaymentAddressId').val(),
		    		            }
		    		       }]
	                     });
	 		   	 		   
	 		   clientData = $.extend({}, obj, obj2);
	 	   }
	    	
	       //var data = clientData;  	
	    
	    }else {
	    	
	    	obj = ({"invoiceAddressesDto":[  
                       {  
                          "addressId":$('#inputInvoiceAddressId').val(),
                       }
                    ],
    	    		//"ssn": null,
    	            "countryId":$('#inputCountryId').val(),
    	            "taxPayerId":($('#inputTaxPayerId').val()) 
    		    });
	    	//clientData.push(obj);
	    	clientData = $.extend({}, obj);
	    	
	    	if($('#inputPaymentAddressId').val() != ""){
		 		   var obj2 = {};
		 		   obj2 = ({"paymentAddressesDto":[  
		                        {  
		                     	   "addressId":$('#inputPaymentAddressId').val(),
		                         }
		                     ]
		                     });
		 		   	 		   
		 		   clientData = $.extend({}, obj, obj2);
		  }
	    	
	     //var data = clientData[0];
	     //var data = clientData;	
	    	
	    }
	    
		$.ajax({
		    url : url,
		    async : false,
		    dataType: 'json',
		    type: "POST",
		    contentType: "application/json;",
		    data : JSON.stringify(clientData),
		    success: function(data, textStatus, jqXHR)
		    {
		    	if(data.status != undefined){
			        var errorText = "<ul class='list-unstyled'>";
		    		$.each(data.causes, function(idx, elem){
		    			errorText += "<li>"+elem+"</li>";
				    });
			    	errorText += "</ul>";
			    	
			    	$("#client-messages").append("<i class='glyphicon glyphicon-warning-sign'> An error occurred:" + errorText);
			    	$("#client-messages").show();
		    	}else {
		    		var table = $("#clients-table tbody");
			        var tr = $("#clients-table tbody tr");
			        tr.remove();
			        
			        $('#dlgClient').dialog("close");
			        $("#client-form").find("input[type=text], textarea").val("");
			        loadTable();
		    	}
		    },
		    error: function (data, jqXHR, textStatus, errorThrown)
		    {
		    	var errorText = "<ul class='list-unstyled'>";
	    		$.each(data.responseJSON.causes, function(idx, elem){
	    			errorText += "<li>"+elem+"</li>";
			    });		    	
		    	errorText += "</ul>";
		    		
		    	$("#client-messages").append("<i class='glyphicon glyphicon-warning-sign'> An error occurred:" + errorText);
		    	$("#client-messages").show();
		    }
		});
	};
	
	deleteClient = function(ssn, invoiceAddressId, paymentAddressId,supplierId, gatewayId, providerId, id, parentId){
		var url = contextPath + "/api/clients";
		
		$.ajax({
		    url: url + '?ssn=' + ssn + '&invoiceAddressId=' + invoiceAddressId + '&paymentAddressId=' + paymentAddressId+ '&supplierId=' + supplierId + '&gatewayId=' + gatewayId + '&providerId=' + providerId,
		    type: 'DELETE',
		    cache: false,
		    success: function(result) {
		    	var row = $( "#"+parentId );
		    	row.remove();
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		    	var row = $( "#"+parentId );
		    	row.remove();
		    }
		});
	};
	
	validateForm = function(){
		var nameReg = /^[A-Za-z]+$/;
	    var numberReg =  /^[0-9]+$/;
	    var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
	    
	    var countryId = $('#inputCountryId').val();
	    var taxPayerId = $('#inputTaxPayerId').val();
	    var invoiceAddressId = $('#inputInvoiceAddressId').val();
	    
	    var gatewayId = $('#inputGatewayId').val();
	    var providerId = $('#inputProviderId').val();
	    var supplierId = $('#inputSupplierId').val();
	    
	    var inputVal = new Array(countryId, taxPayerId, invoiceAddressId, gatewayId, providerId, supplierId);
	    var inputMessage = new Array("Country Id", "Tax Payer Id", "Invoice Address Id", "Gateway Id", "Provider Id", "Supplier Id");
	    
	    $('.error').hide();
	    
	    if(inputVal[0] == ""){
            $('#countryIdLabel').after('<span class="error"> Please enter the ' + inputMessage[0] + '</span>');
            return false;
        }
	    /*
        else if(!nameReg.test(names)){
            $('#nameLabel').after('<span class="error"> Letters only</span>');
        }*/
	    	    
	    if(inputVal[1] == ""){
            $('#taxPayerIdLabel').after('<span class="error"> Please enter the ' + inputMessage[1] + '</span>');
            return false;
        }
	    
	    if(inputVal[2] == ""){
            $('#invoiceAddressIdLabel').after('<span class="error"> Please enter the ' + inputMessage[2] + '</span>');
            return false;
        }
	    
	    if($('#isProvider').is(':checked')) {
		    if(inputVal[4] == ""){
	            $('#gatewayIdLabel').after('<span class="error"> Please enter the ' + inputMessage[4] + '</span>');
	            return false;
	        }	    
		    if(inputVal[5] == ""){
	            $('#providerIdLabel').after('<span class="error"> Please enter the ' + inputMessage[5] + '</span>');
	            return false;
	        }
		    if(inputVal[6] == ""){
	            $('#supplierIdLabel').after('<span class="error"> Please enter the ' + inputMessage[6] + '</span>');
	            return false;
	        }
	    }
	    		
	    return true;
	};
	
	$('#client-form').submit(function(event) {
		event.preventDefault();
		addClient();		
	})
	
	$('#addBtn').on('click', function(event){
		$('#supplier-fields').hide();
		$('#isProvider').prop('checked',false);
		$('#client-fields').removeClass('col-md-6').addClass('col-md-12');
		$('#isProviderLabel').show();
		$('#dlgClient').dialog({
            resizable: false,
            height: 430,
            width: 350,
            modal: true,
            buttons: {            	
                "Save": function() {
                	var valid = validateForm();
					if(valid){
                		$('#client-form').submit();
					}                	
                    //$(this).dialog("close");
                },
                Close: function() {
                	$("#client-messages").html("").hide();
                	$("#client-form").find("input[type=text], textarea").val("");
                	
                	$('#client-fields').removeClass('col-md-12').addClass('col-md-6');
            		//$('#isProviderLabel').show();
                	
                    $(this).dialog("close");
                }
            }
        });
	})
	
	/* Edit Client ---------------------------------------------------------------------------------------- */
	$('#clients-table').on('click', '.editRow', function(event){
		var parentId = $(this).parent('td').parent('tr').attr('id');
		var rowId = parentId.substring(6);
		var idx = parentId.substring(4,5);
		var url = contextPath + "/api/clients";
		
		var countryId = $(this).parent('td').siblings(".countryId").html();
		var taxPayerId = $(this).parent('td').siblings(".taxPayerId").html();
		var gatewayId = $(this).parent('td').siblings(".gatewayId").html();
		var providerId = $(this).parent('td').siblings(".providerId").html();
		var supplierId = $(this).parent('td').siblings(".supplierId").html();
		var invoiceAddressCode = $(this).parent('td').siblings(".invoiceAddressId").html();
		var paymentAddressCode = $(this).parent('td').siblings(".paymentAddressId").html();
		var ssn = $(this).parent('td').siblings(".ssn").html();
		var invoiceOracleAddressCode = $(this).parent('td').siblings(".invoiceOracleAddressCode").html();
		var paymentOracleAddressCode = $(this).parent('td').siblings(".paymentOracleAddressCode").html();
				
		$('#ssnGroup').show();
		$("#invoiceAddressCode").show();
		$("#paymentAddressCode").show();
		$('#inputCountryId').val(countryId).prop('disabled', true);
	    $('#inputTaxPayerId').val(taxPayerId).prop('disabled', true);
	    $('#inputGatewayId').val(gatewayId).prop('disabled', true);
	    $('#inputProviderId').val(providerId).prop('disabled', true);
	    $('#inputSupplierId').val(supplierId).prop('disabled', true);
	    $('#inputInvoiceAddressId').val(invoiceAddressCode);
	    $('#inputPaymentAddressId').val(paymentAddressCode).prop('disabled', true);	    
	    $('#inputSSN').val(ssn).prop('disabled', true);
	    $('#inputInvoiceAddressCode').val(invoiceOracleAddressCode).prop('disabled', true);
	    $('#inputPaymentAddressCode').val(paymentOracleAddressCode).prop('disabled', true);
	    
	    
	    $('#supplier-fields').show();
		   $('#isProviderLabel').hide();
		   $('#client-fields').removeClass('col-md-12').addClass('col-md-6');
		   
			$('#dlgClient').dialog({
	            resizable: false,
	            height: 575,
	            width: 650,
	            modal: true,
	            draggable: true,
	            buttons: {
	            	"Save": function() {
	                	$('#client-form').submit();
	                	$("#client-form").find("input[type=text], textarea").val("").prop('disabled', false);
	                    $(this).dialog("close");
	                },
					Close: function() {
	                	$("#client-form").find("input[type=text], textarea").val("").prop('disabled', false);
	                	$('#ssnGroup').hide();
	            		$("#invoiceAddressCode").hide();
	            		$("#paymentAddressCode").hide();
	                    $(this).dialog("close");
	                }
	            }
	        });
	
	})
	
	/* Delete client -------------------------------------------------------------------------- */
	$('#clients-table').on('click', '.deleteRow', function(event){
		var parentId = $(this).parent('td').parent('tr').attr('id');
		var invoiceAddressId = $(this).parent('td').siblings('.invoiceAddressId').html();
		var paymentAddressId = $(this).parent('td').siblings('.paymentAddressId').html();
		var supplierId = $(this).parent('td').siblings('.supplierId').html();
		var gatewayId = $(this).parent('td').siblings('.gatewayId').html();
		var providerId = $(this).parent('td').siblings('.providerId').html();
		var ssn = parentId.substring(6);
		var idx = parentId.substring(4,5);
		
		$('#dlgConfirm').dialog({
            resizable: false,
            height: 180,
            width: 380,
            modal: true,
            buttons: {
                "Delete": function() {
                	deleteClient(ssn, invoiceAddressId, paymentAddressId, supplierId, gatewayId, providerId, idx, parentId);
                    $(this).dialog("close");
                },
                Cancel: function() {
                    $(this).dialog("close");
                }
            }
        });
	})
	
	/* Filters ---------------------------------------------------------------------------------------------------- */
	
	/* Filter by SSN */
	$('#client-ssn-form').submit(function(event) {
	    event.preventDefault();
	    
	    var url = contextPath + "/api/clients";
	    var ssn = $('#ssn').val() ? $('#ssn').val() : '';
	    var formData = $(this).serialize();
	
	    if(ssn != ""){
		     $.ajax ( {
		        url: url + "/" + ssn,
		        //data: formData,
		        success: function (data) {
		           var table = $("#clients-table tbody");
		           var tr = $("#clients-table tbody tr");
		           tr.remove(); 
		          
		           if(data){
		        	   var invoiceAddressesList, paymentAddressesList, suppliersList = [];
		        	   var result = ""
		        	   $.each(data, function(){
		        		   
		        		   if(data.invoiceAddressesDto){
		        			   invoiceAddressesList = createRowFromAddress(data, data.invoiceAddressesDto, false);
		        		   }
		        		   
		        		   if(data.paymentAddressesDto){
		        			   paymentAddressesList = createRowFromAddress(data, data.paymentAddressesDto, true);
		        		   }
		        		   
		        		   if(data.suppliers){
		        			   suppliersList = createRowFromSuppliers(data, data.suppliers);
		        		   }
		        		   
		        		   if(paymentAddressesList){
		        			   result = invoiceAddressesList.concat(paymentAddressesList, suppliersList);
		        		   }else {
		        			   result = invoiceAddressesList.concat(suppliersList);
		        		   } 		   
		        		   
		        	   });
		        	   
		        	   drawRow(result);
		        	   
		           } else{
					   table.append("<tr><td colspan='11' style='text-align:center'>There is no results</td><</tr>");
				   }
		        },
	            error: function(result) {
	               var table = $("#clients-table tbody");
	 	           var tr = $("#clients-table tbody tr");
	 	           tr.remove();
	 	           table.append("<tr><td colspan='11' style='text-align:center'>There is no results</td><</tr>");
	            }
		    } );
	    }else {
	    	var tr = $("#clients-table tbody tr");
	        tr.remove();
	    	loadTable();
	    }
	})
	
	/* Client filter by taxPayerId, countryId and invoiceAddressId*/
	$('#client-filter-form').submit(function(event) {
	    event.preventDefault();
	    
	    var url = contextPath + "/api/clients";
	    var formData = $(this).serialize();
	    var taxPayerId = $('#taxPayerId').val() ? $('#taxPayerId').val() : '';
	    var countryId  = $('#countryId').val() ? $('#countryId').val() : '';
	    var invoiceAddressId = $('#invoiceAddressId').val()? $('#invoiceAddressId').val() : '';	    
	
	     $.ajax ( {
	        url: url,
	        data: formData,
	        success: function (data) {
	           var table = $("#clients-table tbody");
	           var tr = $("#clients-table tbody tr");
	           tr.remove();
	           if(data.length > 0){
	        	   //drawTable(data);
	        	   
	        	   $.each(data, function(){
	        		   var invoiceAddressesList, paymentAddressesList, suppliersList = [];
	        		   
	        		   if(this.invoiceAddressesDto){
	        			   invoiceAddressesList = createRowFromAddress(this, this.invoiceAddressesDto, false);
	        		   }
	        		   
	        		   if(this.paymentAddressesDto){
	        			   paymentAddressesList = createRowFromAddress(this, this.paymentAddressesDto, true);
	        		   }
	        		   
	        		   if(this.suppliers){
	        			   suppliersList = createRowFromSuppliers(this, this.suppliers);
	        		   }		        		   
	        		   
	        		   if(paymentAddressesList){
	        			   var result = invoiceAddressesList.concat(paymentAddressesList, suppliersList);
	        		   }else {
	        			   var result = invoiceAddressesList.concat(suppliersList);
	        		   }
	        		   drawRow(result);
	        		   
	        	   });
	        	   	        	   
	           } else{
				   table.append("<tr><td colspan='11' style='text-align:center'>There is no results</td><</tr>");
			   }
	        },
            error: function(result) {
               var table = $("#clients-table tbody");
 	           var tr = $("#clients-table tbody tr");
 	           tr.remove();
 	           table.append("<tr><td colspan='11' style='text-align:center'>There is no results</td><</tr>");
            }
	    } );
	})
	
	/* Client filter by supplierId, gatewayId and providerId*/
	$('#supplier-filter-form').submit(function(event) {
		
	    event.preventDefault();
		var url = contextPath + "/api/clients";
	    var formData = $(this).serialize();
	    var supplierId = $('#supplierId').val() ? $('#supplierId').val(): '';	    
	    var gatewayId = $('#gatewayId').val() ? $('#gatewayId').val() : '';
	    var providerId  = $('#providerId').val() ? $('#providerId').val() : '';
	    
	    if(supplierId != ""){
	     $.ajax ( {
	        url: url + "/suppliers",
	        data: formData,
	        success: function (data) {
	           var table = $("#clients-table tbody");
	           var tr = $("#clients-table tbody tr");
	           tr.remove();	           
			    
			   if(data.length > 0){
				   
				   $.each(data, function(){
	        		   var invoiceAddressesList, paymentAddressesList, suppliersList = [];
	        		   
	        		   if(this.invoiceAddressesDto){
	        			   invoiceAddressesList = createRowFromAddress(this, this.invoiceAddressesDto, false);
	        		   }
	        		   
	        		   if(this.paymentAddressesDto){
	        			   paymentAddressesList = createRowFromAddress(this, this.paymentAddressesDto, true);
	        		   }
	        		   
	        		   if(this.suppliers){
	        			   suppliersList = createRowFromSuppliers(this, this.suppliers);
	        		   }		        		   
	        		   
	        		   if(paymentAddressesList){
	        			   var result = invoiceAddressesList.concat(paymentAddressesList, suppliersList);
	        		   }else {
	        			   var result = invoiceAddressesList.concat(suppliersList);
	        		   }
	        		   drawRow(result);
	        		   
	        	   });		   
				   
			   }else{
				   table.append("<tr><td colspan='11' style='text-align:center'>There is no results</td><</tr>");
			   }
	        },
            error: function(result) {
               var table = $("#clients-table tbody");
  	           var tr = $("#clients-table tbody tr");
  	           tr.remove();
  	           table.append("<tr><td colspan='11' style='text-align:center'>There is no results</td><</tr>");
             }
	     });
	    }
	    
	})
		
	$('#supplierId').keyup(function(){
	     $('#gatewayId').prop('disabled', !this.value.length);
	     $('#providerId').prop('disabled', !this.value.length);
		/*
		if (this.value.length > 0) {
	       $('#gatewayId').prop('disabled', false)
	    } else {
	       $('#gatewayId').prop('disabled', true)
	    }
	    */
	})
	
	$('#taxPayerId').keyup(function(){
	     $('#countryId').prop('disabled', !this.value.length);
	     $('#invoiceAddressId').prop('disabled', !this.value.length);
	})
	
	$('#toggle-filter').on('click', function(event){
		$("#filterColapse").toggle();
		$(".toggle-ico", this).toggleClass("glyphicon-minus-sign").toggleClass("glyphicon-plus-sign");  
	})
	
	$('#toggle-filter-upload').on('click', function(event){
		$("#filterColapseUpload").toggle();
		$(".toggle-ico", this).toggleClass("glyphicon-minus-sign").toggleClass("glyphicon-plus-sign");  
	})
	
	$('body').on('click', '.ui-dialog-titlebar-close', function () {
		$("#client-form").find("input[type=text], textarea").val("");
	})
	
	/* Upload ---------------------------------------------------------------------------------------- */
	
	$('#fileupload').fileupload({
		url : contextPath + "/html/source/upload/add",
		type: "POST",
        contentType: false,
		add: function (e, data) {
			$('#messages').text('').hide();
			$('#filename').val(data.files[0].name);
	        data.context = $('<button/>')
	        	.addClass('btn btn-primary start')
	        	.append('<i class="glyphicon glyphicon-upload"></i><span> Upload</span>')
	        	//.text('Upload')	        	
	            .appendTo('#fileupload-bar')
	            .click(function (event) {
	                //data.context = $('#upload-text').text('Uploading...').replaceAll($(this));
	            	event.preventDefault();
	                data.submit();
	            });
	        
	    },
	    done: function (e, data) {  	
	    	var tr = $("#clients-table tbody tr");
	        tr.remove();
	    	loadTable();
	    	$('#filename').val("");
	    	$.each(data.files, function (index, file) {
                $('<p/>').text(file.name + ' has been uploaded successfully').appendTo('#messages');
            });
	    	$('#messages').show();
	    	
	    	setTimeout(function() {
	            $("#messages").hide('blind', {}, 500)
	        }, 5000);    
	        
	    },	    
	    error: function (e, data) {
	    	var errorText = "<ul class='list-unstyled'>";
    		$.each(e.responseJSON.causes, function(idx, elem){
    			errorText += "<li>"+elem+"</li>";
		    });		    	
	        errorText += "</ul>";
	        
	        $("#messages").append('<i class="glyphicon glyphicon-warning-sign"></i> An error occurred: ' + errorText);
	        $('#messages').show();
	    },
	    progressall: function (e, data) {
	        var progress = parseInt(data.loaded / data.total * 100, 10);
	        $('#progress .bar').css(
	            'width',
	            progress + '%'
	        );
	    }
    });
	
	/* Is provider checkbox -------------------------------------------------------------------------- */
	$('#isProvider').on('change', function(event){
		
		$('#supplier-fields').toggle();
		
		var w = this.checked ? '650' : '350';
		var rClass = this.checked ? 'col-md-12' : 'col-md-6';
		var aClass = this.checked ? 'col-md-6' : 'col-md-12';	
		
		$('#dlgClient').dialog({ width: w });		
		$('#client-fields').removeClass(rClass).addClass(aClass);
		
	})
	
	/* Generate Excel function  ----------------------------------------------------------------------- */
	generateExcel = function(){
		//generateExcel = function(ssn, taxPayerId, countryId, addressId, supplierId, gatewayId, providerId){
		fillHiddenFilterFields();
		var url = contextPath + "/api/xls/clients";
		
		var frmExcel = $("#frmExcelExporter");
		
		frmExcel.attr("action", url).attr("method", "GET");
		frmExcel.submit();
	};
	
	fillHiddenFilterFields = function(){
		var ssnValue = $("#ssn").val();
		var taxPayerIdValue = $("#taxPayerId").val();
		var countryIdValue = $("#countryId").val();
		var supplierIdValue = $("#supplierId").val();
		var gatewayIdValue = $("#gatewayId").val();
		var providerIdValue = $("#providerId").val();
		
		$("#ssn_h").val(ssnValue);
		$("#taxPayerId_h").val(taxPayerIdValue);
		$("#countryId_h").val(countryIdValue);
		$("#supplierId_h").val(supplierIdValue);
		$("#gatewayId_h").val(gatewayIdValue);
		$("#providerId_h").val(providerIdValue);
		
		return;
	}
	
});