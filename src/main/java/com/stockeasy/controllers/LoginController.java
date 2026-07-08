package com.stockeasy.controllers;
import com.stockeasy.domain.Usuario;
import com.stockeasy.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class LoginController {
    @Autowired private UsuarioService usuarioService;
    @GetMapping({"/","/login"}) public String login(){ return "login"; }
    @PostMapping("/login") public String loginPost(@RequestParam String correo, @RequestParam String contrasena, HttpSession session, RedirectAttributes r){
        var usuario = usuarioService.login(correo, contrasena);
        if(usuario.isPresent()){ session.setAttribute("usuarioSesion", usuario.get()); return "redirect:/dashboard"; }
        r.addFlashAttribute("error","Correo o contraseña incorrectos"); return "redirect:/login";
    }
    @GetMapping("/logout") public String logout(HttpSession session){ session.invalidate(); return "redirect:/login"; }
}
