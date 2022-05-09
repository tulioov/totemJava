CREATE TABLE IF NOT EXISTS  usuario (
	cod_usuario INT AUTO_INCREMENT PRIMARY KEY,
	NFC INT NOT NULL,
	Nome VARCHAR(250) NOT NULL,
	Especialidade VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS  barco (
	cod_barco INT AUTO_INCREMENT PRIMARY KEY,
	imagem BLOB,
	Nome VARCHAR(250) NOT NULL,
	descricao VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS  etapa (
	cod_etapa INT AUTO_INCREMENT PRIMARY KEY,
	Nome VARCHAR(250)  NOT NULL,
	constante_campo VARCHAR(100)  NOT NULL,
	Descricao VARCHAR(250)  NOT NULL
);

CREATE TABLE IF NOT EXISTS  atividade (
	cod_atividade INT AUTO_INCREMENT PRIMARY KEY,
	Descricao VARCHAR(250) NOT NULL,
	constante_campo VARCHAR(100) NOT NULL
);

insert into etapa  (CONSTANTE_CAMPO,DESCRICAO)
select  'ETAPA_0', 'Etapa 0'  from dual 
where not exists(select * from etapa where CONSTANTE_CAMPO='ETAPA_0');
insert into etapa  (CONSTANTE_CAMPO,DESCRICAO)
select  'ETAPA_1', 'Etapa 1'  from dual 
where not exists(select * from etapa where CONSTANTE_CAMPO='ETAPA_1');
insert into etapa  (CONSTANTE_CAMPO,DESCRICAO)
select  'ETAPA_2', 'Etapa 2'  from dual 
where not exists(select * from etapa where CONSTANTE_CAMPO='ETAPA_2');
insert into etapa  (CONSTANTE_CAMPO,DESCRICAO)
select  'ETAPA_3', 'Etapa 3'  from dual 
where not exists(select * from etapa where CONSTANTE_CAMPO='ETAPA_3');
insert into etapa  (CONSTANTE_CAMPO,DESCRICAO)
select  'ETAPA_4', 'Etapa 4'  from dual 
where not exists(select * from etapa where CONSTANTE_CAMPO='ETAPA_4');


CREATE TABLE IF NOT EXISTS  sub_atividade (
	cod_sub_atividade INT AUTO_INCREMENT PRIMARY KEY,
	Descricao VARCHAR(250) NOT NULL,
	constante_campo VARCHAR(100) NOT NULL,
	tempo_Estimado INTEGER
);

CREATE TABLE IF NOT EXISTS  Material (
	cod_material INT AUTO_INCREMENT PRIMARY KEY,
	constante_campo VARCHAR(100)  NOT NULL,
	Descricao VARCHAR(250)
);

CREATE TABLE IF NOT EXISTS  item (
	cod_item INT AUTO_INCREMENT PRIMARY KEY,
	constante_campo VARCHAR(100)  NOT NULL,
	Descricao VARCHAR(250),
	Unidade_medida VARCHAR(10),
	quantidade INT
);

/*
DROP TABLE IF EXISTS BARCO ;
DROP TABLE IF EXISTS ETAPA ;
DROP TABLE IF EXISTS ITEM  ;
DROP TABLE IF EXISTS MATERIAL ;
DROP TABLE IF EXISTS USUARIO ;
DROP TABLE IF EXISTS SUB_ATIVIDADE ;
DROP TABLE IF EXISTS MONITORACAO ;
DROP TABLE IF EXISTS ATIVIDADE ;
*/