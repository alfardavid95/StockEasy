package com.stockeasy.service;

import com.stockeasy.domain.Categoria;
import java.util.List;

public interface CategoriaService {
    List<Categoria> getCategorias();
    Categoria getCategoria(Integer id);
    void save(Categoria c);
    void desactivar(Integer id);
    boolean existeNombre(String nombre, Integer idCategoria);
}
