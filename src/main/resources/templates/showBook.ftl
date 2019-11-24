<#import "parts/common.ftl" as common>
<#import "parts/account.ftl" as account>
<@common.page>
<head>
    <link rel="stylesheet" href="/static/css/book.css" type="text/css">
</head>
            <div class="book">
                <div class="img">
                    <#if book.filename??>
                        <img src="/img/${book.filename}" />
                    </#if>
                </div>
                <div class="date-book">
                    <div class="book_name"><h4>${book.name}</h4></div>
                    <div class="book_author"><b><i><a href="/book/author=${book.author.id}">${book.getAuthorName()}</a></i></b></div>
                    <hr>
                    <div class="book_info">
                        <div class="book_info_text">
                            <p>Цена:</p>
                            <p>Первое издание:</p>
                            <p>Издательство:</p>
                            <p>Количество страниц:</p>
                            <#if bookCategories?has_content>
                                <p>Категория:</p>
                            </#if>
                        </div>
                        <div class="book_info_date">
                            <p>${book.price} руб.</p>
                            <p>${book.getDateOfPublicationToString()} г.</p>
                            <p>${book.getPublishingHouse()}</p>
                            <p>${book.getCountOfPage()} с.</p>
                            <p>
                                <#list bookCategories as bookCategory>
                                        ${bookCategory.category.name},
                                </#list>
                            </p>
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
