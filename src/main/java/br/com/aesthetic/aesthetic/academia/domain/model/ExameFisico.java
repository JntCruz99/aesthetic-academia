package br.com.aesthetic.aesthetic.academia.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class ExameFisico implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String data;
    private float peso;
    private float altura;
    private float imc;
    private float peito;
    private float cintura;
    private float panturrilhaDireita;
    private float panturrilhaEsquerda;
    private float coxaDireita;
    private float coxaEsquerda;
    private float bracoDireito;
    private float bracoEsquerdo;
    private float anteBracoDireito;
    private float anteBracoEsquerdo;
    private float gluteo;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getImc() {
        return imc;
    }

    public void setImc(float imc) {
        this.imc = imc;
    }

    public float getPeito() {
        return peito;
    }

    public void setPeito(float peito) {
        this.peito = peito;
    }

    public float getCintura() {
        return cintura;
    }

    public void setCintura(float cintura) {
        this.cintura = cintura;
    }

    public float getPanturrilhaDireita() {
        return panturrilhaDireita;
    }

    public void setPanturrilhaDireita(float panturrilhaDireita) {
        this.panturrilhaDireita = panturrilhaDireita;
    }

    public float getPanturrilhaEsquerda() {
        return panturrilhaEsquerda;
    }

    public void setPanturrilhaEsquerda(float panturrilhaEsquerda) {
        this.panturrilhaEsquerda = panturrilhaEsquerda;
    }

    public float getCoxaDireita() {
        return coxaDireita;
    }

    public void setCoxaDireita(float coxaDireita) {
        this.coxaDireita = coxaDireita;
    }

    public float getCoxaEsquerda() {
        return coxaEsquerda;
    }

    public void setCoxaEsquerda(float coxaEsquerda) {
        this.coxaEsquerda = coxaEsquerda;
    }

    public float getBracoDireito() {
        return bracoDireito;
    }

    public void setBracoDireito(float bracoDireito) {
        this.bracoDireito = bracoDireito;
    }

    public float getBracoEsquerdo() {
        return bracoEsquerdo;
    }

    public void setBracoEsquerdo(float bracoEsquerdo) {
        this.bracoEsquerdo = bracoEsquerdo;
    }

    public float getAnteBracoDireito() {
        return anteBracoDireito;
    }

    public void setAnteBracoDireito(float anteBracoDireito) {
        this.anteBracoDireito = anteBracoDireito;
    }

    public float getAnteBracoEsquerdo() {
        return anteBracoEsquerdo;
    }

    public void setAnteBracoEsquerdo(float anteBracoEsquerdo) {
        this.anteBracoEsquerdo = anteBracoEsquerdo;
    }

    public float getGluteo() {
        return gluteo;
    }

    public void setGluteo(float gluteo) {
        this.gluteo = gluteo;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}

