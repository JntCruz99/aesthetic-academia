package br.com.aesthetic.aesthetic.academia.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Exercicio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int serie;
    private int repeticoes;

    @ManyToOne
    @JoinColumn(name = "treino_id")
    @JsonIgnore
    private Treino treino;

    @OneToOne(cascade = CascadeType.ALL)
    private TipoExercicio tipo;

    @OneToOne(cascade = CascadeType.ALL)
    private Professor professor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public int getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }

    public TipoExercicio getTipo() {
        return tipo;
    }

    public void setTipo(TipoExercicio tipo) {
        this.tipo = tipo;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}

