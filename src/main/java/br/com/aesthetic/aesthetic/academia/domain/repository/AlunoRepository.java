package br.com.aesthetic.aesthetic.academia.domain.repository;

import br.com.aesthetic.aesthetic.academia.domain.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    boolean existsByMatricula (String matricula);
}
