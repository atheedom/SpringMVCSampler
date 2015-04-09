<!DOCTYPE html>
<html>
<#import "/spring.ftl" as spring />
<head>
    <title>Welcome to the JPM database project.</title>

    <#include "../fragments/media.ftl">

</head>
<body>
<#include "../fragments/header.ftl">
<div class="container">
    <div class="text-center">
        <h1>Test</h1>
        <p class="lead">
            Welcome to the JPM database project.
        </p>
        <p>
            <a href="<@spring.url '/signup'/>" class="btn btn-success btn-lg">Sign up</a>
        </p>
    </div>

<#include "../fragments/footer.ftl">

</div>
</body>
</html>