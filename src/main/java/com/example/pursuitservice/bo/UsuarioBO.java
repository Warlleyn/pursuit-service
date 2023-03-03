package com.example.pursuitservice.bo;

import com.example.pursuitservice.domain.sql.Usuario;
import com.example.pursuitservice.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioBO {
    Usuario getUserByEmail(String email);
    Usuario saveByDTO(UsuarioDTO usuarioDTO);
    List<Usuario> findByDataExclusaoIsNull();
    void deleteById(Long id);
    Usuario updateUser(UsuarioDTO usuarioDTO, Long idUser);
    Usuario getById(Long id);
}
