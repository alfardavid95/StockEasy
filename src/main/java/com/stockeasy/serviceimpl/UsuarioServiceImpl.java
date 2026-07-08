package com.stockeasy.serviceimpl;
import com.stockeasy.domain.Usuario;
import com.stockeasy.repository.UsuarioRepository;
import com.stockeasy.service.UsuarioService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired private UsuarioRepository repo;
    @Override @Transactional(readOnly=true) public List<Usuario> getUsuarios(){ return repo.findAll(); }
    @Override @Transactional(readOnly=true) public Usuario getUsuario(Integer id){ return repo.findById(id).orElse(null); }
    @Override @Transactional public void save(Usuario u){ if(u.getEstado()==null)u.setEstado(true); repo.save(u); }
    @Override @Transactional public void desactivar(Integer id){ Usuario u=getUsuario(id); if(u!=null){u.setEstado(false); repo.save(u);} }
    @Override @Transactional(readOnly=true) public Optional<Usuario> login(String correo, String contrasena){ return repo.findByCorreoAndContrasenaAndEstadoTrue(correo, contrasena); }
}
