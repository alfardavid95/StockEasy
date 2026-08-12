package com.stockeasy.controllers;

import com.stockeasy.service.MovimientoInventarioService;
import com.stockeasy.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private MovimientoInventarioService movimientoService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute(
                "totalProductos",
                productoService.countActivos()
        );

        model.addAttribute(
                "productosBajoStock",
                productoService.getBajoStock()
        );

        model.addAttribute(
                "totalBajoStock",
                productoService.getBajoStock().size()
        );

        model.addAttribute(
                "entradasMes",
                movimientoService.entradasMes()
        );

        model.addAttribute(
                "salidasMes",
                movimientoService.salidasMes()
        );

        model.addAttribute(
                "ultimosMovimientos",
                movimientoService.getUltimos()
        );

        return "dashboard";
    }
}