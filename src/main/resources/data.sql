/*CREATE TABLE IF NOT EXISTS  usuario (
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

CREATE TABLE IF NOT EXISTS  fase (
	cod_fase INT AUTO_INCREMENT PRIMARY KEY,
	Nome VARCHAR(250)  NOT NULL,
	constante_campo VARCHAR(100)  NOT NULL,
	Descricao VARCHAR(250)  NOT NULL
);

CREATE TABLE IF NOT EXISTS  atividade (
	cod_atividade INT AUTO_INCREMENT PRIMARY KEY,
	Descricao VARCHAR(250) NOT NULL,
	constante_campo VARCHAR(100) NOT NULL
);
*/
insert into fase  (CONSTANTE_CAMPO,NOME)
select  'FASE_0', 'Fase 0'  from FASE 
where not exists(select * from fase where CONSTANTE_CAMPO='FASE_0');
insert into fase  (CONSTANTE_CAMPO,NOME)
select  'FASE_1', 'Fase 1'  from FASE 
where not exists(select * from fase where CONSTANTE_CAMPO='FASE_1');
insert into fase  (CONSTANTE_CAMPO,NOME)
select  'FASE_2', 'Fase 2'  from FASE 
where not exists(select * from fase where CONSTANTE_CAMPO='FASE_2');
insert into fase  (CONSTANTE_CAMPO,NOME)
select  'FASE_3', 'Fase 3'  from FASE 
where not exists(select * from fase where CONSTANTE_CAMPO='FASE_3');
insert into fase  (CONSTANTE_CAMPO,NOME)
select  'FASE_4', 'Fase 4'  from FASE 
where not exists(select * from fase where CONSTANTE_CAMPO='FASE_4');
insert into fase  (CONSTANTE_CAMPO,NOME)
select  'FASE_5', 'Fase 5'  from FASE 
where not exists(select * from fase where CONSTANTE_CAMPO='FASE_5');
insert into fase  (CONSTANTE_CAMPO,NOME)
select  'FASE_6', 'Fase 6'  from FASE 
where not exists(select * from fase where CONSTANTE_CAMPO='FASE_6');
insert into fase  (CONSTANTE_CAMPO,NOME)
select  'FASE_7', 'Fase 7'  from FASE 
where not exists(select * from fase where CONSTANTE_CAMPO='FASE_7');


/*
insert into status_monitoracao  (CONSTANTE_CAMPO,NOME)
select  'TRABALHANDO', 'Trabalhando'  from dual 
where not exists(select * from status_monitoracao where CONSTANTE_CAMPO='TRABALHANDO');

insert into status_monitoracao  (CONSTANTE_CAMPO,NOME)
select  'FINALIZADO', 'Finalizado'  from dual 
where not exists(select * from status_monitoracao where CONSTANTE_CAMPO='FINALIZADO');

insert into status_monitoracao  (CONSTANTE_CAMPO,NOME)
select  'AVULSA', 'Avulsa'  from dual 
where not exists(select * from status_monitoracao where CONSTANTE_CAMPO='AVULSA');
*/

/*
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
*/
/*
DROP TABLE IF EXISTS BARCO ;
DROP TABLE IF EXISTS BARCO _MONITORACAO;
DROP TABLE IF EXISTS FASE ;
DROP TABLE IF EXISTS ITEM  ;
DROP TABLE IF EXISTS MATERIAL ;
DROP TABLE IF EXISTS USUARIO ;
DROP TABLE IF EXISTS SUB_ATIVIDADE ;
DROP TABLE IF EXISTS MONITORACAO ;
DROP TABLE IF EXISTS ATIVIDADE ;
*/