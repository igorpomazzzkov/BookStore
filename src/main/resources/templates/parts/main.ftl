<#import "pager.ftl" as pager>
<#macro home path isCartForm>
<#include "security.ftl">


<#if books.content?has_content>
    <div>
        <#list books.content as book>
            <div class="book">
                <div class="img">
                    <#if book.filename??>
                        <img src="/img/${book.filename}" />
                    </#if>
                </div>
                <div class="date-book">
                    <div class="book_name"><h4><a href="/book/book=${book.id}">${book.name}</a></h4></div>
                    <div class="book_author"><b><i><a href="/book/author=${book.author.id}">${book.getAuthorName()}</a></i></b></div>
                    <hr>
                    <div class="book_info">
                        <div class="book_info_text">
                            <p>Цена:</p>
                            <p>Первое издание:</p>
                            <p>Издательство:</p>
                        </div>
                        <div class="book_info_date">
                            <p>${book.price} руб.</p>
                            <p>${book.getDateOfPublicationToString()}г.</p>
                            <p>${book.getPublishingHouse()}</p>
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
                            <input type="submit" class="btn btn-info" value="Добавить в корзину">
                        </form>
                    </#if>
                </div>
            </div>
        </#list>
        <@pager.pager url books/>
    </div>
<#else>
    <div class="empty_cart">
        <a href="/book"><h2>Корзина пуста</h2></a>
    </div>
</#if>
</#macro>