package com.stockeasy.service;

import com.stockeasy.domain.Producto;
import java.util.List;

public interface ProductoService {

    List<Producto> getProductos(
            String texto,
            Integer idCategoria,
            Integer idProveedor,
            Boolean estado
    );

    Producto getProducto(Integer idProducto);

    void save(Producto producto);

    void desactivar(Integer idProducto);

    boolean existeCodigo(String codigo, Integer idProducto);

    long countActivos();

    List<Producto> getBajoStock();

    List<Producto> getProductosBajoStock(
            Integer idCategoria,
            Integer idProveedor
    );
}