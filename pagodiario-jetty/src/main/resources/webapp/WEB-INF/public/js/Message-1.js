Message = function(){}

Message.showMessages = function(messageContainer, container, message, containerClass, icon, introMessage){
	var errorText = "<ul class='list-unstyled'>";
	
    errorText += "<li>" + message + "</li>";
	
	errorText += "</ul>";

	messageContainer.removeClass("alert-danger");
	messageContainer.removeClass("alert-warning");
	
	if(containerClass == "" || containerClass == null || containerClass === undefined){
		containerClass = "alert-danger";
	}

	messageContainer.addClass(containerClass);
	
	if(icon == "" || icon == null || icon === undefined){
		icon = "glyphicon-warning-sign";
	}
	
	if(introMessage == "" || introMessage == null || introMessage === undefined){
		introMessage = "Ups! ";
	}
	
	messageContainer.children('span').eq(0).html('');
	messageContainer.children("span").eq(0).append(
			"<i class='glyphicon " + icon + "'>&nbsp;" + introMessage + errorText);
	container.removeClass("hide");
	
	return;
}

Message.hideMessages = function(messageContainer, container){
	messageContainer.children("span").eq(0).html("");
	container.addClass("hide");
	
	return;
}

Message.createErrorBlockMessage = function(text){
	
	var ul = $("<ul></ul>").addClass("list-unstyled");
	var li = $("<li></li>").append(text);
	ul.append(li);
	
	return ul;
}

