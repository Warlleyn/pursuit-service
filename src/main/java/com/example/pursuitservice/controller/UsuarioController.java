package com.example.pursuitservice.controller;

import com.example.pursuitservice.bo.UsuarioBO;
import com.example.pursuitservice.domain.sql.Usuario;
import com.example.pursuitservice.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioBO usuarioBO;
    @GetMapping(value = "/find/all")
    public List<Usuario> findAll(){
        return usuarioBO.findByDataExclusaoIsNull();
    }
    @GetMapping(value = "/find/{id}")
    public Usuario findAll(@PathVariable Long id){
        return usuarioBO.getById(id);
    }
    @PostMapping(value = "/save")
    public Usuario saveUser(@RequestBody UsuarioDTO usuarioDTO){
       return usuarioBO.saveByDTO(usuarioDTO);
    }
    @DeleteMapping(value = "{id}")
    public void deleteById(@PathVariable Long id){
        usuarioBO.deleteById(id);
    }
    @PutMapping(value = "{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO){
        usuarioBO.updateUser(usuarioDTO, id);
    }
}
