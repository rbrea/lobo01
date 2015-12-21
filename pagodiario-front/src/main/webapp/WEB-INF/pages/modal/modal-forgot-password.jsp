<div id="modal-forgot-password-container" class="container-fluid" style="display:none;">
	<div id="modalForgotMessages" class="row hide">
	    <div class="col-md-12">
	        <div id="modalForgotAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
	          <button type="button" class="close" 
	                    onclick="javascript:$('#modalForgotAlertMessages').children('span').eq(0).html('');$('#modalForgotMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
	              <span></span>
	        </div>
	    </div>
	</div>
	<div class="row">
		<div class="col-md-3">
			&nbsp;
		</div>
		<div class="col-md-6">
			<div class="panel panel-default">
  				<div class="panel-body">
  					<form id="frmForgotPassword">
  						<div class="form-group">
							<label for="verificationCode">C&oacute;digo de Verificaci&oacute;n</label>
							<div class='input-group'>
								<span class="input-group-addon">
			                		<span class="fa fa-barcode"></span>
			                	</span>
						    	<input type="text" class="form-control" id="verificationCode" name="verificationCode" placeholder="Ingrese C&oacute;digo de Verificaci&oacute;n ..." required>
						    </div>
						    <div class="help-block with-errors"></div>
						</div>
						<div class="form-group">
							<label for="newpassword">Contrase&ntilde;a</label>
							<div class='input-group'>
								<span class="input-group-addon">
			                		<span class="fa fa-expeditedssl"></span>
			                	</span>
						    	<input type="password" class="form-control" id="newpassword" name="newpassword" placeholder="Ingrese nueva Contrase&ntilde;a..." data-minlength="6" required>
						    </div>
						    <div class="help-block with-errors"></div>
						</div>
						<div class="form-group">
							<label for="rnewpassword">Re-Ingrese Contrase&ntilde;a</label>
							<div class='input-group'>
								<span class="input-group-addon">
			                		<span class="fa fa-expeditedssl"></span>
			                	</span>
						    	<input type="password" class="form-control" id="rnewpassword" name="rnewpassword" placeholder="Re-Ingrese Contrase&ntilde;a..." data-minlength="6" data-match="#newpassword" required>
						    </div>
						    <div class="help-block with-errors"></div>
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