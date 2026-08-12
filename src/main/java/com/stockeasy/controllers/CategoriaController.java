package com.stockeasy.controllers;

import com.stockeasy.domain.Categoria;
import com.stockeasy.service.CategoriaService;
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
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String listado(
            @RequestParam(required = false) String texto,
            Model model
    ) {

        model.addAttribute(
                "categorias",
                categoriaService.buscarCategorias(texto)
        );

        model.addAttribute(
                "texto",
                texto
        );

        return "categoria/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {

        model.addAttribute(
                "categoria",
                new Categoria()
        );

        return "categoria/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable("id") Integer idCategoria,
            Model model
    ) {

        model.addAttribute(
                "categoria",
                categoriaService.getCategoria(idCategoria)
        );

        return "categoria/form";
    }

    @PostMapping("/guardar")
    public String guardar(
            @ModelAttribute Categoria categoria,
            RedirectAttributes redirectAttributes
    ) {

        categoriaService.save(categoria);

        redirectAttributes.addFlashAttribute(
                "mensaje",
                "Categoría guardada correctamente."
        );

        return "redirect:/categorias/listado";
    }

    @GetMapping("/desactivar/{id}")
    public String desactivar(
            @PathVariable("id") Integer idCategoria,
            RedirectAttributes redirectAttributes
    ) {

        categoriaService.desactivar(idCategoria);

        redirectAttributes.addFlashAttribute(
                "mensaje",
                "Categoría desactivada correctamente."
        );

        return "redirect:/categorias/listado";
    }
}