CREATE TABLE tipo_colaborador (
  id SERIAL4 PRIMARY KEY,
  tipo VARCHAR(200) NOT NULL
);

ALTER SEQUENCE tipo_setor_id_seq RESTART WITH 1001;

Insert into tipo_colaborador Values(
	1,
	'ALUNO'
);
Insert into tipo_colaborador Values(
	2,
	'PROFESSOR'
);
Insert into tipo_colaborador Values(
	3,
	'PARFISTA'
);


