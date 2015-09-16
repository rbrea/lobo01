Chart = function(){}

Chart.init = function(){
	
	new Morris.Line({
	  // ID of the element in which to draw the chart.
	  element: 'topSales-area-chart',
	  // Chart data records -- each entry in this array corresponds to a point on
	  // the chart.
	  data: [
	    { year: '2008', value: 20 },
	    { year: '2009', value: 10 },
	    { year: '2010', value: 5 },
	    { year: '2011', value: 5 },
	    { year: '2012', value: 20 }
	  ],
	  // The name of the data record attribute that contains x-values.
	  xkey: 'year',
	  // A list of names of data record attributes that contain y-values.
	  ykeys: ['value'],
	  // Labels for the ykeys -- will be displayed when you hover over the
	  // chart.
	  labels: ['Value'],
	  barColors: ["#2E64FE", "#2EFE9A", "#F7FE2E", "#FE9A2E", "#FE2E2E"]
	});
	
	new Morris.Bar({
	  // ID of the element in which to draw the chart.
	  element: 'top5traders-area-chart',
	  // Chart data records -- each entry in this array corresponds to a point on
	  // the chart.
	  data: [
	    { traderName: 'Rodrigo', value: 1000 },
	    { traderName: 'Jose Maria', value: 500 },
	    { traderName: 'Ramiro', value: 1500 },
	    { traderName: 'Tobias', value: 1500 },
	    { traderName: 'Hernan', value: 2000 }
	  ],
	  // The name of the data record attribute that contains x-values.
	  xkey: 'traderName',
	  // A list of names of data record attributes that contain y-values.
	  ykeys: ['value'],
	  // Labels for the ykeys -- will be displayed when you hover over the
	  // chart.
	  labels: ['$ Monto']
	});
	
	Morris.Donut({
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

	return;
}
