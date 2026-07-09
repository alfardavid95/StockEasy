package com.stockeasy.controllers;

import com.stockeasy.domain.Categoria;
import com.stockeasy.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("categorias", service.getCategorias());
        return "categoria/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categoria/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("categoria", service.getCategoria(id));
        return "categoria/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Categoria categoria, BindingResult result) {
        if (categoria.getNombre() != null && service.existeNombre(categoria.getNombre(), categoria.getIdCategoria())) {
            result.rejectValue("nombre", "error.nombre", "Ya existe una categoría con ese nombre");
        }
        if (result.hasErrors()) {
            return "categoria/form";
        }
        service.save(categoria);
        return "redirect:/categorias/listado";
    }

    @GetMapping("/desactivar/{id}")
    public String desactivar(@PathVariable Integer id) {
        service.desactivar(id);
        return "redirect:/categorias/listado";
    }
}
