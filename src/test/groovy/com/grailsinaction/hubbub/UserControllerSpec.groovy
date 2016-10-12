package com.grailsinaction.hubbub

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(UserController)
@Mock([User, Profile])
class UserControllerSpec extends Specification {

    def "Registering a user with known good parameters"() {
        given: "a set of user parameters"
        params.with {
            loginId = "glen_a_smith"
            password = "winning"
            homepage =  "http://blogs.bytecode.com.au/glen"
        }

        and: "a set of profile parameters"
        params['profile.fullName'] = "Glen Smith"
        params['profile.email'] = "glen@bytecode.com.au"
        params['profile.homepage'] = "http://blogs.bytecode.com.au/glen"

        when: "the user is registered"
        request.method = "POST"
        controller.register()

        then: "the user is created, and browser redirected"
        response.redirectedUrl == '/'
        User.count() == 1
        Profile.count() == 1
    }
}