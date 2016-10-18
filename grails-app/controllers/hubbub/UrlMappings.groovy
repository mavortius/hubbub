package hubbub

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/users/$id"(controller: "post", action: "timeline")
        "/timeline"(controller: "post", action: "personal")
        "/login/form"(controller: "auth", action: "form")
        "/"(view:"/index")
        "500"(controller: "error", action: "internalServer")
        "404"(view:'/notFound')
        "/api/posts"(resources: "postRest")
    }
}
