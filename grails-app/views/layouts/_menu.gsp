<!-- Navigation -->
<nav class="navbar navbar-default navbar-fixed-top topnav" role="navigation">
    <div class="container topnav">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand topnav" href="${createLink(controller: "home")}"><i
                    class="fa fa-ticket"></i> <g:message code="menu.app.name.title"/></a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <sec:ifNotLoggedIn>
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="${createLink(controller: 'login')}"><g:message code="menu.iniciaSesion"/></a>
                    </li>
                    <li>
                        <a href="${createLink(controller: 'enrollment', action: 'index')}"><g:message
                                code="menu.registration.label"/></a>
                    </li>
                    <li>
                        <a href="#contact"><g:message code="menu.contact.label"/></a>
                    </li>
                </ul>
            </sec:ifNotLoggedIn>
            <sec:ifLoggedIn>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="${createLink(controller: 'profile', action: 'myEvents')}">
                        <g:message code="menu.myevents.label"/></a></li>
                    <li><a href="${createLink(uri: "/perfil/${g.username()}")}">
                        <i class="fa fa-user"></i> <sec:username/></a></li>
                    <li>
                        <a href="${createLink(controller: 'logout', action: 'index')}" data-toggle="tooltip"
                           data-placement="bottom" title="${message(code: "menu.singout.label")}"><i
                                class="fa fa-sign-out"></i></a>
                    </li>
                </ul>
            </sec:ifLoggedIn>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>