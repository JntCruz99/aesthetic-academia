package br.com.aesthetic.aesthetic.academia.domain.repository;

import br.com.aesthetic.aesthetic.academia.domain.model.ExameFisico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface ExameFisicoRepository extends JpaRepository<ExameFisico, Long> {
}
