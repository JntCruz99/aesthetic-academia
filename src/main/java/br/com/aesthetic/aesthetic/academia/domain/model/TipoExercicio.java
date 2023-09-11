package br.com.aesthetic.aesthetic.academia.domain.model;

import br.com.aesthetic.aesthetic.academia.domain.model.enums.GrupoMuscular;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class TipoExercicio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private GrupoMuscular grupomuscular;

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

    public GrupoMuscular getGrupomuscular() {
        return grupomuscular;
    }

    public void setGrupomuscular(GrupoMuscular grupomuscular) {
        this.grupomuscular = grupomuscular;
    }
}

