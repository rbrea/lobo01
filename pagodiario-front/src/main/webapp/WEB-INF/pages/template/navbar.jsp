<style>
	.table-hover tbody tr:hover > td {
	  cursor: pointer;
	}
</style>

<!-- Fixed navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}/controller/html/index">
				<img src="${pageContext.request.contextPath}/public/images/ice-tea-logo50x50.png">&nbsp;SGPD - Sistema de Gesti&oacute;n de Pago Diario
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
	                		<i class="glyphicon glyphicon-list-alt"></i>&nbsp;Cargar Factura
	                	</a>
	                </li>
	                <li role="presentation">
	                	<a id="optHistorialFactura" style="cursor:pointer;" role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/controller/html/bill/history/index"><i class="glyphicon glyphicon-calendar"></i>&nbsp;Historial Factura</a>
	                </li>
	                <li role="presentation">
	                	<a id="optDevoluciones" style="cursor:pointer;" role="menuitem" tabindex="-1" href="#"><i class="glyphicon glyphicon-retweet"></i>&nbsp;Devoluciones</a>
	                </li>
	                <li role="presentation">
	                	<a id="optBajas" style="cursor:pointer;" role="menuitem" tabindex="-1" href="#"><i class="glyphicon glyphicon-thumbs-down"></i>&nbsp;Bajas</a>
	                </li>
	               	<li role="presentation" class="divider"></li>
	               	<li role="presentation">
	               		<a id="optGenerarCuponCobro" style="cursor:pointer;" role="menuitem" tabindex="-1" href="#" disabled><i class="glyphicon glyphicon-usd"></i>&nbsp;Generar cup&oacute;n de Cobro (ticket)</a>
	               	</li>
	              </ul>
	            </li>
	            <li class="dropdown">
	              <a id="drop2" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">Liquidaciones <span class="caret"></span></a>
	              <ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
	                <li role="presentation">
	                	<a id="optEjecutarLiq" style="cursor:pointer;" role="menuitem" tabindex="-1" 
	                			href="#" class="disabled">
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
				<li><a id="btnLogout" href="javascript:void(0);">Cerrar sesi&oacute;n</a></li>
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
	<img src="${pageContext.request.contextPath}/public/images/spiffygif_32x32.gif">&nbsp;&nbsp;Espere por favor ...
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
    			
    			return;
    		}
    	);

</script>