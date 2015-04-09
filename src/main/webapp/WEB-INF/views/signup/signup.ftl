<!DOCTYPE html>
<html>
<#import "/spring.ftl" as spring />
<head>
    <title>Signup</title>
    <#include "../fragments/media.ftl">

</head>
<body>

<#include "../fragments/header.ftl">

<@spring.bind "signupForm" />

<form class="form-narrow form-horizontal" method="post" action="<@spring.url '/signup'/>">
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
            <label for="password" class="col-lg-2 control-label">Password</label>
            <div class="col-lg-10">
                <#--<input type="password" class="form-control" id="password" placeholder="Password" name="password"/>-->
                <@spring.formPasswordInput "signupForm.password", 'class="form-control" placeholder="Password"' />
                <#--<@spring.showErrors ' ', 'has-error'/>-->
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <button type="submit" class="btn btn-default">Sign up</button>
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <p>Already have an account? <a href="signin">Sign In</a></p>
            </div>
        </div>
    </fieldset>
</form>

<#include "../fragments/footer.ftl">


</body>
</html>