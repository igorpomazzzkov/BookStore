<#import "pager.ftl" as pager>
<#macro home path isCartForm isSearch>
    <#include "security.ftl">

    <#if isSearch>
        <div class="alert alert-${messageType}" role="alert">
            <p>${message}</p>
        </div>
    </#if>
    <#if books.content?has_content>
        <div>
            <#list books.content as book>
                <div class="book">
                    <div class="img">
                        <#if book.filename?has_content>
                            <img src="/img/${book.filename}"/>
                        <#else >
                            <img src="/img/0.png">
                        </#if>
                    </div>
                    <div class="date-book">
                        <div class="book_name">
                            <#if book.name?has_content>
                                <h4><a href="/book/book=${book.id}">${book.name}</a></h4>
                            </#if>
                            <#if isEditor>
                                <a href="/bookDelete=${book.id}" class="close" data-toggle="tooltip"
                                   data-placement="right" title="Удалить книгу">&times</a>
                            </#if>
                        </div>
                        <div class="book_author"><b><i><a
                                            href="/book/author=${book.author.id}">${book.getAuthorName()}</a></i></b>
                        </div>
                        <hr>
                        <div class="book_info">
                            <div class="book_info_text">
                                <p>Цена:</p>
                                <#if book.dateOfPublication?has_content>
                                    <p>Первое издание:</p>
                                </#if>
                                <p>Издательство:</p>
                                <#if book.getCategories()?has_content>
                                    <p>Категория:</p>
                                </#if>
                            </div>
                            <div class="book_info_date">
                                <#if book.price?has_content>
                                    <p>${book.price} руб.</p>
                                </#if>
                                <#if book.dateOfPublication?has_content>
                                    <p>${book.getDateOfPublicationToString()}г.</p>
                                </#if>
                                <#if book.getPublishingHouse()?has_content>
                                    <p>${book.getPublishingHouse()}</p>
                                </#if>
                                <p>
                                    <#if book.getCategories()?has_content>
                                        <#list book.getCategories() as bookCategory>
                                            ${bookCategory.name}<#if bookCategory?has_next>,</#if>
                                        </#list>
                                    </#if>
                                </p>
                            </div>
                        </div>
                        <#if isCartForm>
                            <form method="post" action="${path}/${book.id}">
                                <input type="hidden" value="${_csrf.token}" name="_csrf">
                                <input type="submit" class="btn btn-danger" value="Удалить из корзины">
                            </form>
                        <#else>
                            <form method="post" action="${path}/${book.id}">
                                <input type="hidden" value="${_csrf.token}" name="_csrf">
                                <#if isEditor>
                                    <a href="/bookEdit/id=${book.id}" class="btn btn-info">Редактировать</a>
                                <#else >
                                    <input type="submit" class="btn btn-primary" value="Добавить в корзину"/>
                                </#if>
                            </form>
                        </#if>
                    </div>
                </div>
            </#list>
            <@pager.pager url books/>
        </div>
    <#elseif isCartForm>
        <div class="empty_cart">
            <a href="/book"><h2>Корзина пуста</h2></a>
        </div>
    </#if>
</#macro>