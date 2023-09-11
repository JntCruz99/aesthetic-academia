package br.com.aesthetic.aesthetic.academia.service;

import br.com.aesthetic.aesthetic.academia.domain.model.Aluno;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AlunoService {

    List<Aluno> findAll();

    Aluno findById(Long id);

    Aluno save(Aluno aluno);

    Aluno update(Aluno aluno, Long id);

}
