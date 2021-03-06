<div id="modal-bill-client-container" class="container-fluid" style="display:none;">
	<div id="modalClientMessages" class="row hide">
	    <div class="col-md-12">
	        <div id="modalClientAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#modalClientAlertMessages').children('span').eq(0).html('');$('#modalClientMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	</div>
	<div class="row">
  		<form id="frmBillAddClient">
		<div class="col-md-4">
			<div class="panel panel-default">
			  	<div class="panel-body">
			    	<div class="form-group">
						<label for="billClientName">Nombre y Apellido</label>
						<div class='input-group'>
							<span class="input-group-addon">
		                        <span class="fa fa-pencil-square-o"></span>
		                    </span>
					    	<input type="text" class="form-control" id="billClientName" name="name" placeholder="Ingrese Nombre y Apellido..." required><!-- &nbsp;<span style="color:RED;font-weight:bold;">*</span> -->
					    </div>
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="billClientDocumentNumber">N&uacute;mero de Documento</label>
						<div class='input-group'>
							<span class="input-group-addon">
		                        <span class="fa fa-user"></span>
		                    </span>
					    	<input type="number" class="form-control" id="billClientDocumentNumber" name="documentNumber" placeholder="Ingrese N&uacute;mero de Documento...">
					    </div>
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="billClientEmail">e-mail</label>
						<div class='input-group'>
							<span class="input-group-addon">
		                        <span class="fa fa-envelope-o"></span>
		                    </span>
					    	<input type="email" class="form-control" id="billClientEmail" name="email" placeholder="Ingrese e-mail...">
					    </div>
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="billClientCompanyPhone">Tel&eacute;fono</label>
						<div class='input-group'>
							<span class="input-group-addon">
		                        <span class="fa fa-fax"></span>
		                    </span>
					    	<input type="text" class="form-control" id="billClientCompanyPhone" name="companyPhone" placeholder="Ingrese N&uacute;mero de tel&eacute;fono...">
					    </div>
					    <div class="help-block with-errors"></div>
					</div>
			  	</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="panel panel-default">
			  	<div class="panel-body">
					<div class="form-group">
						<label for="billClientCompanyAddress">Domicilio</label>
						<div class='input-group'>
							<span class="input-group-addon">
		                        <span class="fa fa-industry"></span>
		                    </span>
					    	<input type="text" class="form-control" id="billClientCompanyAddress" name="companyAddress" placeholder="Ingrese domicilio..." required>
					    </div>
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="billClientNearStreets">Entre Calles</label>
						<div class='input-group'>
							<span class="input-group-addon">
		                        <span class="fa fa-industry"></span>
		                    </span>
					    	<input type="text" class="form-control" id="billClientNearStreets" name="nearStreets" placeholder="Ingrese entre calles...">
					    </div>
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="billClientCompanyCity">Localidad/Barrio</label>
						<div class='input-group'>
							<span class="input-group-addon">
		                        <span class="fa fa-industry"></span>
		                    </span>
					    	<input type="text" class="form-control" id="billClientCompanyCity" name="companyCity" placeholder="Ingrese localidad/barrio..." required>
					    </div>
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="billClientCompanyType">Tipo de Comercio</label>
						<div class='input-group'>
							<span class="input-group-addon">
		                        <span class="fa fa-industry"></span>
		                    </span>
					    	<input type="text" class="form-control" id="billClientCompanyType" name="companyType" placeholder="Ingrese tipo de comercio...">
					    </div>
					    <div class="help-block with-errors"></div>
					</div>
			  	</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="form-group">
						<label for="billClientPhone">Tel&eacute;fono Particular</label>
						<div class='input-group'>
							<span class="input-group-addon">
		                        <span class="fa fa-phone"></span>
		                    </span>
					    	<input type="text" class="form-control" id="billClientPhone" name="phone" placeholder="Ingrese N&uacute;mero de tel&eacute;fono...">
					    </div>
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="billClientAddress">Domicilio Particular</label>
						<div class='input-group'>
							<span class="input-group-addon">
		                        <span class="fa fa-home"></span>
		                    </span>
					    	<input type="text" class="form-control" id="billClientAddress" name="address" placeholder="Ingrese domicilio...">
					    </div>
					    <div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label for="billClientCity">Localidad/Barrio Particular</label>
						<div class='input-group'>
							<span class="input-group-addon">
		                        <span class="fa fa-home"></span>
		                    </span>
					    	<input type="text" class="form-control" id="billClientCity" name="city" placeholder="Ingrese localidad/barrio...">
					    </div>
					    <div class="help-block with-errors"></div>
					</div>
			  	</div>
			</div>
		</div>
		</form>
	</div>
