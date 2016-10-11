package com.grailsinaction.hubbub

import org.hibernate.criterion.Example
import org.hibernate.criterion.MatchMode

class Post {
    String content
    Date dateCreated
    static belongsTo = [ user:User ]
    static hasMany = [ tags:Tag ]
    static constraints = {
        content blank: false
    }

    public static findAllLike(Post qbe) {
        def criteria = Post.createCriteria()
        def example = Example.create(qbe)
                .enableLike(MatchMode.ANYWHERE)
                .excludeZeroes()
                .ignoreCase()
        return criteria.add(example).list()
    }
}
