<!-- Fixed navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top navbar-grad" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header" style="height:20px;">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}/controller/html/index">
				<img src="${pageContext.request.contextPath}/public/images/ice-tea-logo50x50.png">&nbsp;SGPD - Sistema de Gesti&oacute;n de Pago Diario - versi&oacute;n 1.0
			</a>
		</div>
		<form id="frmLogout" action="${pageContext.request.contextPath}/controller/html/logout"></form>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right" style="padding-top: 1.2%;">
				<li class="dropdown icon-circle">
	              <a id="drop1" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-balance-scale fgrey"></i>&nbsp;&nbsp;Cr&eacute;ditos <span class="caret"></span></a>
	              <ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
	                <li role="presentation" class="icon-circle">
	                	<a id="optCargarFactura" style="cursor:pointer;" role="menuitem" tabindex="-1" 
	                			href="${pageContext.request.contextPath}/controller/html/bill/index">
	                		<i class="fa fa-list-alt fa-fw"></i>&nbsp;Cargar Factura (Cr&eacute;dito)
	                	</a>
	                </li>
	                <li role="presentation" class="icon-circle">
	                	<a id="optHistorialFactura" style="cursor:pointer;" role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/controller/html/bill/history/index"><i class="fa fa-folder-open-o fa-fw"></i>&nbsp;Consultar Cr&eacute;ditos</a>
	                </li>
	                <li role="presentation" class="icon-circle">
	                	<a id="optPremio" style="cursor:pointer;" role="menuitem" tabindex="-1" 
	                		href="${pageContext.request.contextPath}/controller/html/payment/index">
	                			<i class="fa fa-credit-card fa-fw"></i>&nbsp;Cargar Pagos
	                	</a>
	                </li>
	               	<li role="presentation" class="divider"></li>
	               	<li role="presentation" class="icon-circle">
	               		<a id="optGenerarCuponCobro" style="cursor:pointer;" role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/controller/html/ticket/index">
	               			<i class="fa fa-file-text-o fa-fw"></i>&nbsp;Generar cup&oacute;n de Cobro (ticket)
	               		</a>
	               	</li>
	              </ul>
	            </li>
	            <li class="dropdown icon-circle">
	              <a id="drop2" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-calendar-plus-o fgrey"></i>&nbsp;&nbsp;Liquidaciones <span class="caret"></span></a>
	              <ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
	                <li role="presentation" class="ROLE_ADMIN icon-circle">
	                	<a id="optEjecutarLiq" style="cursor:pointer;" role="menuitem" tabindex="-1"
	                			href="${pageContext.request.contextPath}/controller/html/payroll/index">
		                		<i class="fa fa-calculator fa-fw"></i>
	                		&nbsp;Vendedor / Supervisor
	                	</a>
	                </li>
	                <li role="presentation" class="divider"></li>
	                <li role="presentation" class="icon-circle">
	                	<a id="opt" style="cursor:pointer;" role="menuitem" tabindex="-1" 
	                			href="${pageContext.request.contextPath}/controller/html/payrollcollect/index">
	                		<i class="glyphicon glyphicon-calendar"></i>&nbsp;Liquidaci&oacute;n de Cobradores
	                	</a>
	                </li>
	              </ul>
	            </li>
	            <li class="dropdown icon-circle">
	              <a id="drop2" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-industry fgrey"></i>&nbsp;&nbsp;Administraci&oacute;n <span class="caret"></span></a>
	              <ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
	                <li role="presentation" class="ROLE_ADMIN icon-circle">
	                	<a id="optEjecutarLiq" style="cursor:pointer;" role="menuitem" tabindex="-1"
	                			href="${pageContext.request.contextPath}/controller/html/dashboard/index">
		                		<i class="fa fa-bar-chart"></i>
	                		&nbsp;Tablero de Control
	                	</a>
	                </li>
	              </ul>
	            </li>
				<li class="dropdown icon-circle">
	              <a id="dropAbm" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-sitemap fgrey"></i>&nbsp;&nbsp;ABM <span class="caret"></span></a>
	              <ul class="dropdown-menu" role="menu" aria-labelledby="dropAbm">
	              	<li role="presentation" class="icon-circle">
	                	<a id="optClients" style="cursor:pointer;" role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/controller/html/client/index"><i class="glyphicon glyphicon-briefcase"></i>&nbsp;Clientes</a>
	                </li>
	                <li role="presentation" class="icon-circle">
	                	<a id="optProducts" style="cursor:pointer;" role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/controller/html/product/index"><i class="fa fa-money fa-fw"></i>&nbsp;Productos</a>
	                </li>
	                <li role="presentation" class="icon-circle">
	                	<a id="optTraders" style="cursor:pointer;" role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/controller/html/trader/index"><i class="fa fa-venus-mars"></i>&nbsp;Vendedores/Supervidores</a>
	                </li>
	                <li role="presentation" class="icon-circle">
	                	<a id="optCollectors" style="cursor:pointer;" role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/controller/html/collector/index">
	                		<i class="glyphicon glyphicon-inbox"></i>&nbsp;Cobradores
	                	</a>
	                </li>
	                <li role="presentation" class="divider"></li>
	                <li role="presentation" class="ROLE_ADMIN icon-circle">
	                	<a id="optUsuarios" style="cursor:pointer;" role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/controller/html/user">
	                		<i class="fa fa-users fa-fw"></i>&nbsp;Usuarios
	                	</a>
	                </li>
	                <!--  
	                <li role="presentation" class="divider"></li>
	                <li role="presentation"><a role="menuitem" tabindex="-1" href="http://twitter.com/fat">Separated link</a></li>
	                -->
	              </ul>
	            </li>
	            <li class="dropdown icon-circle">
	              <a id="dropUser" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">
	              	<i class="glyphicon glyphicon-user fgrey"></i>&nbsp;&nbsp;<span id="userLogged"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="caret"></span>
	              </a>
	              <ul class="dropdown-menu" role="menu" aria-labelledby="dropUser">
	              	<li><a id="btnLogout" href="javascript:void(0);"><i class="glyphicon glyphicon-off"></i>&nbsp;Cerrar sesi&oacute;n</a></li>
	              </ul>
	            </li>
				<!--  <li><a id="btnLogout" href="javascript:void(0);">Cerrar sesi&oacute;n</a></li> -->
			</ul>
			<form class="navbar-form navbar-right">
				
			</form>
			<div class="nav navbar-nav navbar-right" style="width:15%;">&nbsp;</div>
			<div class="nav navbar-nav navbar-right" style="width:10%;">&nbsp;</div>
			<!--  
			<div id="loading" class="nav navbar-nav navbar-right" style="color: WHITE;height:40px;margin-top:0.5%;position:relative;">
				<i class="fa fa-spinner fa-5 fa-spin"></i> &nbsp;Espere por favor ...
			</div>
			-->
		</div>
	</div>
</nav>

<div id="loading-message-div">
	<img src="${pageContext.request.contextPath}/public/images/loader.gif" width="32px">&nbsp;&nbsp;Espere por favor ...
</div>

<script>
		
    	$(document).ready(
    		function(){
    			
    			$("#loading-message-div").css({"display": "none"});
    			
    	        $(document).ajaxStart(function () {
    	        	$("#loading-message-div").css({"display": "block"});
    	        	
    	        	return;
    	        });

    	        $(document).ajaxStop(function () {
    	        	$("#loading-message-div").css({"display": "none"});
    	        	
    	        	return;
    	        });
    	        
    	        $("#btnLogout").click(
    	        	function(){
    	        		$("#frmLogout").submit();
    	        		
    	        		return;
    	        	}		
    	        );

    	        Commons.init();
    	        
    	        $('.not-writable').keypress(function(e) {
    	            e.preventDefault();
    	            
    	            return;
    	    	}).css({
    	    		"cursor": "not-allowed"
    	    	});
    	        
    	        User.printUsername();
    	        
    			return;
    		}
    	);

</script>