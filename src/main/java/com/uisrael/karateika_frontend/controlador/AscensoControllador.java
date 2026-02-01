package com.uisrael.karateika_frontend.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uisrael.karateika_frontend.modelo.dto.request.AscensoDTORequest;
import com.uisrael.karateika_frontend.modelo.dto.response.AscensoDTOResponse;
import com.uisrael.karateika_frontend.service.IAscensoServicio;

@Controller
@RequestMapping("/ascensos")
public class AscensoControllador {

    @Autowired
    private IAscensoServicio servicioAscenso;

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
        model.addAttribute("ascenso", new AscensoDTORequest());
        return "/ascenso/nuevoascensos";
    }

    // GUARDAR ASCENSO
    @PostMapping
    public String guardarAscenso(@ModelAttribute AscensoDTORequest ascenso) {
        servicioAscenso.crearAscenso(ascenso);
        return "redirect:/ascensos/listarascensos";
    }
}