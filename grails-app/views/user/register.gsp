<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register New User</title>
    <meta name="layout" content="main"/>
</head>
<body>
<g:uploadForm action="confirmRegister" class="form-horizontal" style="max-width: 530px;" role="form">
    <h2>Registration New User</h2>
    <g:hasErrors>
        <div class="alert alert-danger" role="alert">
            <g:renderErrors bean="${user}" as="list" />
        </div>
    </g:hasErrors>
    <g:if test="${flash.message}">
        <div class="alert alert-success" role="alert">${flash.message}</div>
    </g:if>
    <div class="form-group">
        <label for="loginId" class="col-sm-3 control-label">Login ID</label>
        <div class="col-sm-9">
            <input name="loginId"  value="${user?.loginId}" type="text" class="form-control" autofocus />
            <span class="help-block">Lower case characters without spaces, eg.: smithharry</span>
        </div>
    </div>
    <div class="form-group">
        <label for="fullName" class="col-sm-3 control-label">Full Name</label>
        <div class="col-sm-9">
            <input name="fullName"  value="${user?.fullName}" type="text" class="form-control" autofocus />
        </div>
    </div>
    <div class="form-group">
        <label for="email" class="col-sm-3 control-label">Email</label>
        <div class="col-sm-9">
            <input type="email" name="email" value="${user?.email}" class="form-control" />
        </div>
    </div>
    <div class="form-group">
        <label for="password" class="col-sm-3 control-label">Password</label>
        <div class="col-sm-9">
            <input type="password" name="password" class="form-control" />
        </div>
    </div>
    <div class="form-group">
        <label for="passwordRepeat" class="col-sm-3 control-label">Password (repeat)</label>
        <div class="col-sm-9">
            <input type="password" name="passwordRepeat" class="form-control" />
        </div>
    </div>
    <div class="form-group">
        <label for="photo" class="col-sm-3 control-label">Photo</label>
        <div class="col-sm-9">
            <input type="file" name="photo" />
        </div>
    </div>
    <div class="form-group">
        <label for="bio" class="col-sm-3 control-label">Bio</label>
        <div class="col-sm-9">
            <textarea name="bio" class="form-control input-sm" rows="2" ></textarea>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-9 col-sm-offset-3">
            <button type="submit" class="btn btn-primary btn-block">Register</button>
        </div>
    </div>
</g:uploadForm> <!-- /form -->
</body>
</html>