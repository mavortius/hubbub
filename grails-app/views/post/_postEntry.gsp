<div class="postEntry">
    <g:if test="${post.user.profile.photo}">
        <img src="${createLink(controller: 'image', action: 'renderImage', id: post.user.loginId)}" />
    </g:if>
    <p><strong>${post.user.profile.fullName}</strong></p>
    <div class="postText">${post.content}</div>
    <div class="postDate">
        <hub:dateFromNow date="${post.dateCreated}"/>
    </div>
</div>