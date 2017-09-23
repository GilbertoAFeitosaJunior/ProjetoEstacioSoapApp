package br.com.projetoestacioapp.bean;
import java.io.Serializable;
import java.util.Date;
import br.com.projetoestacioapp.enumm.SexoEnum;
import mobi.stos.podataka_lib.annotations.Column;
import mobi.stos.podataka_lib.annotations.Entity;

/**
 * Created by feito on 21/09/2017.
 */

@Entity
public class Aluno implements Serializable {

    @Column
    private Long id;

    @Column
    private String nome;

    @Column
    private String foto;

    @Column
    private String nomeMae;

    @Column
    private int cep;

    @Column
    private String logradouro;

    @Column
    private String complemento;

    @Column(length = 50)
    private String bairro;

    @Column
    private String cidade;

    @Column
    private String uf;

    @Column
    private String email;

    @Column
    private int celular;

    @Column
    private int ddd;

    @Column
    private SexoEnum sexoEnum;

    @Column
    private Date dataDeNascimento;

    @Column
    private String cpf;

    @Column
    private String orgaoEmissor;

    @Column
    private Long rg;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public SexoEnum getSexoEnum() {
        return sexoEnum;
    }

    public void setSexoEnum(SexoEnum sexoEnum) {
        this.sexoEnum = sexoEnum;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getOrgaoEmissor() {
        return orgaoEmissor;
    }

    public void setOrgaoEmissor(String orgaoEmissor) {
        this.orgaoEmissor = orgaoEmissor;
    }

    public Long getRg() {
        return rg;
    }

    public void setRg(Long rg) {
        this.rg = rg;
    }
}
