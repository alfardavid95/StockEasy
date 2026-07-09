package com.stockeasy.controllers;

import com.stockeasy.domain.Producto;
import com.stockeasy.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private MovimientoInventarioService movimientoService;

    @GetMapping("/listado")
    public String listado(@RequestParam(required = false) String texto, @RequestParam(required = false) Integer idCategoria, @RequestParam(required = false) Integer idProveedor, @RequestParam(required = false) Boolean estado, Model model) {
        model.addAttribute("productos", productoService.getProductos(texto, idCategoria, idProveedor, estado));
        model.addAttribute("categorias", categoriaService.getCategorias());
        model.addAttribute("proveedores", proveedorService.getProveedores());
        return "producto/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new Producto());
        cargarCombos(model);
        return "producto/form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        model.addAttribute("producto", productoService.getProducto(id));
        cargarCombos(model);
        return "producto/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Producto producto, BindingResult result, Model model) {
        if (producto.getCodigo() != null && productoService.existeCodigo(producto.getCodigo(), producto.getIdProducto())) {
            result.rejectValue("codigo", "error.codigo", "Ya existe un producto con ese código");
        }
        if (result.hasErrors()) {
            cargarCombos(model);
            return "producto/form";
        }
        productoService.save(producto);
        return "redirect:/productos/listado";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Integer id, Model model) {
        model.addAttribute("producto", productoService.getProducto(id));
        model.addAttribute("movimientos", movimientoService.getPorProducto(id));
        return "producto/detalle";
    }

    @GetMapping("/desactivar/{id}")
    public String desactivar(@PathVariable Integer id) {
        productoService.desactivar(id);
        return "redirect:/productos/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("categorias", categoriaService.getCategorias());
        model.addAttribute("proveedores", proveedorService.getProveedores());
    }
}
