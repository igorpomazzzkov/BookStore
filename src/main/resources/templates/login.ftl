<#import "parts/common.ftl" as common>
<@common.page>
    <link rel="stylesheet" href="/static/css/login.css" type="text/css">
    <form method="post" action="/login">
        <div class="login_page">
            <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
                <div class="alert alert-danger" role="alert">
                    ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
                </div>
            </#if>
            <#if message??>
                <div class="alert alert-${messageType}" role="alert">
                    ${message}
                </div>
            </#if>
            <input type="text" class="form-control" name="username" placeholder="username"/>

            <input type="password" class="form-control" name="password" value="" placeholder="password"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="login_page_button">
                <a class="btn btn-light" href="/registration">Регистрация</a>
                <input type="submit" class="btn btn-success" value="Войти"/>
            </div>
        </div>
    </form>
</@common.page>
