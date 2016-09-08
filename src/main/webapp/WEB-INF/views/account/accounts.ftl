<#-- @ftlvariable name="accounts" type="java.util.List<com.springmvcsampler.model.User>" -->

<!DOCTYPE html>
<html>
<#import "/spring.ftl" as spring />
<head>
    <title>List of Accounts</title>
<#include "../fragments/media.ftl">

</head>
<body>

<#include "../fragments/header.ftl">

<#include "../fragments/account_menu.ftl">

<h1>List of Accounts</h1>

<table>
    <thead>
    <tr>
        <th>E-mail</th>
        <th>Role</th>
    </tr>
    </thead>
    <tbody>
    <#list accounts as account>
    <tr>
        <td><a href="<@spring.url '/accounts/${account.id}'/>">${account.email}</a></td>
        <td>${account.role}</td>
    </tr>
    </#list>
    </tbody>
</table>
</>
</html>