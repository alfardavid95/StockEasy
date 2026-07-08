package com.stockeasy.service;
import com.stockeasy.domain.Proveedor;
import java.util.List;
public interface ProveedorService { List<Proveedor> getProveedores(); Proveedor getProveedor(Integer id); void save(Proveedor p); void desactivar(Integer id); }
