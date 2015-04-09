<!DOCTYPE html>
<html>
<#import "/spring.ftl" as spring />
<head>
    <title>Sign In</title>
<#include "../fragments/media.ftl">
</head>

<#include "../fragments/header.ftl">
<body>

<form role="form" action="<@spring.url '/login'/>" method="post" class="form-narrow form-horizontal">

    <fieldset>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <legend>Please Login</legend>

        <div class="form-group">
            <label for="inputEmail" class="col-lg-2 control-label">Username</label>
            <div class="col-lg-10">
                <input type="text" class="form-control" id="inputUsername" placeholder="username" name="username" required autofocus/>
            </div>
        </div>

        <div class="form-group">
            <label for="inputPassword" class="col-lg-2 control-label">Password</label>
            <div class="col-lg-10">
                <input type="password" class="form-control" id="inputPassword" placeholder="Password" name="password" />
            </div>
        </div>

        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="_spring_security_remember_me" /> Remember me
                    </label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <button type="submit" class="btn btn-default">Login</button>
            </div>
        </div>

        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <p>New here? <a href="<@spring.url '/signup'/>">Sign Up</a></p>
            </div>
        </div>

    </fieldset>
</form>

<#if error.isPresent()>
<p>The username or password you have entered is invalid, try again.</p>
</#if>
</body>
</html>