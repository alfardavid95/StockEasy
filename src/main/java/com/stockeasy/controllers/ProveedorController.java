package com.stockeasy.controllers;

import com.stockeasy.domain.Proveedor;
import com.stockeasy.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("/listado")
    public String listado(
            @RequestParam(required = false) String texto,
            Model model
    ) {

        model.addAttribute(
                "proveedores",
                proveedorService.buscarProveedores(texto)
        );

        model.addAttribute(
                "texto",
                texto
        );

        return "proveedor/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {

        model.addAttribute(
                "proveedor",
                new Proveedor()
        );

        return "proveedor/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable("id") Integer idProveedor,
            Model model
    ) {

        model.addAttribute(
                "proveedor",
                proveedorService.getProveedor(idProveedor)
        );

        return "proveedor/form";
    }

    @PostMapping("/guardar")
    public String guardar(
            @ModelAttribute Proveedor proveedor,
            RedirectAttributes redirectAttributes
    ) {

        proveedorService.save(proveedor);

        redirectAttributes.addFlashAttribute(
                "mensaje",
                "Proveedor guardado correctamente."
        );

        return "redirect:/proveedores/listado";
    }

    @GetMapping("/desactivar/{id}")
    public String desactivar(
            @PathVariable("id") Integer idProveedor,
            RedirectAttributes redirectAttributes
    ) {

        proveedorService.desactivar(idProveedor);

        redirectAttributes.addFlashAttribute(
                "mensaje",
                "Proveedor desactivado correctamente."
        );

        return "redirect:/proveedores/listado";
    }
}