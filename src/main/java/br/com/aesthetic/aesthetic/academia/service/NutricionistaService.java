package br.com.aesthetic.aesthetic.academia.service;

import br.com.aesthetic.aesthetic.academia.domain.model.Aluno;
import br.com.aesthetic.aesthetic.academia.domain.model.Nutricionista;

import java.util.List;


public interface NutricionistaService {

    List<Nutricionista> findAll();

    Nutricionista findById(Long id);

    Nutricionista save(Nutricionista nutricionista);

    Nutricionista update(Nutricionista nutricionista);

}
