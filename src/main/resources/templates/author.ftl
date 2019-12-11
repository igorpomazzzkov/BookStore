<#import "parts/common.ftl" as common>
<#import "parts/publishing.ftl" as publish>

<@common.page>
    <div class="alert alert-danger" role="alert" style="overflow-y: hidden">
        <p>В случаи удаления автора, его книги также будут удалены</p>
    </div>
    <@publish.publish "/author" false/>
</@common.page>