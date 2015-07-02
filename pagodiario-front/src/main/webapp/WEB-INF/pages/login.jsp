<style>

.form-signin
{
    max-width: 330px;
    padding: 15px;
    margin: 0 auto;
}
.form-signin .form-signin-heading, .form-signin .checkbox
{
    margin-bottom: 10px;
}
.form-signin .checkbox
{
    font-weight: normal;
}
.form-signin .form-control
{
    position: relative;
    font-size: 16px;
    height: auto;
    padding: 10px;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}
.form-signin .form-control:focus
{
    z-index: 2;
}
.form-signin input[type="text"]
{
    margin-bottom: -1px;
    border-bottom-left-radius: 0;
    border-bottom-right-radius: 0;
}
.form-signin input[type="password"]
{
    margin-bottom: 10px;
    border-top-left-radius: 0;
    border-top-right-radius: 0;
}
.account-wall
{
    margin-top: 20px;
    padding: 40px 0px 20px 0px;
    background-color: #f7f7f7;
    -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}
.login-title
{
    color: #555;
    font-size: 2.8em;
    display: block;
    text-shadow: 2px 2px 4px #8B9194;
}
.profile-img
{
    width: 96px;
    height: 96px;
    margin: 0 auto 10px;
    display: block;
    -moz-border-radius: 50%;
    -webkit-border-radius: 50%;
    border-radius: 50%;
}
.need-help
{
    margin-top: 10px;
}
.new-account
{
    display: block;
    margin-top: 10px;
}

</style>
<div class="modal fade" id="modalRegistration" tabindex="-1" role="dialog" aria-labelledby="modalRegistrationLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modalRegistrationLabel">Registraci&oacute;n</h4>
      </div>
      <div class="modal-body">
		<form id="frmRegistration" action="${pageContext.request.contextPath}/controller/html/user/registration" 
				method="POST" data-toggle="validator">
				<input type="hidden" id="pageFrom" name="pageFrom" value="login">
	    	<div class="form-group">
				<label for="name">Nombre y Apellido</label>
			    <input type="text" class="form-control" id="name" name="name" placeholder="Ingrese Nombre y Apellido..." required>
			    <div class="help-block with-errors"></div>
			</div>
			<div class="form-group">
				<label for="email">e-mail</label>
			    <input type="email" class="form-control" id="email" name="email" placeholder="Ingrese e-mail..." required>
			    <div class="help-block with-errors"></div>
			</div>
			<div class="form-group">
				<label for="documentNumber">N&uacute;mero de Documento</label>
			    <input type="number" class="form-control" id="documentNumber" name="documentNumber" placeholder="Ingrese N&uacute;mero de Documento..." required>
			    <div class="help-block with-errors"></div>
			</div>
			<div class="form-group">
				<label for="nusername">Nombre de Usuario</label>
			    <input type="text" class="form-control" id="nusername" name="username" placeholder="Ingrese Nombre de Usuario..." required>
			    <div class="help-block with-errors"></div>
			</div>
			<div class="form-group">
				<label for="npassword">Contrase&ntilde;a</label>
			    <input type="password" class="form-control" id="npassword" name="password" placeholder="Ingrese Contrase&ntilde;a..." data-minlength="6" required>
			    <div class="help-block with-errors"></div>
			</div>
			<div class="form-group">
				<label for="rpassword">Re-Ingrese Contrase&ntilde;a</label>
			    <input type="password" class="form-control" id="rpassword" name="rpassword" placeholder="Re-Ingrese Contrase&ntilde;a..." data-minlength="6" data-match="#npassword" required>
			    <div class="help-block with-errors"></div>
			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        <button id="btnRegistration" type="button" class="btn btn-primary">Guardar</button>
      </div>
    </div>
  </div>
</div>
<div class="row">
	<div class="col-md-12">
		&nbsp;
	</div>
</div>
<div id="loginMessages" class="row hide">
    <div class="col-md-2">
        &nbsp;
    </div>
    <div class="col-md-8">
        <div id="loginAlertMessages" class="alert alert-danger alert-dismissible" role="alert">
          <button type="button" class="close" 
                    onclick="javascript:$('#loginAlertMessages').children('span').eq(0).html('');$('#loginMessages').addClass('hide');"><span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span></button>
              <span></span>
        </div>
    </div>
    <div class="col-md-2">
        &nbsp;
    </div>
</div>
<div class="row">
    <div class="col-md-8">
    </div>
	<div class="col-sm-6 col-md-4 col-md-offset-4">
        <h1 class="text-center login-title">SGPD - Sistema de Gesti&oacute;n de Pago Diario</h1>
        <div class="account-wall rounded-corner">
        	<img class="profile-img" src="${pageContext.request.contextPath}/public/images/photo.png?sz=120"
                    alt="">
            <form id="frmLogin" class="form-signin" action="${pageContext.request.contextPath}/controller/html/login" 
            		method="POST" data-toggle="validator">
            	<div class="form-group">
	            	<input id="username" type="text" name="username" class="form-control" placeholder="username" required autofocus>
	            	<div class="help-block with-errors"></div>
	            </div>
	            <input id="password" type="password" name="password" class="form-control" placeholder="Password" required>
	            <br>
	            <button id="btnLogin" class="btn btn-lg btn-success btn-block" type="submit">
	                Iniciar Sesi&oacute;n</button>
	            <br>
	            <div class="pull-right">
            		<a href="#" data-toggle="modal" data-target="#modalRegistration">Registrarse</a>
            	</div>
	            <label class="checkbox pull-left">
	            <!--
	                <input type="checkbox" name="rememberMe" value="remember-me">
	                Remember me -->
	            </label>
            </form>
        </div>
    </div>
</div>
<script>
        function getParameterByName(name) {
            name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
            var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
                results = regex.exec(location.search);
            
            return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
        }
        
        $(document).ready(
            function(){
            	$("body").addClass("grad2");
            	Message.hideMessages($('#loginAlertMessages'), $("#loginMessages"));
            	
            	var errorMsg = "${errorMsg}";
            	if(errorMsg != null && errorMsg != ""){
            		Message.showMessages($('#loginAlertMessages'), $("#loginMessages"), errorMsg);
            	}
            	
            	$("#btnRegistration").click(
            		function(){
            			
            			$("#frmRegistration").submit();
            			
            			return;
            		}		
            	);

            	$('#modalRegistration').on('hidden.bs.modal', function (e) {
            		
            		Login.registrationReset();
            		
            		return;
            	});
            	
                return;
            }
        );
</script>