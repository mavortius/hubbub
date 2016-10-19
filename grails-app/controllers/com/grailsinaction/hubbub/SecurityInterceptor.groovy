package com.grailsinaction.hubbub


class SecurityInterceptor {
    def springSecurityService

    SecurityInterceptor() {
        match(controller: "profile", action: ~/(edit|update)/)
    }

    boolean before() {
        def curLoginId = springSecurityService.currentUser.loginId

        if(curLoginId != Profile.get(params.id).user.loginId) {
            redirect controller: "login", action: "denied"
            false
        } else {
            true
        }
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
