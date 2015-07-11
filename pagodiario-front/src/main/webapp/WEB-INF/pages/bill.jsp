<div class="col-md-1">
	&nbsp;
</div>
<div class="col-md-10">
	<div id="facturaMessages" class="row hide">
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	    <div class="col-md-8">
	        <div id="facturaAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#facturaAlertMessages').children('span').eq(0).html('');$('#facturaMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div class="panel panel-success">
		  		<div class="panel-heading">
		    		<h3 class="panel-title">Datos de Factura</h3>
		  		</div>
		  		<div class="panel-body">
		  			<div class="row">
		  				<div class="col-md-6">
		  					<div class="form-group">
		  						<label for="billNumber">N&uacute;mero de Factura</label>
				                <input type="text" class="form-control input-sm" id="billNumber" name="billNumber" placeholder="Ingrese n&uacute;mero de factura...">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-6">
		  					<div class="form-group">
		  						<label for="billDate">Fecha de Factura</label>
				                <div class='input-group date' id='billDate'>
				                    <input type='text' class="form-control input-sm not-writable" id="billDate" name="billDate" placeholder="Ingrese fecha..."/>
				                    <span class="input-group-addon">
				                        <span class="glyphicon glyphicon-calendar"></span>
				                    </span>
				                </div>
				            </div>
		  				</div>
		  			</div>
		  			<div class="row">
		  				<div class="col-md-6">
		  					&nbsp;
		  				</div>
		  				<div class="col-md-6">
		  					&nbsp;
		  				</div>
		  			</div>
		    		<div class="row" >
		  				<div class="col-md-7">
		  					&nbsp;
		  				</div>
		  				<div class="col-md-5">
		  					<button id="btnFirstNext" type="button" data-loading-text="Espere..." class="btn btn-primary btn-sm"><i class="glyphicon glyphicon-ok"></i>&nbsp;Siguiente</button>&nbsp;
		  					<button id="btnFirstCancel" type="button" data-loading-text="Espere..." class="btn btn-default btn-sm"><i class="glyphicon glyphicon-remove"></i>&nbsp;Cancelar</button>
		  				</div>
		  			</div>
		  		</div>
			</div>	
		</div>
		<div class="col-md-6">
			<div class="panel panel-info">
		  		<div class="panel-heading">
		    		<h3 class="panel-title">Datos de Vendedor</h3>
		  		</div>
		  		<div class="panel-body">
		  			<div class="row">
		  				<div class="col-md-2">
		  					<div class="form-group">
		  						<label for="btraderid">C&oacute;digo</label>
				                <input type="text" class="form-control input-sm not-writable" id="btraderid" name="btraderid" placeholder="C&oacute;digo">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-8">
		  					<div class="form-group">
		  						<label for="btradername">Nombre y Apellido</label>
				                <input type="text" class="form-control input-sm not-writable" id="btradername" name="btradername" placeholder="Nombre y Apellido">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-2">
		  					<div class="form-group centered">
		  						<label>Buscar</label><br>
		  						<button id="btnBillSearchTrader" type="button" 
		  							class="btn btn-default btn-sm"><i class="glyphicon glyphicon-search"></i></button>&nbsp;
				            </div>
		  				</div>
		  			</div>
		  			<div class="row">
		  				<div class="col-md-6">
		  					&nbsp;
		  				</div>
		  				<div class="col-md-6">
		  					&nbsp;
		  				</div>
		  			</div>
		    		<div class="row" >
		  				<div class="col-md-7">
		  					&nbsp;
		  				</div>
		  				<div class="col-md-5">
		  					<button id="btnSecondNext" type="button" data-loading-text="Espere..." class="btn btn-primary btn-sm"><i class="glyphicon glyphicon-ok"></i>&nbsp;Siguiente</button>&nbsp;
		  					<button id="btnSecondCancel" type="button" data-loading-text="Espere..." class="btn btn-default btn-sm"><i class="glyphicon glyphicon-remove"></i>&nbsp;Cancelar</button>
		  				</div>
		  			</div>
		  		</div>
			</div>	
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
		  		<div class="panel-heading">
		    		<h3 class="panel-title">Datos de Cliente</h3>
		  		</div>
		  		<div class="panel-body">
		  			<div class="row">
		  				<div class="col-md-3">
		  					<div class="form-group">
		  						<label for="bname">Nombre y Apellido</label>
				                <input type="text" class="form-control input-sm not-writable" id="bname" name="bname" placeholder="Nombre y Apellido">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-3">
		  					<div class="form-group">
		  						<label for="baddress">Domicilio</label>
				                <input type="text" class="form-control input-sm not-writable" id="baddress" name="baddress" placeholder="Domicilio">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-2">
							<div class="form-group">
		  						<label for="bphone">Tel&eacute;fono</label>
				                <input type="text" class="form-control input-sm not-writable" id="bphone" name="bphone" placeholder="Tel&eacute;fono">
								<div class="help-block with-errors"></div>
				            </div>		  					
		  				</div>
		  				<div class="col-md-3">
		  					<div class="form-group">
		  						<label for="bcompanytype">Comercio</label>
				                <input type="text" class="form-control input-sm not-writable" id="bcompanytype" name="bcompanytype" placeholder="Comercio">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-1">
		  					<div class="form-group centered">
		  						<label for="bcompanytype">Buscar</label><br>
		  						<button id="btnBillSearchClient" type="button" 
		  							class="btn btn-default btn-sm"><i class="glyphicon glyphicon-search"></i></button>&nbsp;
				            </div>
		  				</div>
		  			</div>
		  			<!--  
		    		<div class="row">
		  				<div class="col-md-3">
		  					<div class="form-group">
		  						<label for="bcity">Localidad/Barrio</label>
				                <input type="text" class="form-control input-sm" id="bcity" name="bcity">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-3">
				            <div class="form-group">
		  						<label for="bnearstreets">Entre las calles</label>
				                <input type="text" class="form-control input-sm" id="bnearstreets" name="bnearstreets">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-3">
				            <div class="form-group">
		  						<label for="bparticularaddress">Domicilio Particular</label>
				                <input type="text" class="form-control input-sm" id="bparticularaddress" name="bparticularaddress">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-3">
							<div class="form-group">
		  						<label for="bparticularcity">Localidad/Barrio</label>
				                <input type="text" class="form-control input-sm" id="bparticularcity" name="bparticularcity">
								<div class="help-block with-errors"></div>
				            </div>		  					
		  				</div>
		  			</div>
		  			<div class="row">
		  				<div class="col-md-3">
		  					<div class="form-group">
		  						<label for="bparticularphone">Tel&eacute;fono</label>
				                <input type="text" class="form-control input-sm" id="bparticularphone" name="bparticularphone">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-9">
		  					&nbsp;
		  				</div>
		  			</div>
		  			-->
		  			<div class="row" >
		  				<div class="col-md-9">
		  					&nbsp;
		  				</div>
		  				<div class="col-md-3">
		  					<button id="btnThirdNext" type="button" data-loading-text="Espere..." class="btn btn-primary btn-sm"><i class="glyphicon glyphicon-ok"></i>&nbsp;Siguiente</button>&nbsp;
		  					<button id="btnThirdCancel" type="button" data-loading-text="Espere..." class="btn btn-default btn-sm"><i class="glyphicon glyphicon-remove"></i>&nbsp;Cancelar</button>
		  				</div>
		  			</div>
		  		</div>
			</div>	
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-danger">
		  		<div class="panel-heading">
		    		<h3 class="panel-title">Productos</h3>
		  		</div>
		  		<div class="panel-body">
		  			<div class="row">
		  				<div class="col-md-1">
		  					<div class="form-group centered">
		  						<label for="bcant_0">Cantidad</label>
				                <input type="number" class="form-control input-sm" id="bcant_0" name="bcant_0" placeholder="Cant">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-6">
		  					<div class="form-group centered">
		  						<label for="bname_0">Articulo/s</label>
				                <input type="text" class="form-control input-sm not-writable" id="bname_0" name="bname_0" placeholder="Art&iacute;culo">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-1">
							<div class="form-group centered">
		  						<label for="bpu_0">$ c/cta.</label>
				                <input type="text" class="form-control input-sm not-writable" id="bpu_0" name="bpu_0" placeholder="$ c/cta.">
								<div class="help-block with-errors"></div>
				            </div>		  					
		  				</div>
		  				<div class="col-md-2">
		  					<div class="form-group centered">
		  						<label for="bimp_0">$ Importe</label>
				                <input type="text" class="form-control input-sm not-writable" id="bimp_0" name="bimp_0" placeholder="$ Importe">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-2">
		  					<div class="form-group centered">
		  						<label>Acciones</label><br>
		  						<button id="btnBillSearchProduct_0" type="button" 
		  							class="btn btn-default btn-sm"><i class="glyphicon glyphicon-search"></i></button>&nbsp;
		  						<button id="btnBillAddProduct_0" type="button" 
		  							class="btn btn-primary btn-sm"><i class="glyphicon glyphicon-plus-sign"></i></button>&nbsp;
		  						<button id="btnBillRemoveProduct_0" type="button" 
		  							class="btn btn-danger btn-sm"><i class="glyphicon glyphicon-minus-sign"></i></button>
				            </div>
		  				</div>
		  			</div>
		  			<div class="row">
		  				<div class="col-md-1">
		  					<div class="form-group centered">
				                <input type="number" class="form-control input-sm" id="bcant_1" name="bcant_1" placeholder="Cant">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-6">
		  					<div class="form-group centered">
				                <input type="text" class="form-control input-sm not-writable" id="bname_1" name="bname_1"  placeholder="Art&iacute;culo">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-1">
							<div class="form-group centered">
				                <input type="text" class="form-control input-sm not-writable" id="bpu_1" name="bpu_1" placeholder="$ c/cta.">
								<div class="help-block with-errors"></div>
				            </div>		  					
		  				</div>
		  				<div class="col-md-2">
		  					<div class="form-group centered">
				                <input type="text" class="form-control input-sm not-writable" id="bimp_1" name="bimp_1" placeholder="$ Importe">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-2">
		  					<div class="form-group centered">
		  						<button id="btnBillSearchProduct_1" type="button" 
		  							class="btn btn-default btn-sm"><i class="glyphicon glyphicon-search"></i></button>&nbsp;
		  						<button id="btnBillAddProduct_1" type="button" 
		  							class="btn btn-primary btn-sm"><i class="glyphicon glyphicon-plus-sign"></i></button>&nbsp;
		  						<button id="btnBillRemoveProduct_1" type="button" 
		  							class="btn btn-danger btn-sm"><i class="glyphicon glyphicon-minus-sign"></i></button>
				            </div>
		  				</div>
		  			</div>
		  			<div class="row">
		  				<div class="col-md-1">
		  					&nbsp;
		  				</div>
		  				<div class="col-md-6">
		  					&nbsp;
		  				</div>
		  				<div class="col-md-1">
							<div class="form-group pull-right">
								<strong>Total $</strong>
				            </div>		  					
		  				</div>
		  				<div class="col-md-2">
		  					<div class="form-group centered">
				                <input type="text" class="form-control input-sm not-writable" id="bimpTotal" name="bimpTotal" placeholder="$ Importe Total">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-2">
		  					&nbsp;
		  				</div>
		  			</div>
		  			<div class="row" >
		  				<div class="col-md-9">
		  					&nbsp;
		  				</div>
		  				<div class="col-md-3">
		  					<button id="btnFourNext" type="button" data-loading-text="Espere..." class="btn btn-primary btn-sm"><i class="glyphicon glyphicon-ok"></i>&nbsp;Siguiente</button>&nbsp;
		  					<button id="btnFourCancel" type="button" data-loading-text="Espere..." class="btn btn-default btn-sm"><i class="glyphicon glyphicon-remove"></i>&nbsp;Cancelar</button>
		  				</div>
	  				</div>
		  		</div>
			</div>	
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-default">
		  		<div class="panel-body">
		  			<div class="row" >
		  				<div class="col-md-2">
							<div class="form-group">
		  						<label for="bnroticket">N&uacute;mero de ticket</label>
				                <input type="number" class="form-control input-sm not-writable" id="bnroticket" name="bnroticket" placeholder="Nro.Ticket">
								<div class="help-block with-errors"></div>
				            </div>		  					
		  				</div>
		  				<div class="col-md-2">
							<div class="form-group">
		  						<label for="bcobrador">Cobrador</label>
				                <input type="number" class="form-control input-sm" id="bcobrador" name="bcobrador" placeholder="Cobrador">
								<div class="help-block with-errors"></div>
				            </div>		  					
		  				</div>
		  				<div class="col-md-5">
		  					&nbsp;
		  				</div>
		  				<div class="col-md-3">
		  					<div class="form-group">
		  						<label for="bcobrador">&nbsp;</label><br>
		  						<button id="btnFinalize" type="button" data-loading-text="Espere..." class="btn btn-success"><i class="glyphicon glyphicon-floppy-save"></i>&nbsp;Finalizar</button>&nbsp;
		  						<button id="btnCancel" type="button" data-loading-text="Espere..." class="btn btn-danger"><i class="glyphicon glyphicon-off"></i>&nbsp;Borrar</button>
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
	$(document).ready(
		function(){
			
			var imgCheckUrl = "${pageContext.request.contextPath}/public/images/checkmark-outline_32x32.png";
			
			$('.not-writable').keypress(function(e) {
		         e.preventDefault();
		         
		         return;
			});
			
			Factura.init();
			
			return;
		}	
		
	);
</script>