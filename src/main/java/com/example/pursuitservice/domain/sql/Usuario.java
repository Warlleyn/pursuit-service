package com.example.pursuitservice.domain.sql;

import com.example.pursuitservice.dto.UsuarioDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "usuario", catalog = "pessoa")
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_usuario")
    private Long codigo;
    @NonNull
    @Column(name = "ds_nome")
    private String nome;
    @NonNull
    @Column(name = "ds_email")
    private String email;
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_endereco")
    private Endereco endereco;
    @Column(name = "dt_exclusao")
    private LocalDate dataExclusao;
    @Column(name = "dt_alteracao")
    private LocalDate dataAlteracao;

    public Usuario(UsuarioDTO usuarioDTO, Endereco endereco) {
        this.nome = usuarioDTO.getNome();
        this.email = usuarioDTO.getEmail();
        this.endereco = endereco;
    }
}
