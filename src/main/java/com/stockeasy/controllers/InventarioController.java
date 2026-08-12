package com.stockeasy.controllers;

import com.stockeasy.service.CategoriaService;
import com.stockeasy.service.ProductoService;
import com.stockeasy.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public String inventarioGeneral(
            @RequestParam(required = false) String texto,
            @RequestParam(required = false) Integer idCategoria,
            @RequestParam(required = false) Integer idProveedor,
            @RequestParam(required = false) Boolean estado,
            Model model) {

        model.addAttribute(
                "productos",
                productoService.getProductos(
                        texto,
                        idCategoria,
                        idProveedor,
                        estado
                )
        );

        model.addAttribute(
                "categorias",
                categoriaService.getCategorias()
        );

        model.addAttribute(
                "proveedores",
                proveedorService.getProveedores()
        );

        model.addAttribute("texto", texto);
        model.addAttribute("idCategoria", idCategoria);
        model.addAttribute("idProveedor", idProveedor);
        model.addAttribute("estado", estado);

        return "inventario/listado";
    }
}