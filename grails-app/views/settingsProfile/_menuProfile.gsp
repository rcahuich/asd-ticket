<br/>
<ul class="nav nav-pills nav-stacked">
    <li<%= request.forwardURI == "${createLink(uri: '/configuracion/cuenta')}" ? ' class=active' : '' %>><a href="${createLink(uri: "/perfil/${user.username}")}"><i class="fa fa-user"></i> <g:message code="profile.subnav.miCuenta" /></a></li>
    <li<%= request.forwardURI == "${createLink(uri: '/perfil/cambiar-contrasena')}" ? ' class=active' : '' %>><a href="${createLink(action:'password')}"><i class="fa fa-key"></i> <g:message code="profile.subnav.contraseña" /></a></li>
    <li<%= request.forwardURI == "${createLink(uri: '/perfil/cambiar-asociacion')}" ? ' class=active' : '' %>><a href="${createLink(action:'asociacion')}"><i class="fa fa-map-marker"></i> <g:message code="profile.subnav.asociacion" /></a></li>
    <li<%= request.forwardURI == "${createLink(uri: '/perfil/cambiar-email')}" ? ' class=active' : '' %>><a href="${createLink(action:'email')}"><i class="fa fa-envelope-square"></i> <g:message code="profile.subnav.email" /></a></li>
    <li><a href="${createLink(uri: "/perfil/${user.username}")}"><i class="fa fa-arrow-left"></i> <g:message code="profile.subnav.back.label" /></a></li>
</ul>