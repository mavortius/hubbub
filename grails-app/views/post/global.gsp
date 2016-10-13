<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Global Timeline</title>
    <meta name="layout" content="main"/>
</head>
<body>
    <h1>Global Timeline</h1>
    <g:if test="${flash.message}">
        <div class="flash">
            ${flash.message}
        </div>
    </g:if>
    <div id="allPosts">
        <g:each var="post" in="${posts}">
            <div class="postEntry">
                ${post.content}
            </div>
            <div class="postDate">
                ${post.dateCreated}
            </div>
        </g:each>
    </div>
    <div class="pagination" style="text-align: center;">
        <g:paginate total="${postCount}" max="5" />
    </div>
</body>
</html>