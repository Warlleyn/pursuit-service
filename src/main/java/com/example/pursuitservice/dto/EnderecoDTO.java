package com.example.pursuitservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {
    private String logradouro;
    private String numero;
    private String bairro;
    private String cep;
    private String complemento;
}
