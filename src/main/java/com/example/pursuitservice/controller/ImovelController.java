package com.example.pursuitservice.controller;

import com.example.pursuitservice.bo.ImovelBO;
import com.example.pursuitservice.domain.sql.Imovel;
import com.example.pursuitservice.dto.ImovelDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/imovel")
@RequiredArgsConstructor
public class ImovelController {

    private final ImovelBO imovelBO;

    @GetMapping(value = "/find/user")
    public List<Imovel> findByUser(Principal principal){
        return imovelBO.findByUser(principal.getName());
    }
    @GetMapping(value = "/find/perimeter")
    public List<Imovel> findByPerimeter(@RequestParam(value = "cep") String cep,
                                        @RequestParam(value = "raio", required = false, defaultValue = "5") Integer raio){
        return imovelBO.findByPerimeter(cep, raio);
    }
    @GetMapping(value = "find/all")
    public List<Imovel> findAll(){
        return imovelBO.findAll();
    }
    @PostMapping("/save")
    public Imovel saveByDTO(@RequestBody ImovelDTO imovelDTO, Principal principal){
        return imovelBO.saveByDTO(imovelDTO, principal.getName());
    }
    @PutMapping("/update/{id}")
    public Imovel update(@PathVariable Long id, @RequestBody ImovelDTO imovelDTO){
        return imovelBO.update(imovelDTO, id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        imovelBO.deleteById(id);
    }
}
