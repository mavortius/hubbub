package com.grailsinaction.hubbub

class User {
    String loginId
    String passwordHash
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    Date dateCreated

    static hasOne = [ profile:Profile ]
    static hasMany = [ posts:Post, tags:Tag, following:User ]

    transient springSecurityService

    static transients = ['springSecurityService']

    static constraints = {
        loginId size: 3..20, unique: true, nullable: false
        tags()
        posts()
        profile nullable: true
    }
    static mapping = {
        posts sort: 'dateCreated', order: 'desc'
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect {  it.role } as Set
    }

    String toString() { return "User $loginId (id: $id)" }
    String getDisplayString() { return loginId }
}
