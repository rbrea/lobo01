<div class="modal fade" id="modalBillClient" tabindex="-1" role="dialog" aria-labelledby="modalBillClientLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modalBillClientLabel">Clientes</h4>
      </div>
      <div class="modal-body">
		<form id="frmBillClient" 
				method="POST" data-toggle="validator">
			<input type="hidden" id="billClientId" name="id" value="">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-6">
						<div class="panel panel-default">
						  	<div class="panel-body">
						    	<div class="form-group">
									<label for="clientName">Nombre y Apellido</label>
								    <input type="text" class="form-control" id="clientName" name="clientName" placeholder="Ingrese Nombre y Apellido..." required>
								    <div class="help-block with-errors"></div>
								</div>
								<div class="form-group">
									<label for="clientDocumentNumber">N&uacute;mero de Documento</label>
								    <input type="number" class="form-control" id="clientDocumentNumber" name="clientDocumentNumber" placeholder="Ingrese N&uacute;mero de Documento..." required>
								    <div class="help-block with-errors"></div>
								</div>
								<div class="form-group">
									<label for="clientEmail">e-mail</label>
								    <input type="email" class="form-control" id="clientEmail" name="clientEmail" placeholder="Ingrese e-mail...">
								    <div class="help-block with-errors"></div>
								</div>
								<div class="form-group">
									<label for="clientCompanyPhone">Tel&eacute;fono</label>
								    <input type="text" class="form-control" id="clientCompanyPhone" name="clientCompanyPhone" placeholder="Ingrese N&uacute;mero de tel&eacute;fono...">
								    <div class="help-block with-errors"></div>
								</div>
								<div class="form-group">
									<label for="clientCompanyAddress">Domicilio</label>
								    <input type="text" class="form-control" id="clientCompanyAddress" name="clientCompanyAddress" placeholder="Ingrese domicilio..." required>
								    <div class="help-block with-errors"></div>
								</div>
								<div class="form-group">
									<label for="clientNearStreets">Entre Calles</label>
								    <input type="text" class="form-control" id="clientNearStreets" name="clientNearStreets" placeholder="Ingrese entre calles...">
								    <div class="help-block with-errors"></div>
								</div>
						  	</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="panel panel-default">
							<div class="panel-body">
						    	<div class="form-group">
									<label for="clientCompanyCity">Localidad/Barrio</label>
								    <input type="text" class="form-control" id="clientCompanyCity" name="clientCompanyCity" placeholder="Ingrese localidad/barrio..." required>
								    <div class="help-block with-errors"></div>
								</div>
								<div class="form-group">
									<label for="clientCompanyType">Tipo de Comercio</label>
								    <input type="text" class="form-control" id="clientCompanyType" name="clientCompanyType" placeholder="Ingrese tipo de comercio...">
								    <div class="help-block with-errors"></div>
								</div>
								<div class="form-group">
									<label for="clientPhone">Tel&eacute;fono Particular</label>
								    <input type="text" class="form-control" id="clientPhone" name="clientPhone" placeholder="Ingrese N&uacute;mero de tel&eacute;fono...">
								    <div class="help-block with-errors"></div>
								</div>
								<div class="form-group">
									<label for="clientAddress">Domicilio Particular</label>
								    <input type="text" class="form-control" id="clientAddress" name="clientAddress" placeholder="Ingrese domicilio...">
								    <div class="help-block with-errors"></div>
								</div>
								<div class="form-group">
									<label for="clientCity">Localidad/Barrio Particular</label>
								    <input type="text" class="form-control" id="clientCity" name="clientCity" placeholder="Ingrese localidad/barrio...">
								    <div class="help-block with-errors"></div>
								</div>
						  	</div>
						</div>
					</div>
				</div>
			</div>
	    	
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        <button id="btnBillClient" type="button" class="btn btn-primary">Guardar</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="modalBillTrader" tabindex="-1" role="dialog" aria-labelledby="modalBillTraderLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modalBillTraderLabel">Vendedores/Supervisores</h4>
      </div>
      <div class="modal-body">
		<form id="frmTrader" 
				method="POST" data-toggle="validator">
			<input type="hidden" id="billTraderId" name="id" value="">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-6">
						<div class="panel panel-default">
						  	<div class="panel-body">
						    	<div class="form-group">
									<label for="traderName">Nombre y Apellido</label>
								    <input type="text" class="form-control" id="traderName" name="traderName" placeholder="Ingrese Nombre y Apellido..." required>
								    <div class="help-block with-errors"></div>
								</div>
								<div class="form-group">
									<label for="traderDocumentNumber">N&uacute;mero de Documento</label>
								    <input type="number" class="form-control" id="traderDocumentNumber" name="traderDocumentNumber" placeholder="Ingrese N&uacute;mero de Documento..." required>
								    <div class="help-block with-errors"></div>
								</div>
								<div class="form-group">
									<label for="traderEmail">e-mail</label>
								    <input type="email" class="form-control" id="traderEmail" name="traderEmail" placeholder="Ingrese e-mail...">
								    <div class="help-block with-errors"></div>
								</div>
								<div class="form-group">
									<label for="traderBillParentDescription">Supervisor</label>
									<div class="inner-addon right-addon">
										<input type="hidden" id="traderBillParentId" name="traderBillParentId" value="">
									    <input type="text" class="form-control lov" id="traderBillParentDescription" name="traderBillParentDescription" placeholder="Asignar supervisor...">
										<i class="glyphicon glyphicon-search"></i>
										<div class="help-block with-errors"></div>
									</div>
								</div>
								<div class="form-group">
									<div class="checkbox">
								    	<label>
								      		<input type="checkbox" name="traderSupervisor" id="traderSupervisor"> Es Supervisor?
								    	</label>
								  	</div>
								</div>
						  	</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="panel panel-default">
							<div class="panel-body">
								<div class="form-group">
									<label for="traderPhone">Tel&eacute;fono</label>
								    <input type="text" class="form-control" id="traderPhone" name="traderPhone" placeholder="Ingrese N&uacute;mero de tel&eacute;fono...">
								    <div class="help-block with-errors"></div>
								</div>
								<div class="form-group">
									<label for="traderAddress">Domicilio</label>
								    <input type="text" class="form-control" id="traderAddress" name="traderAddress" placeholder="Ingrese domicilio...">
								    <div class="help-block with-errors"></div>
								</div>
								<div class="form-group">
									<label for="traderCity">Localidad/Barrio</label>
								    <input type="text" class="form-control" id="traderCity" name="traderCity" placeholder="Ingrese localidad/barrio...">
								    <div class="help-block with-errors"></div>
								</div>
						  	</div>
						</div>
					</div>
				</div>
			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        <button id="btnTrader" type="button" class="btn btn-primary">Guardar</button>
      </div>
    </div>
  </div>
