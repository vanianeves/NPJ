CREATE TABLE colaborador (
  id                  SERIAL4 PRIMARY KEY,
  oab                 VARCHAR(10),
  carga_horaria       SMALLINT NULL,
  usuario_id          INTEGER  NULL,
  matricula_ufjf      VARCHAR(100) NULL,
  id_Colaborador      INTEGER,
  endereco_id        INT4    NOT NULL,
  telefone           VARCHAR(50),
  celular            VARCHAR(50),
  setor_id            INT4           NULL,
  tipo_colaborador_id INT4           NULL,
  ativo              BOOLEAN NOT NULL,
  dt_criacao         TIMESTAMP NULL DEFAULT  current_date,
  dt_exclusao        TIMESTAMP NULL DEFAULT  current_date
);


ALTER TABLE colaborador
  ADD FOREIGN KEY (tipo_colaborador_id) REFERENCES tipo_colaborador (id) ON DELETE CASCADE ON UPDATE CASCADE;

--ALTER TABLE colaborador
  --ADD FOREIGN KEY (setor_id) REFERENCES tipo_setor (id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE usuario
  DROP COLUMN tipo_colaborador_id;

ALTER TABLE colaborador
  DROP COLUMN usuario_id;

ALTER TABLE usuario
  DROP COLUMN setor_id;

ALTER TABLE usuario
  ALTER COLUMN telefone SET NOT NULL;

ALTER TABLE usuario
  RENAME COLUMN celular TO telefone_opcional;
