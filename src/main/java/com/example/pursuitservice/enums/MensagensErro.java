package com.example.pursuitservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MensagensErro {
    LOGRADOURO_EMPTY("Logradouro deve ser informado"),
    BAIRRO_EMPTY("Bairro deve ser informado"),
    CEP_EMPTY("CEP deve ser informado"),
    NUMERO_LOGRADOURO_EMPTY("Numero logradouro deve ser informado"),
    ENDERECO_NOT_FOUN("Endereco não encontrado"),
    IMOVEL_NOT_FOUND("Imovel não encontrado"),
    USUARIO_NOT_FOUND("Usuario não encontrado"),
    TIPO_IMOVEL_EMPTY("Tipo imovel deve ser informado"),
    GARAGEM_EMPTY("Garagem deve ser informada"),
    QUANTIDADE_QUARTOS_EMPTY("Quantidade de quartos deve ser informada"),
    QUANTIDADE_CAMAS_EMPTY("Quantidade camas deve ser informada"),
    NOME_EMPTY("Nome deve ser informado"),
    EMAIL_EMPTY("Email deve ser informado"),
    ENDERECO_IMOVEL_EMPTY("Endereco do imovel deve ser informado");
    private String descricao;
}
