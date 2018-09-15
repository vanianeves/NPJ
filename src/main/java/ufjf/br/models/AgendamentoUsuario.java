package ufjf.br.models;


import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
public class AgendamentoUsuario {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String disciplinaEmCurso ;


    @ManyToOne
    private Colaborador aluno1;

    @ManyToOne
    private Colaborador aluno2;

    @ManyToOne
    private Colaborador parfista1;

    @ManyToOne
    private Colaborador parfista2;

    @OneToOne
    private PreAtendimento pre_atendimento;


    public AgendamentoUsuario() {
    }

    public AgendamentoUsuario(String disciplinaEmCurso, Colaborador aluno1, Colaborador aluno2, Colaborador parfista1, Colaborador parfista2, PreAtendimento pre_atendimento) {
        this.disciplinaEmCurso = disciplinaEmCurso;
        this.aluno1 = aluno1;
        this.aluno2 = aluno2;
        this.parfista1 = parfista1;
        this.parfista2 = parfista2;
        this.pre_atendimento = pre_atendimento;
    }

    @Override
    public String toString() {
        return "AgendamentoUsuario{" +
                "id=" + id +
                ", disciplinaEmCurso='" + disciplinaEmCurso + '\'' +
                ", aluno1=" + aluno1 +
                ", aluno2=" + aluno2 +
                ", parfista1=" + parfista1 +
                ", parfista2=" + parfista2 +
                ", pre_atendimento=" + pre_atendimento +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDisciplinaEmCurso() {
        return disciplinaEmCurso;
    }

    public void setDisciplinaEmCurso(String disciplinaEmCurso) {
        this.disciplinaEmCurso = disciplinaEmCurso;
    }

    public Colaborador getAluno1() {
        return aluno1;
    }

    public void setAluno1(Colaborador aluno1) {
        this.aluno1 = aluno1;
    }

    public Colaborador getAluno2() {
        return aluno2;
    }

    public void setAluno2(Colaborador aluno2) {
        this.aluno2 = aluno2;
    }

    public Colaborador getParfista1() {
        return parfista1;
    }

    public void setParfista1(Colaborador parfista1) {
        this.parfista1 = parfista1;
    }

    public Colaborador getParfista2() {
        return parfista2;
    }

    public void setParfista2(Colaborador parfista2) {
        this.parfista2 = parfista2;
    }

    public PreAtendimento getPre_atendimento() {
        return pre_atendimento;
    }

    public void setPre_atendimento(PreAtendimento pre_atendimento) {
        this.pre_atendimento = pre_atendimento;
    }

    public String getNomeDupla(){

        String nomes = aluno1.getNome() ;

        if (aluno2 != null){
            nomes = nomes + " | " + aluno2.getNome();

        }
        return nomes;
    }

    public String getNomeParfistas(){

        String nomes = parfista1.getNome() ;

        if (parfista2 != null){
            nomes = nomes + " | " + parfista2.getNome();

        }
        return nomes;
    }
}
