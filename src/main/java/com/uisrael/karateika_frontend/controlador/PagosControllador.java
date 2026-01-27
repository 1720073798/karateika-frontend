package com.uisrael.karateika_frontend.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pagos")
public class PagosControllador {

    @GetMapping ("/listarpago")
    public String listarPagos() {
        return "/pago/listarpagos"; // Ubicación física en templates
    }
    
    @GetMapping("/nuevopago")
    public String nuevoPago() {
        return "/pago/nuevopagos"; // vista para crear
    }
}