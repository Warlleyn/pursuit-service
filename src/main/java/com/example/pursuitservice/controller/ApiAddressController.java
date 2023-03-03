package com.example.pursuitservice.controller;

import com.example.pursuitservice.dto.ResponseViaCepApiDTO;
import com.example.pursuitservice.helpers.GeocodingHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApiAddressController {

    private final GeocodingHelper geocodingHelper;

    @GetMapping("/find/address/{cep}")
    public ResponseEntity<ResponseViaCepApiDTO> findAddressByCep(@PathVariable String cep){
        return geocodingHelper.getAddressByCep(cep);
    }
}
