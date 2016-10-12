<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Timeline for ${user.profile ? user.profile.fullName : user.loginId}</title>
    <meta name="layout" content="main" />
</head>
<body>
    <h1>Timeline for ${user.profile ? user.profile.fullName : user.loginId}</h1>
    <div id="allPosts">
       <g:each var="post" in="${user.posts}">
           <div class="postEntry">
               ${post.content}
           </div>
           <div class="postDate">
               ${post.dateCreated}
           </div>
       </g:each>
    </div>
</body>
</html>