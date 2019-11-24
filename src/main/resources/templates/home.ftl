<#import "parts/common.ftl" as common>
<#import "parts/pager.ftl" as pager>

<@common.page>
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
                    </div>
                </div>
            </#list>
        </div>
    <@pager.pager url books/>
</@common.page>
