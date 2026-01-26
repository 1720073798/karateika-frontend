package com.uisrael.karateika_frontend.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alumnos")
public class AlumnoControllador {
	
	@GetMapping
	public String listarAlumnos() {
		return "/alumno/alumnos"; //Ubicacion fisica templates
	}
}
