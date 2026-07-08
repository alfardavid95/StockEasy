package com.stockeasy.serviceimpl;
import com.stockeasy.domain.Proveedor;
import com.stockeasy.repository.ProveedorRepository;
import com.stockeasy.service.ProveedorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ProveedorServiceImpl implements ProveedorService {
    @Autowired private ProveedorRepository repo;
    @Override @Transactional(readOnly=true) public List<Proveedor> getProveedores(){ return repo.findAll(); }
    @Override @Transactional(readOnly=true) public Proveedor getProveedor(Integer id){ return repo.findById(id).orElse(null); }
    @Override @Transactional public void save(Proveedor p){ if(p.getEstado()==null)p.setEstado(true); repo.save(p); }
    @Override @Transactional public void desactivar(Integer id){ Proveedor p=getProveedor(id); if(p!=null){p.setEstado(false); repo.save(p);} }
}
