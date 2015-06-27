<style>
	.table-hover tbody tr:hover > td {
	  cursor: pointer;
	}
</style>

<!-- Fixed navbar -->
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}/controller/index">SGPD - Sistema de Gesti&oacute;n de Pago Diario</a>
		</div>
		<form id="frmLogout" action="${pageContext.request.contextPath}/controller/html/logout">
				</form>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
	              <a id="drop1" href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">Acciones <span class="caret"></span></a>
	              <ul class="dropdown-menu" role="menu" aria-labelledby="drop1">
	                <li role="presentation">
	                	<a id="optEliminar" style="cursor:pointer;" data-toggle="modal" role="menuitem" tabindex="-1" data-target="#eliminarProyectoModal">Eliminar Proyecto</a>
	                </li>
	                <li role="presentation" class="divider"></li>
	                <li role="presentation">
	                	<a id="optXlsAll" style="cursor:pointer;" role="menuitem" tabindex="0" href="#")>Exportar a Excel</a>
	                
	                </li>
	                <!--  
	                <li role="presentation" class="divider"></li>
	                <li role="presentation"><a role="menuitem" tabindex="-1" href="http://twitter.com/fat">Separated link</a></li>
	                -->
	              </ul>
	            </li>
				<li><a href="#">Ayuda</a></li>
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
</div>

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