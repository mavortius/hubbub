package com.grailsinaction.hubbub

class PostController {

    static scaffold = Post

    def index() {
        def post = new Post(content: "test")
        def posts = Post.findAll(post, max: 5)
    }

}
