<#import "parts/common.ftl" as commom>
<@commom.page>
    <link rel="stylesheet" type="text/css" href="/static/css/users.css">
    <div class="users_page">
        <form action="/user" method="post">
            <h3>Информация о пользователе</h3>
            <br>
            <input type="text" class="form-control" value="${user.firstName}" name="firstName"/>
            <input type="text" class="form-control" value="${user.lastName}" name="lastName"/>
            <input type="text" class="form-control" value="${user.username}" name="username"/>
            <input type="email" class="form-control" value="${user.email}" name="email"/>

            <#list roles as role>
                <div>
                    <label><input type="checkbox"
                                  name="${role}" ${user.roleSet?seq_contains(role)?string("checked","")}/>${role}
                    </label>
                </div>
            </#list>
            <input type="hidden" value="${user.id}" name="usedId"/>
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <input type="submit" name="edit" value="Редактировать" class="btn btn-success"/>
        </form>
    </div>
</@commom.page>