</div>

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
		<div class="col-md-12">
			<div id="pnlBill" class="panel panel-default">
		  		<div class="panel-heading">
		    		<h3 class="panel-title">Datos de Factura</h3>
		  		</div>
		  		<div class="panel-body">
		  			<div class="row">
		  				<form id="frmBillFirst">
		  				<div class="col-md-3">
		  					<div class="form-group">
		  						<label for="billDateValue">Fecha de Factura</label>
				                <div class='input-group date' id='billDate'>
				                    <input type='text' class="form-control input-sm not-writable" id="billDateValue" name="billDateValue" placeholder="Ingrese fecha..." required/>
				                    <span class="input-group-addon">
				                        <span class="glyphicon glyphicon-calendar"></span>
				                    </span>
				                </div>
				            </div>
		  				</div>
		  				<div class="col-md-4">
		  					<div class="form-group">
		  						<label for="billNumber">N&uacute;mero de Cr&eacute;dito</label>
				                <input type="number" class="form-control input-sm" id="billNumber" name="billNumber" placeholder="Ingrese n&uacute;mero de factura..." min="1" required>
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-2">
							<div class="form-group">
		  						<label for="bcobrador">Cobrador / Zona</label>
				                <input type="number" class="form-control input-sm" id="bcobrador" name="bcobrador" placeholder="Cobrador" min="1" required>
								<div class="help-block with-errors"></div>
				            </div>		  					
		  				</div>
		  				<div class="col-md-2">
							&nbsp;		  					
		  				</div>
		  				<div class="col-md-1">
							<div class="form-group">
								&nbsp;
				            </div>		  					
		  				</div>
					</form>
		  			</div>
		  			<div class="row">
		  				<div class="col-md-6">
		  					&nbsp;
		  				</div>
		  				<div class="col-md-6">
		  					&nbsp;
		  				</div>
		  			</div>
		  		</div>
			</div>	
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div id="pnlClient" class="panel panel-default">
		  		<div class="panel-heading">
		    		<h3 class="panel-title">Datos de Cliente</h3>
		  		</div>
		  		<div class="panel-body">
		  			<form id="frmBillSecond">
		  			<div class="row">
		  				<div class="col-md-2">
		  					<input type="hidden" id="billClientIdSelected" name="billClientIdSelected">
		  					<div class="form-group">
		  						<label for="bname">C&oacute;digo</label>
				                <input type="text" class="form-control input-sm" id="bClientId" name="bClientId" placeholder="C&oacute;digo" required>
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-8">
		  					<div class="form-group">
		  						<label for="baddress">Nombre / Domicilio / Comercio</label>
		  						<div class="input-group">
					                <input type="text" class="form-control input-sm not-writable" id="baddress" name="baddress" placeholder="Nombre / Domicilio / Comercio" required>
									<span id="btnSearchClient" class="input-group-addon"><i class="glyphicon glyphicon-search lov"></i></span>		  						
		  						</div>
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-1">
		  					<div class="form-group centered">
		  						<label for="bcompanytype">Nuevo</label><br>
		  						<button id="btnBillNewClient" type="button" 
		  							class="btn btn-success btn-sm"><i class="glyphicon glyphicon-plus-sign"></i></button>&nbsp;
				            </div>
		  				</div>
		  			</div>
		  			<!-- 
		  			<div class="row" >
		  				<div class="col-md-7">
		  					&nbsp;
		  				</div>
		  				<div class="col-md-5">
		  					<button id="btnSecondNext" type="button" data-loading-text="Espere..." class="btn btn-primary btn-sm"><i class="glyphicon glyphicon-ok"></i>&nbsp;Siguiente</button>&nbsp;
		  					<button id="btnSecondCancel" type="button" data-loading-text="Espere..." class="btn btn-default btn-sm"><i class="glyphicon glyphicon-remove"></i>&nbsp;Cancelar</button>
		  				</div>
		  			</div>
		  			 -->
		  			</form>
		  		</div>
			</div>	
		</div>
		<div class="col-md-6">
			<div id="pnlTrader" class="panel panel-default">
		  		<div class="panel-heading">
		    		<h3 class="panel-title">Datos de Vendedor</h3>
		  		</div>
		  		<div class="panel-body">
		  			<form id="frmBillThird">
		  			<div class="row">
		  				<div class="col-md-2">
		  					<div class="form-group">
		  						<label for="btraderid">C&oacute;digo</label>
				                <input type="text" class="form-control input-sm" id="btraderid" name="btraderid" placeholder="C&oacute;digo" required>
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-10">
		  					<div class="form-group">
		  						<label for="btradername">Nombre / DNI</label>
		  						<div class="input-group">
				                	<input type="text" class="form-control input-sm not-writable" id="btradername" 
				                			name="btradername" placeholder="Nombre / DNI" required>
				                	<span id="btnSearchTrader" class="input-group-addon lov"><i class="glyphicon glyphicon-search"></i></span>
								</div>
								<div class="help-block with-errors"></div>
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
		  			<!-- 
		    		<div class="row" >
		  				<div class="col-md-7">
		  					&nbsp;
		  				</div>
		  				<div class="col-md-5">
		  					<button id="btnThirdNext" type="button" data-loading-text="Espere..." class="btn btn-primary btn-sm"><i class="glyphicon glyphicon-ok"></i>&nbsp;Siguiente</button>&nbsp;
		  					<button id="btnThirdCancel" type="button" data-loading-text="Espere..." class="btn btn-default btn-sm"><i class="glyphicon glyphicon-remove"></i>&nbsp;Cancelar</button>
		  				</div>
		  			</div>
		  			 -->
		  			</form>
		  		</div>
			</div>	
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div id="pnlProduct" class="panel panel-default">
		  		<div class="panel-heading">
		    		<h3 class="panel-title">Productos</h3>
		  		</div>
		  		<div class="panel-body">
		  			<form id="frmBillFour">
		  			<div class="row">
		  				<div class="col-md-9">
		  					&nbsp;
		  				</div>
		  				<div class="col-md-3">
		  					<button id="btnBillAddProduct" type="button" 
		  							class="btn btn-info btn-sm"><i class="glyphicon glyphicon-plus-sign"></i>&nbsp;Agregar</button>&nbsp;
	  						<button id="btnBillRemoveProduct" type="button"
	  							class="btn btn-danger btn-sm"><i class="glyphicon glyphicon-minus-sign"></i>&nbsp;Quitar</button>
		  				</div>
	  				</div>
	  				<div class="row" >
		  				<div class="col-md-12">
		  					&nbsp;
		  				</div>
	  				</div>
	  				<div id="baseProductRow" class="row hide">
		  				<div class="col-md-1">
		  					<div class="form-group centered">
				                <input type="number" class="form-control input-sm" id="bcant_X" name="bcant_X" placeholder="Cant" min="1">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<input type="hidden" id="billProductId_X" name="billProductId_X">
		  				<input type="hidden" id="billProductPrice_X" name="billProductPrice_X">
		  				<div class="col-md-1">
		  					<div class="form-group centered">
					            <input type="text" class="form-control input-sm" id="bProductCode_X" name="bProductCode_X" placeholder="C&oacute;digo" required>
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-5">
		  					<div class="form-group centered">
		  						<div class="input-group">
					                <input type="text" class="form-control input-sm not-writable" id="bname_X" name="bname_X" placeholder="Art&iacute;culo">
									<span id="btnSearchProduct_X" class="input-group-addon lov" onclick="javascript:Bill.showLovProduct('_X');"><i class="glyphicon glyphicon-search"></i></span>		  						
		  						</div>
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-2">
							<div class="form-group centered">
				                <input type="text" class="form-control input-sm" id="bcuotadiaria_X" name="bcuotadiaria_X" placeholder="$ c/cta.">
								<div class="help-block with-errors"></div>
				            </div>		  					
		  				</div>
		  				<div class="col-md-2">
		  					<div class="form-group centered">
				                <input type="text" class="form-control input-sm" id="bimp_X" name="bimp_X" placeholder="$ Importe">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-1">
		  					<div class="form-group centered">
		  						&nbsp;
				            </div>
		  				</div>
		  			</div>
		  			<div id="product_0" class="row">
		  				<div class="col-md-1">
		  					<div class="form-group centered">
		  						<label for="bcant_0">Cantidad</label>
				                <input type="number" class="form-control input-sm" id="bcant_0" name="bcant_0" placeholder="Cant" min="1" required>
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<input type="hidden" id="billProductId_0" name="billProductId_0">
		  				<input type="hidden" id="billProductPrice_0" name="billProductPrice_0">
		  				<div class="col-md-1">
		  					<div class="form-group centered">
		  						<label for="bProductCode_0">C&oacute;digo</label>
					            <input type="text" class="form-control input-sm" id="bProductCode_0" name="bProductCode_0" placeholder="C&oacute;digo" required>
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-5">
		  					<div class="form-group centered">
		  						<label for="bname_0">Articulo/s</label>
		  						<div class="input-group">
					                <input type="text" class="form-control input-sm not-writable" id="bname_0" name="bname_0" placeholder="Art&iacute;culo" required>
									<span id="btnSearchProduct_0" class="input-group-addon lov" onclick="javascript:Bill.showLovProduct('_0');"><i class="glyphicon glyphicon-search"></i></span>		  						
		  						</div>
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-2">
							<div class="form-group centered">
		  						<label for="bcuotadiaria_0">$ c/cta.</label>
				                <input type="number" class="form-control input-sm" id="bcuotadiaria_0" name="bcuotadiaria_0" placeholder="$ c/cta." required>
								<div class="help-block with-errors"></div>
				            </div>		  					
		  				</div>
		  				<div class="col-md-2">
		  					<div class="form-group centered">
		  						<label for="bimp_0">$ Importe</label>
				                <input type="number" class="form-control input-sm" id="bimp_0" name="bimp_0" placeholder="$ Importe" required>
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-1">
		  					<div class="form-group centered">
		  						&nbsp;
				            </div>
		  				</div>
		  			</div>
		  			<div id="product_1" class="row">
		  				<div class="col-md-1">
		  					<div class="form-group centered">
				                <input type="number" class="form-control input-sm" id="bcant_1" name="bcant_1" placeholder="Cant" min="1">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<input type="hidden" id="billProductId_1" name="billProductId_1">
		  				<input type="hidden" id="billProductPrice_1" name="billProductPrice_1">
		  				<div class="col-md-1">
		  					<div class="form-group centered">
					            <input type="text" class="form-control input-sm" id="bProductCode_1" name="bProductCode_1" placeholder="C&oacute;digo" required>
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-5">
		  					<div class="form-group centered">
			  					<div class="input-group">
					                <input type="text" class="form-control input-sm not-writable" id="bname_1" name="bname_1"  placeholder="Art&iacute;culo">
									<span id="btnSearchProduct_1" class="input-group-addon lov" onclick="javascript:Bill.showLovProduct('_1');"><i class="glyphicon glyphicon-search"></i></span>		  					
			  					</div>
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-2">
							<div class="form-group centered">
				                <input type="number" class="form-control input-sm" id="bcuotadiaria_1" name="bcuotadiaria_1" placeholder="$ c/cta.">
								<div class="help-block with-errors"></div>
				            </div>		  					
		  				</div>
		  				<div class="col-md-2">
		  					<div class="form-group centered">
				                <input type="number" class="form-control input-sm" id="bimp_1" name="bimp_1" placeholder="$ Importe">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-1">
		  					&nbsp;
		  				</div>
		  			</div>
		  			<div id="totalProductRow" class="row">
		  				<div class="col-md-1">
		  					&nbsp;
		  				</div>
		  				<div class="col-md-6">
		  					<div class="form-group pull-right">
								<strong>Total $</strong>
				            </div>
		  				</div>
		  				<div class="col-md-2">
							<div class="form-group centered">
				                <input type="number" class="form-control input-sm not-writable" id="bcuotaTotal" name="bcuotaTotal" placeholder="$ c/cta." required>
								<div class="help-block with-errors"></div>
				            </div>		  					
		  				</div>
		  				<div class="col-md-2">
		  					<div class="form-group centered">
				                <input type="number" class="form-control input-sm not-writable" id="bimpTotal" name="bimpTotal" placeholder="$ Importe Total" required>
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-1">
		  					&nbsp;
		  				</div>
		  			</div>
		  			<!-- 
		  			<div class="row" >
		  				<div class="col-md-9">
		  					&nbsp;
		  				</div>
		  				<div class="col-md-3">
		  					<button id="btnFourNext" type="button" data-loading-text="Espere..." class="btn btn-primary btn-sm"><i class="glyphicon glyphicon-ok"></i>&nbsp;Siguiente</button>&nbsp;
		  					<button id="btnFourCancel" type="button" data-loading-text="Espere..." class="btn btn-default btn-sm"><i class="glyphicon glyphicon-remove"></i>&nbsp;Cancelar</button>
		  				</div>
	  				</div>
	  				 -->
	  				</form>
		  		</div>
			</div>	
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div id="pnlFinalize" class="panel panel-default">
		  		<div class="panel-body">
		  			<div class="row" >
		  				<div class="col-md-2">
		  					<!--  
							<div class="form-group">
		  						<label for="bnroticket">N&uacute;mero de ticket</label>
				                <input type="number" class="form-control input-sm not-writable" id="bnroticket" name="bnroticket" placeholder="Nro.Ticket">
								<div class="help-block with-errors"></div>
				            </div>
				            -->
				            &nbsp;		  					
		  				</div>
		  				<div class="col-md-2">
		  					<!--
							<div class="form-group">
		  						<label for="bcobrador">Cobrador</label>
				                <input type="number" class="form-control input-sm" id="bcobrador" name="bcobrador" placeholder="Cobrador">
								<div class="help-block with-errors"></div>
				            </div>
				            -->
				            &nbsp;	  					
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
			
			Bill.init();
			
			return;
		}	
		
	);
</script>