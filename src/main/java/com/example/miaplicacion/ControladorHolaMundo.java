package com.example.miaplicacion;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorHolaMundo {

    @GetMapping("/hola")
    public String holaMundo() {
        return "¡Hola, este es mi primer proyecto :)!";
    }
}
