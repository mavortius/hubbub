package com.grailsinaction.hubbub

import grails.test.mixin.integration.Integration
import grails.transaction.*

import spock.lang.*
import geb.spock.*

/**
 * See http://www.gebish.org/manual/current/ for more instructions
 */
@Integration
@Rollback
class TimelineFunctionalSpec extends GebSpec {

    def "Check that timeline loads for user 'phil'"() {
        when: "We load phil's timeline"
        go "users/phil"

        then: "the page display Phil's full name"
        ${"#newPost h3"}.text() == "What is Phil Potts hacking on right now?"
    }
}
