package com.stockeasy.serviceimpl;

import com.stockeasy.domain.*;
import com.stockeasy.repository.*;
import com.stockeasy.service.MovimientoInventarioService;
import java.time.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
 public class MovimientoInventarioServiceImpl implements MovimientoInventarioService {
    @Autowired private MovimientoInventarioRepository movRepo;
    @Autowired private ProductoRepository productoRepo;
    @Autowired private TipoMovimientoRepository tipoRepo;
    @Autowired private UsuarioRepository usuarioRepo;

    @Override
    @Transactional(readOnly = true)
    public List<MovimientoInventario> getMovimientos(String texto, String tipo, LocalDate fechaInicio, LocalDate fechaFin) {
        LocalDateTime inicio = (fechaInicio != null) ? fechaInicio.atStartOfDay() : null;
        LocalDateTime fin = (fechaFin != null) ? fechaFin.atTime(LocalTime.MAX) : null;
        return movRepo.buscar(texto, tipo, inicio, fin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovimientoInventario> getUltimos() {
        return movRepo.findTop5ByOrderByFechaDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovimientoInventario> getPorProducto(Integer idProducto) {
        return movRepo.findByProductoIdProductoOrderByFechaDesc(idProducto);
    }

    @Override
    @Transactional
    public void entrada(Integer idProducto, Integer cantidad, Integer idUsuario, String obs) {
        registrar(idProducto, cantidad, idUsuario, obs, "Entrada");
    }

    @Override
    @Transactional
    public void salida(Integer idProducto, Integer cantidad, Integer idUsuario, String obs) {
        registrar(idProducto, cantidad, idUsuario, obs, "Salida");
    }

    private void registrar(Integer idProducto, Integer cantidad, Integer idUsuario, String obs, String tipoNombre) {
        Producto p = productoRepo.findById(idProducto).orElseThrow();
        Usuario u = usuarioRepo.findById(idUsuario).orElseThrow();
        TipoMovimiento t = tipoRepo.findByNombre(tipoNombre).orElseThrow();
        int anterior = p.getStockActual();
        int nuevo = tipoNombre.equals("Entrada") ? anterior + cantidad : anterior - cantidad;
        if (nuevo < 0) {throw new IllegalArgumentException("La cantidad a retirar no puede ser mayor al stock disponible.");}
        p.setStockActual(nuevo);
        productoRepo.save(p);
        MovimientoInventario m = new MovimientoInventario();
        m.setProducto(p);
        m.setUsuario(u);
        m.setTipoMovimiento(t);
        m.setCantidad(cantidad);
        m.setStockAnterior(anterior);
        m.setStockNuevo(nuevo);
        m.setObservacion(obs);
        movRepo.save(m);
    }
    @Override @Transactional(readOnly=true) public long entradasMes(){ return contarMes("Entrada"); }
    @Override @Transactional(readOnly=true) public long salidasMes(){ return contarMes("Salida"); }
    private long contarMes(String tipo){ LocalDate h=LocalDate.now(); return movRepo.countByTipoMovimientoNombreAndFechaBetween(tipo, h.withDayOfMonth(1).atStartOfDay(), h.withDayOfMonth(h.lengthOfMonth()).atTime(LocalTime.MAX)); }
}
