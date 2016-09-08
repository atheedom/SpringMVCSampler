<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Spring MVC Sampler Application</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">

            <li>
            <@security.authorize access="hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')">
                    <a href="" onclick="$('#formLogout').submit();">log out</a>
                    <form style="visibility: hidden" id="formLogout" method="post" action="<@spring.url '/logout'/>">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
            </@security.authorize>

                <@security.authorize access="hasRole('ROLE_ANONYMOUS')">
                    <a href="<@spring.url '/login'/>" onclick="$('#formLogin').submit();">log in</a>
                    <form style="visibility: hidden" id="formLogin" method="post" action="#"></form>
                </@security.authorize>

                </li>

            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</div>
