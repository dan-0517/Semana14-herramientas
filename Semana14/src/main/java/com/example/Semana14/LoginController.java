package com.saludica.loginbotica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class LoginController {

    // Almacenamiento estático: usuario -> [clave, rol]
    private static final Map<String, String[]> USUARIOS = Map.of(
            "admin",  new String[]{"admin123", "Administrador"},
            "caja01", new String[]{"caja123",  "Cajero"},
            "alm01",  new String[]{"alm123",   "Almacenero"}
    );

    @GetMapping({"/", "/login"})
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam("usuario") String usuario,
            @RequestParam("clave") String clave,
            Model model) {

        String[] datos = USUARIOS.get(usuario.trim());

        if (datos != null && datos[0].equals(clave)) {
            model.addAttribute("usuario", usuario.trim());
            model.addAttribute("rol", datos[1]);
            return "bienvenida";
        }

        model.addAttribute("error", "Credenciales incorrectas");
        return "login";
    }
}