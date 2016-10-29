<style>

.popover{
    max-width: 100%; /* Max Width of the popover (depending on the container!) */
    width: 320px;
}

</style>

<div class="col-md-1">
	&nbsp;
</div>
<div class="col-md-10">
	<div id="billDetailMessages" class="row hide">
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	    <div class="col-md-8">
	        <div id="billDetailAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#billDetailAlertMessages').children('span').eq(0).html('');$('#billDetailMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	    <div class="col-md-2">
	        &nbsp;
	    </div>
	</div>
	<div class="row">
		<div clasS="col-md-12">
			<ol class="breadcrumb">
			  <li><a href="${pageContext.request.contextPath}/controller/html/bill/history/index">Consultar Cr&eacute;ditos</a></li>
			  <li class="active">Detalle de Cr&eacute;dito</li>
			</ol>
		</div>
	</div>
	<form id="frmCreditDetailExportPdf" method="POST" action="${pageContext.request.contextPath}/controller/html/bill/detail/export/pdf">
		<input type="hidden" id="billId" name="billId" value="${billId}">
		<input type="hidden" id="creditNumber" name="creditNumber" value="${creditNumber}">
	</form>
	<form id="frmCreditDetailExportXls" method="POST" action="${pageContext.request.contextPath}/controller/html/bill/detail/export/xls">
		<input type="hidden" id="billId" name="billId" value="${billId}">
		<input type="hidden" id="creditNumber" name="creditNumber" value="${creditNumber}">
	</form>
	<div class="row">
		<div class="col-md-2">
	        &nbsp;
	    </div>
		<div class="col-md-8">
			<div class="panel panel-red">
		  		<div class="panel-heading">
		    		<i class="glyphicon glyphicon-eye-open"></i>&nbsp;DETALLE DE CR&Eacute;DITO&nbsp;<span id="detailCreditNumber" style="font-weight:bold;">${creditNumber}</span>
		    		<div class="pull-right">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                            	<i class="fa fa-chevron-down"></i>
                            </button>
                            <ul class="dropdown-menu pull-right" role="menu">
                            	<li>
                                	<a href="javascript:void(0);" onclick="javascript:Bill.showPaydays();">
                                    	<i class="fa fa-calendar-o"></i>&nbsp;&nbsp;D&iacute;as de Cobro
                                    </a>
                                </li>
                                <li>
                                	<a href="javascript:void(0);" onclick="javascript:Bill.exportDetailToPdf();">
                                    	<i class="glyphicon glyphicon-print"></i>&nbsp;&nbsp;Imprimir
                                    </a>
                                </li>
                                <li>
                                	<a href="javascript:void(0);" onclick="javascript:Bill.exportDetailToXls();">
                                    	<i class="glyphicon glyphicon-download"></i>&nbsp;&nbsp;Exportar a Excel
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
		  		</div>
		  		<div class="panel-body">
		  			<table id="tCreditDetail" class="table table-bordered table-striped">
		  				<col style="width:40%">
		  				<tr>
		  					<td style="font-weight:bold;">Cliente</td>
		  					<td colspan="2" id="clientName"></td>
		  				</tr>
		  				<tr>
		  					<td style="font-weight:bold;">Direcci&oacute;n</td>
		  					<td colspan="2"  id="clientAddress"></td>
		  				</tr>
		  				<tr>
		  					<td style="font-weight:bold;">Tipo de Comercio</td>
		  					<td colspan="2"  id="customerCompanyType"></td>
		  				</tr>
		  				<tr>
		  					<td style="font-weight:bold;padding-top: 3%;">Zona / Cobrador</td>
		  					<td>
		  						<div class="input-group" style="margin-top: 2%;">
		  							<input type="hidden" id="billCollectorIdSelected" name="billCollectorIdSelected" required>
					                <input type="text" class="form-control" id="collectorDescription" name="collectorDescription" placeholder="Ingrese cobrador" required>
									<span id="btnSearchCollectorDetail" class="input-group-addon"><i class="glyphicon glyphicon-search lov"></i></span>		  						
		  						</div>
								<div id="billDetailCollectorErrorMessageDiv" class="help-block with-errors"></div>
		  					</td>
		  					<td id="billDetailCollectorActionCol" class="centered">
		  						<button id="btnBillDetailCollectorChange" class="btn btn-xs btn-primary" data-toggle="tooltip" 
		  								data-placement="top" title="Modificar Cobrador">
		  							<i class="fa fa-pencil"></i>
		  						</button>
		  					</td>
		  				</tr>
		  				<tr>
		  					<td style="font-weight:bold;">Vendedor</td>
		  					<td colspan="2"  id="traderName"></td>
		  				</tr>
		  				<tr>
		  					<td style="font-weight:bold;">Fecha Cr&eacute;dito</td>
		  					<td colspan="2"  id="creditDate"></td>
		  				</tr>
		  				<tr>
		  					<td style="font-weight:bold;">Importe Cr&eacute;dito</td>
		  					<td colspan="2"  id="creditAmount"></td>
		  				</tr>
		  				<tr>
		  					<td style="font-weight:bold;">Total Abonado</td>
		  					<td colspan="2"  id="currentAmountContainer"></td>
		  				</tr>
		  				<tr>
		  					<td style="font-weight:bold;">Cuota</td>
		  					<td colspan="2"  id="installment"></td>
		  				</tr>
		  				<tr>
		  					<td style="font-weight:bold;">Pago Semanal</td>
		  					<td colspan="2"  id="weekAmountContainer"></td>
		  				</tr>
		  				<tr>
		  					<td style="font-weight:bold;">Pago Primer Cuota</td>
		  					<td colspan="2"  id="firstInstallment"></td>
		  				</tr>
		  				<tr>
		  					<td style="font-weight:bold;">Estado</td>
		  					<td colspan="2"  id="status" style="font-weight:bold;"></td>
		  				</tr>
		  				<tr>
		  					<td style="font-weight:bold;">Fecha de Pago total</td>
		  					<td colspan="2"  id="completedDate"></td>
		  				</tr>
		  			</table>
		  			<table id="tBillProducts" class="table table-bordered table-striped">
		  				<thead>
			  				<tr>
			  					<th colspan="5" class="centered">PRODUCTOS</th>
			  				</tr>
			  				<tr>
			  					<th class="centered">CANTIDAD</th>
			  					<th class="centered">C&Oacute;DIGO</th>
			  					<th class="centered">DESCRIPCI&Oacute;N</th>
			  					<th class="centered">CUOTA DIARIA</th>
			  					<th class="centered">TOTAL</th>
			  				</tr>
		  				</thead>
		  				<tbody>
		  					<tr>
		  						<td colspan="3" class="centered">No se han encontrado resultados</td>
		  					</tr>
		  				</tbody>
		  			</table>
		  			<table id="tBillPayment" class="table table-bordered table-striped">
		  				<thead>
			  				<tr>
			  					<th colspan="5" class="centered">PAGOS</th>
			  				</tr>
			  				<tr>
			  					<th class="centered">FECHA</th>
			  					<th class="centered">COBRADOR</th>
			  					<th class="centered">IMPORTE</th>
			  					<th class="centered">PAGO VENDEDOR?</th>
			  					<th class="centered">ACCIONES</th>
			  				</tr>
		  				</thead>
		  				<tbody>
		  					<tr>
		  						<td colspan="5" class="centered">No se han encontrado resultados</td>
		  					</tr>
		  				</tbody>
		  			</table>
		  			<table id="tBillDevolution" class="table table-bordered table-striped">
		  				<thead>
			  				<tr>
			  					<th colspan="6" class="centered">DEVOLUCIONES</th>
			  				</tr>
			  				<tr>
			  					<th class="centered">FECHA</th>
			  					<th class="centered">CANT</th>
			  					<th class="centered">DESCRIPCI&Oacute;N</th>
			  					<th class="centered">IMPORTE</th>
			  					<th class="centered">CUOTA</th>
			  					<th class="centered">DETALLE</th>
			  				</tr>
		  				</thead>
		  				<tbody>
		  					<tr>
		  						<td colspan="6" class="centered">No se han encontrado resultados</td>
		  					</tr>
		  				</tbody>
		  			</table>
		  			<table id="tBillDiscount" class="table table-bordered table-striped">
		  				<thead>
			  				<tr>
			  					<th colspan="3" class="centered">DESCUENTOS</th>
			  				</tr>
			  				<tr>
			  					<th class="centered">FECHA</th>
			  					<th class="centered">IMPORTE</th>
			  					<th class="centered">DETALLE</th>
			  				</tr>
		  				</thead>
		  				<tbody>
		  					<tr>
		  						<td colspan="3" class="centered">No se han encontrado resultados</td>
		  					</tr>
		  				</tbody>
		  			</table>
		  			<table id="tBillReduction" class="table table-bordered table-striped">
		  				<thead>
			  				<tr>
			  					<th colspan="3" class="centered">BAJAS</th>
			  				</tr>
			  				<tr>
			  					<th class="centered">FECHA</th>
			  					<th class="centered">IMPORTE</th>
			  					<th class="centered">ACCIONES</th>
			  				</tr>
		  				</thead>
		  				<tbody>
		  					<tr>
		  						<td colspan="3" class="centered">No se han encontrado resultados</td>
		  					</tr>
		  				</tbody>
		  			</table>
		  			<table id="billRemainingAmount" class="table table-bordered table-striped">
		  				<tr>
		  					<td style="font-weight:bold;background-color:#FF7E7E;">SALDO RESTANTE</td>
		  					<td id="remainingAmount" style="font-weight:bold;background-color:#FF7E7E;"></td>
		  				</tr>
		  			</table>
		  		</div>
			</div>	
		</div>
		<div class="col-md-2">
	        &nbsp;
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
			
			var input = null;
			var billId = '${billId}';
			if(billId != ''){
				input = billId;
			}
			
			$("#btnBillDetailCollectorChange").on('click', function(){
				
				var collectorId = $("#billCollectorIdSelected").val();
				if(Commons.isNotValid(collectorId)){
					collectorId = -1;
				}
				var billId = $("#billId").val();
				if(Commons.isNotValid(billId)){
					billId = -1;
				}
				
				$.ajax({ 
				   type    : "PUT",
				   url     : Constants.contextRoot + "/controller/html/bill/collector/" + billId + "/" + collectorId,
				   dataType: 'json',
				   contentType: "application/json;",
				   success:function(data) {
					   Message.hideMessages($('#billDetailAlertMessages'), $("#billDetailMessages"));
					   if(data != null && data.status == 0){
						   
						   var list = data.data;

						   if(list.length > 0){
							   
							   var r = list[0];
							   
							   var selectedId = r.collectorZone;
							   var selectedDescription = r.collectorId + " / " + r.collectorDescription;
							   
							   $("#billCollectorIdSelected").val(selectedId);
							   $("#collectorDescription").val(selectedDescription);
							   
							   Message.showMessages($('#billDetailAlertMessages'), $("#billDetailMessages"), 
									   "El cobrador se ha modificado correctamente.", "alert-warning", "glyphicon glyphicon-info-sign", "Bien! ");
							   setTimeout(function(){$("#billDetailMessages").addClass("hide");}, 2500);
						   }
						   
						   return;
					   }else{
						   Message.showMessages($('#billDetailAlertMessages'), $("#billDetailMessages"), data.message);
					   }
				   },
				   error:function(data){
					   Message.showMessages($('#billDetailAlertMessages'), $("#billDetailMessages"), data.responseJSON.message);
					   
					   return;
				   }
				});
				
				
				
				return;
			});
			
			Bill.initDetail(input);
			
			$("#collectorDescription").autocomplete({
			    paramName: 'q',
			    serviceUrl: "${pageContext.request.contextPath}/controller/html/collector/autocomplete",
			    transformResult: function(response) {
			    	
			    	var list = [];
			    	
			    	var parsed = JSON.parse(response);

			    	$.each(parsed, function(){
			    		
			    		var obj = new Object();
			    		obj.data = "" + this.id;
			    		
			    		obj.value = this.zone + " / " + this.description;
			    		list.push(obj);
			    		
			    		return;
			    	});
			    	
			        return {
			            suggestions: list 
			        };
			    },
			    onSelect: function (suggestion) {
			        $("#billCollectorIdSelected").val(suggestion.data);
			        
			        return;
			    }
			});
		    
			return;
		}
		
	);
</script>