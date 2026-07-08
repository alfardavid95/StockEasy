package com.stockeasy.repository;
import com.stockeasy.domain.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreoAndContrasenaAndEstadoTrue(String correo, String contrasena);
}
