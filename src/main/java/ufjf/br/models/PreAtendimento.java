package ufjf.br.models;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
public class PreAtendimento {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = true, name = "data_horario")
    @Temporal(TemporalType.TIMESTAMP)
//    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "Informe o horário")
    private Date data_horario;


    @NotBlank(message = "Preencha o semestre")
    @Column(nullable=true, name="semestre")
    private String semestre;

    @NotNull(message = "Informe a duração do atendimento")
    private int duracaoAtendimento;

    @ManyToOne
    @JoinColumn(nullable = true)
    public Cliente cliente;

    @ManyToOne
    public DemandaJuridica demandaJuridica;

    public String confirmacao;

    public PreAtendimento(Date data_horario, String semestre, int duracaoAtendimento, Cliente cliente, DemandaJuridica demandaJuridica, String confirmacao) {
        this.data_horario = data_horario;
        this.semestre = semestre;
        this.duracaoAtendimento = duracaoAtendimento;
        this.cliente = cliente;
        this.demandaJuridica = demandaJuridica;
        this.confirmacao = confirmacao;
    }

    public PreAtendimento() {
    }

    @Override
    public String toString() {
        return "PreAtendimento{" +
                "id=" + id +
                ", data_horario=" + data_horario +
                ", semestre='" + semestre + '\'' +
                ", duracaoAtendimento=" + duracaoAtendimento +
                ", cliente=" + cliente +
                ", demandaJuridica=" + demandaJuridica +
                ", confirmacao='" + confirmacao + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData_horario() {
        return data_horario;
    }

    public void setData_horario(Date data_horario) {
        this.data_horario = data_horario;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getDuracaoAtendimento() {
        return duracaoAtendimento;
    }

    public void setDuracaoAtendimento(int duracaoAtendimento) {
        this.duracaoAtendimento = duracaoAtendimento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public DemandaJuridica getDemandaJuridica() {
        return demandaJuridica;
    }

    public void setDemandaJuridica(DemandaJuridica demandaJuridica) {
        this.demandaJuridica = demandaJuridica;
    }

    public String getConfirmacao() {
        return confirmacao;
    }

    public void setConfirmacao(String confirmacao) {
        this.confirmacao = confirmacao;
    }

    public String getDataFormatada() {


            SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");


            return formatar.format(data_horario);

    }
    public String getHoraFormatada() {

            String horaFormatada;


            SimpleDateFormat formatar = new SimpleDateFormat("HH:mm");

            horaFormatada = formatar.format(data_horario);



        return horaFormatada;

    }
    public String getHoraFimFormatada() {

        String horaFormatada;
        Date aux = data_horario;

        aux.setMinutes(data_horario.getMinutes()+ duracaoAtendimento);

        SimpleDateFormat formatar = new SimpleDateFormat("HH:mm");

        horaFormatada = formatar.format(aux);



        return horaFormatada;

    }


    public String getDiaFormatado() {

        String diaFormatado;


        SimpleDateFormat formatar = new SimpleDateFormat("EEEE");
        diaFormatado= formatar.format(data_horario);



        return diaFormatado;

    }




    //    @Transient
//    private final List<String> diasSemana = new ArrayList<String>(Arrays.asList(new String[]{
//            "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo"
//    }));



//    public String getData(){
//        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        String data = String.valueOf(getData_horario());
//        data = formatar.format(data);
//
//        System.out.println(data);
//        return data;
//
//    }
}