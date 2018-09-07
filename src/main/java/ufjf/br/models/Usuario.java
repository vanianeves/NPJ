package ufjf.br.models;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import ufjf.br.service.ColaboradorService;
import ufjf.br.validation.PasswordMatches;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ValidateOnExecution;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "usuario")
//@PasswordMatches
public class Usuario {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    @NotBlank(message = "Preencha o Nome")
    private String nome;

    private String telefone;

    private String celular;

    @Column(name = "senha")
    //@NotBlank(message = "Deve ser informada uma senha")
    @Length(min = 6,message = "A senha deve conter no mínimo seis digítos")
    private String password;

    //@NotBlank(message = "Confirme a senha")
    //@Length(min = 6,message = "A senha deve conter no mínimo seis digítos")
    private String matchingPassword;

    @Email
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Preencha o e-mail")
    private String email;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false,insertable = false,updatable = false)
    private Timestamp dt_criacao;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column//(nullable = false)
    private Timestamp dt_exclusao;

    @OneToOne//(cascade=CascadeType.ALL)
    @Valid
    private Endereco endereco;

   // @OneToOne//(cascade=CascadeType.ALL)
    //private Colaborador colaborador;

    @OneToOne//(cascade=CascadeType.ALL)
    private PerfilUsuario perfil_usuario;

    @Column(nullable=false)
    private Boolean ativo;

    @Column
    private Integer id_Colaborador;

    public Integer getId_Colaborador() {
        return id_Colaborador;
    }

    public void setId_Colaborador(Integer id_Colaborador) {
        this.id_Colaborador = id_Colaborador;
    }

    public String informaSetor(){
        if(this.id_Colaborador!=null){
            ColaboradorService colaboradorService = new ColaboradorService();
            return "";//colaboradorService.findOne(this.getId_Colaborador()).getSetor().getSetor();
        }
        else
        {
          return "";
        }
    }

    public String informaMatricula(){
        if(this.id_Colaborador!=null){
            ColaboradorService colaboradorService =  new ColaboradorService();
            Colaborador colaborador= new Colaborador();
            //colaborador =colaboradorService.findOne(this.getId_Colaborador());
            String retorno  =   this.getId_Colaborador().toString();  // "";//.toString(); // está retornando o valor correto
            //retorno = colaborador.getMatricula_ufjf();
             return retorno;
        }
        else
        {
            return "";
        }
    }



    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }


    public String informaSituacao() { // função para informar a situação do usuario
            if(ativo!=null) {
                if (!this.ativo)
                    return "Usuário desabilitado!";
                else
                    return "Usuário habilitado";
            }
            else
                return  "valor nulo encontrado";
    }



    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
               // ", matricula_ufjf='" + matricula_ufjf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", celular='" + celular + '\'' +
                ", email='" + email + '\'' +
                ", dt_criacao=" + dt_criacao +
                ", dt_exclusao=" + dt_exclusao +
                ", endereco=" + endereco +
               // ", colaborador=" + colaborador +
                ", perfil_usuario=" + perfil_usuario +
                '}';
    }

    public Usuario() {
    }

    public Usuario(String nome, Boolean ativo,String matricula_ufjf, String telefone, String celular, String email, Endereco endereco, Colaborador colaborador, PerfilUsuario perfil_usuario) {
        this.nome = nome;
        //this.matricula_ufjf = matricula_ufjf;
        this.telefone = telefone;
        this.celular = celular;
        this.email = email;
        this.endereco = endereco;
        //this.colaborador = colaborador;
        this.perfil_usuario = perfil_usuario;
        this.ativo =ativo;
    }

    public Integer getId() {
        return id;
    }

   public void setId(Integer id) {
           this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

//    public String getMatricula_ufjf() {
//        return matricula_ufjf;
//    }
//
//    public void setMatricula_ufjf(String matricula_ufjf) {
//        this.matricula_ufjf = matricula_ufjf;
//    }
//
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDt_criacao() {

        String Data = this.dt_criacao.toString();
        Data = Data.substring(0,10);
        return Data;
    }

    public void setDt_criacao(Timestamp dt_criacao) {
        dt_criacao.toString();
        this.dt_criacao = dt_criacao;
    }

    public String get_dt_Exclusao_index(){

        if(this.dt_exclusao!=null) {
            String Data = this.dt_exclusao.toString();
            Data = Data.substring(0, 10);
            return Data;
        }
        else
            return "";
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

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

//    public Colaborador getColaborador() {
//        return colaborador;
//    }
//
//    public void setColaborador(Colaborador colaborador) {
//        this.colaborador = colaborador;
//    }
//
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public PerfilUsuario getPerfil_usuario() {
        return perfil_usuario;
    }

    public void setPerfil_usuario(PerfilUsuario perfil_usuario) {
        this.perfil_usuario = perfil_usuario;
    }

}
