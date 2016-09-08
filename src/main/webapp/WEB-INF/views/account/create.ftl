<#-- @ftlvariable name="accountCreateForm" type="com.springmvcsampler.web.form.AccountCreateForm" -->
<!DOCTYPE html>
<html>
<#import "/spring.ftl" as spring />
<head>
    <title>Signup</title>
    <#include "../fragments/media.ftl">

</head>
<body>

<#include "../fragments/header.ftl">


<form class="form-narrow form-horizontal" method="post" action="<@spring.url '/accounts/create'/>">
<@spring.bind "accountCreateForm" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <!--/* Show general error message when form contains errors */-->
    <#--<th:block th:if="${#fields.hasErrors('${accountCreateForm.*}')}">-->
        <#--<div th:replace="fragments/alert :: alert (type='danger', message='Form contains errors. Please try again.')">Alert</div>-->
    <#--</th:block>-->
    <fieldset>
        <legend>Please Sign Up</legend>
        <div class="form-group">
            <label for="email" class="col-lg-2 control-label">Email</label>
            <div class="col-lg-10">
                <@spring.formInput 'accountCreateForm.email', 'class="form-control" placeholder="Email address"' />
                <@spring.showErrors "<br>" />
                <#--<#if fields.hasErrors('email')><span class="help-block">Incorrect email</span></#if>-->
            </div>
        </div>

        <div class="form-group">
            <label for="username" class="col-lg-2 control-label">Username</label>
            <div class="col-lg-10">
                <@spring.formInput 'accountCreateForm.username', 'class="form-control" placeholder="Username"' />
                <@spring.showErrors "<br>" />
            </div>
        </div>

        <div class="form-group">
            <label for="password" class="col-lg-2 control-label">Password</label>
            <div class="col-lg-10">
                <@spring.formPasswordInput "accountCreateForm.password", 'class="form-control" placeholder="Password"' />
                <@spring.showErrors "<br>" />
            </div>
        </div>

        <div class="form-group">
            <label for="repeatPassword" class="col-lg-2 control-label">Repeat Password</label>
            <div class="col-lg-10">
                <@spring.formPasswordInput "accountCreateForm.passwordRepeated", 'class="form-control" placeholder="Repeat Password"' />
                <@spring.showErrors "<br>" />
            </div>
        </div>

        <div class="form-group">
            <label for="role" class="col-lg-2 control-label">Role</label>
            <div class="col-lg-10">
                <select name="role" id="role" required>
                    <option <#if accountCreateForm.role == 'ROLE_USER'>selected</#if>>ROLE_USER</option>
                    <option <#if accountCreateForm.role == 'ROLE_ADMIN'>selected</#if>>ROLE_ADMIN</option>
                    <@spring.showErrors "<br>" />
                </select>
            </div>
        </div>

        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <button type="submit" class="btn btn-default">Create Account</button>
            </div>
        </div>
    </fieldset>
</form>

<#if spring.status.error>
<ul>
    <#list spring.status.errorMessages as error>
        <li>${error}</li>
    </#list>
</ul>
</#if>


<#include "../fragments/footer.ftl">


</body>
</html>