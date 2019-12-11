<#import "parts/common.ftl" as commom>

<@commom.page>
    <link rel="stylesheet" type="text/css" href="/static/css/editBook.css">
    <div class="editBook_page">
        <form method="post" action="/bookSave" enctype="multipart/form-data">
            <div class="left_block">
                <label for="name">Наименование книги:</label>
                <label>
                    <input type="text" name="name" class="form-control">
                </label>
                <label for="author">Автор:</label>
                <label>
                    <select class="form-control" name="author">
                        <#list authors as author>
                            <option name="${author.id}" value="${author.id}">
                                ${author.firstName} ${author.lastName}
                            </option>
                        </#list>
                    </select>
                </label>
                <label for="publishing">Издательство:</label>
                <label>
                    <select class="form-control" name="publishing">
                        <#list publishing as publish>
                            <option>${publish.name}</option>
                        </#list>
                    </select>
                </label>
                <label for="price">Цена:</label>
                <label>
                    <input type="number" step="0.01" name="price" class="form-control"/>
                </label>
                <label for="pages">Количество страниц:</label>
                <label>
                    <input type="number" name="pages" class="form-control"/>
                </label>
                <label for="dateOfPublication">Дата публикации:</label>
                <label>
                    <input type="date" class="form-control" name="dateOfPublication"/>
                </label>
                <input type="file" name="filename" value="Выбрать обложку"/>
            </div>
            <div class="right_block">
                <label for="description">Краткое описание:</label>
                <label>
                    <textarea rows="12" class="form-control" name="text"></textarea>
                </label>
                <label for="category">Категории:</label>
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