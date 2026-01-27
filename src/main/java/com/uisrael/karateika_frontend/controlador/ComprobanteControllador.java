package com.uisrael.karateika_frontend.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comprobantes")
public class ComprobanteControllador {

    @GetMapping ("/listarcomprobante")
    public String listarComprobantes() {
        return "/comprobante/listarcomprobantes"; // Ubicación física en templates
    }
    
    @GetMapping("/nuevocomprobante")
    public String nuevoComprobante() {
        return "/comprobante/nuevocomprobantes"; // vista para crear
    }

}