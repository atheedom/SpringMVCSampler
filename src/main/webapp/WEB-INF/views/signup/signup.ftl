<#-- @ftlvariable name="signupForm" type="com.springmvcsampler.web.form.SignupForm" -->
<!DOCTYPE html>
<html>
<#import "/spring.ftl" as spring />
<head>
    <title>Signup</title>
    <#include "../fragments/media.ftl">

</head>
<body>

<#include "../fragments/header.ftl">



<form class="form-narrow form-horizontal" method="post" action="<@spring.url '/signup'/>">
<@spring.bind "signupForm" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <!--/* Show general error message when form contains errors */-->
    <#--<th:block th:if="${#fields.hasErrors('${signupForm.*}')}">-->
        <#--<div th:replace="fragments/alert :: alert (type='danger', message='Form contains errors. Please try again.')">Alert</div>-->
    <#--</th:block>-->
    <fieldset>
        <legend>Please Sign Up</legend>
        <div class="form-group">
            <label for="email" class="col-lg-2 control-label">Email</label>
            <div class="col-lg-10">
                <#--<input type="text" class="form-control" id="email" placeholder="Email address" name="email" />-->
                <@spring.formInput 'signupForm.email', 'class="form-control" placeholder="Email address"' />
                <#--<@spring.showErrors ' ', 'has-error'/>-->
                <#--<#if fields.hasErrors('email')><span class="help-block">Incorrect email</span></#if>-->
            </div>
        </div>

        <div class="form-group">
            <label for="username" class="col-lg-2 control-label">Username</label>
            <div class="col-lg-10">
            <#--<input type="text" class="form-control" id="email" placeholder="Email address" name="email" />-->
                <@spring.formInput 'signupForm.username', 'class="form-control" placeholder="Username"' />
                <#--<@spring.showErrors ' ', 'has-error'/>-->
                <#--<#if fields.hasErrors('email')><span class="help-block">Incorrect email</span></#if>-->
            </div>
        </div>

        <div class="form-group">
            <label for="password" class="col-lg-2 control-label">Password</label>
            <div class="col-lg-10">
                <#--<input type="password" class="form-control" id="password" placeholder="Password" name="password"/>-->
                <@spring.formPasswordInput "signupForm.password", 'class="form-control" placeholder="Password"' />
                <#--<@spring.showErrors ' ', 'has-error'/>-->
            </div>
        </div>

        <div class="form-group">
            <label for="repeatPassword" class="col-lg-2 control-label">Repeat Password</label>
            <div class="col-lg-10">
            <#--<input type="password" class="form-control" id="password" placeholder="Password" name="password"/>-->
                <@spring.formPasswordInput "signupForm.passwordRepeated", 'class="form-control" placeholder="Repeat Password"' />
                <#--<@spring.showErrors ' ', 'has-error'/>-->
            </div>
        </div>

        <div class="form-group">
            <label for="role" class="col-lg-2 control-label">Role</label>
            <div class="col-lg-10">
                <select name="role" id="role" required>
                    <option <#if signupForm.role == 'ROLE_USER'>selected</#if>>ROLE_USER</option>
                    <option <#if signupForm.role == 'ROLE_ADMIN'>selected</#if>>ROLE_ADMIN</option>
                </select>
            </div>
        </div>


        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <button type="submit" class="btn btn-default">Sign up</button>
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <p>Already have an account? <a href="<@spring.url '/signin'/>">Sign In</a></p>
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