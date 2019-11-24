<#macro account>
    <link rel="stylesheet" href="/static/css/account.css" type="text/css">
    <div class="account_page">
        <#include "security.ftl">
        <div class="account_page_info">
            <nav class="nav">
                <a class="nav-link active" href="/book">Каталог книг</a>
                <#if isAdmin>
                    <a class="nav-link" href="/user">Пользователи</a>
                </#if>
                <#if user??>
                    <a class="nav-link" href="/user/profile">Профиль</a>
                    <a class="nav-link" href="/logout">Выход</a>
                </#if>
            </nav>
        </div>
        <div class="account_page_cart">
            <div class="cart" data-toggle="modal" data-target="#exampleModalCenter">
                <#if user?? && name!="unkown">
                    <div class="user_name">${name}</div>
                </#if>
                <div class="cart_img"></div>
            </div>
        </div>
    </div>
</#macro>
