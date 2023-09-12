package br.com.aesthetic.aesthetic.academia.service;

import br.com.aesthetic.aesthetic.academia.domain.model.Nutricionista;
import br.com.aesthetic.aesthetic.academia.domain.model.Professor;

import java.util.List;


public interface ProfessorService {
    List<Professor> findAll();

    Professor findById(Long id);

    Professor save(Professor professor);

    Professor update(Professor professor, Long id);
}
