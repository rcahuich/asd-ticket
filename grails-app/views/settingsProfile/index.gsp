<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>${user.nameFull} </title>
    <style type="text/css">
    body {
        margin-top: 50px;
    }
    </style>
</head>
<body>

<!-- Header Profile -->
<div class="banner animated fadeInDown">
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <div class="row">
                    <div class="col-xs-6 col-md-4">
                        <img data-src="holder.js/140x140" class="img-circle" alt="140x140" style="width: 140px; height: 140px; margin-top: -25%;" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMTQwIiBoZWlnaHQ9IjE0MCIgdmlld0JveD0iMCAwIDE0MCAxNDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjxkZWZzLz48cmVjdCB3aWR0aD0iMTQwIiBoZWlnaHQ9IjE0MCIgZmlsbD0iI0VFRUVFRSIvPjxnPjx0ZXh0IHg9IjQ0LjA0Njg3NSIgeT0iNzAiIHN0eWxlPSJmaWxsOiNBQUFBQUE7Zm9udC13ZWlnaHQ6Ym9sZDtmb250LWZhbWlseTpBcmlhbCwgSGVsdmV0aWNhLCBPcGVuIFNhbnMsIHNhbnMtc2VyaWYsIG1vbm9zcGFjZTtmb250LXNpemU6MTBwdDtkb21pbmFudC1iYXNlbGluZTpjZW50cmFsIj4xNDB4MTQwPC90ZXh0PjwvZz48L3N2Zz4=" data-holder-rendered="true">
                    </div>
                    <div class="col-xs-12 col-md-8">
                        <h2>${user.nameFull}</h2>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <ul class="list-inline banner-social-buttons">
                    <li>
                        <a href="https://twitter.com/SBootstrap" class="btn btn-default btn-lg"><i class="fa fa-twitter fa-fw"></i> <span class="network-name">Twitter</span></a>
                    </li>
                    <li>
                        <a href="https://github.com/IronSummitMedia/startbootstrap" class="btn btn-default btn-lg"><i class="fa fa-github fa-fw"></i> <span class="network-name">Github</span></a>
                    </li>
                    <li>
                        <a href="#" class="btn btn-default btn-lg"><i class="fa fa-linkedin fa-fw"></i> <span class="network-name">Linkedin</span></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-xs-6 col-sm-6 col-md-3">
            <g:render template="menuProfile"/>
        </div>
        <div class="col-xs-12 col-sm-6 col-md-9">
            <br/>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><g:message code="profile.miPerfil.subTitle" /></h3>
                </div>
                <div class="panel-body">





                    <g:form action="profileUpdate" class="form-horizontal" enctype="multipart/form-data">
                        <g:render template="profile"/>

                        <div class="form-actions" style="text-align: right;">
                            <g:set var="idUsuario" value="${user.id }" scope="session" />
                            <button type="submit" class="btn btn-primary">
                                <i class="fa fa-refresh"></i>
                                <g:message code="button.profileUpdate" />
                            </button>
                        </div>

                    </g:form>


                </div>
            </div>

        </div>
    </div>

</div>


</body>
</html>