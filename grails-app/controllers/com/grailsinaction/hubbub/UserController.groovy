package com.grailsinaction.hubbub

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

    def register() { }

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
                redirect uri: '/'
            } else {
                // may not be unique loginId?
                [user: urc]
            }
        }
    }

    def profile() {
        def user = springSecurityService.currentUser

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
