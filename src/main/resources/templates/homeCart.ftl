<#import "parts/common.ftl" as common>
<#import "parts/main.ftl" as home>

<@common.page>
    <div class="alert alert-warning" role="alert">
        <p>Ваша корзина</p>
    </div>
    <@home.home "/removeFromCart" true false/>
</@common.page>