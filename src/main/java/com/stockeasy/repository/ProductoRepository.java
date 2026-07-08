package com.stockeasy.repository;
import com.stockeasy.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    long countByEstadoTrue();
    @Query("""
           SELECT p FROM Producto p
           WHERE (:texto IS NULL OR :texto = '' OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :texto, '%')) OR LOWER(p.codigo) LIKE LOWER(CONCAT('%', :texto, '%')))
             AND (:idCategoria IS NULL OR p.categoria.idCategoria = :idCategoria)
             AND (:idProveedor IS NULL OR p.proveedor.idProveedor = :idProveedor)
             AND (:estado IS NULL OR p.estado = :estado)
           ORDER BY p.idProducto ASC
           """)
    List<Producto> buscar(@Param("texto") String texto, @Param("idCategoria") Integer idCategoria, @Param("idProveedor") Integer idProveedor, @Param("estado") Boolean estado);
    @Query("SELECT p FROM Producto p WHERE p.estado = true AND p.stockActual <= p.stockMinimo ORDER BY p.stockActual ASC")
    List<Producto> bajoStock();
}
