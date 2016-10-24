package com.grailsinaction.hubbub

import grails.plugin.springsecurity.annotation.Secured

@Secured('isAuthenticated()')
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

    def timeline() {
        def user = authenticatedUser

        if(user) {
            [user:user]
        } else {
            response.sendError(404)
        }
    }

    def addPost(String content) {
        try {
            def user = authenticatedUser
            def newPost = postService.createPost(user.loginId, content)
            flash.message = "Added new post: ${newPost.content}"
        } catch (PostException e) {
            flash.message = e.message
        }
        redirect action: 'timeline', id: user.loginId
    }

    def addPostAjax(String content) {
        try {
            def user = authenticatedUser
            def newPost = postService.createPost(user.loginId, content)
            def recentPosts = Post.findAllByUser(user, [sort: 'dateCreated', order: 'desc', max: 20])

            render template: 'postEntry', collection: recentPosts, var: 'post'
        } catch (PostException e) {
            render {
                div class: "errors", e.message
            }
        }
    }

    def personal() {
        def user = authenticatedUser

        render view: 'timeline', model: [user: user]
    }

    @Secured('permitAll')
    def global() {
        def offset = params.offset ?: 0
        def max = params.max ?: 5
        [posts: Post.list(offset: offset, max: max, sort: 'dateCreated', order: 'desc'), postCount: Post.count(), max: max]
    }

    def tinyUrl(String fullUrl) {
        def origUrl = fullUrl?.encodeAsURL()
        def tinyUrl = new URL("http://tinyurl.com/api-create.php?url=${origUrl}").text

        render(contentType:"application/json") {
            urls(small: tinyUrl, full:fullUrl)
        }
    }
}
