$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

/*
 * Commons class  
 * 
 */
Commons = function(){}

Commons.init = function(){
	$("input").focus(function() { $(this).select(); } ).end().click(function () {$(this).select();});
	
	return;
}

Commons.isValid = function(input){
	if(input === undefined || input == "" || input == null){
		return false;
	}
	
	return true;
}

Commons.isNotValid = function(input){
	return !Commons.isValid(input);
}

Commons.loadAccount = function(accountKeyUrl,status){
	if (status=="INACTIVE"){
		alert("Inactive account");
		return;
	}
	window.location.href = accountKeyUrl
}

Commons.formatDate = function(d){
	var day = d.getDate();
	var month = d.getMonth()+1;
	var year = d.getFullYear();
	var hour = d.getHours();
	var min = d.getMinutes();
	
	if (day<10){
		day="0"+day;
	}
	if (month<10){
		month="0"+month;
	}
	var defaultDateFormat = day+"/"+month+"/"+year;
	var defaultTimeFormat = hour + ":" + min;
	return defaultDateFormat+" "+defaultTimeFormat;	
}

Commons.setTooltip = function(id){
	$("a[id*='" + id + "']").tooltip();
	
	return true;
}


Commons.addNewClonedRow = function(rowGridId, toClone, map){
	
	var i = $("div[id*='" + rowGridId + "']").length;
	
	var newRow = toClone.clone();
	
	newRow.contents().each(function () {
	    if (this.nodeType === 3) this.nodeValue = $.trim($(this).text()).replace(/_X/g, "_" + i)
	    if (this.nodeType === 1) $(this).html( $(this).html().replace(/_X/g, "_" + i) )
	});
	newRow.attr("id", rowGridId + i);
	
	if(map.changeLabel){
		var lbl = newRow.find('label');
		lbl.html("Pago #" + (parseInt(i) + 1));
	}
	if(map.execute != undefined){
		map.execute(newRow);
	}
	
	newRow.removeClass("hide");
	
	newRow.insertAfter($("div[id*='" + rowGridId + "']:last"));
	
	return;
}

Commons.removeClonedRow = function(rowGridId){
	
	var i = $("div[id*='" + rowGridId + "']").length;
	if(i == 1){
		return false;
	}
	$("div[id*='" + rowGridId + "']:last").remove();
	
	return;
}

Commons.gd = function(year, month, day) {
    return new Date(year, month - 1, day).getTime();
}

Commons.getIndexFromString = function(input, valueSearched){
	
	if(Commons.isNotValid(input) || Commons.isNotValid(valueSearched)){
		
		return null;
	}
	
	var resp = input.substring(input.indexOf(valueSearched) + 1);
	
	return resp;
}

Commons.isTrue = function(value){
	return (value == true || value == "true" || value == "checked");
}

Commons.isFalse = function(value){
	return !Commons.isTrue(value);
}

Commons.selectDayOfWeek = function(jElement, value){

	value = Commons.isTrue(value);
	if(value == null){
		return false;
	}
	
	jElement.prop('checked', value);
	if(value){
		jElement.parent().addClass("active");
	} else {
		jElement.parent().removeClass("active");
	}
	
	return;
}