package com.stockeasy.service;
import com.stockeasy.domain.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> getUsuarios();
    Usuario getUsuario(Integer id);
    void save(Usuario u);
    void desactivar(Integer id);
}