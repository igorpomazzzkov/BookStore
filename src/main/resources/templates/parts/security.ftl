<#assign known = Session.SPRING_SECURITY_CONTEXT??>
<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getFirstName()
        username = user.getUsername()
        isAdmin = user.isAdmin()>
    <#else >
    <#assign
        name = "unkown"
        isAdmin = false>
</#if>