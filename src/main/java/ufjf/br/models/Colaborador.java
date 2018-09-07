package ufjf.br.models;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
public class Colaborador {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String telefone;

    @Column
    @NotBlank(message = "Nome não pode ficar em branco")
    private String nome;

    @Column
    @NotBlank(message = "Celular não pode ficar em branco")
    private String celular;

    @Column
    private String oab;

    @Column
    private Integer carga_horaria;

    //@ManyToOne
    @Column(name = "usuario_id")
    private Integer usuario_id;

    @OneToOne
    @JoinColumn(name = "tipo_colaborador_id")
    private TipoColaborador tipoColaborador;

    @OneToOne
    @JoinColumn(name = "setor_id")
    private TipoSetor setor;

    @Column
    @NotBlank(message = "Matrícula não pode ficar em branco")
    private String matricula;

    @Column
    private Boolean  ativo;

    @Column(nullable = false,insertable = false,updatable = false)
    private Timestamp dt_criacao;

    @Column
    private Timestamp dt_exclusao;

    @OneToOne
    @Valid
    private Endereco endereco;



    public String getMatricula_ufjf() {
        return matricula;
    }

    public void setMatricula_ufjf(String matricula) {
        this.matricula = matricula;
    }


    public Integer getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Integer usuario_id) {
        this.usuario_id = usuario_id;
    }

    @Override
    public String toString() {
        return "Colaborador{" +
                "id=" + id +
                ", oab='" + oab + '\'' +
                ", carga_horaria=" + carga_horaria +
                ", matricula_ufjf=" + matricula +
                ", tipoColaborador=" + tipoColaborador +
                ", setor=" + setor +
                ", ativo=" + ativo +
                ", dt_criacao=" + dt_criacao+
                ", dt_exclusao=" + dt_exclusao+
                ", telefone=" + telefone+
                ", celular=" + celular+

                '}';
    }



    public Colaborador() {
    }

    public String informaOAB(){
        if(!this.oab.isEmpty())
          return this.getOab();
        else
            return "Sem registro OAB cadastrado";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Colaborador(String oab, Integer carga_horaria, Usuario usuario, TipoColaborador tipoColaborador, TipoSetor setor, String matricula_ufjf, PerfilUsuario perfil) {
        this.oab = oab;
        this.carga_horaria = carga_horaria;
        // this.usuario = usuario;
        this.tipoColaborador = tipoColaborador;
        this.setor = setor;
        this.matricula = matricula_ufjf;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Timestamp getDt_criacao() {
        return dt_criacao;
    }

    public void setDt_criacao(Timestamp dt_criacao) {
        this.dt_criacao = dt_criacao;
    }

    public Timestamp getDt_exclusao() {
        return dt_exclusao;
    }

    public void setDt_exclusao(Timestamp dt_exclusao) {
        this.dt_exclusao = dt_exclusao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getDtExclusaoIndex(){
        if(this.dt_exclusao!=null) {
            String Data1 = this.dt_exclusao.toString();
            Data1 = Data1.substring(0, 10);
            return Data1;
        }
        else
            return "";
    }

    public String getDataCriacaoIndex(){

        if(this.dt_criacao!=null) {
            String Data1 = this.dt_criacao.toString();
            Data1 = Data1.substring(0, 10);
            return Data1;
        }
        else
            return "";

    }

    public String informaCelular(){
        if(this.celular!=null){
           return  this.getCelular();
        }
        else{

           return "Sem celular cadastrado";
        }
    }

    public String informaTelefoneResidencial(){
        if(this.telefone!=null){
            return  this.getTelefone();
        }
        else{
            return "Sem telefone cadastrado";
        }
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String informaUsuarioAssociado(){

        if(this.getUsuario_id()==null){
            return "Não há usuário associado";

        }else{
            return ""+this.getUsuario_id();
        }
    }






    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOab() {
        return oab;
    }

    public void setOab(String oab) {
        this.oab = oab;
    }

    public Integer getCarga_horaria() {
        return carga_horaria;
    }

    public void setCarga_horaria(Integer carga_horaria) {
        this.carga_horaria = carga_horaria;
    }

    public TipoColaborador getTipoColaborador() {
        return tipoColaborador;
    }

    public void setTipoColaborador(TipoColaborador tipoColaborador) {
        this.tipoColaborador = tipoColaborador;
    }

    public TipoSetor getSetor() {
        return setor;
    }

    public void setSetor(TipoSetor setor) {
        this.setor = setor;
    }

    public String informaSituacao(){
        if(this.ativo==false)
            return "Colaborador desabilitado";
        else
            return "Colaborador habilitado";

    }

    public String informaMatricula(){
        if(this.matricula != null)
          return this.getMatricula_ufjf();
         else
             return "matrícula ufjf não cadastrada";


    }




}
