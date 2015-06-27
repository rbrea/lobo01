	$(document).ready(
		function(){
			AjaxCommons.init();
			
			return;
		}		
	);


/*
 * AjaxCommons class  
 * 
 */
AjaxCommons = function(){}

AjaxCommons.counter = 0;

AjaxCommons.init = function(){
	// se encarga de mostrar los loading en las llamadas ajax ...
	$("body").on({
        ajaxStart: function() {
            $(this).addClass("loading"); 
        },
        ajaxStop: function() {
            $(this).removeClass("loading"); 
        }    
    });

	return;
}

AjaxCommons.get = function(url, data, doSuccess, doLoading, doDone, dataType, async) {
	return AjaxCommons.call("GET", url, data, doSuccess, doLoading, doDone, dataType, async); 
}

AjaxCommons.post = function(url, data, doSuccess, doLoading, doDone, dataType, async) {
	return AjaxCommons.call("POST", url, data, doSuccess, doLoading, doDone, dataType, async); 
}

AjaxCommons.call = function(method, url, data, doSuccess, doLoading, doDone, dataType, async) {
	if(Commons.isNotValid(url)){
		alert("empty url param not supported...");
		
		return false;
	}
	if(Commons.isNotValid(method)){
		alert("empty method param not supported...");
		
		return false;
	}
	if(Commons.isNotValid(dataType)){
		dataType = null;
	}
	if(Commons.isNotValid(async)){
		async = true;
	}
	if(Commons.isValid(doLoading)){
		doLoading(true);
	}
	AjaxCommons.counter++;
	$.ajax({
	  url: url,
	  type: method,
	  data: data,
	  dataType: dataType,
	  async: async,
	  success: function(data, textStatus, jqXHR) {
		  var urlRedirect = jqXHR.getResponseHeader("url.redirect");
		  // esta validacion se realiza ya que el browser esta redirigiendo sin devolverle el control al ajax ... 
		  // por lo tanto al estar llegando un 200 en vez de un 302 ... optamos por incluir en el playPlugin's handler @see RedirectHandler.java ...
		  // logica para determinar este factor y setear al header del response un parametro que permita redirigir y modificar comportamiento al 
		  // presentarse este issue ...
		  if(urlRedirect == null || urlRedirect === ""){
			  if(Commons.isValid(doSuccess)){
				  doSuccess(data);
			  }
			  if(Commons.isValid(doLoading)){
				  doLoading(false);
			  }
		  } else {
			  window.location.replace(urlRedirect);
		  }
		  
	      return;
	  }
	  }).done(
		  function() {
			  if(Commons.isValid(doDone)){
				  doDone();
			  }
			  
			  return;
		  }
	  );
	
	return true;
}

AjaxCommons.onRequestCompleted = function(xhr, textStatus) {
	if(xhr.getResponseHeader("REDIRECT_FLAG") == "99"){
	}
	
	if (xhr.status == 302) {
	   location.href = xhr.getResponseHeader("Location");
	}
   
   return;
}

AjaxCommons.onError = function(xhr, textStatus) {
	//alert("onError --> " + xhr.status)
   if (xhr.status == 302) {
	  // alert("302 error");
	   location.href = xhr.getResponseHeader("Location");
   }
   
   return;
}