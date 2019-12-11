<#import "parts/common.ftl" as common>
<#import "parts/publishing.ftl" as publish>

<@common.page>
    <div class="alert alert-danger" role="alert" style="overflow-y: hidden">
        <p>Все книги удаляемого издательство автоматически будут удалены</p>
    </div>
    <@publish.publish "/publish" true/>
</@common.page>