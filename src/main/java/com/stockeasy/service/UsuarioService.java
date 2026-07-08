package com.stockeasy.service;
import com.stockeasy.domain.Usuario;
import java.util.List;
import java.util.Optional;
public interface UsuarioService { List<Usuario> getUsuarios(); Usuario getUsuario(Integer id); void save(Usuario u); void desactivar(Integer id); Optional<Usuario> login(String correo, String contrasena); }
