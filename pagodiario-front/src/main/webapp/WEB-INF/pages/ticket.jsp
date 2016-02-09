<div class="col-md-1">
	&nbsp;
</div>
<div class="col-md-10">
	<div id="ticketMessages" class="row hide">
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	    <div class="col-md-8">
	        <div id="ticketAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#ticketAlertMessages').children('span').eq(0).html('');$('#ticketMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div id="pnlTicket" class="panel panel-default">
		  		<div class="panel-heading">
		    		<h3 class="panel-title"><i class="fa fa-file-text-o fa-fw"></i> Buscar tickets de cobro</h3>
		  		</div>
		  		<div class="panel-body">
		  			<div class="row">
		  				<div class="col-md-6">
		  					<div class="panel panel-default">
							  	<div class="panel-body">
								  	<form id="frmTicketPrintFile" action="${pageContext.request.contextPath}/controller/html/ticket" method="post">
									    <input type="hidden" id="printKey" name="printKey" value="">
									</form>
							  		<span class="btn btn-success fileinput-button">
								        <i class="glyphicon glyphicon-plus"></i>
								        <span>Agregar Archivo</span>
								        <!-- The file input field used as target for the file upload widget -->
								        <input id="fileupload" type="file" name="files[]" multiple>
								    </span>
								    &nbsp;
								    <button id='fileReset' class='btn btn-danger' onclick='javascript:resetFile();'><i class="fa fa-trash"></i>&nbsp;Borrar</button>&nbsp;
								    <button id='btnPrintFileContent' class='btn btn-info disabled' onclick='javascript:printFileContent();'><i class="fa fa-print"></i>&nbsp;Imprimir</button>
								    <br>
								    <br>
								    <!-- The global progress bar -->
								    <div id="progress" class="progress">
								        <div class="progress-bar progress-bar-warning"></div>
								    </div>
								    <!-- The container for the uploaded files -->
								    <div id="files" class="files"></div>
								    <br>
							  	
							  	</div>
							</div>
		  				</div>
		  				<div class="col-md-6">
		  					<div class="panel panel-default">
							  	<div class="panel-body">
		  			<form id="frmTicket" action="${pageContext.request.contextPath}/controller/html/ticket" method="post">
							  		<div class="row">
						  				<div class="form-group col-md-6">
						  					<div class="form-group">
						  						<label for="ticketDateValue">Fecha de Ticket</label>
								                <div class='input-group date' id='ticketDate'>
								                    <input type='text' class="form-control" id="ticketDateValue" name="ticketDateValue" placeholder="Ingrese fecha de Ticket..." data-required-error="Requerido" required/>
								                    <span class="input-group-addon">
								                        <span class="glyphicon glyphicon-calendar"></span>
								                    </span>
								                </div>
								            </div>
						  				</div>
						  				<div class="col-md-6">
						  					&nbsp;
						  				</div>
						  			</div>
							  		<div class="row">
							  			<div class="col-md-3">
											<input type="hidden" id="zone" name="zone">
											<label for="bCollectorId">Zona</label>
											<div class="form-group">
								                <input type="number" class="form-control " id="bCollectorId" name="bCollectorId" placeholder="ID" min="1" data-error="Requerido" required>
												<div class="help-block with-errors"></div>
								            </div>		  					
						  				</div>
						  				<div class="col-md-9">
						  					<label for="bCollectorDescription">Descripci&oacute;n de Cobrador</label>
											<div class="form-group">
						  						<div class="input-group">
								                	<input type="text" class="form-control not-writable" id="bCollectorDescription" 
								                			name="bCollectorDescription" placeholder="Zona / Descripci&oacute;n" data-error="Requerido" required>
								                	<span id="btnTicketSearchCollector" class="input-group-addon lov"><i class="glyphicon glyphicon-search"></i></span>
												</div>
												<div class="help-block with-errors"></div>
								            </div>		  					
						  				</div>
							  		</div>
							  		</form>
							  	</div>
							</div>
		  				</div>
					</div>
		  		</div>
			</div>	
		</div>
	</div>
	<div class="row" >
		<div class="col-md-8">
			&nbsp;
		</div>
		<div class="col-md-4">
			<button id="btnValidation" type="button" data-loading-text="Espere..." class="btn btn-primary"><i class="glyphicon glyphicon-ok-circle"></i>&nbsp;Validar</button>&nbsp;
			<button id="btnAccept" type="button" data-loading-text="Espere..." class="btn btn-success disabled"><i class="glyphicon glyphicon-print"></i>&nbsp;Generar</button>&nbsp;
			<button id="btnReset" type="button" data-loading-text="Espere..." class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i>&nbsp;Limpiar</button>
		</div>
	</div>
