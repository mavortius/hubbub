<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code='springSecurity.login.title'/></title>
    <meta name="layout" content="main">
</head>
<body>
<h3></h3>
<g:if test="${flash.message}">
    <div class="alert alert-danger" role="alert">${flash.message}</div>
</g:if>
<div class="row">
    <div class="col-sm-6 col-md-4 col-md-offset-4">
        <h1 class="text-center login-title"><g:message code='springSecurity.login.header'/></h1>
        <div class="account-wall">
            <form id="loginForm" action="${postUrl ?: '/login/authenticate'}" method="post" class="form-signin">
                <input name="username" value="${loginId}" type="text" class="form-control" placeholder="Username" required autofocus>
                <input name="password" type="password" class="form-control" placeholder="Password" required>
                <button class="btn btn-primary btn-block" type="submit">
                    <span class="glyphicon glyphicon-log-in" aria-hidden="true"> Sign in</span></button>
                <label class="checkbox pull-left">
                    <input type="checkbox" value="remember-me" name="${rememberMeParameter ?: 'remember-me'}" id="remember_me" <g:if test='${hasCookie}'>checked="checked"</g:if>/>
                    Remember me
                </label>
                <g:link controller="user" action="register" class="pull-right need-help">Not a user? Register</g:link><span class="clearfix"></span>
            </form>
        </div>
    </div>
</div>
</body>
</html>