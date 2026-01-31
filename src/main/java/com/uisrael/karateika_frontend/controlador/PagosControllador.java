package com.uisrael.karateika_frontend.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	//@Autowired
	//private IAlumnoServicio servicioAlumno;        
	@Autowired
	private IComprobanteServicio servicioComprobante;
	@GetMapping("/listarpago")
	public String listarPagos(Model model) {
	    List<PagosDTOResponse> contenidoBD = servicioPago.listarPagos();
	    model.addAttribute("listapagos", contenidoBD);
	    return "/pago/listarpagos";
	}

	@GetMapping("/nuevopago")
	public String nuevoPago(Model model) {
	    model.addAttribute("pago", new PagosDTORequest());

	   // model.addAttribute("listaAlumnos", servicioAlumno.listarAlumnos());

	    model.addAttribute("listaComprobantes", servicioComprobante.listarComprobantes());
	    
	    return "/pago/nuevopagos";
	}

	@PostMapping
	public String guardarPago(@ModelAttribute PagosDTORequest pago) {
	    servicioPago.crearPago(pago);
	    return "redirect:/pagos/listarpago";
	}
}