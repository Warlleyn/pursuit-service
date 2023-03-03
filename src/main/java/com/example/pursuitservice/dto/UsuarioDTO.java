package com.example.pursuitservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

    private String nome;
    private String email;
    private EnderecoDTO enderecoDTO;
}
