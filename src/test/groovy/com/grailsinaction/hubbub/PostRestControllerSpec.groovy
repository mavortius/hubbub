package com.grailsinaction.hubbub

import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(PostRestController)
@Mock([User, Post])
class PostRestControllerSpec extends Specification {

    def setup() {
        defineBeans {
            springSecurityService(SpringSecurityService)
        }
    }

    def cleanup() {
    }

    void "GET a list of posts as JSON"() {
        given: "A set of posts"
        initialiseUsersAndPosts()

        when: "I invoke the index action "
        controller.index()

        then: "I get the expected posts as a JSON list"
        response.json*.content.sort() == [
                "A first post",
                "A second post",
                "Preparing for battle",
                "Soaking up the sun" ]
    }

    void "GET a list of posts as XML"() {
        given: "A set of posts"
        initialiseUsersAndPosts()

        when: "I invoke the show action without an ID and requesting XML"
        response.format = "xml"
        controller.index()

        then: "I get the expected posts as an XML document"
        response.xml.post.content*.text().sort() == [
                "A first post",
                "A second post",
                "Preparing for battle",
                "Soaking up the sun" ]
    }

    void "POST a single post as JSON"() {
        given: "An existing user"
        def userId = 7

        when: "I invoke the save action with a JSON packet"
        request.json = '{"message":"A new post from functional test!","user":{"id":' + userId + '}}'
        controller.save()

        then: "I get a 201 JSON response with the ID of the new post"
        response.status == 201
        response.json.id != null
    }

    private initialiseUsersAndPosts() {
        def chuck = new User(loginId: "chuck_norris", passwordHash: "password")
        chuck.addToPosts(content: "A first post")
        chuck.addToPosts(content: "A second post")
        chuck.save(failOnError: true)

        def bruce = new User(loginId: "bruce_lee", passwordHash: "iknowkungfu")
        bruce.addToPosts(content: "Soaking up the sun")
        bruce.addToPosts(content: "Preparing for battle")
        bruce.save(failOnError: true, flush: true)

        return chuck.id
    }
}
