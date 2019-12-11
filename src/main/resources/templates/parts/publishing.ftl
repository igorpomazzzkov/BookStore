<#macro publish path isPublish>
    <#include "security.ftl">
    <link rel="stylesheet" href="/static/css/publishing.css" type="text/css">
    <div class="publishing_page">
        <div class="publishing_info">
            <table>
                <#if isPublish>
                    <tr>
                        <th>Наименование</th>
                        <th>Удалить</th>
                    </tr>
                    <#list publishing as publish>
                        <tr>
                            <td>${publish.name}</td>
                            <td align="center"><a href="/publish/deletePublish=${publish.id}"><font color="red">X</font></a></td>
                        </tr>
                    </#list>
                </#if>
                <#if !isPublish>
                    <tr>
                        <th>Фамилия</th>
                        <th>Имя</th>
                        <th>Дата рождения</th>
                        <th>Дата смерти</th>
                        <th>Удалить</th>
                    </tr>
                    <#list authors as author>
                        <tr>
                            <td>
                                ${author.lastName}
                            </td>
                            <td>
                                ${author.firstName}
                            </td>
                            <td>
                                <#if author.dateOfBirth?has_content>
                                    ${author.getDateOfBirthToString()}
                                    <#else>
                                    неизвестно
                                </#if>
                            </td>
                            <td>
                                <#if author.dateOfDeath?has_content>
                                    ${author.getDateOfDeathToString()}
                                <#else >

                                </#if>
                            </td>
                            <td align="center"><a href="${path}/delete=${author.id}"><font color="red">X</font></a></td>
                        </tr>
                    </#list>
                </#if>
            </table>
        </div>
        <div class="publishing_add">
            <form method="post" action="${path}/add">
                <#if isPublish>
                    <label for="name">Наименование издательсва:</label>
                    <label>
                        <input type="text" name="name" class="form-control ${(nameError??)?string('is-invalid','')}" placeholder="Издательство">
                        <#if nameError??>
                            <div class="invalid-feedback">
                                ${nameError}
                            </div>
                        </#if>
                    </label>
                    <#else >
                    <label for="lastName">Фамилия</label>
                        <label>
                            <input type="text" name="lastName" placeholder="Фамилия" class="form-control ${(lastNameError??)?string('is-invalid','')}"/>
                            <#if lastNameError??>
                                <div class="invalid-feedback">
                                    ${lastNameError}
                                </div>
                            </#if>
                        </label>
                        <label for="firstName">Имя</label>
                        <label>
                            <input type="text" name="firstName" placeholder="Имя" class="form-control ${(firstNameError??)?string('is-invalid','')}"/>
                            <#if firstNameError??>
                                <div class="invalid-feedback">
                                    ${firstNameError}
                                </div>
                            </#if>
                        </label>
                        <label for="dateOfBirth">Дата рождения</label>
                        <label>
                            <input type="date" name="dateOfBirth" class="form-control">
                        </label>
                        <label for="dateOfDeath">Дата смерти</label>
                        <label>
                            <input type="date" name="dateOfDeath" class="form-control">
                        </label>
                </#if>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input type="submit" class="btn btn-success" value="Добавить">
            </form>
        </div>
    </div>
</#macro>
