Chart = function(){}

Chart.init = function(){
	
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
	
	var donutChart = Morris.Donut({
	  element: 'collectedByZone-area-chart',
	  data: [
	    {label: "Zona 1", value: 1200},
	    {label: "Zona 2", value: 3000},
	    {label: "Zona 3", value: 2000},
	    {label: "Zona 4", value: 1500},
	    {label: "Zona 5", value: 800}
	  ],
	  colors: ["#2E64FE", "#2EFE9A", "#F7FE2E", "#FE9A2E", "#FE2E2E"]
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
