<html>
<head>

	<title><g:message code="springSecurity.login.title"/></title>
    <meta name="layout" content="main"/>
    <style type="text/css">
      body {
        padding-top: 90px;
        padding-bottom: 60px;
      }

      .form-signin {
        max-width: 330px;
        padding: 15px;
        margin: 0 auto;
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin .checkbox {
        font-weight: normal;
      }
      .form-signin .form-control {
        position: relative;
        font-size: 16px;
        height: auto;
        padding: 10px;
        -webkit-box-sizing: border-box;
           -moz-box-sizing: border-box;
                box-sizing: border-box;
      }
      .form-signin .form-control:focus {
        z-index: 2;
      }
      .form-signin input[type="text"] {
        margin-bottom: -1px;
        border-bottom-left-radius: 0;
        border-bottom-right-radius: 0;
      }
      .form-signin input[type="password"] {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
      }
      .nav {
        margin-left: 65px;
      }
      .img-logo {
        width: 240px; 
        height: 50px;
        border-radius: 7px;
      }
    </style>        
</head>

<body>


    <div class="container">
      <form action='${postUrl}' method='POST' autocomplete='off' class="form-signin">
        <div align="center">
            <asset:image src="AT-2.png" class="img-responsive img-logo" title="ADVENTicket"/>
        </div>
        
        <h1 class="form-signin-heading lead" style="text-align: center;">Inicia sesión</h1>
        <g:if test='${flash.message}'>
              <div class="alert alert-danger lead" style="font-size: 13px;text-align: center;">${flash.message}</div>
        </g:if>

        <input type='text' class='form-control' name='j_username' id='username' placeholder='${message(code: "menu.username.placeHolder")}' required autofocus=""/>
        <input type='password' class='form-control' name='j_password' id='password' placeholder='${message(code: "menu.contrasena.placeHolder")}' required/>
        <label class="checkbox">
          <a href="${createLink(controller: 'recoverAccount')}" class="btn btn-default btn-xs" style="float: right;" tabindex="5"><g:message code="menu.olvidoContrasena" /></a>
          <input type='checkbox' tabindex="3" class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>><g:message code="menu.recordar" />
        </label>
        <input type='submit' id="submit" value='${message(code: "springSecurity.login.button")}' class="btn btn-lg btn-primary btn-block"/>
        <br/>
      </form>


    </div> <!-- /container -->
    
</body>
</html>
