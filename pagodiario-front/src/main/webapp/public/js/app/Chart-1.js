Chart = function(){}

Chart.init = function(){

	Chart.drawDashboard();
	
	Chart.plotSalesByWeek();
	
	return;
}


Chart.initMorris = function(){
	
	var lineChart = new Morris.Line({
	  // ID of the element in which to draw the chart.
	  element: 'topSales-area-chart',
	  // Chart data records -- each entry in this array corresponds to a point on
	  // the chart.
	  /*
	  data: [
	    { year: '2008', value: 20 },
	    { year: '2009', value: 10 },
	    { year: '2010', value: 5 },
	    { year: '2011', value: 5 },
	    { year: '2012', value: 20 }
	  ],*/
	  // The name of the data record attribute that contains x-values.
	  xkey: 'periodo',
	  // A list of names of data record attributes that contain y-values.
	  ykeys: ['value'],
	  // Labels for the ykeys -- will be displayed when you hover over the
	  // chart.
	  labels: ['Value'],
	  parseTime : false
	});
	
	var barChart = new Morris.Bar({
	  // ID of the element in which to draw the chart.
	  element: 'top5traders-area-chart',
	  // Chart data records -- each entry in this array corresponds to a point on
	  // the chart.
	  /*
	  data: [
	    { traderName: 'Rodrigo', value: 1000 },
	    { traderName: 'Jose Maria', value: 500 },
	    { traderName: 'Ramiro', value: 1500 },
	    { traderName: 'Tobias', value: 1500 },
	    { traderName: 'Hernan', value: 2000 }
	  ],*/
	  // The name of the data record attribute that contains x-values.
	  xkey: 'nombre',
	  // A list of names of data record attributes that contain y-values.
	  ykeys: ['value'],
	  // Labels for the ykeys -- will be displayed when you hover over the
	  // chart.
	  labels: ['$ Monto']
	});
	
	var barChart2 = new Morris.Bar({
	  // ID of the element in which to draw the chart.
	  element: 'collectedByZone-area-chart',
	  // Chart data records -- each entry in this array corresponds to a point on
	  // the chart.
	  /*
	  data: [
	    { traderName: 'Rodrigo', value: 1000 },
	    { traderName: 'Jose Maria', value: 500 },
	    { traderName: 'Ramiro', value: 1500 },
	    { traderName: 'Tobias', value: 1500 },
	    { traderName: 'Hernan', value: 2000 }
	  ],*/
	  // The name of the data record attribute that contains x-values.
	  xkey: 'label',
	  // A list of names of data record attributes that contain y-values.
	  ykeys: ['value'],
	  // Labels for the ykeys -- will be displayed when you hover over the
	  // chart.
	  labels: ['$ Monto']
	});
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/chart",
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
			if(data.status == 0){
				lineChart.setData(data.totalSales);
				barChart.setData(data.topTraders);
				barChart2.setData(data.topCollectors);
				//donutChart.setData(data.topCollectors);
			}
	   },
	   error:function(data){
		   //Message.showMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"), data.responseJSON.message);
		   
		   return;
	   }
	});

	return;
}

Chart.showBills = function(){
	
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/chart",
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
			if(data.status == 0){
		/*		
				var list = [ 
				             { label: "Foo", data: [ [10, 1], [17, -14], [30, 5] ] },
				             { label: "Bar", data: [ [11, 13], [19, 11], [30, -7] ] }
				             ];
				var options = {
					    series: {
					        lines: { show: true },
					        points: { show: true }
					    }
					};
		*/		
				BootstrapDialog.show({
					type:BootstrapDialog.TYPE_PRIMARY,
					title: '<i class="fa fa-line-chart"></i>&nbsp;Estadísticas de ventas',
					autodestroy: true,
					draggable: true,
					message: function(dialog) {
						
						var container = $("#placeholder-topsales-container");
						
						container.css({"display" : "block"});
						/*
						var d1 = [];
						for (var i = 0; i < 14; i += 0.5) {
							d1.push([i, Math.sin(i)]);
						}

						var d2 = [[0, 3], [4, 8], [8, 5], [9, 13]];

						var d3 = [];
						for (var i = 0; i < 14; i += 0.5) {
							d3.push([i, Math.cos(i)]);
						}

						var d4 = [];
						for (var i = 0; i < 14; i += 0.1) {
							d4.push([i, Math.sqrt(i * 10)]);
						}

						var d5 = [];
						for (var i = 0; i < 14; i += 0.5) {
							d5.push([i, Math.sqrt(i)]);
						}

						var d6 = [];
						for (var i = 0; i < 14; i += 0.5 + Math.random()) {
							d6.push([i, Math.sqrt(2*i + Math.sin(i) + 5)]);
						}

						$.plot("#placeholder-topsales", [{
							data: d1,
							lines: { show: true, fill: true }
						}, {
							data: d2,
							bars: { show: true }
						}, {
							data: d3,
							points: { show: true }
						}, {
							data: d4,
							lines: { show: true }
						}, {
							data: d5,
							lines: { show: true },
							points: { show: true }
						}, {
							data: d6,
							lines: { show: true, steps: true }
						}]);
						
						*/
						//$.plot($("#placeholder-topsales"), [ [[0, 0], [1, 1]] ], { yaxis: { max: 1 } });
						
						return container;
					}
				});
				
			}
	   },
	   error:function(data){
		   //Message.showMessages($('#billHistoryAlertMessages'), $("#billHistoryMessages"), data.responseJSON.message);
		   
		   return;
	   }
	});
	
	
	
	return;
}

Chart.drawDashboard = function(){

	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/dashboard/info",
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
			if(data.status == 0){
				$("#totalAmountValue").html("$ " + data.totalAmount);
				$("#totalCollectedValue").html("$ " + data.totalCollected);
				if(data.totalcommission == null || data.totalcommission == "" || data.totalcommission === undefined){
					data.totalcommission = "-";
				}
				$("#totalCommissionValue").html("$ " + data.totalcommission);
				$("#cantBillsValue").html(data.countActivesBills);
				
			}
	   },
	   error:function(data){
		   
		   return;
	   }
	});
	
}

Chart.plotSalesByWeek = function(){
	
	$.ajax({ 
	   type    : "GET",
	   url     : Constants.contextRoot + "/controller/html/chart/sales/week",
	   dataType: 'json',
	   contentType: "application/json;",
	   success:function(data) {
			if(data.status == 0){
				
				var data = data.data;
				
				var options = {
					series: {
						lines: {
							show: true
						},
						points: {
							show: true
						}
					},
					legend: {
						noColumns: 2
					},
					xaxis: {
						tickDecimals: 0
					},
					yaxis: {
						min: 0
					},
					selection: {
						mode: "x"
					}
				};

				var placeholder = $("#placeholder");

				placeholder.bind("plotselected", function (event, ranges) {

					$("#selection").text(ranges.xaxis.from.toFixed(1) + " to " + ranges.xaxis.to.toFixed(1));

					var zoom = $("#zoom").prop("checked");

					if (zoom) {
						$.each(plot.getXAxes(), function(_, axis) {
							var opts = axis.options;
							opts.min = ranges.xaxis.from;
							opts.max = ranges.xaxis.to;
						});
						plot.setupGrid();
						plot.draw();
						plot.clearSelection();
					}
				});

				placeholder.bind("plotunselected", function (event) {
					$("#selection").text("");
				});

				var plot = $.plot(placeholder, data, options);
				
				
			}
	   },
	   error:function(data){
		   
		   return;
	   }
	});
	
	return;
}