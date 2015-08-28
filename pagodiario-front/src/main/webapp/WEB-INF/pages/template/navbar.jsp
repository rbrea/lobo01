<!-- Fixed navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top navbar-grad" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}/controller/html/index">
				<img src="${pageContext.request.contextPath}/public/images/ice-tea-logo50x50.png">&nbsp;SGPD - Sistema de Gesti&oacute;n de Pago Diario - versi&oacute;n 0.1-BETA
			</a>
		</div>
		<form id="frmLogout" action="${pageContext.request.contextPath}/controller/html/logout"></form>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right" style="padding-top: 1.2%;">
				<li class="dropdown">
	              <a id="drop1" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">Cr&eacute;ditos <span class="caret"></span></a>
	              <ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
	                <li role="presentation">
	                	<a id="optCargarFactura" style="cursor:pointer;" role="menuitem" tabindex="-1" 
	                			href="${pageContext.request.contextPath}/controller/html/bill/index">
	                		<i class="glyphicon glyphicon-list-alt"></i>&nbsp;Cargar Factura (Cr&eacute;dito)
	                	</a>
	                </li>
	                <li role="presentation">
	                	<a id="optHistorialFactura" style="cursor:pointer;" role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/controller/html/bill/history/index"><i class="glyphicon glyphicon-calendar"></i>&nbsp;Consultar Cr&eacute;ditos</a>
	                </li>
	                <li role="presentation">
	                	<a id="optPremio" style="cursor:pointer;" role="menuitem" tabindex="-1" 
	                		href="${pageContext.request.contextPath}/controller/html/payment/index">
	                			<i class="glyphicon glyphicon-tag"></i>&nbsp;Cargar Pagos
	                	</a>
	                </li>
	               	<li role="presentation" class="divider"></li>
	               	<li role="presentation">
	               		<a id="optGenerarCuponCobro" style="cursor:pointer;" role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/controller/html/ticket/index">
	               			<i class="glyphicon glyphicon-usd"></i>&nbsp;Generar cup&oacute;n de Cobro (ticket)
	               		</a>
	               	</li>
	              </ul>
	            </li>
	            <li class="dropdown">
	              <a id="drop2" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">Liquidaciones <span class="caret"></span></a>
	              <ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
	                <li role="presentation">
	                	<a id="optEjecutarLiq" style="cursor:pointer;" role="menuitem" tabindex="-1" 
	                			href="${pageContext.request.contextPath}/controller/html/payroll/index" class="disabled">
	                		<i class="glyphicon glyphicon-play-circle"></i>&nbsp;Ejecutar Liquidaci&oacute;n
	                	</a>
	                </li>
	                <li role="presentation">
	                	<a id="optHistorialLiq" style="cursor:pointer;" role="menuitem" tabindex="-1" href="#"><i class="glyphicon glyphicon-calendar"></i>&nbsp;Historial</a>
	                </li>
	                <li role="presentation" class="divider"></li>
	                <li role="presentation">
	                	<a id="opt" style="cursor:pointer;" role="menuitem" tabindex="-1" href="#"><i class="glyphicon glyphicon-calendar"></i>&nbsp;Imprimir Liquidaci&oacute;n</a>
	                </li>
	              </ul>
	            </li>
				<li class="dropdown">
	              <a id="dropAbm" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">ABM <span class="caret"></span></a>
	              <ul class="dropdown-menu" role="menu" aria-labelledby="dropAbm">
	              	<li role="presentation">
	                	<a id="optClients" style="cursor:pointer;" role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/controller/html/client/index"><i class="glyphicon glyphicon-briefcase"></i>&nbsp;Clientes</a>
	                </li>
	                <li role="presentation">
	                	<a id="optProducts" style="cursor:pointer;" role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/controller/html/product/index"><i class="glyphicon glyphicon-folder-close"></i>&nbsp;Productos</a>
	                </li>
	                <li role="presentation">
	                	<a id="optTraders" style="cursor:pointer;" role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/controller/html/trader/index"><i class="glyphicon glyphicon-globe"></i>&nbsp;Vendedores/Supervidores</a>
	                </li>
	                <li role="presentation" class="divider"></li>
	                <li role="presentation">
	                	<a id="optUsuarios" style="cursor:pointer;" role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/controller/html/user"><i class="glyphicon glyphicon-user"></i>&nbsp;Usuarios</a>
	                </li>
	                <!--  
	                <li role="presentation" class="divider"></li>
	                <li role="presentation"><a role="menuitem" tabindex="-1" href="http://twitter.com/fat">Separated link</a></li>
	                -->
	              </ul>
	            </li>
	            <li class="dropdown">
	              <a id="dropUser" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">
	              	<i class="glyphicon glyphicon-user"></i>&nbsp;<span id="userLogged"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="caret"></span>
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