<div class="form-group ${hasErrors(bean: user, field: 'nameFull', 'error')} required">
    <label class="col-lg-4 control-label" for="nameFull">
        <g:message code="enrollment.form.name.label" />
        <span class="required-indicator">*</span>
    </label>
    <div class="col-lg-4">
        <g:textField name="nameFull" maxlength="64" value="${user?.nameFull}" class="validate[required,custom[onlyLetter]] form-control"/>
    </div>
</div>

<div class="form-group ${hasErrors(bean: user, field: 'gender', 'error')} ">
    <label class="col-lg-4 control-label" for="gender">
        <g:message code="enrollment.form.sexo.label" />
        <span class="required-indicator">*</span>
    </label>
    <div class="col-lg-4">
        <label class="checkbox-inline">
            <input type="radio" name="gender" id="radioMale" value="M" class="validate[required]" ${user?.gender.toString() == "M" ? "checked" : ""} /> Masculino
        </label>
        <label class="checkbox-inline">
            <input type="radio" name="gender" id="radioFemale" value="M" class="validate[required]" ${user?.gender.toString() == "F" ? "checked" : ""} /> Femenino
        </label>
    </div>
</div>

<div class="form-group ${hasErrors(bean: user, field: 'birthday', 'error')} required">
    <label class="col-lg-4 control-label" for="birthday">
        <g:message code="enrollment.form.fechaDeNacimiento.label" />
        <span class="required-indicator">*</span>
    </label>
    <div class="col-lg-6">
        <g:datePicker class="form-control" name="birthday" precision="day"  value="${user?.birthday}"/>
    </div>
</div>
<%--
<div class="form-group ${hasErrors(bean: usuario, field: 'imagenes', 'error')} required">
    <label class="col-lg-4 control-label" for="imagen">
        <g:message code="enrollment.form.foto.label" />
    </label>
    <div class="col-lg-6">
        <div class="fileupload fileupload-new" data-provides="fileupload">
            <div class="fileupload-new thumbnail" style="width: 200px; height: 150px;"><img src="${createLink(action:'imagen', id:user?.id)}" style="width: 200px; height: 150px;"/></div>
            <div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 150px; line-height: 20px;"></div>
            <div>
                <span class="btn btn-file btn-default"><span class="fileupload-new">Selecionar imagen</span><span class="fileupload-exists">Cambiar</span><input type="file" name="imagen" id="imagen" value="${user?.imagenes}"/></span>
                <a href="#" class="btn fileupload-exists btn-default" data-dismiss="fileupload">Remover</a>
            </div>
        </div>
        <span class="help-block"> <g:message code="enrollment.form.imagen.note.label"/></span>
    </div>
</div>--%>