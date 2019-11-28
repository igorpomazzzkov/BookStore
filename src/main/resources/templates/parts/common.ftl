<#macro page>
    <#import "account.ftl" as account>
    <#include "security.ftl">
    <#import "context-menu.ftl" as context>
    <html>
    <head>
        <meta charset="UTF-8">
        <title>Book</title>
        <link rel="stylesheet"  href="/static/css/bootstrap.min.css" crossorigin="anonymous" type="text/css">
        <link rel="stylesheet" href="/static/css/main.css" type="text/css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
    <div class="account_blue"></div>
    <div class="wrapper">
        <div class="page">
            <div class="books">
                <div class="account">
                    <@account.account/>
                </div>
                <div class="main_page">
                    <#if isAdmin>
                        <div class="content" style="width: 100%">
                            <#nested >
                        </div>
                        <#else >
                        <div class="context-menu">
                            <@context.context/>
                        </div>
                        <div class="content">
                            <#nested >
                        </div>
                    </#if>
                </div>
            </div>
        </div>
        </div>
    </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="/static/js/bootstrap.js"></script>
    <script src="/static/js/main.js"></script>
   </html>
</#macro>