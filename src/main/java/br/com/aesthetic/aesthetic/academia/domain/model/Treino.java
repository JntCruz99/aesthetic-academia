package br.com.aesthetic.aesthetic.academia.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Treino implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean ativo;
    private String data;
    private String descricao;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Exercicio> exercicio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<Exercicio> getExercicio() {
        return exercicio;
    }

    public void setExercicio(List<Exercicio> exercicio) {
        this.exercicio = exercicio;
    }
}

