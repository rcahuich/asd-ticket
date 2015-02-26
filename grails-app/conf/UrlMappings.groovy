class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "home", action: "index")
        "/registro"(controller: "enrollment", action: "index")
        "500"(view:'/error')
	}
}
