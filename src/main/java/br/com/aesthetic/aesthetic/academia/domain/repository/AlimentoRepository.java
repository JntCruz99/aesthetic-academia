package br.com.aesthetic.aesthetic.academia.domain.repository;

import br.com.aesthetic.aesthetic.academia.domain.model.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlimentoRepository extends JpaRepository<Alimento,Long > {
}
