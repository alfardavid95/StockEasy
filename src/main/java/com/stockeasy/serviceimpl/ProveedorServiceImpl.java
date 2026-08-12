package com.stockeasy.serviceimpl;

import com.stockeasy.domain.Proveedor;
import com.stockeasy.repository.ProveedorRepository;
import com.stockeasy.service.ProveedorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Proveedor> getProveedores() {
        return proveedorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Proveedor> buscarProveedores(String texto) {

        if (texto == null || texto.isBlank()) {
            return proveedorRepository.findAll();
        }

        return proveedorRepository.findByNombreContainingIgnoreCase(texto);
    }

    @Override
    @Transactional(readOnly = true)
    public Proveedor getProveedor(Integer idProveedor) {
        return proveedorRepository.findById(idProveedor).orElse(null);
    }

    @Override
    @Transactional
    public void save(Proveedor proveedor) {

        if (proveedor.getEstado() == null) {
            proveedor.setEstado(true);
        }

        proveedorRepository.save(proveedor);
    }

    @Override
    @Transactional
    public void desactivar(Integer idProveedor) {

        Proveedor proveedor = getProveedor(idProveedor);

        if (proveedor != null) {
            proveedor.setEstado(false);
            proveedorRepository.save(proveedor);
        }
    }
}