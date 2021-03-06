package com.grailsinaction.hubbub

class UtilTagLib {
    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    static namespace = "hub"

    def springSecurityService

    def loggedUsername = { attrs ->
        if(springSecurityService.isLoggedIn()) {
            def user = springSecurityService.currentUser
            def username = user.profile ? user.profile.fullName : user.loginId

            out << username
        }
    }

    def dateFromNow = { attrs ->
        def date = attrs.date
        def niceDate = getNiceDate(date)

        out << niceDate
    }

    def certainBrowser = { attrs, body ->
        if(request.getHeader('User-Agent') =~ attrs.userAgent) {
            out << body()
        }
    }

    def tinyThumbnail = { attrs ->
        def loginId = attrs.loginId

        out << "<img src='"
        out << g.createLink(action: "tiny", controller: "image", id: loginId)
        out << "' alt='${loginId}'"
    }

    protected String getNiceDate(Date date) {
        def now = new Date()
        def diff = Math.abs(now.time - date.time)
        final long second = 1000
        final long minute = second * 60
        final long hour = minute * 60
        final long day = hour * 24
        def niceTime = ""
        long calc = Math.floor(diff / day)

        if(calc) {
            niceTime += calc + " day" + (calc > 1 ? "s " : " ")
            diff %= day
        }
        calc = Math.floor(diff / hour)
        if(calc) {
            niceTime += calc + " hour" + (calc > 1 ? "s " : " ")
            diff %= hour
        }
        calc = Math.floor(diff / minute)
        if(calc) {
            niceTime += calc + " minute" + (calc > 1 ? "s " : " ")
            diff %= minute
        }
        if(!niceTime) {
            niceTime = "Right now"
        } else {
            niceTime += (date.time > now.time) ? "from now" : "ago"
        }
        return niceTime
    }
}
