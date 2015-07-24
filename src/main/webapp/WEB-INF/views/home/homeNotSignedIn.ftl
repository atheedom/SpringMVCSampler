<!DOCTYPE html>
<html>
<#import "/spring.ftl" as spring />
<head>
    <title>Welcome to the Spring MVC Sampler Application.</title>

    <#include "../fragments/media.ftl">

</head>
<body>
<#include "../fragments/header.ftl">
<div class="container">
    <div class="text-center">
        <h1>Test</h1>
        <p class="lead">
            Welcome to the Spring MVC Sampler Application.
        </p>
        <p>
            <a href="<@spring.url '/signup'/>" class="btn btn-success btn-lg">Sign up</a>
        </p>
    </div>


    <div class="text-center">

        <h1>Freemarker Snippets</h1>

        <a href="freemarker/formelements">Form Elements</a>




    </div>

<#include "../fragments/footer.ftl">

</div>
</body>
</html>