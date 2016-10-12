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

    def register() {
        if(request.method == "POST") {
            def  user = new User(params)

            if(user.validate()) {
                user.save()
                flash.message = "Successfully Created User"
                redirect uri: '/'
            } else {
                flash.message = "Error Registering User"
                [user:user]
            }
        }
    }
}
