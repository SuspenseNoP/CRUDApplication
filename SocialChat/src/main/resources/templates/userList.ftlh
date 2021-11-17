<#import "parts/common.ftlh" as c>

<@c.page>
List of users
<table>
    <thead>
    <tr>
        <th>Username</th>
        <th>Role</th>
        <th>Email</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list users as user>
        <tr>
            <td>${user.username}</td>
            <td>
                <#list user.roles as role>${role}<#sep>, </#list>
            </td>
            <td>${user.email?ifExists}</td>
            <td><a href="/user/edit/${user.id}">Edit</a> </td>
            <td><a href="/user/delete/${user.id}">Delete</a> </td>
            <td><a href="/user/info/${user.id}">Info</a> </td>
        </tr>

    </#list>
    </tbody>
</table>
</@c.page>