package ufjf.br.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
public class DemandaJuridica {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    @NotBlank(message = "Preencha a Demanda Juridica")
    private String demanda;

    @Override
    public String toString()
    {
        return "DemandaJuridica{"+"id="+ id +
                ", demanda_juridica ='" + demanda+ '}';
    }
    public DemandaJuridica()
    {
    }
    public DemandaJuridica(String demanda_juridica) {
        this.demanda = demanda_juridica;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDemanda() {
        return demanda;
    }

    public void setDemanda(String demanda_juridica) {
        this.demanda = demanda_juridica;
    }
}
