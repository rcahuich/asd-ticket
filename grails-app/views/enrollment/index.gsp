<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="menu.registration.label"/></title>
    <asset:stylesheet src="bootstrap-datepicker-v2.min.css"/>
    <asset:javascript src="bootstrap-datepicker-v2.min.js"/>
    <asset:javascript src="bootstrap-datepicker-v2.es.js"/>
    <asset:stylesheet src="validationEngine.jquery.css"/>
    <asset:javascript src="jquery.validationEngine.js"/>
    <asset:javascript src="jquery.validationEngine-es.js"/>
    <script type="text/javascript">
        function reloadCaptcha() {
            $("#ajax_spinner_time").show();
            $.ajax({
                type: 'GET',
                url: '${createLink(controller: 'enrollment', action: 'reloadCaptcha')}',
                success: function (data) {
                    $('#gcaptcha').html(data);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log(textStatus);
                }
            });
        }

        $(document).ready(function () {
            reloadCaptcha();
            $("#form-register").validationEngine('attach', {scroll: false});
            $('#birthday').datepicker({
                endDate: "today",
                language: "es"
            });

            $(document).on('click', '#save-register', function (e) {
                e.preventDefault();
                if ($("#form-register").validationEngine('validate')) {
                    $('#login').hide();
                    $('#msg-error').hide();
                    $('#outline-register').show();
                    $.ajax({
                        type: 'POST',
                        url: '${createLink(controller: 'enrollment', action: 'saveEnroll')}',
                        data: $('#form-register').serialize(),
                        success: function (data) {
                            $('#outline-register').hide();
                            $('#name').html(data.username);
                            $('#msg-success').show();
                        },
                        error: function (data) {
                            reloadCaptcha();
                            $('#msg-error').show();
                            $('#error').html(data.responseText);
                            $('#outline-register').hide();
                            $('#login').addClass('animated fadeIn').show();
                        }
                    });
                }
            });

        });
    </script>
    <style type="text/css">
    body {
        margin-top: 30px;
    }

    #msg-success {
        display: none;
        width: 100%;
        margin: 5% 0 60% 0;
    }

    .msg {
        height: 70px;
    }
    </style>
</head>

<body>

<div class="container">
    <div class="page-header text-center">
        <h1><small><g:message code="enrollment.title.label"/></small></h1>
    </div>

    <div class="text-center">
        <p><g:message code="enrollment.instruction1.label"/></p>
    </div>
</div>
<br>

<div class="row centered-form">
    <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
        <div class="panel panel-default">

            <div class="panel-body">

                <div id="outline-register">
                    <div class="circle">
                    </div>
                </div>

                <div id="msg-success">
                    <div class="msg text-center">
                        <i class="fa fa-check-circle fa-4x animated rotateIn" style="color: #00B300"></i>

                        <div class="animated fadeInUp">
                            <h2><g:message code="enrollment.form.msg.success1.label"/> <span id="name"></span>!</h2>

                            <h5 style="line-height: 1.5;"><g:message code="enrollment.form.msg.success2.label"/></h5>
                            <a class="btn btn-default" href="${createLink(uri: "/")}">Ir a Inicio</a>
                        </div>
                    </div>
                </div>

                <div id="login">

                    <form id="form-register" role="form">
                        <div id="msg-error" style="display: none;">
                            <div class="alert alert-danger" role="alert"><span id="error"></span></div>
                        </div>

                        <div class="form-group">
                            <input type="text" name="username" id="username" class="validate[required] form-control"
                                   autofocus
                                   placeholder="${message(code: "enrollment.form.username.label")}">
                        </div>

                        <div class="form-group">
                            <input type="text" name="nameFull" id="nameFull" class="validate[required] form-control"
                                   placeholder="${message(code: "enrollment.form.name.label")}">
                        </div>

                        <div class="form-group">
                            <input type="text" name="birthday" id="birthday" class="validate[required] form-control"
                                   readonly
                                   placeholder="${message(code: "enrollment.form.fechaDeNacimiento.label")}">
                        </div>

                        <div class="form-group">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="genderM" value="M"
                                       class="validate[required]"> <g:message code="enrollment.form.sexo.m.label"/>
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="genderF" value="F"
                                       class="validate[required]"> <g:message code="enrollment.form.sexo.f.label"/>
                            </label>
                        </div>

                        <div class="form-group">
                            <input type="email" name="email" id="email"
                                   class="validate[required,custom[email]] form-control"
                                   placeholder="${message(code: "enrollment.form.correo.label")}">
                        </div>

                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="password" name="password" id="password"
                                           class="validate[required,minSize[6],maxSize[25],custom[cpPassword]] form-control"
                                           placeholder="${message(code: "enrollment.form.password.label")}">
                                </div>
                            </div>

                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="password" name="password_confirmation" id="password_confirmation"
                                           class="validate[required,equals[password]] form-control"
                                           placeholder="${message(code: "enrollment.form.confirmPassword.label")}">
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 col-md-offset-3">
                                <div id="gcaptcha"></div>
                            </div>
                        </div>
                           <br/>
                <button id="save-register" type="submit" class="btn btn-success btn-block"><g:message
                        code="enrollment.form.btn.add.label"/></button>
            </form>
            <!--
            <div class="login-or">
                <hr class="hr-or">
                <span class="span-or">or</span>
            </div>-->

        </div>
    </div>
</div>
</div>
</div>
</body>
</html>