package ufjf.br.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ufjf.br.config.LocalTimeDeserializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
public class DisponibilidadeHorario {
    @Id
    @GeneratedValue
    private Integer id_disponibilidade;

    @ManyToOne
    @JoinColumn(name="fk_usuario")
    private Colaborador usuario;

    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @Column(name="horario_inicio",nullable = false)
    @NotNull(message = "Preencha a Hora de Início")
    private LocalTime horarioinicio;

    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @Column(name="horario_fim",nullable = false)
    @NotNull(message = "Preencha a Hora De Fim")
    private LocalTime horariofim;

    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @Column(name="cargahoraria",nullable = false)
    private LocalTime cargahoraria;

    @Column(name="dia_semana",nullable = false)
    @NotNull(message = "Preencha o Dia da Semana")
    @Enumerated(EnumType.STRING)
    private Semana dia;


    public DisponibilidadeHorario() {
    }

    public DisponibilidadeHorario(Colaborador usuario, LocalTime horaInicio, LocalTime horario_fim, LocalTime cargahoraria, Semana dia_semana) {
        this.usuario = usuario;
        this.horarioinicio = horaInicio;
        this.horariofim = horario_fim;
        this.cargahoraria = cargahoraria;
        this.dia = dia_semana;
    }

    public Integer getId() {
        return id_disponibilidade;
    }

    public void setId(Integer id) {
        this.id_disponibilidade = id;
    }

    public Colaborador getColaborador() {
        return usuario;
    }

    public void setColaborador(Colaborador usuario) {
        this.usuario = usuario;
    }

    public LocalTime getHorarioinicio() {
        return horarioinicio;
    }

    public void setHorarioinicio(LocalTime horarioinicio) {
        this.horarioinicio = horarioinicio;
    }

    public LocalTime getHorariofim() {
        return horariofim;
    }

    public void setHorariofim(LocalTime horariofim) {
        this.horariofim = horariofim;
    }

    public LocalTime getCargahoraria() {
        return cargahoraria;
    }

    public void setCargahoraria(LocalTime cargahoraria) {
        this.cargahoraria = cargahoraria;
    }

    public Semana getDia_semana() {
        return dia;
    }

    public void setDia_semana(Semana dia_semana) {
        this.dia = dia_semana;
    }
}
