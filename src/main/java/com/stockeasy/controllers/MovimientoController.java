package com.stockeasy.controllers;
import com.stockeasy.domain.Usuario;
import com.stockeasy.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Controller @RequestMapping("/movimientos")
public class MovimientoController {
    @Autowired private MovimientoInventarioService movimientoService;
    @Autowired private ProductoService productoService;
    @GetMapping("/listado") public String listado(@RequestParam(required=false) String texto, @RequestParam(required=false) String tipo, @RequestParam(required=false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate fechaInicio, @RequestParam(required=false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate fechaFin, Model model){ model.addAttribute("movimientos", movimientoService.getMovimientos(texto,tipo,fechaInicio,fechaFin)); model.addAttribute("texto", texto); model.addAttribute("tipo", tipo); model.addAttribute("fechaInicio", fechaInicio); model.addAttribute("fechaFin", fechaFin); return "movimiento/listado"; }
    @GetMapping("/entrada") public String entrada(Model model){ model.addAttribute("productos", productoService.getProductos(null,null,null,true)); return "movimiento/entrada"; }
    @PostMapping("/entrada/guardar") public String guardarEntrada(@RequestParam Integer idProducto, @RequestParam Integer cantidad, @RequestParam(required=false) String observacion, HttpSession session){ movimientoService.entrada(idProducto,cantidad,usuario(session),observacion); return "redirect:/movimientos/listado"; }
    @GetMapping("/salida") public String salida(Model model){ model.addAttribute("productos", productoService.getProductos(null,null,null,true)); return "movimiento/salida"; }
    @PostMapping("/salida/guardar") public String guardarSalida(@RequestParam Integer idProducto, @RequestParam Integer cantidad, @RequestParam(required=false) String observacion, HttpSession session, RedirectAttributes r){ try{ movimientoService.salida(idProducto,cantidad,usuario(session),observacion); return "redirect:/movimientos/listado"; } catch(Exception e){ r.addFlashAttribute("error", e.getMessage()); return "redirect:/movimientos/salida"; } }
    private Integer usuario(HttpSession s){ Usuario u=(Usuario)s.getAttribute("usuarioSesion"); return u != null ? u.getIdUsuario() : 1; }
}
