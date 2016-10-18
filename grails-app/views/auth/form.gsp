<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
    <meta name="layout" content="main">
</head>
<body>
<g:form url="/login/authenticate" method="POST">
    <fieldset class="form">
        <div class="fieldcontain required">
            <label for="username">Logim ID</label>
            <g:textField name="username" value="${loginId}" />
        </div>
        <div class="fieldcontain required">
            <label for="password">Password</label>
            <g:passwordField name="password" />
        </div>
        <div class="fieldcontain required">
            <label for="remember-me">Remember me</label>
            <g:checkBox name="remember-me" />
        </div>
    </fieldset>
    <fieldset class="buttons">
        <g:submitButton name="signIn" value="Sign in" />
    </fieldset>
</g:form>
</body>
</html>