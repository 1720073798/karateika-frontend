package com.uisrael.karateika_frontend.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ascensos")
public class AscensoControllador {

    @GetMapping("/listarascensos")
    public String listarAscensos() {
        return "/ascenso/listarascensos"; // Ubicación física en templates
    }
    
    @GetMapping("/nuevoascenso")
    public String nuevoAscenso() {
        return "/ascenso/nuevoascensos"; // vista para crear
    }

}
