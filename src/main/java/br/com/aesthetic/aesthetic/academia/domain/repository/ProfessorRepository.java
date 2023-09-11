package br.com.aesthetic.aesthetic.academia.domain.repository;

import br.com.aesthetic.aesthetic.academia.domain.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
