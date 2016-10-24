package com.grailsinaction.hubbub

import grails.plugin.springsecurity.annotation.Secured

class PhotoUploadCommand {
    byte[] photo
    String loginId
}

@Secured('hasRole("ROLE_ADMIN")')
class ImageController {
    def upload(PhotoUploadCommand puc) {
        def user = User.findByLoginId(puc.loginId)
        user.profile.photo = puc.photo

        redirect controller: "user", action: "profile", id: puc.loginId
    }

    def form() {
        // pass through to upload form
        [userList:User.list()]
    }

    @Secured('permitAll')
    def renderImage(String id) {
        def user = User.findByLoginId(id)

        if(user?.profile?.photo) {
            response.contentLength = user.profile.photo.size()
            response.outputStream.write(user.profile.photo)
        } else {
            response.sendError(404)
        }
    }
}
