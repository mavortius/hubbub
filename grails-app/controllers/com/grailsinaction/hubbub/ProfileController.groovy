package com.grailsinaction.hubbub

import grails.plugin.springsecurity.annotation.Secured

@Secured('isAuthenticated()')
class ProfileController {

    static scaffold = Profile

}
