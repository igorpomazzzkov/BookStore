<#import "parts/common.ftl" as commom>
<@commom.page>
    <link rel="stylesheet" type="text/css" href="/static/css/users.css">
    <div class="users_page">
        <table>
            <tr>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Username</th>
                <th>Email</th>
                <th>Роли</th>
                <th>Удалить</th>
            </tr>
            <#list users as user>
            <tr>
                <td><a href="/user/${user.id}">${user.firstName}</a></td>
                <td><a href="/user/${user.id}">${user.lastName}</a></td>
                <td><a href="/user/${user.id}">${user.username}</a></td>
                <td><a href="/user/${user.id}">${user.email}</a></td>
                <td>
                    <#list user.roleSet as role>${role.name()}<#sep >, </#list>
                </td>
                <td align="center"><a href="/user/deleteUser/${user.id}"><font color="red">X</font></a></td>
            </tr>
            </#list>
        </table>
    </div>
</@commom.page>