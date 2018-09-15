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

    @Column(name= "dt_atendimento_testemunhas")
    private Timestamp dtAtendimentoTestemunhas;

    @OneToOne
    @JoinColumn(name = "aluno1_id")
    @NotNull(message ="selecone o primeiro aluno")
    private Colaborador aluno1;

    @OneToOne
    @JoinColumn(name = "aluno2_id")
    @NotNull(message ="selecone o segundo aluno")
    private Colaborador  aluno2;

    @OneToOne
    @JoinColumn(name = "parfista1_id")
    @NotNull(message ="selecone o primeiro parfista")
    private Colaborador parfista1;

    @OneToOne
    @JoinColumn(name = "parfista2_id")
    @NotNull(message ="selecone o segundo parfista")
    private Colaborador  parfista2;

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
    @NotNull(message = "Selecione um responsável, caso necessário")
    private Cliente idResponsavelegal;

    @OneToOne
    @JoinColumn(name="parteContraria_id")
    @NotNull(message = "Selecione a parte contrária")
    private Cliente ParteContrariaId;


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

    @OneToOne
    @JoinColumn(name="testemunha1_id")
    @NotNull(message = "Selecione a primeira testemunha")
    private Cliente testemunha1;


    @OneToOne
    @JoinColumn(name="testemunha2_id")
    @NotNull(message = "Selecione a segunda testemunha")
    private Cliente testemunha2;

    @OneToOne
    @JoinColumn(name="testemunha3_id")
    @NotNull(message = "Selecione a terceira testemunha")
    private Cliente testemunha3;

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

    public Cliente getTestemunha3() {
        return testemunha3;
    }

    public void setTestemunha3(Cliente testemunha3) {
        this.testemunha3 = testemunha3;
    }

    public Cliente getTestemunha2() {
        return testemunha2;
    }

    public void setTestemunha2(Cliente testemunha2) {
        this.testemunha2 = testemunha2;
    }

    public Cliente getTestemunha1() {
        return testemunha1;
    }

    public void setTestemunha1(Cliente testemunha1) {
        this.testemunha1 = testemunha1;
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

    public Cliente getParteContrariaId() {
        return ParteContrariaId;
    }

    public void setParteContrariaId(Cliente parteContrariaId) {
        ParteContrariaId = parteContrariaId;
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


    public Timestamp getDtAtendimentoTestemunhas() {
        return dtAtendimentoTestemunhas;
    }

    public void setDtAtendimentoTestemunhas(Timestamp dtAtendimentoTestemunhas) {
        this.dtAtendimentoTestemunhas = dtAtendimentoTestemunhas;
    }

    public Colaborador getAluno1() {
        return aluno1;
    }

    public void setAluno1(Colaborador aluno1) {
        this.aluno1 = aluno1;
    }

    public Colaborador getAluno2() {
        return aluno2;
    }

    public void setAluno2(Colaborador aluno2) {
        this.aluno2 = aluno2;
    }

    public Colaborador getParfista1() {
        return parfista1;
    }

    public void setParfista1(Colaborador parfista1) {
        this.parfista1 = parfista1;
    }

    public Colaborador getParfista2() {
        return parfista2;
    }

    public void setParfista2(Colaborador parfista2) {
        this.parfista2 = parfista2;
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
