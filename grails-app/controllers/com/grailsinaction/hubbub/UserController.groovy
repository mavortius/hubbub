package com.grailsinaction.hubbub

import grails.plugin.springsecurity.annotation.Secured

@Secured('hasRole("ROLE_ADMIN")')
class UserController {

    static scaffold = User

    def springSecurityService

    def search() { }

    def results(String loginId) {
        def users = User.where {
            loginId =~ "%${loginId}%"
        }.list()

        [   users: users,
            term: params.loginId,
            totalUsers: User.count()    ]
    }

    def advancedSearch() { }

    def advancedResults() {
        def profileProps = Profile.metaClass.properties*.name
        def profiles = Profile.withCriteria {
            "${params.queryType}" {
                params.each { field, value ->
                    if(profileProps.grep(field) && value) {
                        ilike(field, value)
                    }
                }
            }
        }
        [ profiles:profiles ]
    }

    @Secured('permitAll')
    def register() { }

    @Secured('permitAll')
    def confirmRegister(UserRegistrationCommand urc) {
        if(urc.hasErrors()) {
            render view: "register", model: [user:urc]
        } else {
            def user = new User(urc.properties)
            user.passwordHash = springSecurityService.passwordEncoder ?
                                    springSecurityService.encodePassword(urc.password) :
                                        urc.password
            user.profile = new Profile(urc.properties)

            if(user.validate() && user.save()) {
                flash.message = "Welcome aboard, ${urc.fullName ?: urc.loginId}"

                redirect controller: "login", action: "auth"
            } else {
                // may not be unique loginId?
                [user: urc]
            }
        }
    }

    @Secured('isAuthenticated()')
    def profile() {
        def user = authenticatedUser

        if(user) {
            [profile:user.profile]
        } else {
            response.sendError(404)
        }
    }
}

class UserRegistrationCommand {
    String loginId
    String password
    String passwordRepeat
    byte[] photo
    String fullName
    String bio
    String homepage
    String email
    String timezone
    String country

    static constraints = {
        importFrom Profile
        importFrom User
        password size: 6..8, blank: false, validator: { passwd, urc ->
            return passwd != urc.loginId
        }
        passwordRepeat nullable: false, validator: { passwd2, urc ->
            return passwd2 == urc.password
        }
    }
}
