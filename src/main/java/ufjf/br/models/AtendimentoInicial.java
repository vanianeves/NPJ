package ufjf.br.models;

import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Timestamp;

@Entity
@Table(name= "atendimento")
public class AtendimentoInicial {

    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name= "dt_atendimento")
    private String dtAtendimento;

    @OneToOne
    @JoinColumn(name = "bolsistaTP_id")
    @NotNull(message ="selecone um bolsista de TP")
    private Colaborador  bolsistaTP;


    @OneToOne
    @JoinColumn(name = "professorOrientador_id")
    @NotNull(message ="selecone o professor responsável")
    private Colaborador  professorOrientador;

    @Column(name="dados_Demanda")
    private String dadosDemanda;

    @OneToOne
    @JoinColumn(name="Cliente_id")
    @NotNull(message ="Selecione um cliente")
    private Cliente idce;

    @OneToOne
    @JoinColumn(name="id_responsavel_legal")
    private Cliente idResponsavelegal;

    @OneToOne
    @JoinColumn(name="desdobramento_id")
    @NotNull(message = "Selecione o desdobramento do atendimento")
    private Desdobramentos desdobramento;

    //@OneToOne
    //@JoinColumn(name="id_responsavel_Atendimento")  precisamos saber quantos responsáveis são.
    //@NotNull(message = "Selecione o responsável pelo atendimento")
    //private Integer idResponsavelAtendimento;

    @Column(name="fatosIniciais")
    private String fatosIniciais;

    @Column(name="idContencioso")
    private Integer idContencioso;

    @Column(name="documentosApresentados")
    private String documentos;

    public String getDocumentos() {
        return documentos;
    }

    public void setDocumentos(String documentos) {
        this.documentos = documentos;
    }

    @Column(name="numeroProcessoVara")
    private int numeroProcesso;

    @Column(name="vara")
    private String vara;

    @Column(name="historico_id")
    private int historicoId;

///Gets e sets




    //  public Integer getIdResponsavelAtendimento() {
   //     return idResponsavelAtendimento;
  //  }

  //  public void setIdResponsavelAtendimento(Integer idResponsavelAtendimento) {
  //      this.idResponsavelAtendimento = idResponsavelAtendimento;
   // }


    public String getDtAtendimento() {
        return dtAtendimento;
    }

    public void setDtAtendimento(String dtAtendimento) {
        this.dtAtendimento = dtAtendimento;
    }

    public void insereHistorico(int id){

  }

    public int getHistoricoId() {
        return historicoId;
    }

    public void setHistoricoId(int historicoId) {
        this.historicoId = historicoId;
    }

    public int getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(int numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getVara() {
        return vara;
    }

    public void setVara(String vara) {
        this.vara = vara;
    }


    public Desdobramentos getDesdobramento() {
        return desdobramento;
    }

    public void setDesdobramento(Desdobramentos desdobramentoId) {
        this.desdobramento = desdobramentoId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDadosDemanda() {
        return dadosDemanda;
    }

    public void setDadosDemanda(String dadosDemanda) {
        this.dadosDemanda = dadosDemanda;
    }

    public Cliente getIdce() {
        return idce;
    }

    public void setIdce(Cliente idce) {
        this.idce = idce;
    }

    public Cliente getIdResponsavelegal() {
        return idResponsavelegal;
    }

    public void setIdResponsavelegal(Cliente idResponsavelegal) {
        this.idResponsavelegal = idResponsavelegal;
    }

    public String getFatosIniciais() {
        return fatosIniciais;
    }

    public void setFatosIniciais(String fatosIniciais) {
        this.fatosIniciais = fatosIniciais;
    }

    public Integer getIdContencioso() {
        return idContencioso;
    }

    public void setIdContencioso(Integer idContencioso) {
        this.idContencioso = idContencioso;
    }


    public Colaborador getBolsistaTP() {
        return bolsistaTP;
    }

    public void setBolsistaTP(Colaborador bolsistaTP) {
        this.bolsistaTP = bolsistaTP;
    }

    public Colaborador getProfessorOrientador() {
        return professorOrientador;
    }

    public void setProfessorOrientador(Colaborador professorOrientador) {
        this.professorOrientador = professorOrientador;
    }
}
