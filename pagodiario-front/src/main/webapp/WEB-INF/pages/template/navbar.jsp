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
			<a class="navbar-brand" href="${pageContext.request.contextPath}/controller/html/index">SGPD - Sistema de Gesti&oacute;n de Pago Diario</a>
		</div>
		<form id="frmLogout" action="${pageContext.request.contextPath}/controller/html/logout"></form>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
	              <a id="drop1" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">Acciones <span class="caret"></span></a>
	              <ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
	                <li role="presentation">
	                	<a id="optCargarFactura" style="cursor:pointer;" role="menuitem" tabindex="-1" 
	                			href="${pageContext.request.contextPath}/controller/html/bill/index">
	                		<i class="glyphicon glyphicon-list-alt"></i>&nbsp;Cargar Factura
	                	</a>
	                	<a id="optCargarFactura" style="cursor:pointer;" role="menuitem" tabindex="-1" 
	                			href="#">
	                		<i class="glyphicon glyphicon-list-alt"></i>&nbsp;Generar cup&oacute;n de Cobro (ticket)
	                	</a>
	                	<a id="optCargarFactura" style="cursor:pointer;" role="menuitem" tabindex="-1" 
	                			href="#">
	                		<i class="glyphicon glyphicon-list-alt"></i>&nbsp;Ingresar Cobro diario
	                	</a>
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
			<div id="loading" class="nav navbar-nav navbar-right" style="color: WHITE;height:40px;margin-top:0.5%;position:relative;">
				<i class="fa fa-spinner fa-5 fa-spin"></i> &nbsp;Espere por favor ...
			</div>
		</div>
	</div>
</nav>

<script>
		
    	$(document).ready(
    		function(){
    			
    			$("#loading").css({"display": "none"});
    			
    	        $(document).ajaxStart(function () {
    	        	$("#loading").css({"display": "block"});
    	        	
    	        	return;
    	        });

    	        $(document).ajaxStop(function () {
    	        	$("#loading").css({"display": "none"});
    	        	
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