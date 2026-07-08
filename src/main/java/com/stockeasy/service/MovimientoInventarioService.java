package com.stockeasy.service;
import com.stockeasy.domain.MovimientoInventario;
import java.util.List;
public interface MovimientoInventarioService {
    List<MovimientoInventario> getMovimientos(String texto, String tipo);
    List<MovimientoInventario> getUltimos();
    List<MovimientoInventario> getPorProducto(Integer idProducto);
    void entrada(Integer idProducto, Integer cantidad, Integer idUsuario, String observacion);
    void salida(Integer idProducto, Integer cantidad, Integer idUsuario, String observacion);
    long entradasMes(); long salidasMes();
}
