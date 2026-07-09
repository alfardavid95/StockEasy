package com.stockeasy.serviceimpl;
import com.stockeasy.domain.Producto;
import com.stockeasy.repository.ProductoRepository;
import com.stockeasy.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired private ProductoRepository repo;
    @Override @Transactional(readOnly=true)
    public List<Producto> getProductos(String texto, Integer idCategoria, Integer idProveedor, Boolean estado) { return repo.buscar(texto, idCategoria, idProveedor, estado); }
    @Override @Transactional(readOnly=true)
    public List<Producto> getBajoStock() { return repo.bajoStock(); }
    @Override @Transactional(readOnly=true)
    public Producto getProducto(Integer id) { return repo.findById(id).orElse(null); }
    @Override @Transactional
    public void save(Producto p) { if (p.getEstado()==null) p.setEstado(true); repo.save(p); }
    @Override @Transactional
    public void desactivar(Integer id) { Producto p=getProducto(id); if(p!=null){p.setEstado(false); repo.save(p);} }
    @Override @Transactional(readOnly=true)
    public long countActivos() { return repo.countByEstadoTrue(); }
    @Override @Transactional(readOnly=true)
    public boolean existeCodigo(String codigo, Integer idProducto) { return repo.existsByCodigoAndIdProductoNot(codigo, idProducto == null ? -1 : idProducto);}
}
