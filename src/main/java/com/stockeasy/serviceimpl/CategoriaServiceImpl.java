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
    @Autowired private CategoriaRepository repo;
    @Override @Transactional(readOnly=true) public List<Categoria> getCategorias(){ return repo.findAll(); }
    @Override @Transactional(readOnly=true) public Categoria getCategoria(Integer id){ return repo.findById(id).orElse(null); }
    @Override @Transactional public void save(Categoria c){ if(c.getEstado()==null)c.setEstado(true); repo.save(c); }
    @Override @Transactional public void desactivar(Integer id){ Categoria c=getCategoria(id); if(c!=null){c.setEstado(false); repo.save(c);} }
}
