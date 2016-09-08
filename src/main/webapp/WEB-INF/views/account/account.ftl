<#-- @ftlvariable name="accounts" type="java.util.List<com.springmvcsampler.model.User>" -->

<!DOCTYPE html>
<html>
<#import "/spring.ftl" as spring />
<head>
    <title>Account details</title>
<#include "../fragments/media.ftl">

</head>
<body>

<#include "../fragments/header.ftl">

<#include "../fragments/account_menu.ftl">

<h1>Account details</h1>

<table>
    <thead>
    <tr>
        <th>E-mail</th>
        <th>Role</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><p>E-mail: ${account.email}</p>

            <p>Role: ${account.role}</p></td>
    </tr>
    </tbody>
</table>
</>
</html>