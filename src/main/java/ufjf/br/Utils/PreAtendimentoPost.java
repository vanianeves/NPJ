package ufjf.br.Utils;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PreAtendimentoPost {


    String dataInicio;

    String dataFim;

    String horarioInicio;

    String horarioFim;

    String intervalo;

    String [] checkBoxValues;

    int numAtend;

    int numAtendDia;

    String semestre;

    public PreAtendimentoPost(String dataInicio, String dataFim, String horarioInicio, String horarioFim, String intervalo, String[] checkBoxValues, int numAtend, int numAtendDia, String semestre) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.intervalo = intervalo;
        this.checkBoxValues = checkBoxValues;
        this.numAtend = numAtend;
        this.numAtendDia = numAtendDia;
        this.semestre = semestre;
    }

    public PreAtendimentoPost() {
    }

    public int getNumAtendDia() {
        return numAtendDia;
    }

    public void setNumAtendDia(int numAtendDia) {
        this.numAtendDia = numAtendDia;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(String horarioFim) {
        this.horarioFim = horarioFim;
    }

    public String getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(String intervalo) {
        this.intervalo = intervalo;
    }

    public int getNumAtend() {
        return numAtend;
    }

    public void setNumAtend(int numAtend) {
        this.numAtend = numAtend;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String[] getCheckBoxValues() {
        return checkBoxValues;
    }

    public void setCheckBoxValues(String[] checkBoxValues) {
        this.checkBoxValues = checkBoxValues;
    }
}
