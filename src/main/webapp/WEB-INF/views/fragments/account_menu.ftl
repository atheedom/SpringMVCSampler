

<@security.authorize access="hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')">

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
                <li><a href="<@spring.url '/accounts/create'/>">Create Account</a></li>
                <li><a href="<@spring.url '/accounts'/>">List Accounts</a></li>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</div>

</@security.authorize>