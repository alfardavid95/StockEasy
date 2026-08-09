package com.stockeasy.serviceimpl;
import com.stockeasy.domain.Usuario;
import com.stockeasy.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements com.stockeasy.service.UsuarioService {
    @Autowired private UsuarioRepository repo;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override @Transactional(readOnly=true)
    public List<Usuario> getUsuarios(){ return repo.findAll(); }

    @Override @Transactional(readOnly=true)
    public Usuario getUsuario(Integer id){ return repo.findById(id).orElse(null); }

    @Override @Transactional
    public void save(Usuario u){
        if (u.getEstado() == null) u.setEstado(true);

        if (u.getIdUsuario() != null) {
            // Editando un usuario existente
            Usuario existente = repo.findById(u.getIdUsuario()).orElse(null);
            if (u.getContrasena() == null || u.getContrasena().isBlank()) {
                u.setContrasena(existente != null ? existente.getContrasena() : null);
            } else {
                u.setContrasena(passwordEncoder.encode(u.getContrasena()));
            }
        } else {
            // Usuario nuevo
            u.setContrasena(passwordEncoder.encode(u.getContrasena()));
        }
        repo.save(u);
    }

    @Override @Transactional
    public void desactivar(Integer id){ Usuario u=getUsuario(id); if(u!=null){u.setEstado(false); repo.save(u);} }
}