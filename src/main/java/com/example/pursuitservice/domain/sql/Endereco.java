package com.example.pursuitservice.domain.sql;

import com.example.pursuitservice.dto.EnderecoDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "endereco", catalog = "diversos")
@NoArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_endereco")
    private Long codigo;
    @Column(name = "ds_logradouro")
    private String logradouro;
    @Column(name = "nr_logradouro")
    private String numeroLogradouro;
    @Column(name = "ds_bairro")
    private String bairro;
    @Column(name = "nr_cep")
    private String cep;
    @Column(name = "ds_latitude")
    private String latitude;
    @Column(name = "ds_longitude")
    private String longitude;
    @Column(name = "dt_exclusao")
    private LocalDate dataExclusao;
    @Column(name = "dt_alteracao")
    private LocalDate dataAlteracao;

    public Endereco(EnderecoDTO enderecoDTO) {
        this.logradouro = enderecoDTO.getLogradouro();
        this.numeroLogradouro = enderecoDTO.getNumero();
        this.bairro = enderecoDTO.getBairro();
        this.cep = enderecoDTO.getCep();
        this.dataAlteracao = LocalDate.now();
    }
}
