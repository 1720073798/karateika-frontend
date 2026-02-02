package com.uisrael.karateika_frontend.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uisrael.karateika_frontend.modelo.dto.request.AlumnoDTORequest;
import com.uisrael.karateika_frontend.modelo.dto.response.AlumnoDTOResponse;
import com.uisrael.karateika_frontend.service.IAlumnoServicio;

@Controller
@RequestMapping("/alumnos")
public class AlumnoControllador {
	
	@Autowired
	private IAlumnoServicio servicioAlumno;
	
	@GetMapping
	public String listarAlumnos(Model model) {
		List<AlumnoDTOResponse> contenidoBD = servicioAlumno.listarAlumno();
		model.addAttribute("modelAlumno", contenidoBD);
		return "/alumno/alumnos"; //Ubicacion fisica templates
	}
	
	@GetMapping("/nuevo")
	public String nuevoAlumnos(Model model) {
		model.addAttribute("modelAlumno", new AlumnoDTORequest());
		return "/alumno/nuevoAlumno"; //Ubicacion fisica templates
	}
	
	@PostMapping
	public String guardar (@ModelAttribute AlumnoDTORequest alumno) {
		servicioAlumno.crearAlumno(alumno);
		return "redirect:/alumnos";
	}
}
