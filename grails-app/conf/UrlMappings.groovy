class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "home", action: "index")
        "/registro"(controller: "enrollment", action: "index")
        "/perfil/$username"(controller: "profile", action: "index")
        "/configuracion/cuenta"(controller: "settingsProfile", action: "index")
        "/registro/confirma/$id"(controller: "enrollment", action: "confirm")
        "500"(view:'/error')
	}
}
