package com.stockeasy.repository;

import com.stockeasy.domain.Categoria;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    List<Categoria> findByNombreContainingIgnoreCase(String nombre);
}