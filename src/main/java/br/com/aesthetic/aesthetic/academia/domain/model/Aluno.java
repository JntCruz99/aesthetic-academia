package br.com.aesthetic.aesthetic.academia.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Aluno implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int idade;
    private String dataDeNascimento;
    private String endereco;
    private String telefone;
    private String email;
    private String dataDeCadastro;
    private boolean ativo;
    private String matricula;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "aluno")
    private List<Treino> treinos;

    @OneToMany(mappedBy = "aluno")
    private List<ExameFisico> examesFisicos;

    @OneToMany(mappedBy = "aluno")
    private List<Dieta> dietas;

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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(String dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Treino> getTreinos() {
        return treinos;
    }

    public void setTreinos(List<Treino> treinos) {
        this.treinos = treinos;
    }

    public List<ExameFisico> getExamesFisicos() {
        return examesFisicos;
    }

    public void setExamesFisicos(List<ExameFisico> examesFisicos) {
        this.examesFisicos = examesFisicos;
    }

    public List<Dieta> getDietas() {
        return dietas;
    }

    public void setDietas(List<Dieta> dietas) {
        this.dietas = dietas;
    }
}

