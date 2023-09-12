package br.com.aesthetic.aesthetic.academia.service.impl;

import br.com.aesthetic.aesthetic.academia.domain.model.Professor;
import br.com.aesthetic.aesthetic.academia.domain.repository.ProfessorRepository;
import br.com.aesthetic.aesthetic.academia.service.ProfessorService;
import br.com.aesthetic.aesthetic.academia.service.exceptions.DuplicateEnrollmentException;
import br.com.aesthetic.aesthetic.academia.service.exceptions.EntityNotFoundExceptions;
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
        return professorRepository.findAll();
    }

    @Override
    public Professor findById(Long id) {
        return professorRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExceptions("Id Não encontrado: " + id));
    }

    @Override
    public Professor save(Professor professor) {
        if (professorRepository.existsByMatricula(professor.getMatricula())) {
            throw new DuplicateEnrollmentException("Matrícula já existe: " + professor.getMatricula());
        }
        professor.setAtivo(true);
        return professorRepository.save(professor);
    }

    @Override
    public Professor update(Professor professor, Long id) {
        Professor professorOptional = professorRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundExceptions("Id Não encontrado: " + id));
        if (professorOptional != null){
            if (professorOptional.getNome() != null){
                professorOptional.setNome(professor.getNome());
            } else if (professorOptional.getEmail() != null) {
                professorOptional.setEmail(professor.getEmail());
            }else if (professorOptional.getTelefone() != null){
                professorOptional.setTelefone(professor.getTelefone());
            }else if (!professorOptional.isAtivo())
                professorOptional.setAtivo(professor.isAtivo());
        }

        return professorRepository.save(professorOptional);
    }


}
