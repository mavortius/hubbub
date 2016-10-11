package com.grailsinaction.hubbub

class PostController {
    static scaffold = Post

    def timeline() {
        def user = User.findByLoginId(params.id)

        if(user) {
            [user:user]
        } else {
            response.sendError(404)
        }
    }
}
