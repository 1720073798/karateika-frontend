package com.uisrael.karateika_frontend.controlador;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uisrael.karateika_frontend.modelo.dto.request.AscensoDTORequest;
import com.uisrael.karateika_frontend.modelo.dto.response.AscensoDTOResponse;
import com.uisrael.karateika_frontend.modelo.enums.Cinturon;
import com.uisrael.karateika_frontend.service.IAlumnoServicio;
import com.uisrael.karateika_frontend.service.IAscensoServicio;

@Controller
@RequestMapping("/ascensos")
public class AscensoControllador {

    @Autowired
    private IAscensoServicio servicioAscenso;
    
    @Autowired
    private IAlumnoServicio alumnoService;

    // LISTAR ASCENSOS
    @GetMapping("/listarascensos")
    public String listarAscensos(Model model) {
        List<AscensoDTOResponse> contenidoBD = servicioAscenso.listarAscensos();
        model.addAttribute("listaascensos", contenidoBD);
        return "/ascenso/listarascensos";
    }

    // FORM NUEVO ASCENSO
    @GetMapping("/nuevoascenso")
    public String nuevoAscenso(Model model) {
    	model.addAttribute("alumnoModel", alumnoService.listarAlumno());
		// Añadimos la lista de cinturones desde el enum para poblar el select en la vista
		model.addAttribute("cinturones", Arrays.asList(Cinturon.values()));
        model.addAttribute("ascenso", new AscensoDTORequest());
        return "/ascenso/nuevoascensos";
    }

    // GUARDAR ASCENSO
    @PostMapping
    public Object guardarAscenso(@ModelAttribute AscensoDTORequest ascenso) {
        // Si el checkbox de certificado está marcado, llamamos al endpoint que devuelve PDF
        if (ascenso.isAsc_c_generado()) {
            byte[] pdf = servicioAscenso.crearAscensoYDescargarCertificado(ascenso);
            if (pdf != null && pdf.length > 0) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", "certificado_ascenso.pdf");
                return ResponseEntity.ok().headers(headers).body(pdf);
            }
            // Si algo salió mal, redirigimos y mostramos la lista (podrías mejorar mostrando un mensaje)
            return "redirect:/ascensos/listarascensos";
        }

        // Si no se pidió certificado, sólo guardamos y redirigimos
        servicioAscenso.crearAscenso(ascenso);
        // Redirigimos a la lista de alumnos para que se visualice el cinturón actualizado
        return "redirect:/alumnos";
    }
    
	@GetMapping("buscar/{idAscenso}")
	public String editarAscenso(@PathVariable int idAscenso , Model model) {
		model.addAttribute("alumnoModel", alumnoService.listarAlumno());
		model.addAttribute("cinturones", Arrays.asList(Cinturon.values())); //lista de cinturones
		model.addAttribute("ascenso", servicioAscenso.buscarPorId(idAscenso)); //buscar por id //enviar el objeto encontrado		
		return "ascenso/nuevoascensos";  //abrir formulario nuevo curso
	}
}