</div>

	<div id="facturaMessages" class="row hide" style="position:fixed;z-index:9000;width:80%;left:20%;">
		<div class="row">
		    <div class="col-md-12">
		        <div id="facturaAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
		          <button type="button" class="close" 
		                    onclick="javascript:$('#facturaAlertMessages').children('span').eq(0).html('');$('#facturaMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
		              <span></span>
		        </div>
		    </div>
		</div>
	</div>
<div class="col-md-1">
	&nbsp;
</div>
<div class="col-md-10">
	<form id="frmBillAdd" role="form">
	<div class="row">
		<div class="col-md-6">
			<div id="pnlBill" class="panel panel-default">
		  		<div class="panel-heading">
		    		<h3 class="panel-title"><i class="glyphicon glyphicon-play"></i> Datos de Factura</h3>
		  		</div>
		  		<div class="panel-body">
		  			<div class="row">
		  				<div class="col-md-6">
		  					<div class="form-group">
		  						<label for="billDateValue">Fecha de Factura</label>
				                <div class='input-group date' id='billDate'>
				                    <input type='text' class="form-control" id="billDateValue" name="billDateValue" placeholder="Ingrese fecha" data-error="Requerido" required/>
				                    <span class="input-group-addon">
				                        <span class="glyphicon glyphicon-calendar"></span>
				                    </span>
				                </div>
				            </div>
		  				</div>
		  				<div class="col-md-6">
		  					<div class="form-group">
		  						<label for="billNumber">N&uacute;mero de Cr&eacute;dito</label>
				                <input type="number" class="form-control " id="billNumber" name="billNumber" placeholder="Ingrese n&uacute;mero de factura" min="1" data-error="Requerido" required>
								<div id="billCreditNumberErrorMessageDiv" class="help-block with-errors"></div>
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
		  		</div>
			</div>	
		</div>
		<div class="col-md-6">
			<div id="pnlCollector" class="panel panel-default">
		  		<div class="panel-heading">
		    		<h3 class="panel-title"><i class="glyphicon glyphicon-play"></i> Datos de Cobrador</h3>
		  		</div>
		  		<div class="panel-body">
		  			<div class="row">
		  				<input type="hidden" id="bcobrador" name="bcobrador">
		  				<div class="col-md-2">
							<div class="form-group">
		  						<label for="bCollectorId">Zona</label>
				                <input type="number" class="form-control " id="bCollectorId" name="bCollectorId" placeholder="Zona" min="1" data-error="Requerido" required>
								<div class="help-block with-errors"></div>
				            </div>		  					
		  				</div>
		  				<div class="col-md-10">
							<div class="form-group">
		  						<label for="bCollectorDescription">Descripci&oacute;n</label>
		  						<div class="input-group">
				                	<input type="text" class="form-control not-writable" id="bCollectorDescription" 
				                			name="bCollectorDescription" placeholder="Zona / Descripci&oacute;n" data-error="Requerido" required>
				                	<span id="btnSearchCollector" class="input-group-addon lov"><i class="glyphicon glyphicon-search"></i></span>
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
		  		</div>
			</div>	
		</div>
	</div>
	<div class="row">
		<div class="col-md-6">
			<div id="pnlClient" class="panel panel-default">
		  		<div class="panel-heading">
		    		<h3 class="panel-title"><i class="glyphicon glyphicon-play"></i> Datos de Cliente</h3>
		  		</div>
		  		<div class="panel-body">
		  			<div class="row">
		  				<div class="col-md-10">
		  					<div id="bill-client-form-group" class="form-group">
		  						<label for="baddress">Cliente</label>
		  						<div class="input-group">
		  							<input type="hidden" id="billClientIdSelected" name="billClientIdSelected" required>
					                <input type="text" class="form-control" id="baddress" name="baddress" placeholder="Ingrese nombre de cliente" required>
									<span id="btnSearchClient" class="input-group-addon"><i class="glyphicon glyphicon-search lov"></i></span>		  						
		  						</div>
								<div id="billClientErrorMessageDiv" class="help-block with-errors"></div>
				            </div>
		  				</div>
						<!--  
		  				<div class="col-md-2">
		  					<input type="hidden" id="billClientIdSelected" name="billClientIdSelected">
		  					<div class="form-group">
		  						<label for="bname">C&oacute;digo</label>
				                <input type="text" class="form-control " id="bClientId" name="bClientId" placeholder="C&oacute;digo" data-error="Requerido" required>
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-8">
		  					<div class="form-group">
		  						<label for="baddress">Nombre / Domicilio / Comercio</label>
		  						<div class="input-group">
					                <input type="text" class="form-control  not-writable" id="baddress" name="baddress" placeholder="Nombre / Domicilio / Comercio" data-error="Requerido" required>
									<span id="btnSearchClient" class="input-group-addon"><i class="glyphicon glyphicon-search lov"></i></span>		  						
		  						</div>
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				-->
		  				<div class="col-md-1">
		  					<div class="form-group centered">
		  						<label for="bcompanytype">Nuevo</label><br>
		  						<button id="btnBillNewClient" type="button" 
		  							class="btn btn-success btn-sm"><i class="glyphicon glyphicon-plus-sign"></i></button>&nbsp;
				            </div>
		  				</div>
		  			</div>
		  		</div>
			</div>	
		</div>
		<div class="col-md-6">
			<div id="pnlTrader" class="panel panel-default">
		  		<div class="panel-heading">
		    		<h3 class="panel-title"><i class="glyphicon glyphicon-play"></i> Datos de Vendedor</h3>
		  		</div>
		  		<div class="panel-body">
		  			<div class="row">
		  				<div class="col-md-2">
		  					<div class="form-group">
		  						<label for="btraderid">C&oacute;digo</label>
				                <input type="number" class="form-control " id="btraderid" name="btraderid" placeholder="C&oacute;digo" data-error="Requerido" required>
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-10">
		  					<div class="form-group">
		  						<label for="btradername">Nombre / DNI</label>
		  						<div class="input-group">
				                	<input type="text" class="form-control  not-writable" id="btradername" 
				                			name="btradername" placeholder="Nombre / DNI" data-error="Requerido" required>
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
		  		</div>
			</div>	
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div id="pnlProduct" class="panel panel-default">
		  		<div class="panel-heading">
		    		<h3 class="panel-title"><i class="glyphicon glyphicon-play"></i> Productos</h3>
		  		</div>
		  		<div class="panel-body">
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
				                <input type="number" class="form-control " id="bcant_X" name="bcant_X" placeholder="Cant" min="1" max="200">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<input type="hidden" id="billProductId_X" name="billProductId_X">
		  				<input type="hidden" id="billProductPrice_X" name="billProductPrice_X">
		  				<input type="hidden" id="billInstallmentAmount_X" name="billInstallmentAmount_X">
		  				<div class="col-md-1">
		  					<div class="form-group centered">
					            <input type="text" class="form-control " id="bProductCode_X" name="bProductCode_X" placeholder="C&oacute;digo">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-5">
		  					<div class="form-group centered">
		  						<div class="input-group">
					                <input type="text" class="form-control  not-writable" id="bname_X" name="bname_X" placeholder="Art&iacute;culo">
									<span id="btnSearchProduct_X" class="input-group-addon lov" onclick="javascript:Bill.showLovProduct('_X');"><i class="glyphicon glyphicon-search"></i></span>		  						
		  						</div>
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-2">
							<div class="form-group centered">
				                <input type="text" class="form-control " id="bcuotadiaria_X" name="bcuotadiaria_X" placeholder="$ c/cta.">
								<div class="help-block with-errors"></div>
				            </div>		  					
		  				</div>
		  				<div class="col-md-2">
		  					<div class="form-group centered">
				                <input type="text" class="form-control " id="bimp_X" name="bimp_X" placeholder="$ Importe">
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
				                <input type="number" class="form-control " id="bcant_0" name="bcant_0" placeholder="Cant" min="1" max="200" required>
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<input type="hidden" id="billProductId_0" name="billProductId_0">
		  				<input type="hidden" id="billProductPrice_0" name="billProductPrice_0">
		  				<input type="hidden" id="billInstallmentAmount_0" name="billInstallmentAmount_0">
		  				<div class="col-md-1">
		  					<div class="form-group centered">
		  						<label for="bProductCode_0">C&oacute;digo</label>
					            <input type="text" class="form-control " id="bProductCode_0" name="bProductCode_0" placeholder="C&oacute;digo" data-error="Requerido" required>
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-5">
		  					<div class="form-group centered">
		  						<label for="bname_0">Articulo/s</label>
		  						<div class="input-group">
					                <input type="text" class="form-control  not-writable" id="bname_0" name="bname_0" placeholder="Art&iacute;culo" data-error="Requerido" required>
									<span id="btnSearchProduct_0" class="input-group-addon lov" onclick="javascript:Bill.showLovProduct('_0');"><i class="glyphicon glyphicon-search"></i></span>		  						
		  						</div>
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-2">
							<div class="form-group centered">
		  						<label for="bcuotadiaria_0">$ c/cta.</label>
				                <input type="text" class="form-control " id="bcuotadiaria_0" name="bcuotadiaria_0" placeholder="$ c/cta." data-error="Requerido" required>
								<div class="help-block with-errors"></div>
				            </div>		  					
		  				</div>
		  				<div class="col-md-2">
		  					<div class="form-group centered">
		  						<label for="bimp_0">$ Importe</label>
				                <input type="text" class="form-control " id="bimp_0" name="bimp_0" placeholder="$ Importe" data-error="Requerido" required>
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
				                <input type="number" class="form-control " id="bcant_1" name="bcant_1" placeholder="Cant" min="1" max="200">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<input type="hidden" id="billProductId_1" name="billProductId_1">
		  				<input type="hidden" id="billProductPrice_1" name="billProductPrice_1">
		  				<input type="hidden" id="billInstallmentAmount_1" name="billInstallmentAmount_1">
		  				<div class="col-md-1">
		  					<div class="form-group centered">
					            <input type="text" class="form-control " id="bProductCode_1" name="bProductCode_1" placeholder="C&oacute;digo">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-5">
		  					<div class="form-group centered">
			  					<div class="input-group">
					                <input type="text" class="form-control  not-writable" id="bname_1" name="bname_1"  placeholder="Art&iacute;culo">
									<span id="btnSearchProduct_1" class="input-group-addon lov" onclick="javascript:Bill.showLovProduct('_1');"><i class="glyphicon glyphicon-search"></i></span>		  					
			  					</div>
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-2">
							<div class="form-group centered">
				                <input type="text" class="form-control " id="bcuotadiaria_1" name="bcuotadiaria_1" placeholder="$ c/cta.">
								<div class="help-block with-errors"></div>
				            </div>		  					
		  				</div>
		  				<div class="col-md-2">
		  					<div class="form-group centered">
				                <input type="text" class="form-control " id="bimp_1" name="bimp_1" placeholder="$ Importe">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-1">
		  					&nbsp;
		  				</div>
		  			</div>
		  			<div id="product_2" class="row">
		  				<div class="col-md-1">
		  					<div class="form-group centered">
				                <input type="number" class="form-control " id="bcant_2" name="bcant_2" placeholder="Cant" min="1" max="200">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<input type="hidden" id="billProductId_2" name="billProductId_2">
		  				<input type="hidden" id="billProductPrice_2" name="billProductPrice_2">
		  				<input type="hidden" id="billInstallmentAmount_2" name="billInstallmentAmount_2">
		  				<div class="col-md-1">
		  					<div class="form-group centered">
					            <input type="text" class="form-control " id="bProductCode_2" name="bProductCode_2" placeholder="C&oacute;digo">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-5">
		  					<div class="form-group centered">
			  					<div class="input-group">
					                <input type="text" class="form-control  not-writable" id="bname_2" name="bname_2"  placeholder="Art&iacute;culo">
									<span id="btnSearchProduct_2" class="input-group-addon lov" onclick="javascript:Bill.showLovProduct('_2');"><i class="glyphicon glyphicon-search"></i></span>		  					
			  					</div>
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-2">
							<div class="form-group centered">
				                <input type="text" class="form-control " id="bcuotadiaria_2" name="bcuotadiaria_2" placeholder="$ c/cta.">
								<div class="help-block with-errors"></div>
				            </div>		  					
		  				</div>
		  				<div class="col-md-2">
		  					<div class="form-group centered">
				                <input type="text" class="form-control " id="bimp_2" name="bimp_2" placeholder="$ Importe">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-1">
		  					&nbsp;
		  				</div>
		  			</div>
		  			<div id="product_3" class="row">
		  				<div class="col-md-1">
		  					<div class="form-group centered">
				                <input type="number" class="form-control " id="bcant_3" name="bcant_3" placeholder="Cant" min="1" max="200">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<input type="hidden" id="billProductId_3" name="billProductId_3">
		  				<input type="hidden" id="billProductPrice_3" name="billProductPrice_3">
		  				<input type="hidden" id="billInstallmentAmount_3" name="billInstallmentAmount_3">
		  				<div class="col-md-1">
		  					<div class="form-group centered">
					            <input type="text" class="form-control " id="bProductCode_3" name="bProductCode_3" placeholder="C&oacute;digo">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-5">
		  					<div class="form-group centered">
			  					<div class="input-group">
					                <input type="text" class="form-control  not-writable" id="bname_3" name="bname_3"  placeholder="Art&iacute;culo">
									<span id="btnSearchProduct_3" class="input-group-addon lov" onclick="javascript:Bill.showLovProduct('_3');"><i class="glyphicon glyphicon-search"></i></span>		  					
			  					</div>
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-2">
							<div class="form-group centered">
				                <input type="text" class="form-control " id="bcuotadiaria_3" name="bcuotadiaria_3" placeholder="$ c/cta.">
								<div class="help-block with-errors"></div>
				            </div>		  					
		  				</div>
		  				<div class="col-md-2">
		  					<div class="form-group centered">
				                <input type="text" class="form-control " id="bimp_3" name="bimp_3" placeholder="$ Importe">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-1">
		  					&nbsp;
		  				</div>
		  			</div>
		  			<div id="product_4" class="row">
		  				<div class="col-md-1">
		  					<div class="form-group centered">
				                <input type="number" class="form-control " id="bcant_4" name="bcant_4" placeholder="Cant" min="1" max="200">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<input type="hidden" id="billProductId_4" name="billProductId_4">
		  				<input type="hidden" id="billProductPrice_4" name="billProductPrice_4">
		  				<input type="hidden" id="billInstallmentAmount_4" name="billInstallmentAmount_4">
		  				<div class="col-md-1">
		  					<div class="form-group centered">
					            <input type="text" class="form-control " id="bProductCode_4" name="bProductCode_4" placeholder="C&oacute;digo">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-5">
		  					<div class="form-group centered">
			  					<div class="input-group">
					                <input type="text" class="form-control  not-writable" id="bname_4" name="bname_4"  placeholder="Art&iacute;culo">
									<span id="btnSearchProduct_4" class="input-group-addon lov" onclick="javascript:Bill.showLovProduct('_4');"><i class="glyphicon glyphicon-search"></i></span>		  					
			  					</div>
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-2">
							<div class="form-group centered">
				                <input type="text" class="form-control " id="bcuotadiaria_4" name="bcuotadiaria_4" placeholder="$ c/cta.">
								<div class="help-block with-errors"></div>
				            </div>		  					
		  				</div>
		  				<div class="col-md-2">
		  					<div class="form-group centered">
				                <input type="text" class="form-control " id="bimp_4" name="bimp_4" placeholder="$ Importe">
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
				                <input type="text" class="form-control  not-writable" id="bcuotaTotal" name="bcuotaTotal" placeholder="$ c/cta.">
								<div class="help-block with-errors"></div>
				            </div>		  					
		  				</div>
		  				<div class="col-md-2">
		  					<div class="form-group centered">
				                <input type="text" class="form-control  not-writable" id="bimpTotal" name="bimpTotal" placeholder="$ Importe Total">
								<div class="help-block with-errors"></div>
				            </div>
		  				</div>
		  				<div class="col-md-1">
		  					&nbsp;
		  				</div>
		  			</div>
		  		</div>
			</div>	
		</div>
	</div>
	</form>
	<div class="row">
		<div class="col-md-9">
          &nbsp;	  					
		</div>
		<div class="col-md-3">
			<div class="form-group">
				<label for="bcobrador">&nbsp;</label><br>
				<button id="btnFinalize" type="button" data-loading-text="Espere..." class="btn btn-success"><i class="glyphicon glyphicon-floppy-save"></i>&nbsp;Finalizar</button>&nbsp;
				<button id="btnCancel" type="button" data-loading-text="Espere..." class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i>&nbsp;Limpiar</button>
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
			
			$("#baddress").autocomplete({
			    paramName: 'q',
			    serviceUrl: "${pageContext.request.contextPath}/controller/html/client/autocomplete",
			    transformResult: function(response) {
			    	
			    	var list = [];
			    	
			    	var parsed = JSON.parse(response);

			    	$.each(parsed, function(){
			    		
			    		var obj = new Object();
			    		obj.data = "" + this.id;
			    		
			    		var mark = "";
			    	   	if(this.reductionMark != null && this.reductionMark != ""){
			    			mark = " / B";
			    	   	}
			    		
			    		obj.value = this.name + " / " + this.address + " / " + this.companyType + mark;
			    		list.push(obj);
			    		
			    		return;
			    	});
			    	
			        return {
			            suggestions: list 
			        };
			    },
			    onSelect: function (suggestion) {
			        $("#billClientIdSelected").val(suggestion.data);
			        
			        return;
			    }
			});
			
			return;
		}	
		
	);
</script>