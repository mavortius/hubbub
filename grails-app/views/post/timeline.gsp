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
            // toggleTinyUrl();
            $("#tinyUrl input[name='fullUrl']").val('');
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
            <g:form >
                <g:hiddenField name="id" value="${params.id}" />
                <g:textArea name="content" id="postContent" rows="3" cols="50" /><br />
                <g:submitToRemote
                        value="Post"
                        url="[controller: 'post', action: 'addPostAjax']"
                        update="allPosts"
                        onSuccess="clearPost(data)"
                        onLoading="showSpinner(true)"
                        onComplete="showSpinner(false)" />
                <asset:image src="spinner.gif" id="spinner" style="display: none;" />
                <a href="#" id="showHideUrl" onclick="$('#tinyUrl').slideToggle(300); return false;">
                    Show TinyUrl
                </a>
            </g:form>
            <div id="tinyUrl" style="display: none;">
                <g:formRemote name="tinyUrlForm" url="[action: 'tinyUrl']" onSuccess="addTinyUrl(data);">
                    TinyUrl: <g:textField name="fullUrl" />
                    <g:submitButton name="submit" value="Make Tiny" />
                </g:formRemote>
            </div>
        </p>
    </div>
<div id="allPosts">
    <g:render template="postEntry" collection="${user.posts}" var="post" />
    </div>
</body>
</html>