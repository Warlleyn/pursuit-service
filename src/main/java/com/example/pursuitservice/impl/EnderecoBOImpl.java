package com.example.pursuitservice.impl;


import com.example.pursuitservice.bo.EnderecoBO;
import com.example.pursuitservice.domain.sql.Endereco;
import com.example.pursuitservice.dto.EnderecoDTO;
import com.example.pursuitservice.enums.MensagensErro;
import com.example.pursuitservice.helpers.GeocodingHelper;
import com.example.pursuitservice.repositories.sql.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;
@Component
@RequiredArgsConstructor
public class EnderecoBOImpl implements EnderecoBO {
    private final EnderecoRepository enderecoRepository;
    private final GeocodingHelper geocodingHelper;

    @Transactional
    public Endereco saveByDTO(EnderecoDTO enderecoDTO){
        validateParamsAddress(enderecoDTO);
        validateCep(enderecoDTO.getCep());
        return saveByEntity(new Endereco(enderecoDTO));
    }
    private void validateParamsAddress(EnderecoDTO enderecoDTO) {
        if(Objects.isNull(enderecoDTO.getLogradouro()))
            throw new RuntimeException(MensagensErro.LOGRADOURO_EMPTY.getDescricao());
        if(Objects.isNull(enderecoDTO.getCep()))
            throw new RuntimeException(MensagensErro.CEP_EMPTY.getDescricao());
        if(Objects.isNull(enderecoDTO.getBairro()))
            throw new RuntimeException(MensagensErro.BAIRRO_EMPTY.getDescricao());
        if(Objects.isNull(enderecoDTO.getNumero()))
            throw new RuntimeException(MensagensErro.NUMERO_LOGRADOURO_EMPTY.getDescricao());
    }

    public Endereco updateByDTO(EnderecoDTO enderecoDTO, Long codigoEndereco){
        Endereco endereco = getById(codigoEndereco);
        String newCep = enderecoDTO.getCep();
        if(Objects.nonNull(newCep)){
            validateCep(newCep);
            endereco.setCep(newCep);
        }
        if(Objects.nonNull(enderecoDTO.getNumero()))
            endereco.setNumeroLogradouro(enderecoDTO.getNumero());
        if(Objects.nonNull(enderecoDTO.getComplemento()))
            endereco.setBairro(enderecoDTO.getBairro());
        if(Objects.nonNull(enderecoDTO.getLogradouro()))
            endereco.setLogradouro(enderecoDTO.getLogradouro());

        return saveByEntity(endereco);
    }

    public Endereco deleteByEntity(Endereco endereco){
        endereco.setDataExclusao(LocalDate.now());
        return saveByEntity(endereco);
    }
    @Transactional(readOnly = true)
    public Endereco getById(Long id){
        return enderecoRepository.findById(id).orElseThrow(() -> new RuntimeException(MensagensErro.ENDERECO_NOT_FOUN.getDescricao()));
    }
    private void validateCep(String cep) {geocodingHelper.getAddressByCep(cep);}
    private Endereco saveByEntity(Endereco endereco){
        return enderecoRepository.save(endereco);
    }
}
