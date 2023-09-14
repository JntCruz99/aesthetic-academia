package br.com.aesthetic.aesthetic.academia.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Dieta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int caloriasDiarias;
    private String descricao;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @OneToOne(cascade = CascadeType.ALL)
    private Nutricionista nutricionista;

    @OneToMany(mappedBy = "dieta")
    private List<Alimento> cafeDaManha;

    @OneToMany(mappedBy = "dieta")
    private List<Alimento> lancheDaManha;

    @OneToMany(mappedBy = "dieta")
    private List<Alimento> almoco;

    @OneToMany(mappedBy = "dieta")
    private List<Alimento> lancheDaTarde;

    @OneToMany(mappedBy = "dieta")
    private List<Alimento> janta;

    @OneToMany(mappedBy = "dieta")
    private List<Alimento> preTreino;

    @OneToMany(mappedBy = "dieta")
    private List<Alimento> posTreino;

    public Dieta() {
    }

    public Dieta(int caloriasDiarias, String descricao, Aluno aluno, Nutricionista nutricionista, List<Alimento> cafeDaManha, List<Alimento> lancheDaManha, List<Alimento> almoco, List<Alimento> lancheDaTarde, List<Alimento> janta, List<Alimento> preTreino, List<Alimento> posTreino) {
        this.caloriasDiarias = caloriasDiarias;
        this.descricao = descricao;
        this.aluno = aluno;
        this.nutricionista = nutricionista;
        this.cafeDaManha = cafeDaManha;
        this.lancheDaManha = lancheDaManha;
        this.almoco = almoco;
        this.lancheDaTarde = lancheDaTarde;
        this.janta = janta;
        this.preTreino = preTreino;
        this.posTreino = posTreino;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCaloriasDiarias() {
        return caloriasDiarias;
    }

    public void setCaloriasDiarias(int caloriasDiarias) {
        this.caloriasDiarias = caloriasDiarias;
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

    public Nutricionista getNutricionista() {
        return nutricionista;
    }

    public void setNutricionista(Nutricionista nutricionista) {
        this.nutricionista = nutricionista;
    }

    public List<Alimento> getCafeDaManha() {
        return cafeDaManha;
    }

    public void setCafeDaManha(List<Alimento> cafeDaManha) {
        this.cafeDaManha = cafeDaManha;
    }

    public List<Alimento> getLancheDaManha() {
        return lancheDaManha;
    }

    public void setLancheDaManha(List<Alimento> lancheDaManha) {
        this.lancheDaManha = lancheDaManha;
    }

    public List<Alimento> getAlmoco() {
        return almoco;
    }

    public void setAlmoco(List<Alimento> almoco) {
        this.almoco = almoco;
    }

    public List<Alimento> getLancheDaTarde() {
        return lancheDaTarde;
    }

    public void setLancheDaTarde(List<Alimento> lancheDaTarde) {
        this.lancheDaTarde = lancheDaTarde;
    }

    public List<Alimento> getJanta() {
        return janta;
    }

    public void setJanta(List<Alimento> janta) {
        this.janta = janta;
    }

    public List<Alimento> getPreTreino() {
        return preTreino;
    }

    public void setPreTreino(List<Alimento> preTreino) {
        this.preTreino = preTreino;
    }

    public List<Alimento> getPosTreino() {
        return posTreino;
    }

    public void setPosTreino(List<Alimento> posTreino) {
        this.posTreino = posTreino;
    }

    @Override
    public String toString() {
        return "Dieta{" +
                "caloriasDiarias=" + caloriasDiarias +
                ", descricao='" + descricao + '\'' +
                ", aluno=" + aluno +
                ", nutricionista=" + nutricionista +
                ", cafeDaManha=" + cafeDaManha +
                ", lancheDaManha=" + lancheDaManha +
                ", almoco=" + almoco +
                ", lancheDaTarde=" + lancheDaTarde +
                ", janta=" + janta +
                ", preTreino=" + preTreino +
                ", posTreino=" + posTreino +
                '}';
    }
}

