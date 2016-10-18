package com.grailsinaction.hubbub

import grails.converters.JSON
import grails.converters.XML

import javax.annotation.PostConstruct
import java.text.SimpleDateFormat

/**
 * Created by Marcelo Martins on 18/10/16.
 */
class MarshallerRegister {

    @PostConstruct
    void register() {
        def dateFormatter = new SimpleDateFormat("dd/MM/yyyy'T'hh:mm:ss")

        XML.registerObjectMarshaller(Post) { Post p, converter ->
            converter.attribute "id", p.id.toString()
            converter.attribute "published", dateFormatter.format(p.dateCreated)
            converter.build {
                message p.content
                user p.user.loginId
                tags {
                    for (t in p.tags) {
                        tag t.name
                    }
                }
            }
        }

        JSON.createNamedConfig("v1") { cfg ->
            cfg.registerObjectMarshaller(Post) { Post p ->
                return [ published: dateFormatter.format(p.dateCreated),
                         message: p.content,
                         user: p.user.loginId,
                         tags: p.tags.collect { it.name } ]
            }
        }

        JSON.createNamedConfig("v2") { cfg ->
            cfg.registerObjectMarshaller(Post) { Post p ->
                return [ published: dateFormatter.format(p.dateCreated),
                         message: p.content,
                         user: [ id: p.user.loginId,
                                 name: p.user.profile.fullName ],
                         tags: p.tags.collect { it.name } ]
            }
        }
    }
}
