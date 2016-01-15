<#-- @ftlvariable name="accounts" type="java.util.List<com.springmvcsampler.model.User>" -->

<!DOCTYPE html>
<html>
<#import "/spring.ftl" as spring />
<head>
    <title>Create Company</title>
<#include "../fragments/media.ftl">

</head>
<body>

<#include "../fragments/header.ftl">

<#include "../fragments/account_menu.ftl">

<h1>Company details</h1>

<form class="form-narrow form-horizontal" method="post" action="<@spring.url '/company/create'/>">
<@spring.bind "company" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <table>
        <tr>
            <td>
                Name: <@spring.formInput 'company.name', 'class="form-control"' />
            <@spring.showErrors "<br>" />
            </td>
        </tr>

        <tr>
            <td>
                employee name:

            <input id="employees[0].name"
                       name="employees[0].name"
                       type='text'
                       value=''>

            <#--<@spring.formInput 'company.employee[0].name', 'class="form-control"'/>-->
            <@spring.showErrors "<br>" />
            </td>
        </tr>

        <tr>
            <td>
                employee address:

                <input id="employees[0].address"
                       name="employees[0].address"
                       type='text'
                       value=''>

            <#--<@spring.formInput 'company.employee[0].address', 'class="form-control"'/>-->
            <@spring.showErrors "<br>" />
            </td>
        </tr>


        <button type=""submit">submit</button>

    </table>
</form>
</html>