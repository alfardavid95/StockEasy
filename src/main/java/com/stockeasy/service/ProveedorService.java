package com.stockeasy.service;

import com.stockeasy.domain.Proveedor;
import java.util.List;

public interface ProveedorService {

    List<Proveedor> getProveedores();

    List<Proveedor> buscarProveedores(String texto);

    Proveedor getProveedor(Integer idProveedor);

    void save(Proveedor proveedor);

    void desactivar(Integer idProveedor);
}