<#import "parts/common.ftl" as common>
<@common.page>
    <link rel="stylesheet" type="text/css" href="/static/css/users.css">
    <div class="users_page">
        <form method="post" action="/user/profile">
            <h5>${user.username}</h5>
            <input type="text" class="form-control" name="firstName" value="${user.firstName}">
            <input type="text" class="form-control" name="lastName" value="${user.lastName}">
            <input type="email" class="form-control" name="email" value="${user.email!''}">
            <input type="password" class="form-control" name="password" placeholder="password"/>
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <input type="submit" class="btn btn-success" value="Сохранить"/>
        </form>
    </div>
</@common.page>