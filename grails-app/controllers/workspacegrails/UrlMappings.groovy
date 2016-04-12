package workspacegrails

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: 'home', action: 'index')
        "500"(controller:'error', action: 'error')
        "404"(controller:'error', action: 'notFound')
    }
}
