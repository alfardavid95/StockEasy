package com.stockeasy.serviceimpl;

import com.stockeasy.domain.Producto;
import com.stockeasy.repository.ProductoRepository;
import com.stockeasy.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> getProductos(
            String texto,
            Integer idCategoria,
            Integer idProveedor,
            Boolean estado
    ) {
        return productoRepository.buscar(
                texto,
                idCategoria,
                idProveedor,
                estado
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Producto getProducto(Integer idProducto) {
        return productoRepository.findById(idProducto).orElse(null);
    }

    @Override
    @Transactional
    public void save(Producto producto) {

        if (producto.getEstado() == null) {
            producto.setEstado(true);
        }

        if (producto.getStockActual() == null) {
            producto.setStockActual(0);
        }

        if (producto.getStockMinimo() == null) {
            producto.setStockMinimo(0);
        }

        productoRepository.save(producto);
    }

    @Override
    @Transactional
    public void desactivar(Integer idProducto) {

        Producto producto = getProducto(idProducto);

        if (producto != null) {
            producto.setEstado(false);
            productoRepository.save(producto);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeCodigo(String codigo, Integer idProducto) {

        if (idProducto == null) {
            idProducto = 0;
        }

        return productoRepository.existsByCodigoAndIdProductoNot(
                codigo,
                idProducto
        );
    }

    @Override
    @Transactional(readOnly = true)
    public long countActivos() {
        return productoRepository.countByEstadoTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> getBajoStock() {
        return productoRepository.bajoStock(
                null,
                null
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> getProductosBajoStock(
            Integer idCategoria,
            Integer idProveedor
    ) {
        return productoRepository.bajoStock(
                idCategoria,
                idProveedor
        );
    }
}