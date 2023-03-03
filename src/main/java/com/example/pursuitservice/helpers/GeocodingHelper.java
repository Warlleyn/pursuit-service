package com.example.pursuitservice.helpers;


import com.example.pursuitservice.dto.ResponseViaCepApiDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Log4j2
public class GeocodingHelper {

    private static final String urlViaCep = "https://viacep.com.br/ws/%s/json";
    private final RestTemplate restTemplate;

    public ResponseEntity<ResponseViaCepApiDTO> getAddressByCep(String cep){
        log.info("Buscando dados na API Via CEP");
        try {
            return restTemplate.getForEntity(String.format(urlViaCep, cep), ResponseViaCepApiDTO.class);
        }catch (Exception e){
            throw new RuntimeException("Endereço não encontrado");
        }
    }

}
