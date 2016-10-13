package com.grailsinaction.hubbub

class PostController {
    static scaffold = Post
    static defaultAction = "home"

    def postService

    def home() {
        if (!params.id) {
            params.id = "chuck_norris"
        }
        redirect action: 'timeline', params: params
    }

    def timeline(String id) {
        def user = User.findByLoginId(id)

        if(user) {
            [user:user]
        } else {
            response.sendError(404)
        }
    }

    def addPost(String id, String content) {
        try {
            def newPost = postService.createPost(id, content)
            flash.message = "Added new post: ${newPost.content}"
        } catch (PostException e) {
            flash.message = e.message
        }
        redirect action: 'timeline', id: id
    }

    def personal() {
        if(!session.user) {
            redirect controller: 'login', action: 'form'
            return
        } else {
            // Need to reattach the user domain object to the session using
            // the refresh() method.
            render view: 'timeline', model: [user: session.user.refresh()]
        }
    }

    def global() {
        def offset = params.offset ?: 0
        def max = params.max ?: 5
        [posts: Post.list(offset: offset, max: max), postCount: Post.count()]
    }
}
