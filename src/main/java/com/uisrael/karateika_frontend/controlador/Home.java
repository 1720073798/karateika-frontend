package com.uisrael.karateika_frontend.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {
	  @GetMapping("/")
	    public String home() {
	        return "/home/home";
	    }

}
