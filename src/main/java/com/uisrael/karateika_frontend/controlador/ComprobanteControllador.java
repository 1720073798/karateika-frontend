package com.uisrael.karateika_frontend.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.uisrael.karateika_frontend.modelo.dto.request.ComprobanteDTORequest;
import com.uisrael.karateika_frontend.modelo.dto.response.ComprobanteDTOResponse;
import com.uisrael.karateika_frontend.service.IComprobanteServicio;

@Controller
@RequestMapping("/comprobantes")
public class ComprobanteControllador {

	@Autowired
	private IComprobanteServicio servicioComprobante;

	@GetMapping("/listarcomprobante")
	public String listarComprobantes(Model model) {
		List<ComprobanteDTOResponse> contenidoBD = servicioComprobante.listarComprobantes();
		model.addAttribute("listacomprobantes", contenidoBD);
		return "/comprobante/listarcomprobantes";
	}

	@GetMapping("/nuevocomprobante")
	public String nuevoComprobante(Model model) {
		model.addAttribute("comprobante", new ComprobanteDTORequest());
		return "/comprobante/nuevocomprobantes";
	}

	// ✅ ÚNICO MÉTODO PARA CREAR Y EDITAR (lógica del profesor)
	@PostMapping
	public String guardarComprobante(@ModelAttribute ComprobanteDTORequest comprobante,
			@RequestParam(value = "archivo", required = false) MultipartFile archivo) {

		servicioComprobante.guardarComprobante(comprobante, archivo);
		return "redirect:/comprobantes/listarcomprobante";
	}

	@GetMapping("/editar/{id}")
	public String editarComprobante(@PathVariable int id, Model model) {
		ComprobanteDTOResponse comprobante = servicioComprobante.buscarPorId(id);
		model.addAttribute("comprobante", comprobante);
		return "/comprobante/nuevocomprobantes"; // ✅ MISMO FORMULARIO QUE NUEVO
	}

	@GetMapping("/eliminar/{id}")
	public String eliminarComprobante(@PathVariable int id) {
		servicioComprobante.eliminarComprobante(id);
		return "redirect:/comprobantes/listarcomprobante";
	}
}