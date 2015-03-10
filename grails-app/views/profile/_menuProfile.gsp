<br/>
<ul class="nav nav-pills nav-stacked">
    <li<%= request.forwardURI == "${createLink(uri: '/mis-eventos')}" ? ' class=active' : '' %>><a href="${createLink(action:'myEvents')}"><i class="fa fa-calendar"></i> <g:message code="profile.subnav.misEventos" /></a></li>
</ul>