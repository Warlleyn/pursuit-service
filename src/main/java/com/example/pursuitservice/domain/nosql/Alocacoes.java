package com.example.pursuitservice.domain.nosql;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import java.time.LocalDate;

@Document(collection = "Alocacoes")
@Getter
@Setter
public class Alocacoes {
    @Column(name = "cd_usuario")
    private Long codigoUsuario;
    @Column(name = "cd_imovel")
    private Long codigoImovel;
    @Column(name = "data_inicio")
    private LocalDate dataInicio;
    @Column(name = "data_fim")
    private LocalDate dataFIm;
}
