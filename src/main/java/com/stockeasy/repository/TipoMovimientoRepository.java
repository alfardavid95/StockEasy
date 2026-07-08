package com.stockeasy.repository;
import com.stockeasy.domain.TipoMovimiento;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TipoMovimientoRepository extends JpaRepository<TipoMovimiento, Integer> {
    Optional<TipoMovimiento> findByNombre(String nombre);
}
