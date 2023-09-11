package br.com.aesthetic.aesthetic.academia.domain.repository;

import br.com.aesthetic.aesthetic.academia.domain.model.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercicioRepository extends JpaRepository<Exercicio, Long> {
}
