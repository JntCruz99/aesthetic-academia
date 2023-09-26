package br.com.aesthetic.aesthetic.academia.service;

import br.com.aesthetic.aesthetic.academia.domain.model.Aluno;
import br.com.aesthetic.aesthetic.academia.domain.model.Dieta;
import br.com.aesthetic.aesthetic.academia.domain.model.Treino;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


public interface AlunoService {

    List<Aluno> findAll();

    Aluno findById(Long id);

    Aluno save(Aluno aluno) throws MessagingException, IOException;

    Aluno update(Aluno aluno, Long id);

    Aluno createDieta(Long idAluno, Long idNutricionista, Dieta dieta) throws MessagingException;

    Aluno createTreino(Long idAluno, Long idProfessor, Treino treino) throws MessagingException;
}
