CREATE TABLE IF NOT EXISTS  usuario (
	cod_usuario INT AUTO_INCREMENT PRIMARY KEY,
	Nome VARCHAR(250) NOT NULL,
	Especialidade VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS  atividade (
	cod_atividade INT AUTO_INCREMENT PRIMARY KEY,
	Descricao VARCHAR(250) NOT NULL,
	constante_campo VARCHAR(100) NOT NULL,
	tempo_Estimado DATE
);

CREATE TABLE IF NOT EXISTS  etapa (
	cod_etapa INT AUTO_INCREMENT PRIMARY KEY,
	Nome VARCHAR(250)  NOT NULL,
	constante_campo VARCHAR(100)  NOT NULL,
	Descricao VARCHAR(250)  NOT NULL
);

CREATE TABLE IF NOT EXISTS  ponto (
	horas_trabalhadas DECIMAL,
	cod_ponto INT AUTO_INCREMENT PRIMARY KEY,
	data_criacao DATETIME NOT NULL,
	id_atividade INT,
	FOREIGN KEY(id_atividade) REFERENCES atividade (cod_atividade)
);

CREATE TABLE IF NOT EXISTS  Grupo_material (
	cod_grupo_material INT AUTO_INCREMENT PRIMARY KEY,
	Descricao VARCHAR(250) NOT NULL,
	constante_campo VARCHAR(100)  NOT NULL,
	cod_atividade INT,
	FOREIGN KEY(cod_atividade) REFERENCES atividade (cod_atividade)
);

CREATE TABLE IF NOT EXISTS  relatorio_material (
	quantidade INTEGER,
	cod_relatorio_material INT
);

CREATE TABLE IF NOT EXISTS  Material (
	constante_campo VARCHAR(100)  NOT NULL,
	Descricao VARCHAR(250),
	Unidade_medida VARCHAR(10),
	quantidade INT,
	cod_material INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS  usuario_ponto (
	cod_usuario INT NOT NULL,
	cod_ponto INT NOT NULL,
	FOREIGN KEY(cod_usuario) REFERENCES usuario (cod_usuario),
	FOREIGN KEY(cod_ponto) REFERENCES ponto (cod_ponto)
);

CREATE TABLE IF NOT EXISTS  usuario_atividade (
	cod_atividade INT NOT NULL,
	cod_usuario INT NOT NULL,
	FOREIGN KEY(cod_atividade) REFERENCES atividade (cod_atividade),
	FOREIGN KEY(cod_usuario) REFERENCES usuario (cod_usuario)
);

CREATE TABLE IF NOT EXISTS  ponto_relatorio_material (
	cod_ponto INT NOT NULL,
	FOREIGN KEY(cod_ponto) REFERENCES ponto (cod_ponto)
);

CREATE TABLE IF NOT EXISTS  grupo_material_material (
	cod_material INT NOT NULL,
	cod_grupo_material INT NOT NULL,
	FOREIGN KEY(cod_material) REFERENCES Material (cod_material),
	FOREIGN KEY(cod_grupo_material) REFERENCES Grupo_material (cod_grupo_material)
);

CREATE TABLE IF NOT EXISTS  atividade_etapa (
	cod_etapa INT NOT NULL,
	cod_atividade INT NOT NULL,
	FOREIGN KEY(cod_etapa) REFERENCES etapa (cod_etapa),
	FOREIGN KEY(cod_atividade) REFERENCES atividade (cod_atividade)
);

CREATE TABLE IF NOT EXISTS  reatorio_material_material (
	cod_material INT NOT NULL,
	FOREIGN KEY(cod_material) REFERENCES Material (cod_material)
);
