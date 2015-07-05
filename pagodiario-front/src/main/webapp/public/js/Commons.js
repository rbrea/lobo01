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

