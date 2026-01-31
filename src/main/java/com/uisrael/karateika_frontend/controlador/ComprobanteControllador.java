package com.uisrael.karateika_frontend.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String guardarComprobante(@ModelAttribute ComprobanteDTORequest comprobante) {
	    servicioComprobante.crearComprobante(comprobante);
	    return "redirect:/comprobantes/listarcomprobante";
	}

}