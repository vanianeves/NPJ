//package ufjf.br.models;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//
//@Entity(name="Contencioso")
//public class Contencioso {
//
//    @Id
//    @GeneratedValue
//    private Integer id;
//
//    @Column(name= "dt_atendimento")
//    private String dtAtendimento;
//
//    //@OneToMany
//    //@JoinColumn(name = "cliente_id")
//    @Column
//    private Cliente aluno;
//
//    public Contencioso(){}
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getDtAtendimento() {
//        return dtAtendimento;
//    }
//
//    public void setDtAtendimento(String dtAtendimento) {
//        this.dtAtendimento = dtAtendimento;
//    }
//
//    public Cliente getAluno() {
//        return aluno;
//    }
//
//    public void setAluno(Cliente aluno) {
//        this.aluno = aluno;
//    }
//
//}
