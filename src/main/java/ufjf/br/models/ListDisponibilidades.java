package ufjf.br.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListDisponibilidades {
    private List<DisponibilidadeHorario> disponibilidades;

    public List<DisponibilidadeHorario> getDisponibilidades() {
        return disponibilidades;
    }

    public void setDisponibilidades(List<DisponibilidadeHorario> disponibilidades) {
        this.disponibilidades = disponibilidades;
    }
}
