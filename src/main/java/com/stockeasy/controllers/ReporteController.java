package com.stockeasy.controllers;
import com.stockeasy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller @RequestMapping("/reportes")
public class ReporteController {
    @Autowired private ProductoService productoService;
    @GetMapping("/bajo-stock") public String bajoStock(Model model){ model.addAttribute("productos", productoService.getBajoStock()); return "reporte/bajo-stock"; }
}