</div>
<div class="col-md-1">
	&nbsp;
</div>


<script>
	$(document).ready(
		function(){
			
			Ticket.init();
			
			return;
		}	
		
	);
</script>

<script>
/*jslint unparam: true, regexp: true */
/*global window, $ */
$(function () {
    'use strict';
    // Change this to the location of your server-side upload handler:
    var url = '${pageContext.request.contextPath}/controller/html/ticket/upload',
        uploadButton = $('<button/>')
            .addClass('btn btn-primary')
            .prop('disabled', true)
            .text('Procesando...')
            .on('click', function () {
                var $this = $(this),
                    data = $this.data();
                $this
                    .off('click')
                    .text('Cancelar')
                    .on('click', function () {
                        $this.remove();
                        data.abort();
                    });
                data.submit().always(function () {
                    $this.remove();
                });
            });
    $('#fileupload').fileupload({
        url: url,
        dataType: 'json',
        autoUpload: false,
        acceptFileTypes: /(\.|\/)(txt)$/i,
        maxFileSize: 999000,
        // Enable image resizing, except for Android and Opera,
        // which actually support image resizing, but fail to
        // send Blob objects via XHR requests:
        disableImageResize: /Android(?!.*Chrome)|Opera/
            .test(window.navigator.userAgent),
        previewMaxWidth: 100,
        previewMaxHeight: 100,
        previewCrop: true
    }).on('fileuploadadd', function (e, data) {
    	
    	Message.hideMessages($('#ticketAlertMessages'), $("#ticketMessages"));
    	
        data.context = $('<div/>').appendTo('#files');
        $.each(data.files, function (index, file) {
            var node = $('<p/>')
                    .append($('<span/>').text(file.name));
            if (!index) {
                node
                    .append('<br>')
                    .append(uploadButton.clone(true).data(data));
            }
            node.appendTo(data.context);
        });
        
    }).on('fileuploadprocessalways', function (e, data) {
        var index = data.index,
            file = data.files[index],
            node = $(data.context.children()[index]);
        if (file.preview) {
            node
                .prepend('<br>')
                .prepend(file.preview);
        }
        if (file.error) {
        	
        	var errorMessage = file.error;
        	if(errorMessage == 'File type not allowed'){
        		errorMessage = 'Tipo de archivo no permitido. Solo se acepta: .txt';
        	}
            node
                .append('<br>')
                .append($('<span class="text-danger"/>').text(errorMessage));
        }
        if (index + 1 === data.files.length) {
            data.context.find('button')
                .html('<i class="fa fa-upload"></i>&nbsp;Cargar Archivo')
                .prop('disabled', !!data.files.error);
        }
    }).on('fileuploadprogressall', function (e, data) {
        var progress = parseInt(data.loaded / data.total * 100, 10);
        $('#progress .progress-bar').css(
            'width',
            progress + '%'
        );
        
        return;
    }).on('fileuploaddone', function (e, data) {

    	var r = data.result;
    	
    	if(Commons.isValid(r.message)){
    		Message.showMessages($('#ticketAlertMessages'), $("#ticketMessages"), r.message);
    		
    		return false;
    	}
    	
    	$("#printKey").val(r.printKey);
    	$("#btnPrintFileContent").removeClass("disabled");
    	
    	/*
    	$.each(data.result.files, function (index, file) {
            if (file.url) {
                var link = $('<a>')
                    .attr('target', '_blank')
                    .prop('href', file.url);
                $(data.context.children()[index])
                    .wrap(link);
            } else if (file.error) {
                var error = $('<span class="text-danger"/>').text(file.error);
                $(data.context.children()[index])
                    .append('<br>')
                    .append(error);
            }
        });
    	*/
    	
    	return;
    }).on('fileuploadfail', function (e, data) {
    	
        $.each(data.files, function (index) {
            var error = $('<span class="text-danger"/>').text('El archivo no pudo ser cargado...');
            $(data.context.children()[index])
                .append('<br>')
                .append(error);
        });
    }).prop('disabled', !$.support.fileInput)
        .parent().addClass($.support.fileInput ? undefined : 'disabled');
});

resetFile = function(){
	
	Message.hideMessages($('#ticketAlertMessages'), $("#ticketMessages"));
	
	var progressElem = $("#progress");
	
	progressElem.children().remove();
	
	progressElem.append("<div class=\"progress-bar progress-bar-warning\"></div>");
	
	var d = $("div[id='files']");
	
	d.children().remove();
	
	$("#printKey").val("");
	
	//$("#fileReset").remove();
	
	$("#btnPrintFileContent").addClass("disabled");
	
	return;
}

printFileContent = function(){
	
	$("#frmTicketPrintFile").submit();
	
	return;
}

</script>