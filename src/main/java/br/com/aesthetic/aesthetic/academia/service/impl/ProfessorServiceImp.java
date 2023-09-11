package br.com.aesthetic.aesthetic.academia.service.impl;

import br.com.aesthetic.aesthetic.academia.domain.model.Professor;
import br.com.aesthetic.aesthetic.academia.domain.repository.ProfessorRepository;
import br.com.aesthetic.aesthetic.academia.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImp implements ProfessorService {

    @Autowired
    private final ProfessorRepository professorRepository;

    public ProfessorServiceImp(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public List<Professor> findAll() {
        return null;
    }

    @Override
    public Professor findById(Long id) {
        return null;
    }

    @Override
    public Professor save(Professor professor) {
        return null;
    }

    @Override
    public Professor update(Professor professor) {
        return null;
    }
}
