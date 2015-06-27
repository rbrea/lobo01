Message = function(){}

Message.showMessages = function(messageContainer, container, message){
	var errorText = "<ul class='list-unstyled'>";
	
    errorText += "<li>" + message + "</li>";
	
	errorText += "</ul>";
	
	messageContainer.children('span').eq(0).html('');
	messageContainer.children("span").eq(0).append(
			"<i class='glyphicon glyphicon-warning-sign'> Ups! " + errorText);
	container.removeClass("hide");
	
	return;
}

Message.hideMessages = function(messageContainer, container){
	messageContainer.children("span").eq(0).html("");
	container.addClass("hide");
	
	return;
}

