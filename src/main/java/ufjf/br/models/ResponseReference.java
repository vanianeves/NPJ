package ufjf.br.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

public class ResponseReference {
    private boolean houveCadastro;
    private String referencia;
    private List<DisponibilidadeHorario> duplicidades;

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    public ResponseReference(String referencia) {
        this.referencia = referencia;
    }

    public ResponseReference(String referencia,List<DisponibilidadeHorario> duplicidades,boolean houveCadastro){
        this.houveCadastro = houveCadastro;
        this.referencia = referencia;
        this.duplicidades = duplicidades;
    }

    public List<DisponibilidadeHorario> getDuplicidades() {
        return duplicidades;
    }

    public void setDuplicidades(List<DisponibilidadeHorario> duplicidades) {
        this.duplicidades = duplicidades;
    }

    public boolean isHouveCadastro() {
        return houveCadastro;
    }

    public void setHouveCadastro(boolean houveCadastro) {
        this.houveCadastro = houveCadastro;
    }
}
