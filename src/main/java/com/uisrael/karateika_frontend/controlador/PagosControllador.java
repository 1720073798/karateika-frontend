package com.uisrael.karateika_frontend.controlador;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uisrael.karateika_frontend.modelo.dto.request.PagosDTORequest;
import com.uisrael.karateika_frontend.modelo.dto.response.PagosDTOResponse;
import com.uisrael.karateika_frontend.service.IAlumnoServicio;
import com.uisrael.karateika_frontend.service.IComprobanteServicio;
import com.uisrael.karateika_frontend.service.IPagosServicio;

@Controller
@RequestMapping("/pagos")
public class PagosControllador {

	@Autowired
	private IPagosServicio servicioPago;

	@Autowired
	private IComprobanteServicio servicioComprobante;

	@Autowired
	private IAlumnoServicio servicioAlumno;

	@GetMapping("/listarpago")
	public String listarPagos(Model model) {
		List<PagosDTOResponse> contenidoBD = servicioPago.listarPagos();
		model.addAttribute("listapagos", contenidoBD);
		return "/pago/listarpagos";
	}

	@GetMapping("/nuevopago")
	public String nuevoPago(Model model) {
		model.addAttribute("pago", new PagosDTORequest());
		model.addAttribute("listaComprobantes", servicioComprobante.listarComprobantes());
		model.addAttribute("listaAlumnos", servicioAlumno.listarAlumno());
		return "/pago/nuevopagos";
	}

	
	@PostMapping
	public String guardarPago(@ModelAttribute PagosDTORequest pago, Model model) {
		try {
			// ✅ Validaciones manuales antes de enviar al backend

			// Validar Alumno
			if (pago.getFkalumno() == null || pago.getFkalumno().getAlu_id() <= 0) {
				model.addAttribute("error", "Seleccione un alumno válido");
				model.addAttribute("pago", pago);
				model.addAttribute("listaAlumnos", servicioAlumno.listarAlumno());
				model.addAttribute("listaComprobantes", servicioComprobante.listarComprobantes());

				// ✅ PRESERVAR DATOS DEL FORMULARIO
				if (pago.getFkalumno() != null && pago.getFkalumno().getAlu_id() > 0) {
					// Si ya tenía un alumno seleccionado, mantenerlo
					model.addAttribute("alumnoSeleccionado", pago.getFkalumno().getAlu_id());
				}

				return "/pago/nuevopagos";
			}

			// Validar Comprobante
			if (pago.getFkcomprobante() == null || pago.getFkcomprobante().getCom_id() == null
					|| pago.getFkcomprobante().getCom_id() <= 0) {
				model.addAttribute("error", "Seleccione un comprobante válido");
				model.addAttribute("pago", pago);
				model.addAttribute("listaAlumnos", servicioAlumno.listarAlumno());
				model.addAttribute("listaComprobantes", servicioComprobante.listarComprobantes());

				// ✅ PRESERVAR DATOS DEL FORMULARIO
				if (pago.getFkcomprobante() != null && pago.getFkcomprobante().getCom_id() != null
						&& pago.getFkcomprobante().getCom_id() > 0) {
					model.addAttribute("comprobanteSeleccionado", pago.getFkcomprobante().getCom_id());
				}

				return "/pago/nuevopagos";
			}

			// Validar Fecha de Pago
			if (pago.getPag_fecha_pago() == null) {
				model.addAttribute("error", "Seleccione la fecha de pago");
				model.addAttribute("pago", pago);
				model.addAttribute("listaAlumnos", servicioAlumno.listarAlumno());
				model.addAttribute("listaComprobantes", servicioComprobante.listarComprobantes());
				return "/pago/nuevopagos";
			}

			// Validar Monto
			if (pago.getPag_monto() == null || pago.getPag_monto().compareTo(BigDecimal.ZERO) <= 0) {
				model.addAttribute("error", "El monto debe ser mayor a 0");
				model.addAttribute("pago", pago);
				model.addAttribute("listaAlumnos", servicioAlumno.listarAlumno());
				model.addAttribute("listaComprobantes", servicioComprobante.listarComprobantes());
				return "/pago/nuevopagos";
			}

			// Validar Método de Pago
			if (pago.getPag_metodo_pago() == null || pago.getPag_metodo_pago().trim().isEmpty()) {
				model.addAttribute("error", "Seleccione el método de pago");
				model.addAttribute("pago", pago);
				model.addAttribute("listaAlumnos", servicioAlumno.listarAlumno());
				model.addAttribute("listaComprobantes", servicioComprobante.listarComprobantes());
				return "/pago/nuevopagos";
			}

			// Validar Registrado por
			if (pago.getPag_registrado_por() == null || pago.getPag_registrado_por().trim().isEmpty()) {
				model.addAttribute("error", "Ingrese quién registró el pago");
				model.addAttribute("pago", pago);
				model.addAttribute("listaAlumnos", servicioAlumno.listarAlumno());
				model.addAttribute("listaComprobantes", servicioComprobante.listarComprobantes());
				return "/pago/nuevopagos";
			}

			// ✅ Intentar guardar
			servicioPago.guardarPago(pago);
			return "redirect:/pagos/listarpago";

		} catch (Exception e) {
			// ✅ Capturar y mostrar el error específico
			String mensajeError = e.getMessage();

			if (e.getCause() != null) {
				mensajeError = e.getCause().getMessage();
			}

			if (mensajeError == null || mensajeError.isEmpty()) {
				mensajeError = "Error al guardar el pago. Verifique los datos e intente nuevamente.";
			}

			model.addAttribute("error", mensajeError);
			model.addAttribute("pago", pago);
			model.addAttribute("listaAlumnos", servicioAlumno.listarAlumno());
			model.addAttribute("listaComprobantes", servicioComprobante.listarComprobantes());
			return "/pago/nuevopagos";
		}
	}

	@GetMapping("/editar/{id}")
	public String editarPago(@PathVariable int id, Model model) {
		PagosDTOResponse pago = servicioPago.buscarPorId(id);
		model.addAttribute("pago", pago);
		model.addAttribute("listaComprobantes", servicioComprobante.listarComprobantes());
		model.addAttribute("listaAlumnos", servicioAlumno.listarAlumno());
		return "/pago/nuevopagos";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarPago(@PathVariable int id) {
		servicioPago.eliminarPago(id);
		return "redirect:/pagos/listarpago";
	}
}