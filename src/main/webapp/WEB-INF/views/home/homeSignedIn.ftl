<!DOCTYPE html>
<html>
<#import "/spring.ftl" as spring />
<head>
    <#--<title th:text="#{view.index.title}">Welcome!</title>-->
    <#include "../fragments/media.ftl">

</head>
<body>
<#include "../fragments/header.ftl">
<div class="container">

    signed in

    <!-- /* Handle the flash message */-->
    <#--<th:block th:if="${message != null}">-->
        <!-- /* The message code is returned from the @Controller */ -->
        <#--<div th:replace="fragments/alert :: alert (type=${#strings.toLowerCase(message.type)}, message=#{${message.message}(${#authentication.name})})">&nbsp;</div>-->
    <#--</th:block>-->
    <p>
        Welcome to the Spring MVC Sampler Application!
    </p>




<@security.authentication "principal.username" />





</div>

<#include "../fragments/footer.ftl">

</body>
</html>