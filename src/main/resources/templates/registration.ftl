<#import "parts/common.ftl" as common>
<@common.page>
<link rel="stylesheet" href="/static/css/login.css" type="text/css"/>
<form method="post" action="/registration">
    <div class="login_page">
        <div class="form-group">
        <input type="text" class="form-control ${(firstNameError??)?string('is-invalid','')}" name="firstName"
               placeholder="Имя"
               value="<#if user??>${user.firstName}</#if>"/>
            <#if firstNameError??>
                <div class="invalid-feedback">
                    ${firstNameError}
                </div>
            </#if>
        </div>
        <div class="form-group">
            <input type="text" class="form-control ${(lastNameError??)?string('is-invalid','')}" name="lastName"
                   placeholder="Фамилия"
                   value="<#if user??>${user.lastName}</#if>"/>
            <#if lastNameError??>
                <div class="invalid-feedback">
                    ${lastNameError}
                </div>
            </#if>
        </div>
        <div class="form-group">
            <input type="text" class="form-control ${(usernameError??)?string('is-invalid','')}"
                   name="username"
                   placeholder="Username"
                   value="<#if user??>${user.username}</#if>"/>
            <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                </div>
            </#if>
        </div>
        <div class="form-group">
            <input type="email" class="form-control ${(emailError??)?string('is-invalid','')}"
                   name="email"
                   placeholder="Email"
                   value="<#if user??>${user.email}</#if>"
            />
            <#if emailError??>
                <div class="invalid-feedback">
                    ${emailError}
                </div>
            </#if>
        </div>
        <div class="form-group">
            <input type="password" class="form-control ${(passwordError??)?string('is-invalid','')}" name="password" placeholder="Пароль"/>
            <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
        </div>
        <div class="form-group">
            <input type="password" class="form-control ${(passwordTooError??)?string('is-invalid','')}" name="passwordToo" placeholder="Повторите пароль"/>
            <#if passwordTooError??>
                <div class="invalid-feedback">
                    ${passwordTooError}
                </div>
            </#if>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="submit" class="btn btn-success" value="Регистрация"/>
        <a href="/login" class="btn btn-light">Логин</a>
    </div>
</form>
</@common.page>