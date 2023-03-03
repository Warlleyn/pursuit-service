package com.example.pursuitservice.impl;

import com.example.pursuitservice.bo.EnderecoBO;
import com.example.pursuitservice.bo.UsuarioBO;
import com.example.pursuitservice.domain.sql.Endereco;
import com.example.pursuitservice.domain.sql.Usuario;
import com.example.pursuitservice.dto.UsuarioDTO;
import com.example.pursuitservice.enums.MensagensErro;
import com.example.pursuitservice.repositories.sql.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
@Component
@RequiredArgsConstructor
public class UsuarioBOImpl implements UsuarioBO {
    private final UsuarioRepository usuarioRepository;
    private final EnderecoBO enderecoBO;

    @Transactional
    public Usuario saveByDTO(UsuarioDTO usuarioDTO){
        validateParamsUser(usuarioDTO);
        Endereco endereco = enderecoBO.saveByDTO(usuarioDTO.getEnderecoDTO());
        return save(new Usuario(usuarioDTO, endereco));
    }
    private void validateParamsUser(UsuarioDTO usuarioDTO) {
        if(Objects.isNull(usuarioDTO.getNome()))
            throw new RuntimeException(MensagensErro.NOME_EMPTY.getDescricao());
        if(Objects.isNull(usuarioDTO.getEmail()))
            throw new RuntimeException(MensagensErro.EMAIL_EMPTY.getDescricao());
        if(Objects.isNull(usuarioDTO.getEnderecoDTO()))
            throw new RuntimeException(MensagensErro.ENDERECO_IMOVEL_EMPTY.getDescricao());
    }
    @Transactional
    public Usuario getUserByEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(()
                -> new RuntimeException(MensagensErro.USUARIO_NOT_FOUND.getDescricao()));
    }
    @Transactional
    public List<Usuario> findByDataExclusaoIsNull(){
        return usuarioRepository.findByDataExclusaoIsNull();
    }
    @Transactional
    public void deleteById(Long id){
        Usuario usuario = getById(id);
        usuario.setDataExclusao(LocalDate.now());
        enderecoBO.deleteByEntity(usuario.getEndereco());
        save(usuario);
    }

    public Usuario updateUser(UsuarioDTO usuarioDTO, Long idUser){
        Usuario usuario = getById(idUser);
        return save(mapFrom(usuario, usuarioDTO));
    }

    private Usuario mapFrom(Usuario usuario, UsuarioDTO usuarioDTO) {
        if(Objects.nonNull(usuarioDTO.getNome()))
            usuario.setNome(usuario.getNome());
        if(Objects.nonNull(usuarioDTO.getEmail()))
            usuario.setEmail(usuario.getEmail());
        if(Objects.nonNull(usuarioDTO.getEnderecoDTO())){
            Endereco endereco = enderecoBO.saveByDTO(usuarioDTO.getEnderecoDTO());
            usuario.setEndereco(endereco);
        }
        return usuario;
    }

    @Transactional(readOnly = true)
    public Usuario getById(Long id){
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }
    private Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

}
