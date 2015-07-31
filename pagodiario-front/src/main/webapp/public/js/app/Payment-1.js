Payment = function(){}

Payment.init = function(){
	
	$("#btnSearch").on('click', function(){
		
		var c = 0;
		
		$("#frmPaymentSearch").on('invalid.bs.validator', 
			function(e){
			    c++;
				
				return;
		});
		
		$("#frmPaymentSearch").validator('validate');
		
		if(c == 0){
			$(this).addClass("disabled");
    		
			var collectorId = $("#zone").val();
			
			$.ajax({ 
			   type    : "GET",
			   url     : Constants.contextRoot + "/controller/html/payment?collectorId=" + collectorId,
			   dataType: 'json',
			   contentType: "application/json;",
			   success:function(data) {
					
				   Message.hideMessages($('#paymentAlertMessages'), $("#paymentMessages"));
				   $("#btnSearch").removeClass("disabled");
				   
				   if(data != null && data.status == 0){
					   
					   $.each(data.data, function(){
						   
						   
						   return;
					   });
					   

				   } else {
					   Message.showMessages($('#paymentAlertMessages'), $("#paymentMessages"), data.message);
				   }
			   },
			   error:function(data){
				   Message.showMessages($('#paymentAlertMessages'), $("#paymentMessages"), data.responseJSON.message);
				   $("#btnSearch").removeClass("disabled");
				   
				   return;
			   }
			});
		}
		
		return;
	});
	
	$('#zone').keydown(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 9){
	    	e.preventDefault();
	    	$("#creditNumber_0").focus();
	    }
	    
	    return;
	});
	
	$('#zone').keyup(function(e){
		// 13: enter
		// 9: tab
	    if(e.keyCode == 13 || e.keyCode == 9){
	    	e.preventDefault();
	    	$("#creditNumber_0").focus();
	    }
	    
	    return;
	});
	
	$("#btnPaymentAdd").on('click', function(){
		Commons.addNewClonedRow('paymentRow_', $('#basePaymentRow'), {
			'changeLabel':true,
			execute:function(row){
				
				var paymentAmountInput = row.find("input[id*='paymentAmount_']");
				
				paymentAmountInput.keyup(function(e){
				    if(e.keyCode == 13){
				        $("#btnPaymentAdd").trigger('click');
						$("input[id*='creditNumber_']:last").focus();
				    }
				    
				    return;
				});
				
				var creditNumberInput = row.find("input[id*='creditNumber_']");
				
				creditNumberInput.keyup(function(e){
				    if(e.keyCode == 13){
				    	$(this).parent().parent().find('input:last').focus();
				    }
				    
				    return;
				});
				
				$("input").focus(function() { $(this).select(); } ).end().click(function () {$(this).select();});
				
				return;
			}
		});
		
		return;
	});
	
	$("#btnPaymentRemove").on('click', function(){
		Commons.removeClonedRow('paymentRow_');
		
		return;
	});
	
	Payment.addInputs();
	
	$("#btnReset").on('click', function(){
		
		var list = $("div[id*='paymentRow_']");
		var size = list.length;
		if(size > 1){
			for(var i=size-1;i>=1;i--){
				$("div[id*='paymentRow_" + i + "']").remove();
			}
		}
		$("#zone").val("").focus();
		$("#creditNumber_0").val("");
		$("#paymentAmount_0").val("");

		
		return;
	});
	
	$("#btnAccept").on('click', function(){
		
		
		
		return;
	});
	
	return;
}

Payment.addInputs = function(){
	
	$("input[id*='paymentAmount_']").keyup(function(e){
	    if(e.keyCode == 13){
	        $("#btnPaymentAdd").trigger('click');
			$("input[id*='creditNumber_']:last").focus();
	    }
	    
	    return;
	});
	
	$("input[id*='creditNumber_']").keyup(function(e){
	    if(e.keyCode == 13){
	    	$(this).parent().parent().find('input:last').focus();
	    }
	    
	    return;
	});
	
	return;
}