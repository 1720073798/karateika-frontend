package com.uisrael.karateika_frontend.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/asistencias")
public class AsistenciaController {
	@GetMapping
	public String listarAlumnos() {
		return "/asistencia/buscarAsistenciaAlumno"; //Ubicacion fisica templates
	}
	
	@GetMapping("/nuevo")
	public String nuevoAlumnos() {
		return "/asistencia/registrarAsistencias"; //Ubicacion fisica templates
	}

}
