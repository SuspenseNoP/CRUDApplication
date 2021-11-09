<#include "security.ftlh">
<#list messages as message>
    <div>
        <a href="/user-messages/${message.author.id}"> <strong>${message.authorName}</strong></a>
        <span>${message.text}</span><br/>
        <i>#${message.tag}</i>
        <#if message.author.id == currentUserId>
            <a class="btn btn-primary" href="/user-messages/${message.author.id}?message=${message.id}">
                Edit
            </a>
        </#if>
    </div>
<#else>
    No message
</#list >