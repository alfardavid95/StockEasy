package com.stockeasy.serviceimpl;

import com.stockeasy.domain.Categoria;
import com.stockeasy.repository.CategoriaRepository;
import com.stockeasy.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> getCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> buscarCategorias(String texto) {

        if (texto == null || texto.isBlank()) {
            return categoriaRepository.findAll();
        }

        return categoriaRepository.findByNombreContainingIgnoreCase(texto);
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria getCategoria(Integer idCategoria) {
        return categoriaRepository.findById(idCategoria).orElse(null);
    }

    @Override
    @Transactional
    public void save(Categoria categoria) {

        if (categoria.getEstado() == null) {
            categoria.setEstado(true);
        }

        categoriaRepository.save(categoria);
    }

    @Override
    @Transactional
    public void desactivar(Integer idCategoria) {

        Categoria categoria = getCategoria(idCategoria);

        if (categoria != null) {
            categoria.setEstado(false);
            categoriaRepository.save(categoria);
        }
    }
}