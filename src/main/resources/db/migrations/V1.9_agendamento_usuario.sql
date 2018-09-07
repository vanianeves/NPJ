CREATE TABLE agendamento_usuario(

    id SERIAL4 PRIMARY KEY,
    disciplina_em_curso TEXT NOT NULL,
    aluno1_id integer NOT NULL,
    aluno2_id integer,
    parfista1_id integer Not Null,
    parfista2_id integer,
    pre_atendimento_id integer NOT NULL

);

ALTER TABLE agendamento_usuario
    ADD CONSTRAINT fk_agendamento_aluno1 FOREIGN KEY (aluno1_id) REFERENCES colaborador (id)
    ON UPDATE CASCADE ON DELETE CASCADE;


ALTER TABLE agendamento_usuario
    ADD CONSTRAINT fk_agendamento_aluno2 FOREIGN KEY (aluno2_id) REFERENCES colaborador (id)
    ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE agendamento_usuario
    ADD CONSTRAINT fk_agendamento_parfista1 FOREIGN KEY (parfista1_id) REFERENCES colaborador (id)
    ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE agendamento_usuario
    ADD CONSTRAINT fk_agendamento_parfista2 FOREIGN KEY (parfista2_id) REFERENCES colaborador (id)
    ON UPDATE CASCADE ON DELETE CASCADE;


ALTER TABLE agendamento_usuario
    ADD CONSTRAINT fk_agendamento_pre_atendimento FOREIGN KEY (pre_atendimento_id) REFERENCES pre_atendimento (id)
    ON UPDATE CASCADE ON DELETE CASCADE;