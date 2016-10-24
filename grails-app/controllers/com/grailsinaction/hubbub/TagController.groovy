package com.grailsinaction.hubbub

import grails.plugin.springsecurity.annotation.Secured

@Secured('isAuthenticated()')
class TagController {

    static scaffold = Tag

}
