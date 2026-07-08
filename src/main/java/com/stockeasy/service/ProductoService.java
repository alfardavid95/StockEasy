package com.stockeasy.service;
import com.stockeasy.domain.Producto;
import java.util.List;
public interface ProductoService {
    List<Producto> getProductos(String texto, Integer idCategoria, Integer idProveedor, Boolean estado);
    List<Producto> getBajoStock();
    Producto getProducto(Integer id);
    void save(Producto producto);
    void desactivar(Integer id);
    long countActivos();
}
