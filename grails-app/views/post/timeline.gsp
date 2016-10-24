<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Timeline for ${user.profile ? user.profile.fullName : user.loginId}</title>
    <meta name="layout" content="main" />
    <g:if test="${user.profile?.skin}">
        <asset:stylesheet src="${user.profile.skin}.css" />
    </g:if>
    <g:javascript>
        function clearPost(e) {
            $('#postContent').val('');
        }
        function showSpinner(visible) {
            if (visible) $('#spinner').show();
            else $('#spinner').hide();
        }
        function addTinyUrl(data) {
            var tinyUrl = data.urls.small;
            var postBox = $("#postContent")
            postBox.val(postBox.val() + tinyUrl);
            toggleTinyUrl();
            $("#tinyUrl input[name='fullUrl']").val('');
        }
        function toggleTinyUrl() {
            var toggleText = $('#showHideUrl');
            if ($('#tinyUrl').is(':visible')) {
                $('#tinyUrl').slideUp(300);
                toggleText.innerText = 'Hide TinyURL';
            } else {
                $('#tinyUrl').slideDown(300);
                toggleText.innerText = 'Show TinyURL';
            }
        }
    </g:javascript>
</head>
<body>
    <h1>Timeline for ${user.profile ? user.profile.fullName : user.loginId}</h1>
    <g:if test="${flash.message}">
        <div class="flash">
            ${flash.message}
        </div>
    </g:if>
    <div id="newPost">
        <h3>What is ${user.profile ? user.profile.fullName : user.loginId} hacking on right now?</h3>
        <p>
            <g:form class="form" role="form" >
                <g:hiddenField name="id" value="${params.id}" />
                <div class="form-group">
                <g:textArea name="content" id="postContent" class="form-control" rows="3" cols="50" /><br />
                <g:submitToRemote
                        value="Post"
                        url="[controller: 'post', action: 'addPostAjax']"
                        update="allPosts"
                        onSuccess="clearPost(data)"
                        onLoading="showSpinner(true)"
                        onComplete="showSpinner(false)" class="btn btn-primary" />
                <asset:image src="spinner.gif" id="spinner" style="display: none;" />
                <a href="#" id="showHideUrl" class="btn btn-default" onclick="toggleTinyUrl(); return false;">
                    Show TinyUrl
                </a>
                </div>
            </g:form>
            <div id="tinyUrl" class="form-group" style="display: none;">
                <g:formRemote name="tinyUrlForm" url="[action: 'tinyUrl']" onSuccess="addTinyUrl(data);" class="form-inline">
                    <div class="form-group">
                    TinyUrl: <g:textField name="fullUrl" class="form-control" />
                    <g:submitButton name="submit" value="Make Tiny" class="form-control" />
                    </div>
                </g:formRemote>
            </div>
        </p>
    </div>
<div id="allPosts">
    <g:render template="postEntry" collection="${user.posts}" var="post" />
    </div>
</body>
</html>