<#macro context>
    <#include "security.ftl">
    <link rel="stylesheet" type="text/css" href="/static/css/context.css">
    <div class="context-page">
        <#if categories?has_content>
        <form method="post" class="form-control">
            <label for="nameOfBook">Название книги:</label>
            <input type="text" name="nameOfBook" class="form-control">
            <hr>
            <label for="nameOfAuthor">Автор:</label>
            <input type="text" name="nameOfAuthor" class="form-control">
            <hr>
            <label for="priceMin">Цена:</label>
            <div class="block-price">
                <input type="number" name="priceMin" class="form-control">
                <input type="number" name="priceMax" class="form-control">
            </div>
            <hr>
            <label for="category">Жанр:</label>
            <select class="form-control" id="exampleFormControlSelect1">
                <option aria-checked="true">---</option>
                <#list categories as category>
                    <option>${category.name}</option>
                </#list>
            </select>
            <hr>
            <input type="submit" class="btn btn-dark" value="Фильтр"/>
        </form>
            <#else >
            <div class="advertising">
                <p>Место для рекламы</p>
            </div>
        </#if>
    </div>
</#macro>