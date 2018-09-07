package ufjf.br.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Historico {
    @Id
    @GeneratedValue
    private Integer id;


   @Column(name="atendimento_Id")
   private Integer idAtendimento;

    @Column(name="dataHistorico")
    private String dtHistorico;

    @Column(name="descricao")
    @NotBlank(message = "Descreva o andamento dado ao atendimento")
    private  String descricao;

    @Column(name="atestoParfista")
    private String atestoParfista;






    //get's e setter's

    public Integer getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(Integer idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

    public String getAtestoParfista() {
        return atestoParfista;
    }

    public void setAtestoParfista(String atestoParfista) {
        this.atestoParfista = atestoParfista;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getDtHistorico() {
        return dtHistorico;
    }

    public void setDtHistorico(String dtHistorico) {
        this.dtHistorico = dtHistorico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
