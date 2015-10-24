<div class="col-md-12">
	<div class="row">
		<div class="col-md-12">
			&nbsp;
		</div>
	</div>
	 <!--  
	<div class="row">
		<div class="col-md-4">
			&nbsp;
		</div>
		<div class="col-md-4 centered">
			<span style="font-weight: bold;">
				Sistema de Gesti&oacute;n de Pago Diario
			</span>
		</div>
		<div class="col-md-4">
			&nbsp;
		</div>	
	</div>
	-->
	<div class="row">
		<div class="col-md-1">
			&nbsp;
		</div>
		<div class="col-md-10">
			<div class="panel panel-default">
				<div class="panel-heading">
			    	<h3 class="panel-title"><i class="fa fa-area-chart"></i>&nbsp;&nbsp;Dashboard</h3>
			  	</div>
			  	<div class="panel-body">
			    	<div class="container-fluid">
						<div class="row">
							<div class="col-md-3">
					           <div class="panel panel-primary">
					               <div class="panel-heading">
					                   <div class="row">
					                       <div class="col-xs-3">
					                           <i class="fa fa-line-chart fa-5x"></i>
					                       </div>
					                       <div class="col-xs-9 text-right">
					                           <div class="huge" id="totalAmountValue"></div>
					                           <div>Total Facturado</div>
					                       </div>
					                   </div>
					               </div>
					               <a href="javascript:void(0);" id="btnTopSalesChart">
					                   <div class="panel-footer">
					                       <span class="pull-left">FACTURACI&Oacute;N</span>
					                       <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					                       <div class="clearfix"></div>
					                   </div>
					               </a>
					           </div>
					       </div>
					       <div class="col-md-3">
					            <div class="panel panel-green">
					                <div class="panel-heading">
					                    <div class="row">
					                        <div class="col-xs-3">
					                            <i class="fa fa-bar-chart fa-5x"></i>
					                        </div>
					                        <div class="col-xs-9 text-right">
					                            <div class="huge" id="totalCollectedValue"></div>
					                            <div>Total Cobrado</div>
					                        </div>
					                    </div>
					                </div>
					                <a href="#">
					                    <div class="panel-footer">
					                        <span class="pull-left">FACTURACI&Oacute;N</span>
					                        <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					                        <div class="clearfix"></div>
					                    </div>
					                </a>
					            </div>
					        </div>
					        <div class="col-md-3">
					            <div class="panel panel-yellow">
					                <div class="panel-heading">
					                    <div class="row">
					                        <div class="col-xs-3">
					                            <i class="fa fa-area-chart fa-5x"></i>
					                        </div>
					                        <div class="col-xs-9 text-right">
					                            <div class="huge" id="totalCommissionValue"></div>
					                            <div>Total Comisiones</div>
					                        </div>
					                    </div>
					                </div>
					                <a href="#">
					                    <div class="panel-footer">
					                        <span class="pull-left">FACTURACI&Oacute;N</span>
					                        <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					                        <div class="clearfix"></div>
					                    </div>
					                </a>
					            </div>
					        </div>
					        <div class="col-md-3">
					            <div class="panel panel-red">
					                <div class="panel-heading">
					                    <div class="row">
					                        <div class="col-xs-3">
					                            <i class="fa fa-pie-chart fa-5x"></i>
					                        </div>
					                        <div class="col-xs-9 text-right">
					                            <div class="huge" id="cantBillsValue"></div>
					                            <div>Facturas Activas</div>
					                        </div>
					                    </div>
					                </div>
					                <a href="#">
					                    <div class="panel-footer fred">
					                        <span class="pull-left">FACTURACI&Oacute;N</span>
					                        <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
					                        <div class="clearfix"></div>
					                    </div>
					                </a>
					            </div>
					        </div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="panel panel-default">
									<div class="panel-heading">
										<i class="fa fa-line-chart"></i>&nbsp;&nbsp;Total Facturado por Vendedor
										<div class="widget-icons pull-right">
					                 		<a id="chartCollapseButton-01" class="wminimize" data-toggle="collapse" href="#body-01" 
					                 			 aria-expanded="true" aria-controls="body-01">
					                 			<i class="fa fa-chevron-up"></i>
					                 		</a>&nbsp;&nbsp; 
					               		</div>
									</div>
									<div id="body-01" class="panel-body in">
										<div id="placeholder" style="height: 305px;">
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="panel panel-default">
									<div class="panel-heading">
										<i class="fa fa-pie-chart"></i>&nbsp;&nbsp;Total por Vendedor
										<div class="widget-icons pull-right">
					                 		<a id="chartCollapseButton-02" class="wminimize" data-toggle="collapse" href="#body-02" 
					                 			 aria-expanded="true" aria-controls="body-02">
					                 			<i class="fa fa-chevron-up"></i>
					                 		</a>&nbsp;&nbsp; 
					               		</div>
									</div>
									<div id="body-02" class="panel-body in">
										<div id="pie-chart-example" style="height: 305px;width:100%;">
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="panel panel-default">
									<div class="panel-heading">
										<i class="fa fa-bar-chart"></i>&nbsp;&nbsp;Total por Cobrador
										<div class="widget-icons pull-right">
					                 		<a id="chartCollapseButton-03" class="wminimize" data-toggle="collapse" href="#body-03" 
					                 			 aria-expanded="true" aria-controls="body-03">
					                 			<i class="fa fa-chevron-up"></i>
					                 		</a>&nbsp;&nbsp; 
					               		</div>
									</div>
									<div id="body-03" class="panel-body in">
										<div id="flot-placeholder" style="height: 305px;">
										</div>
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
	</div>
