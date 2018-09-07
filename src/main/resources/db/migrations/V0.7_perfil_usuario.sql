CREATE TABLE perfil_usuario (
  id INTEGER PRIMARY KEY,
  perfil VARCHAR(50) NOT NULL
);

INSERT INTO perfil_usuario VALUES (1,'Professor');
INSERT INTO perfil_usuario VALUES (2,'Coordenador');
INSERT INTO perfil_usuario VALUES (3,'Aluno');