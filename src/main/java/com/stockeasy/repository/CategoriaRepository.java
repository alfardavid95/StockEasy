package com.stockeasy.repository;
import com.stockeasy.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
