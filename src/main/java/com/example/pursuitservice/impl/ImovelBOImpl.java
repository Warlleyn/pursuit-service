package com.example.pursuitservice.impl;

import com.example.pursuitservice.bo.EnderecoBO;
import com.example.pursuitservice.bo.ImovelBO;
import com.example.pursuitservice.bo.UsuarioBO;
import com.example.pursuitservice.domain.sql.Endereco;
import com.example.pursuitservice.domain.sql.Imovel;
import com.example.pursuitservice.domain.sql.Usuario;
import com.example.pursuitservice.dto.ImovelDTO;
import com.example.pursuitservice.dto.LatLongAddress;
import com.example.pursuitservice.enums.MensagensErro;
import com.example.pursuitservice.repositories.sql.ImovelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ImovelBOImpl implements ImovelBO {
    private final ImovelRepository imovelRepository;
    private final UsuarioBO usuarioBO;
    private final EnderecoBO enderecoBO;

    public Imovel update(ImovelDTO imovelDTO, Long idImovel){
        Imovel imovel = getById(idImovel);
        Endereco endereco = enderecoBO.saveByDTO(imovelDTO.getEnderecoDTO());
        return save(new Imovel(imovelDTO, imovel.getUsuarioResponsavel(), endereco));
    }

    public Imovel saveByDTO(ImovelDTO imovelDTO, String emailUser){
        Usuario usuario = usuarioBO.getUserByEmail(emailUser);
        validateParamsImovel(imovelDTO);
        Endereco endereco = enderecoBO.saveByDTO(imovelDTO.getEnderecoDTO());
        return save(new Imovel(imovelDTO, usuario, endereco));
    }

    private void validateParamsImovel(ImovelDTO imovelDTO) {
        if(Objects.isNull(imovelDTO.getTipoImovel()))
            throw new RuntimeException(MensagensErro.TIPO_IMOVEL_EMPTY.getDescricao());
        if(Objects.isNull(imovelDTO.getGaragem()))
            throw new RuntimeException(MensagensErro.GARAGEM_EMPTY.getDescricao());
        if(Objects.isNull(imovelDTO.getQuantidadeCamas()))
            throw new RuntimeException(MensagensErro.QUANTIDADE_CAMAS_EMPTY.getDescricao());
        if(Objects.isNull(imovelDTO.getQuantidadeQuartos()))
            throw new RuntimeException(MensagensErro.QUANTIDADE_QUARTOS_EMPTY.getDescricao());
    }

    public Imovel deleteById(Long id){
        Imovel imovel = getById(id);
        imovel.setDataExclusao(LocalDate.now());
        enderecoBO.deleteByEntity(imovel.getEndereco());
        return save(imovel);
    }

    @Transactional
    public Imovel save(Imovel imovel){
        return imovelRepository.save(imovel);
    }

    @Transactional(readOnly = true)
    public Imovel getById(Long id){
        return imovelRepository.findById(id).orElseThrow(() -> new RuntimeException(MensagensErro.IMOVEL_NOT_FOUND.getDescricao()));
    }

    @Transactional
    public List<Imovel> findByUser(String emailUser){
        usuarioBO.getUserByEmail(emailUser);
        return imovelRepository.findByEmailAndDataExclusaoIsNull(emailUser);
    }

    public List<Imovel> findByPerimeter(String cep, Integer raio){
        LatLongAddress latLongByCep = getLatLongByCep(cep);
        return findByAddressByPerimeter(latLongByCep, raio);
    }

    private LatLongAddress getLatLongByCep(String cep) {
        // Metodo que deveria chamar api para consulta de lat long via cep
        LatLongAddress latLongAddress = new LatLongAddress();
        latLongAddress.setLatitude("-29.1776969");
        latLongAddress.setLongitude("-51.2182026");
        return latLongAddress;
    }

    private List<Imovel> findByAddressByPerimeter(LatLongAddress latLongAddress, Integer raio){
        return imovelRepository.findRadiansByLatLong(latLongAddress.getLatitude(), latLongAddress.getLongitude(), raio);
    }

    public List<Imovel> findAll(){
       return imovelRepository.findByDataExclusaoIsNull();
    }
}
