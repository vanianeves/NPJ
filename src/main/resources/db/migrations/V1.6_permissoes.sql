CREATE TABLE permissoes(
  perfil_usuario_id INTEGER,
  funcionalidades_id INTEGER,
  criar BOOLEAN NOT NULL DEFAULT FALSE,
  deletar BOOLEAN NOT NULL DEFAULT FALSE,
  atualizar BOOLEAN NOT NULL DEFAULT FALSE,
  visualizar BOOLEAN NOT NULL DEFAULT FALSE
);

--ALTER TABLE permissoes DROP CONSTRAINT pk_permissoes;
--ALTER TABLE permissoes DROP CONSTRAINT fk_permissoes_perfil;
--ALTER TABLE permissoes DROP CONSTRAINT fk_permissoes_funcionalidades;

--ALTER TABLE permissoes ADD CONSTRAINT pk_permissoes PRIMARY KEY (perfil_usuario_id,funcionalidades_id);
--ALTER TABLE permissoes ADD CONSTRAINT fk_permissoes_perfil FOREIGN KEY (perfil_usuario_id) REFERENCES perfil_usuario (id) ON UPDATE CASCADE ON DELETE CASCADE;
--ALTER TABLE permissoes ADD CONSTRAINT fk_permissoes_funcionalidades FOREIGN KEY (funcionalidades_id) REFERENCES funcionalidades (id) ON UPDATE CASCADE ON DELETE CASCADE;

--INSERT INTO permissoes VALUES (1,1,TRUE,TRUE,TRUE,TRUE);