<#import "parts/common.ftl" as common>
<#import "parts/account.ftl" as account>
<@common.page>
    <head>
        <link rel="stylesheet" href="/static/css/book.css" type="text/css">
    </head>
    <div class="book">
        <div class="img">
            <#if book.filename??>
                <img src="/img/${book.filename}"/>
            </#if>
        </div>
        <div class="date-book">
            <div class="book_name"><h4>${book.name}</h4></div>
            <div class="book_author"><b><i><a href="/book/author=${book.author.id}">${book.getAuthorName()}</a></i></b>
            </div>
            <hr>
            <div class="book_info">
                <div class="book_info_text">
                    <#if book.price?has_content>
                        <p>Цена:</p>
                    </#if>
                    <#if book.dateOfPublication?has_content>
                        <p>Первое издание:</p>
                    </#if>
                    <#if book.publishing?has_content>
                        <p>Издательство:</p>
                    </#if>
                    <#if book.countOfPage?has_content>
                        <p>Количество страниц:</p>
                    </#if>
                    <#if bookCategories?has_content>
                        <p>Категория:</p>
                    </#if>
                </div>
                <div class="book_info_date">
                    <#if book.price?has_content>
                        <p>${book.price} руб.</p>
                    </#if>
                    <#if book.dateOfPublication?has_content>
                        <p>${book.getDateOfPublicationToString()} г.</p>
                    </#if>
                    <#if book.publishing?has_content>
                        <p>${book.getPublishingHouse()}</p>
                    </#if>
                    <#if book.countOfPage?has_content>
                        <p>${book.getCountOfPage()} с.</p>
                    </#if>
                    <#if book.categories?has_content>
                        <p>
                            <#list bookCategories as bookCategory>
                                ${bookCategory.name}<#if bookCategory?has_next>,</#if>
                            </#list>
                        </p>
                    </#if>
                </div>
            </div>
            <br>
            <div class="book_additional_info">
                <p>Описание:</p>
                <span>${book.getText()}</span>
            </div>
        </div>
    </div>
</@common.page>
