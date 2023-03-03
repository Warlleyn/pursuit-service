package com.example.pursuitservice.bo;

import com.example.pursuitservice.domain.sql.Imovel;
import com.example.pursuitservice.dto.ImovelDTO;

import java.util.List;

public interface ImovelBO {

    Imovel save(Imovel imovel);
    Imovel getById(Long id);
    List<Imovel> findByUser(String emailUser);
    List<Imovel> findByPerimeter(String cep, Integer raio);
    List<Imovel> findAll();
    Imovel saveByDTO(ImovelDTO imovelDTO, String emailUser);
    Imovel update(ImovelDTO imovelDTO, Long idImovel);
    Imovel deleteById(Long id);

}
