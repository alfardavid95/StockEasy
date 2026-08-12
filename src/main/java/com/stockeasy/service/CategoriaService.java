package com.stockeasy.service;

import com.stockeasy.domain.Categoria;
import java.util.List;

public interface CategoriaService {

    List<Categoria> getCategorias();

    List<Categoria> buscarCategorias(String texto);

    Categoria getCategoria(Integer idCategoria);

    void save(Categoria categoria);

    void desactivar(Integer idCategoria);
}