package com.example.pursuitservice.dto;

import com.example.pursuitservice.domain.sql.Endereco;
import com.example.pursuitservice.enums.TipoImovel;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ImovelDTO {
    private TipoImovel tipoImovel;
    private Boolean garagem;
    private Long quantidadeQuartos;
    private Long quantidadeCamas;
    private EnderecoDTO enderecoDTO;
}
