package com.uisrael.karateika_frontend.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uisrael.karateika_frontend.modelo.dto.request.AsistenciaDTORequest;
import com.uisrael.karateika_frontend.service.IAlumnoServicio;
import com.uisrael.karateika_frontend.service.IAsistenciaService;

@Controller
@RequestMapping("/asistencias")
public class AsistenciaController {
	@Autowired
	private IAsistenciaService asistenciaService;

	@Autowired
	private IAlumnoServicio alumnoService;

	@GetMapping
	public String listarAlumnos(Model model) {
		model.addAttribute("asistenciaModel", asistenciaService.listarAsistencia());
		return "/asistencia/buscarAsistenciaAlumno"; // Ubicacion fisica templates
	}

	@GetMapping("/nuevo")
	public String nuevoAlumnos(Model model) {
		model.addAttribute("alumnoModel", alumnoService.listarAlumno());
		model.addAttribute("asistenciasModel", new AsistenciaDTORequest());
		return "/asistencia/registrarAsistencias"; // Ubicacion fisica templates
	}

	public String guardar(@ModelAttribute AsistenciaDTORequest asistencias) {
		asistenciaService.crearAsistencia(asistencias);
		return "redirect:/asistencias";
	}

	@PostMapping("/guardar")
	public String guardarAsistencias(@RequestBody List<AsistenciaDTORequest> asistencias) {
		asistenciaService.guardarAsistencias(asistencias);
		return "redirect:/asistencias";
	}

}
