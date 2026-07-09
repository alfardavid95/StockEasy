package com.stockeasy.controllers;
import com.stockeasy.domain.Proveedor;
import com.stockeasy.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller @RequestMapping("/proveedores")
public class ProveedorController {
    @Autowired private ProveedorService service;
    @GetMapping("/listado") public String listado(Model model){ model.addAttribute("proveedores", service.getProveedores()); return "proveedor/listado"; }
    @GetMapping("/nuevo") public String nuevo(Model model){ model.addAttribute("proveedor", new Proveedor()); return "proveedor/form"; }
    @GetMapping("/editar/{id}") public String editar(@PathVariable Integer id, Model model){ model.addAttribute("proveedor", service.getProveedor(id)); return "proveedor/form"; }
    @PostMapping("/guardar") public String guardar(@Valid Proveedor proveedor, BindingResult result){if (result.hasErrors()) {return "proveedor/form";}service.save(proveedor);return "redirect:/proveedores/listado";}
    @GetMapping("/desactivar/{id}") public String desactivar(@PathVariable Integer id){ service.desactivar(id); return "redirect:/proveedores/listado"; }
}
