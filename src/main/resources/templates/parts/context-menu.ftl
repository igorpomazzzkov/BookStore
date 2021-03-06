<#macro context>
    <#include "security.ftl">
    <link rel="stylesheet" type="text/css" href="/static/css/context.css">
    <div class="context-page">
        <#if isEditor>
            <div class="isEditor-block">
                <ul>
                    <li><a href="/addBook">Добавить книгу</a></li>
                    <li><a href="/author">Авторы</a></li>
                    <li><a href="/publish">Издательства</a></li>
                </ul>
            </div>
        <#elseif categories?has_content>
        <form method="post" class="form-control" action="/search">
            <label for="nameOfBook">Название книги:</label>
            <input type="text" name="nameOfBook" class="form-control">
            <hr>
            <label for="nameOfAuthor">Автор:</label>
            <input type="text" name="nameOfAuthor" class="form-control">
            <hr>
            <label for="priceMin">Цена:</label>
            <div class="block-price">
                <div class="block-price-info">
                    <p>От:</p>
                    <p>До:</p>
                </div>
                <div class="block-price-input">
                    <input type="number" name="priceMin" class="form-control">
                    <input type="number" name="priceMax" class="form-control">
                </div>
            </div>
            <hr>
            <label for="category">Жанр:</label>
            <select class="form-control" id="exampleFormControlSelect1" name="genre">
                <option aria-checked="true"></option>
                <#list categories as category>
                    <option>${category.name}</option>
                </#list>
            </select>
            <hr>
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <input type="submit" class="btn btn-dark" value="Фильтр"/>
        </form>
            <#else >
            <div class="advertising">
                <p>Место для рекламы</p>
            </div>
        </#if>
    </div>
</#macro>