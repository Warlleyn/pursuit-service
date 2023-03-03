#### script para criação do scheema pessoa
CREATE DATABASE pessoa;
#### script para criação do scheema diversos
CREATE DATABASE diversos;
#### script para criação do scheema imoveis
CREATE DATABASE imoveis;

#### script para criacao da tabela de usuario
CREATE TABLE pessoa.usuario (
'cd_usuario' BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
'ds_nome' VARCHAR (80) NOT NULL,
'ds_email' VARCHAR (50) NOT NULL,
'cd_endereco' BIGINT NOT NULL,
'dt_alteracao' NULL TIMESTRAMP,
'dt_exclusao' NULL TIMESTRAMP,
'createdAt' TIMESTRAMP DEFAULT CURRENT_TIMESTAMP,
'updatedAt' TIMESTRAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
ENGINE=InnoDB  DEFAULT CHARSET=utf8mb3;);

#### script para criacao da tabela de imovel
CREATE TABLE imoveis.imovel (
'cd_imveol' BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
'ds_tipo_imovel' VARCHAR (80) NOT NULL,
'in_garagem' BOOL NOT NULL,
'nr_quartos' BIGINT NOT NULL,
'nr_camas' BIGINT NOT NULL,
'cd_usuario' BIGINT NOT NULL,
'cd_endereco' BIGINT NOT NULL,
'dt_alteracao' NULL TIMESTRAMP,
'dt_exclusao' NULL TIMESTRAMP,
'createdAt' TIMESTRAMP DEFAULT CURRENT_TIMESTAMP,
'updatedAt' TIMESTRAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
CONSTRAINT 'fk_cd_user_imovel' FOREIGN KEY ('cd_usuario') REFERENCES 'pessoa'.'usuario' ('cd_usuario'),
CONSTRAINT 'fk_cd_address_imovel' FOREIGN KEY ('cd_endereco') REFERENCES 'diversos'.'endereco' ('cd_endereco'),
ENGINE=InnoDB  DEFAULT CHARSET=utf8mb3;);


#### script para criacao da tabela de endereco
CREATE TABLE diversos.endereco (
'cd_endereco' BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
'ds_logradouro' VARCHAR (80) NOT NULL,
'nr_logradouro' BIGINT NULL,
'ds_bairro' VARCHAR(50) NOT NULL,
'nr_cep' VARCHAR(50) NOT NULL,
'ds_latitude' VARCHAR(100) NOT NULL,
'ds_longitude' VARCHAR(100) TIMESTRAMP,
'dt_exclusao' NULL TIMESTRAMP,
'createdAt' TIMESTRAMP DEFAULT CURRENT_TIMESTAMP,
'updatedAt' TIMESTRAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
ENGINE=InnoDB  DEFAULT CHARSET=utf8mb3;);


