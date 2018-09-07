package ufjf.br.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name ="Alunos_e_ParfistasPorAtendimento" )
public class AlunosParfistasPorAtendimento {
    public AlunosParfistasPorAtendimento(){};

    @Id
    @GeneratedValue
    Integer id;

    @Column(name="atendimentoId")
    private Integer atendimentoId;


    @Column(name="alunoId")
    private Integer alunoId;














    public Integer getId() {
        return id;
    }




    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAtendimentoId() {
        return atendimentoId;
    }

    public void setAtendimentoId(Integer atendimentoId) {
        this.atendimentoId = atendimentoId;
    }

    public Integer getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Integer alunoId) {
        this.alunoId = alunoId;
    }
}
