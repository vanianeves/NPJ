CREATE TABLE usuario (
  id                 SERIAL4 PRIMARY KEY,
  nome               VARCHAR(250) NOT NULL,
  --matricula_ufjf     VARCHAR(100),
  id_Colaborador      INTEGER,
  endereco_id        INT4         NOT NULL,
  telefone           VARCHAR(50),
  celular            VARCHAR(50),
  email              VARCHAR(250) NOT NULL,
  setor_id            INT4           NULL,
  tipo_colaborador_id INT4           NULL,
  perfil_usuario_id  INTEGER NOT NULL,
  ativo              BOOLEAN NOT NULL,
  dt_criacao         TIMESTAMP NULL DEFAULT  current_date,
  dt_exclusao        TIMESTAMP NULL DEFAULT  current_date
);

ALTER TABLE usuario ADD FOREIGN KEY (tipo_colaborador_id) REFERENCES tipo_colaborador (id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE usuario ADD FOREIGN KEY (setor_id) REFERENCES tipo_setor (id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE usuario
  ADD FOREIGN KEY (endereco_id) REFERENCES endereco (id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE usuario ADD CONSTRAINT fk_usuario_perfil_usuario FOREIGN KEY (perfil_usuario_id) REFERENCES perfil_usuario (id) ON UPDATE CASCADE ON DELETE CASCADE;
