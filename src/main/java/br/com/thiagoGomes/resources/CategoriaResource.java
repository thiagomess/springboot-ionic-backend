package br.com.thiagoGomes.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriaResource {

    @RequestMapping(value="/circles", method=RequestMethod.GET)
    public String listar() {
        return "REST está funcionando!";
    }

}
