package com.stockeasy.controllers;
import com.stockeasy.domain.Usuario;
import com.stockeasy.repository.RolRepository;
import com.stockeasy.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller @RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired private UsuarioService service;
    @Autowired private RolRepository rolRepository;
    @GetMapping("/listado") public String listado(Model model){ model.addAttribute("usuarios", service.getUsuarios()); return "usuario/listado"; }
    @GetMapping("/nuevo") public String nuevo(Model model){ model.addAttribute("usuario", new Usuario()); model.addAttribute("roles", rolRepository.findAll()); return "usuario/form"; }
    @GetMapping("/editar/{id}") public String editar(@PathVariable Integer id, Model model){ model.addAttribute("usuario", service.getUsuario(id)); model.addAttribute("roles", rolRepository.findAll()); return "usuario/form"; }
    @PostMapping("/guardar") public String guardar(Usuario usuario){ service.save(usuario); return "redirect:/usuarios/listado"; }
    @GetMapping("/desactivar/{id}") public String desactivar(@PathVariable Integer id){ service.desactivar(id); return "redirect:/usuarios/listado"; }
}
