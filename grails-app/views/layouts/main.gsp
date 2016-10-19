<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>Hubbub &raquo;
        <g:layoutTitle default="Welcome"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>
    <g:layoutHead/>
</head>

<body>
<div>
    <div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
        <div class="navbar-inner">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/">
                        <asset:image src="headerlogo.png" alt="hubbub logo"/>
                    </a>
                </div>
                <div class="navbar-collapse collapse" role="navigation" aria-expanded="false" >
                    <ul class="nav navbar-nav">
                        <sec:ifLoggedIn>
                        <li>
                            <g:link uri="/timeline" >My Timeline</g:link>
                        </li>
                        </sec:ifLoggedIn>
                        <li>
                            <g:link controller="post" action="global">Global Timeline</g:link>
                        </li>
                    </ul>
                    <sec:ifNotLoggedIn>
                        <ul class="nav navbar-nav navbar-right">
                            <li>
                                <g:link controller="user" action="register">Register</g:link>
                            </li>
                            <li>
                                <g:link controller="auth" action="form">Login</g:link>
                            </li>
                        </ul>
                    </sec:ifNotLoggedIn>
                    <sec:ifLoggedIn>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                    Hello, <hub:loggedUsername />
                                    <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <g:link controller="user" action="profile">
                                            <i class="fa fa-user"></i>
                                            Profile
                                        </g:link>
                                    </li>
                                    <li class="divider">
                                        <a></a>
                                    </li>
                                    <li>
                                        <g:link url="/logoff">
                                            <i class="glyphicon glyphicon-log-out"></i>
                                            Exit
                                        </g:link>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </sec:ifLoggedIn>
                </div>
            </div>
        </div>
    </div>
    <div id="bd" class="container"><!-- start body -->
    <g:layoutBody/>
    </div>  <!-- end body -->
    <div id="ft">
        <div id="footerText">Hubbub - Social Networking on Grails</div>
        Version <g:meta name="app.version"/>
        on Grails <g:meta name="app.grails.version"/>
    </div>
</div>
<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>
<asset:javascript src="application.js"/>

</body>
</html>
