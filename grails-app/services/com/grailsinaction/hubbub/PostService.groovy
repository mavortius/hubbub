package com.grailsinaction.hubbub

import grails.transaction.Transactional

class PostException extends RuntimeException {
    String message
    Post post
}

@Transactional
class PostService {
    Post createPost(String loginId, String content) {
        def user = User.findByLoginId(loginId)

        if(user) {
            def post = new Post(content: content)

            user.addToPosts(post)

            if(post.validate() && user.save()) {
                post
            } else {
                throw new PostException(
                        message: "Invalid or empty post",
                        post: post
                )
            }
        } else {
            throw new PostException(message: "Invalid user ID")
        }
    }
}