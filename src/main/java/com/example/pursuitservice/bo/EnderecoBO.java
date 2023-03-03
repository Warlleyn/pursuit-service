package com.example.pursuitservice.bo;

import com.example.pursuitservice.domain.sql.Endereco;
import com.example.pursuitservice.dto.EnderecoDTO;

public interface EnderecoBO {
    Endereco saveByDTO(EnderecoDTO enderecoDTO);
    Endereco getById(Long id);
    Endereco updateByDTO(EnderecoDTO enderecoDTO, Long codigoEndereco);
    Endereco deleteByEntity(Endereco endereco);
}
