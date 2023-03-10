package com.example.pursuitservice.repositories.sql;

import com.example.pursuitservice.domain.sql.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByDataExclusaoIsNull();
    Optional<Usuario> findByEmail(String email);
}
