package com.stockeasy.repository;

import com.stockeasy.domain.MovimientoInventario;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Integer> {
    List<MovimientoInventario> findTop5ByOrderByFechaDesc();
    List<MovimientoInventario> findByProductoIdProductoOrderByFechaDesc(Integer idProducto);
    long countByTipoMovimientoNombreAndFechaBetween(String nombre, LocalDateTime inicio, LocalDateTime fin);
    @Query("""
           SELECT m FROM MovimientoInventario m
           WHERE (:texto IS NULL OR :texto = '' OR LOWER(m.producto.nombre) LIKE LOWER(CONCAT('%', :texto, '%')) OR LOWER(m.producto.codigo) LIKE LOWER(CONCAT('%', :texto, '%')))
             AND (:tipo IS NULL OR :tipo = '' OR m.tipoMovimiento.nombre = :tipo)
             AND (:fechaInicio IS NULL OR m.fecha >= :fechaInicio)
             AND (:fechaFin IS NULL OR m.fecha <= :fechaFin)
           ORDER BY m.fecha DESC
           """)
    List<MovimientoInventario> buscar(@Param("texto") String texto, @Param("tipo") String tipo, @Param("fechaInicio") LocalDateTime fechaInicio, @Param("fechaFin") LocalDateTime fechaFin);
}