</div>

<script>

	$(document).ready(
		function(){

			Chart.init();
			// Shim allowing us to get the state of the check-box on jQuery versions
			// prior to 1.6, when prop was added.  The reason we don't just use attr
			// is because it doesn't work in jQuery versions 1.9.x and later.
	
			// TODO: Remove this once Flot's minimum supported jQuery reaches 1.6.
			if (typeof $.fn.prop != 'function') {
			    $.fn.prop = $.fn.attr;
			}
			
			var sin = [], cos = [];
		    for (var i = 0; i < 14; i += 0.5) {
		        sin.push([i, Math.sin(i)]);
		        cos.push([i, Math.cos(i)]);
		    }
	
			var plot = $.plot($("#curve-chart"),
	           [ { data: sin, label: "sin(x)"}, { data: cos, label: "cos(x)" } ], {
	               series: {
	                   lines: { show: true, fill: true},
	                   points: { show: true }
	               },
	               grid: { hoverable: true, clickable: true, borderWidth:0 },
	               yaxis: { min: -1.2, max: 1.2 },
	               colors: ["#1eafed", "#1eafed"]
	             });			
			
			var previousPoint = null;
		    $("#curve-chart").bind("plothover", function (event, pos, item) {
		        $("#x").text(pos.x.toFixed(2));
		        $("#y").text(pos.y.toFixed(2));

		        if ($("#enableTooltip:checked").length > 0) {
		            if (item) {
		                if (previousPoint != item.dataIndex) {
		                    previousPoint = item.dataIndex;
		                    
		                    $("#tooltip").remove();
		                    var x = item.datapoint[0].toFixed(2),
		                        y = item.datapoint[1].toFixed(2);
		                    
		                    showTooltip(item.pageX, item.pageY, 
		                                item.series.label + " of " + x + " = " + y);
		                }
		            }
		            else {
		                $("#tooltip").remove();
		                previousPoint = null;            
		            }
		        }
		    }); 

		    $("#curve-chart").bind("plotclick", function (event, pos, item) {
		        if (item) {
		            $("#clickdata").text("You clicked point " + item.dataIndex + " in " + item.series.label + ".");
		            plot.highlight(item.series, item.datapoint);
		        }
		    });
		    
			return;
		}		
	);

</script>

