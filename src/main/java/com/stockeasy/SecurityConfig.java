package com.stockeasy;

import com.stockeasy.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String[] PUBLIC_URLS = {
        "/login", "/webjars/**", "/css/**", "/js/**", "/acceso_denegado"
    };
    public static final String[] ADMIN_URLS = {
        "/usuarios/**"
    };
    public static final String[] ADMIN_ENCARGADO_URLS = {
        "/productos/**", "/categorias/**", "/proveedores/**", "/movimientos/**"
    };
    public static final String[] TODOS_URLS = {
        "/dashboard", "/reportes/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UsuarioRepository usuarioRepository) throws Exception {
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers(PUBLIC_URLS).permitAll()
                .requestMatchers(ADMIN_URLS).hasRole("ADMINISTRADOR")
                .requestMatchers(ADMIN_ENCARGADO_URLS).hasAnyRole("ADMINISTRADOR", "ENCARGADO")
                .requestMatchers(TODOS_URLS).hasAnyRole("ADMINISTRADOR", "ENCARGADO", "CONSULTA")
                .requestMatchers("/").permitAll()
                .anyRequest().authenticated()
        );

        http.formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("correo")
                .passwordParameter("contrasena")
                .successHandler(authenticationSuccessHandler(usuarioRepository))
                .failureUrl("/login?error=true")
                .permitAll()
        );

        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
        );

        http.exceptionHandling(exceptions -> exceptions
                .accessDeniedPage("/acceso_denegado")
        );

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(UsuarioRepository usuarioRepository) {
        return (request, response, authentication) -> {
            usuarioRepository.findByCorreoAndEstadoTrue(authentication.getName())
                    .ifPresent(u -> request.getSession().setAttribute("usuarioSesion", u));
            response.sendRedirect(request.getContextPath() + "/dashboard");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}