<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register New User</title>
    <meta name="layout" content="main"/>
</head>
<body>
    <h3>Register New User</h3>
    <g:hasErrors>
        <div class="errors">
            <g:renderErrors bean="${user}" as="list" />
        </div>
    </g:hasErrors>
    <g:if test="${flash.message}">
        <div class="error">${flash.message}</div>
    </g:if>
    <g:uploadForm action="confirmRegister">
        <fieldset class="form">
            <div class="fieldcontain required">
                <label for="loginId">Login ID</label>
                <g:textField name="loginId" value="${user?.loginId}" />
            </div>
            <div class="fieldcontain required">
                <label for="password">Password</label>
                <g:passwordField name="password"/>
            </div>
            <div class="fieldcontain required">
                <label for="passwordRepeat">Password (repeat)</label>
                <g:passwordField name="passwordRepeat"/>
            </div>
            <div class="fieldcontain required">
                <label for="photo">Photo</label>
                <input type="file" name="photo" />
            </div>
            <div class="fieldcontain required">
                <label for="fullName">Full Name</label>
                <g:textField name="fullName" value="${user?.fullName}" />
            </div>
            <div class="fieldcontain required">
                <label for="bio">Bio</label>
                <g:textArea name="bio" />
            </div>
            <div class="fieldcontain required">
                <label for="email">Email</label>
                <g:textField name="email" value="${user?.email}" />
                <g:hasErrors bean="${user}" field="email">
                    <g:eachError bean="${user}" field="email">
                        <p style="color: red;"><g:message error="${it}" /></p>
                    </g:eachError>
                </g:hasErrors>
            </div>
        </fieldset>
        <fieldset class="button">
            <g:submitButton name="register" value="Confirm" />
        </fieldset>
    </g:uploadForm>
</body>
</html>