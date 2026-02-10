package com.uisrael.karateika_frontend.controlador;

import java.util.List;
import com.uisrael.karateika_frontend.modelo.enums.Cinturon;
import java.util.Arrays;
import java.util.Date;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		// Añadimos la lista de cinturones desde el enum para poblar el select en la vista
		model.addAttribute("cinturones", Arrays.asList(Cinturon.values()));
		return "/alumno/nuevoAlumno"; //Ubicacion fisica templates
	}
	
	@PostMapping
	public String guardar (@ModelAttribute AlumnoDTORequest alumno, Model model) {
		// Validación: la cédula no debe estar registrada
		if (alumno.getAlu_cedula() != null && !alumno.getAlu_cedula().trim().isEmpty()) {
			String cedula = alumno.getAlu_cedula().trim();
			List<AlumnoDTOResponse> existentes = servicioAlumno.listarAlumno();
			boolean existe = existentes != null && existentes.stream()
				// Si estamos editando (alu_id > 0) debemos excluir al propio registro de la comprobación
				.anyMatch(a -> a.getAlu_cedula() != null && a.getAlu_cedula().trim().equalsIgnoreCase(cedula)
					// permitir que coincida con el mismo id cuando se edita
					&& a.getAlu_id() != alumno.getAlu_id());
			if (existe) {
				model.addAttribute("modelAlumno", alumno);
				model.addAttribute("cinturones", Arrays.asList(Cinturon.values()));
				model.addAttribute("cedulaError", "Usuario ya está registrado");
				return "/alumno/nuevoAlumno";
			}
		}

		// Validación: la fecha de nacimiento debe indicar al menos 4 años de edad
		if (alumno.getAlu_fecha_nacimiento() != null) {
			LocalDate fechaNac = alumno.getAlu_fecha_nacimiento();
			LocalDate hoy = LocalDate.now();
			int edad = Period.between(fechaNac, hoy).getYears();
			if (edad < 4) {
				// volver al formulario con mensaje de error y preservar datos
				model.addAttribute("modelAlumno", alumno);
				model.addAttribute("cinturones", Arrays.asList(Cinturon.values()));
				model.addAttribute("fechaError", "La fecha de nacimiento debe indicar al menos 4 años de edad.");
				return "/alumno/nuevoAlumno";
			}
			// Nueva validación: si es menor de 18 años, representante es obligatorio
			if (edad < 18) {
				boolean faltaNombre = alumno.getAlu_nombre_representante() == null || alumno.getAlu_nombre_representante().trim().isEmpty();
				boolean faltaTelefono = alumno.getAlu_telefono_representante() == null || alumno.getAlu_telefono_representante().trim().isEmpty();
				if (faltaNombre || faltaTelefono) {
					model.addAttribute("modelAlumno", alumno);
					model.addAttribute("cinturones", Arrays.asList(Cinturon.values()));
					model.addAttribute("representanteError", "Para menores de 18 años el nombre y teléfono del representante son obligatorios.");
					return "/alumno/nuevoAlumno";
				}
			} else {
				// Si tiene 18 o más, limpiamos datos de representante (no son requeridos)
				alumno.setAlu_nombre_representante(null);
				alumno.setAlu_telefono_representante(null);
			}
		}
		// Validación: la fecha de ingreso no puede ser posterior a hoy (no la forzamos, solo la rechazamos)
		if (alumno.getAlu_fecha_ingreso() != null) {
			LocalDate fechaIngreso = alumno.getAlu_fecha_ingreso();
			LocalDate hoy = LocalDate.now();
			if (fechaIngreso.isAfter(hoy)) {
				model.addAttribute("modelAlumno", alumno);
				model.addAttribute("cinturones", Arrays.asList(Cinturon.values()));
				model.addAttribute("fechaError", "La fecha de ingreso no puede ser posterior a la fecha actual.");
				return "/alumno/nuevoAlumno";
			}
		}
		// No forzar fecha de ingreso en el servidor — el campo lo gestiona el usuario; el cliente bloquea fechas futuras
		alumno.setAlu_fecha_creacion(new Date());
		alumno.setAlu_fecha_modificacion(new Date());
		alumno.setAlu_alerta_pago(true);
		servicioAlumno.crearAlumno(alumno);
		return "redirect:/alumnos";
	}
	@GetMapping("buscar/{idAlumno}")
	public String editarAlumno(@PathVariable int idAlumno , Model model) {
		model.addAttribute("cinturones", Arrays.asList(Cinturon.values())); //lista de cinturones
		model.addAttribute("modelAlumno", servicioAlumno.buscarPorId(idAlumno)); //buscar por id //enviar el objeto encontrado		
		return "alumno/nuevoAlumno";  //abrir formulario nuevo curso
	}
	
}