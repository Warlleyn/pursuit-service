package com.example.pursuitservice.domain.sql;

import com.example.pursuitservice.dto.ImovelDTO;
import com.example.pursuitservice.enums.TipoImovel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "imovel", catalog = "imoveis")
@NoArgsConstructor
public class Imovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_imovel")
    private Long codigo;
    @Enumerated(EnumType.STRING)
    @Column(name = "ds_tipo_imovel")
    private TipoImovel tipoImovel;
    @Column(name = "in_garagem")
    private Boolean garagem;
    @Column(name = "nr_quartos")
    private Long quantidadeQuartos;
    @Column(name = "nr_camas")
    private Long quantidadeCamas;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_usuario")
    private Usuario usuarioResponsavel;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_endereco")
    private Endereco endereco;
    @Column(name = "dt_exclusao")
    private LocalDate dataExclusao;
    @Column(name = "dt_alteracao")
    private LocalDate dataAlteracao;

    public Imovel(ImovelDTO imovelDTO, Usuario usuario, Endereco endereco) {
        this.tipoImovel = imovelDTO.getTipoImovel();
        this.garagem = imovelDTO.getGaragem();
        this.quantidadeQuartos = imovelDTO.getQuantidadeQuartos();
        this.quantidadeCamas = imovelDTO.getQuantidadeCamas();
        this.usuarioResponsavel = usuario;
        this.endereco = endereco;
        this.dataAlteracao = LocalDate.now();
    }
}
