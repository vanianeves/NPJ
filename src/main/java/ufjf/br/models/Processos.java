package ufjf.br.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Processos {
    @Id
    @GeneratedValue
    private Integer id;

   @Column(name="numeroProcessosAcao")
   private int numeroProc;

    @Column(name="Vara")
    private String vara;

    @Column(name="data")
    private String data;

    @Column(name="idAtendimento")
    private Integer idAtendimento;


    public Integer getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(Integer idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumeroProc() {
        return numeroProc;
    }

    public void setNumeroProc(int numeroProc) {
        this.numeroProc = numeroProc;
    }

    public String getVara() {
        return vara;
    }

    public void setVara(String vara) {
        this.vara = vara;
    }
}
