package ufjf.br.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PerfilUsuario {

    @Id
    private Integer id;

    @Column
    private String perfil;

    @Override
    public String toString() {
        return "PerfilUsuario{" +
                "id=" + id +
                ", perfil='" + perfil + '\'' +
                '}';
    }

    public PerfilUsuario(Integer id, String perfil) {
        this.id = id;
        this.perfil = perfil;
    }

    public PerfilUsuario() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
