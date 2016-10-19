package com.grailsinaction.hubbub


class RestInterceptor {
    RestInterceptor() {
        match(controller: "postRest")
    }

    boolean before() {
        if(!(request.format in ["json", "xml", "all"]) && !(request.method in ["DELETE", "GET", "HEAD"])) {
            render status: 415, text: "Unrecognized content type"
            false
        }

        if(!(response.format in ["json", "xml", "all"])) {
            render status: 406, text: "${response.format} not supported"
            false
        }

        true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
