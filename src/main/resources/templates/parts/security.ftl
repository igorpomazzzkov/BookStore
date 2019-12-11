<#assign known = Session.SPRING_SECURITY_CONTEXT??>
<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getFirstName()
        username = user.getUsername()
        userID = user.getId()
        isAdmin = user.isAdmin()
        isEditor = user.isEditor()
    >
    <#else >
    <#assign
        name = "NoName"
        userID = -1
        isAdmin = false
        isEditor = false
    >
</#if>