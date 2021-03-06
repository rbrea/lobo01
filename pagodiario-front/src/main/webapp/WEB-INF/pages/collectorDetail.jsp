<div class="col-md-1">
	&nbsp;
</div>
<div class="col-md-10">
	<div id="collectorDetailMessages" class="row hide">
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	    <div class="col-md-8">
	        <div id="collectorDetailAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#collectorDetailAlertMessages').children('span').eq(0).html('');$('#collectorDetailMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
		  		<div class="panel-heading">
		    		<i class="glyphicon glyphicon-play"></i> Detalle de Cobradores
		    		<div class="pull-right">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                            	<i class="fa fa-chevron-down"></i>
                            </button>
                            <ul class="dropdown-menu pull-right" role="menu">
                                <li>
                                	<a href="javascript:void(0);" onclick="javascript:Collector.exportCollectorDetailToPdf();">
                                    	<i class="glyphicon glyphicon-print"></i>&nbsp;&nbsp;Imprimir
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
		  		</div>
		  		<div class="panel-body">
		  			<div class="row">
		  				<div class="col-md-3">
		  					<label for="week-date-value">Seleccione semana a mostrar:</label> 
		  				</div>
		  				<form id="frmCollectorDetailExportPdf" method="POST" action="${pageContext.request.contextPath}/controller/html/collector/detail/export/pdf">
		  				<div class="col-md-3">
		  					<input type="hidden" name="fromDate" id="fromDate">
		  					<input type="hidden" name="toDate" id="toDate">
		  					<div class='input-group'>
		  						<input id="week-date-value" type="text" class="week-picker form-control"></input>
		  						<span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
		  					</div>
		  				</div>
		  				</form>
		  				<div class="col-md-6">
		  					&nbsp;
		  				</div>
		  			</div>
		  			<div class="row" >
		  				<div class="col-md-12">
		  					&nbsp;
		  				</div>
		  			</div>
		  			<div class="row tools-bar">
		  				<div class="col-md-6">
		  					&nbsp;
		  				</div>
		  				<div class="col-md-4">
		  					<button id="btnSearchCollectorDetail" type="button" data-loading-text="Espere..." class="btn btn-success"><i class="glyphicon glyphicon-search"></i>&nbsp;Buscar</button>
		  					&nbsp;
		  					<button id="btnResetCollectorDetail" type="button" data-loading-text="Espere..." class="btn btn-default"><i class="glyphicon glyphicon-trash"></i>&nbsp;Limpiar</button>
		  				</div>
		  				<div class="col-md-2">
		  					&nbsp;
		  				</div>
		  			</div>
		    		<div class="row" >
		  				<div class="col-md-12">
		  					&nbsp;
		  				</div>
		  			</div>
		    		<div class="row">
						<div class="col-md-12">
							<div class="table-responsive">
								<table id="tCollectorDetailResult" class="table table-condensed display">
									<thead>
							            <tr>
							                <th>C&oacute;digo de Cobrador</th>
							                <th>Zona</th>
							                <th>Nombre</th>
							                <th>Monto Total</th>
							                <th>Monto Cobrado</th>
							                <th>Monto Restante</th>
							                <th>Acciones</th>
							            </tr>
							        </thead>
							        <tbody>
							        </tbody>
							        <tfoot>
								        <tr>
								            <th style="text-align:right" colspan="3"><b>Totales:</b></th>
								            <th id="totalAmount"></th>
								            <th id="collectedAmount"></th>
								            <th id="remainingAmount"></th>
								            <th></th>
								        </tr>
								    </tfoot>
								</table>							
							</div>
						</div>
					</div>
		  		</div>
			</div>	
		</div>
	</div>
</div>
<div class="col-md-1">
	&nbsp;
</div>


<script>
	$(function() {   
	    var startDate;
	    var endDate;
	
	    var selectCurrentWeek = function () {
	        window.setTimeout(function () {
	            $('.ui-weekpicker').find('.ui-datepicker-current-day a').addClass('ui-state-active').removeClass('ui-state-default');
	        }, 1);
	    }
	
	    var setDates = function (input) {
	        var $input = $(input);
	        var date = $input.datepicker('getDate');
	        if (date !== null) {
	            var firstDay = $input.datepicker( "option", "firstDay" );
	            var dayAdjustment = date.getDay() - firstDay;
	            if (dayAdjustment < 0) {
	                dayAdjustment += 7;
	            }
	            startDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - dayAdjustment);
	            endDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - dayAdjustment + 6);
	    
	            var inst = $input.data('datepicker');
	            var dateFormat = inst.settings.dateFormat || $.datepicker._defaults.dateFormat;
	            //$('#startDate').text($.datepicker.formatDate(dateFormat, startDate, inst.settings));
	            //$('#endDate').text($.datepicker.formatDate(dateFormat, endDate, inst.settings));
	            $('#fromDate').val($.datepicker.formatDate(dateFormat, startDate, inst.settings));
	            $('#toDate').val($.datepicker.formatDate(dateFormat, endDate, inst.settings));
	            $("#week-date-value").val($.datepicker.formatDate(dateFormat, startDate, inst.settings) 
	            		+ " - " + $.datepicker.formatDate(dateFormat, endDate, inst.settings));
	        }
	    }
	
	    $('.week-picker').datepicker({
	        beforeShow: function () {
	            $('#ui-datepicker-div').addClass('ui-weekpicker');
	            
	            var $calendarTR = $('.ui-weekpicker .ui-datepicker-calendar tr');
	    	    $calendarTR.on('mousemove', function () {
	    	        $(this).find('td a').addClass('ui-state-hover');
	    	    });
	    	    $calendarTR.on('mouseleave', function () {
	    	        $(this).find('td a').removeClass('ui-state-hover');
	    	    });
	            
	            selectCurrentWeek();
	        },
	        onClose: function () {
	            $('#ui-datepicker-div').removeClass('ui-weekpicker');
	        },
	        showOtherMonths: true,
	        selectOtherMonths: true,
	        onSelect: function (dateText, inst) {
	            setDates(this);
	            selectCurrentWeek();
	            $(this).change();
	        },
	        beforeShowDay: function (date) {
	            var cssClass = '';
	            if (date >= startDate && date <= endDate)
	                cssClass = 'ui-datepicker-current-day';
	            return [true, cssClass];
	        },
	        onChangeMonthYear: function (year, month, inst) {
	            selectCurrentWeek();
	        },
	        dateFormat: 'dd/mm/yy'
	    });
	    
	    setDates('.week-picker');
	/*
	    var $calendarTR = $('.ui-weekpicker .ui-datepicker-calendar tr');
	    $calendarTR.on('mousemove', function () {
	        $(this).find('td a').addClass('ui-state-hover');
	    });
	    $calendarTR.on('mouseleave', function () {
	        $(this).find('td a').removeClass('ui-state-hover');
	    });
	    */
	});

	$(document).ready(
		function(){
			
			var imgCheckUrl = "${pageContext.request.contextPath}/public/images/checkmark-outline_32x32.png";

			
			$("#btnSearchCollectorDetail").on('click', function(){
				
				Collector.initCollectorDetail($("#fromDate").val(), $("#toDate").val(), imgCheckUrl);
				
				return;
			});
			
			$("#btnResetCollectorDetail").on('click', function(){
		
				$("#week-date-value").val("");
				$("#fromDate").val("");
				$("#toDate").val("");
				
				var table = $('#tCollectorDetailResult').DataTable();
				 
				table
				    .clear()
				    .draw();
				
				return;
			});
			
			return;
		}	
		
	);
</script>