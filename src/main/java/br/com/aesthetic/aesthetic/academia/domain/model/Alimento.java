package br.com.aesthetic.aesthetic.academia.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Alimento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String alimento;
    private String medida;
    private int caloria;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "dieta_id")
    private Dieta dieta;

    public Alimento(String alimento, String medida, int caloria, Dieta dieta) {
        this.alimento = alimento;
        this.medida = medida;
        this.caloria = caloria;
        this.dieta = dieta;
    }

    public Alimento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlimento() {
        return alimento;
    }

    public void setAlimento(String alimento) {
        this.alimento = alimento;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public int getCaloria() {
        return caloria;
    }

    public void setCaloria(int caloria) {
        this.caloria = caloria;
    }

    public Dieta getDieta() {
        return dieta;
    }

    public void setDieta(Dieta dieta) {
        this.dieta = dieta;
    }

    @Override
    public String toString() {
        return "Alimento{" +
                ", alimento='" + alimento + '\'' +
                ", quantidade='" + medida + '\'' +
                ", caloria=" + caloria +
                ", dieta=" + dieta +
                '}';
    }
}

