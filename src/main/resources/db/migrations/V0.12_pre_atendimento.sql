CREATE TABLE pre_atendimento
(
    id SERIAL PRIMARY KEY,
    data_horario TIMESTAMP,
    semestre VARCHAR,
    duracao_atendimento integer ,
    cliente_id integer,
    demanda_juridica_id integer,
    confirmacao text default null

);

ALTER TABLE pre_atendimento
   ADD CONSTRAINT fk_atendimento_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
   ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE pre_atendimento
   ADD CONSTRAINT fk_atendimento_demanda FOREIGN KEY (demanda_juridica_id) REFERENCES demanda_juridica(id) ON DELETE CASCADE ON UPDATE CASCADE;

