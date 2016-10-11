package com.grailsinaction.hubbub

class UserController {

    static scaffold = User

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
}
