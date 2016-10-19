<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code='springSecurity.login.title'/></title>
    <meta name="layout" content="main">
</head>
<body>
<h3><g:message code='springSecurity.login.header'/></h3>
<g:if test="${flash.message}">
    <div class="errors">${flash.message}</div>
</g:if>
<form id="loginForm" action="${postUrl ?: '/login/authenticate'}" method="post">
    <fieldset class="form">
        <div class="fieldcontain required">
            <label for="username"><g:message code='springSecurity.login.username.label'/>:</label>
            <g:textField name="username" value="${loginId}" />
        </div>
        <div class="fieldcontain required">
            <label for="password"><g:message code='springSecurity.login.password.label'/>:</label>
            <g:passwordField name="password" />
        </div>
        <div class="fieldcontain required">
            <input type="checkbox" class="chk" name="${rememberMeParameter ?: 'remember-me'}" id="remember_me" <g:if test='${hasCookie}'>checked="checked"</g:if>/>
            <label for="remember_me"><g:message code='springSecurity.login.remember.me.label'/></label>
        </div>
    </fieldset>
    <fieldset class="buttons">
        <g:submitButton name="signIn" value="Sign in" />
    </fieldset>
</form>
<script>
    (function() {
        document.forms['loginForm'].elements['username'].focus();
    })();
</script>
</body>
</html>