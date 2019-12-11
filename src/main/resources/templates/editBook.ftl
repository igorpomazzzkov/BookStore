<#import "parts/common.ftl" as commom>

<@commom.page>
    <link rel="stylesheet" type="text/css" href="/static/css/editBook.css">
    <div class="editBook_page">
        <form method="post" action="/addBook/id=${book.id}">
            <div class="left_block">
                <label for="name">Наименование книги:</label>
                <label>
                    <input type="text" name="name" class="form-control" value="${book.name}">
                </label>
                <label for="author">Автор:</label>
                <label>
                    <select class="form-control" name="author">
                        <option aria-checked="true" value="${book.author.id}">${book.author.firstName} ${book.author.lastName}</option>
                        <#list authors as author>
                            <#if author.firstName != book.author.firstName &&
                            author.lastName != book.author.firstName>
                                <option name="${author.id}" value="${author.id}">
                                    ${author.firstName} ${author.lastName}
                                </option>
                            </#if>
                        </#list>
                    </select>
                </label>
                <label for="publishing">Издательство:</label>
                <label>
                    <select class="form-control" name="publishing">
                        <option aria-checked="true">${book.publishing.name}</option>
                        <#list publishing as publish>
                            <#if publish.name != book.publishing.name>
                                <option>${publish.name}</option>
                            </#if>
                        </#list>
                    </select>
                </label>
                <label for="price">Цена:</label>
                <label>
                    <input type="number" name="price" class="form-control" value="${book.price}"/>
                </label>
                <label for="pages">Количество страниц:</label>
                <label>
                    <input type="number" name="pages" class="form-control" value="${book.countOfPage}"/>
                </label>
            </div>
            <div class="right_block">
                <label for="description">Краткое описание:</label>
                <label>
                    <textarea rows="10" class="form-control" name="text">${book.text}</textarea>
                </label>
                <label for="category">Категории:</label>
                <ul class="li_category">
                    <#list book.getCategories() as category >
                        <li class="category">
                            ${category.name}
                            <a href="/deleteCategory=${category.name}byBook=${book.id}" class="close">&times</a>
                        </li>
                    </#list>
                </ul>
                <label>
                    <select class="form-control" name="category">
                        <option></option>
                        <#list categories as category>
                            <option>${category.name}</option>
                        </#list>
                    </select>
                </label>
                <input type="hidden" value="${_csrf.token}" name="_csrf">
                <input type="submit" class="btn btn-info" value="Сохранить"/>
            </div>
        </form>
    </div>
</@commom.page>