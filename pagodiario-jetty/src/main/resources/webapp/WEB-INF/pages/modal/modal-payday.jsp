<style>
	
	.payday.active, .payday:active {
    	background-color: #5cb85c;
    }
}

</style>

<div id="modal-payday-container" class="container-fluid" style="display:none;">
	<div id="modalPaydayMessages" class="row hide">
	    <div class="col-md-12">
	        <div id="modalPaydayAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#modalPaydayAlertMessages').children('span').eq(0).html('');$('#modalPaydayMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	</div>
	<div class="row">
		<div class="col-md-7">
			&nbsp;
		</div>
		<div class="col-md-3">
			<h4><span class="label label-primary"><i class="fa fa-key"></i>&nbsp;N&uacute;mero de Cr&eacute;dito:&nbsp;<span id="creditNumberPaydaySpan" class="badge"></span></span></h4>
		</div>
		<div class="col-md-2">
			&nbsp;
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			&nbsp;
		</div>
	</div>
	<div class="row">
		<div class="col-md-3">
			&nbsp;
		</div>
		<div class="col-md-6">
			<div class="panel panel-default">
  				<div class="panel-body">
  					<form id="frmPayday">
  						<input type="hidden" id="paydayBillId" name="paydayBillId">
					<div class="form-group centered">
						<label for="paydayCollectorDays">D&iacute;as de Cobro</label>
					    <div class="btn-group" data-toggle="buttons">
						  <label class="payday btn btn-danger btn-sm active">
						    <input id="weekSunday" type="checkbox" autocomplete="off"> D
						  </label>
						  <label class="payday btn btn-danger btn-sm active">
						    <input id="weekMonday" type="checkbox" autocomplete="off"> L 
						  </label>
						  <label class="payday btn btn-danger btn-sm active">
						    <input id="weekTuesday" type="checkbox" autocomplete="off"> M
						  </label>
						  <label class="payday btn btn-danger btn-sm active">
						    <input id="weekWednesday" type="checkbox" autocomplete="off"> M
						  </label>
						  <label class="payday btn btn-danger btn-sm active">
						    <input id="weekThursday" type="checkbox" autocomplete="off"> J
						  </label>
						  <label class="payday btn btn-danger btn-sm active">
						    <input id="weekFriday" type="checkbox" autocomplete="off"> V
						  </label>
						  <label class="payday btn btn-danger btn-sm active">
						    <input id="weekSaturday" type="checkbox" autocomplete="off"> S
						  </label>
						</div>
					</div>
					</form>
				</div>
			</div>
		</div>
		<div class="col-md-3">
			&nbsp;
		</div>
	</div>
</div>