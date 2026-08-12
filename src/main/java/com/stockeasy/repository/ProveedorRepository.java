package com.stockeasy.repository;

import com.stockeasy.domain.Proveedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

    List<Proveedor> findByNombreContainingIgnoreCase(String nombre);
}