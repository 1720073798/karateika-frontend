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

	@PostMapping
	public String guardarComprobante(
	        @ModelAttribute ComprobanteDTORequest comprobante,
	        @RequestParam(value = "archivo", required = false) MultipartFile archivo,
	        Model model) {
	    
	    // Validación manual del número
	    if (comprobante.getCom_numero() == null || comprobante.getCom_numero() <= 0) {
	        model.addAttribute("com_numero_error", "El número de comprobante es obligatorio y debe ser mayor a 0");
	        model.addAttribute("comprobante", comprobante);
	        return "/comprobante/nuevocomprobantes";
	    }
	    
	    // Validación manual de la fecha
	    if (comprobante.getCom_fecha_subida() == null) {
	        // Put the message into the field-specific attribute so it appears under the date input
	        model.addAttribute("com_fecha_error", "La fecha de subida es obligatoria");
	        model.addAttribute("comprobante", comprobante);
	        return "/comprobante/nuevocomprobantes";
	    }

	    // Validación manual del archivo adjunto: obligatorio
	    if (archivo == null || archivo.isEmpty()) {
	        model.addAttribute("com_archivo_error", "Debe subir archivo de respaldo");
	        model.addAttribute("comprobante", comprobante);
	        return "/comprobante/nuevocomprobantes";
	    }
	    
	    try {
	        servicioComprobante.guardarComprobante(comprobante, archivo);
	        return "redirect:/comprobantes/listarcomprobante";
	    } catch (RuntimeException e) {
	        // Extract backend message that may contain the specific field error
	        String msg = e.getMessage();
	        if (msg == null) msg = "Error al guardar";

	        String lower = msg.toLowerCase();
	        // If backend says the number already exists, attach it to the number-specific model attribute
	        if (lower.contains("número de comprobante") || lower.contains("numero de comprobante")) {
	            model.addAttribute("com_numero_error", msg);
	        } else if (lower.contains("fecha de subida") || (lower.contains("fecha") && lower.contains("subida"))) {
	            // If backend reports an issue related to the date, attach it to the date-specific attribute
	            model.addAttribute("com_fecha_error", msg);
	        } else if (lower.contains("archivo") || lower.contains("respaldo") || lower.contains("pdf") || lower.contains("imagen")) {
	            // If backend reports an issue related to the uploaded file
	            model.addAttribute("com_archivo_error", msg);
	        } else {
	            model.addAttribute("error", "Error al guardar: " + msg);
	        }

	        model.addAttribute("comprobante", comprobante);
	        return "/comprobante/nuevocomprobantes";
	    }
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